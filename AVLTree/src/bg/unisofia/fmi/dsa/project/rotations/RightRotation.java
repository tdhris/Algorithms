package bg.unisofia.fmi.dsa.project.rotations;

import bg.unisofia.fmi.dsa.project.TreeNode;

public class RightRotation<T extends Comparable<T>> implements IRotation<T>  {

	@Override
	public void rotate(TreeNode<T> node) {
		TreeNode<T> left = node.getLeftChild();
		TreeNode<T> parent = node.getParent();	
		left.setParent(node.getParent());
		if (parent != null) {
			parent.setChild(left);
		}
		
		TreeNode<T> right = left.getRightChild();
		node.setLeftChild(right);
		
		left.setRightChild(node);
		node.setParent(left);
	}

}
