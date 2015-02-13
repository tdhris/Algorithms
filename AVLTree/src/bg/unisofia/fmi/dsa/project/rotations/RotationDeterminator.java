package bg.unisofia.fmi.dsa.project.rotations;

import bg.unisofia.fmi.dsa.project.Balance;
import bg.unisofia.fmi.dsa.project.TreeNode;

public class RotationDeterminator<T extends Comparable<T>> {

	public IRotation<T> determineRotation(TreeNode<T> unbalancedNode) {
		if (unbalancedNode.isUnbalancedLeft()) {
			if (unbalancedNode.getLeftChild().getBalance() == Balance.LEFT_HEAVY) {
				return new RightRotation<T>();
			} else if (unbalancedNode.getLeftChild().getBalance() == Balance.RIGHT_HEAVY){
				return new LeftRightRotation<T>();
			}
		} else if (unbalancedNode.isUnbalancedRight()){
			if (unbalancedNode.getRightChild().getBalance() == Balance.RIGHT_HEAVY) {
				return new LeftRotation<T>();
			} else if (unbalancedNode.getRightChild().getBalance() == Balance.LEFT_HEAVY) {
				return new RightLeftRotation<T>();
			}
		}
		return new NoRotation<T>();
	}
}
