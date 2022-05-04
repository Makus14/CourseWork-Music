package by.nyurush.music.tag;

import by.nyurush.music.entity.Playlist;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.language.ResourceBundleUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;


public class PlaylistListBody extends TagSupport {

    private List<Object> objects;

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    @Override
    public int doStartTag() throws JspException {
        try {

            JspWriter out = pageContext.getOut();
            out.write("<div class='section row'>");

            if (objects != null && !objects.isEmpty() && objects.get(0) instanceof Playlist) {
                writeBody();
            }

            out.write("</div>");

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private void writeBody() throws IOException {
        ResourceBundle resourceBundle = ResourceBundleUtil.getResourceBundle((HttpServletRequest) pageContext.getRequest());
        JspWriter out = pageContext.getOut();

        for (Object object : objects) {
            Playlist playlist = (Playlist) object;
            out.write("<div class='col-xl-3 col-lg-4 col-sm-6 pb-4'>" +
                    "<div class='custom-card'>" +
                    "<div class='custom-card--img'>");

            out.write("<a href='" + pageContext.getServletContext().getContextPath() +
                    "/controller?command=playlists&playlistName=" + playlist.getPlaylistName() + "'>" +
                    "<img src='resource/img/playlist.jpg'" +
                    "alt='playlist'" +
                    "class='card-img--radius-lg'></a>");
            out.write("</div>");

            out.write("<a href='" + pageContext.getServletContext().getContextPath());

            if (pageContext.getRequest().getParameter("all") == null) {
                out.write("/controller?command=playlists&playlistName=" + playlist.getPlaylistName() + "'");
            } else {
                out.write("/controller?command=playlists&playlistName=" + playlist.getPlaylistName() + "&all=1'");
            }
            out.write(" class='custom-card--link mt-2'>" +
                    "<h6 class='mb-0'>" + playlist.getPlaylistName() + "</h6>" +
                    "</a>");


            if(!playlist.getPlaylistName().equals(ConstantAttributes.FAVORITE) &&
                    pageContext.getRequest().getParameter("all") == null) {
                out.write("<a href='" + pageContext.getServletContext().getContextPath() +
                        "/controller?command=deletePlaylist&playlistId=" + playlist.getId() + "'>" +
                        "<i class='la la-trash'></i>" +
                        "<span>" + resourceBundle.getString("label.delete") + "</span>" +
                        "</a>");
            }

            out.write("</div></div>");
        }
    }
}
