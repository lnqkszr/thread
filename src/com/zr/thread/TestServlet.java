package com.zr.thread;

/**   
 * @Title: TestServlet.java 
 * @Package com.zr.thread 
 * @Description:  实现非线程安全的情况，模拟一个servlet
 * @author zhurui
 * @date 2017年2月23日 上午10:39:26 
 * @version V1.0   
 */
public class TestServlet {
	private static String userName;
	private static String passWord;
	
	/**
	 * @param a
	 * @param b
	 * @Description:非线程安全
	 */
	public static void doPost(String a, String b){
		try {
			userName = a;
			if ("aa".equals(userName)) {
				Thread.sleep(500);//让出cpu给线程B执行，B改变了username的值
			}
			passWord = b;
			System.out.println("[name="+userName+" password="+passWord+"]");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param a
	 * @param b
	 * @Description:线程安全
	 */
	public synchronized static void doPost2(String a, String b){
		try {
			userName = a;
			if ("aa".equals(userName)) {
				Thread.sleep(500);//让出cpu给线程B执行，B改变了username的值
			}
			passWord = b;
			System.out.println("[name="+userName+" password="+passWord+"]");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Servlet servlet = new Servlet();
		servlet.start();
		Servlet2 servlet2 = new Servlet2();
		servlet2.start();
		/**
		 * 输出结果：
		 * [name=bb password=bbbb]
		 * [name=bb password=aaaa]
		 * 
		 */
	}
}

class Servlet extends Thread{
	@Override
	public void run() {
		TestServlet.doPost("aa", "aaaa");
	}
}

class Servlet2 extends Thread{
	@Override
	public void run() {
		TestServlet.doPost("bb", "bbbb");
	}
}
