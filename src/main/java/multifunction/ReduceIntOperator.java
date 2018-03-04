package multifunction;

import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;

/**
 * Created by Alexander Yakovlev on 04.03.2018.
 */
public class ReduceIntOperator {
    /**
     * The operator combines all values in the given range into one value
     * using combiner and initial value (seed)
     */
    public static final BiFunction<Integer, IntBinaryOperator, IntBinaryOperator> reduceIntOperator =
            (init, operator) -> (left, right) -> {
                int result = init;
                for (int i = left; i <= right; i++) {
                    result = operator.applyAsInt(result, i);
                }
                return result;
            };
    /**
     * The operator calculates the sum in the given range (inclusively)
     */
    public static final IntBinaryOperator sumOperator =
            (xIn, yIn) -> reduceIntOperator.apply(0, (x, y) -> x + y).applyAsInt(xIn, yIn);

    /**
     * The operator calculates the product in the given range (inclusively)
     */
    public static final IntBinaryOperator productOperator =
            reduceIntOperator.apply(1, (x, y) -> x * y);


    public static void main(String[] args) {
        int left = 5;
        int right = 6;
        int sum = sumOperator.applyAsInt(left, right);
        int prod = productOperator.applyAsInt(left, right);

        System.out.println("sum = " + sum);
        System.out.println("prod = " + prod);
    }

}
