package com.selenium.basic;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		Scanner sc = new Scanner(System.in);
		Implementation implementation = new Implementation();
		System.out.println("Enter 1 for Chrome \nEnter 2 for Firefox"); //for selecting the browser
		int ch = sc.nextInt();  //input
		sc.close();
		//Calling the methods in Implementation class
		implementation.createDriver(ch);
		implementation.search();
		try {
			implementation.selectCategory();
			implementation.setPrice();
			implementation.sortByPopularity();
			implementation.printHeadphones();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		implementation.closeBrowser();
	}

}
