/**
 * 物品类
 */
package cn.edu.whut.sept.zuul;

import java.util.HashMap;

public class Goods {

	private String description; // 物品描述
	private double weight; // 物品重量
	
	/**
	 * 初始化
	 */
	public Goods(String description, double weight)
    {
        this.description = description;
        this.weight = weight;
    }
	
  /**
   * @return 返回物品描述
   */
	public String getDescription()
	{
		return description;
	}
	
  /**
   * @return 返回物品重量
   */
	public double getWeight()
	{
		return weight;
	}
	
  /**
   * 设置物品描述
   */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
  /**
   * 设置物品重量
   */
	public void setWeight(float weight)
	{
		this.weight = weight;
	}
}
