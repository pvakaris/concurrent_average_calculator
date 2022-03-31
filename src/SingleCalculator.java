import java.util.ArrayList;

public class SingleCalculator implements Runnable{

    private boolean stopped = false;
    private final ArrayList<Integer> numbers;
    private int result = 0;

    public SingleCalculator(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        int sum = 0;
        for(int x : numbers) {
            sum += x;
        }
        result = sum / numbers.size();
    }

    public void setStopped() {
        stopped = true;
    }

    public boolean isStopped() {
        return stopped;
    }

    public int getResult() {
        return result;
    }
}
