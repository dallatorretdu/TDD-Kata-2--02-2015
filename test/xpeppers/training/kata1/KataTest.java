package xpeppers.training.kata1;

import static org.junit.Assert.*;

import org.junit.Test;


public class KataTest {
	private boolean check(int n, String s) {
		assertEquals( n , new Kata().Add(s));
		return true;
	};
	
	@Test
	public void empty_string() {
		check(0,"");
	}
	@Test
	public void single_number() {
		check(5,"5");
	}
	@Test
	public void single_number_big() {
		check(25,"25");
	}
	@Test
	public void double_number() {
		check(5,"2,3");
	}
	@Test
	public void double_number_big() {
		check(44,"22,22");
	}
	@Test
	public void four_number() {
		check(10,"1,2,3,4");
	}
	@Test
	public void eight_number() {
		check(46,"11,2,3,4,5,6,7,8");
	}
	@Test
	public void newline() {
		check(10,"1\n2\n3\n4");
	}
	@Test
	public void hybrid_check_1() {
		check(6,"1\n2,3");
	}
	@Test
	public void delimiter_check() {
		assertEquals( 3 , new Kata().next_delimiter_position("123,123", ","));
	}
	@Test
	public void new_delimiter() {
		check(3,"//;\n1;2");
	}
	@Test(expected=IllegalArgumentException.class)
	public void negativetest() {
		new Kata().Add("4,5,-1,7");
	}
	
	@Test
	public void findNegatives() {
		assertEquals("-3 -2", new Kata().List_Negatives("1,2,-3,2,-2", ","));
	}
	@Test
	public void noNumbersBiggerThanThousand() {
		check(8,"4,2000,4");
	}
	@Test
	public void findBigDelimiter() {
		assertEquals("***", new Kata().Find_Big_Delimiter("***]\n"));
	}
	@Test
	public void bigDelimiter() {
		check(6,"//[***]\n1***2***3");
	}
}
