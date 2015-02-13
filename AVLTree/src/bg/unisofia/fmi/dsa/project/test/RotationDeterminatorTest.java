package bg.unisofia.fmi.dsa.project.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import bg.unisofia.fmi.dsa.project.TreeNode;
import bg.unisofia.fmi.dsa.project.rotations.LeftRightRotation;
import bg.unisofia.fmi.dsa.project.rotations.LeftRotation;
import bg.unisofia.fmi.dsa.project.rotations.NoRotation;
import bg.unisofia.fmi.dsa.project.rotations.RightLeftRotation;
import bg.unisofia.fmi.dsa.project.rotations.RightRotation;
import bg.unisofia.fmi.dsa.project.rotations.RotationDeterminator;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RotationDeterminatorTest {

	@Test
	public void testLeftRotation() {
		TreeNode<Integer> A = new TreeNode<Integer>(4);
		TreeNode<Integer> B = new TreeNode<Integer>(6);
		TreeNode<Integer> C = new TreeNode<Integer>(8);
		
		A.setChild(B);
		B.setChild(C);
		
		 assertThat(new RotationDeterminator().determineRotation(A), instanceOf(LeftRotation.class));	
	}
	
	@Test
	public void testRightRotation() {
		TreeNode<Integer> A = new TreeNode<Integer>(8);
		TreeNode<Integer> B = new TreeNode<Integer>(6);
		TreeNode<Integer> C = new TreeNode<Integer>(4);
		
		A.setChild(B);
		B.setChild(C);
		
		 assertThat(new RotationDeterminator().determineRotation(A), instanceOf(RightRotation.class));		
	}
	
	@Test
	public void testLeftRightRotation() {
		TreeNode<Integer> A = new TreeNode<Integer>(8);
		TreeNode<Integer> B = new TreeNode<Integer>(6);
		TreeNode<Integer> C = new TreeNode<Integer>(7);
		
		A.setChild(B);
		B.setChild(C);
		
		 assertThat(new RotationDeterminator().determineRotation(A), instanceOf(LeftRightRotation.class));		
	}
	
	@Test
	public void testRightLeftRotation() {
		TreeNode<Integer> A = new TreeNode<Integer>(4);
		TreeNode<Integer> B = new TreeNode<Integer>(6);
		TreeNode<Integer> C = new TreeNode<Integer>(5);
		
		A.setChild(B);
		B.setChild(C);
		
		 assertThat(new RotationDeterminator().determineRotation(A), instanceOf(RightLeftRotation.class));		
	}
	
	@Test
	public void tesNoRotation() {
		TreeNode<Integer> A = new TreeNode<Integer>(4);
		TreeNode<Integer> B = new TreeNode<Integer>(6);
		
		A.setChild(B);
		
		 assertThat(new RotationDeterminator().determineRotation(A), instanceOf(NoRotation.class));		
	}
	
}
