package multifunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Alexander Yakovlev on 04.03.2018.
 */
public class MultifunctionalMapperV2 {
    /**
     * The function accepts a list of mappers and returns an operator that accepts a list of integers
     * and sequentially applies each mapper to each value (perform a transformation)
     */
    public static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper =
            input -> output ->
                    IntStream.range(0, input.size())
                    .map(i -> input.get(i).applyAsInt(output.get(i)))
                    .boxed()
                    .collect(Collectors.toList());


    /**
     * EXAMPLE: the operator transforms each number to the same number (perform the identity transformation)
     * <p>
     * It returns a list of the same numbers.
     */
    public static final UnaryOperator<List<Integer>> identityTransformation =
            list -> {
                IntUnaryOperator intUnaryOperator = operand -> operand;
                List<IntUnaryOperator> collect = list.stream()
                        .map(integer -> intUnaryOperator)
                        .collect(Collectors.toList());
                UnaryOperator<List<Integer>> unaryOperator = multifunctionalMapper.apply(collect);
                return unaryOperator.apply(list);
            };

    /**
     * The operator accepts an integer list.
     * It multiplies by two each integer number and then add one to its.
     * <p>
     * The operator returns transformed integer list.
     */
    public static final UnaryOperator<List<Integer>> multTwoAndThenAddOneTransformation =
            list -> {
                IntUnaryOperator intUnaryOperator = operand -> operand * 2 + 1;
                List<IntUnaryOperator> collect = list.stream()
                        .map(integer -> intUnaryOperator)
                        .collect(Collectors.toList());
                return multifunctionalMapper.apply(collect).apply(list);
            };

    /**
     * The operator accepts an integer list.
     * It squares each integer number and then get the next even number following it.
     * <p>
     * The operator returns transformed integer list.
     */
    public static final UnaryOperator<List<Integer>> squareAndThenGetNextEvenNumberTransformation =
            list -> {
                IntUnaryOperator intUnaryOperator = operand -> {
                    int sqrtNext = operand * operand + 1;
                    return sqrtNext % 2 == 0 ? sqrtNext : sqrtNext + 1;
                };
                List<IntUnaryOperator> collect = list.stream()
                        .map(integer -> intUnaryOperator)
                        .collect(Collectors.toList());
                return multifunctionalMapper.apply(collect).apply(list);
            };

    public static void main(String[] args) {
        List<Integer> list = squareAndThenGetNextEvenNumberTransformation.apply(Arrays.asList(1, 2, 3, 4));

        System.out.println(list);
    }
}
