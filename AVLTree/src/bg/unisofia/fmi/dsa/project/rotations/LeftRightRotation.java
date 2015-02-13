package bg.unisofia.fmi.dsa.project.rotations;

import bg.unisofia.fmi.dsa.project.TreeNode;

public class LeftRightRotation<T extends Comparable<T>> implements IRotation<T> {

	@Override
	public void rotate(TreeNode<T> node) {
		LeftRotation<T> left = new LeftRotation<T>();
		RightRotation<T> right = new RightRotation<T>();
		
		left.rotate(node.getLeftChild());
		right.rotate(node);
	}

}
