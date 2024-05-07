public class Patient {

  private String patientId;
  private String patientName;
  private String patientAddress;

  public Patient(String patientId, String patientName, String patientAddress) {
    this.patientId = patientId;
    this.patientName = patientName;
    this.patientAddress = patientAddress;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getPatientAddress() {
    return patientAddress;
  }

  public void setPatientAddress(String patientAddress) {
    this.patientAddress = patientAddress;
  }

  public void printPatientDetails() {
    System.out.println(
      "Patient ID: " +
      patientId +
      "\nPatient Name: " +
      patientName +
      "\nPatient Address: " +
      patientAddress
    );
  }
}
