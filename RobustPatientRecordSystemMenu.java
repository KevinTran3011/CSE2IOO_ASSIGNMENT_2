import java.util.InputMismatchException;
import java.util.*;
import  java.io.*;

public class RobustPatientRecordSystemMenu extends PatientRecordSystemMenu {

    public RobustPatientRecordSystemMenu() {
        super();
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            String option = scanner.nextLine().toUpperCase();
            try {
                handleChoice(option);
                running = !option.equals("X");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void handleChoice(String option) throws Exception {
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
                patientRecordSystem.saveData();
                System.out.println("Data saved successfully.");
                break;
            case "9":
                patientRecordSystem.loadData();
                System.out.println("Data loaded successfully.");
                break;
            case "D":
                System.out.println(patientRecordSystem);
                break;
            case "X":
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void addMeasurementObservationType() throws Exception {
        System.out.print("Enter observation type code: ");
        String code = scanner.nextLine();
        System.out.print("Enter observation type name: ");
        String name = scanner.nextLine();
        System.out.print("Enter unit: ");
        String unit = scanner.nextLine();
        patientRecordSystem.addMeasurementObservationType(code, name, unit);
        System.out.println("Measurement observation type added successfully.");
    }

    private void addCategoryObservationType() throws Exception {
        System.out.print("Enter observation type code: ");
        String code = scanner.nextLine();
        System.out.print("Enter observation type name: ");
        String name = scanner.nextLine();
        System.out.print("Enter categories (comma-separated): ");
        String[] categories = scanner.nextLine().split(", ");
        patientRecordSystem.addCategoryObservationType(code, name, categories);
        System.out.println("Category observation type added successfully.");
    }

    private void addPatient() throws Exception {
        System.out.print("Enter patient ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        patientRecordSystem.addPatient(id, name);
        System.out.println("Patient added successfully.");
    }

    private void addMeasurementObservation() throws Exception {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter observation type code: ");
        String observationTypeCode = scanner.nextLine();
        System.out.print("Enter observation value: ");
        double value = 0;
        try {
            value = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new Exception("Invalid observation value. Please enter a valid double value.");
        }
        patientRecordSystem.addMeasurementObservation(patientId, observationTypeCode, value);
        System.out.println("Measurement observation added successfully.");
    }

    private void addCategoryObservation() throws Exception {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter observation type code: ");
        String observationTypeCode = scanner.nextLine();
        System.out.print("Enter observation value: ");
        String value = scanner.nextLine();
        patientRecordSystem.addCategoryObservation(patientId, observationTypeCode, value);
        System.out.println("Category observation added successfully.");
    }

    private void displayObservationTypeDetails() throws Exception {
        System.out.print("Enter observation type code: ");
        String code = scanner.nextLine();
        ObservationType observationType = patientRecordSystem.getObservationTypes().get(code);
        if (observationType != null) {
            System.out.println(observationType);
        } else {
            System.out.println("Observation type not found.");
        }
    }

    private void displayPatientRecord() throws Exception {
        System.out.print("Enter patient ID: ");
        String id = scanner.nextLine();
        Patient patient = patientRecordSystem.getPatients().get(id);
        if (patient != null) {
            System.out.println(patient);
        } else {
            System.out.println("Patient not found.");
        }
    }

    public static void main(String[] args) {
        RobustPatientRecordSystemMenu menu = new RobustPatientRecordSystemMenu();
        menu.run();
    }
}