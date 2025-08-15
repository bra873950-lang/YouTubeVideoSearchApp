package com.example.youtubevideosearchapp;

import java.util.List;

public class YouTubeResponse {
    public List<Item> items;

    public class Item {
        public Id id;
        public Snippet snippet;

        public class Id {
            public String videoId;
        }

        public class Snippet {
            public String title;
            public String description;
            public String publishedAt;
            public String channelTitle;
            public Thumbnails thumbnails;

            public class Thumbnails {
                public Thumbnail high;
                public class Thumbnail {
                    public String url;
                }
            }
        }
    }
}
