import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FunctionalProgramming5Files {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("file.txt"))
                .map(str -> str.split(""))
                .forEach(System.out::println);
        //[Ljava.lang.String;@6ce253f1
        //[Ljava.lang.String;@53d8d10a
        //[Ljava.lang.String;@e9e54c2
        //FlatMap converts array of stream to stream
        //For instance: If you you use split("") function in stream
        //it returns array of stream. and to convert it again to stream
        //use can use flatmap


        Files.lines(Paths.get("file.txt"))
                .map(str -> str.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        //A
        //Present
        //Some
        //Text
        //be
        //here
        //in
        //lot
        //of
        //saved
        //that
        //will

        //You can see all the files
        System.out.println("-----");
        Files.list(Paths.get("."))
                .filter(Files::isDirectory)
                .forEach(System.out::println);
    }
}
