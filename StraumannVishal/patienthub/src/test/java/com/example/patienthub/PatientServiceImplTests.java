package com.example.patienthub;

import com.example.patienthub.dao.PatientDao;
import com.example.patienthub.entity.Patient;
import com.example.patienthub.exception.ResourceNotFoundException;
import com.example.patienthub.service.PatientServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class PatientServiceImplTests {

    @Mock
    private PatientDao patientDao;

    @InjectMocks
    private PatientServiceImp patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPatientByIdforValidID() {
        Long id = 1L;
        Patient expectedPatient = new Patient();
        expectedPatient.setId(id);
        when(patientDao.findById(id)).thenReturn(Optional.of(expectedPatient));
        Patient actualPatient = patientService.getPatientById(id);
        assertNotNull(actualPatient);
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    void testGetPatientByIdForInvalidID() {
        Long id = 1L;
        when(patientDao.findById(id)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById(id));
    }

    @Test
    void testGetAllPatients() {
        List<Patient> expectedPatients = new ArrayList<>();
        expectedPatients.add(new Patient());
        when(patientDao.findAll()).thenReturn(expectedPatients);
        List<Patient> actualPatients = patientService.getAllPatients();
        assertEquals(expectedPatients.size(), actualPatients.size());
        assertEquals(expectedPatients.get(0), actualPatients.get(0));
    }

    @Test
    void testCreatePatient() {
        Patient patient = new Patient();
        when(patientDao.save(patient)).thenReturn(patient);
        Patient createdPatient = patientService.addPatient(patient);
        assertNotNull(createdPatient);
        assertEquals(patient, createdPatient);
    }

    @Test
    void testUpdatePatientForValidID() {
        Long id = 1L;
        Patient existingPatient = new Patient();
        existingPatient.setId(id);
        Patient updatedPatient = new Patient();
        updatedPatient.setId(id);
        when(patientDao.existsById(id)).thenReturn(true);
        when(patientDao.save(updatedPatient)).thenReturn(updatedPatient);
        Patient result = patientService.updatePatient(id);
        assertNotNull(result);
        assertEquals(updatedPatient, result);
    }

    @Test
    void testUpdatePatientForInvalidID() {
        Long id = 1L;
        when(patientDao.existsById(id)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> patientService.updatePatient(id));
    }

    @Test
    void testDeletePatientForValidID() {
        Long id = 1L;
        when(patientDao.existsById(id)).thenReturn(true);
        patientService.deletePatient(id);
        verify(patientDao, times(1)).deleteById(id);
    }

    @Test
    void testDeletePatientForInvalidID() {
        Long id = 1L;
        when(patientDao.existsById(id)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> patientService.deletePatient(id));
    }
}