import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
//Time Complexity - O(V+E)
//Space Complexity - O(V)
public class TopologicalSort_Kahns_BFS {
    public static void main(String[] args){
        Map<Integer,List<Integer>> courses_prerequisite = new HashMap<>();
        buildMap(courses_prerequisite);
        System.out.println("Courses order using Topological Sorting: "+getCourseOrderTopSort(courses_prerequisite));
    }
    private static List<Integer> getCourseOrderTopSort(Map<Integer,List<Integer>> courses_prerequisite){
        List<Integer> courseOrder = new ArrayList<>();
        //use bfs, (add node only if all in degree 0)
        //loop until queue is empty
            //if indegree 0 add it to topo sort
            //decrement indegree of child nodes
            //if indegree becomes 0 put in queue
        //check if size(toposort) != no of Vertices -> it means Graph contains cycle-> return null
        //else return topo;
        Queue<Integer> queueBFS = new LinkedList<>();
        Map<Integer,Integer> indegree = new HashMap<>();
        buildInDegrees(courses_prerequisite,indegree);
        // System.out.println(indegree);
        for(Map.Entry<Integer,Integer> entry:indegree.entrySet()){
            if(entry.getValue()==0){
                queueBFS.add(entry.getKey());
            }
        }
        while(!queueBFS.isEmpty()){
            int current = queueBFS.poll();
            courseOrder.add(current);
            for(int v:courses_prerequisite.get(current)){
                indegree.put(v,indegree.get(v)-1);
                if(indegree.get(v)==0){
                    queueBFS.add(v);
                }
            }
        }
        //cycle is present
        if(courseOrder.size()!=courses_prerequisite.size()) return null;
        return courseOrder;
    }
    
    private static void buildInDegrees(Map<Integer, List<Integer>> courses_prerequisite,
            Map<Integer, Integer> indegree) {
                for(int i =0 ;i<courses_prerequisite.size();i++){
                    indegree.put(i,0);
                }
                for(Map.Entry<Integer,List<Integer>> entry:courses_prerequisite.entrySet()){
                    List<Integer> value = entry.getValue();
                    for(int v:value){
                        indegree.put(v,indegree.getOrDefault(v, 0)+1);
                    }
                }
    }

    private static void buildMap(Map<Integer, List<Integer>> courses_prerequisite) {
        // List<Integer> prerequisite1 = new ArrayList<>(Arrays.asList(1,2));
        courses_prerequisite.put(0,new ArrayList<>(Arrays.asList(1,2)));
        courses_prerequisite.put(1,new ArrayList<>(Arrays.asList(2)));
        courses_prerequisite.put(2,new ArrayList<>(Arrays.asList()));
    }
}
/*
0  -----> 1
|
|
2
*/