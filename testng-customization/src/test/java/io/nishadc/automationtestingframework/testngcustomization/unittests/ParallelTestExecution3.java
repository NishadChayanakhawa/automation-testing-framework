package io.nishadc.automationtestingframework.testngcustomization.unittests;

import org.testng.annotations.Test;

public class ParallelTestExecution3 {
	
	@Test
	public void test11() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	@Test
	public void test12() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	@Test
	public void test13() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	@Test
	public void test14() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	@Test
	public void test15() throws InterruptedException {
		Thread.sleep(2000);
	}
}
