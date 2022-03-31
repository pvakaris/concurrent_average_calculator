import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Data {

    private final int[] data;

    public Data(int arraySize, boolean randomData) {
        data = new int[arraySize];
        if(randomData) {
            fillDataArrayWithRandomInt(arraySize);
        }
        else {
            // Fills with 0, 1, 2, 3, 4, ..., (arraySize-1)
            for(int i = 0; i < arraySize; i ++) {
                data[i] = i;
            }
        }
    }

    public int[] getData() {
        return data;
    }

    public ArrayList<ArrayList<Integer>> getSlices(int numberOfSlices) {
        if(numberOfSlices > data.length) {
            numberOfSlices = data.length;
        }
        else if(numberOfSlices <= 0) {
            numberOfSlices = 1;
        }

        if(data.length % numberOfSlices == 0) {
            return getEqualSlices(numberOfSlices);
        }
        else {
            return getAllEqualSlicesWithTheLastOneSmaller(numberOfSlices);
        }
    }

    // ========================     PRIVATE METHODS     ========================

    private ArrayList<ArrayList<Integer>> getEqualSlices(int numberOfSlices) {
        int numbersInEachSlice = data.length / numberOfSlices;
        ArrayList<ArrayList<Integer>> slices = new ArrayList<>();
        for(int i = 0; i < numberOfSlices; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int y = 0; y < numbersInEachSlice; y++) {
                list.add(data[(i*numbersInEachSlice + y)]);
            }
            slices.add(list);
        }
        return slices;
    }

    private ArrayList<ArrayList<Integer>> getAllEqualSlicesWithTheLastOneSmaller(int numberOfSlices) {
        int numbersLeft = data.length % numberOfSlices;
        int numbersInEachFullSlice = (data.length / numberOfSlices) +1;
        ArrayList<ArrayList<Integer>> slices = new ArrayList<>();
        for(int i = 0; i < (numberOfSlices-1); i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int y = 0; y < numbersInEachFullSlice; y++) {
                list.add(data[(i*numbersInEachFullSlice + y)]);
            }
            slices.add(list);
        }
        // Adding those numbers that did not fit.
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < numbersLeft; i++) {
            list.add(data[(numbersInEachFullSlice * (numberOfSlices-1)) + i]);
        }
        slices.add(list);
        return slices;
    }

    private void fillDataArrayWithRandomInt(int arraySize) {
        Random random = new Random();
        for(int i = 0; i < arraySize; i ++) {
            data[i] = random.nextInt(Integer.MAX_VALUE);
        }
    }
}
