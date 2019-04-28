package com.olejnik_macek.Olejnik_Macek.repository;

import com.olejnik_macek.Olejnik_Macek.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("SELECT concat(s.serviceName, ', ', s.serviceLocation) FROM Service s ORDER BY s.serviceName")
    ArrayList<String> getAllServices();
}
