import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class FileDocumentHolder implements DocumentHolder {
    private final LinkedList<Document> documents;

    public FileDocumentHolder(String address) throws RuntimeException {
        documents = new LinkedList<>();
        var parent_directory = new File(address);
        if (parent_directory.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(parent_directory.listFiles()))
                    .filter(file -> file.isFile() && file.canRead())
                    .forEach(file -> {
                        try {
                            documents.add(new FileDocument(file));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } else {
            throw new RuntimeException("Given address is not a directory.");
        }
    }

    @Override
    public LinkedList<Document> get_documents() {
        return documents;
    }
}
