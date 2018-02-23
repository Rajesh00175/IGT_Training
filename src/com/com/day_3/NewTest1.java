package com.com.day_3;

import org.testng.annotations.Test;

public class NewTest1 {

	@Test
	public void testCaseOne() {
		//Printing Id of the thread on using which test method got executed
		System.out.println("Test Case One with Thread Id:- "
				+ Thread.currentThread().getId());
	}

	@Test
	public void testCaseTwo() {
		////Printing Id of the thread on using which test method got executed
		System.out.println("Test Case two with Thread Id:- "
				+ Thread.currentThread().getId());
	}
}
