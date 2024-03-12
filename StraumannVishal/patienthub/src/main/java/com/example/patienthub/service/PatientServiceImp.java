package com.example.patienthub.service;

import com.example.patienthub.dao.PatientDao;
import com.example.patienthub.entity.Patient;
import com.example.patienthub.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService{

    @Autowired
    public PatientDao patientDao;

    @Override
    @Cacheable(value = "patients", key = "#id")
    public Patient getPatientById(Long id) {
        Patient patient= patientDao.getReferenceById(id);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found for id: " + id);
        }
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientDao.save(patient);
    }

    @Override
    public Patient updatePatient(Long id) {
        if (!patientDao.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found for id: " + id);
        }
        Patient patient=patientDao.getReferenceById(id);
        return patientDao.save(patient);
    }

    @Override
    @CacheEvict(value = "patients", key = "#id")
    public void deletePatient(Long id) {
        if (!patientDao.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientDao.deleteById(id);
    }
}
