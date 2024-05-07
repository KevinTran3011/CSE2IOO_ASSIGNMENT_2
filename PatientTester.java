public class PatientTester {

  public static void patientTester() {
    Patient patient = new Patient("123123", "John Doe", "123 Main St.");
    System.out.println(patient);
    patient.setPatientId("456456");
    patient.setPatientName("Jane Doe");
    patient.setPatientAddress("456 Elm St.");
    System.out.println(patient.printPatientDetails());
  }

  public static void main(String[] args) {
    PatientTester.patientTester();
  }
}
