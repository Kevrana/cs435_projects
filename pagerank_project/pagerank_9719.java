// KEVIN RANA cs435 9719 mp
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.text.DecimalFormat;

public class pagerank_9719
{
	// class that manages the directed graphs that
	// are constructed for the adjacency list
	static class dirGraph_9719{ 
		
		// the adjacency list being used to link vertices and edges
		// worse case runtime would be bigOh(1)
		// makes Linkedlist of type Integer to store the the node values
		LinkedList<Integer> adjList[]; 
		
		// the vertex of the graph node
		int vert; 
		// the counter for the graph
		int ctrG;
		// the counter for number of edges added to the graph
		int ctrE;
		
		// constructs the directed graph using the adjacency list
		dirGraph_9719(int vert) {
			// refers to the current vertex instance here and assigns paramater's passed value to it
			this.vert = vert;
			int ctrG = 0;
			// the adjList gets the size of the number of webpages or vertices of the graph
			adjList = new LinkedList[vert];
			
			//creates a new link each time for each of the vertices so it can have adjacent points
			for ( int x = 0; x < vert; x++){
				// worse case runtime would be bigOh(1)
				adjList[x] = new LinkedList<>();
			}
		}
	}
	
	// inserts a note into the directed graph that connects it with a start and ending node
	// takes the vertex's beginning and ending points and the graph it is for as parameters
	static void insertEdge_9719(int i, int j, dirGraph_9719 g){
		// points to the start node 
		g.adjList[i].add(j);
		// points to the node its going to 
		g.adjList[j].add(i);
		int ctrE = 0;
	}
	
