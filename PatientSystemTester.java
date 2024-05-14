public class PatientSystemTester {

  public static void main(String[] args) {
    try {
      PatientRecordSystem p = new PatientRecordSystem();

      // Add observation types
      p.addMeasurementObservationType("T100", "Blood Pressure", "mmHg");
      p.addCategoryObservationType(
        "T200",
        "Blood Type",
        new String[] { "A", "B", "AB", "O" }
      );

      // Add patients
      p.addPatient("P001", "John Doe");
      p.addPatient("P002", "Jane Smith");

      // Add observations
      p.addMeasurementObservation("P001", "T100", 120.0);
      p.addCategoryObservation("P001", "T200", "A");
      p.addCategoryObservation("P002", "T200", "B");

      System.out.println(p);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
