package com.zr.thread;


public class ThreadMethod {
	public static void main(String[] args) {
//		currentThread();
//		isAlive();
//		sleep();
//		sleep2();
//		getId();
		yield();
	}
	
	/**
	 * @Description:获取当前的线程
	 */
	public static void currentThread(){
		System.out.println(Thread.currentThread().getName());
	}
	
	/**
	 * @Description:判断当前线程是否处于活动状态（已经启动尚未终止）
	 */
	public static void isAlive(){
		try {
			MyThread5 myThread5 = new MyThread5();
			System.out.println("begin " + myThread5.getName()+" "+myThread5.isAlive());
			myThread5.start();
			Thread.sleep(3000);//myThread5线程在3秒内执行完毕、end 输出false
			System.out.println("end " + myThread5.getName()+" "+myThread5.isAlive());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description:让正在执行的程序休眠(主线程main)
	 */
	public static void sleep(){
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
	
	/**
	 * @Description:让正在执行的程序休眠
	 */
	public static void sleep2(){
		try {
			MyThread5 myThread5 = new MyThread5();
			myThread5.start();
			System.out.println(Thread.currentThread().getName());
		} catch (Exception e) {
		}
	}
	
	/**
	 * @Description:获取线程的唯一标识
	 */
	public static void getId(){
		try {
			MyThread5 myThread5 = new MyThread5();
			System.out.println(myThread5.getId());
		} catch (Exception e) {
		}
	}
	
	/**
	 * @Description:放弃当前CPU资源 让给其他任务去执行。但是放弃的时间补确定，也许刚刚放弃马上又获得cpu资源
	 */
	public static void yield(){
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Thread.yield();
			System.out.println(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("消耗时间:" + (end - begin));
	}
}


class MyThread5 extends Thread{
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("run " +Thread.currentThread().getName()+" "+this.isAlive());
		} catch (InterruptedException e) {
		}
	}
	
}
