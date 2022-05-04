package by.nyurush.music.controller.command;

import by.nyurush.music.controller.command.impl.admin.*;
import by.nyurush.music.controller.command.impl.common.*;
import by.nyurush.music.controller.command.impl.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("login", new LoginCommandImpl());
        commands.put("signup", new SignUpCommandImpl());
        commands.put("register", new RegisterCommandImpl());
        commands.put("changeLanguage", new ChangeLanguageCommandImpl());
        commands.put("home", new HomeCommandImpl());
        commands.put("profilePage", new ProfilePageCommandImpl());
        commands.put("profile", new ProfileCommandImpl());
        commands.put("logout", new LogoutCommandImpl());
        commands.put("addArtistPage", new AddArtistPageCommandImpl());
        commands.put("addArtist", new AddArtistCommandImpl());
        commands.put("addMusicPage", new AddSongPageCommandImpl());
        commands.put("addMusic", new AddSongCommandImpl());
        commands.put("addAlbumPage", new AddAlbumPageCommandImpl());
        commands.put("addAlbum", new AddAlbumCommandImpl());
        commands.put("genres", new GenresCommandImpl());
        commands.put("artists", new ArtistsCommandImpl());
        commands.put("search", new SearchCommandImpl());
        commands.put("deleteSong", new DeleteSongCommandImpl());
        commands.put("editSong", new EditSongCommandImpl());
        commands.put("addToPlaylistPage", new AddToPlaylistPageCommandImpl());
        commands.put("addToPlaylist", new AddToPlaylistCommandImpl());
        commands.put("createAndAddToPlaylist", new CreatePlaylistAndAddSongCommandImpl());
        commands.put("favorites", new FavoritesCommandImpl());
        commands.put("playlistsPage", new PlaylistsPageCommandImpl());
        commands.put("playlists", new PlaylistsCommandImpl());
        commands.put("deletePlaylist", new DeletePlaylistCommandImpl());
        commands.put("subscription", new SubscriptionManagementCommandImpl());
        commands.put("comments", new CommentsPageCommandImpl());
        commands.put("addComment", new AddCommentCommandImpl());
        commands.put("deleteComment", new DeleteCommentCommandImpl());
    }

    public static Command getCommand(String commandName) {
        Command command = null;
        try {
            command = commands.get(commandName);
        } catch (IllegalArgumentException e) {
            LOGGER.info("Could not receive command by name: " + commandName);
        }
        return command;
    }

}
