import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
//Time Complexity: O(k log(k)+(n-k)log(k))==>O(n log(k))
//Space Complexity: O(k)
public class KSmallestNumbers {
    public static void main(String[] args){
        int[] array = {34,67,23,3,4,98};
        int k = 3;
        List<Integer> result = new ArrayList<>();
        result = findKSmallestNumber(array,k);
        System.out.println(result);
    }

    private static List<Integer> findKSmallestNumber(int[] array, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);
        for(int i=0;i<k;i++){
            maxHeap.offer(array[i]);
        }
        for(int i=k;i<array.length;i++){
            if(array[i]<maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        return new ArrayList<>(maxHeap);
    }
}
