import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class PatientRecordSystem {

  protected HashMap<String, ObservationType> observationTypes;
  protected HashMap<String, Patient> patients;

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

  public HashMap<String, ObservationType> getObservationTypes() {
    return observationTypes;
  }

  public HashMap<String, Patient> getPatients() {
    return patients;
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

  public void saveData() throws IOException {
    saveMeasurementObservationTypes("PRS-MeasurementObservationTypes.txt");
    saveCategoryObservationTypes("PRS-CategoryObservationTypes.txt");
    savePatients("PRS-Patients.txt");
    saveMeasurementObservations("PRS-MeasurementObservations.txt");
    saveCategoryObservations("PRS-CategoryObservations.txt");
  }

  private void saveMeasurementObservationTypes(String fileName)
    throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
      for (ObservationType type : observationTypes.values()) {
        if (type instanceof MeasurementObservationType) {
          MeasurementObservationType measurementType = (MeasurementObservationType) type;
          writer.println(
            measurementType.getCode() +
            "; " +
            measurementType.getName() +
            "; " +
            measurementType.getUnit()
          );
        }
      }
    }
  }

  private void saveCategoryObservationTypes(String fileName)
    throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
      for (ObservationType type : observationTypes.values()) {
        if (type instanceof CategoryObservationType) {
          CategoryObservationType categoryType = (CategoryObservationType) type;
          StringBuilder categories = new StringBuilder();
          for (String category : categoryType.getCategories()) {
            categories.append(category).append(", ");
          }
          String categoriesStr = categories.substring(
            0,
            categories.length() - 2
          );
          writer.println(
            categoryType.getCode() +
            "; " +
            categoryType.getName() +
            "; " +
            categoriesStr
          );
        }
      }
    }
  }

  private void savePatients(String fileName) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
      for (Patient patient : patients.values()) {
        writer.println(patient.getId() + "; " + patient.getName());
      }
    }
  }

  private void saveMeasurementObservations(String fileName) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
      for (Patient patient : patients.values()) {
        for (Observation observation : patient.getObservations().values()) {
          if (observation instanceof MeasurementObservation) {
            MeasurementObservation measurement = (MeasurementObservation) observation;
            writer.println(
              patient.getId() +
              "; " +
              measurement.getObservationType().getCode() +
              "; " +
              measurement.getValue()
            );
          }
        }
      }
    }
  }

  private void saveCategoryObservations(String fileName) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
      for (Patient patient : patients.values()) {
        for (Observation observation : patient.getObservations().values()) {
          if (observation instanceof CategoryObservation) {
            CategoryObservation category = (CategoryObservation) observation;
            writer.println(
              patient.getId() +
              "; " +
              category.getObservationType().getCode() +
              "; " +
              category.getValue()
            );
          }
        }
      }
    }
  }

  public void loadData() throws IOException {
    loadMeasurementObservationTypes("PRS-MeasurementObservationTypes.txt");
    loadCategoryObservationTypes("PRS-CategoryObservationTypes.txt");
    loadPatients("PRS-Patients.txt");
    loadMeasurementObservations("PRS-MeasurementObservations.txt");
    loadCategoryObservations("PRS-CategoryObservations.txt");
  }

  private void loadMeasurementObservationTypes(String fileName)
    throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("; ");
        addMeasurementObservationType(parts[0], parts[1], parts[2]);
      }
    }
  }

  private void loadCategoryObservationTypes(String fileName)
    throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("; ");
        String[] categories = parts[2].split(", ");
        addCategoryObservationType(parts[0], parts[1], categories);
      }
    }
  }

  private void loadPatients(String fileName) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("; ");
        addPatient(parts[0], parts[1]);
      }
    }
  }

  private void loadMeasurementObservations(String fileName) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("; ");
        addMeasurementObservation(
          parts[0],
          parts[1],
          Double.parseDouble(parts[2])
        );
      }
    }
  }

  private void loadCategoryObservations(String fileName) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("; ");
        addCategoryObservation(parts[0], parts[1], parts[2]);
      }
    }
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
