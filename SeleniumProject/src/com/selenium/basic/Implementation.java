package com.selenium.basic;



import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Implementation {
	static WebDriver driver;
	static Properties prop;
	static FileIO fileio;
	static By searchBox;
	static By wirelessFilter;
	static By searchButton;
	static By minPrice;
	static By maxPrice;
	static By priceGoButton;
	static By sortDropdown;
	static By popularityOption;
	static By productTitle;
	static By productPrice = By.className("product-price");
	
	//constructor
		public Implementation() {
			fileio = new FileIO();
			prop = fileio.inputSetup();
		}

		//getting the driver from DriverSetup
		public void createDriver(int option) {
			driver = DriverSetup.getDriver(option);
		}

		//Searching the headphones
		public void search(){
			searchBox=By.id(prop.getProperty("searchBox"));
			driver.findElement(searchBox).sendKeys(prop.getProperty("searchData"));
			searchButton = By.xpath(prop.getProperty("searchButton"));
			driver.findElement(searchButton).click();			
			WebDriverWait wait=new WebDriverWait(driver,5);
		}
		//Selecting wireless category
		public void selectCategory() throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,200)", "");
	        Thread.sleep(5000);
			wirelessFilter = By.xpath(prop.getProperty("wirelessFilter"));
			WebElement wireless = driver.findElement(wirelessFilter);
			wireless.click();
		}
		//setting the minimum and maximum price (700,1400)
		public void setPrice() throws InterruptedException {
			Thread.sleep(1000);
			minPrice = By.name(prop.getProperty("minPrice"));
			maxPrice = By.name(prop.getProperty("maxPrice"));
			priceGoButton = By.xpath(prop.getProperty("priceGoButton"));
			driver.findElement(minPrice).clear();
			driver.findElement(minPrice).sendKeys(prop.getProperty("minData"));
			driver.findElement(maxPrice).clear();
			driver.findElement(maxPrice).sendKeys(prop.getProperty("maxData"));
			driver.findElement(priceGoButton).click();
			Thread.sleep(1000);
		}
		//sorting the results by popularity
		public void sortByPopularity() {
			sortDropdown = By.className(prop.getProperty("sortDropdown"));
			popularityOption = By.xpath(prop.getProperty("popularityOption"));
			driver.findElement(sortDropdown).click();
			driver.findElement(popularityOption).click();
		}
		
		//getting the results from the site and printing in console and Excel file
		public void printHeadphones() throws InterruptedException {
			Thread.sleep(2000);
			productTitle = By.className(prop.getProperty("productTitle"));
			productPrice = By.className(prop.getProperty("productPrice"));
			List<WebElement> titles = driver.findElements(productTitle);
			List<WebElement> prices = driver.findElements(productPrice);
			String[] name = new String[5];
			String[] price = new String[5];
			for (int i = 0; i < 5; i++) {
				name[i] = titles.get(i).getText();
				price[i] = prices.get(i).getText();
				System.out.print(titles.get(i).getText() + "  ");
				System.out.println(prices.get(i).getText());
				fileio.output(name, price);
			}
		}
		public void closeBrowser() {
			driver.quit();
		}
}
