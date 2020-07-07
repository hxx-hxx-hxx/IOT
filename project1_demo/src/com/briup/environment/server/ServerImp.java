package com.briup.environment.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationAware;
import com.briup.environment.util.ConfigurationImp3;
import com.briup.environment.util.Log;
@SuppressWarnings("all")
public class ServerImp implements Server,ConfigurationAware{
	private int port;
	private InputStream is = null;
	private Log logger;
	private ObjectInputStream in =null;
	{
		HashMap nature = ConfigurationImp3.getNature();
		port=Integer.parseInt((String)nature.get("port"));
	}
	@Override
	public void reciver() throws Exception {
		System.out.println("开启服务器");
		ServerSocket server = new ServerSocket(port);
		System.out.println("等待客户端连接");
		Socket socket = server.accept();
		System.out.println("有客户端连接到服务器");
		ArrayList<Environment> list = new ArrayList();
		is=socket.getInputStream();
		in = new ObjectInputStream(is);
		Object obj = in.readObject();
		list=(ArrayList)obj;
		ConfigurationImp3.getCon().getDbStore().saveDb(list);
		String[] split = this.toString().split("[.]");
		String[] split2 = split[split.length-1].split("@");
		ConfigurationImp3.getCon().getLogger().info(split2[0]+".reciver [INFO] 数据从客户端接收成功");
		shutdown();
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		try {
			if(is!=null)is.close();
			if(in!=null)in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setConfiguration(Configuration config) {
		try {
			logger=config.getLogger();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
