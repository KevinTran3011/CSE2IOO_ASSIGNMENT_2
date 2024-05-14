import java.io.*;
import java.util.*;

public class PatientRecordSystemMenu {

  private Scanner scanner;
  private PatientRecordSystem patientRecordSystem;

  public PatientRecordSystemMenu() {
    scanner = new Scanner(System.in);
    patientRecordSystem = new PatientRecordSystem();
  }

  private void displayMenu() {
    System.out.println("=====================");
    System.out.println("Patient Record System");
    System.out.println("=====================");
    System.out.println("1. Add a measurement observation type");
    System.out.println("2. Add a category observation type");
    System.out.println("3. Add a patient");
    System.out.println("4. Add a measurement observation");
    System.out.println("5. Add a category observation");
    System.out.println("6. Display details of an observation type");
    System.out.println("7. Display a patient record by the patient id");
    System.out.println("8. Save data");
    System.out.println("9. Load data");
    System.out.println("D. Display all data for inspection");
    System.out.println("X. Exit");
    System.out.print("Please enter an option (1-9 or D or X): ");
  }

  private void addMeasurementObservationType() {
    System.out.print("Enter observation type code: ");
    String code = scanner.nextLine();
    System.out.print("Enter observation type name: ");
    String name = scanner.nextLine();
    System.out.print("Enter unit: ");
    String unit = scanner.nextLine();
    patientRecordSystem.addMeasurementObservationType(code, name, unit);
    System.out.println("Measurement observation type added successfully.");
  }

  private void addCategoryObservationType() {
    System.out.print("Enter observation type code: ");
    String code = scanner.nextLine();
    System.out.print("Enter observation type name: ");
    String name = scanner.nextLine();
    System.out.print("Enter categories (comma-separated): ");
    String[] categories = scanner.nextLine().split(", ");
    patientRecordSystem.addCategoryObservationType(code, name, categories);
    System.out.println("Category observation type added successfully.");
  }

  private void addPatient() {
    System.out.print("Enter patient ID: ");
    String id = scanner.nextLine();
    System.out.print("Enter patient name: ");
    String name = scanner.nextLine();
    patientRecordSystem.addPatient(id, name);
    System.out.println("Patient added successfully.");
  }

  private void addMeasurementObservation() {
    System.out.print("Enter patient ID: ");
    String patientId = scanner.nextLine();
    System.out.print("Enter observation type code: ");
    String observationTypeCode = scanner.nextLine();
    System.out.print("Enter observation value: ");
    double value = Double.parseDouble(scanner.nextLine());
    patientRecordSystem.addMeasurementObservation(
      patientId,
      observationTypeCode,
      value
    );
    System.out.println("Measurement observation added successfully.");
  }

  private void addCategoryObservation() {
    System.out.print("Enter patient ID: ");
    String patientId = scanner.nextLine();
    System.out.print("Enter observation type code: ");
    String observationTypeCode = scanner.nextLine();
    System.out.print("Enter observation value: ");
    String value = scanner.nextLine();
    patientRecordSystem.addCategoryObservation(
      patientId,
      observationTypeCode,
      value
    );
    System.out.println("Category observation added successfully.");
  }

  private void displayObservationTypeDetails() {
    System.out.print("Enter observation type code: ");
    String code = scanner.nextLine();
    ObservationType observationType = patientRecordSystem
      .getObservationTypes()
      .get(code);
    if (observationType != null) {
      System.out.println(observationType);
    } else {
      System.out.println("Observation type not found.");
    }
  }

  private void displayPatientRecord() {
    System.out.print("Enter patient ID: ");
    String id = scanner.nextLine();
    Patient patient = patientRecordSystem.getPatients().get(id);
    if (patient != null) {
      System.out.println(patient);
    } else {
      System.out.println("Patient not found.");
    }
  }

  public void run() {
    boolean running = true;
    while (running) {
      displayMenu();
      String option = scanner.nextLine().toUpperCase();
      switch (option) {
        case "1":
          addMeasurementObservationType();
          break;
        case "2":
          addCategoryObservationType();
          break;
        case "3":
          addPatient();
          break;
        case "4":
          addMeasurementObservation();
          break;
        case "5":
          addCategoryObservation();
          break;
        case "6":
          displayObservationTypeDetails();
          break;
        case "7":
          displayPatientRecord();
          break;
        case "8":
          try {
            patientRecordSystem.saveData();
            System.out.println("Data saved successfully.");
          } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
          }
          break;
        case "9":
          try {
            patientRecordSystem.loadData();
            System.out.println("Data loaded successfully.");
          } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
          }
          break;
        case "D":
          System.out.println(patientRecordSystem);
          break;
        case "X":
          running = false;
          break;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
    scanner.close();
  }

  public static void main(String[] args) {
    PatientRecordSystemMenu menu = new PatientRecordSystemMenu();
    menu.run();
  }
}
