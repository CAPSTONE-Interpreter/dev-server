package chamsae.koreansignlanguage.entity;

import java.io.Serializable;
import java.util.Objects;

public class ScrapId implements Serializable {

    private long memId;
    private long vidId;

    public ScrapId() {}
    public ScrapId(long memId, long vidId) {
        this.memId = memId;
        this.vidId = vidId;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ScrapId scrapId = (ScrapId) o;
        return (memId == scrapId.memId) && (vidId == scrapId.vidId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memId, vidId);
    }
}
