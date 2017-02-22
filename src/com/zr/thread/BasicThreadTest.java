package com.zr.thread;
/**   
 * @Title: BasicThreadTest.java 
 * @Package com.zr.thread 
 * @Description: 多线程的概念及其基本使用
 * 进程:windows任务管理器中exe文件就是进程，进程是受操作系统管理的基本单元。
 * 线程:线程是进程中独立运行的子任务。例如QQ.exe运行就有很多子任务运行、下载文件线程、好友视频线程
 * 优点:可以最大限度的利用CPU的空闲时间来处理其他的任务。比如一遍打印数据一遍编辑Word文档。CPU在
 * 在这些任务之间不停的切换。切换的速度很快。所以感觉像同时运行（异步）。而单任务的特点是排队执行（同步），任务1
 * 执行完才能执行任务2、CPU利用率很低。
 * @author zhurui
 * @date 2017年2月22日 上午11:46:02 
 * @version V1.0   
 */
public class BasicThreadTest {
	
	public static void main(String[] args) {
		/**
		 * 输出当前线程的名称
		 */
		System.out.println(Thread.currentThread().getName());
		testMyThread();
		testMyRunnable();
	}
	
	public static void testMyThread(){
		/**
		 * 输出结果:
		 * 		运行结束
		 * 		MyThread run
		 * run方法执行的比较晚，说明代码的运行结果和代码的调用顺序是无关的，
		 * 线程是一个子任务，以随机的时间来调用线程中的run方法
		 */
		MyThread myThread  = new MyThread();
		/*
		 * 线程已经准备好等待被系统安排一个时间来调用（不是立即调用），具有异步执行的效果
		 * 所以线程启动的顺序和start()的顺序无关
		 */
		myThread.start();
		System.out.println("运行结束");
	}
	
	public static void testMyRunnable(){
		MyRunnable runnable = new MyRunnable();
		/**
		 * Thread 类的构造函数可以支持传递Runnable接口
		 */
		Thread thread = new Thread(runnable);
		thread.start();
		/**
		 * Thread 类也实现了Runnable接口,所以传递一个Thread对象
		 * 将Thread对象中的run()方法放到另外一个线程中执行
		 */
		Thread thread2 = new Thread(new MyThread());
		thread2.start();
		
		System.out.println("运行结束");
	}
	
}

/**
 * @Description: 继承Thread方式实现多线程 
 * @author zhurui
 * @date 2017年2月22日 下午1:41:58 
 * @version V1.0
 */
class MyThread extends Thread{
	@Override
	public void run(){
		super.run();
		System.out.println("MyThread run");
	}
}

/** 
 * @Description: 实现Runnable接口实现多线程  
 * @author zhurui
 * @date 2017年2月22日 下午2:04:59 
 * @version V1.0
 */
class MyRunnable implements Runnable{
	@Override
	public void run() {
		System.out.println("MyRunnable run");
	}
}





