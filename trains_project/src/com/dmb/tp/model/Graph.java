package com.dmb.tp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Class that represent Graph model
 * 
 * @author Deise Miranda
 * 
 */
public class Graph {
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	Map<String, Edge> mapEdges = new HashMap<String, Edge>();
	Map<String, Node> mapNodes = new HashMap<String, Node>();

	public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
		getMapEdges();
		getMapNodes();
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	/**
	 * Method that sum distances between towns
	 * @param routes content with towns
	 * @return distance of the route
	 */
	public String calculateDistanceRoutes(ArrayList<String> routes) {
		int distance = 0;
		for (int i = 0; i < routes.size() - 1; i++) {
			String key = routes.get(i) + "," + routes.get(i + 1);
			if(mapEdges.containsKey(key)) {
				distance += mapEdges.get(key).getWeight();
			} else {
				return "NO SUCH ROUTE";
			}
		}
		return String.valueOf(distance);
	}


	/**
	 * Method responsible to calculating the number of trips starting at any town and ending in other town with a maximum of X stops
	 * @param nodeStart
	 * @param nodeFinish
	 * @param maximumStops
	 * @return quantity of the trips
	 */
	public int calculateQtdTripsPerMaximumStops(Node nodeStart,
			Node nodeFinish, int maximumStops) {
		return calculateQtdTrips(nodeStart, nodeFinish, maximumStops, 0, 0);
	}

	private int calculateQtdTrips(Node nodeStart, Node nodeFinish,
			int maximumStops, int countStops, int qtdTrips) {
		ArrayList<Node> neighbors = getNeighbors(nodeStart);
		for (int i = 0; i < neighbors.size(); i++) {
			Node begin = neighbors.get(i);
			countStops++;
			for (int j = 0; j < this.edges.size() && countStops != 0; j++) {
				Edge edgeItem = this.edges.get(j);
				if (edgeItem.getNodeInit().getLabel().equals(begin.getLabel())
						&& edgeItem.getNodeEnd().getLabel()
								.equals(nodeFinish.getLabel())) {
					countStops++;
					if (countStops <= maximumStops) {
						qtdTrips++;
						countStops = 0;
					}

				} else if (edgeItem.getNodeInit().getLabel()
						.equals(begin.getLabel())) {
					return calculateQtdTrips(begin, nodeFinish, maximumStops,
							countStops, qtdTrips);
				}
			}
		}

		return qtdTrips;
	}
	
	/**
	 * Method responsible to calculating the number of trips starting at any town and ending in other town with exactly X stops
	 * @param nodeStart
	 * @param nodeFinish
	 * @param exactlyStops
	 * @return
	 */
	public int calculateQtdTripsExactly(Node nodeStart, Node nodeFinish, int exactlyStops) {
		ArrayList<Node> neighbors = getNeighbors(nodeStart);
		int qtdTrips = 0;
		if(exactlyStops < 0) {
			return qtdTrips;
		}
		for (int i = 0; i < neighbors.size(); i++) {
			Node neighbor = neighbors.get(i);
			
			if (nodeFinish.getLabel().equals(neighbor.getLabel())) {
				if(exactlyStops != 0) {
					qtdTrips += calculateQtdTripsExactly(neighbor, nodeFinish, exactlyStops-1);
				} else {
					qtdTrips++;
				}
			} else {
				qtdTrips+= calculateQtdTripsExactly(neighbor, nodeFinish, exactlyStops-1);
			}
		}
		return qtdTrips;
	}

	/**
	 * Method responsible to return the neighbors nodes.
	 * @param node
	 * @return ArrayList<Node> with neighbors nodes
	 */
	public ArrayList<Node> getNeighbors(Node node) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
		for (Edge edgeItem : this.edges) {
			if (edgeItem.getNodeInit().getLabel().equals(node.getLabel())) {
				neighbors.add(edgeItem.getNodeEnd());
			}
		}

