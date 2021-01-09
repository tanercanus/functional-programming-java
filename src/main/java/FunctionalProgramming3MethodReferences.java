import java.util.List;
import java.util.function.Supplier;

public class FunctionalProgramming3MethodReferences {
    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        courses.stream()
                //.map(str -> str.toUpperCase())
                .map(String::toUpperCase) // method reference for member method
                .forEach(System.out::println); // method reference for static method

        Supplier<String> supplier = () -> new String();
        Supplier<String> supplier2 = String::new;//Constructor reference
    }
}
