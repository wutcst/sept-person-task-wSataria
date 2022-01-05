/**
 * look策略类
 */
package cn.edu.whut.sept.zuul;

public class StrategyLook extends IStrategy{

	 private Game game = getGame();

	    public StrategyLook(Command command, Game game) {
	        super(command,game);
	    }

	    /**
	     * 查看当前房间信息
	     * 以及房间内物品信息
	     */
	    @Override
	    public Object copeWithCommand() {
	        System.out.println("This room's description is ");
	        String s = game.getCurrentRoom().getShortDescription();
	        System.out.println(s);
	        System.out.println("The goods in this room is/are");
	        for(Goods goods : game.getCurrentRoom().getGoods())
	        {
	        	System.out.print(goods.getDescription());
	        	System.out.print(" ,it weights ");
	        	System.out.print(goods.getWeight());
	        	System.out.println("kg");
	        }
	        return null;
	    }
}
