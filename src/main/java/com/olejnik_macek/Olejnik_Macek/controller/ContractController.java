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
import java.util.ArrayList;
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
