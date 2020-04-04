package com.test.j2se.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThread {

	public static void main(String[] args) throws Exception {

		Rabbit thread=new Rabbit();
		System.out.println(thread instanceof Thread);
//		thread.start();

//		Class<Rabbit> tmp=Rabbit.class;
//		System.out.println(tmp);
//		Constructor[] cons=Class.forName("com.test.j2se.thread.Rabbit").getConstructors();
//		System.out.println(cons);


	}

	public static class B {

	}

	AtomicInteger atomicInteger = new AtomicInteger(1);

}

class A{

}