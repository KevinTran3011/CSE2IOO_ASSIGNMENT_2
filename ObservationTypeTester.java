public class ObservationTypeTester {

  public static void main(String[] args) {
    // Testing ObservationType
    ObservationType observationType = new ObservationType(
      "1",
      "Type1",
      "This is type 1"
    );
    System.out.println(observationType.printObservationTypeDetails());

    // Modifying ObservationType
    observationType.setTypeId("2");
    observationType.setTypeName("Type2");
    observationType.setTypeDescription("This is type 2");
    System.out.println(observationType.printObservationTypeDetails());

    // Testing MeasurementObservationType
    MeasurementObservationType m = new MeasurementObservationType(
      "3",
      "Type3",
      "This is type 3",
      "kg"
    );
    System.out.println(m.toString());

    // Modifying MeasurementObservationType
    m.setTypeId("4");
    m.setTypeName("Type4");
    m.setTypeDescription("This is type 4");
    m.setUnitString("lbs");
    System.out.println(m.toString());
  }
}
