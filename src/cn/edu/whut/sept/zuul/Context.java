/**
 * 上下文
 */
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

