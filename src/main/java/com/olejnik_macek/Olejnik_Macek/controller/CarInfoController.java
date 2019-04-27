package com.olejnik_macek.Olejnik_Macek.controller;

import com.olejnik_macek.Olejnik_Macek.model.CarInfo;
import com.olejnik_macek.Olejnik_Macek.repository.CarInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CarInfoController {

    @Autowired
    CarInfoRepository carInfoRepository;

    @PostMapping("/carInfo")
    public Integer createCarInfo(@Valid @RequestBody CarInfo carInfo) {
        carInfoRepository.save(carInfo);
        carInfoRepository.flush();

        return carInfo.getCarInfoID();
    }

    @GetMapping("/carInfo/brand")
    public List<String> getAllBrands() {
        return carInfoRepository.getAllBrands();
    }

    @GetMapping("/carInfo/model/{brand}")
    public List<String> getAllModelsForBrand(@PathVariable(value = "brand") String brand) {
        return carInfoRepository.getAllSpecificModels(brand);
    }

    @GetMapping("/carInfo/bodyStyle")
    public List<String> getAllBodyStyles() {
        return carInfoRepository.getAllBodyStyles();
    }

    @GetMapping("/carInfo/gearBox")
    public List<String> getAllGearBoxes() {
        return carInfoRepository.getAllGearBoxes();
    }

    @GetMapping("/carInfo/fuel")
    public List<String> getAllFuels() {
        return carInfoRepository.getAllFuels();
    }

    @GetMapping("/carInfo/color")
    public List<String> getAllColors() {
        return carInfoRepository.getAllColors();
    }

    @GetMapping("/carInfo/getAll")
    public ArrayList<String>[] getAll() {
        //ArrayList<ArrayList<String>> group = new ArrayList<ArrayList<String>>(6);
        ArrayList<String>[] listOfAll = new ArrayList[5];

        listOfAll[0] = carInfoRepository.getAllBrands();
        listOfAll[1] = carInfoRepository.getAllBodyStyles();
        listOfAll[2] = carInfoRepository.getAllGearBoxes();
        listOfAll[3] = carInfoRepository.getAllFuels();
        listOfAll[4] = carInfoRepository.getAllColors();

        return listOfAll;
    }
}

