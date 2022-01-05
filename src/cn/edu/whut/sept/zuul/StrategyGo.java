/**
 * go策略类
 */
package cn.edu.whut.sept.zuul;

public class StrategyGo extends IStrategy{

    private Command command = getCommand();
    private Game game = getGame();

    public StrategyGo(Command command, Game game) {
        super(command,game);
    }

    /**
     * 执行go指令，向房间的指定方向出口移动，如果该出口连接了另一个房间，则会进入该房间，
     * 否则打印输出错误提示信息.
     */
    @Override
    public Object copeWithCommand() {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return "Unknow where to Go....";
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = game.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            game.setCurrentRoom(nextRoom);
            System.out.println(game.getCurrentRoom().getLongDescription());
        }
        return "Success moving !";
    }
}
