package com.dmb.tp.util;

import java.util.Comparator;

import com.dmb.tp.model.Node;

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
	
	public double getF(){
		return g + h;
	}
}
