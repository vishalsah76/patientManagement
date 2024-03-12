package com.example.patienthub.service;

import com.example.patienthub.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient getPatientById(Long id);

    List<Patient> getAllPatients();

    Patient addPatient(Patient patient);

    Patient updatePatient(Long id);

    void deletePatient(Long id);
}
