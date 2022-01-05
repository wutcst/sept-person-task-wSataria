/**
 * 系统中所有房间的基类，主要房间名称和房间出口
 * 可以通过继承该类来构建不同的房间对象。
 */
package cn.edu.whut.sept.zuul;

import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;

public class Room
{
	// 房间名称
    private String description;
    // 出口
    private HashMap<String, Room> exits;

    // 物品
    private LinkedList<Goods> goods;
    
    /**
     * 初始化房间名称和出口.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        goods = new LinkedList<Goods>();
    }

    /**
     * 为出口赋值，方向和下一个房间.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * 设置物品
     */
    public void setGoods(Goods e)
    {
    	goods.add(e);
    }
    
    /**
     * 
     * @return 返回物品
     */
    public LinkedList<Goods> getGoods()
    {
    	return goods;
    }
    
    /**
     * @return 返回房间名.
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * @return 返回长的出口信息.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * 找到当前房间出口对应的所有房间的名称.
     * @return 返回后面房间名.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet(); // get key
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * @param direction 到下一个房间的方向
     * @return 返回下一个房间名
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * 删除物品
     */
    public boolean deleteGoods(String description)
    {
    	for(Goods g : goods)
        {
        	if(g.getDescription().equals(description))
        	{
        		goods.remove();
        		return true;
        	}
        }
    	return false;
    }
    
}


