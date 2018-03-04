package other;

import dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Alexander Yakovlev on 10/02/2018.
 */
public class Sample1 {

    public static final TernaryIntPredicate allValuesAreDifferentPredicate =
            (x1, x2, x3) -> x1 != x2 && x2 != x3 && x1 != x3;

    public static void main(String[] args) {
//        String reduce = concatUpperFunc();
//        System.out.println(reduce);
//
//        Long aLong = multiplyFunc();
//        System.out.println(aLong);
//
//        Function<List<String>, List<String>> function2 = removeSimilarFunc();
//        System.out.println(function2.apply(Arrays.asList("a","b","a","c","b")));
        List<Account> accounts = new ArrayList<>();
        boolean b = IntStream.generate(() -> 100)
                .limit(101)
                .allMatch(value -> value == 100);

        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        List<Integer> generated1 = numbers.stream()
                .flatMap(n -> Stream.generate(() -> n).limit(n))
                .collect(Collectors.toList());

        System.out.println(generated1);
    }

    /**
     * Calculates the general sum of canceled transactions for all non empty accounts in the list
     */
    public static long calcSumOfCanceledTransOnNonEmptyAccounts(List<Account> accounts) {
        return accounts.stream()
                .filter(account -> account.getBalance() > 0)
                .flatMap(account -> account.getTransactions().stream())
                .filter(transaction -> transaction.getState() == State.CANCELED)
                .mapToLong(Transaction::getSum)
                .sum();
        // write your code here
    }

    /**
     * Calculates the number of employees with salary >= threshold (only for 111- departments)
     *
     * @param departments are list of departments
     * @param threshold is lower edge of salary
     *
     * @return the number of employees
     */
    public static long calcNumberOfEmployees(List<Department> departments, long threshold) {
        return departments.stream()
                .filter(department -> department.getCode().startsWith("111-"))
                .flatMap(department -> department.getEmployees().stream())
                .mapToLong(Employee::getSalary)
                .filter(salary -> salary >= threshold)
                .count();
    }

    /**
     * The method calculates the sum of odd numbers in the given range
     *
     * @param start of a range, start >= 0
     * @param end of a range (inclusive), end >= start
     *
     * @return sum of odd numbers
     */
    public static long sumOfOddNumbersInRange(long start, long end) {
        return LongStream.range(start, end + 1)
                .filter(value -> value % 2 != 0)
                .reduce((n1, n2) -> n1 + n2)
                .orElse(0);
    }

    /**
     * Calculates the factorial of the given number n
     *
     * @param n >= 0
     *
     * @return factorial value
     */
    public static long factorial(long n) {
        return LongStream.range(1, n + 1)
                .reduce(Math::multiplyExact)
                .orElse(1);
    }

    /**
     * You have two IntStream. The first stream contains even numbers and the second stream contains odd numbers.
     * Create the third stream that contains numbers from both streams which is divisible by 3 and 5.
     * After calling collect(Collectors.toList()) the stream should return sorted list (ascending) of these numbers.
     * Two first suitable numbers in the sorted list must be skipped.
     */
    public static IntStream createFilteringStream(IntStream evenStream, IntStream oddStream) {
        return IntStream.concat(evenStream, oddStream)
                .filter(value -> value % 3 == 0 && value % 5 == 0)
                .sorted()
                .skip(2);
    }

    /**
     * The method represents a disjunct operator for a list of predicates.
     * For an empty list it returns the always false predicate.
     */
    public static IntPredicate disjunctAll(List<IntPredicate> predicates) {
        return predicates.stream()
                .reduce(IntPredicate::or)
                .orElse(p -> false);
    }

    private static String concatUpperFunc() {
        return Stream.of("a", "b", "c", "d", "e", "f", "g")
                .map(String::toUpperCase)
                .reduce(String::concat)
                .get();
    }

    private static Function<List<String>, List<String>> removeSimilarFunc() {
        return list -> list.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private static Long multiplyFunc() {
        BiFunction<Long, Long, Long> function =
                (x1, x2) -> LongStream.range(x1, x2 + 1)
                        .reduce((xx1, xx2) -> xx1 * xx2)
                        .getAsLong();
        return function.apply(2L, 6L);
    }

    private interface TriFunction {
        int apply(int a, int b, int c);
    }

    private static int quadraticEquation(int in) {

        int a = 2, b = 3, c = 4;
//        Function<Integer, Integer> sqrt = x -> x * x;
//        BiFunction<Integer, Integer, Integer> mult = (x, y) -> x * y;
//        BiFunction<Integer, Integer, Integer> sum = (x, y) -> x + y;
        //a∗x2+b∗x+c
        Function<Integer, Integer> res = x -> a * x * x + b * x + c;
        return res.apply(in);
    }
}
