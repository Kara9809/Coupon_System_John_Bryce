package exception;

public class CouponSystemException extends Exception{

    public CouponSystemException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }


}

