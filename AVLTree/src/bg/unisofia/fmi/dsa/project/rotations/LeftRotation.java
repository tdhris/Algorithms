package bg.unisofia.fmi.dsa.project.rotations;

import bg.unisofia.fmi.dsa.project.TreeNode;

public class LeftRotation<T extends Comparable<T>> implements IRotation<T> {

	@Override
	public void rotate(TreeNode<T> node) {
		TreeNode<T> right = node.getRightChild();
		TreeNode<T> parent = node.getParent();
		right.setParent(parent);
		if (parent != null) {
			parent.setChild(right);
		}
		TreeNode<T> left = right.getLeftChild();
		node.setRightChild(left);
		
		right.setLeftChild(node);
		node.setParent(right);
	}

}
