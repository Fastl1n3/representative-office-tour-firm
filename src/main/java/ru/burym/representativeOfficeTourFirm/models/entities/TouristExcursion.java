package ru.burym.representativeOfficeTourFirm.models.entities;

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
public class TouristExcursion implements Persistable<TouristExcursion.TouristExcursionPK> {

    @Id
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    private TouristExcursionPK touristExcursionPK;

    @Transient
    private int touristGroupId;

    @Transient
    private int excursionId;

    private LocalDateTime visitDate;

    @Transient
    private boolean isNew;

    @Override
    public TouristExcursionPK getId() {
        return touristExcursionPK;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PersistenceCreator
    public TouristExcursion(TouristExcursionPK touristExcursionPK, LocalDateTime visitDate) {
        this.touristExcursionPK = touristExcursionPK;
        this.visitDate = visitDate;
        isNew = false;
    }


    public TouristExcursion(TouristExcursionPK touristExcursionPK, LocalDateTime visitDate, boolean isNew) {
        this.touristExcursionPK = touristExcursionPK;
        this.visitDate = visitDate;
        this.isNew = isNew;
    }


    public record TouristExcursionPK(Integer touristGroupId, Integer excursionId) {}
}
