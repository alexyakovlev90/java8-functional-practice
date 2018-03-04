package multifunction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by Alexander Yakovlev on 04.03.2018.
 */
public class MultifunctionalMapper {
    /**
     * The function accepts a list of mappers and returns an operator that accepts a list of integers
     * and sequentially applies each mapper to each value (perform a transformation)
     */
    public static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper =
            input -> output -> input.stream()
                    .flatMap(
                            intUnaryOperator -> output.stream()
                                    .map(intUnaryOperator::applyAsInt)
                    )
                    .collect(Collectors.toList());

    /**
     * EXAMPLE: the operator transforms each number to the same number (perform the identity transformation)
     * <p>
     * It returns a list of the same numbers.
     */
    public static final UnaryOperator<List<Integer>> identityTransformation =
            multifunctionalMapper.apply(Collections.singletonList(x -> x));

    /**
     * The operator accepts an integer list.
     * It multiplies by two each integer number and then add one to its.
     * <p>
     * The operator returns transformed integer list.
     */
    public static final UnaryOperator<List<Integer>> multTwoAndThenAddOneTransformation =
            multifunctionalMapper.apply(Collections.singletonList(x -> x * 2 + 1));

    /**
     * The operator accepts an integer list.
     * It squares each integer number and then get the next even number following it.
     * <p>
     * The operator returns transformed integer list.
     */
    public static final UnaryOperator<List<Integer>> squareAndThenGetNextEvenNumberTransformation =
            multifunctionalMapper.apply(
                    Collections.singletonList(x -> {
                        int sqrtNext = x * x + 1;
                        return sqrtNext % 2 == 0 ? sqrtNext : sqrtNext + 1;
                    })
            );

    public static void main(String[] args) {
        List<Integer> list = squareAndThenGetNextEvenNumberTransformation.apply(Arrays.asList(1, 2, 3, 4));

        System.out.println(list);
    }
}
