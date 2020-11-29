import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Time complexity - O(n)
//Space complexity - O(1) 
public class SmallestWindowToBeSorted {
    public static void main(String[] args){
        // int[] arr = {1,2,3,6,5,4,7,8};
        int[] arr = {1,2,9,4,3,7,8,19};
        // int[] arr = {1,10,9,3,0,5,4,8};
        // int[] arr = {1,2,4,5,6,3};
        System.out.println("Smallest Window to be sorted to make complete array sorted: Time->O(n log(n)),Space: O(n) "+getSmallestWindowToBeSortedBruteForce(arr));
        System.out.println("Smallest Window to be sorted to make complete array sorted: Time->O(n),Space: O(1) "+getSmallestWindowToBeSorted(arr));
    }

    private static List<Integer> getSmallestWindowToBeSortedBruteForce(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int leftIndex = -1;
        int rightIndex = -1;
        //create another copy of array
        int[] sortedArr = arr.clone();
        //Sort new array
        Arrays.sort(sortedArr);
        //loop through both arrays and find mismatched elements, update leftIndex and rightIndex
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=sortedArr[i] && leftIndex==-1) leftIndex=i;
            else if(arr[i]!=sortedArr[i]) rightIndex=i;
        }
        result.add(leftIndex);
        result.add(rightIndex);
        //return result
        return result;
    }

    private static List<Integer> getSmallestWindowToBeSorted(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int leftIndex = -1;
        int rightIndex=-1;
        int maxSeenSoFar = Integer.MIN_VALUE;
        int minSeenSoFar = Integer.MAX_VALUE;
        //scan from left and find a index where value is less than max seen so far,
        //note this as right Index
        for(int i=0;i<arr.length;i++){
            maxSeenSoFar = Math.max(maxSeenSoFar,arr[i]);
            if(arr[i]<maxSeenSoFar){
                rightIndex=i;
            }
        }
        //scan from right and find a index where value is greater than min seen so far,
        //note this as left Index
        for(int i=arr.length-1;i>=0;i--){
            minSeenSoFar = Math.min(minSeenSoFar,arr[i]);
            if(arr[i]>minSeenSoFar){
                leftIndex=i;
            }
        }
        result.add(leftIndex);
        result.add(rightIndex);
        return result;
    }
}
