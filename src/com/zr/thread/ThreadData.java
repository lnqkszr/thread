package com.zr.thread;

/**   
 * @Title: ThreadData.java 
 * @Package com.zr.thread 
 * @Description: 实例变量与线程安全（线程数据共享） 
 * 非线程安全的：指的是多个线程对同一个对象中同一个实例变量进行操作时会出现值被更改、值不同步的情况
 * @author zhurui   
 * @date 2017年2月22日 下午7:55:28 
 * @version V1.0   
 */
public class ThreadData {
	public static void main(String[] args) {
//		noShartDataTest();
//		noShareDataTest2();
		shareDataTest();
	}
	
	public static void noShartDataTest(){
		MyThread2 thread = new MyThread2("线程1");
		thread.start();
		MyThread2 thread2 = new MyThread2("线程2");
		thread2.start();
		/**
		 * 输出结果：
		 *  线程1计算：count=2
			线程1计算：count=1
			线程1计算：count=0
			线程2计算：count=2
			线程2计算：count=1
			线程2计算：count=0
			创建了两个线程，每个线程都有自己的count，各自减少各自的count
		 */
	}
	
	public static void noShareDataTest2(){
		MyThread3 myThread = new MyThread3();
		Thread threadA = new Thread(myThread,"A");
		Thread threadB = new Thread(myThread,"B");
		Thread threadC = new Thread(myThread,"C");
		Thread threadD = new Thread(myThread,"D");
		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		/**
		 * 输出结果：（每次都不同）
		 *  B计算：count=1
			A计算：count=1
			C计算：count=1
			D计算：count=1
			ABCD打印的都是1，说明ABCD同时对count进行了处理，
			产生非线程安全的问题
		 */
	}
	
	public static void shareDataTest(){
		MyThread4 myThread = new MyThread4();
		Thread threadA = new Thread(myThread,"A");
		Thread threadB = new Thread(myThread,"B");
		Thread threadC = new Thread(myThread,"C");
		Thread threadD = new Thread(myThread,"D");
		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		/**
		 * 输出结果：
		 *  A计算：count=3
			D计算：count=2
			C计算：count=1
			B计算：count=0
			在run()前加synchronized关键字，使线程在执行run方法的时候排队处理。
			当一个线程调用run方法的时候先判断run方法有没有被锁上，如果上锁说明有别的
			程序在调用run方法。必须等别的线程执行完毕释放锁后才能调用run方法，
			synchronized可以对任何方法、对象上加锁。
		 */
	}
	
	
}

class MyThread2 extends Thread {
	private int count = 3;
	public MyThread2(){}
	public MyThread2(String name){
		this.setName(name);
	}
	@Override
	public void run() {
		while(count > 0){
			count--;
			System.out.println(this.currentThread().getName()+"计算：count="+count);
		}
	}
}

class MyThread3 extends Thread {
	private int count = 4;
	@Override
	public void run() {
		count--;
		System.out.println(this.currentThread().getName()+"计算：count="+count);
	}
}

class MyThread4 extends Thread {
	private int count = 4;
	@Override
	public synchronized void run() {
		count--;
		System.out.println(this.currentThread().getName()+"计算：count="+count);
	}
}

