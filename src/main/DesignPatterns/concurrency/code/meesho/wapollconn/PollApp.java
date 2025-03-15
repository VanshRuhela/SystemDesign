package concurrency.code.meesho.wapollconn;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Poll {
    private String pollId;
    private String question;
    private List<String> options;
    private Map<String, Integer> votes;  // Option -> vote count
    private boolean isClosed;
    private final Lock lock = new ReentrantLock();  // For thread-safe operations

    public Poll(String pollId, String question, List<String> options) {
        this.pollId = pollId;
        this.question = question;
        this.options = new ArrayList<>(options);
        this.votes = new HashMap<>();
        for (String option : options) {
            votes.put(option, 0);  // Initialize vote counts to zero
        }
        this.isClosed = false;
    }

    public String getPollId() {
        return pollId;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public Map<String, Integer> getVotes() {
        return votes;
    }

    public boolean isClosed() {
        return isClosed;
    }

    // Close poll in a thread-safe manner
    public void closePoll() {
        lock.lock();  // Acquire the lock
        try {
            this.isClosed = true;
        } finally {
            lock.unlock();  // Release the lock
        }
    }

    // Thread-safe updatePoll method, ensuring no simultaneous updates
    public void updatePoll(List<String> newOptions) {
        lock.lock();
        try {
            for (String option : newOptions) {
                if (!votes.containsKey(option)) {
                    options.add(option);  // Append new options
                    votes.put(option, 0); // Initialize votes for new option
                }
            }
        } finally {
            lock.unlock();  // Ensure the lock is always released
        }
    }

    // Thread-safe voting logic using locks
    public void addVote(String option) {
        lock.lock();
        try {
            if (!isClosed && votes.containsKey(option)) {
                votes.put(option, votes.get(option) + 1);
            } else {
                throw new IllegalArgumentException("Invalid option or poll is closed.");
            }
        } finally {
            lock.unlock();
        }
    }

    // Print the results (can be synchronized if concurrent reading/writing happens)
    public void printResults() {
        lock.lock();  // Acquire the lock
        try {
            System.out.println("Poll Results for: " + question);
            for (Map.Entry<String, Integer> entry : votes.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
            }
        } finally {
            lock.unlock();  // Release the lock
        }
    }
}

class PollManager {
    private final Map<String, Poll> polls = new HashMap<>();
    private final Lock managerLock = new ReentrantLock();  // For thread-safe poll management

    // Create a new poll in a thread-safe manner
    public Poll createPoll(String pollId, String question, List<String> options) {
        managerLock.lock();
        try {
            Poll poll = new Poll(pollId, question, options);
            polls.put(pollId, poll);
            return poll;
        } finally {
            managerLock.unlock();
        }
    }

    // Delete a poll safely
    public void deletePoll(String pollId) {
        managerLock.lock();
        try {
            if (polls.containsKey(pollId)) {
                polls.remove(pollId);
                System.out.println("Poll deleted: " + pollId);
            } else {
                throw new IllegalArgumentException("Poll not found.");
            }
        } finally {
            managerLock.unlock();
        }
    }

    // Update an existing poll safely
    public void updatePoll(String pollId, List<String> newOptions) {
        Poll poll = polls.get(pollId);
        if (poll != null && !poll.isClosed()) {
            poll.updatePoll(newOptions);  // No need to lock this method since it's thread-safe internally
            System.out.println("Poll updated.");
        } else {
            throw new IllegalArgumentException("Poll not found or already closed.");
        }
    }

    // Vote on a poll
    public void voteOnPoll(String pollId, String option) {
        Poll poll = polls.get(pollId);
        if (poll != null && !poll.isClosed()) {
            poll.addVote(option);  // Thread-safe voting
            System.out.println("Vote recorded.");
        } else {
            throw new IllegalArgumentException("Poll not found or is closed.");
        }
    }

    // Print the results of a poll
    public void printPollResults(String pollId) {
        Poll poll = polls.get(pollId);
        if (poll != null) {
            poll.printResults();  // This method handles locking internally
        } else {
            throw new IllegalArgumentException("Poll not found.");
        }
    }

    // Close poll
    public void closePoll(String pollId) {
        Poll poll = polls.get(pollId);
        if (poll != null) {
            poll.closePoll();  // Thread-safe poll closing
            System.out.println("Poll is closed.");
        } else {
            throw new IllegalArgumentException("Poll not found.");
        }
    }
}

public class PollApp {
    public static void main(String[] args) {
        PollManager manager = new PollManager();

        // Create a poll
        List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3");
        manager.createPoll("P1", "What is your favorite color?", options);

        // Vote on the poll
        manager.voteOnPoll("P1", "Option 1");
        manager.voteOnPoll("P1", "Option 2");

        // Print poll results
        manager.printPollResults("P1");

        // Update poll (before closing)
        manager.updatePoll("P1", Arrays.asList("Red", "Green", "Blue"));

        // Print updated poll results
        manager.printPollResults("P1");

        // Close the poll
        manager.closePoll("P1");

        // Print final poll results
        manager.printPollResults("P1");

        // Delete poll
        manager.deletePoll("P1");
    }
}
