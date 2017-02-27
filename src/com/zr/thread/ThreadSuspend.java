package com.zr.thread;

/**   
 * @Title: ThreadSuspend.java 
 * @Package com.zr.thread 
 * @Description: 暂停线程（还可以恢复） suspend() 暂停  resume() 继续
 * @author zhurui
 * @date 2017年2月27日 下午2:04:23 
 * @version V1.0   
 */
public class ThreadSuspend {
	public static void main(String[] args) {
//		test();
//		test2();
		test3();
	}
	
	/**
	 * @Description:停止线程、唤醒线程
	 */
	public static void test(){
		try {
			ThreadA a = new ThreadA();
			a.start();
			Thread.sleep(1000);//让出CPU
			a.suspend();
			System.out.println("time = "+System.currentTimeMillis() + " count=" + a.getCount());
			Thread.sleep(1000);//让出CPU验证现在A是否还在执行
			System.out.println("time = "+System.currentTimeMillis() + " count=" + a.getCount());
			System.out.println("======================================");
			
			a.resume();//唤醒线程
			ThreadA.sleep(1000);
			a.suspend();
			System.out.println("time = "+System.currentTimeMillis() + " count=" + a.getCount());
			System.out.println("time = "+System.currentTimeMillis() + " count=" + a.getCount());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description:suspend()、resume()缺陷 。使用不当造成公共资源独占
	 */
	public static void test2(){
		
		try {
			final SynchronizedObjec object = new SynchronizedObjec();
			Thread a = new Thread(){
				@Override
				public void run() {
					object.printlnString();
				}
			};
			a.setName("a");
			a.start();
			Thread.sleep(1000);
			
			Thread b = new Thread(){
				@Override
				public void run() {
					System.out.println("线程B启动了,但是进不去printlnString方法");
					System.out.println("因为printlnString()被线程A锁定,并且永远的suspend了");
					object.printlnString();
				}
			};
			b.start();
		} catch (InterruptedException e) {
		}
	}
	
	/**
	 * @Description:suspend()、resume()缺陷 。使用不当造成数据不同步
	 */
	public static void test3(){
		try {
			final SynchronizedObjec2 object = new SynchronizedObjec2();
			Thread thread = new Thread(){
				@Override
				public void run() {
					object.setValue("b","bbb");//暂停线程
				}
			};
			thread.setName("a");
			thread.start();
			Thread.sleep(1000);
			object.printlnString();
		} catch (InterruptedException e) {
		}
	}
}	

class ThreadA extends Thread{
	private int count = 0;

	public int getCount(){
	 	return count;
	}
	
	@Override
	public void run() {
		while(true){
			count++;
		}
	}
}


class SynchronizedObjec{
	
	public synchronized void printlnString(){
		System.out.println("begin");
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println("停止A线程");
			Thread.currentThread().suspend();//暂停
		}
		System.out.println("end");
	}
}

class SynchronizedObjec2{
	private String username = "1";
	private String password = "11";
	
	public synchronized void setValue(String username, String password){
		this.username = username;
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println("停止A线程");
			Thread.currentThread().suspend();//暂停
		}
		this.password = password;
	}
	
	public void printlnString() {
		System.out.println("username = "+ username + ", password=" + password);
	}
}
