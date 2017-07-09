package apilipen.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {

	protected WebDriver driver;

	public HelperBase(WebDriver wd) {
		// TODO Auto-generated constructor stub
		this.driver = wd;
	}

	protected void clickElement(By locator) {
		driver.findElement(locator).click();
	}

	protected void setText(By locator, String text) {
		clickElement(locator);
		  driver.findElement(locator).clear();
		  driver.findElement(locator).sendKeys(text);
	}

	
	protected void submitAlertPopUp() {
		driver.switchTo().alert().accept();
		
	}
	
	
	public  boolean isAlertPresent() {
	    try {
	    	driver.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException e) {
	        return false;
	    }
	}

	protected boolean isElementPresent(By locator) {
			try {
			driver.findElement(locator);
			
			return true;
			} catch (NoSuchElementException ex){
			
	return false;
			}
	
	
		}
	
}
