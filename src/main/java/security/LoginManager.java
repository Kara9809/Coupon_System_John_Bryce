package security;


import exception.CouponSystemException;
import exception.ErrorMessage;
import facade.AdminFacade;
import facade.ClientFacade;
import lombok.Getter;

public class LoginManager {

    private AdminFacade adminFacade;

    @Getter
    private static final LoginManager instance = new LoginManager();

    private LoginManager() {
    }

    public ClientFacade loginManagement(String email, String password, ClientType clientType) throws CouponSystemException {
        ClientFacade clientFacade = null;
        switch (clientType) {
            case ADMINISTRATOR:

            case COMPANY:

            case CUSTOMER:
                if (adminFacade.login(email, password)) {
                    return (ClientFacade) adminFacade;
                }
                break;
            default:
                throw new CouponSystemException(ErrorMessage.INVALID_CLIENT_TYPE);

        }
        return clientFacade;
    }

}