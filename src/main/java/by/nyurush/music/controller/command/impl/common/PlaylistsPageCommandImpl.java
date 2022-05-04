package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Account;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AccountService;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlaylistsPageCommandImpl implements Command {
    private final PlaylistService playlistService = new PlaylistService();
    private final AccountService accountService = new AccountService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        if (req.getParameter(ConstantAttributes.ALL) == null) {
            Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
            req.setAttribute(ConstantAttributes.PLAYLIST_LIST, playlistService.findByUserId(account.getId()));
        } else {
            req.setAttribute(ConstantAttributes.PLAYLIST_LIST, playlistService.findByUserId(accountService.findAdmin().getId()));
        }
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
    }
}
