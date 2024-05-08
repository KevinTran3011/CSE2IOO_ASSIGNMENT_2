import java.util.List;

public class CategoryObservationType extends ObservationType {

  private List<String> categories;

  public CategoryObservationType(
    String typeId,
    String typeName,
    String typeDescription,
    List<String> categories
  ) {
    super(typeId, typeName, typeDescription);
    this.categories = categories;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public boolean isValidCategory(String category) {
    return categories.contains(category);
  }

  public String toString() {
    return (super.toString() + "\nCategories: " + categories);
  }
}
