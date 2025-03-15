package concurrency.code.meesho.fb;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String userName;
    private List<Post> posts;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.posts = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }
}
