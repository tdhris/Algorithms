package bg.unisofia.fmi.dsa.project;

import static java.lang.Math.max;

public class TreeNode<T extends Comparable<T>> {
	
	private static final int INITIAL_HEIGHT = -1;
	
	private T key;
	
	private TreeNode<T> parent;

	private TreeNode<T> rightChild;
	
	private TreeNode<T> leftChild;
	
	public TreeNode(T key) {
		this(key, null, null, null);
	}
	
	public TreeNode(T key, TreeNode<T> parent) {
		this(key, parent, null, null);
	}
	
	public TreeNode(T key, TreeNode<T> leftChild, TreeNode<T> rightChild) {
		this(key, null, leftChild, rightChild);
	}

	public TreeNode(T key, TreeNode<T> parent, TreeNode<T> leftChild,
			TreeNode<T> rightChild) {
		this.parent = parent;
		this.key = key;
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
	}

	public long getHeight() {
		return max(determineHeight(leftChild), determineHeight(rightChild)) + 1;
	}

	public T getKey() {
		return key;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}
	
	public boolean isParent(TreeNode<T> isChild) {
		return is(isChild.getParent());
	}

	public TreeNode<T> getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	
	public boolean isLeftChild(TreeNode<T> isParent) {
		return is(isParent.getLeftChild());
	}
	
	public TreeNode<T> getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean isRightChild(TreeNode<T> isParent) {
		return is(isParent.getRightChild());
	}
	
	public Balance getBalance() {
		return Balance.getBalance(determineHeight(leftChild), determineHeight(rightChild));
	}
	
	public boolean isUnbalancedLeft() {
		return getBalance() == Balance.LEFT_UNBALANCED;
	}
	
	public boolean isUnbalancedRight() {
		return getBalance() == Balance.RIGHT_UNBALANCED;
	}
	
	public ChildCount getChildCount() {
		if (leftChild != null && rightChild != null) {
			return ChildCount.TWO_CHILDREN;
		} else if (leftChild != null || rightChild != null) {
			return ChildCount.ONE_CHILD;
		}
		return ChildCount.NO_CHILDREN;
	}
	
	public boolean setChild(TreeNode<T> child) {
		int compare = key.compareTo(child.getKey());
		if (compare == 0) {
			return false;
		} else if (compare > 0) {
			setLeftChild(child);
		} else {			
			setRightChild(child);
		}
		return true;
	}
	
	public void removeChild(TreeNode<T> child) {
		if (child.equals(leftChild)) {
			leftChild = null;
		} else if (child == rightChild) {
			rightChild = null;
		}
	}
	
	private long determineHeight(TreeNode<T> node) {
		return node != null ? node.getHeight() : INITIAL_HEIGHT;
	}
	
	private boolean is(TreeNode<T> node) {
		if (node.getKey().equals(key)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		TreeNode<T> other = (TreeNode<T>) obj;
		if (this == other) {
			return true;
		} else if (other == null) {
			return false;
		} else if (this.key.equals(other.getKey())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return key.toString();
	}
}
