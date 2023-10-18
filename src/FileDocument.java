import java.io.*;
import java.util.LinkedList;

public class FileDocument implements Document {
    private final File document;
    private LinkedList<String> tokens;

    public FileDocument(File file) throws IOException {
        document = file;
        tokenize();
    }

    private void tokenize() throws IOException {
        tokens = new LinkedList<>();
        var reader = new FileReader(document);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        var currentToken = tokenizer.nextToken();
        while (currentToken != StreamTokenizer.TT_EOF) {
            if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                tokens.add(tokenizer.sval.replaceAll("[^a-zA-Z]", "").toUpperCase());
            }
            currentToken = tokenizer.nextToken();
        }
    }

    @Override
    public LinkedList<String> get_tokens() {
        return tokens;
    }

    @Override
    public String get_id() {
        return document.getName();
    }
}
