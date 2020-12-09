import java.util.PriorityQueue;
//Time Complexity: O(k log(k)+(n-k)log(k))==>O(n log(k))
//Space Complexity: O(k)
public class KthLargestNumber {
    public static void main(String[] args){
        int[] array = {34,67,23,3,4,98};
        //3,4,23,34,67,98
        int k = 3;
        // List<Integer> result = new ArrayList<>();
        int result = findKthLargestNumber(array,k);
        System.out.println("kth largest number  in given array = "+result);
    }
    

    private static int findKthLargestNumber(int[] array, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->a-b);
        for(int i=0;i<k;i++){
            minHeap.offer(array[i]);
        }
        for(int i=k;i<array.length;i++){
            if(array[i]>minHeap.peek()){
                minHeap.poll();
                minHeap.offer(array[i]);
            }
        }
        return minHeap.peek();
    }
}
