# 实训报告

> 详细实验报告见纸质版

## 代码结构分析
### 基本类
Main：程序的入口，创建并开始游戏。
Gam：游戏的主类，用户进入游戏后项目就执行此类的 play() 方法开始执行游戏。在初始化 Game类对象时执行 createRooms()方法来初始化房间并将它们连成迷宫，同时初始化加载 Parser工具类对象来执行命令解析的操作。
Room：系统中所有房间的基类，包含房间名称（description）、房间出口（exits）。通过继承该类可以构建不同的房间对象。
Comman：接收用户输入的指令，内部包含基础指令(commandWord)、具体指令(secondWord)；
CommandWord：系统内部可用指令，主要是三个指令 Quit、Go、Help。还包含判断和显示可用指令的方法。
Parser：解析终端用户输入命令的工具类。通过Scanner对象获取用户输入，然后对输入的命令进行解析，解析成两个单词，前一个单词是系统内部可用指令之一，后一个单词表示具体的命令。

### 类之间的关系
1. Main类通过new生成一个Game类的对象，调用play方法，开始游戏，二者间存在依赖关系。
2. Game类中有两个成员变量分别为Room类和Parser类，存在组合关系。
3. Game类中的quit方法的参数类型为Command类，存在依赖关系。
4. Parser类中有成员变量的类型为Room类，存在组合关系。
5. Parser类中的getCommand方法的参数类型为Command类，存在依赖关系。

### 类图
![UML类图](https://github.com/wutcst/sept-person-task-wSataria/blob/master/%E7%B1%BB%E5%9B%BE.png)



## 改进功能实现
### 改进
  在Game类的processCommand()方法中，当用户输入的命令被辨认出来以后，有一系列的if语句用来分派程序到不同的地方去执行。从面向对象的设计原则来看，这种解决方案不太好，因为每当要加入一个新的命令时，就得在这一堆if语句中再加入一个if分支，最终会导致这个方法的代码膨胀得极其臃肿。
可以运用策略模式来解决if-else问题：
创建抽象策略类IStrategy含有算法。将三个有效指令（go、help、quit）的策略分离出来，创建三个具体策略类StrategyGo、StrategyHelp、StrategyQuit，其中含有算法的具体实现。上下文Context类调用策略方法，执行业务算法。

1. 上下文类
```
package cn.edu.whut.sept.zuul;

public class Context {
	// 业务上下文关联的算法策略接口
    private IStrategy strategy;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    /**
     * 调用策略方法，实现业务需求
     * @return
     */
    public Object getResult(){
        return strategy.copeWithCommand();
    }
}
```

2. 抽象策略类
```
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
```

3. 具体策略类（以go为例）
具体策略类中具体算法的实现就是processCommand()中的相关方法，由于有些方法调用了command类和parser类中的算法，在具体策略类中不能调用。这些算法可以获取或修改Game类中成员变量parser和currentRoom的值，所以在Game类中新增get、set方法。
```
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
```

4. 替换Game中的if-else部分
```
switch(command.getCommandWord()) {
        	case "help": new Context(new StrategyHelp(command, this)).getResult(); break;
        	case "go": new Context(new StrategyGo(command, this)).getResult(); break;
        	case "quit": wantToQuit = (boolean)new Context(new StrategyQuit(command, this)).getResult(); break;
        }
```

### 扩展功能(具体实现见代码文件)
1. 扩展游戏，使得一个房间里可以存放任意数量的物件，每个物件可以有一个描述和一个重量值，玩家进入一个房间后，可以通过“look”命令查看当前房间的信息以及房间内的所有物品信息；
   * 先增加一个物品类Goods
   * 在Room类中增加物品成员并编写相关方法
   * 创建StrategyLook策略类并写出其中的具体算法
   * 在Game类中创建物品信息并调用策略

2. 在游戏中实现一个“back”命令，玩家输入该命令后会把玩家带回上一个房间
   * 在CommandWords中加入有效命令back
   * 创建StrategyBack策略类并写出其中的具体算法
   * 在Game类中调用策略
> 该算法能实现基本的简单的返回功能。但该方法是由上一步方向推测出返回的方向，当多次返回时，会出现错误。

3. 在游戏中增加拾取take命令，可以拾取当前房间的物品。
   * 在CommandWords中加入有效命令take
   * 在Room中添加删除物品的方法
   * 创建StrategyTake策略类并写出其中的具体算法
   * 在Game类中调用策略
