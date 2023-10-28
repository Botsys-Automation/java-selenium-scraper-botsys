package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import seleniumutils.Waits;

/**
 * All your Page Objects should extend BasePage. It contains a generic constructor so its subclasses only need to call super(driver) in its constructors
 * instead of calling PageFactory directly.
 * Also acts as wrapper for all the Page Navigation methods (forward, back, refresh).
 * Contains generic methods that can be applied in any Page Object.
 * 
 * @author Botsys Automation (botsys.automation@gmail.com)
 *
 */
public abstract class BasePage {

	protected WebDriver driver;
	protected Waits w = new Waits();
	
	public BasePage(WebDriver driver) {
		setDriver(driver);
		PageFactory.initElements(driver, this);
		w.isPageLoaded(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	protected WebDriver getDriver() {
		return this.driver;
	}
	
	public void navigateBack() {
		driver.navigate().back();
		w.shortWait();
	}
	
	public void navigateForward() {
		driver.navigate().forward();
	}
	
	public void refresh() {
		driver.navigate().refresh();
	}
}
