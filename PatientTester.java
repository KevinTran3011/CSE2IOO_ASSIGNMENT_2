public class  PatientTester{
  public static void main(String[] args) {
    Patient patient1 = new Patient("P001", "John Doe");
    Patient patient2 = new Patient("P002", "Jane Smith");

    System.out.println("Patient 1: " + patient1);
    System.out.println("Patient 2: " + patient2);

    ObservationType obType1 = new MeasurementObservationType("T100", "Blood Pressure", "mmHg");
    ObservationType obType2 = new CategoryObservationType("T200", "Blood Type", new String[]{"A", "B", "AB", "O"});

    patient1.addObservation(new MeasurementObservation(obType1, 120.0));
    patient1.addObservation(new CategoryObservation(obType2, "A"));

    System.out.println("Patient 1 with observations: " + patient1);
}
}