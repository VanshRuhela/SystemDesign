package concurrency.code.meesho.fb;

public class Post {
    private String postId;
    private String content;
    private long timestamp; // Used for sorting posts

    public Post(String postId, String content) {
        this.postId = postId;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public String getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
