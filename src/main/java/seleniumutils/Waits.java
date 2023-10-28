package seleniumutils;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Helper class to create all the different waits.
 * You should not call driver directly in the Page Objects, instead, call a method from this class. 
 * 
 * @author Botsys Automation (botsys.automation@gmail.com)
 *
 */
public class Waits {
	
	private static <T> T waitForCondition(WebDriver driver, Integer timeOutSeconds, Function<WebDriver, T> condition) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds))
                .ignoring(WebDriverException.class) // Ignores all the Selenium related exceptions, ie: TimeOutException, StaleElementException, UnreachableBrowserException etc.
                .until(condition);
	}

	public void javascriptClick(WebDriver d, By by, int seconds) {
		waitForCondition(d, seconds, ExpectedConditions.elementToBeClickable(by));
		WebElement el = findElement(d, by, 1);

		JavascriptExecutor executor = (JavascriptExecutor) d;
		executor.executeScript("arguments[0].click();", el);

		shortWait();
	}
	public void waitElementEnabled(WebDriver d, By by, int seconds) {
		waitForCondition(d, seconds, ExpectedConditions.elementToBeClickable(by));
	}

	public void waitElementEnabled(WebDriver d, WebElement el, int seconds) {
		waitForCondition(d, seconds, ExpectedConditions.elementToBeClickable(el));
	}

	public void waitForElementToBeVisible(WebDriver driver, final WebElement element, int seconds) {
		try {
			waitForCondition(driver, seconds, d -> element.isDisplayed());
		} catch (Exception e) {}
	}
	
	public void waitForElementToDisappear(WebDriver driver, final WebElement element, int seconds) {
		waitForCondition(driver, seconds, d -> !element.isDisplayed());
	}

	public Boolean isElementPresent(WebDriver d, String path, int seconds) {
		try {
			return waitForCondition(d, seconds, ExpectedConditions.visibilityOfElementLocated(By.xpath(path))).isDisplayed();
		} catch (Exception e) { return false; }
	}
	
	public WebElement findElement(WebDriver driver, final String locator, int seconds) {
		By by = By.xpath(locator);
		return findElement(driver, by, seconds);
	}
	
	public WebElement findElement(WebDriver driver, By locator, int seconds) {
		try {
			return waitForCondition(driver, seconds, ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) { return null; }
	}
	
	public boolean isTextPresent(WebDriver driver, String text, int seconds) {
		try {
			return waitForCondition(driver, seconds, d -> d.getPageSource().contains(text));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isPageLoaded(WebDriver driver) {
		try {

			return waitForCondition(driver, 30,
					d -> ((JavascriptExecutor)driver)
							.executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
			return false;
		}
	}

	public void shortWait() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void waitFiveSeconds() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
