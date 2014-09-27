package com.dmb.tp.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.dmb.tp.model.Edge;
import com.dmb.tp.model.Graph;
import com.dmb.tp.model.Node;
import com.dmb.tp.util.FileUtils;

public class Main {

	public static void main(String[] args) {
		try {
			Graph graph = FileUtils.readFileToGraph(System
					.getProperty("user.dir")
					+ File.separator
					+ "data"
					+ File.separator + "graphData.txt");
			printGraph(graph);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printGraph(Graph graph) {
		ArrayList<Edge> edges = graph.getEdges();
		ArrayList<Node> nodes = graph.getNodes();
		for (int i = 0; i < edges.size(); i++) {
			System.out.println("Node init:"
					+ edges.get(i).getNodeInit().getLabel());
			System.out.println("Node end:"
					+ edges.get(i).getNodeEnd().getLabel());
			System.out.println("Distance between nodes:"
					+ edges.get(i).getWeight() + "\n");
		}

		for (int i = 0; i < nodes.size(); i++) {
			System.out.println("Node:" + nodes.get(i).getLabel());
		}
	}

}
