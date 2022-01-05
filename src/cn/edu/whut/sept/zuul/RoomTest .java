/**
 * 房间测试类
 */
package cn.edu.whut.sept.zuul;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoomTest {

	private Room room1 = new Room("a");
	private Goods goods1 = new Goods("apple", 0.2);
	
	@Test
	public void test1() {
		assertEquals("a", room1.getShortDescription());
		
	}

	@Test
	public void test2() {
		room1.setGoods(goods1);
		assertEquals(true, room1.deleteGoods("apple"));
		assertEquals(false, room1.deleteGoods("banana"));
	}
}
