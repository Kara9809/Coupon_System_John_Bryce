package entity;

import exception.CouponSystemException;
import exception.ErrorMessage;
import lombok.Getter;

@Getter
public enum CATEGORY {
  FOOD(1),
  ELECTRICITY(2),
  RESTAURANT(3),
  VACATION(4),
  COURSES(5),
  TOYS(6);


  private final int categoryId;

  CATEGORY(int categoryId) {
    this.categoryId = categoryId;
  }



//  public static CATEGORY getCategoryById(int id) throws CouponSystemException {
//    if (id <= 0 || id >= values().length) {
//      throw new CouponSystemException(ErrorMessage.INVALID_CATEGORY);
//    }
//    return values()[id - 1];
//  }

}
