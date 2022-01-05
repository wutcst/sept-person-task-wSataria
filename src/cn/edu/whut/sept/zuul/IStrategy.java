/**
 * 抽象策略类
 */
package cn.edu.whut.sept.zuul;

public abstract class IStrategy {
    private Command command = null;
    private Game game = null;

    public Command getCommand(){
        return this.command;
    }

    public Game getGame(){
        return this.game;
    }

    public IStrategy(Command command,Game game) {
        this.command = command;
        this.game = game;
    }

    /**
     * 应对指令的算法
     * @return
     */
    public abstract Object copeWithCommand();
}

