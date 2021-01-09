import java.util.List;
/*
* 1- Write all values to the console
* 2- Write only even values to the console
* */
public class FunctionalProgramming1 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12,9,13,5,6,8,2,1,5);
        System.out.println("------");
        printAllNumbers(numbers);
        System.out.println("------");
        printEvenNumbersInTheList(numbers);
        System.out.println("------");
        printSquaresOfEvenNumbersInTheList(numbers);
    }

    private static void print(int number) {
        System.out.println(number);
    }
    private static void printAllNumbers(List<Integer> numbers) {
        numbers.stream().forEach(FunctionalProgramming1::print);//method Reference
    }

    /*
    private static boolean isEven(int number) {
        return number %2 ==0;
    }
    */
    private static void printEvenNumbersInTheList(List<Integer> numbers) {
        numbers.stream()
                //.filter(FunctionalProgramming1::isEven)
                .filter(number -> number % 2 == 0)//lambda expression
                .forEach(System.out::println);
    }

    private static void printSquaresOfEvenNumbersInTheList(List<Integer> numbers) {
        numbers.stream()
                //.filter(FunctionalProgramming1::isEven)
                .filter(number -> number % 2 == 0)//lambda expression
                .map(number -> number * number)
                .forEach(System.out::println);
    }
}
