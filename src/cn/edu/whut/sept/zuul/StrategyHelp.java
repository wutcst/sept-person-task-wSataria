/**
 * help策略类
 */
package cn.edu.whut.sept.zuul;

public class StrategyHelp extends IStrategy{
    private Game game = getGame();

    public StrategyHelp(Command command, Game game) {
        super(command,game);
    }

    /**
     * 执行help指令，在终端打印游戏帮助信息.
     * 此处会输出游戏中用户可以输入的命令列表
     */
    @Override
    public Object copeWithCommand() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        game.getParser().showCommands();
        return null;
    }

}

