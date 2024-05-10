public class PatientSystemTester {
    public static void main(String[] args) {
        try {
            PatientRecordSystem prs = new PatientRecordSystem();

            // Add observation types
            prs.addMeasurementObservationType("T100", "Blood Pressure", "mmHg");
            prs.addCategoryObservationType("T200", "Blood Type", new String[]{"A", "B", "AB", "O"});

            // Add patients
            prs.addPatient("P001", "John Doe");
            prs.addPatient("P002", "Jane Smith");

            // Add observations
            prs.addMeasurementObservation("P001", "T100", 120.0);
            prs.addCategoryObservation("P001", "T200", "A");
            prs.addCategoryObservation("P002", "T200", "B");

            System.out.println(prs);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
