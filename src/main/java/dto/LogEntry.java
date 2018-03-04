package dto;

import java.util.Date;

/**
 * Created by Alexander Yakovlev on 03.03.2018.
 */
public class LogEntry {
    private Date created;
    private String login;
    private String url;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
