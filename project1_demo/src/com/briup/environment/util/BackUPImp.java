package com.briup.environment.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BackUPImp implements BackUP{
	String backUpPath;
	@Override
	public Object load(String key, boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(String key, Object data, boolean flag) {
		// TODO Auto-generated method stub
		if(key==null || "".equals(key.trim())) {
			File file = new File(backUpPath);
		}
		try {
			File file = new File(key);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file,flag));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
