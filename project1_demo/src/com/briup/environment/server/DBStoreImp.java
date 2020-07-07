package com.briup.environment.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.ConfigurationImp3;
import com.briup.environment.util.ConnectionFactory;
@SuppressWarnings("all")
public class DBStoreImp implements DBStore{
	@Override
	public void saveDb(Collection<Environment> coll) {
		Connection conn = ConnectionFactory.getConnection();
		System.out.println("正在写入数据库");
		//使用批处理＋预编译
		try {
			PreparedStatement pst=null;
			for(int i=1;i<=31;i++) {
				String sql="insert into e_detail_"+i+" values(?,?,?,?,?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				for(Environment o:coll) {
					Timestamp gd = o.getGather_date();
					int date = gd.getDate();
					if(i==date) {
						pst.setString(1,o.getName());
						pst.setString(2,o.getSrcId());
						pst.setString(3,o.getDstId());
						pst.setString(4,o.getDevId());
						pst.setString(5,o.getSersorAddress());
						pst.setInt(6,o.getCount());
						pst.setString(7,o.getCmd());
						pst.setInt(8,o.getStatus());
						pst.setFloat(9,o.getData());
						pst.setDate(10,new java.sql.Date(gd.getTime()));
						pst.addBatch();
					}
				}
				pst.executeBatch();
				pst.close();
			}
			System.out.println("写入数据库成功");
			String[] split = this.toString().split("[.]");
			String[] split2 = split[split.length-1].split("@");
			ConfigurationImp3.getCon().getLogger().info(split2[0]+".saveDb [INFO] 写入数据库完毕");
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	}

