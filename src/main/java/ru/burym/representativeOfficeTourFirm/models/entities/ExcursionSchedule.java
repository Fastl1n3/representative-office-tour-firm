package ru.burym.representativeOfficeTourFirm.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExcursionSchedule implements Persistable<ExcursionSchedule.ExcursionSchedulePK> {

    @Id
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    private ExcursionSchedulePK excursionSchedulePK;

    @Transient
    private boolean isNew;

    @Override
    public ExcursionSchedulePK getId() {
        return excursionSchedulePK;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PersistenceCreator
    public ExcursionSchedule(ExcursionSchedulePK excursionSchedulePK) {
        this.excursionSchedulePK = excursionSchedulePK;
        isNew = false;
    }

    public ExcursionSchedule(ExcursionSchedulePK excursionSchedulePK, boolean isNew) {
        this.excursionSchedulePK = excursionSchedulePK;
        this.isNew = isNew;
    }

//    public ExcursionSchedule(int excursionId, LocalDateTime dateTime, boolean isNew) {
//        this.excursionSchedulePK = new ExcursionSchedulePK(excursionId, dateTime);
//        this.isNew = isNew;
//    }
    @Transient
    private int excursionId;

    @Transient
    private LocalDateTime dateTime;

    public record ExcursionSchedulePK(Integer excursionId, LocalDateTime dateTime) {}
}
