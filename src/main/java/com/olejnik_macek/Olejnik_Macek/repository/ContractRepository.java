package com.olejnik_macek.Olejnik_Macek.repository;

import com.olejnik_macek.Olejnik_Macek.model.Car;
import com.olejnik_macek.Olejnik_Macek.model.Contract;
import com.olejnik_macek.Olejnik_Macek.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer>, JpaSpecificationExecutor {

    List<Contract> findAllByEmployeeOrderByContractId(Employee employee, Pageable pageable);

    //List<Contract> findAllByCarOrderByContractId(Car car, Pageable pageable);
    List<Contract> findAllByCarVINContainingOrderByCarVIN(String car, Pageable pageable);
    List<Contract> findAllByCustomerIDContainingOrderByCustomerID(String customerID, Pageable pageable);
}
