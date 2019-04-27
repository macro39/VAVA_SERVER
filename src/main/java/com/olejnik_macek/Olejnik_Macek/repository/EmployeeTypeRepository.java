package com.olejnik_macek.Olejnik_Macek.repository;

import com.olejnik_macek.Olejnik_Macek.model.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {
    @Query("SELECT DISTINCT e.employee_type FROM EmployeeType e")
    ArrayList<String> getAllTypes();
}
