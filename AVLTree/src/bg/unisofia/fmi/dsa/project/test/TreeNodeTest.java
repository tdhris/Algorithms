package bg.unisofia.fmi.dsa.project.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bg.unisofia.fmi.dsa.project.Balance;
import bg.unisofia.fmi.dsa.project.ChildCount;
import bg.unisofia.fmi.dsa.project.TreeNode;

public class TreeNodeTest {
	
	@Test
	public void testGetHeightNoChildrenIsZero() {
		TreeNode<String> nodeWithNoChildren = new TreeNode<String>("key");
		assertEquals(0, nodeWithNoChildren.getHeight());
		assertEquals("key", nodeWithNoChildren.getKey());
		assertEquals(Balance.BALANCED, nodeWithNoChildren.getBalance());
		assertEquals(ChildCount.NO_CHILDREN, nodeWithNoChildren.getChildCount());
	}
	
	@Test
	public void testSetChild() {
		TreeNode<Integer> node = new TreeNode<Integer>(2);
		node.setChild(new TreeNode<Integer>(1));
		assertNull(node.getRightChild());
		node.setChild(new TreeNode<Integer>(3));
		assertEquals(1, (int) node.getLeftChild().getKey());
		assertEquals(3, (int) node.getRightChild().getKey());
		assertEquals(1, node.getHeight());
		assertFalse(node.setChild(new TreeNode<Integer>(2)));
		assertEquals(1, node.getHeight());
	}
	
	@Test
	public void testGetHeightOneChildLeftHeavy() {
		TreeNode<String> nodeWithOneChild = new TreeNode<String>("root", new TreeNode<String>("new"), null);
		assertEquals(1, nodeWithOneChild.getHeight());
		assertEquals(Balance.LEFT_HEAVY, nodeWithOneChild.getBalance());
		assertEquals("root", nodeWithOneChild.getKey());
		assertEquals("new", nodeWithOneChild.getLeftChild().getKey());
		assertEquals(ChildCount.ONE_CHILD, nodeWithOneChild.getChildCount());
	}
	
	@Test
	public void testMultipleNodesBalanced() {
		TreeNode<String> node = new TreeNode<String>("A", new TreeNode<String>("B"), new TreeNode<String>("C"));
		node.getLeftChild().setLeftChild(new TreeNode<String>("D", new TreeNode<String>("E"), new TreeNode<String>("F")));
		node.getLeftChild().setRightChild(new TreeNode<String>("G", new TreeNode<String>("H"), new TreeNode<String>("I")));
		node.getRightChild().setLeftChild(new TreeNode<String>("J", new TreeNode<String>("K"), new TreeNode<String>("L")));
		node.getRightChild().setRightChild(new TreeNode<String>("M", new TreeNode<String>("N"), new TreeNode<String>("O")));
		assertEquals(3, node.getHeight());
		assertEquals(Balance.BALANCED, node.getBalance());
	}
	
	@Test
	public void testGetHeightOneChildRightHeavy() {
		TreeNode<String> nodeWithOneChild = new TreeNode<String>("A", null, new TreeNode<String>("B"));
		assertEquals(1, nodeWithOneChild.getHeight());
		assertEquals(Balance.RIGHT_HEAVY, nodeWithOneChild.getBalance());
	}
	
	@Test
	public void testGetHeightTwoChildrenBalanced() {
		TreeNode<String> node = new TreeNode<String>("A", new TreeNode<String>("B"), new TreeNode<String>("C"));
		assertEquals(1, node.getHeight());
		assertEquals(Balance.BALANCED, node.getBalance());
		assertEquals(ChildCount.TWO_CHILDREN, node.getChildCount());

	}
	
	@Test
	public void testGetHeightLeftUnbalanced() {
		TreeNode<String> node = new TreeNode<String>("A", new TreeNode<String>("B"), null);
		node.getLeftChild().setLeftChild(new TreeNode<String>("C"));
		assertEquals(2, node.getHeight());
		assertEquals(Balance.LEFT_UNBALANCED, node.getBalance());
		assertFalse(node.isUnbalancedRight());
		assertTrue(node.isUnbalancedLeft());
	}
	
	@Test
	public void testGetHeightRightUnbalanced() {
		TreeNode<String> node = new TreeNode<String>("A", null, new TreeNode<String>("B"));
		node.getRightChild().setRightChild(new TreeNode<String>("C"));
		assertEquals(2, node.getHeight());
		assertEquals(Balance.RIGHT_UNBALANCED, node.getBalance());
		assertTrue(node.isUnbalancedRight());
		assertFalse(node.isUnbalancedLeft());
	}
	
	@Test
	public void testGetHeightN() {
		TreeNode<Integer> parent = new TreeNode<Integer>(0);
		setNChildrenLeft(parent, 4);
		assertEquals(4, parent.getHeight());
		assertEquals(Balance.LEFT_UNBALANCED, parent.getBalance());
		assertEquals(0, (int) parent.getKey());
	}
	
	@Test
	public void testIs() {
		TreeNode<String> parent = new TreeNode<String>("P");
		TreeNode<String> left = new TreeNode<String>("L");
		TreeNode<String> right = new TreeNode<String>("R");
		TreeNode<String> randomNinjaNode = new TreeNode<String>("Ninja");
		parent.setLeftChild(left);
		parent.setRightChild(right);
		assertTrue(left.isLeftChild(parent));
		assertTrue(right.isRightChild(parent));
		assertFalse(randomNinjaNode.isLeftChild(parent));
		assertFalse(randomNinjaNode.isRightChild(parent));
	}
	
	@Test
	public void testEquals() {
		TreeNode<String> nodeFirst = new TreeNode<String>("A");
		TreeNode<String> nodeSecond = new TreeNode<String>("A");
		TreeNode<String> nodeThird = new TreeNode<String>("Z");
		assertTrue(nodeFirst.equals(nodeSecond));
		assertTrue(nodeFirst.equals(nodeFirst));
		assertFalse(nodeFirst.equals(nodeThird));
		assertFalse(nodeFirst.equals(null));
	}

	private void setNChildrenLeft(TreeNode<Integer> parent, int N) {
		TreeNode<Integer> currentNode = parent;
		for (int i = 1; i <= N; i++) {
			currentNode.setLeftChild(new TreeNode<Integer>(i));
			currentNode = currentNode.getLeftChild();
		}
	}
}
