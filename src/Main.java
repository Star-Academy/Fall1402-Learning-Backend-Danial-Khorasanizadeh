import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var search = new FileSearch("C:\\Users\\danny\\Desktop\\EnglishData");
        System.out.println("Please enter your search query:");
        var query = scanner.nextLine();
        for(var document: search.get_search_results(query)){
            System.out.println(document.get_id());
        }
    }
}