/**
 * class MyGraph. Will use Matrix to represent a simple weighted directed graph. There is no loop at vertex.
 * No more than one edge from vertex i to another vertex j. The vertices are numbered as 1, 2, ..., n
 * The graph with n vertices is reprented by an (n+1) by (n+1) matrix with row 0 and column 0 unused.
 * If there is an edge from vertex i to vertex j (i != j), then entry on row i column j of the matrix will 
 * be 1. If there is no edge between vertex i to vertex j (i != j), then the entry on row i column j of the 
 * matrix will be Integer.MAX_VALUE
 * 
 * @author Hong Biao Zeng
 * @version Dec 12, 2015
 */
import java.util.*;
public class MyGraph extends CSCI251ProjFive
{
    private int[][] graph;
    private int numberOfVertices;
    
    /**
     * create a graph with given number of vertices with no edges
     * @param numberOfVertices number of vertices of the graph
     */
    public MyGraph(int numberOfVertices){
        this.numberOfVertices = numberOfVertices;
        graph = new int[numberOfVertices+1][numberOfVertices+1];
    }
    
    /**
     * create a graph with given matrix representation
     * @param graph The matrix representation on the given graph. Assume column 0 and row 0 are not used
     */
    public MyGraph(int [][] graph){
        this.graph = graph;
        // change any 0 to infinity if the 0 is not on diagonal
        for(int i = 1; i < graph.length; i++){
            for(int j = 1; j < graph.length; j++){
                if(i == j) graph[i][j] = 0;
                else if(i != j && graph[i][j] == 0)
                    graph[i][j] = Integer.MAX_VALUE;
                //System.out.println(graph[i][j]);
            }
        }
        numberOfVertices = graph.length - 1;
        //System.out.println(graph.length);
    }
    
    /**
     * return a String that represent the vertices in order if the BFS algorithm is used to traversal the graph
     * starting from the given vertex. If the startVertex not exists, return an error message
     * @param startVertex The vertex where the traversal starts
     * @return A String that describes the vertices visited in order
     */
    public String bfs(int startVertex){
        // the student implement this
        String message = "";
        int currentV; 
                
        if(startVertex >= graph.length)
        {
            return "The vertex you are trying to start from is invalid.";
        }
        Queue<Integer> queue = new LinkedList<>(); //Creates a new stack.
        Stack<Integer> visited = new Stack<Integer>(); //Creates a new stack for visited items.
        
        
        queue.add(startVertex); //Pushes startVertex to stack.
        //int count = 0;
        while(queue.peek() != null)
        { //While the queue is not empty.
           // count++;
            currentV = queue.remove(); //currentV now holds the removed item from the queue.
            
            if(visited.search(currentV) == -1)
            { //If the currentV item is not already in the stack.
                visited.push(currentV); //Pushes currentV onto the visited stack.
                
                //for(int i = graph.length - 1; i > 0; i--)
                for(int i = 1; i < graph.length; i++)
                { //Loops through each item adjacent to currentV. 
                    //if(graph[currentV][i] == 1)
                    if(graph[currentV][i] == 1 && visited.search(i) == -1)
                    {
                        //System.out.println(i);
                        queue.add(i); //Adds adjacent onto the queue.
                    }
                }
                //System.out.println(queue);
                message += currentV; //Adds popped item to the string only IF it wasn't visited.
                //if(stack.peek() != -1)
                if(queue.peek() != null)
                { //If it is not the last value on the stack.
                    message += ", ";
                }
                
            }
        }
        //System.out.println("The count is: " + count);
        return message;        
    }
        
    
    /**
     * return a String that represents the vertices in order if the DFS algorithm is used to traversal the graph
     * starting from the given vertex. If the startVertex not exist, return an error message
     * @param startVertex The vertex where the traversal starts
     * @return An ArrayList of Integer that represents the vertices visited in order
     */
    public String dfs(int startVertex){
        // the student implement this
        String message = "";
        int currentV; 
                
        if(startVertex >= graph.length)
        {
            return "The vertex you are trying to start from is invalid.";
        }
        Stack<Integer> stack = new Stack<Integer>(); //Creates a new stack.
        Stack<Integer> visited = new Stack<Integer>(); //Creates a new stack for visited items.
        
        stack.push(startVertex); //Pushes startVertex to stack.
        //int count = 0;
        while(!stack.empty())
        { //While the stack is not empty.
           // count++;
            currentV = stack.pop(); //currentV now holds the popped item on the stack.
            
            if(visited.search(currentV) == -1)
            { //If the currentV item is not already in the stack.
                visited.push(currentV); //Pushes currentV onto the visited stack.
                
                //for(int i = 1; i < graph.length; i++)
                for(int i = graph.length - 1; i > 0; i--)
                { //Loops through each item adjacent to currentV. 
                    //if(graph[currentV][i] == 1)
                    if(graph[currentV][i] == 1 && visited.search(i) == -1)
                    {
                        //System.out.println(i);
                        stack.push(i); //Push adjacent onto the stack.
                    }
                }
                //System.out.println(stack);
                message += currentV; //Adds popped item to the string only IF it wasn't visited.
                //if(stack.peek() != -1)
                if(!stack.empty())
                { //If it is not the last value on the stack.
                    message += ", ";
                }
                
            }
        }
        //System.out.println("The count is: " + count);
        return message;
    }
}


/*
 * OUTPUT
 * 
*********************************
              MENU             *
1. Enter a graph              *
2. Breadth First Search       *
3. Depth First Search         *
4. Quit                       *
********************************
Enter your choice: 1
Enter an graph.
First enter the number of vertices: 6
Enter the matrx reprentation of the graph.
If no edge between two vertices, enter 0
0 1 0 0 1 0 1 0 1 0 1 0 0 1 0 1 0 0 0 0 1 0 1 1 1 1 0 1 0 0 0 0 0 1 0 0
*********************************
*              MENU             *
* 1. Enter a graph              *
* 2. Breadth First Search       *
* 3. Depth First Search         *
* 4. Quit                       *
********************************
Enter your choice: 2
Enter the start vertex: 6
The result for BFS is: 6, 4, 3, 5, 2, 1, 
*********************************
*              MENU             *
* 1. Enter a graph              *
* 2. Breadth First Search       *
* 3. Depth First Search         *
* 4. Quit                       *
********************************
Enter your choice: 3
Enter the start vertex: 6
The result for DFS is: 6, 4, 3, 2, 1, 5, 
*********************************
*              MENU             *
* 1. Enter a graph              *
* 2. Breadth First Search       *
* 3. Depth First Search         *
* 4. Quit                       *
********************************
Enter your choice: 4
Make sure you run enough test before you turn it in
 */