package multifunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Created by Alexander Yakovlev on 03.03.2018.
 */
public class CaryingFunctionPractice {


    public static IntBinaryOperator sumF(IntUnaryOperator f) {
        return (a, b) -> f.applyAsInt(a) + f.applyAsInt(b);
    }


    // build a new sumOfSquares operator
    IntBinaryOperator sumOfSquares1 = sumF(x -> x * x);

    // the sum is equal to 125
    long sum1 = sumOfSquares1.applyAsInt(5, 10);

    // sum of two identities: 0 + 10 = 10
    long sumOfIdentities = sumF(x -> x).applyAsInt(0, 10);

    // sum with coefficients: 10 * 2 + 11 * 2 = 42
    long sumWithCoefficient = sumF(x -> x * 2).applyAsInt(10, 11);

    // sum of two squares: 3 * 3 * 3 + 8 * 8 * 8 = 539
    long sumOfCubes = sumF(x -> x * x * x).applyAsInt(3, 8);

    IntBinaryOperator notCurriedFun = (x, y) -> x + y; // not a curried function

    IntFunction<IntUnaryOperator> curriedFun = x -> y -> x + y; // a curried function

    // curried function
    IntFunction<IntFunction<IntFunction<Integer>>> fff = x -> y -> z -> x * y + z;

    // fff returns a curried function y -> z -> 2 * y + z
    IntFunction<IntFunction<Integer>> ff = fff.apply(2);

    // ff returns a curried function z -> 2 * 3 + z
    IntFunction<Integer> f = ff.apply(3);

    // f returns 7
    int result = f.apply(1);

    // the another result is equal to 153
    int anotherResult = fff.apply(10).apply(15).apply(3);

    Function<IntUnaryOperator, IntBinaryOperator> sumF = (f) -> (a, b) -> f.applyAsInt(a) + f.applyAsInt(b);

    // build a new sumOfSquares operator in terms of sumF
    IntBinaryOperator sumOfSquares = sumF.apply(x -> x * x);

    // the sum is equal to 125 again
    long sum = sumOfSquares.applyAsInt(5, 10);

    //

    public static void main(String[] args) {
        Function<String, Consumer<String>> say = what -> whom -> System.out.println(what + ", " + whom);
        List<String> friends = Arrays.asList("John", "Neal", "Natasha");
        Consumer<String> sayHi = say.apply("Hi");
        // too many lines of a code...
        friends.forEach(sayHi);

        List<String> partners = Arrays.asList("Randolph Singleton", "Jessie James");
        Consumer<String> sayHello = say.apply("Hello");
        // somewhere in another method
        partners.forEach(sayHello);
    }


}
