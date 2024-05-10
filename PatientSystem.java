import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;

// PatientRecordSystem class
class PatientRecordSystem {
    private ObservationType[] observationTypes;
    private Patient[] patients;
    private int numObservationTypes;
    private int numPatients;

    public PatientRecordSystem() {
        observationTypes = new ObservationType[50];
        patients = new Patient[100];
        numObservationTypes = 0;
        numPatients = 0;
    }

    public void addMeasurementObservationType(String code, String name, String unit) throws Exception {
        if (isObservationTypeCodePresent(code)) {
            throw new Exception("Observation type code already exists: " + code);
        }
        observationTypes[numObservationTypes++] = new MeasurementObservationType(code, name, unit);
    }

    public void addCategoryObservationType(String code, String name, String[] categories) throws Exception {
        if (isObservationTypeCodePresent(code)) {
            throw new Exception("Observation type code already exists: " + code);
        }
        observationTypes[numObservationTypes++] = new CategoryObservationType(code, name, categories);
    }

    public void addPatient(String id, String name) throws Exception {
        if (isPatientIdPresent(id)) {
            throw new Exception("Patient ID already exists: " + id);
        }
        patients[numPatients++] = new Patient(id, name);
    }

    public void addMeasurementObservation(String patientId, String observationTypeCode, double value) throws Exception {
        Patient patient = getPatientById(patientId);
        MeasurementObservationType observationType = (MeasurementObservationType) getObservationTypeByCode(observationTypeCode);
        if (patient.hasMeasurementObservation(observationType)) {
            throw new Exception("Patient already has a measurement observation of this type");
        }
        patient.addObservation(new MeasurementObservation(observationType, value));
    }

    public void addCategoryObservation(String patientId, String observationTypeCode, String value) throws Exception {
        Patient patient = getPatientById(patientId);
        CategoryObservationType observationType = (CategoryObservationType) getObservationTypeByCode(observationTypeCode);
        if (!observationType.isValidCategory(value)) {
            throw new Exception("Invalid category value: " + value);
        }
        if (patient.hasCategoryObservation(observationType)) {
            throw new Exception("Patient already has a category observation of this type");
        }
        patient.addObservation(new CategoryObservation(observationType, value));
    }

    private boolean isObservationTypeCodePresent(String code) {
        for (int i = 0; i < numObservationTypes; i++) {
            if (observationTypes[i].getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPatientIdPresent(String id) {
        for (int i = 0; i < numPatients; i++) {
            if (patients[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private Patient getPatientById(String id) throws Exception {
        for (int i = 0; i < numPatients; i++) {
            if (patients[i].getId().equals(id)) {
                return patients[i];
            }
        }
        throw new Exception("Patient not found with ID: " + id);
    }

    private ObservationType getObservationTypeByCode(String code) throws Exception {
        for (int i = 0; i < numObservationTypes; i++) {
            if (observationTypes[i].getCode().equals(code)) {
                return observationTypes[i];
            }
        }
        throw new Exception("Observation type not found with code: " + code);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PATIENT RECORD SYSTEM DATA\n");
        sb.append("OBSERVATION TYPES:\n");
        for (int i = 0; i < numObservationTypes; i++) {
            sb.append("-- " + observationTypes[i] + "\n");
        }
        sb.append("PATIENTS:\n");
        for (int i = 0; i < numPatients; i++) {
            sb.append("-- " + patients[i] + "\n");
        }
        return sb.toString();
    }
}
