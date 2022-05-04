package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.service.impl.UserService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.GenreUtil;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.*;

public class RegisterCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommandImpl.class);
    private final UserService userService = new UserService();
    private final TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(request);
        UserDataValidator validator = new UserDataValidator();
        if (!validator.isValid(request)) {
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }

        User user = buildUser(request);
        if (!userService.isFreeLogin(user.getLogin())) {
            request.setAttribute(INVALID_LOGIN, rb.getString(ConstantMessages.TAKEN_LOGIN));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        } else if (!userService.isFreeEmail(user.getEmail())) {
            request.setAttribute(INVALID_EMAIL, rb.getString(ConstantMessages.TAKEN_EMAIL));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        } else {
            session.setAttribute(USER, userService.save(user));
            LOGGER.info("User with login = " + user.getLogin() + " was registered.");
        }
        session.setAttribute(ConstantAttributes.GENRES, GenreUtil.getGenres(trackService.findAllGenres(), rb));

        return CommandResult.redirect("/controller?command=home");
    }

    private User buildUser(HttpServletRequest request) {
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        return new User(null, login, password, AccountRole.CLIENT, firstName, lastName, email, false);
    }
}
