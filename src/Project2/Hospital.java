package Project2;

import java.util.LinkedList;
import java.util.ArrayList;

public class Hospital implements Comparable<Hospital>{
	private int nodeID;
	private int distance;
	private ArrayList<Integer> path;

	public Hospital(int nId, int dist, ArrayList<Integer> path) {
		this.nodeID = nId;
		this.distance = dist;
		this.path = path;
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
		return ("Distance to Hospital Node #" + this.nodeID + ": " + this.distance + 
				"\nShortest path to Hospital Node #" + this.nodeID + ": " + this.path +"\n");
	}
	
	public int compareTo(Hospital hos) {
		return this.distance - hos.distance;
	}

}
