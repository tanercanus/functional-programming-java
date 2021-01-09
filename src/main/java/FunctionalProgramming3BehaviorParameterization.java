import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalProgramming3BehaviorParameterization {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        //Predicate<Integer> evenPredicate = x -> x % 2 == 0;
        //filterAndPrint(numbers, evenPredicate);
        System.out.println("-----");
        filterAndPrint(numbers, x -> x % 2 == 0);

        System.out.println("-----");
        Predicate<Integer> oddPredicate = x -> x % 2 != 0;
        filterAndPrint(numbers, oddPredicate);

        System.out.println("-----");
        Function<Integer, Integer> squaredFunction = x -> x * x;
        List<Integer> squaredNumbers =
                mapAndCreateList(numbers, squaredFunction);
        System.out.println(squaredNumbers);

        System.out.println("-----");
        Function<Integer, Integer> cubeFunction = x -> x * x * x;
        List<Integer> cubeNumbers =
                mapAndCreateList(numbers, cubeFunction);
        System.out.println(cubeNumbers);

    }

    private static List<Integer> mapAndCreateList(List<Integer> numbers, Function<Integer, Integer> function) {
        return numbers.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    // extract method
    //This is behavior parameterization
    private static void filterAndPrint(List<Integer> numbers, Predicate<? super Integer> predicate) {
        numbers.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }
}
