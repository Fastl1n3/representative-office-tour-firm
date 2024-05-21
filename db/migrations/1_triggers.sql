CREATE OR REPLACE PROCEDURE insert_spending(t_id int, service varchar(10), spended numeric(10,2), date_time timestamp)
    language plpgsql
AS $$
BEGIN
    INSERT INTO Spending(tourist_id, service_type, "cost", spend_date)
    VALUES (t_id, service, spended, date_time);
END;
$$;

CREATE OR REPLACE FUNCTION ins_spending_acc_tr_fnc()
    RETURNS trigger AS
$$
DECLARE
    price numeric(10,2);
    t_id int;
BEGIN
    SELECT day_price INTO price FROM Hotel_room WHERE id = NEW.hotel_room_id;
    SELECT tourist_id INTO t_id FROM Tourist_Group	WHERE Tourist_Group.id = NEW.tourist_group_id;
    call insert_spending(t_id, 'hotel', (price * date_part('day', NEW.check_out_date - NEW.check_in_date))::numeric, NEW.check_in_date);
    RETURN NEW;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_ins_spending_acc
    AFTER INSERT ON Accommodation
    FOR EACH ROW
EXECUTE PROCEDURE ins_spending_acc_tr_fnc();

CREATE OR REPLACE FUNCTION ins_spending_excur_tr_fnc()
    RETURNS trigger AS
$$
DECLARE
    exc_price numeric(10,2);
    t_id int;
BEGIN
    SELECT price INTO exc_price FROM Excursion WHERE Excursion.excursion_id  = NEW.excursion_id;
    SELECT tourist_id INTO t_id FROM Tourist_Group WHERE Tourist_Group.id = NEW.tourist_group_id;
    call insert_spending(t_id, 'excur', exc_price, NEW.visit_date);
    RETURN NEW;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_ins_spending_excur
    AFTER INSERT ON Tourist_Excursion
    FOR EACH ROW
EXECUTE PROCEDURE ins_spending_excur_tr_fnc();

CREATE OR REPLACE FUNCTION ins_spending_stor_tr_fnc()
    RETURNS trigger AS
$$
BEGIN
    call insert_spending(NEW.owner_id, 'cargo', NEW.price, NEW.delivery_date );
    RETURN NEW;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_ins_spending_stor
    AFTER INSERT ON Storage
    FOR EACH ROW
EXECUTE PROCEDURE ins_spending_stor_tr_fnc();

CREATE OR REPLACE FUNCTION ins_spending_deliv_tr_fnc()
    RETURNS trigger AS
$$
BEGIN
    call insert_spending(NEW.owner_id, 'deliv', NEW.package_price + NEW.insurance_price + NEW.delivery_price, NEW.compilation_date);
    RETURN NEW;
END;
$$ language plpgsql;


CREATE OR REPLACE TRIGGER tr_ins_spending_deliv
    AFTER INSERT ON Transport_list
    FOR EACH ROW
EXECUTE PROCEDURE ins_spending_deliv_tr_fnc();

CREATE OR REPLACE FUNCTION incr_people_num_tr_fnc()
    RETURNS trigger AS
$$
BEGIN
    UPDATE "Group" SET people_number = people_number + 1 WHERE "Group".group_id = NEW.group_id;
    RETURN NEW;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_insert_trst_grp
    AFTER INSERT ON Tourist_Group
    FOR EACH ROW
EXECUTE PROCEDURE incr_people_num_tr_fnc();

CREATE OR REPLACE FUNCTION decr_people_num_tr_fnc()
    RETURNS trigger AS
$$
BEGIN
    UPDATE "Group" SET people_number = people_number - 1 WHERE "Group".group_id = OLD.group_id;
    RETURN OLD;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_delete_trst_grp
    AFTER DELETE ON Tourist_Group
    FOR EACH ROW
EXECUTE PROCEDURE decr_people_num_tr_fnc();

CREATE OR REPLACE FUNCTION check_trst_grp_tr_fnc()
    RETURNS trigger AS
$$
DECLARE
    new_date_to timestamp;
    new_date_back timestamp;
BEGIN
    SELECT f_t.date_time, f_b.date_time INTO new_date_to, new_date_back FROM "Group" AS gr
                                                                                 JOIN Flight AS f_b ON gr.flight_back = f_b.flight_id
                                                                                 JOIN Flight AS f_t ON gr.flight_to = f_t.flight_id
    WHERE gr.group_id = NEW.group_id;
    IF EXISTS (
            SELECT tg.tourist_id, tg.group_id, f_t.date_time AS date_to, f_b.date_time AS date_back FROM Tourist_Group AS tg
                                                                                                             JOIN "Group" AS gr ON tg.group_id = gr.group_id
                                                                                                             JOIN Flight AS f_b ON gr.flight_back = f_b.flight_id
                                                                                                             JOIN Flight AS f_t ON gr.flight_to = f_t.flight_id
            WHERE tg.tourist_id = NEW.tourist_id AND
                (new_date_to <= f_b.date_time AND new_date_back >= f_t.date_time)
        )
    THEN
        RAISE EXCEPTION 'Tourist has another tour for these dates.';
    END IF;
    RETURN NEW;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_check_trst_grp
    BEFORE INSERT OR UPDATE ON Tourist_Group
    FOR EACH ROW
