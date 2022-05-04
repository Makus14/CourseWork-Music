package by.nyurush.music.util.validation;

import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.*;

public class UserDataValidator {
    private static final Logger LOGGER = LogManager.getLogger(UserDataValidator.class);

    public boolean isValid(HttpServletRequest request) {
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(request);

        String name = request.getParameter(FIRST_NAME);
        if (!StringUtil.areNotNullAndNotEmpty(name) || DataValidator.isIncorrectNameSurname(name)) {
            LOGGER.info("Invalid first name format was received:" + name);
            request.setAttribute(INVALID_FIRST_NAME, rb.getString(ConstantMessages.INVALID_NAME));
            return false;
        }
        String lastName = request.getParameter(LAST_NAME);
        if (!StringUtil.areNotNullAndNotEmpty(lastName) || DataValidator.isIncorrectNameSurname(lastName)) {
            LOGGER.info("Invalid name format was received:" + lastName);
            request.setAttribute(INVALID_LAST_NAME, rb.getString(ConstantMessages.INVALID_NAME));
            return false;
        }
        String login = request.getParameter(LOGIN);
        if (!StringUtil.areNotNullAndNotEmpty(login) || !DataValidator.isCorrectLogin(login)) {
            LOGGER.info("Invalid login format was received:" + login);
            request.setAttribute(INVALID_LOGIN, rb.getString(ConstantMessages.INVALID_LOGIN));
            return false;
        }
        String email = request.getParameter(EMAIL);
        if (!StringUtil.areNotNullAndNotEmpty(email) || !DataValidator.isCorrectEmail(email)) {
            LOGGER.info("Invalid email format was received:" + email);
            request.setAttribute(INVALID_EMAIL, rb.getString(ConstantMessages.INVALID_EMAIL));
            return false;
        }
        String password = request.getParameter(PASSWORD);
        if (!StringUtil.areNotNullAndNotEmpty(password) || !DataValidator.isCorrectPassword(password)) {
            LOGGER.info("Invalid password format was received");
            request.setAttribute(INVALID_PASS, rb.getString(ConstantMessages.INVALID_PASSWORD));
            return false;
        }
        String confPassword = request.getParameter(CONFIRM_PASSWORD);
        if (!StringUtil.areNotNullAndNotEmpty(confPassword) || !DataValidator.isCorrectPassword(confPassword)) {
            LOGGER.info("Invalid confirm password format was received");
            request.setAttribute(INVALID_CONFIRM_PASS, rb.getString(ConstantMessages.INVALID_PASSWORD));
            return false;
        }
        if (!StringUtil.isNullOrEmpty(confPassword) && !password.equals(confPassword)) {
            LOGGER.info("Passwords do not match");
            request.setAttribute(INVALID_PASS_MATCH, rb.getString(ConstantMessages.INVALID_PASSWORDS_MATCH));
            return false;
        }
        return true;
    }
}
