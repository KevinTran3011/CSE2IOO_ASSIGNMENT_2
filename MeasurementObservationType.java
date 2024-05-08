public class MeasurementObservationType extends ObservationType {

  private String unitString;

  public MeasurementObservationType(
    String typeId,
    String typeName,
    String typeDescription,
    String unitString
  ) {
    super(typeId, typeName, typeDescription);
    this.unitString = unitString;
  }

  public String getUnitString() {
    return unitString;
  }

  public void setUnitString(String unitString) {
    this.unitString = unitString;
  }

  public String toString() {
    return (super.toString() + "\nUnit String: " + unitString);
  }
}
