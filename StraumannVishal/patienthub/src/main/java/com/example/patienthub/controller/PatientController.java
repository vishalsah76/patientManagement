package com.example.patienthub.controller;

import com.example.patienthub.entity.Patient;
import com.example.patienthub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable String id){
        return patientService.updatePatient(Long.valueOf(id));
    }
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable String id){
        try{
            patientService.deletePatient(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}