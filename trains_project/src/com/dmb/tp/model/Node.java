package com.dmb.tp.model;

public class Node {
	private String label;
	protected boolean visited;
	public double distance = Double.POSITIVE_INFINITY;

	public Node(String label) {
		this.label = label;
	}
	public Node() {}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isVisited() {
		return visited;
	}

	public void visit() {
		visited = true;
	}

	public void unvisit() {
		visited = false;
	}
	
	public int compareTo(Node ob) {
        String tempA = this.toString();
        String tempB = ob.toString();
         
        return tempA.compareTo(tempB);
    }
	
	public String toString() {
        return label.toString();
    }
}
