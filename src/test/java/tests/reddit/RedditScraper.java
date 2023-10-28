package tests.reddit;

import com.opencsv.exceptions.CsvException;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import output.Out;
import pages.reddit.RedditBasePage;
import pages.reddit.SubredditPage;
import testrunner.BaseTest;
import utilities.CSVParser;
import utilities.Constants;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class RedditScraper extends BaseTest {

    private static final String[] HEADERS_GROUP = { "Author Name", "Post Title", "Date", "Contents" };

    @Test(groups = "group1")
    @Parameters({"minusDays", "plusDays", "subreddits"})
    public void redditScrape(String subreddits)
            throws RuntimeException, IOException, GeneralSecurityException, InterruptedException, CsvException {

        List<Out> out = new ArrayList();

        getDriver().navigate().to(Constants.REDDIT_BASE_URL);

        RedditBasePage redditPage = new RedditBasePage(getDriver());

        String username = System.getenv("REDDIT_USERNAME");
        String password = System.getenv("REDDIT_PASSWORD");

        redditPage.login(username, password);

        // The logic behind this could be inside a loop, navigating through several reddits if needed.
        SubredditPage subredditPage = redditPage.searchSubreddit(subreddits);

        subredditPage.goToCategory("NEW");

        subredditPage.scrollDown(2000);
        subredditPage.scrollDown(1000);

        Document doc = Jsoup.connect(getDriver().getCurrentUrl()).get();

        Elements posts = doc.selectXpath("//shreddit-post");

        for (Element element: posts) {
            Out o = new Out();
            String postTitle = element.selectXpath(".//div[contains(@id, 'post-title')]").text();

            String author = element.selectXpath(".//span[@slot='authorName']//a").attr("href").replace("/user", "").replace("/", "");
            
            o.setTitle(postTitle);
            o.setAuthorName(author);
            // o.setContents(); Get contents, date, etc of each post and set on the DTO.

            out.add(o);
        }
        
        for (Out o: out) {
            System.out.println("AUTHOR: " + o.getAuthorName() + "\n TITLE: " + o.getTitle());
        }

        CSVParser csv = new CSVParser();
        csv.writeCSV(out, HEADERS_GROUP);
    }
}
