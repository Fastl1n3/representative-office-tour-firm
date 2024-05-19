package ru.burym.representativeOfficeTourFirm.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table("Group")
public class Group {

    @Id
    @Setter
    private Integer groupId;

    @Setter
    private int peopleNumber;

    @Setter
    private Integer flightTo;

    @Setter
    private Integer flightBack;

    public Group(int flightTo, int flightBack) {
        this.flightTo = flightTo;
        this.flightBack = flightBack;
    }
}
