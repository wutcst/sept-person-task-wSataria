/**
 * 该类用来接收用户输入的命令。
 */
package cn.edu.whut.sept.zuul;

public class Command
{
	// 基础指令
    private String commandWord;
    // 具体操作
    private String secondWord;

    /**
     * 创建初始化指令.
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * @return 返回基础指令.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return 返回具体操作.
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    /**
     * 
     */
    public void setSecondWord(String second)
    {
    	secondWord = second;
    }

    /**
     * 判断基础指令是否为空.
     * @return 若为空，则返回true，否则返回false.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * 判断是否有具体操作.
     * @return 若为不为空，则返回true，否则返回false.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
