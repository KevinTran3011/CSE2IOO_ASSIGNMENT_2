class MeasurementObservation extends Observation {
    private double value;

    public MeasurementObservation(ObservationType observationType, double value) {
        super(observationType);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String toString() {
        return "MeasurementObservation[observationType: " + getObservationType() + ", value: " + value + "]";
    }
}