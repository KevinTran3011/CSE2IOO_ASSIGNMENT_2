import java.util.*;

class PatientRecordSystem {

  private HashMap<String, ObservationType> observationTypes;
  private HashMap<String, Patient> patients;

  public PatientRecordSystem() {
    observationTypes = new HashMap<>();
    patients = new HashMap<>();
  }

  public void addMeasurementObservationType(
    String code,
    String name,
    String unit
  ) {
    if (observationTypes.containsKey(code)) {
      throw new RuntimeException(
        "Observation type with code " + code + " already exists."
      );
    }
    ObservationType type = new MeasurementObservationType(code, name, unit);
    observationTypes.put(code, type);
  }

  public void addCategoryObservationType(
    String code,
    String name,
    String[] categories
  ) {
    if (observationTypes.containsKey(code)) {
      throw new RuntimeException(
        "Observation type with code " + code + " already exists."
      );
    }
    ObservationType type = new CategoryObservationType(code, name, categories);
    observationTypes.put(code, type);
  }

  public void addPatient(String id, String name) {
    if (patients.containsKey(id)) {
      throw new RuntimeException("Patient with ID " + id + " already exists.");
    }
    Patient patient = new Patient(id, name);
    patients.put(id, patient);
  }

  public void addMeasurementObservation(
    String patientId,
    String observationTypeCode,
    double value
  ) {
    Patient patient = patients.get(patientId);
    if (patient == null) {
      throw new RuntimeException(
        "Patient with ID " + patientId + " does not exist."
      );
    }
    ObservationType type = observationTypes.get(observationTypeCode);
    if (type == null || !(type instanceof MeasurementObservationType)) {
      throw new RuntimeException(
        "Invalid observation type code " + observationTypeCode
      );
    }
    if (patient.hasMeasurementObservation((MeasurementObservationType) type)) {
      throw new RuntimeException(
        "Patient already has a measurement observation of type " +
        type.getName()
      );
    }
    MeasurementObservation observation = new MeasurementObservation(
      type,
      value
    );
    patient.addObservation(observation);
  }

  public void addCategoryObservation(
    String patientId,
    String observationTypeCode,
    String value
  ) {
    Patient patient = patients.get(patientId);
    if (patient == null) {
      throw new RuntimeException(
        "Patient with ID " + patientId + " does not exist."
      );
    }
    ObservationType type = observationTypes.get(observationTypeCode);
    if (type == null || !(type instanceof CategoryObservationType)) {
      throw new RuntimeException(
        "Invalid observation type code " + observationTypeCode
      );
    }
    CategoryObservationType categoryType = (CategoryObservationType) type;
    if (!categoryType.isValidCategory(value)) {
      throw new RuntimeException(
        "Invalid category value " +
        value +
        " for observation type " +
        type.getName()
      );
    }
    if (patient.hasCategoryObservation(categoryType)) {
      throw new RuntimeException(
        "Patient already has a category observation of type " + type.getName()
      );
    }
    CategoryObservation observation = new CategoryObservation(type, value);
    patient.addObservation(observation);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("PATIENT RECORD SYSTEM DATA\n");
    sb.append("OBSERVATION TYPES:\n");
    for (ObservationType type : observationTypes.values()) {
      sb.append("-- " + type + "\n");
    }
    sb.append("PATIENTS:\n");
    for (Patient patient : patients.values()) {
      sb.append(patient + "\n");
    }
    return sb.toString();
  }
}
