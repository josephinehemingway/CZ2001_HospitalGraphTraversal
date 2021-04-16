package Project2;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;

public class HospitalUserInput {
	static int numberOfHospitals;
	static ArrayList<Integer> nodeIdAr = new ArrayList<Integer>();

	public static void main(String[] args) {
		int curnode;

		System.out.println("Enter number of hospitals: ");
		Scanner sc = new Scanner(System.in);
		numberOfHospitals = sc.nextInt();
		System.out.println("Enter node ID of hospitals: ");
		for (int i = 1; i <= numberOfHospitals; i++) {
			curnode = sc.nextInt();
			nodeIdAr.add(curnode);
		}

		System.out.println(nodeIdAr);
		writeNodeToFile();

	}

	public static void writeNodeToFile() {
		File hosText = new File("file2.txt");
		try (PrintWriter out = new PrintWriter(hosText);) {
			out.println("#" + numberOfHospitals);
			for (int nodeID : nodeIdAr) {
				out.println(nodeID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}