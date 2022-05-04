package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;
import by.nyurush.music.dao.impl.PlaylistDaoImpl;
import by.nyurush.music.dao.impl.UserDaoImpl;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlaylistDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Before
    @After
    public void deleteData() {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            UserDaoImpl userDao = daoHelper.createUserDao();
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            playlistDao.deleteAll();
            userDao.deleteAll();
            accountDao.deleteAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            playlistDao.save(createTestPlaylist(daoHelper.createUserDao()));
            assertTrue(playlistDao.findAll().size() > 0);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            Playlist playlist = playlistDao.save(createTestPlaylist(daoHelper.createUserDao()));
            assertTrue(playlistDao.findById(playlist.getId()).isPresent());
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            Playlist playlist = playlistDao.save(createTestPlaylist(daoHelper.createUserDao()));
            boolean actual = playlist.getId() != null;
            assertTrue(actual);
            assertTrue(playlistDao.delete(playlist));
        }
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            playlistDao.save(createTestPlaylist(daoHelper.createUserDao()));
            assertEquals(playlistDao.findByName("test").size(), 1);
        }
    }

    private Playlist createTestPlaylist(UserDaoImpl userDao) throws DaoException {
        User user = new User(null, "test", "test", AccountRole.CLIENT, "test", "test", "test", true);
        User testUser = userDao.save(user);
        return new Playlist(null, "test", true, testUser);
    }
}
