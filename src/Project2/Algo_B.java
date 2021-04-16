package Project2;

import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class Algo_B {

	// arrays to store the information for each source Node with their respective k
	// nearest hospital found in BFS
	static LinkedList<Integer> distanceAr = new LinkedList<Integer>();
	static ArrayList<ArrayList<Integer>> pathAr = new ArrayList<ArrayList<Integer>>();

	// arraylist to store unique totalnodes
	static ArrayList<Integer> totalNodes = new ArrayList<Integer>();

	// arraylist to store nearest hospital from node
	static ArrayList<Integer> hosAr = new ArrayList<Integer>();

	// array to store output string
	static ArrayList<String> OutputAr = new ArrayList<String>();

	// ArrayList to store all hospital for each source Node in arrays
	static ArrayList<Node> allNodeHosAr = new ArrayList<Node>();

	// Arraylists to generate each node info to a nested arraylist
	static ArrayList<Node> subInfoAr = new ArrayList<Node>();

	// Driver Program
	public static void main(String args[]) throws IOException, InterruptedException {

		// reading map network file & creating a graph
		String filepath = "file1.txt";
		File NetworkDB = new File(filepath);
		Scanner sc = new Scanner(NetworkDB);

		int linecount = 0; // Intializing linecount as zero
		FileReader fr = new FileReader(NetworkDB); // Creation of File Reader object
		BufferedReader br = new BufferedReader(fr); // Creation of File Reader object
		String s;

		// count number of lines by reading content from the file line by line
		while ((s = br.readLine()) != null) {
			linecount++; // For each line increment linecount by one
		}

		// creating array lists for start and end nodes
		ArrayList<Integer> startList = new ArrayList<Integer>();
		ArrayList<Integer> endList = new ArrayList<Integer>();
		ArrayList<Integer> totalNodes = new ArrayList<Integer>();

		int NumEdges = 0;
		for (int i = 0; i < linecount; i++) {
			String line = sc.nextLine(); // read line
			if (!(line.startsWith("#"))) {
				String[] res = line.split("	");
				int curstart = Integer.parseInt((res[0]));
				int curend = Integer.parseInt((res[1]));

				startList.add(curstart);
				endList.add(curend);
				totalNodes.add(curstart);
				totalNodes.add(curend);
				NumEdges++;
			}

		}

		// find max in start and end list
		int maxStart = startList.get(0);
		for (int i = 0; i < startList.size(); i++) {
			if (startList.get(i) > maxStart) {
				maxStart = startList.get(i);
			}
		}
		int maxEnd = endList.get(0);
		for (int i = 0; i < endList.size(); i++) {
			if (endList.get(i) > maxEnd) {
				maxEnd = endList.get(i);
			}
		}
		int max = 0;
		if (maxEnd > maxStart)
			max = maxEnd;
		else
			max = maxStart;

		HashSet<Integer> uniqueNodes = new HashSet<Integer>(totalNodes);
		int v = uniqueNodes.size();
		int maxV_ID = max + 1;

		// number of vertices = max nodeID + 1
		System.out.println("Total number of nodes in Road Network: " + v);
		System.out.println("Total number of edges in Road Network: " + NumEdges);
		System.out.println();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(maxV_ID);
		for (int i = 0; i < maxV_ID; i++) {
			adj.add(new ArrayList<Integer>());
		}

		// adding edges from text file to adj list
		for (int i = 0; i < NumEdges; i++) {
			try {
				int a = startList.get(i);
				int b = endList.get(i);
				addEdge(adj, a, b);
			} catch (Exception e) {
				System.out.println(i);
			}
		}

		// reading hospital input file now
		ArrayList<Integer> nodeIdAr = new ArrayList<Integer>();
		File HospitalDB = new File("file2.txt");
		Scanner scan = new Scanner(HospitalDB);
		String Hospline = scan.nextLine();

		int Hosplinecount = 0; // Intializing linecount as zero

		// Creation of File Reader object
		FileReader fr1 = new FileReader(HospitalDB);
		BufferedReader br1 = new BufferedReader(fr1);
		String s1;

		// linecount
		while ((s1 = br1.readLine()) != null) {
			Hosplinecount++;
		}

		// remove first header line
		int numOfHospNodes = Hosplinecount - 1;

		// Read the hospital nodes
		for (int i = 0; i < numOfHospNodes; i++) {
			Hospline = scan.nextLine(); // read line
			int curnode = Integer.parseInt(Hospline);
			nodeIdAr.add(curnode); // add into nodeID array
		}

		// prints out array of hospital nodes
		System.out.println("Hospital nodes are: " + nodeIdAr);

		// user input top-k hospitals
		System.out.println("Specify top-k nearest hospitals: ");
		Scanner scan1 = new Scanner(System.in);
		int k = scan1.nextInt();

		// for loop to run BFS for different source Node
		long startTime = System.nanoTime();
		for (int i = 0; i < v; i++) {
			printShortestDistance(adj, i, totalNodes, maxV_ID, v);
			System.out.println(i + "/" + v);
			if (i == v - 1)
				System.out.println("Done!");
		}
		
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		long runtime_ms = totalTime/1000000;
		
	    DecimalFormat df = new DecimalFormat("#.00");
	    String runtime_formatted = df.format(runtime_ms);
	    
		//execution time in ms
		System.out.println("Execution time: "+ runtime_formatted + "ms for " + v + " nodes, h = " + nodeIdAr.size() + ", k = " + k);

		for (int j = 0; j < subInfoAr.size(); j++) {
			if (nodeIdAr.contains(subInfoAr.get(j).getNodeID())) {
				allNodeHosAr.add(subInfoAr.get(j));
			}
		}

		for (int j = 0; j < allNodeHosAr.size(); j++) {
			int count = 0;
			for (int i = j; i < allNodeHosAr.size(); i++) {
				if (allNodeHosAr.get(i).getSource() == j && count < k) {
					OutputAr.add(allNodeHosAr.get(i).getSource() + "  	" + allNodeHosAr.get(i).getNodeID() + "  	"
							+ allNodeHosAr.get(i).getDistance() + "  	" + allNodeHosAr.get(i).getPath());
					count++;
				}
				if (count == k) {
					break;
				}
			}
		}

		writeOutputToFile();
	}

	private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
		adj.get(i).add(j);
		adj.get(j).add(i);
	}

	// function to print the shortest distance and path
	// between source vertex and destination vertex
	private static Node[] printShortestDistance(ArrayList<ArrayList<Integer>> adj, int s, ArrayList<Integer> dest,
			int v, int k) {

		// predecessor[i] array stores predecessor of i
		// and distance array stores distance of i from s
		int pred[] = new int[v];
		int dist[] = new int[v];

		if (BFS(adj, s, dest, v, pred, dist, k) == false) {
			System.out.println("Given source and destination are not connected");
			return null;
		}

		// handle case when number of hospital found is less than k
		if ((hosAr.size() < k)) {
			System.out.println("Only " + hosAr.size() + " hospitals found instead of " + k);
			k = hosAr.size();
		}

		// array of nodes to store the info
		Node[] node = new Node[k];

		// store the information of the connected nodes found for the current source Node into
		// a Node array
		for (int i = 0; i < k; i++) {
			node[i] = new Node(s, hosAr.get(i), distanceAr.get(i), pathAr.get(i));
		}

		// clear the arrays storing the hospitals information for the current source
		// Node
		// to prevent interfering with the hospitals information for the next source
		// Node
		distanceAr.clear();
		pathAr.clear();
		hosAr.clear();

		for (int i = 0; i < k; i++) {
			subInfoAr.add(node[i]);
		}

		return node;
	}

	// Function to check if current vertex in BFS is found in hospital array
	public static boolean contains(final int[] arr, final int key) {
		return Arrays.stream(arr).anyMatch(i -> i == key);
	}

	// a modified version of BFS that stores predecessor of each vertex in array
	// pred and its distance from source in array dist
	private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, ArrayList<Integer> dest, int v, int pred[],
			int dist[], int k) {
		// a queue to maintain queue of vertices whose
		// adjacency list is to be scanned as per normal
		// BFS algorithm using LinkedList of Integer type
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// boolean array visited[] which stores the
		// information whether ith vertex is reached
		// at least once in the Breadth first search
		boolean visited[] = new boolean[v];

		// initially all vertices are unvisited
		// so v[i] for all i is false
		// and as no path is yet constructed
		// dist[i] for all i set to infinity
		for (int i = 0; i < v; i++) {
			visited[i] = false;
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
		}

		// now source is first to be visited and
		// distance from source to itself should be 0
		visited[src] = true;
		dist[src] = 0;
		queue.add(src);

		// BFS Algorithm
		int counter = 0;
		while (!queue.isEmpty() && counter < k) {
			int u = queue.remove();

			// case when source Node is a hospital
			// check counter == 0 to ensure the current Node 'u' is the source Node
			if (dest.contains(u) && counter == 0) {
				hosAr.add(u);
				distanceAr.add(0);

				ArrayList<Integer> path = new ArrayList<Integer>();
				path.add(u);
				pathAr.add(path);
				counter += 1;
			}

			for (int i = 0; i < adj.get(u).size(); i++) {
				if (visited[adj.get(u).get(i)] == false) {
					visited[adj.get(u).get(i)] = true;
					dist[adj.get(u).get(i)] = dist[u] + 1;
					pred[adj.get(u).get(i)] = u;
					queue.add(adj.get(u).get(i));

					// stopping condition (when we find our destination)
					if (dest.contains(adj.get(u).get(i))) {
						// LinketList path to find path from source to destination
						LinkedList<Integer> pathReversed = new LinkedList<Integer>();
						int crawl = adj.get(u).get(i);
						pathReversed.add(crawl);
						while (pred[crawl] != -1) {
							pathReversed.add(pred[crawl]);
							crawl = pred[crawl];
						}
						ArrayList<Integer> path = new ArrayList<Integer>();
						for (int x = pathReversed.size() - 1; x >= 0; x--) {
							path.add(pathReversed.get(x));
						}
						// store hospital node, dist and path of hospital from source in respective
						// arrays
						hosAr.add(adj.get(u).get(i));
						distanceAr.add(dist[adj.get(u).get(i)]);
						pathAr.add(path);
						counter += 1;
					}
				}
			}
		}
		// return true if at least 1 path is found
		if (counter > 0)
			return true;
		else
			return false;
	}

	public static void writeOutputToFile() {
		File OutputText = new File("Output.txt");
		try (PrintWriter out = new PrintWriter(OutputText);) {
			out.println("# Source	NodeID		Distance	Path");
			for (String output : OutputAr) {
				out.println(output);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}