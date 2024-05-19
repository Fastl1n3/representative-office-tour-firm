package ru.burym.representativeOfficeTourFirm.models.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class TouristGroup {

    @Id
    private Integer id;

    @Setter
    private Integer touristId;

    @Setter
    private Integer groupId;

    @Setter
    private String touristType;

    @Setter
    @Transient
    private BigDecimal cost;

    public TouristGroup(int touristId, int groupId, String touristType, BigDecimal cost) {
        this.touristId = touristId;
        this.groupId = groupId;
        this.touristType = touristType;
        this.cost = cost;
    }
}
