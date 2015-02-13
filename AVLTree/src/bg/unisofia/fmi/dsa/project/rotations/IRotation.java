package bg.unisofia.fmi.dsa.project.rotations;

import bg.unisofia.fmi.dsa.project.TreeNode;

public interface IRotation<T extends Comparable<T>> {
	
	public void rotate(TreeNode<T> node);

}
