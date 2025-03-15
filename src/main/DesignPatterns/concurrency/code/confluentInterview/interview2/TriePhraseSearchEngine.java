package concurrency.code.confluentInterview.interview2;

import java.util.*;

import java.util.*;

public class TriePhraseSearchEngine {

    // Trie node definition
    private static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        Map<Integer, List<Integer>> positions = new HashMap<>();  // sentenceId -> list of word positions
    }

    private TrieNode root;
    private List<String> sentences;

    // Constructor to initialize with a list of sentences
    public TriePhraseSearchEngine(List<String> sentences) {
        this.sentences = sentences;
        this.root = new TrieNode();
        buildTrie();
    }

    // Build the Trie from the list of sentences
    private void buildTrie() {
        for (int sentenceId = 0; sentenceId < sentences.size(); sentenceId++) {
            String[] words = sentences.get(sentenceId).toLowerCase().split("\\s+");
            insertSentence(words, sentenceId);
        }
    }

    // Insert a sentence into the Trie, storing word positions
    private void insertSentence(String[] words, int sentenceId) {
        for (int i = 0; i < words.length; i++) {
            TrieNode node = root;
            for (int j = i; j < words.length; j++) {
                String word = words[j];
                node.children.putIfAbsent(word, new TrieNode());
                node = node.children.get(word);

                // Add the word position in the corresponding sentence
                node.positions.putIfAbsent(sentenceId, new ArrayList<>());
                node.positions.get(sentenceId).add(i);
            }
        }
    }

    // Search for a phrase in the list of sentences
    public List<Integer> searchPhrase(String phrase) {
        String[] words = phrase.toLowerCase().split("\\s+");
        TrieNode node = root;

        // Traverse the Trie for the first word of the phrase
        if (!node.children.containsKey(words[0])) {
            return Collections.emptyList();  // Return empty if the first word doesn't exist
        }

        node = node.children.get(words[0]);

        // Check each sentence where the first word occurs
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : node.positions.entrySet()) {
            int sentenceId = entry.getKey();
            List<Integer> positions = entry.getValue();

            // For each position of the first word, check if the full phrase exists contiguously
            for (int pos : positions) {
                if (isPhraseInSentence(sentenceId, pos, words)) {
                    result.add(sentenceId);
                    break;
                }
            }
        }

        return result;
    }

    // Helper method to check if the full phrase exists starting at a given position in a sentence
    private boolean isPhraseInSentence(int sentenceId, int startPos, String[] words) {
        String[] sentenceWords = sentences.get(sentenceId).toLowerCase().split("\\s+");

        // Ensure the phrase fits within the sentence starting from this position
        if (startPos + words.length > sentenceWords.length) {
            return false;
        }

        // Check if all the words match consecutively
        for (int i = 0; i < words.length; i++) {
            if (!sentenceWords[startPos + i].equals(words[i])) {
                return false;
            }
        }

        return true;
    }

    // Main method to test the TriePhraseSearchEngine
    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
                "The quick brown fox jumps over the lazy dog",
                "The quick brown fox is very quick",
                "The brown fox is not lazy",
                "The fox jumps over the quick brown fence"
        );

        TriePhraseSearchEngine engine = new TriePhraseSearchEngine(sentences);

        String phraseToSearch = "quick brown fox";
        List<Integer> result = engine.searchPhrase(phraseToSearch);

        System.out.println("Phrase found in sentence(s): " + result);
    }
}