EXECUTE PROCEDURE check_trst_grp_tr_fnc();

CREATE OR REPLACE FUNCTION check_parent_tr_fnc()
    RETURNS trigger AS
$$
BEGIN
    IF NEW.is_child AND NEW.parent IS NULL
    THEN
        RAISE EXCEPTION 'Parent must be not null';
    END IF;
    RETURN NEW;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_check_parent
    BEFORE INSERT OR UPDATE ON Tourist
    FOR EACH ROW
EXECUTE PROCEDURE check_parent_tr_fnc();

CREATE OR REPLACE FUNCTION check_grp_plane_t_tr_fnc()
    RETURNS trigger AS
$$
DECLARE
    pl_t1 varchar(2);
    pl_t2 varchar(2);
    dep1 varchar(30);
    dep2 varchar(30);
BEGIN
    SELECT plane_type, depart_city INTO pl_t1, dep1 FROM Flight WHERE flight_id = NEW.flight_to;
    SELECT plane_type, depart_city INTO pl_t2, dep2 FROM Flight WHERE flight_id = NEW.flight_back;

    IF pl_t1='C' OR pl_t2='C'
    THEN
        RAISE EXCEPTION 'The plane must not be a cargo plane';
    END IF;

    IF dep1 != 'Moscow'
    THEN
        RAISE EXCEPTION 'The flight_to must be from Moscow and to Athens';
    END IF;

    IF dep2 != 'Athens'
    THEN
        RAISE EXCEPTION 'The flight_back must be from Athens and to Moscow';
    END IF;

    RETURN NEW;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_check_grp_plane
    BEFORE INSERT OR UPDATE ON "Group"
    FOR EACH ROW
EXECUTE PROCEDURE check_grp_plane_t_tr_fnc();

CREATE OR REPLACE FUNCTION check_cargo_plane_t_tr_fnc()
    RETURNS trigger AS
$$
BEGIN
    IF (SELECT depart_city FROM Flight WHERE flight_id = NEW.flight_id) != 'Athens'
    THEN
        RAISE EXCEPTION 'The cargo flight must be from Athens and to Moscow';
    END IF;

    RETURN NEW;
END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_check_cargo_plane
    BEFORE INSERT OR UPDATE ON Transport_list
    FOR EACH ROW
EXECUTE PROCEDURE check_cargo_plane_t_tr_fnc();

CREATE OR REPLACE FUNCTION contains_parent_in_grp_tr_fnc()
    RETURNS trigger AS
$$
DECLARE
    child bool;
    parent_id int;
BEGIN
    SELECT is_child, parent INTO child, parent_id FROM Tourist t WHERE t.tourist_id=NEW.tourist_id;
    IF child = false
    THEN
        RETURN NEW;
    END IF;

    IF NOT EXISTS (
            SELECT * FROM tourist_group tg WHERE tg.group_id = NEW.group_id AND tg.tourist_id=parent_id
        )
    THEN
        RAISE EXCEPTION 'The parent of this child is missing from the group';
    END IF;
    RETURN NEW;

END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_contains_parent_in_grp
    BEFORE INSERT ON tourist_group
    FOR EACH ROW
EXECUTE PROCEDURE contains_parent_in_grp_tr_fnc();

CREATE OR REPLACE FUNCTION contains_parent_in_excur_tr_fnc()
    RETURNS trigger AS
$$
DECLARE
    child bool;
    parent_id int;
BEGIN
    SELECT is_child, parent INTO child, parent_id FROM Tourist_group tg
                                                           JOIN Tourist t ON tg.tourist_id = t.tourist_id WHERE tg.id=NEW.tourist_group_id;
    IF child = false
    THEN
        RETURN NEW;
    END IF;

    IF NOT EXISTS (
            SELECT * FROM tourist_excursion te JOIN tourist_group tg ON tg.id = te.tourist_group_id
            WHERE te.excursion_id = NEW.excursion_id AND tg.tourist_id = parent_id AND te.visit_date = NEW.visit_date
        )
    THEN
        RAISE EXCEPTION 'The parent of this child is missing from the excursion';
    END IF;
    RETURN NEW;

END;
$$ language plpgsql;

CREATE OR REPLACE TRIGGER tr_contains_parent_in_excur
    BEFORE INSERT ON tourist_excursion
    FOR EACH ROW
EXECUTE PROCEDURE contains_parent_in_excur_tr_fnc();

