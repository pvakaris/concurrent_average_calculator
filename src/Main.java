import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int[] amountsOfThreads = {1, 2, 4, 8, 16, 32, 64};
        int dataSize = 986752;
        AverageCalculator calculator = new AverageCalculator(dataSize, true);
        long[] averages = calculator.getAverageExecutionTime(amountsOfThreads, 10);

        for(int i = 0; i < amountsOfThreads.length; i++) {
            System.out.println(amountsOfThreads[i] + " slices --> " + averages[i] + " ns.");
        }
    }
}
