package com.example.patienthub.dao;

import com.example.patienthub.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Long> {
}
