package com.olejnik_macek.Olejnik_Macek.controller;

import com.olejnik_macek.Olejnik_Macek.model.Car;
import com.olejnik_macek.Olejnik_Macek.model.CarInfo;
import com.olejnik_macek.Olejnik_Macek.model.Contract;
import com.olejnik_macek.Olejnik_Macek.model.Employee;
import com.olejnik_macek.Olejnik_Macek.repository.CarRepository;
import com.olejnik_macek.Olejnik_Macek.repository.ContractRepository;
import com.olejnik_macek.Olejnik_Macek.repository.CustomerRepository;
import com.olejnik_macek.Olejnik_Macek.repository.EmployeeRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ContractController {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/contract")
    public Boolean contract(@Valid @RequestBody Contract contract){

        contractRepository.save(contract);

        return true;
    }

    @GetMapping("/contract/byOffSet/{offSet}")
    public List<Contract> getContracts(@PathVariable (value = "offSet") Integer offSet){
        Pageable pageable = PageRequest.of(offSet,500);

        Page<Contract> page = contractRepository.findAll(pageable);

        List<Contract> list = page.getContent();

        return list;
    }

    @GetMapping("/contract/exists/{carVIN}/{dateFrom}/{dateTO}")
    public List<String> checkIfContractExistsInSpecificTimePeriod(
            @PathVariable (value = "carVIN") String carVIN,
            @PathVariable (value = "dateFrom") String dateFrom,
            @PathVariable (value = "dateTO") String dateTo) {
        Car car = carRepository.findById(carVIN).orElse(null);

        if(car == null) {
            return null;
        }

        Date dateFROM = null;
        Date dateTO = null;
        try {
            dateFROM = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
            dateTO = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Contract> contractList = contractRepository.findAllByCar(car);

        if(contractList.isEmpty()) {
            return null;
        }

        List<String> rentalPeriods = new ArrayList<>();

        for(Contract contract : contractList) {
            if(dateTO.before(contract.getDateFrom())) {
                continue;
            } else if(dateFROM.before(contract.getDateFrom()) && dateTO.before(contract.getDateFrom())) {
                continue;
            } else if(dateFROM.after(contract.getDateTo())) {
                continue;
            }

            rentalPeriods.add(new SimpleDateFormat("yyyy-MM-dd").format(contract.getDateFrom()) + " - " +
                   new SimpleDateFormat("yyyy-MM-dd").format(contract.getDateTo()));
        }
        if(rentalPeriods.isEmpty()) {
            return null;
        } else {
            return  rentalPeriods;
        }
    }

    @GetMapping("/contract/byEmployee/{employeeID}")
    public List<Contract> getContractsByEmployee(@PathVariable (value = "employeeID") Integer employeeID){
        List<Contract> contractList = contractRepository.findAll();

        List<Contract> listOfContractsWithSpecificEmployee = new ArrayList<>();

        for(Contract contract : contractList) {
            if(contract.getEmployee().getEmployee_id().equals(employeeID)) {
                listOfContractsWithSpecificEmployee.add(contract);
            }
        }

        return listOfContractsWithSpecificEmployee;
    }

    @GetMapping("/contract/byCar/{carVIN}")
    public List<Contract> getContractsByCar(@PathVariable (value = "carVIN") String carVIN){
        List<Contract> contractList = contractRepository.findAll();

        List<Contract> listOfContractsWithSpecificCar = new ArrayList<>();

        for(Contract contract : contractList) {
            if(contract.getCar().getCarVIN().contains(carVIN)) {
                listOfContractsWithSpecificCar.add(contract);
            }
        }

        return listOfContractsWithSpecificCar;
    }

    @GetMapping("/contract/byCustomer/{customerID}")
    public List<Contract> getContractsByCustomer(@PathVariable (value = "customerID") String customerID){
        List<Contract> contractList = contractRepository.findAll();

        List<Contract> listOfContractsWithSpecificCustomer = new ArrayList<>();

        for(Contract contract : contractList) {
            if(contract.getCustomer().getCustomerID().contains(customerID)) {
                listOfContractsWithSpecificCustomer.add(contract);
            }
        }

        return listOfContractsWithSpecificCustomer;
    }

    @DeleteMapping("/contract")
    public Boolean deleteContract(@Valid @RequestBody Contract contract) {
        contractRepository.delete(contract);

        return true;
    }
}
