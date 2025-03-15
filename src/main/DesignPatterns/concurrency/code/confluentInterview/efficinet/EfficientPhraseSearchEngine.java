package concurrency.code.confluentInterview.efficinet;

import java.util.*;

public class EfficientPhraseSearchEngine {

    // Inverted index: word -> map of docId -> list of positions in the document
    private final Map<String, Map<Integer, List<Integer>>> invertedIndex = new HashMap<>();
    private final Map<Integer, String> documents = new HashMap<>(); // Store documents for reference

    // Add document to the inverted index
    public void addDocument(int docId, String content) {
        documents.put(docId, content);  // Save original document for reference
        String[] words = content.toLowerCase().split("\\s+");  // Split content into words

        // Populate inverted index with word positions
        for (int position = 0; position < words.length; position++) {
            String word = words[position];

            // Initialize map and list for word if not already present
            invertedIndex.computeIfAbsent(word, k -> new HashMap<>());
            invertedIndex.get(word).computeIfAbsent(docId, k -> new ArrayList<>()).add(position);
        }
    }

    // Search for a phrase in documents
    public List<Integer> searchPhrase(String phrase) {
        System.out.println("invertedMap" + invertedIndex);
        String[] words = phrase.toLowerCase().split("\\s+");

        if (words.length == 0 || !invertedIndex.containsKey(words[0])) {
            return Collections.emptyList();  // Return empty list if no documents contain the first word
        }

        // Get initial candidates from the first word
        Map<Integer, List<Integer>> candidateDocs = invertedIndex.get(words[0]);
        System.out.println("Candidate docs"+candidateDocs);
        List<Integer> result = new ArrayList<>();

        // Check for each document if the phrase exists as a sequence
        for (int docId : candidateDocs.keySet()) {
            List<Integer> positions = candidateDocs.get(docId);
            System.out.println("Posotions " + positions);
            // For each position of the first word, check if the subsequent words match in sequence
            for (int startPos : positions) {
                System.out.println("Start Pos");
                if (isPhraseInDocument(docId, startPos, words)) {
                    result.add(docId);  // Add document if phrase found
                    break;
                }
            }
        }

        return result;
    }

    // Helper method to check if phrase exists at the given start position in the document
    private boolean isPhraseInDocument(int docId, int startPos, String[] words) {
        String[] docWords = documents.get(docId).toLowerCase().split("\\s+");
        System.out.println("doc Words" + Arrays.stream(docWords).toArray());
        // Ensure the phrase fits within the document starting at this position
        if (startPos + words.length > docWords.length) {
            return false;
        }

        // Check each word of the phrase against the document
        for (int i = 0; i < words.length; i++) {
            if (!docWords[startPos + i].equals(words[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        EfficientPhraseSearchEngine searchEngine = new EfficientPhraseSearchEngine();

        // Add some sample documents
        searchEngine.addDocument(1, "The quick brown fox jumps over the lazy dog.");
        searchEngine.addDocument(2, "A journey of a thousand miles begins with a single step.");
        searchEngine.addDocument(3, "To be or not to be, that is the question.");
        searchEngine.addDocument(4, "The quick brown fox.");

        // Search for phrases
        System.out.println("Search for 'quick brown': " + searchEngine.searchPhrase("quick brown"));
//        System.out.println("Search for 'thousand miles': " + searchEngine.searchPhrase("thousand miles"));
//        System.out.println("Search for 'lazy dog': " + searchEngine.searchPhrase("lazy dog"));
//        System.out.println("Search for 'quick fox': " + searchEngine.searchPhrase("quick"));  // Should return empty
    }
}

