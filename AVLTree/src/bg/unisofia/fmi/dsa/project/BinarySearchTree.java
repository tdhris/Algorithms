package bg.unisofia.fmi.dsa.project;


public class BinarySearchTree<T extends Comparable<T>> implements
		IBinarySearchTree<T> {

	protected TreeNode<T> root;

	@Override
	public boolean contains(T target) {
		return find(target) != null;
	}

	@Override
	public TreeNode<T> find(T target) {
		TreeNode<T> currentNode = root;
		while (currentNode != null) {
			int compare = currentNode.getKey().compareTo(target);
			if (compare == 0) {
				return currentNode;
			} else if (compare > 0) {
				currentNode = currentNode.getLeftChild();
			} else {
				currentNode = currentNode.getRightChild();
			}
		}
		return null;
	}

	@Override
	public TreeNode<T> insert(T key) {
		TreeNode<T> nodeToInsert = new TreeNode<T>(key);

		if (isEmpty()) {
			root = nodeToInsert;
			return root;
		}

		TreeNode<T> currentNode = root;
		TreeNode<T> parent = root;
		while (currentNode != null) {
			int compare = currentNode.getKey().compareTo(key);
			parent = currentNode;
			if (compare == 0) {
				return currentNode;
			} else if (compare > 0) {
				currentNode = currentNode.getLeftChild();
			} else {
				currentNode = currentNode.getRightChild();
			}
		}

		parent.setChild(nodeToInsert);
		nodeToInsert.setParent(parent);
		return nodeToInsert;
	}

	@Override
	public TreeNode<T> delete(T key) {

		TreeNode<T> nodeToDelete = find(key);
		if (nodeToDelete != null) {
			if (nodeToDelete.getChildCount() == ChildCount.NO_CHILDREN) {
				deleteNoChildrenNode(nodeToDelete);
			} else if (nodeToDelete.getChildCount() == ChildCount.ONE_CHILD) {
				deleteOneChildNode(nodeToDelete);
			} else {
				deleteTwoChildrenNode(nodeToDelete);
			}
			return nodeToDelete.getParent();
		}
		return nodeToDelete;
	}

	@Override
	public TreeNode<T> findMin(TreeNode<T> node) {
		TreeNode<T> min = node;
		while (node.getLeftChild() != null) {
			node = node.getLeftChild();
			if (min.getKey().compareTo(node.getKey()) > 0) {
				min = node;
			}
		}
		return min;
	}

	@Override
	public TreeNode<T> findMax(TreeNode<T> node) {
		TreeNode<T> max = node;
		while (node.getRightChild() != null) {
			node = node.getRightChild();
			if (max.getKey().compareTo(node.getKey()) < 0) {
				max = node;
			}
		}
		return max;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public long size() {
		return size(root);
	}
	
	@Override
	public long getHeight() {
		return root != null ? root.getHeight() : 0;
	}
		
	private int size(TreeNode<T> node) {
		if (node == null)
			return (0);
		else {
			return (size(node.getLeftChild()) + 1 + size(node.getRightChild()));
		}
	}

	private void deleteNoChildrenNode(TreeNode<T> nodeToDelete) {
		if (root.equals(nodeToDelete)) {
			root = null;
			return;
		}
		TreeNode<T> parent = nodeToDelete.getParent();
		parent.removeChild(nodeToDelete);
	}

	private void deleteOneChildNode(TreeNode<T> nodeToDelete) {
		TreeNode<T> child = nodeToDelete.getLeftChild() != null ? nodeToDelete
				.getLeftChild() : nodeToDelete.getRightChild();
		if (root.equals(nodeToDelete)) {
			root = child;
			return;
		}
		TreeNode<T> parent = nodeToDelete.getParent();
		parent.setChild(child);
	}

	private void deleteTwoChildrenNode(TreeNode<T> nodeToDelete) {
		TreeNode<T> successor = findMin(nodeToDelete.getRightChild());
		replace(nodeToDelete, successor);
	}

	private void replace(TreeNode<T> node, TreeNode<T> replacement) {
		TreeNode<T> parentReplacement = replacement.getParent();
		TreeNode<T> rightChildReplacement = replacement.getRightChild();
		parentReplacement.setLeftChild(rightChildReplacement);

		TreeNode<T> parentNode = node.getParent();
		TreeNode<T> leftChildNode = node.getLeftChild();
		TreeNode<T> rightChildNode = node.getRightChild();
		replacement.setLeftChild(leftChildNode);
		replacement.setRightChild(rightChildNode);
		parentNode.setRightChild(replacement);
	}
}
