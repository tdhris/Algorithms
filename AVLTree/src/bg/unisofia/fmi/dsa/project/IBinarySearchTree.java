package bg.unisofia.fmi.dsa.project;

public interface IBinarySearchTree<T extends Comparable<T>> {

	public boolean contains(T target);

	public TreeNode<T> find(T target);
	
	public TreeNode<T> findMin(TreeNode<T> start);
	
	public TreeNode<T> findMax(TreeNode<T> start);

	public TreeNode<T> insert(T key);

	public TreeNode<T> delete(T key);

	public boolean isEmpty();

	public long size();
	
	public long getHeight();

}
