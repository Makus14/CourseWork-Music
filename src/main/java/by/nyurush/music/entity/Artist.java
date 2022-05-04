package by.nyurush.music.entity;

import java.util.Objects;

public class Artist extends Entity {
    private String artistName;
    private String imagePath;

    public Artist(Integer id, String artistName, String imagePath) {
        super(id);
        this.artistName = artistName;
        this.imagePath = imagePath;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(artistName, artist.artistName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), artistName);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistName='" + artistName + '\'' +
                '}';
    }
}
