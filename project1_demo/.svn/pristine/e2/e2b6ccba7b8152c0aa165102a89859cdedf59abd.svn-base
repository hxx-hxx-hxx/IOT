package com.briup.environment.util;


import java.io.IOException;
import java.util.Properties;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;

public class ConfigurationImp implements Configuration{
	
	private static String Log=null;
	private static String Server=null;
	private static String Client=null;
	private static String DBStore=null;
	private static String Gather=null;
	static{
		Properties properties = new Properties();
		try {
			properties.load(ConnectionFactory.class.getResourceAsStream("peizhi.properties"));
			Log = properties.getProperty("Log");
			Server = properties.getProperty("Server");
			Client = properties.getProperty("Client");
			DBStore = properties.getProperty("DBStore");
			Gather = properties.getProperty("Gather");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override//获取日志模块实例
	public Log getLogger() throws Exception {
		// TODO Auto-generated method stub
		Class<?> cs = Class.forName(Log);
		Log logImp = (Log)cs.newInstance();
		return logImp;
	}

	@Override//获取服务器端模块实例
	public Server getServer() throws Exception {
		Class<?> cs = Class.forName(Server);
		Server server = (Server)cs.newInstance();
		return server;
	}

	@Override//获取客户端模块实例
	public Client getClient() throws Exception {
		Class<?> cs = Class.forName(Client);
		Client client = (Client)cs.newInstance();
		return client;
	}

	@Override//获取入库模块实例
	public DBStore getDbStore() throws Exception {
		Class<?> cs = Class.forName(DBStore);
		DBStore dbStore = (DBStore)cs.newInstance();
		return dbStore;
	}

	@Override//获取采集模块实例
	public Gather getGather() throws Exception {
		Class<?> cs = Class.forName(Gather);
		Gather gather = (Gather)cs.newInstance();
		return gather;
	}

}
