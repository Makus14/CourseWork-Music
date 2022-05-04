package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.UserService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubscriptionManagementCommandImpl implements Command {
    private final UserService userService = new UserService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(ConstantAttributes.USER);
        user.setSubscription(!user.getSubscription());
        if (userService.save(user) != null) {
            session.setAttribute(ConstantAttributes.USER, user);
        } else {
            req.setAttribute(ConstantAttributes.ERROR_MESSAGE, ConstantMessages.FAILED_TO_SUBSCRIBE);
        }
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
    }
}
