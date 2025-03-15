package concurrency.code.confluentInterview.phasesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    List<Integer> docIds; // Store document IDs where the phrase ends

    public TrieNode() {
        this.children = new HashMap<>();
        this.docIds = new ArrayList<>();
    }
}

class PhraseTrie {
    private final TrieNode root;

    public PhraseTrie() {
        this.root = new TrieNode();
    }

    // Insert phrase into the Trie
    public void insert(String phrase, int docId) {
        TrieNode node = root;
        for (char ch : phrase.toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        node.docIds.add(docId); // Add document ID where the phrase ends
    }

    // Search for a phrase in the Trie
    public List<Integer> search(String phrase) {
        TrieNode node = root;
        for (char ch : phrase.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return new ArrayList<>(); // Phrase not found
            }
        }
        return new ArrayList<>(node.docIds); // Return document IDs
    }
}

class PhraseSearchEngine {
    private final PhraseTrie phraseTrie;

    public PhraseSearchEngine() {
        this.phraseTrie = new PhraseTrie();
    }

    // Add document with text to the engine
    public void addDocument(int docId, String text) {
        String[] phrases = text.toLowerCase().split("\\W+");
        for (int i = 0; i < phrases.length; i++) {
            // Insert all continuous phrases of varying lengths
            for (int j = i; j < phrases.length; j++) {
                StringBuilder phraseBuilder = new StringBuilder();
                for (int k = i; k <= j; k++) {
                    phraseBuilder.append(phrases[k]).append(" ");
                }
                // Trim the last space and insert the phrase into the Trie
                phraseTrie.insert(phraseBuilder.toString().trim(), docId);
            }
        }
    }

    // Search for a continuous phrase
    public List<Integer> searchPhrase(String phrase) {
        return phraseTrie.search(phrase.toLowerCase());
    }
}

public class Main {
    public static void main(String[] args) {
        PhraseSearchEngine searchEngine = new PhraseSearchEngine();

        // Adding documents
        searchEngine.addDocument(1, "Hello world, welcome to the universe.");
        searchEngine.addDocument(2, "Hello from the other side.");
        searchEngine.addDocument(3, "Welcome to the world of programming.");
        searchEngine.addDocument(4, "The universe is vast and full of wonders.");

        // Searching for continuous phrases
        String[] phrasesToSearch = {"the world", "hello", "welcome to the"};

        for (String phrase : phrasesToSearch) {
            List<Integer> phraseResults = searchEngine.searchPhrase(phrase);
            System.out.println("Documents containing the phrase '" + phrase + "': " + phraseResults);
        }
    }
}

