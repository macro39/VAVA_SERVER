package com.olejnik_macek.Olejnik_Macek.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "car_info")
public class CarInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_info_id")
    private Integer carInfoID;

    @NotBlank
    @Column(name = "brand")
    private String brand;

    @NotBlank
    @Column(name = "model")
    private String model;

    @NotBlank
    @Column(name = "body_style")
    private String bodyStyle = "Sedan";

    @NotNull
    @Column(name = "engine_capacity")
    private Double engineCapacity = 2.0;

    @NotNull
    @Column(name = "engine_power")
    private Integer enginePower = 120;

    @NotBlank
    @Column(name = "gear_box")
    private String gearBox = "manuálna";

    @NotBlank
    @Column(name = "fuel")
    private String fuel = "diesel";

    @NotBlank
    @Column(name = "color")
    private String color = "biela";

    @NotNull
    @Column(name = "price_per_day")
    private Double pricePerDay = 50.0;

    public Integer getCarInfoID() {
        return carInfoID;
    }

    public void setCarInfoID(Integer carInfoID) {
        this.carInfoID = carInfoID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
