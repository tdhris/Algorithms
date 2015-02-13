package bg.unisofia.fmi.dsa.project;

public class Main {
	
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		insertValues(tree, new int[]{5, 2, 7, 1, 6, 9, 8, 10, 11});
		
		//printing needs a little imagination :D :D
		System.out.println(tree.toString());
	}
	
	private static void insertValues(IBinarySearchTree<Integer> tree, int... values) {
		for (int value: values) {
			tree.insert(value);
		}
	}
}
