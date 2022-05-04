package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AlbumDaoImpl;
import by.nyurush.music.dao.impl.ArtistDaoImpl;
import by.nyurush.music.dao.impl.TrackDaoImpl;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Artist;
import by.nyurush.music.entity.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrackDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Before
    @After
    public void deleteData() {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            albumDao.deleteAll();
            artistDao.deleteAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            trackDao.save(createTestTrack(daoHelper));
            assertTrue(trackDao.findAll().size() > 0);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            Track track = trackDao.save(createTestTrack(daoHelper));
            assertTrue(trackDao.findById(track.getId()).isPresent());
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            Track track = trackDao.save(createTestTrack(daoHelper));
            boolean actual = track.getId() != null;
            assertTrue(actual);
            assertTrue(trackDao.delete(track));
        }
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            Track track = trackDao.save(createTestTrack(daoHelper));
            assertTrue(trackDao.findByName("test", 0, 1000).size() > 0);
        }
    }

    @Test
    public void findByAlbumName() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            Track track = trackDao.save(createTestTrack(daoHelper));
            assertEquals(trackDao.findByAlbumName("test").get(0), track);
        }
    }

    @Test
    public void findByGenre() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            Track track = trackDao.save(createTestTrack(daoHelper));
            assertTrue(trackDao.findByGenre("ROCK", 0, 1000).size() > 0);
        }
    }

    @Test
    public void findByArtist() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            Track track = trackDao.save(createTestTrack(daoHelper));
            assertEquals(trackDao.findByArtist("test").get(0), track);
        }
    }

    private Track createTestTrack(DaoHelper daoHelper) throws DaoException {
        AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
        ArtistDaoImpl artistDao = daoHelper.createArtistDao();
        Artist testArtist = new Artist(null, "test", "test");
        Artist artist = artistDao.save(testArtist);
        Album album = albumDao.save(new Album(null, "test", 2020, artist));
        return new Track(null, "test", "test", "ROCK", album);
    }
}
