package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.ArtistDaoImpl;
import by.nyurush.music.entity.Artist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArtistDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
    private final Artist testArtist = new Artist(null, "test", "test");

    @Before
    @After
    public void deleteData() {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            artistDao.deleteAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            artistDao.save(testArtist);
            assertEquals(1, artistDao.findAll().size());
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            Artist artist = artistDao.save(testArtist);
            assertTrue(artistDao.findById(artist.getId()).isPresent());
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            Artist result = artistDao.save(testArtist);
            boolean actual = result.getId() != null;
            assertTrue(actual);
            assertTrue(artistDao.delete(result));
        }
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            Artist artist = artistDao.save(testArtist);
            assertEquals(artistDao.findByName("test").get(0), artist);
        }
    }
}
