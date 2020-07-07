package com.briup.environment.client;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationAware;
import com.briup.environment.util.ConfigurationImp3;
@SuppressWarnings("all")
public class ClientImp2 implements Client,ConfigurationAware{
	private String host;
	private int port;
	{
		HashMap nature = ConfigurationImp3.getNature();
		host=(String)nature.get("host");
		port=Integer.parseInt((String)nature.get("port"));
	}
	@Override
	public void send(Collection<Environment> coll) throws Exception {
		Socket socket = new Socket(host,port);
		System.out.println("成功建立一个客户端");
		System.out.println("向服务器发送数据");
		OutputStream os = socket.getOutputStream();	
		ObjectOutputStream out = new ObjectOutputStream(os);
		System.out.println("正在发送数据至服务器端");
		out.writeObject(coll);
		out.flush();
		System.out.println("发送成功");
		String[] split = this.toString().split("[.]");
		String[] split2 = split[split.length-1].split("@");
		ConfigurationImp3.getCon().getLogger().info(split2[0]+".send [INFO] 正常发送至服务器端");
	}
	@Override
	public void setConfiguration(Configuration config) {
		// TODO Auto-generated method stub
		
	}

}
