package monad;

import dto.Account;
import dto.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Alexander Yakovlev on 04.03.2018.
 */
public class MonadPractice {

    //Account (guid: UUID, balance: long)
    //User (login: String, account: Account)
    private static final Set<User> users = new HashSet<>();

    //You need to write a method findUserByLogin(String login) that returns an optional value of type Optional<User>.
    // If the user exists in the users set you need to return non-empty optional
    // wrapping the user inside, otherwise returned optional should be empty.
    public static Optional<User> findUserByLogin(String login) {
        return users.stream()
                .filter(user -> Optional.of(user)
                        .map(User::getLogin)
                        .filter(s -> s.equals(login))
                        .isPresent())
                .findFirst();
    }

    // prints an account balance for an existing user if `balance > 0`.
    // In this case, the result format should print the string:
    //login: balance
    public static void printBalanceIfNotEmpty(String userLogin) {
        users.stream()
                .filter(user -> Optional.of(user)
                        .map(User::getLogin)
                        .filter(s -> s.equals(userLogin))
                        .isPresent())
                .filter(user -> Optional.of(user)
                        .map(User::getAccount)
                        .map(Account::getBalance)
                        .filter(aLong -> aLong > 0)
                        .isPresent())
                .findFirst()
                .ifPresent(user -> System.out.println(user.getLogin() + ": " + user.getAccount().getBalance()));
    }
}
