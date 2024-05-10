abstract class Observation {
    private ObservationType observationType;

    public Observation(ObservationType observationType) {
        this.observationType = observationType;
    }

    public ObservationType getObservationType() {
        return observationType;
    }
}