	// performs pagerank alogorithm 
	// with number of webpages or vetices n, a buffer d of 0.85
	// a directed graph g of dirGraph_9719
	// and iterations, and initial value amounts
	static void pageranker_9719( int n, double buffer, dirGraph_9719 g, int iterations, int initialvalue)
	{
		double d = buffer;
		int x = 0;
		int t = iterations;
		int k = 1;
		double pageRank[] = new double[n];
		
		
		// goes through each vertex in the directed graph g 
		// and gets the value of that node and
		// stores the value of the vertex and edge into pageRank[y] 
		for(int y = 0; y < g.vert; y++){
			for(Integer direction: g.adjList[y]){
				pageRank[y] = direction;				
			}
		}
	
		// continue while k is less than or equal to the # of iterations t
		while ( k <= t ){
			//prints out t-th iteration
			System.out.print("\n Iter :  "+ k +" :");
			
			// pagerank computation with d = 0.85
			for (x = 0; x < n; x++){
				// pagerank algorithm which is ((1-0.85)/#of webpages) + 0.85 * value of webpage
				pageRank[x] = ( (1 - d ) / n ) + d * pageRank[x];
				//prints out to 6 decimal points the value of the pagerank at that webpage at that iteration
				System.out.print("P[" + x + "] = " + new DecimalFormat("0.000000").format( pageRank[x])+ " ");
			}
			// increments k in while loop
			k++; 
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		//checks to see if any command line arguments exist.
		if (args.length > 0 ){
			try {
				// iterations gets 1st argument which is # of iterations
				// initialvalue gets 2nd argument which is initial value
				// gets the 3rd argument which is the file of a directed graph
				int iterations = Integer.parseInt(args[0]); 
				int initialvalue = Integer.parseInt(args[1]); 
				File file = new File(args[2]); 
				
				// reads the file
				FileReader fr = new FileReader(file);
				// reads the contents of the file
				BufferedReader br = new BufferedReader(fr);
				// used to read through the lines of the file
				String line = null;
				
				// reada the first line which contains the number of vertices and edges
				String first = null;
				first = br.readLine();
				String[] firstLine = first.split(" ");
				
				// stores the number of vertices which is the 1st value on line 1
				int numV = Integer.parseInt(firstLine[0]);
				// stores the number of edges which is the 2nd value on line 1
				int numE = Integer.parseInt(firstLine[1]);
				
				// using the numV #vertices value
				// creates a graph of given size
				dirGraph_9719 g = new dirGraph_9719(numV);
				
				
				// reads the rest of each line in the file
				// and adds each vertex and edge to the directed graph
				while ((line = br.readLine()) != null ){
					// splits the line by the space to seperate vertex and edge
					String[] parts = line.split(" ");
					int i,j; 
					// stores the vertex value in i
					// stores the edge value in j
					if ( parts.length == 2 ){
						 i = Integer.parseInt(parts[0]);
						 j = Integer.parseInt(parts[1]);
						 // adds the edge link to the directed graph in the adjacency list
						 insertEdge_9719(i,j,g);
						}
				}
				
				// variables used for the pagerank
				// buffer size d  between 0 and 1, 0.85 given in mp description
				// n is number of vertices (webpages)
				// t is for the iterations 
				// starRank is for the initial value
				// pageRank[] stores the value of the webpages with vertex and edge
				// x is a counter 
				double d = 0.85; 
				int n = numV;
				int t;
				double startRank = initialvalue;
				int inval = initialvalue;
				double pageRank[] = new double[n];
				int x = 0;
					
				
				// if iterations is less than 0 then 
				//it converts it to it's positive number
				iterations = Math.abs(iterations);
					
				// if the number of iterations is greater than 0
				// make t(iterations) equal to that value
				// else make it equal to run for as many iterations needed  
				// to achieve the FIXED errorrate of 10 ^ -4
				if ( iterations > 0) {
					t = iterations ; 
				}
				else {
					t = 60; 
				}
				
				// if initialvalue is 0
				if (inval == 0){ 
					startRank = 0;
				}
				// if initialvalue is 1
				else if (inval == 1){ 
					startRank = 1;
				}
				 // if initialvalue is -1
				else if ( inval == -1){
					startRank = 1.0/n;
				}
				// if initialvalue is -2
				else if (inval == -2){ 
					startRank = 1/(Math.sqrt(n));
				}
				
				// If n > 10 then the values for
				// iterations, initialvalue are to be 0 and -1 respectively
				// In such a case the pageranks at the stopping
				// iteration are ONLY shown and one per line.
				if ( n > 10) {
					inval = -1;
					t = 0;
					
					//prints out t-th iteration
					System.out.println("\n Iter :  "+ iterations +" :");
					
					// base iteration pagerank
					for(x = 0; x < n; x++){
						// stores the initial value in each rank
						pageRank[x] = startRank;
					}
					
					// pagerank computation with d = 0.85
					for (x = 0; x < n; x++){
						// pagerank algorithm which is ((1-0.85)/#of webpages) + 0.85 * value of webpage
						pageRank[x] = ( (1 - d ) / n ) + d * pageRank[x];
						//prints out to 6 decimal points the value of the pagerank at that webpage at that iteration
						System.out.println("P[" + x + "] = " + new DecimalFormat("0.000000").format( pageRank[x])+ " ");
					}
					// exits the program after the iteration completes the pagerank process
					System.exit(0);
					
				}
				
				// base iteration pagerank
				for(x = 0; x < n; x++){
					pageRank[x] = startRank;
				}

				// prints out base iteration pagerank
				System.out.print("\n Base :  0 :");
				for(x = 0; x < n; x++){
					System.out.print("P["+ x +"] = " + new DecimalFormat("0.000000").format( pageRank[x]) + " ");
				}
				
				// calls the pagerank method and passes the 
				// the value of the number of vertices(webpages)
				// the buffer size of 0.85
				// the directed graph g that has the vertices and edges inserted into the adjList
				// and the initial value 
				// runs through the ranker algorithm method
				pageranker_9719( n, d, g, t, inval);
				
				// closes the bufferedreader so no unwanted content is read into the program
				br.close();
				// closes the file reader so no unwanted files are read into the program
				fr.close();
		
			}
			
			// if no file was found print notification to output
			catch (FileNotFoundException ex) {
				System.out.println("Not able to open file");
			}
			// if not able to read file then it backtraces the file's error stream
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		// if no command line arguments were given
		// notifies user with a simple message
		else {
			System.out.println("No command line arguments were inputed/found");
		}
	}
	
}