package concurrency.code.meesho.wapoll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poll {
    private String pollId;
    private String question;
    private List<String> options;
    private Map<String , Integer> votes;
    private boolean isClosed;

    public Poll(String pollId, String question, List<String> options){
        this.pollId = pollId;
        this.question = question;
        this.options = new ArrayList<>(options);
        this.votes = new HashMap<>();
        for(String option : options)
            votes.put(option, 0);
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

    public void closePoll() {
        this.isClosed = true;
    }

    public void updatePollOptions(List<String> newOptions){
        Map<String , Integer> existingVotes = this.votes;
        for(var o : newOptions){
            if(!existingVotes.containsKey(o)){
                existingVotes.put(o, 0);
                this.options.add(o);
            }
        }
    }

    public void addVote(String option){
        if(!isClosed && votes.containsKey(option)){
            votes.put(option, votes.get(option) + 1);
        } else{
            System.out.println("Invalid option or poll");
        }
    }

    public void printResults(){
        System.out.println("Poll Res:" + question+":\n");
        for(var o : votes.entrySet())
            System.out.println(o.getKey()+ " : "+o.getValue());
    }

    public void updateQuestion(String updatedQuestion){
        this.question = updatedQuestion;
    }
}
