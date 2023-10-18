import java.util.HashSet;
import java.util.LinkedList;

public class FileSearch implements Search {
    private final Index index;

    public FileSearch(String documents_directory) {
        FileDocumentHolder holder = new FileDocumentHolder(documents_directory);
        index = new Index(holder);
    }

    @Override
    public HashSet<Document> get_search_results(String query) {
        var words = query.trim().split(" ");
        LinkedList<String> and_words = new LinkedList<>();
        LinkedList<String> or_words = new LinkedList<>();
        LinkedList<String> not_words = new LinkedList<>();
        for (var word : words) {
            if (word.startsWith("+")) {
                or_words.add(word.substring(1).toUpperCase());
            } else if (word.startsWith("-")) {
                not_words.add(word.substring(1).toUpperCase());
            } else {
                and_words.add(word.toUpperCase());
            }
        }
        HashSet<Document> documents = new HashSet<>();
        HashSet<Document> result;
        for (var word : or_words) {
            result = index.search(word);
            if (!result.isEmpty()) {
                documents.addAll(result);
            }
        }
        for (var word : and_words) {
            result = index.search(word);
            if (!result.isEmpty()) {
                documents.retainAll(result);
            }
        }
        for (var word : not_words) {
            result = index.search(word);
            if (!result.isEmpty()) {
                documents.removeAll(result);
            }
        }
        return documents;
    }
}
