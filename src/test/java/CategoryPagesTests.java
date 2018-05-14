import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class CategoryPagesTests {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setUpPath() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver");
    }

    @AfterClass
    public static void removePath() {
        System.clearProperty("webdriver.chrome.driver");
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://www.theperfecturn.com/");
    }

    // category pages
//Test Category Title

    @Test
    public void checkCategoryTitlePresent() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        assertTrue(isElementPresent(By.className("category-title")));
    }

// category pages
//Test Click Category Title goes to description

    @Test
    public void checkCategoryDescription() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        WebElement categoryDescription = driver.findElement(By.xpath("//*[@id=\"MainContainer\"]/h3/a"));
        categoryDescription.click();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        assertTrue(driver.getCurrentUrl().contains("categoryDescription"));

    }


    //    test cookie trail
    @Test
    public void checkYouAreHereIsPresent() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        WebElement cookieTrailAvailable = driver.findElement(By.className("breadcrumb-label"));
        assertTrue(cookieTrailAvailable.isDisplayed());
    }


// test finder on category page


    @Test
    public void checkFinder() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        WebElement finderlink = driver.findElement(By.className("category-finder-banner"));
        finderlink.click();
        assertTrue(driver.getCurrentUrl().contains("finder"));
    }


    // test category description expands on click
    @Test
    public void checkCategoryDesctiprionExpands() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        WebElement categoryDescription = driver.findElement(By.id("categoryDescription"));
        WebDriverWait wait = new WebDriverWait(driver, 100);
        categoryDescription.click();

        assertTrue(isElementPresent(By.className("extended collapse show")));
    }


    // test product listing in category results
    @Test
    public void checkCategoryListings() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        List<WebElement> productGrid = driver.findElements(By.tagName("a"));
        List<WebElement> categoryItems = new ArrayList<WebElement>();
        for (WebElement prod : productGrid)
            if (prod.getAttribute("class").contains("category-item"))
                categoryItems.add(prod);
        assertTrue(categoryItems.size() != 0);
        categoryItems.get(0).click();
        assertTrue(driver.getCurrentUrl().contains("-c-"));
    }

    @Test
    public void checkProductListings() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        List<WebElement> productGrid = driver.findElements(By.tagName("a"));
        List<WebElement> productItems = new ArrayList<WebElement>();
        for (WebElement prod : productGrid)
            if (prod.getAttribute("class").contains("product-item"))
                productItems.add(prod);
        assertTrue(productItems.size() != 0);
        productItems.get(0).click();
        assertTrue(driver.getCurrentUrl().contains("-p-"));
    }


//Category page Test
// Pets  Cremation Urns :Test Sorting Option
//Sort by rating  lowest first + filter by color

    @Test
    public void testFilterColor() {
        driver.get("http://www.theperfecturn.com/cremation-urn-finder?sort=reviews&sort_order=desc&filters=&action=results&type=pet");
        WebElement color = driver.findElement(By.partialLinkText("Black"));
        color.click();
        List<WebElement> ratingList = driver.findElements(By.className("reviewers"));
        List<Double> ratings = new ArrayList<Double>();
        for (WebElement ratingElement : ratingList) {
            String ratingString = ratingElement.getText().replace('(', ' ').replace(')', ' ').trim();

            ratings.add(Double.parseDouble(ratingString));
        }
        for (int i = 0; i < ratings.size() - 1; i++) {
            assertTrue(ratings.get(i) >= ratings.get(i + 1));
        }
    }

    //Category page Test
//Test  Start over button
    @Test
    public void testStartOverButton() {
        driver.get("http://www.theperfecturn.com/cremation-urn-finder?type=pet&material=Biodegradable&filters=243&action=results");
        WebElement color = driver.findElement(By.partialLinkText("Black"));
        color.click();
        WebElement startOver = driver.findElement(By.linkText("Start over"));
        startOver.click();
        assertTrue(driver.getCurrentUrl().contains("cremation-urn-finder"));
    }

    @After
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
