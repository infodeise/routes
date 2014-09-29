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
			System.out.println("Graph:\n");
			printGraph(graph);
			
			//1. The distance of the route A-B-C.
			ArrayList<String> routes = new ArrayList<String>();
			routes.add("A");routes.add("B");routes.add("C");
			System.out.println("1. The distance of the route A-B-C.");
			System.out.println("Output #1: "+graph.calculateDistanceRoutes(routes)+"\n");
			
			//2. The distance of the route A-D
			routes.clear();
			routes.add("A");routes.add("D");
			System.out.println("2. The distance of the route A-D");
			System.out.println("Output #2: "+graph.calculateDistanceRoutes(routes)+"\n");
			
			//3. The distance of the route A-D-C.
			routes.clear();
			routes.add("A");routes.add("D");routes.add("C");
			System.out.println("3. The distance of the route A-D-C.");
			System.out.println("Output #3: "+graph.calculateDistanceRoutes(routes)+"\n");
			
			//4. The distance of the route A-E-B-C-D.
			routes.clear();
			routes.add("A");routes.add("E");routes.add("B");routes.add("C");routes.add("D");
			System.out.println("4. The distance of the route A-E-B-C-D.");
			System.out.println("Output #4: "+graph.calculateDistanceRoutes(routes)+"\n");
			
			//5. The distance of the route A-E-D.
			routes.clear();
			routes.add("A");routes.add("E");routes.add("D");
			System.out.println("5. The distance of the route A-E-D.");
			System.out.println("Output #5: "+graph.calculateDistanceRoutes(routes)+"\n");
			
			Node nodeFinish = new Node("C");
			Node nodeStart = new Node("C");
			//6. The number of trips starting at C and ending at C with a maximum of 3 stops.
			int qtd = graph.calculateQtdTripsPerMaximumStops(nodeFinish, nodeFinish, 3);
			System.out.println("6. The number of trips starting at C and ending at C with a maximum of 3 stops.");
			System.out.println("Output #6: "+qtd+"\n");
			
			
			
			//7. The number of trips starting at A and ending at C with exactly 4 stops.
			nodeStart.setLabel("A");
			nodeFinish.setLabel("C");
			qtd = graph.calculateQtdTripsExactly(nodeStart, nodeFinish, 4);
			System.out.println("7. The number of trips starting at A and ending at C with exactly 4 stops.");
			System.out.println("Output #7: "+qtd+"\n");
			
			//8. The length of the shortest route (in terms of distance to travel) from A to C.
			nodeStart.setLabel("A");
			nodeFinish.setLabel("C");
			System.out.println("8. The length of the shortest route (in terms of distance to travel) from A to C.");
			System.out.println("Output #8: "+(int)graph.getShortestRoute(nodeStart, nodeFinish)+"\n");
			
			//9. The length of the shortest route (in terms of distance to travel) from B to B
			nodeStart.setLabel("B");
			nodeFinish.setLabel("B");
			System.out.println("9. The length of the shortest route (in terms of distance to travel) from B to B");
			System.out.println("Output #9: "+(int)graph.getShortestRoute(nodeStart, nodeFinish)+"\n");
			
		} catch (FileNotFoundException e) {
			System.out.println("Error while try read file. File not exists");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while try read file. File not exists");
			e.printStackTrace();
		}
	}

	public static void printGraph(Graph graph) {
		ArrayList<Edge> edges = graph.getEdges();
		for (int i = 0; i < edges.size(); i++) {
			System.out.println("Node init:"
					+ edges.get(i).getNodeInit().getLabel());
			System.out.println("Node end:"
					+ edges.get(i).getNodeEnd().getLabel());
			System.out.println("Distance between nodes:"
					+ edges.get(i).getWeight() + "\n");
		}
	}

}
