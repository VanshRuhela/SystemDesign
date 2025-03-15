package concurrency.code.meesho.wapoll;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {
        PollManager manager = new PollManager();
        List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3");
        Poll poll = manager.createPoll("P1", "What is your favorite color?", options);

        // Number of threads to simulate concurrent voting
        int numThreads = 100;
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Simulate concurrent voting by 100 threads
        for (int i = 0; i < numThreads; i++) {
            int threadId = i;
            executor.submit(() -> {
                String option = options.get(threadId % options.size());  // Each thread picks a random option
                try {
                    manager.voteOnPoll("P1", option);
                } catch (Exception e) {
                    System.out.println("Error in voting: " + e.getMessage());
                }
            });
        }

        // Simulate updates while voting is happening
        executor.submit(() -> {
            List<String> newOptions = Arrays.asList("Red", "Green", "Blue");
            try {
                manager.updatePollOptions("P1", newOptions);
                System.out.println("Poll updated during voting.");
            } catch (Exception e) {
                System.out.println("Error in updating poll: " + e.getMessage());
            }
        });

        // Shutdown executor and wait for all tasks to complete
        executor.shutdown();
        try {
            // Wait for all threads to finish
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // Print final results
        manager.printPollResults("P1");

        // Test that no more votes can be added after the poll is closed
        manager.closePoll("P1");
        manager.printPollResults("P1");  // Should print final results and show no further votes

        // Check consistency after closing poll
        executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < numThreads; i++) {
            int threadId = i;
            executor.submit(() -> {
                String option = options.get(threadId % options.size());
                try {
                    manager.voteOnPoll("P1", option);  // This should throw an exception as the poll is closed
                } catch (Exception e) {
                    System.out.println("Expected error after poll close: " + e.getMessage());
                }
            });
        }

        // Shutdown again after attempting to vote on closed poll
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}