package com.olejnik_macek.Olejnik_Macek.controller;

import com.olejnik_macek.Olejnik_Macek.model.Car;
import com.olejnik_macek.Olejnik_Macek.model.CarInfo;
import com.olejnik_macek.Olejnik_Macek.repository.CarInfoRepository;
import com.olejnik_macek.Olejnik_Macek.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarInfoRepository carInfoRepository;

    // find just one by VIN
    @GetMapping("/car/{carVIN}")
    public Car getCarByVIN(@PathVariable (value = "carVIN") String carVIN) {
        return carRepository.findByCarVIN(carVIN);
    }

    // get 500
    @GetMapping("/car/byOffSet/{offSet}")
    public List<Car> getCarsWithOffSet(@PathVariable(value = "offSet") Integer offSet) {
        Pageable pageable = PageRequest.of(offSet,500, Sort.by("carVIN").and(Sort.by("carSPZ")));

        Page<Car> cars = carRepository.findAll(pageable);

        List<Car> listOfCars = cars.getContent();

        return listOfCars;
    }

    // find all by VIN
    @GetMapping("/car/{carVIN}/{offSet}")
    public List<Car> getCarsByVIN(@PathVariable (value = "carVIN") String carVIN,
                             @PathVariable (value = "offSet") Integer offSet) {
        Pageable pageable = PageRequest.of(offSet,500);

        return carRepository.findAllByCarVINContainingOrderByCarVINAscCarSPZAsc(carVIN,pageable);
    }

    // add car
    @PostMapping("/car")
    public Boolean createCar(@Valid @RequestBody Car car) {
        if(carRepository.existsById(car.getCarVIN())) {
            return false;
        }

        carInfoRepository.save(car.getCarInfo());

        carRepository.save(car);
        return true;
    }

    // update
    @PutMapping("/car")
    public Boolean updateCar(@Valid @RequestBody Car carDetail) {

        Car car = carRepository.findById(carDetail.getCarVIN()).orElse(null);
        CarInfo carInfo = carInfoRepository.findById(car.getCarInfo().getCarInfoID()).orElse(null);

        if(car == null || carInfo == null) {
            return  false;
        }

        carInfo.setEnginePower(carDetail.getCarInfo().getEnginePower());
        carInfo.setPricePerDay(carDetail.getCarInfo().getPricePerDay());
        carInfoRepository.save(car.getCarInfo());

        car.setCarSPZ(carDetail.getCarSPZ());
        car.setMileAge(carDetail.getMileAge());
        carRepository.save(car);

        return true;
    }

    // delete
    @DeleteMapping("/car/{carVIN}")
    public boolean deleteCar(@PathVariable(value = "carVIN") String carVIN) {

        Car car = carRepository.findById(carVIN).orElse(null);

        if(car == null) {
            return false;
        } else {
            carRepository.delete(car);
            return true;
        }
    }
}
