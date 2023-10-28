package pages.reddit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import utilities.Constants;

public class RedditBasePage extends BasePage {

	public RedditBasePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "login-button")
	private WebElement loginButton;

	@FindBy(how = How.ID, using = "header-search-bar")
	private WebElement searchBar;

	public void login(String username, String pass) {

		if (loginButton.isDisplayed() && loginButton.isEnabled()) {
			loginButton.click();

			w.findElement(driver, By.id("login-username"), 10).sendKeys(username);
			w.findElement(driver, By.id("login-password"), 10).sendKeys(pass);

			w.findElement(driver, By.id("login-password"), 10).sendKeys(Keys.ENTER);

			w.shortWait();
		} else {
			System.out.println("User is already logged in.");
		}
	}

	public SubredditPage searchSubreddit(String subredditName) {
		getDriver().navigate().to(Constants.REDDIT_BASE_URL.concat("/r/" + subredditName));
		w.shortWait();

		return PageFactory.initElements(driver, SubredditPage.class);
	}
}
