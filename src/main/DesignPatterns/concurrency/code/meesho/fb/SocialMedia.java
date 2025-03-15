package concurrency.code.meesho.fb;

import java.util.*;

//- [ ] Design social media network system which allow user to follow friends and add posts and browse post feed.
// The system should provide below functionalities:
//* Users must be able to upload posts
//* Users must be able to delete posts
//* Users must be able to browse post feed which would contain the recent 10 posts from his/her account and followings' accounts sorted by time posted
//* Users must be able to follow other users
//* Users must be able to unfollow the users
//1. createPost(userld,content): Compose a new post.
// 2. getNewsFeed(userld):Retrieve the 10 most recent post ids in the user's news feed.
// Each item in the news feed must be posted by users who the user followed or by the user herself
// (Order -> most to least recent)
// 3. follow(followerld, followeeld):Follower follows a followee.
// 4. deletePost(userId, postId) :delete post of a user
// 5. unfollow(followerld, followeeld) : Follower unfollows a followee.
public class SocialMedia {
    private Map<String, User> users; // Maps userId to User object
    private Map<String, Set<String>> followings; // Maps userId to a set of followed userIds

    public SocialMedia() {
        users = new HashMap<>();
        followings = new HashMap<>();
    }

    // Create a new user
    public void createUser(String userId, String userName) {
        users.put(userId, new User(userId, userName));
        followings.put(userId, new HashSet<>());
    }

    // User uploads a post
    public void uploadPost(String userId, String content) {
        User user = users.get(userId);
        if (user != null) {
            Post post = new Post(UUID.randomUUID().toString(), content);
            user.addPost(post);
        }
    }

    // User deletes a post
    public void deletePost(String userId, String postId) {
        User user = users.get(userId);
        if (user != null) {
            List<Post> posts = user.getPosts();
            posts.removeIf(post -> post.getPostId().equals(postId));
        }
    }

    // User follows another user
    public void followUser(String followerId, String followeeId) {
        if (users.containsKey(followerId) && users.containsKey(followeeId)) {
            followings.get(followerId).add(followeeId);
        }
    }

    // User unfollows another user
    public void unfollowUser(String followerId, String followeeId) {
        if (followings.containsKey(followerId)) {
            followings.get(followerId).remove(followeeId);
        }
    }

    // Get the recent 10 posts from the user and their followings
    public List<Post> getFeed(String userId) {
        return getPaginatedFeed(userId, 0, 10);
    }

    // Get paginated news feed for the user
    public List<Post> paginatedNewsFeed(String userId, int page, int pageSize) {
        return getPaginatedFeed(userId, page, pageSize);
    }

    private List<Post> getPaginatedFeed(String userId, int page, int pageSize) {
        List<Post> feed = new ArrayList<>();

        // Add user's own posts
        User user = users.get(userId);
        if (user != null) {
            feed.addAll(user.getPosts());
        }

        // Add posts from followings
        Set<String> followeeIds = followings.get(userId);
        if (followeeIds != null) {
            for (String followeeId : followeeIds) {
                User followee = users.get(followeeId);
                if (followee != null) {
                    feed.addAll(followee.getPosts());
                }
            }
        }

        // Sort posts by timestamp in descending order
        feed.sort((p1, p2) -> Long.compare(p2.getTimestamp(), p1.getTimestamp()));

//        feed.sort((p1, p2) -> {
//            if (((Post) p1).getTimestamp() > ((Post) p2).getTimestamp()) return 1;
//            else if(((Post) p1).getTimestamp() < ((Post) p2).getTimestamp()) return -1;
//            return 0;
//        });

        // Calculate start and end index for pagination
        int start = page * pageSize;
        int end = Math.min(start + pageSize, feed.size());

        return (start < feed.size()) ? feed.subList(start, end) : Collections.emptyList(); // Return the paginated result
    }
}
