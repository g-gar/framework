package com.ggar.framework.javafx.application.utils;

import java.util.ArrayList;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;

public class JavaFxUtils {
	
	public static ScrollBar findScrollBar(Node node, Orientation orientation) {
		return node.lookupAll(".scroll-bar").stream().filter(n -> n instanceof ScrollBar).map(n -> (ScrollBar) n)
				.filter(sb -> sb.getOrientation() == orientation).findFirst().orElse(null);
	}

	public static int getRowCount(GridPane pane) {
		int numRows = pane.getRowConstraints().size();
		for (int i = 0; i < pane.getChildren().size(); i++) {
			Node child = pane.getChildren().get(i);
			if (child.isManaged()) {
				Integer rowIndex = GridPane.getRowIndex(child);
				if (rowIndex != null) {
					numRows = Math.max(numRows, rowIndex + 1);
				}
			}
		}
		return numRows;
	}

	public static int getColumnCount(GridPane pane) {
		int numColumns = pane.getColumnConstraints().size();
		for (int i = 0; i < pane.getChildren().size(); i++) {
			Node child = pane.getChildren().get(i);
			if (child.isManaged()) {
				Integer columnIndex = GridPane.getColumnIndex(child);
				if (columnIndex != null) {
					numColumns = Math.max(numColumns, columnIndex + 1);
				}
			}
		}
		return numColumns;
	}

	public static class NodeUtils {
		
		public static ArrayList<Node> getAllNodes(Parent root) {
			ArrayList<Node> nodes = new ArrayList<Node>();
			addAllDescendents(root, nodes);
			return nodes;
		}

		private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
			for (Node node : parent.getChildrenUnmodifiable()) {
				nodes.add(node);
				if (node instanceof Parent)
					addAllDescendents((Parent) node, nodes);
			}
		}
		
	}

}
