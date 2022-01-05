/**
 * 该类是解析终端用户输入命令的工具类。
 * 通过Scanner对象获取用户输入，输入的命令进行解析，
 * 解析成两个单词，前一个单词是系统内部可用指令之一，后一个单词表示具体的命令。
 */
package cn.edu.whut.sept.zuul;

import java.util.Scanner;

public class Parser
{
    private CommandWords commands;
    private Scanner reader;
    
    private String second;

    /**
     * 创建并初始化用户指令类和扫描器.
     */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * 获取用户从终端输入的命令
     * @return 返回下一条用户命令
     */
    public Command getCommand()
    {
        String inputLine;  // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> ");  // print prompt

        inputLine = reader.nextLine();

        // 获取输入的前两个单词
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();   // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();  // get second word
                second = word2;
                // note: we just ignore the rest of the input line.
            }
        }

        // 判断用户输入的命令是否合法，若合法，创建Command对象.
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }

    /**
     * @return 返回第二个词.
     */
    public String getSecond()
    {
    	return second;
    }
    
    /**
     * 显示有效指令.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
