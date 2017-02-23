package com.zr.thread;

/**   
 * @Title: ThreadStop.java 
 * @Package com.zr.thread 
 * @Description: 停止线程 
 * @author zhurui
 * @date 2017年2月23日 下午4:30:42 
 * @version V1.0   
 */
public class ThreadStop {
	public static void main(String[] args) {
//		test();
		test2();
	}
	
	/**
	 * @Description:中断线程，判断线程的中断状态
	 */
	public static void test(){
		try {
			TestThread testThread = new TestThread();
			testThread.start();
			testThread.interrupt();//中断线程
			
			//isInterrupted测试线程是否中断,
			System.out.println("testThread.isInterrupted()="+testThread.isInterrupted());
			
			//interrupted测试当前线程是否中断，判断完毕后清楚中断状态。当前线程是main。一直未中断
			System.out.println("Thread.interrupted()="+Thread.interrupted());
			
			Thread.currentThread().interrupt();//中断当前线程
			System.out.println("Thread.interrupted()="+Thread.interrupted());//true
			System.out.println("Thread.interrupted()="+Thread.interrupted());//false 是因为第二次调用interrupted()会清除中断状态
		} catch (Exception e) {
		}
	}
	
	/**
	 * @Description: 测试中断线程后，线程是否还继续执行
	 */
	public static void test2(){
		TestThread2 testThread2 = new TestThread2();
		testThread2.start();
		testThread2.interrupt();
	}
}


class TestThread extends Thread{
	@Override
	public void run() {
	}
}

class TestThread2 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			if (Thread.interrupted()) {
				System.out.println("线程已经中断该退出了");
				break;
			}
		}
		System.out.println("输出说明线程并未停止还在继续运行");
	}
}