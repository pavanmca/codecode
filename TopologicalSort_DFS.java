import java.util.*;
//Time Complexity: O(V+E)
//Space complexity: O(V)
public class TopologicalSort_DFS {
    //normal dfs
    //while returning from dfs, assign end vertex as last value of topo sort
    //if found loop, return null
enum Status{
    VISITED,VISITING,UNVISITED
}
    public static void main(String[] args){
        Map<Integer,List<Integer>> courses_prerequisite = new HashMap<>();

        buildMap(courses_prerequisite);
        
        System.out.println("Courses order using Topological Sorting: ");
        Stack<Integer> topo = getCourseOrderTopSort(courses_prerequisite);
        System.out.print("[");
        while(!topo.isEmpty()){
            System.out.print(" "+topo.pop());
        }
        System.out.print("]");
    }
   private static Stack<Integer> getCourseOrderTopSort(Map<Integer,List<Integer>> courses_prerequisite){
       Stack<Integer> topo = new Stack<>();
       Status[] visited = new Status[courses_prerequisite.size()];
       for(int i=0;i<visited.length;i++){
           visited[i]=Status.UNVISITED;
       }
       for(int i=0;i<courses_prerequisite.size();i++){
        if(visited[i]==Status.UNVISITED){
            if(!topologicalSort_DFS(courses_prerequisite, topo,i,visited)){
                //cycle
                return null;
            }
        }
        
       }
       return topo;
   }
    
   private static boolean topologicalSort_DFS(Map<Integer, List<Integer>> courses_prerequisite, Stack<Integer> topo,int source, Status[] visited) {
       if(visited[source]==Status.VISITING){
           return false;
       }
        // visited.add(source);
        
       if(visited[source] == Status.UNVISITED){
           visited[source]=Status.VISITING;
        for(int n:courses_prerequisite.get(source)){
            
            
            if( !topologicalSort_DFS(courses_prerequisite, topo, n, visited)){
                return false;
            }
          
        }
        // else if(n!=parent){
        //     return false;
        // }
   
   visited[source]=Status.VISITED;
   topo.push(source);
       }
       
    return true;
   }

   private static void buildMap(Map<Integer, List<Integer>> courses_prerequisite) {
        // List<Integer> prerequisite1 = new ArrayList<>(Arrays.asList(1,2));
        courses_prerequisite.put(0,new ArrayList<>(Arrays.asList(1,2)));
        courses_prerequisite.put(1,new ArrayList<>(Arrays.asList(2)));
        courses_prerequisite.put(2,new ArrayList<>(Arrays.asList()));
    }
}
