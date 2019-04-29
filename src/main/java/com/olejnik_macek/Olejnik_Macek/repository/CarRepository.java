package com.olejnik_macek.Olejnik_Macek.repository;

import com.olejnik_macek.Olejnik_Macek.model.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String>, JpaSpecificationExecutor {
    Car findByCarVIN(String carVIN);
    List<Car> findAllByCarVINContainingOrderByCarVINAscCarSPZAsc(
            String carVIN, Pageable pageable); // ked nejde je zly import pageable

    List<Car> findAllByCarVIN(String carVIN);
}
