import java.util.*;

class Patient {

  private String id;
  private String name;
  private HashMap<ObservationType, Observation> observations;

  public Patient(String id, String name) {
    this.id = id;
    this.name = name;
    observations = new HashMap<>();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public HashMap<ObservationType, Observation> getObservations() {
    return observations;
  }

  public void addObservation(Observation observation) {
    observations.put(observation.getObservationType(), observation);
  }

  public boolean hasMeasurementObservation(MeasurementObservationType type) {
    return observations.containsKey(type);
  }

  public boolean hasCategoryObservation(CategoryObservationType type) {
    return observations.containsKey(type);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Patient id: " + id + ", name: " + name + "\nObservations:\n");
    for (Observation observation : observations.values()) {
      sb.append("- " + observation + "\n");
    }
    return sb.toString();
  }
}
