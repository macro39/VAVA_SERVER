package com.olejnik_macek.Olejnik_Macek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @Size(max = 17)
    @Column(name = "car_vin")
    private String carVIN;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_info_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private CarInfo carInfo;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "year_of_production")
    private Date yearOfProduction;

    @NotNull
    @Column(name = "mileage")
    private Integer mileAge;

    @NotBlank
    @Size(max = 20)
    @Column(name = "spz")
    private String carSPZ;

    @ManyToMany
    @JoinTable(
            name = "car_repair",
            joinColumns = @JoinColumn(name = "car_vin"),
            inverseJoinColumns = @JoinColumn(name = "repair_id"))
    private Set<CarRepair> carRepairs;


    public String getCarVIN() {
        return carVIN;
    }

    public void setCarVIN(String carVIN) {
        this.carVIN = carVIN;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public Date getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Date yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Integer getMileAge() {
        return mileAge;
    }

    public void setMileAge(Integer mileAge) {
        this.mileAge = mileAge;
    }

    public String getCarSPZ() {
        return carSPZ;
    }

    public void setCarSPZ(String carSPZ) {
        this.carSPZ = carSPZ;
    }

    public Set<CarRepair> getCarRepairs() {
        return carRepairs;
    }

    public void setCarRepairs(Set<CarRepair> carRepairs) {
        this.carRepairs = carRepairs;
    }

    public void addRepair(CarRepair carRepair) {
        this.carRepairs.add(carRepair);
    }
}
