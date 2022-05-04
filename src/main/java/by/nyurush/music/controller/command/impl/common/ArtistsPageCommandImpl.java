package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArtistsPageCommandImpl implements Command {
    private final ArtistService artistService = new ArtistService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        req.setAttribute(ConstantAttributes.ARTISTS_LIST, artistService.findAll());
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ARTISTS);
    }
}
