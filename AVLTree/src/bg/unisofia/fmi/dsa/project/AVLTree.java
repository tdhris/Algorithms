package bg.unisofia.fmi.dsa.project;

import bg.unisofia.fmi.dsa.project.rotations.IRotation;
import bg.unisofia.fmi.dsa.project.rotations.RotationDeterminator;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	@Override
	public TreeNode<T> insert(T key) {
		TreeNode<T> inserted = super.insert(key);
		balanceTree(inserted);
		return inserted;
	}

	@Override
	public TreeNode<T> delete(T key) {
		TreeNode<T> deleted = super.delete(key);
		balanceTree(deleted);
		return deleted;
	}
	
	private void balanceTree(TreeNode<T> inserted) {
		TreeNode<T> unbalancedNode = findUnbalancedNode(inserted.getParent());
		if (unbalancedNode != null) {
			RotationDeterminator<T> determinator = new RotationDeterminator<T>();
			IRotation<T> rotation = determinator.determineRotation(unbalancedNode);
			rotation.rotate(unbalancedNode);
			if (root.equals(unbalancedNode)) {
				root = findRoot(unbalancedNode);
			}
		}
	}

	private TreeNode<T> findUnbalancedNode(TreeNode<T> node) {
		while (node != null) {
			if (node.isUnbalancedLeft() || node.isUnbalancedRight()) {
				return node;
			} else if (node.getBalance() == Balance.BALANCED) {
				return null;
			}
			node = node.getParent();
		}
		return null;
	}
	
	private TreeNode<T> findRoot(TreeNode<T> unbalancedNode) {
		while (unbalancedNode.getParent() != null) {
			unbalancedNode = unbalancedNode.getParent();
		}
		return unbalancedNode;
	}

}
