package entity;

import exception.CouponSystemException;
import exception.ErrorMessage;

public enum CATEGORY {
  FOOD,
  ELECTRICITY,
  RESTAURANT,
  VACATION,
  COURSES,
  TOYS;

  public static CATEGORY getCategoryById(int id) throws CouponSystemException {
    if (id <= 0 || id >= values().length) {
      throw new CouponSystemException(ErrorMessage.INVALID_CATEGORY);
    }
    return values()[id - 1];
  }

}
