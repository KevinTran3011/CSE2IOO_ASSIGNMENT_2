import  java.util.*;

class Patient {
  private String id;
  private String name;
  private ArrayList<Observation> observations;

  public Patient(String id, String name) {
      this.id = id;
      this.name = name;
      observations = new ArrayList<>();
  }

  public String getId() {
      return id;
  }

  public String getName() {
      return name;
  }

  public ArrayList<Observation> getObservations() {
      return observations;
  }

  public void addObservation(Observation observation) {
      observations.add(observation);
  }

  public boolean hasMeasurementObservation(MeasurementObservationType type) {
      for (Observation observation : observations) {
          if (observation instanceof MeasurementObservation && observation.getObservationType() == type) {
              return true;
          }
      }
      return false;
  }

  public boolean hasCategoryObservation(CategoryObservationType type) {
      for (Observation observation : observations) {
          if (observation instanceof CategoryObservation && observation.getObservationType() == type) {
              return true;
          }
      }
      return false;
  }

  public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Patient id: " + id + ", name: " + name + "\nObservations:\n");
      for (Observation observation : observations) {
          sb.append("- " + observation + "\n");
      }
      return sb.toString();
  }
}
