package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.language.GenreUtil;
import by.nyurush.music.util.language.ResourceBundleUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class ChangeLanguageCommandImpl implements Command {
    private final TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String lang = req.getParameter(ConstantAttributes.LANG);
        String page = req.getParameter(ConstantAttributes.PAGE);

        HttpSession session = req.getSession();
        Cookie langCookie = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals(ConstantAttributes.LANGUAGE)) {
                langCookie = cookie;
                break;
            }
        }
        if (langCookie == null) {
            langCookie = new Cookie(ConstantAttributes.LANGUAGE, ConstantAttributes.DEFAULT_LANG);
        }

        if (lang.contains(ConstantAttributes.EN_LANG) || lang.contains(ConstantAttributes.RU_LANG)) {
            langCookie.setValue(lang);
            resp.addCookie(langCookie);
        } else {
            throw new UnsupportedOperationException("Unknown language: " + lang);
        }

        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        session.setAttribute(ConstantAttributes.GENRES, GenreUtil.getGenres(trackService.findAllGenres(), rb));

        return CommandResult.redirect(req.getServletPath() + "?command=" + page);
    }
}
