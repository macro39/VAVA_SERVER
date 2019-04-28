package com.olejnik_macek.Olejnik_Macek.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "repair")
public class CarRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_id")
    private Integer repairID;

    @NotBlank
    @Column(name = "type")
    private String typeOfRepair;

    @NotNull
    @Column(name = "date")
    private Date dateOfService;

    @NotNull
    @Column(name = "price")
    private Float price;

    @NotBlank
    @Column(name = "service_name")
    private String serviceName;

    @NotBlank
    @Column(name = "service_location")
    private String serviceLocation;

    public Integer getRepairID() {
        return repairID;
    }

    public void setRepairID(Integer repairID) {
        this.repairID = repairID;
    }

    public String getTypeOfRepair() {
        return typeOfRepair;
    }

    public void setTypeOfRepair(String typeOfRepair) {
        this.typeOfRepair = typeOfRepair;
    }

    public Date getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(Date dateOfService) {
        this.dateOfService = dateOfService;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }
}
