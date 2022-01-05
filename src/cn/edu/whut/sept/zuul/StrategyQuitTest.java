/**
 * 退出策略测试类
 */
package cn.edu.whut.sept.zuul;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyQuitTest {
	
	private static Game game = new Game();
	private static Command command1 = new Command("quit", "");
	private static Command command2 = new Command("quit", "west");
	
	@Test
	public void testdoStrategy() {
		StrategyQuit stake1 = new StrategyQuit(command1, game);
		assertEquals(true, stake1.copeWithCommand());
		
		StrategyQuit stake2 = new StrategyQuit(command2, game);
		assertEquals(false, stake2.copeWithCommand());
		
	}
}
