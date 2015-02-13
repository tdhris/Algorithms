package bg.unisofia.fmi.dsa.project.rotations;

import bg.unisofia.fmi.dsa.project.TreeNode;

public class RightLeftRotation<T extends Comparable<T>> implements IRotation<T>  {

	@Override
	public void rotate(TreeNode<T> node) {
		RightRotation<T> right = new RightRotation<T>();
		LeftRotation<T> left = new LeftRotation<T>();

		right.rotate(node.getRightChild());		
		left.rotate(node);
	}

}
