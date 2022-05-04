package by.nyurush.music.controller.filter;

import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "commandSecurityFilter", urlPatterns = {"/controller/*"})
public class CommandSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
        AccountRole role;
        if(account != null) {
            role = account.getRole();
        } else {
            role = AccountRole.GUEST;
        }

        Map<String, List<AccountRole>> commandRoleMap = initializeCommandRoleMap();
        String command = req.getParameter(ConstantAttributes.PARAMETER_COMMAND);
        if (commandRoleMap.containsKey(command)){
            List<AccountRole> roles = commandRoleMap.get(command);
            if (!roles.contains(role)){
                RequestDispatcher dispatcher = session.getServletContext().getRequestDispatcher(ConstantPathPages.PATH_PAGE_ERROR_NO_ACCESS);
                dispatcher.forward(req, resp);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


    private Map<String, List<AccountRole>> initializeCommandRoleMap(){
        List<AccountRole> client = new ArrayList<>(Collections.singletonList(AccountRole.CLIENT));
        List<AccountRole> admin = new ArrayList<>(Collections.singletonList(AccountRole.ADMIN));
        List<AccountRole> clientAndAdmin = new ArrayList<>(Arrays.asList(AccountRole.CLIENT, AccountRole.ADMIN));
        List<AccountRole> all = new ArrayList<>(Arrays.asList(AccountRole.CLIENT, AccountRole.ADMIN, AccountRole.GUEST));
        Map<String, List<AccountRole>> map = new HashMap<>();

        map.put("login", all);
        map.put("changeLanguage", all);
        map.put("signup", all);
        map.put("register", all);

        map.put("logout", clientAndAdmin);
        map.put("home", clientAndAdmin);
        map.put("genres", clientAndAdmin);
        map.put("artists", clientAndAdmin);
        map.put("search", clientAndAdmin);
        map.put("addToPlaylistPage", clientAndAdmin);
        map.put("addToPlaylist", clientAndAdmin);
        map.put("createAndAddToPlaylist", clientAndAdmin);
        map.put("playlistPage", clientAndAdmin);
        map.put("playlists", clientAndAdmin);
        map.put("deletePlaylist", clientAndAdmin);
        map.put("comments", clientAndAdmin);
        map.put("deleteComment", clientAndAdmin);

        map.put("profilePage", client);
        map.put("profile", client);
        map.put("favorites", client);
        map.put("subscription", client);
        map.put("addComment", client);

        map.put("addArtistPage", admin);
        map.put("addArtist", admin);
        map.put("addMusicPage", admin);
        map.put("addMusic", admin);
        map.put("addAlbumPage", admin);
        map.put("addAlbum", admin);
        map.put("deleteSong", admin);
        map.put("editSong", admin);

        return map;
    }

    @Override
    public void destroy() {

    }
}
