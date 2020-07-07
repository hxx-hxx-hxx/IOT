package com.briup.environment.client;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.ConfigurationImp3;
@SuppressWarnings("all")
public class GatherImp implements Gather{
	private String address;
	private static ArrayList<Environment> list = new ArrayList();
	{
		HashMap nature = ConfigurationImp3.getNature();
		address= (String)nature.get("gatherAddress");
	}
	public void write(String[] sp,String name) {
		Float data=0f;
		Environment en = new Environment();
		if(name.equals("湿度")) {
			String sub = sp[6].substring(4, 8);//获取第二个四个字节
			int parseInt = Integer.parseInt(sub, 16);
			data=(float) (parseInt*0.00190735-6);
		}
		else {
			String sub = sp[6].substring(0, 4);//获取前四个字节
			int parseInt = Integer.parseInt(sub, 16);
			data=(float) (parseInt*0.00268127-46.85);
		}
		en.setName(name);//传感器id
		en.setSrcId(sp[0]);//发送端id
		en.setDstId(sp[1]);//树莓派id
		en.setDevId(sp[2]);//模块id
		en.setSersorAddress(sp[3]);//模块上传感器id
		en.setCount(Integer.parseInt(sp[4]));//传感器个数
		en.setCmd(sp[5]);//接收数据或者发送数据
		en.setData(data);
		en.setStatus(Integer.parseInt(sp[7]));//表示成功或者失败
		Timestamp date = new Timestamp(Long.parseLong(sp[8]));
		en.setGather_date(date);
		list.add(en);
	}
	@Override
	public Collection<Environment> gather() throws Exception {
		
		GatherImp gather = new GatherImp();
		try {
			FileReader fr = new FileReader(address);
			BufferedReader br = new BufferedReader(fr);
			String str=null;
			System.out.println("正在从文件中采集数据");
			while((str=br.readLine())!=null) {
				String strName=null;
				String[] sp = str.split("\\|");
				if(sp[3].equals("16")) {//湿度传感器和温度传感器,一条数据两个对象
					strName="温度";
					String strName2="湿度";
					gather.write(sp, strName);
					gather.write(sp, strName2);
				}
				else if(sp[3].equals("256")) {
					strName="光照强度";
					gather.write(sp, strName);
				}
				else if(sp[3].equals("1280")) {
					strName="二氧化碳浓度";
					gather.write(sp, strName);
				}
			}
			String[] split = this.toString().split("[.]");
			String[] split2 = split[split.length-1].split("@");
			ConfigurationImp3.getCon().getLogger().info(split2[0]+".gather [INFO] 采集完毕");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
