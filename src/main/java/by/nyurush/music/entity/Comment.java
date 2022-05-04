package by.nyurush.music.entity;

import java.util.Date;
import java.util.Objects;

public class Comment extends Entity {
    private String text;
    private Date date;
    private String path;
    private Track track;
    private User user;
    private Integer depth;

    public Comment(Integer id, String text, Date date, String path, Track track, User user) {
        super(id);
        this.text = text;
        this.date = date;
        this.path = path;
        this.track = track;
        this.user = user;
        this.depth = calcDepth();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text) &&
                Objects.equals(date, comment.date) &&
                Objects.equals(path, comment.path) &&
                Objects.equals(track, comment.track) &&
                Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text, date, path, track, user);
    }

    private Integer calcDepth() {
        return (int) this.path.chars().filter(ch -> ch == '/').count() - 1;
    }
}
