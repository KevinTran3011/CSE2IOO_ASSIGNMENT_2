class CategoryObservation extends Observation {
    private String value;

    public CategoryObservation(ObservationType observationType, String value) {
        super(observationType);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "CategoryObservation[observationType: " + getObservationType() + ", value: " + value + "]";
    }
}