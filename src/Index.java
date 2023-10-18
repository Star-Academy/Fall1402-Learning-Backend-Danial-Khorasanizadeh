import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashSet;

public class Index {
    private final HashMap<String, HashSet<Document>> index;

    private void index_document(LinkedList<String> tokens, FileDocument document) {
        for (var token : tokens) {
            if (!index.containsKey(token)) {
                index.put(token, new HashSet<>());
            }
            index.get(token).add(document);
        }
    }

    public Index(DocumentHolder documents_holder) {
        index = new HashMap<>();
        for (var document : documents_holder.get_documents()) {
            index_document(document.get_tokens(), (FileDocument) document);
        }
    }

    public HashSet<Document> search(String term) {
        if(index.containsKey(term)){
            return index.get(term);
        }
        return new HashSet<>();
    }
}