		return neighbors;
	}

	/**
	 * Method responsible to calculating the distance between two nodes
	 * @param a Start node
	 * @param b End node
	 * @return value of the distance between two nodes
	 */
	public double calcDistance(Node a, Node b) {
		int distance = 0;

		ArrayList<Node> neighbors = getNeighbors(a);
		String key = null;
		for (int i = 0; i < neighbors.size(); i++) {
			Node neighbor = neighbors.get(i);
			if (b.getLabel().equals(neighbor.getLabel())) {
				key = a.getLabel() + "," + b.getLabel();
				return distance += mapEdges.get(key).getWeight();
			} else {
				if (!mapNodes.get(neighbor.getLabel()).isVisited()) {
					key = a.getLabel() + "," + neighbor.getLabel();
					distance += mapEdges.get(key).getWeight();
				}

				key = neighbor.getLabel() + "," + b.getLabel();
				if (mapEdges.containsKey(key)) {
					distance += mapEdges.get(key).getWeight();
					return distance;
				} else if (!mapNodes.get(neighbor.getLabel()).isVisited()) {
					mapNodes.get(neighbor.getLabel()).setVisit(true);
					return distance += calcDistance(neighbor, b);
				}
			}
		}
		return 0;
	}

	/**
	 * Method responsible to calculating the length of the shortest route between two nodes
	 * @param init
	 * @param finish
	 * @return value of the shortest distance
	 */
	public double getShortestRoute(Node init, Node finish) {
		Map<String, AStarNode> unvisitedNodes = new HashMap<String, AStarNode>();
		PriorityQueue<AStarNode> pQueue = new PriorityQueue(11, new AStarNodeComparator());
		Map<String, AStarNode> visitedNodes = new HashMap<String, AStarNode>();
		AStarNode start = new AStarNode(init, 0, calcDistance(init, finish));
		unvisitedNodes.put(init.getLabel(), start);
		pQueue.add(start);

		AStarNode routeEnd = null;
		//in cases the initNode = finish
		boolean first = true;
		while (unvisitedNodes.size() > 0) {
			AStarNode current = pQueue.poll();
			unvisitedNodes.remove(current.getNode().getLabel());
			if (init.getLabel().equals(finish.getLabel()) && !first
					&& current.getNode().getLabel().equals(finish.getLabel())) {
				routeEnd = current;
				break;
			} else if (!init.getLabel().equals(finish.getLabel())
					&& current.getNode().getLabel().equals(finish.getLabel())) {
				routeEnd = current;
				break;
			} else {
				if (!init.getLabel().equals(finish.getLabel())) {
					visitedNodes.put(current.getNode().getLabel(), current);
				} else if (!first) {
					visitedNodes.put(current.getNode().getLabel(), current);
				}
				
				ArrayList<Node> neighbors = getNeighbors(current.getNode());
				for (Node neighbor : neighbors) {
					//AStarNode visited = visitedNodes.get(neighbor.getLabel());
					if (!visitedNodes.containsKey(neighbor.getLabel())) {
						double g = current.getG()
								+ calcDistance(current.getNode(), neighbor);
						AStarNode node = unvisitedNodes.get(neighbor.getLabel());
						if (node == null) {
							// not in unvisitedNodes
							node = new AStarNode(neighbor, g, calcDistance(
									neighbor, finish));
							node.setCameFrom(current);
							unvisitedNodes.put(neighbor.getLabel(), node);
							pQueue.add(node);
						} else if (g < node.getG()) {
							node.setCameFrom(current);
							node.setG(g);
							node.setH(calcDistance(neighbor, finish));
						}
					}
				}
			}
			first = false;
		}
		
		// start to construct the path
		List<Node> route = pathRoutes(routeEnd);
		if(route == null) {
			return 0;
		}
		
		return getCost(route);
	}

	private List<Node> pathRoutes(AStarNode nodeFinish) {
		if (nodeFinish != null) {
			Stack<Node> stack = new Stack<Node>();
			List<Node> list = new ArrayList<Node>();
			stack.push(nodeFinish.getNode());
			AStarNode parent = nodeFinish.getCameFrom();
			while (parent != null) {
				stack.push(parent.getNode());
				parent = parent.getCameFrom();
			}
			while (stack.size() > 0) {
				list.add(stack.pop());
			}
			return list;
		}
		return null;
	}

	private double getCost(List<Node> routePath) {
		// get cost of the route
		int distance = 0;
		for (int i = 0; i < routePath.size() - 1; i++) {
			String key = routePath.get(i).getLabel() + ","
					+ routePath.get(i + 1).getLabel();
			distance += mapEdges.get(key).getWeight();
		}

		return distance;
	}

	private Map<String, Edge> getMapEdges() {
		if (mapEdges.size() <= 0) {
			for (Edge item : this.edges) {
				String key = item.getNodeInit().getLabel() + ","
						+ item.getNodeEnd().getLabel();
				mapEdges.put(key, item);
			}
		}

		return mapEdges;
	}

	private Map<String, Node> getMapNodes() {
		if (mapNodes.size() <= 0) {
			for (Node item : this.nodes) {
				String key = item.getLabel();
				mapNodes.put(key, item);
			}
		}

		return mapNodes;
	}
}
