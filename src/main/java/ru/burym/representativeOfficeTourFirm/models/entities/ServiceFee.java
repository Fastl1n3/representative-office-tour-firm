package ru.burym.representativeOfficeTourFirm.models.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
public class ServiceFee {

    @Id
    private String serviceType;

    @Min(value = 0, message = "Fee should not be negative")
    @Max(value = 100, message = "Fee should not exceed 100")
    @Setter
    private int fee;

    public ServiceFee(String serviceType, int fee) {
        this.serviceType = serviceType;
        this.fee = fee;
    }
}
