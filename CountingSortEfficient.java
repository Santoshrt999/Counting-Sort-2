package com.graphs;

import java.util.Arrays;

public class CountingSortEfficient {

    public static int[] countingSort(int[] A){

        int max = Arrays.stream(A).max().getAsInt();
        int min = Arrays.stream(A).min().getAsInt();
        int range = max-min+1;

        int[] count = countElements(A, range, min);

        int[] output = new int[A.length];

        /**
         * In order to sort the sample input array, we should first start with the number 5, since it's the last element. According to C[5], there are 11 elements are less than or equal to the number 5.
         *
         * So, 5 should be the 11th element in the sorted array, hence the index 10:
         */
        for(int i=A.length-1; i>=0; i--){
            int current = A[i]-min; // which is the last element
            output[count[current]-1] = A[i]; //now get the occurrance of that element from count and put in output -1
            count[current]--;
        }

        System.arraycopy(output, 0, A, 0, A.length);

        return A;

    }

    /**
     * Calculate frequency and return the sum of elements from 1 to n.
     */
    private static int[] countElements(int[] A, int range, int min) {

        int[] count = new int[range];
        //count
        for(int i: A){
            count[i - min]++; //count the frequency
            //use min for calculating negative values
        }

        //now sum up the previous frequencies with the incoming one
        for(int i=1; i<count.length; i++){
            count[i] += count[i-1];
        }
        return count;

    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(countingSort(new int[]{4, 3, 2, 5, -1, 4, 3, 5, 1, 0, 2, 5})));

    }

}
