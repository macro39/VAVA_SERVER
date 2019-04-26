package com.olejnik_macek.Olejnik_Macek.repository;

import com.olejnik_macek.Olejnik_Macek.model.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {
}
