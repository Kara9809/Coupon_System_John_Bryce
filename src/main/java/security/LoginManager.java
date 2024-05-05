package security;


import exception.CouponSystemException;
import exception.ErrorMessage;
import facade.*;
import lombok.Getter;

import java.sql.SQLException;

public class LoginManager {

    @Getter
    private static final LoginManager instance = new LoginManager();

    private LoginManager() {
    }

    public ClientFacade loginManagement(String email, String password, ClientType clientType) throws CouponSystemException, SQLException {
        switch (clientType) {
            case ADMINISTRATOR:
                AdminFacade adminFacade = AdminFacadeImpl.getInstance();
                if (adminFacade.login(email, password)) {
                    return (ClientFacade) adminFacade;
                }
                break;
            case COMPANY:
                CompanyFacade companyFacade = CompanyFacadeImpl.getInstance();
                if (companyFacade.login(email, password)) {
                    return (ClientFacade) companyFacade;
                }
                break;
            case CUSTOMER:
                CustomerFacade customerFacade = CustomerFacadeImpl.getInstance();
                if (customerFacade.login(email, password)) {
                    return (ClientFacade) customerFacade;
                }
                break;
            default:
                throw new CouponSystemException(ErrorMessage.INVALID_CLIENT_TYPE);
        }
        throw new CouponSystemException(ErrorMessage.LOGIN_FAILED);
    }

}