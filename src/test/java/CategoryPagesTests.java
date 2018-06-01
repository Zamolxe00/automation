import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedList;
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

//Test Click on Category title goes to category description

    @Test
    public void checkCategoryDescription() {
        WebElement element = driver.findElement(By.linkText("Jewelry"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Jewelry For Ashes")).click();
        WebElement categoryDescription = driver.findElement(By.id("MainContainer")).findElement(By.className("category-title")).findElement(By.tagName("a"));
        categoryDescription.click();
        assertTrue(driver.getCurrentUrl().contains(".html#categoryDescription"));
    }

    // Test Cookie trail - assert available
    @Test
    public void checkAssertPresentCookieTrail() {
        WebElement element = driver.findElement(By.linkText("Jewelry"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Jewelry For Ashes")).click();
        WebElement cookieTrail = driver.findElement(By.className("breadcrumb-label"));
        assertTrue(cookieTrail.isDisplayed());
    }


    // test jewelry finder results are related to Jewelry
    @Test
    public void checkJewelryFinder() {
        WebElement element = driver.findElement(By.linkText("Jewelry"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Jewelry")).click();
        WebElement jewelryFinder = driver.findElement(By.className("category-finder-banner"));
        jewelryFinder.click();
        assertTrue(driver.getCurrentUrl().contains("cremation-jewelry-finder"));
    }

    //test Memorial Finder Results are related to memorial products
    @Test
    public void checkMemorialFinder() {
        WebElement element = driver.findElement(By.linkText("Comfort Products"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Comfort Products")).click();
        WebElement memorialFinder = driver.findElement(By.className("category-finder-banner"));
        memorialFinder.click();
        assertTrue(driver.getCurrentUrl().contains("memorial-product-finder"));
    }

    //test Cremation Urn Finder Results are related to memorial products
    @Test
    public void checKCremationUrnFinder() {
        WebElement element = driver.findElement(By.linkText("Cremation Urns"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Cremation Urns")).click();
        WebElement cremationUrnFinder = driver.findElement(By.className("category-finder-banner"));
        cremationUrnFinder.click();
        List<WebElement> answer1Buttons = driver.findElement(By.id("UrnFinderQuery")).findElement(By.className("dialog-1")).findElements(By.tagName("button"));
        answer1Buttons.get(0).click();
        List<WebElement> answer2Buttons = driver.findElement(By.id("UrnFinderQuery")).findElement(By.className("dialog-2")).findElements(By.tagName("button"));
        answer2Buttons.get(0).click();
        WebElement poundInput = driver.findElement(By.id("volumeInput"));
        poundInput.click();
        poundInput.clear();
        poundInput.sendKeys("150");
        WebElement showUrns = driver.findElement(By.linkText("Show urns"));
        showUrns.click();
        assertTrue(driver.getCurrentUrl().contains("150&sort=popularity&action=results"));
    }


    // test category description expands on click
    @Test
    public void checkCategoryDesctiprionExpands() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        WebElement categoryDescription = driver.findElement(By.id("categoryDescription"));
        WebDriverWait wait = new WebDriverWait(driver, 100);
        categoryDescription.click();

        assertTrue(!categoryDescription.getAttribute("class").contains("collapsed"));
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

    // Test Display results in Sort by Color and Filter By Rating
    @Test
    public void testSortFilter() {
        WebElement cremationUrnsDroplist = driver.findElement(By.linkText("Cremation Urns"));
        Actions action = new Actions(driver);
        action.moveToElement(cremationUrnsDroplist).build().perform();

        driver.findElement(By.id("PeopleBanner")).findElement(By.tagName("a")).click();

        WebElement color = driver.findElement(By.partialLinkText("Black"));
        color.click();
        driver.findElement(By.linkText("Reviews")).click();
        List<WebElement> ratingList = driver.findElements(By.className("reviewers"));
        LinkedList<Double> ratings = new LinkedList<Double>();
        for (WebElement ratingElement : ratingList) {
            String ratingString = ratingElement.getText().replace('(', ' ').replace(')', ' ').trim();
            ratings.add(Double.parseDouble(ratingString));
        }
        for (int i = 0; i < ratings.size() - 1; i++) {
            assertTrue(ratings.get(i) >= ratings.get(i + 1));
        }
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
