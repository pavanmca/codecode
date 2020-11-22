
import java.util.*;
public class dijkastras {
    public static void main(String[] args) {
        System.out.println("Graph: Adjacency Matrix: ");
        int vertices=8;
        Integer[][] graph = new Integer[8][8];
        buildGraph(graph);
        printGraph(graph);
        Integer[] cost = new Integer[vertices];
        int start=0;
        cost[start]=0;
        Set<Integer> known = new HashSet<>();
        //run dijkastra's algorithm to generate cost array
        dijkastras(graph,cost,known);
        //print cost
        System.out.println("Final cost array: ");
        for(int n:cost){
            System.out.print(" "+n+" ");
        }
        
    }
    private static void dijkastras(Integer[][] graph,Integer[] cost, Set<Integer> known){
        //run till known. size eqlas to total vertices (visited all nodes)
        while(known.size()!=graph.length){
            //get the verttex with minimum cost
            int minCostVertex = findUnknownMinCostVertex(cost,known);
            //edge case when there is no connectivity to any vertex
            if(minCostVertex==Integer.MAX_VALUE) break;
            //add current vertex to known list
            known.add(minCostVertex);
            //retrieve all neighbors of current vertex
            List<Integer> neighbors = getAllNeighbors(minCostVertex,graph);
            //if no neighbor to visit
            if(neighbors.size()==0) continue;
            //loop through all neighbors and update cost array if new cost is less than current cost
            for(int neighbor:neighbors){
                if(cost[neighbor]==null || cost[neighbor]>cost[minCostVertex]+graph[minCostVertex][neighbor]){
                    cost[neighbor] = cost[minCostVertex]+graph[minCostVertex][neighbor];
                }
            }
            
        }
        //update all vertices with null to -1, it means there is no path from source to that vertex
        for(int i=0;i<cost.length;i++){
            if(cost[i]==null) cost[i]=-1;
        }
    }
    
    //Method to get all neighbors of a vertex
    private static List<Integer> getAllNeighbors(int vertex,Integer[][] graph){
        List<Integer> neighbors=new ArrayList<>();
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph.length;j++){
                //if value is non-null
                if(graph[vertex][i]!=null) {
                    neighbors.add(i);
                    // System.out.println(vertex);
                }
            }
        }
        return neighbors;
    }
    
    //find a min vertex which is not known or visited till now
    private static int findUnknownMinCostVertex(Integer[] cost, Set<Integer> known){
        int min=Integer.MAX_VALUE;
        for(int i=0;i<cost.length;i++){
            if(cost[i]!=null && !known.contains(i)){
                if(cost[i]<min){
                    min=i;
                }
            }
        }
        return min;
    }
    //helper function to print a graph in adjacency format
    private static void printGraph(Integer[][] graph){
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph.length;j++){
                if(graph[i][j]!=null){
                     System.out.print(" "+graph[i][j]+" ");
            }else{
                System.out.print(" 0 ");
            }
        }
            System.out.println();
        }
    }
    
    //build grapg as adjacency matrix
    //value in cell is the weight from index 0 vertex to index 1 vertex
    private static void buildGraph(Integer[][] graph){
        graph[0][1]=5;
        graph[0][3]=5;
        graph[0][4]=3;
        graph[0][3]=5;
        graph[1][0]=5;
        graph[1][2]=3;
        graph[1][5]=9;
        graph[1][6]=1;
        graph[2][1]=3;
        graph[2][5]=9;
        graph[3][0]=5;
        graph[4][0]=3;
        graph[4][7]=8;
        graph[5][1]=9;
        graph[5][2]=9;
        graph[5][7]=2;
        graph[6][1]=1;
        graph[6][7]=3;
        graph[7][4]=8;
        graph[7][5]=2;
        graph[7][6]=3;
    }
}