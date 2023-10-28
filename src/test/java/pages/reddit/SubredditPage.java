package pages.reddit;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import seleniumutils.Utilities;

import java.util.Locale;

public class SubredditPage extends BasePage {

    public SubredditPage(WebDriver driver) {
        super(driver);
    }

    // hot / new / top / rising
    public void goToCategory(String category) {
        getDriver().navigate().to(getDriver().getCurrentUrl() + category.toLowerCase(Locale.ROOT));
    }

    public void scrollDown() {
        scrollDown(350);
    }

    public void scrollDown(int pixels) {
        Utilities.scrollDown(getDriver(), pixels);
        w.shortWait();
    }
}
