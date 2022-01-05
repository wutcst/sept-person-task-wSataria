/**
 * 拾取策略
 */
package cn.edu.whut.sept.zuul;

public class StrategyTake extends IStrategy{
	private Command command = getCommand();
	private Game game = getGame();

    public StrategyTake(Command command, Game game) {
        super(command,game);
    }

    /**
     * 查看当前房间信息
     * 以及房间内物品信息
     */
    @Override
    public Object copeWithCommand() {
    	String takedes = command.getSecondWord();
    	if(takedes == null) System.out.println("Please input the goods' description.");
    	
        if(game.getCurrentRoom().deleteGoods(takedes) == true)
        {
        		System.out.println("You have taken it.");
        }
        else System.out.println("There is no such goods.");
        
        return null;
    }
}
