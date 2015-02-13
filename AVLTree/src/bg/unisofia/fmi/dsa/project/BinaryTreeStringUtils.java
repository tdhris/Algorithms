package bg.unisofia.fmi.dsa.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeStringUtils {
	public static <T extends Comparable<T>> String getTreeString(
			TreeNode<T> root) {

		StringBuilder tree = new StringBuilder();

		List<List<TreeNode<T>>> levels = getTreeLevels(root);

		for (int i = 0; i <= root.getHeight(); i++) {

			List<TreeNode<T>> level = levels.get(i);
			if (!isEmpty(level)) {
				int currentLevel = (int) root.getHeight() - i;
				int leadingSpaces = (int) Math.pow(2, (currentLevel)) - 1;
				tree.append(getWhitespaces(leadingSpaces));
				for (TreeNode<T> node : level) {
					if (node != null) {
						tree.append(node.getKey().toString());
					} else {
						tree.append(" ");
					}
					tree.append(getWhitespaces((int) Math.pow(2,
							(currentLevel + 1)) - 1));
				}
				appendArrows(tree, level, currentLevel, leadingSpaces, root.getHeight());
				appendNewLine(tree);
			}
		}
		return tree.toString();

	}

	private static <T extends Comparable<T>> void appendArrows(
			StringBuilder tree, List<TreeNode<T>> level, int currentLevel,
			int leadingSpaces, long maxLevel) {
		if (isEmpty(level)) {
			return;
		}
		int arrowsCount = (int) Math.pow(2, (Math.max(currentLevel - 1, 0)));
		for (int i = 0; i < arrowsCount; i++) {
			appendNewLine(tree);
			tree.append(getWhitespaces(leadingSpaces - i));
			for (TreeNode<T> node : level) {
				if (node != null) {
					if (node.getLeftChild() != null) {
						tree.append("/");
					} else {
						tree.append(" ");
					}

					int betweenSpaces = 2 * i + (int) maxLevel - currentLevel;
					tree.append(getWhitespaces(betweenSpaces));
					
					if (node.getRightChild() != null) {
						tree.append("\\");
					} else {
						tree.append(" ");
					}
					
					tree.append(getWhitespaces(arrowsCount + arrowsCount - i - 1));
				} else {
					tree.append(getWhitespaces(arrowsCount + arrowsCount + i
							+ 1));
					continue;
				}
			}
		}
	}

	private static <T extends Comparable<T>> List<List<TreeNode<T>>> getTreeLevels(
			TreeNode<T> root) {
		List<List<TreeNode<T>>> levels = new ArrayList<List<TreeNode<T>>>();

		List<TreeNode<T>> currentLevel = new ArrayList<TreeNode<T>>();
		List<TreeNode<T>> nextLevel = new ArrayList<TreeNode<T>>();

		currentLevel.add(root);

		while (!currentLevel.isEmpty()) {
			levels.add(currentLevel);
			for (TreeNode<T> currentNode : currentLevel) {
				if (currentNode != null) {
					if (currentNode.getLeftChild() != null) {
						nextLevel.add(currentNode.getLeftChild());
					} else {
						nextLevel.add(null);
					}

					if (currentNode.getRightChild() != null) {
						nextLevel.add(currentNode.getRightChild());
					} else {
						nextLevel.add(null);
					}
				}

			}
			currentLevel = nextLevel;
			nextLevel = new LinkedList<TreeNode<T>>();

		}
		return levels;
	}

	private static String getWhitespaces(int count) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 0; i < count; i++)
			spaces.append(" ");
		return spaces.toString();
	}

	private static <T extends Comparable<T>> boolean isEmpty(
			List<TreeNode<T>> level) {
		for (TreeNode<T> node : level) {
			if (node != null) {
				return false;
			}
		}
		return true;
	}

	private static void appendNewLine(StringBuilder tree) {
		tree.append(System.getProperty("line.separator"));
	}
}
