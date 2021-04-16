package Project2;


import java.util.LinkedList;
import java.util.ArrayList;

public class Node implements Comparable<Node>{
	private int source;
	private int nodeID;
	private int distance;
	private ArrayList<Integer> path;

	public Node(int src, int nId, int dist, ArrayList<Integer> path) {
		this.source = src;
		this.nodeID = nId;
		this.distance = dist;
		this.path = path;
	}
	
	public void setSource(int source) {
		this.source = source;
	}

	public int getSource() {
		return source;
	}

	public int getNodeID() {
		return nodeID;
	}

	public int getDistance() {
		return distance;
	}
	
	public ArrayList<Integer> getPath() {
		return path;
	}

	public String toString() {
		return ("Distance from Node #" + this.source +" to Node #" + this.nodeID + ": " + this.distance + 
				"\nShortest path to Node #" + this.nodeID + ": " + this.path +"\n");
	}
	
	public int compareTo(Node other) {
		return this.distance - other.distance;
	}

}
