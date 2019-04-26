package com.olejnik_macek.Olejnik_Macek.repository;

import com.olejnik_macek.Olejnik_Macek.model.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor {
    boolean existsByCustomerID(String customerID);
    //List<Customer> findAllByCustomerIDContainingOrderByLastNameAscFirstNameAsc(String customerID, Pageable pageable);
    List<Customer> findAllByCustomerIDContainingOrderByLastNameAscFirstNameAsc(String customerID, Pageable pageable);
}
