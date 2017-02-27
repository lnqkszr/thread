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
//		test2();
//		test3();
//		test4();
		test5();
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
		/**
		 * 输出结果：
		 * 线程已经中断该退出了
		         输出说明线程并未停止还在继续运行
		 */
	}
	
	/**
	 * @Description:使用抛出异常的方法解决线程中断后还继续运行的问题
	 */
	public static void test3(){
		TestThread3 testThread3 = new TestThread3();
		testThread3.start();
		testThread3.interrupt();
	}
	
	/**
	 * @Description:在睡眠中停止线程，会进入catch方法中断线程运行
	 */
	public static void test4(){
		try {
			TestThread4 testThread4 = new TestThread4();
			testThread4.start();
			Thread.sleep(1000);
			testThread4.interrupt();
		} catch (InterruptedException e) {
		}
	}
	
	/**
	 * @Description:使用return 终止线程运行
	 */
	public static void test5(){
		try {
			TestThread5 testThread4 = new TestThread5();
			testThread4.start();
			testThread4.interrupt();
		} catch (Exception e) {
		}
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

class TestThread3 extends Thread{
	@Override
	public void run() {
		try {
			for (int i = 0; i < 1000; i++) {
				if (Thread.interrupted()) {
					System.out.println("线程已经中断该退出了");
					throw new InterruptedException();
				}
			}
			System.out.println("输出说明线程并未停止还在继续运行");
		} catch (InterruptedException e) {
			System.out.println("进入catch方法,线程中断");
		}
	}
}

class TestThread4 extends Thread{
	@Override
	public void run() {
		try {
			System.out.println("线程开始");
			Thread.sleep(50000);
			System.out.println("线程结束");
		} catch (InterruptedException e) {
			System.out.println("进入catch方法线程结束");
		}
	}
}

class TestThread5 extends Thread{
	@Override
	public void run() {
		System.out.println("线程开始");
		if (Thread.interrupted()) {
			System.out.println("线程已经中断该退出了");
			return; 
		}
		System.out.println("线程结束");
	}
}