package com.briup.environment.util;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;

@SuppressWarnings("all")
public class ConfigurationImp3 implements Configuration,ConfigurationAware{
	private static HashMap target = new HashMap();//存放对象
	private static HashMap nature = new HashMap();//存放属性
	static{		
			SAXReader saxReader = new SAXReader();
			File file = new File("data/configuration.xml");
			try {
				Document read = saxReader.read(file);
				Element root = read.getRootElement();
				Iterator<Element> iterator = root.elementIterator();
				while(iterator.hasNext()) {
					Element childEle = iterator.next();
					Iterator<Attribute> childAtt = childEle.attributeIterator();
					Iterator<Element> iterator2 = childEle.elementIterator();
					while(iterator2.hasNext()) {//获取对象的属性值
						Element childEle2 = iterator2.next();
						nature.put(childEle2.getName(), childEle2.getTextTrim());
					}	
					while(childAtt.hasNext()) {//获取类的全限定名并新建对象
						Attribute attribute = childAtt.next();
						Class<?> name = Class.forName(attribute.getValue());
						Object obj = name.newInstance();
						target.put(attribute.getName(),obj);
					}
				}
				}catch (Exception e) {
					e.printStackTrace();
					}
		}
	public static HashMap getNature() {//返回属性集合，供其它实现类进行初始化
		return nature;
	}
	public static ConfigurationImp3 getCon() {
		ConfigurationImp3 con = new ConfigurationImp3();
		return con;
	}
	@Override
	public Log getLogger() throws Exception {
		for(Object value:target.values()){//遍历集合的value值并判断是否是Log的实现类对象
			if(value instanceof Log) {
				//System.out.println(value);
				return (Log)value;
			}
		}
		return null;
	}
	@Override
	public Server getServer() throws Exception {
		for(Object value:target.values()){
			if(value instanceof Server) {
				//System.out.println(value);
				return (Server)value;
			}
		}
		return null;
	}
	@Override
	public Client getClient() throws Exception {
		for(Object value:target.values()){
			if(value instanceof Client) {
				//System.out.println(value);
				return (Client)value;
			}
		}
		return null;
	}
	@Override
	public DBStore getDbStore() throws Exception {
		for(Object value:target.values()){
			if(value instanceof DBStore) {
				//System.out.println(value);
				return (DBStore)value;
			}
		}
		return null;
	}
	@Override
	public Gather getGather() throws Exception {
		for(Object value:target.values()){
			if(value instanceof Gather) {
				//System.out.println(value);
				return (Gather)value;
			}
		}
		return null;
	}
	@Override
	public void setConfiguration(Configuration config) {
		
	}
}
