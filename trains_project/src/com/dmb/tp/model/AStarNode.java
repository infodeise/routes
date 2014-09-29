package com.dmb.tp.model;

/**
 * Model of the Node to A Star
 * @author Deise Miranda
 *
 */
public class AStarNode {

	private Node node;
	private AStarNode cameFrom;
	private double g;
	private double h;
	
	
	
	public AStarNode(Node node, double g, double h) {
		this.node = node;
		this.g = g;
		this.h = h;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public AStarNode getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(AStarNode cameFrom) {
		this.cameFrom = cameFrom;
	}
	public double getG() {
		return g;
	}
	public void setG(double g) {
		this.g = g;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	
	public double getFunctionAvaliation(){
		return g + h;
	}
}
