import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalProgramming2 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        int sum = addList(numbers);

        System.out.println("-----");
        System.out.println(sum);
        System.out.println("-----");
        numbers.stream().distinct().forEach(System.out::println);
        System.out.println("-----");
        numbers.stream().sorted().forEach(System.out::println);
        System.out.println("-----");

        List<String> courses = List.of("Spring", "Spring Boot", "API", "Spring",
                "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        courses.stream().sorted().forEach(System.out::println);
        System.out.println("-----");
        courses.stream().distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("-----");
        courses.stream().distinct().sorted(Comparator.comparing(str -> str.length())).forEach(System.out::println);
        System.out.println("-----");

        List<Integer> doubleNumbers = doubleList(numbers);
        System.out.println(doubleNumbers);

    }

    private static List<Integer> doubleList(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> number * number)
                .collect(Collectors.toList());
    }

    private static int addList(List<Integer> numbers) {
        return numbers.stream()
                //.reduce(0, FunctionalProgramming2::sum);//convert it one single value
                //.reduce(0, (x,y) -> x+y);
                .reduce(0, Integer::sum);
    }

    private static int sum(int a, int b){
        return a+b;
    }
}
