package com.briup.environment.util;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * 日志模块的实现类
 * @author lining
 * 类中的方法表示： 不能场景下调用不同的方法。
 * 1.正常信息： info
 * 2.警告信息： warn
 * 3.报错信息：error
 */
@SuppressWarnings("all")
public class LogImp implements Log{
	private static Logger logger = Logger.getLogger(LogImp.class.getName()); 
	private String logAddress=null;
	{
		HashMap nature = ConfigurationImp3.getNature();
		logAddress=(String)nature.get("logAddress");
		PropertyConfigurator.configure(logAddress);
	}
	@Override
	public void debug(String message) {
		logger.debug(message);
	}

	@Override
	public void info(String message) {
		logger.info(message);
	}

	@Override
	public void warn(String message) {
		logger.warn(message);
	}

	@Override
	public void error(String message) {
		logger.error(message);
	}

	@Override
	public void fatal(String message) {
		logger.fatal(message);
	}
	/**
	 * 扩展思考：
	 * 1.项目 ： 团队开发： 一个项目  分工开发。共享代码  代码的版本 
	 *   使用工具 ： svn 版本控制工具  ：TortoiseSVN
	 * 2.项目：   团队开发： 一个项目 多人开发  多个第三方jar包 
	 *          场景 ： dom4j.jar : 1.1升级 1.5 回退 1.3稳定版本。
	 *          问题 ： 管理jar包  多个jar包版本控制问题
	 *   使用工具 ：maven 工具 : 管理jar包。
	 *           使用该工具的项目： maven项目 
	 *  网络模块  入库模块  日志模块 
	 * 3.代码的优化问题：
	 *  	1.配置信息：文件路径 端口号 jdbc 连接 ip地址
	 *                 是否可以放在一个文件中
	 *      2.实现类信息： new 实现类();  硬编码
	 *      优化 ：  1.将实现类的对象 通过反射生成。
	 *             2.将类的类名存放配置文件中。
	 *             3.统一管理创建对象的过程： 在一个类中创建所有对象 /使用到工厂模式
	 *             4.统一管理创建对象中使用到参数。
	 *             5.是否可以将信息配置到xml中。
	 *             xml内容如下：
	 *             <class>
	 *             		<className ip="127.0.0.1">gather</className>
	 *             		lasspath>com.briup.en.GatherImp</classpath>
	 *            		<port>8888</port>
	 *             </class>
	 *             <class></class >
	 *             
	 *           
	 *
	 * 
	 * 
	 */
}
