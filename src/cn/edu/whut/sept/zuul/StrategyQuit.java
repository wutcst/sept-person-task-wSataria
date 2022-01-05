/**
 * quit策略类
 */
package cn.edu.whut.sept.zuul;

public class StrategyQuit extends IStrategy{
    public StrategyQuit(Command command, Game game) {
        super(command,game);
    }

    /**
     * 执行Quit指令，用户退出游戏。如果用户在命令中输入了其他参数，则进一步询问用户是否真的退出.
     * @return 如果游戏需要退出则返回true，否则返回false.
     */
    @Override
    public Object copeWithCommand() {
        Command command = getCommand();
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
