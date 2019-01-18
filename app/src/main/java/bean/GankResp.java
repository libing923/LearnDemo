package bean;

import java.util.List;

public class GankResp {
    public boolean error;
    public List<GankEntry> results;

    public static class GankEntry {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}
