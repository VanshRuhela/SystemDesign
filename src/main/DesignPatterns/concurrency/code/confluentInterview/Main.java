package concurrency.code.confluentInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WordSearchEngine {
    private final Map<String, List<Integer>> wordMap;

    public WordSearchEngine() {
        this.wordMap = new HashMap<>();
    }

    // Step 1: Add document to the engine
    public void addDocument(int docId, String text) {
        String[] words = text.split("\\W+"); // Split text into words
        for (String word : words) {
            word = word.toLowerCase(); // Normalize to lowercase
            wordMap.putIfAbsent(word, new ArrayList<>());
            wordMap.get(word).add(docId);
        }
    }

    // Step 2: Search for a single word
    public List<Integer> searchWord(String word) {
        return wordMap.getOrDefault(word.toLowerCase(), new ArrayList<>());
    }

    // Step 3: Search for a phrase
    public List<Integer> searchPhrase(String phrase) {
        List<Integer> results = new ArrayList<>();
        String[] words = phrase.split("\\W+"); // Split phrase into words // the world
        for (int docId : wordMap.getOrDefault(words[0].toLowerCase(), new ArrayList<>())) {
            boolean containsAll = true;
            System.out.println("loop");
            for (String word : words) {
                if (!wordMap.getOrDefault(word.toLowerCase(), new ArrayList<>()).contains(docId)) {
                    containsAll = false;
                    break;
                }
            }
            if (containsAll) {
                results.add(docId);
            }
        }
        return results;
    }

    // Optional: Print all document ids for debugging
    public void printDocuments() {
        for (Map.Entry<String, List<Integer>> entry : wordMap.entrySet()) {
            System.out.println("Word: " + entry.getKey() + " -> Document IDs: " + entry.getValue());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        WordSearchEngine searchEngine = new WordSearchEngine();

        // Adding documents
        searchEngine.addDocument(1, "Hello world, welcome to the universe.");
        searchEngine.addDocument(2, "Hello from the other side.");
        searchEngine.addDocument(3, "Welcome to the world of programming.");
        searchEngine.addDocument(4, "The universe is vast and full of wonders.");

        // Searching for a word
        String wordToSearch = "hello";
        List<Integer> wordResults = searchEngine.searchWord(wordToSearch);
        System.out.println("Documents containing the word '" + wordToSearch + "': " + wordResults);

        // Searching for a phrase
        String phraseToSearch = "the world";
        List<Integer> phraseResults = searchEngine.searchPhrase(phraseToSearch);
        System.out.println("Documents containing the phrase '" + phraseToSearch + "': " + phraseResults);

        // Print all documents for debugging
//        searchEngine.printDocuments();
    }
}
