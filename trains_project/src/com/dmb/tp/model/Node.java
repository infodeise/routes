package com.dmb.tp.model;
/**
 * Model to Node of the Graph
 * @author Deise Miranda
 *
 */
public class Node {
	private String label;
	private boolean visited;

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

	public void setVisit(boolean visit) {
		visited = visit;
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
