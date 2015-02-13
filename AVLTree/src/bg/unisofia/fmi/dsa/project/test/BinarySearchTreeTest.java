package bg.unisofia.fmi.dsa.project.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bg.unisofia.fmi.dsa.project.BinarySearchTree;
import bg.unisofia.fmi.dsa.project.TreeNode;

public class BinarySearchTreeTest {

	private BinarySearchTree<Integer> tree;
	
	@Before
	public void setUp() {
		tree = new BinarySearchTree<Integer>();
	}
	
	@Test
	public void testContainsEmpty() {
		assertFalse(tree.contains(1));
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void testSizeEmpty() {
		assertEquals(0, tree.size());
	}
	
	@Test
	public void testSize() {
		insertValues(new int[] {1, 0, 2});
		assertEquals(3, tree.size());
	}
	
	@Test
	public void testContains() {
		insertValues(new int[] {1, 0, 2});
		assertTrue(tree.contains(1));
		assertTrue(tree.contains(0));
		assertTrue(tree.contains(2));
		assertFalse(tree.contains(3));
	}
	
	@Test
	public void testFindMin() {
		insertValues(new int[] {5, 4, 3, 1, 0});
		TreeNode<Integer> node = tree.find(5);
		assertEquals(0, (int) tree.findMin(node).getKey());
	}
	
	@Test
	public void testFindMax() {
		insertValues(new int[] {5, 6, 7, 9, 10});
		TreeNode<Integer> node = tree.find(5);
		assertEquals(10, (int) tree.findMax(node).getKey());
	}
	
	@Test
	public void testDeleteNoChildrenRoot() {
		tree.insert(5);
		tree.delete(5);
		assertTrue(tree.isEmpty());
		assertNull(tree.find(5));
	}
	
	@Test
	public void testDeleteNoChildren() {
		insertValues(new int[] {5, 4});
		tree.delete(4);
		assertFalse(tree.isEmpty());
		assertNull(tree.find(4));
	}
	
	@Test
	public void testDeleteUnexistingEmpty() {
		tree.delete(100);
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void testDeleteUnexisting() {
		insertValues(new int[] {5, 3, 4});
		tree.delete(100);
		assertEquals(3, tree.size());
	}
	
	@Test
	public void testDeleteOneChildRoot() {
		insertValues(new int[] {5, 4});
		tree.delete(5);
		assertEquals(1, tree.size());
		assertNull(tree.find(5));
	}
	
	@Test
	public void testDeleteOneChildLeft() {
		insertValues(new int[] {5, 3, 4});
		tree.delete(4);
		assertEquals(2, tree.size());
		assertNull(tree.find(4));
	}
	
	@Test
	public void testDeleteOneChildRight() {
		insertValues(new int[] {5, 6, 7});
		tree.delete(6);
		assertEquals(2, tree.size());
		assertNull(tree.find(6));
	}
	
	@Test
	public void testDeleteTwoChildrenRoot() {
		insertValues(new int[] {5, 3, 4});
		tree.delete(5);
		assertEquals(2, tree.size());
		assertNull(tree.find(5));
	}
	
	@Test
	public void testDeleteTwoChildren() {		
		insertValues(new int[] {5, 2, 12, -4, 3, 9, 10, 21, 19, 25});
		tree.delete(12);
		assertEquals(9, tree.size());
		assertNull(tree.find(12));
	}

	private void insertValues(int... values) {
		for (int value: values) {
			tree.insert(value);
		}
	}
	
}
