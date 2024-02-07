package entity;

import lombok.Getter;

@Getter
public enum Category {
  FOOD(1),
  ELECTRICITY(2),
  RESTAURANT(3),
  VACATION(4),
  COURSES(5),
  TOYS(6);


  private final int categoryId;

  Category(int categoryId) {
    this.categoryId = categoryId;
  }

  public static int getRandomCategoryId() {
    return (int)(Math.random() * values().length + 1);
  }

  public static Category getRandomCategory() {
    return values()[(int)(Math.random() * values().length)];
  }


//  public static CATEGORY getCategoryById(int id) throws CouponSystemException {
//    if (id <= 0 || id >= values().length) {
//      throw new CouponSystemException(ErrorMessage.INVALID_CATEGORY);
//    }
//    return values()[id - 1];
//  }

}
