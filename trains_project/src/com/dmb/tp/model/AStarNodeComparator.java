package com.dmb.tp.model;

import java.util.Comparator;
/**
 * Comparator to AStartNode tha compare using the avaliation function
 * @author Deise Miranda
 *
 */
public class AStarNodeComparator implements Comparator<AStarNode> {

	public int compare(AStarNode first, AStarNode second) {
		if (first.getFunctionAvaliation() < second.getFunctionAvaliation()) {
			return -1;
		} else if (first.getFunctionAvaliation() > second.getFunctionAvaliation()) {
			return 1;
		} else {
			return 0;
		}
	}
}
