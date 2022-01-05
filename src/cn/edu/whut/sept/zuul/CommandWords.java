/**
 * 该类包含系统内部可用的基础指令。
 */
package cn.edu.whut.sept.zuul;

public class CommandWords
{
	// 有效的指令是go、quit、help、look、back、take
    private static final String[] validCommands = {
            "go", "quit", "help", "look", "back", "take"
    };

    /**
     * 初始化.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * 判断字符串是否为有效指令.
     * @param aString 输入的字符串.
     * @return 是，则返回true，否则返回false.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))  // 判断aString是否为有效指令之一.
                return true;
        }
        return false;
    }

    /**
     * 显示所有有效指令.
     */
    public void showAll()
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
