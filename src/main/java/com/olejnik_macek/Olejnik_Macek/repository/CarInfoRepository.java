package com.olejnik_macek.Olejnik_Macek.repository;

import com.olejnik_macek.Olejnik_Macek.model.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface CarInfoRepository extends JpaRepository<CarInfo, Integer> {

    @Query("SELECT DISTINCT c.brand FROM CarInfo c ORDER BY 1")
    ArrayList<String> getAllBrands();

    @Query("SELECT DISTINCT c.model FROM CarInfo c WHERE c.brand = ?1 ORDER BY 1")
    ArrayList<String> getAllSpecificModels(String brand);

    @Query("SELECT DISTINCT c.bodyStyle FROM CarInfo c ORDER BY 1")
    ArrayList<String> getAllBodyStyles();

    @Query("SELECT DISTINCT c.gearBox FROM CarInfo c ORDER BY 1")
    ArrayList<String> getAllGearBoxes();

    @Query("SELECT DISTINCT c.fuel FROM CarInfo c ORDER BY 1")
    ArrayList<String> getAllFuels();

    @Query("SELECT DISTINCT c.color FROM CarInfo c ORDER BY 1")
    ArrayList<String> getAllColors();
}
