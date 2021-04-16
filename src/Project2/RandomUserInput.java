package Project2;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class RandomUserInput {
	static ArrayList<String> GraphNodes = new ArrayList<String>();

	public static void main(String args[]) {
		System.setProperty("org.graphstream.ui", "swing");

		// No of vertices
		int v, source, dest;
		System.out.println("Enter the number of nodes: ");
		Scanner sc = new Scanner(System.in);
		v = sc.nextInt();

		Graph graph = new SingleGraph("Random");
		Generator gen = new RandomGenerator(2);
		gen.addSink(graph);
		gen.begin(); // adds first node
		for (int i = 0; i < v - 3; i++)
			gen.nextEvents();
		gen.end();
		graph.display();

		
		graph.edges().forEach(e -> {

			System.out.println(e.getId().replace("_", "	"));
			GraphNodes.add(e.getId().replace("_", "	"));

		});

		writeGraphToFile();
	}

	public static void writeGraphToFile() {
		File graphText = new File("file1.txt");
		try (PrintWriter out = new PrintWriter(graphText);) {
			for (String g : GraphNodes) {
				out.println(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
