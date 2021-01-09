import java.util.List;
import java.util.Random;
import java.util.function.*;

public class FunctionalProgramming3 {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;

        Predicate<Integer> isEvenPredicateBackground = new Predicate<Integer>() {//anonymous class

            @Override
            public boolean test(Integer x) {
                return x % 2 == 0;
            }
        };

        Function<Integer, Integer> squareFunction = x -> x * x;

        Function<Integer, Integer> squareFunctionBackground = new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer x) {
                return x * x;
            }
        };

        Consumer<Integer> sysOutPrintln = System.out::println;

        Consumer<Integer> sysOutPrintlnBackground = new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.println(x);
            }
        };

        numbers.stream()
                .filter(isEvenPredicate)
                .map(squareFunction)
                .forEach(sysOutPrintln);
        System.out.println("------");
        numbers.stream()
                .filter(isEvenPredicateBackground)
                .map(squareFunctionBackground)
                .forEach(sysOutPrintlnBackground);
        System.out.println("------");

        //For operator accept 2 parameter and return 1 output.
        // And all parameters and output are the same type(Integer for below)
        BinaryOperator<Integer> sumBinaryOperator = Integer::sum;
        BinaryOperator<Integer> sumBinaryOperator2 = new BinaryOperator<Integer>() {

            @Override
            public Integer apply(Integer a, Integer b) {
                return a+b;
            }
        };
        int sum = numbers.stream()
                .reduce(0, sumBinaryOperator2);
        System.out.println(sum);

        System.out.println("------");
        //No input > Return sth. (Like a factory pattern)
        Supplier<Integer> randomIntegerSupplier = () -> {
            Random random = new Random();
            return random.nextInt(1000);
        };

        System.out.println(randomIntegerSupplier.get());

        System.out.println("------");
        UnaryOperator<Integer> unaryOperator = (x) -> 3*x;
        System.out.println(unaryOperator.apply(10));

        System.out.println("------");
        BiPredicate<Integer, String> biPredicate = (number,str)-> {
            return number <10 && str.length()>5;
        };
        System.out.println(biPredicate.test(5, "tanerus"));

        System.out.println("------");
        BiFunction<Integer, String, String> biFunction = (number,str)-> {
            return number +" " + str;
        };
        System.out.println(biFunction.apply(5, "tanerus"));

        System.out.println("------");
        BiConsumer<Integer, String> biConsumer = (s1, s2) -> {
            System.out.println(s1);
            System.out.println(s2);
        };
        biConsumer.accept(16,"tanerus!!");

        //For below function, you cannot use generic type, you can use only int type
        //To avoid the wrapper class increase your code's performance
        //because of boxing and unboxing
        //primitive type

        //IntBinaryOperator
        //IntConsumer
        //IntFunction
        //IntPredicate
        //IntSupplier
        //IntToDoubleFunction
        //IntToLongFunction
        //IntUnaryOperator

        IntBinaryOperator intBinaryOperator = (x,y) -> x+y;
        System.out.println();

    }
}
