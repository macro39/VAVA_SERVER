package com.olejnik_macek.Olejnik_Macek.repository;

import com.olejnik_macek.Olejnik_Macek.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor {
    boolean existsEmployeeByLogin(String login);
    Employee findByLoginAndPassword(String login, String password);
    List<Employee> findAllByLastNameContainingOrderByLastNameAscFirstNameAsc(String lastName, Pageable pageable);
}
