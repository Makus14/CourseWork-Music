package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AlbumDaoImpl;
import by.nyurush.music.dao.impl.ArtistDaoImpl;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Artist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlbumDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Before
    @After
    public void deleteData() {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            albumDao.deleteAll();
            artistDao.deleteAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            albumDao.save(createTestAlbum(artistDao));
            boolean actual = albumDao.findAll().size() == 1;
            assertTrue(actual);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            Album album = albumDao.save(createTestAlbum(daoHelper.createArtistDao()));
            boolean actual = albumDao.findById(album.getId()).isPresent();
            assertTrue(actual);
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            Album result = albumDao.save(createTestAlbum(artistDao));
            boolean actual = result.getId() != null;
            assertTrue(actual);
            assertTrue(albumDao.delete(result));
        }
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            Album result = albumDao.save(createTestAlbum(artistDao));
            assertEquals(albumDao.findByName("test").get(0), result);
        }
    }

    @Test
    public void findByYearPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            Album result = albumDao.save(createTestAlbum(artistDao));
            assertEquals(albumDao.findByYear(2020).get(0), result);
        }
    }

    private Album createTestAlbum(ArtistDaoImpl artistDao) throws DaoException {
        Artist testArtist = new Artist(null, "test", "test");
        artistDao.save(testArtist);
        return new Album(null, "test", 2020, testArtist);
    }
}
