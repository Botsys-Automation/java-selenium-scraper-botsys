package testrunner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import logging.Logging;
import utils.PropertyReader;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

	private ChromeDriver driver;

	@BeforeMethod(alwaysRun = true)
	protected void setup(Method m) {
		driver = new ChromeDriver(getChromeOptions());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	private ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--no-sandbox");
		//options.addArguments("--headless"); // Uncomment if running on a server or Github Actions.
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-features=EnableEphemeralFlashPermission");
		options.addArguments("--disable-infobars");

		return options;
	}

	@AfterMethod(alwaysRun = true)
	protected void cleanUp() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
