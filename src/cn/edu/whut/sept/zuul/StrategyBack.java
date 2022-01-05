/**
 * back策略类
 */
package cn.edu.whut.sept.zuul;

public class StrategyBack extends IStrategy{

	private Game game = getGame();
	
	public StrategyBack(Command command, Game game) {
		super(command, game);
	}
	
	/**
     * 执行back命令，回到上一个房间
     */
    @Override
    public Object copeWithCommand() {
        String second = game.getParser().getSecond(); 
        if(second == null) 
        {
        	System.out.printf("You're in the first room."); 
        	return null;
        }
        Command command = new Command("go", null);
        System.out.println("You are back.");
        switch (second)
        {
	        case "east": // 若上一步向东走，则返回西边
	        {
	        	command.setSecondWord("west");
	        	new Context(new StrategyGo(command, game)).getResult();
	        	break;
	        }
	        case "west": // 若上一步向西走，则返回东边
	        {
	        	command.setSecondWord("west");
	        	new Context(new StrategyGo(command, game)).getResult();
	        	break;
	        }
	        case "north": // 若上一步向北走，则返回南边
	        {
	        	command.setSecondWord("south");
	        	new Context(new StrategyGo(command, game)).getResult();
	        	break;
	        }
	        case "south": // 若上一步向南走，则返回北边
	        {
	        	command.setSecondWord("north");
	        	new Context(new StrategyGo(command, game)).getResult();
	        	break;
	        }
	
        }
        return null;
    }
}
