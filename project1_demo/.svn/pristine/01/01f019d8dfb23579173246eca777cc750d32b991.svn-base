package com.briup.environment.test;



import com.briup.environment.util.ConfigurationImp3;

public class Test {
	
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Runnable() {		
			@Override
			public void run() {
				try {
					ConfigurationImp3.getCon().getServer().reciver();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ConfigurationImp3.getCon().getClient().send(ConfigurationImp3.getCon().getGather().gather());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		t2.start();
	}
}
