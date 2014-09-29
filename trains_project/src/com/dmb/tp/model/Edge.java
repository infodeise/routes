package com.dmb.tp.model;

public class Edge {
	private Node nodeInit;
	private Node nodeEnd;
	private int weight;

	public Edge(Node nodeInit, Node nodeEnd, int weight) {
		this.nodeInit = nodeInit;
		this.nodeEnd = nodeEnd;
		this.weight = weight;
	}

	public Node getNodeInit() {
		return nodeInit;
	}

	public void setNodeInit(Node nodeInit) {
		this.nodeInit = nodeInit;
	}

	public Node getNodeEnd() {
		return nodeEnd;
	}

	public void setNodeEnd(Node nodeEnd) {
		this.nodeEnd = nodeEnd;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String toString() {
		return nodeInit + " ==> " + nodeEnd;
	}

}
