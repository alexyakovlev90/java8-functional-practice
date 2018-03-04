package collector;

import dto.LogEntry;
import dto.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alexander Yakovlev on 03.03.2018.
 * long summary = accounts.stream()
 * .collect(summingLong(Account::getBalance));
 * <p>
 * double average = accounts.stream()
 * .collect(averagingLong(Account::getBalance));
 * <p>
 * String meganumber = accounts.stream()
 * .collect(reducing("", Account::getNumber, String::concat));
 * <p>
 * Map<Boolean, List<Account>> partByBalance = accounts.stream()
 * .collect(Collectors.partitioningBy(a -> a.getBalance() >= 10000));
 * <p>
 * Map<Account.State, List<Account>> groupingByState = accounts.stream()
 * .collect(Collectors.groupingBy(Account::getState));
 */
public class CollectorsPractice {

    public static void main(String[] args) {


        List<Integer> numbers = Arrays.asList(1, 2, 3);
        long val = numbers.stream()
                .collect(Collectors.reducing(1, x -> x * x, (x, y) -> x * y));

        // collector that partitions all words in a stream into two groups: palindromes (true) and usual words (false).
        String[] words = new String[]{"level", "bbaa", "ac"};
        Map<Boolean, List<String>> palindromeOrNoMap = Arrays.stream(words)
                .collect(
                        Collectors.partitioningBy(
                                word -> word.equals(new StringBuilder(word).reverse().toString())
                        )
                );

        // Write a collector that calculates the total sum of transactions (long type, not integer)
        // by each account (i.e. by account number).
        // The collector will be applied to a stream of transactions.

        List<Transaction> transactions = new ArrayList<>();
        Map<String, Long> totalSumOfTransByEachAccount = transactions.stream()
                .collect(
                        Collectors.groupingBy(tr -> tr.getAccount().getNumber(),
                                Collectors.reducing(0L, Transaction::getSum, (x, y) -> x + y)
                        )
                );

        // Write a collector that calculates how many times was clicked each url by users.
        // The collector will be applied to a stream of log entries for creating a map: url -> click count.
        List<LogEntry> logs = new ArrayList<>();
        Map<String, Long> clickCount = logs.stream()
                .collect(Collectors.groupingBy(LogEntry::getUrl, Collectors.counting()));

    }

}
