package com.dmb.tp.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.dmb.tp.model.Edge;
import com.dmb.tp.model.Graph;
import com.dmb.tp.model.Node;

public class FileUtils {

	public static Graph readFileToGraph(String path) throws FileNotFoundException, IOException {
		BufferedReader bfreader = new BufferedReader(new FileReader(path));
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		Graph graph = null;
		try {
			String line = null;
			while ((line = bfreader.readLine()) != null) {
				String[] s = line.split(",");
				if(s.length > 1) {
					for(int i=0; i < s.length; i++) {
						Node nodeInit = new Node();
						Node nodeEnd = new Node();
						nodeInit.setLabel(s[i].substring(0, 1));
						nodeEnd.setLabel(s[i].substring(1, 2));
						//Add nodes
						if(!nodeExists(nodeInit, nodes)){
							nodes.add(nodeInit);
						}
						if(!nodeExists(nodeEnd, nodes)){
							nodes.add(nodeEnd);
						}
						
						//Add edges
						Edge edge = new Edge(nodeInit, nodeEnd, Integer.valueOf(s[i].substring(2)).intValue());
						edges.add(edge);
					}
				}
			}
			
			graph = new Graph(nodes, edges);
		} catch (NumberFormatException nfe) {
			System.out.println("Error while try convert string to int");
		}
		finally {
			bfreader.close();
		}
		
		return graph;
		
	}
	
	private static boolean nodeExists(Node node, ArrayList<Node> nodesList) {
		for (Node nodeItem : nodesList) {
			if(nodeItem.getLabel().equals(node.getLabel())) {
				return true;
			}
		}
		return false;
	}
}
