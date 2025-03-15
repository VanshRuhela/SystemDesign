package concurrency.code.meesho.fb;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.createUser("U1", "Alice");
        socialMedia.createUser("U2", "Bob");
        socialMedia.createUser("U3", "Charlie");

        // Users uploading posts
        socialMedia.uploadPost("U1", "Hello World!");
        socialMedia.uploadPost("U2", "Learning Java.");
        socialMedia.uploadPost("U3", "Excited for the weekend!");
        socialMedia.uploadPost("U1", "Just had a great lunch!");
        socialMedia.uploadPost("U2", "Working on my project.");
        socialMedia.uploadPost("U1", "Had a great workout.");
        socialMedia.uploadPost("U2", "Can't wait for the holidays!");
        socialMedia.uploadPost("U3", "What a nice day!");
        socialMedia.uploadPost("U1", "Just finished a book.");
        socialMedia.uploadPost("U2", "Started a new hobby.");
        socialMedia.uploadPost("U1", "Looking forward to the weekend!");
        socialMedia.uploadPost("U2", "Enjoying some coffee.");
        socialMedia.uploadPost("U3", "Going for a hike!");

        // User follows another user
        socialMedia.followUser("U1", "U2");
        socialMedia.followUser("U1", "U3");

        // Get paginated feed for user Alice (U1) on page 0
        List<Post> aliceFeedPage1 = socialMedia.paginatedNewsFeed("U1", 0, 5);
        System.out.println("Alice's Feed - Page 1:");
        for (Post post : aliceFeedPage1) {
            System.out.println(post.getContent());
        }

        // Get paginated feed for user Alice (U1) on page 1
        List<Post> aliceFeedPage2 = socialMedia.paginatedNewsFeed("U1", 1, 5);
        System.out.println("\nAlice's Feed - Page 2:");
        for (Post post : aliceFeedPage2) {
            System.out.println(post.getContent());
        }

        // User unfollows another user
        socialMedia.unfollowUser("U1", "U2");

        // User deletes a post
        socialMedia.deletePost("U1", aliceFeedPage1.get(0).getPostId());

        // Get updated paginated feed for user Alice (U1) on page 0
        aliceFeedPage1 = socialMedia.paginatedNewsFeed("U1", 0, 5);
        System.out.println("\nAlice's Updated Feed - Page 1:");
        for (Post post : aliceFeedPage1) {
            System.out.println(post.getContent());
        }
    }
}
