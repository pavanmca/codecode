import java.util.PriorityQueue;
//Time Complexity: O(nlogn) -> n is number of elements in stones array
//Space Complexity: O(n) -> because we are using max Heap of size n
public class LastStoneWeight {
    public static void main(String[] args){
        int[] stones = {2,7,4,1,8,1};
        System.out.println("Last stone weight: "+lastStoneWeight(stones));

    }

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);
        for(int stone:stones){
            maxHeap.offer(stone);
        }
        while(maxHeap.size()>1){
            maxHeap.offer(maxHeap.poll()-maxHeap.poll());
        }
        int result = maxHeap.isEmpty()?0:maxHeap.poll();
        return result;
    }
}
