package other;

import java.util.stream.LongStream;

/**
 * Created by Alexander Yakovlev on 13/02/2018.
 */
public class Prime {

    /**
     * Checking if a number is prime
     *
     * @param number to test >= 2
     * @return true if number is prime else false
     */
    public static boolean isPrime(final long number) {
        // write your code here
        return LongStream.range(2, number)
                .allMatch(num -> number % num > 0);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(i + " - " + isPrime(i));
        }
    }
}
