public class CategoryObservationType extends ObservationType {

  private String[] categories;

  public CategoryObservationType(
    String code,
    String name,
    String[] categories
  ) {
    super(code, name);
    this.categories = categories;
  }

  public String[] getCategories() {
    return categories;
  }

  public boolean isValidCategory(String category) {
    for (String c : categories) {
      if (c.equals(category)) {
        return true;
      }
    }
    return false;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(
      "CategoryObservationType[code: " +
      getCode() +
      ", name: " +
      getName() +
      ", categories: |"
    );
    for (int i = 0; i < categories.length; i++) {
      sb.append(categories[i]);
      if (i < categories.length - 1) {
        sb.append("|");
      }
    }
    sb.append("|]");
    return sb.toString();
  }
}
