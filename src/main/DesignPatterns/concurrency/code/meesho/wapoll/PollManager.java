package concurrency.code.meesho.wapoll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PollManager {
    private Map<String , Poll> polls;

    public PollManager(){
        polls = new HashMap<>();
    }

    // 1.create poll
    public Poll createPoll(String pollId , String question , List<String> options){
        Poll poll = new Poll(pollId, question, options);
        polls.put(pollId , poll);
        return poll;
    }

    // 2.delete poll
    public void deletePoll(String pollId){
        if(polls.containsKey(pollId)){
            polls.remove(pollId);
        }
        else{
            System.out.println("Not found");
        }
    }

    // 3. UpdatePoll
    public void updatePollOptions(String pollId, List<String> newOptions){
        Poll poll = polls.get(pollId);
        if(poll != null && !poll.isClosed()){
            poll.updatePollOptions(newOptions);
        }
        else{
            System.out.println("NOT Found");
        }
    }

    //4. vote
    public void voteOnPoll(String pollId , String option){
        Poll poll = polls.get(pollId);
        if(poll != null && !poll.isClosed()) {
            poll.addVote(option);
        }
        else{
            System.out.println("NOT Found");
        }
    }

    public void printPollResults(String pollId){
        Poll poll = polls.get(pollId);
        if (poll != null) {
            poll.printResults();
        } else {
            System.out.println("NOT found");
        }
    }

    public void closePoll(String pollId){
        Poll poll = polls.get(pollId);
        if (poll != null) {
            poll.closePoll();
            System.out.println("Poll " + pollId + "is closed");
        } else {
            System.out.println("NOT found");
        }
    }

    public void updateQuestion(String pollId, String newQuestion){
        Poll poll = polls.get(pollId);
        if(poll != null && !poll.isClosed()){
            poll.updateQuestion(newQuestion);
        }
    }
}
