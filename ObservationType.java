public class ObservationType {

  private String typeId;
  private String typeName;
  private String typeDescription;

  public ObservationType(
    String typeId,
    String typeName,
    String typeDescription
  ) {
    this.typeId = typeId;
    this.typeName = typeName;
    this.typeDescription = typeDescription;
  }

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeDescription() {
    return typeDescription;
  }

  public void setTypeDescription(String typeDescription) {
    this.typeDescription = typeDescription;
  }

  public String toString() {
    return (
      "Type ID: " +
      typeId +
      "\nType Name: " +
      typeName +
      "\nType Description: " +
      typeDescription
    );
  }

  public String printObservationTypeDetails() {
    return (this.toString());
  }
}
