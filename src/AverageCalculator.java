import java.util.ArrayList;

public class AverageCalculator {

    private final Data data;

    public AverageCalculator(int dataSize, boolean randomData) {
        data = new Data(dataSize, randomData);
    }

    public Data getData() {
        return data;
    }

    public long[] getAverageExecutionTime(int[] slices, int timesToExecute) {
        long[] averages = new long[slices.length];
        for(int i = 0; i < slices.length; i++) {
            averages[i] = getExecutionTimeAverageOfSpecificAmountOfSlices(timesToExecute, slices[i]);
        }
        return averages;
    }

    // ========================     PRIVATE METHODS     ========================

    private long getExecutionTimeAverageOfSpecificAmountOfSlices(int timesToExecute, int slices) {
        long sum = 0;
        for(int i = 0; i < timesToExecute; i++) {
            sum += getExecutionTime(slices);
        }
        return sum / slices;
    }

    private int execute(int numberOfSlices) {
        ArrayList<SingleCalculator> calculators = getSingleCalculators(getData().getSlices(numberOfSlices));
        ArrayList<Thread> threads = getThreads(calculators);
        runThreads(threads);

        int sum = 0;
        for(SingleCalculator calculator : calculators) {
            sum += calculator.getResult();
        }
        return sum / numberOfSlices;   // The average
    }

    private long getExecutionTime(int numberOfSlices) {
        long startTime = System.nanoTime();
        int result = execute(numberOfSlices);
        // System.out.println("With " + numberOfSlices + " slices the execution result is: " + result(numberOfSlices));
        return System.nanoTime() - startTime;
    }

    private ArrayList<SingleCalculator> getSingleCalculators(ArrayList<ArrayList<Integer>> slices) {
        ArrayList<SingleCalculator> calculators = new ArrayList<>(slices.size());
        for (ArrayList<Integer> slice : slices) {
            calculators.add(new SingleCalculator(slice));
        }
        return calculators;
    }

    private ArrayList<Thread> getThreads(ArrayList<SingleCalculator> calculators) {
        ArrayList<Thread> threads = new ArrayList<>(calculators.size());
        for (SingleCalculator calculator : calculators) {
            threads.add(new Thread(calculator));
        }
        return threads;
    }

    private void runThreads(ArrayList<Thread> threads) {
        for(Thread thread : threads) {
            thread.start();
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
