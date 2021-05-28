package chamsae.koreansignlanguage.domain;

import java.io.Serializable;
import java.util.Objects;

public class ScrapId implements Serializable {

    private String email;
    private int id;

    public ScrapId() {}
    public ScrapId(String email, int id) {
        this.email = email;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ScrapId scrapId = (ScrapId) o;
        return email.equals(scrapId.email) && (id == scrapId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, id);
    }
}
