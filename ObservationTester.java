public class ObservationTester {
    public static void main(String[] args) {
        ObservationType obType1 = new MeasurementObservationType("T100", "Blood Pressure", "mmHg");
        ObservationType obType2 = new CategoryObservationType("T200", "Blood Type", new String[]{"A", "B", "AB", "O"});

        System.out.println("Observation Type 1: " + obType1);
        System.out.println("Observation Type 2: " + obType2);
    }
}
