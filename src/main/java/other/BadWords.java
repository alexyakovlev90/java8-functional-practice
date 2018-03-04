package other;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Alexander Yakovlev on 13/02/2018.
 */
public class BadWords {
    public static Stream<String> createBadWordsDetectingStream(String text, List<String> badWords) {
        return Stream.of(text.split(" "))
                .filter(badWords::contains)
                .distinct()
                .sorted();
    }
}
