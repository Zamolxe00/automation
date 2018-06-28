import org.apache.log4j.Logger;
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

public class TestCategoryPages {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger log = Logger.getLogger(TestCategoryPages.class);

    @BeforeClass
    public static void setUpPath() {
        Utils.setDriverPath();
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

//This process describes following steps:
//-> go to Homepage-> Hover Over Category Menu in Header -> Select Jewelry for Ashes -> Identify Category Description -> Click on Category Description
// -> confirm the curent URL reads "#categoryDescription ” meaning that the cursor jumped to Product Description section on product page
// -> Confirm that On Category Page BreadCrumbNavigation is available
    @Test
    public void checkCategoryDescriptionAndBreadCrumb() {
        log.info("Select Jewelry Category from Header Menu Drop-Down");
        log.info("Click on Category Title");
        log.info("Identify Breadcrumb and confirm presence on page");
        log.info("Confirm Category Page Description is displayed in browser" );
        WebElement element = driver.findElement(By.linkText("Jewelry"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Jewelry For Ashes")).click();
        WebElement categoryDescription = driver.findElement(By.id("MainContainer")).findElement(By.className("category-title")).findElement(By.tagName("a"));
        categoryDescription.click();
        assertTrue(driver.getCurrentUrl().contains(".html#categoryDescription"));
        WebElement cookieTrail = driver.findElement(By.className("breadcrumb-label"));
        assertTrue(cookieTrail.isDisplayed());
    }
//Test Finders
//Test Jewelry finder results are related to Jewelry
//This process describes following steps: -> go to Homepage-> Hover Over Jewelry Category -> Click on Jewelry -> Identify Finder on Page -> Click on Jewelry Finder
// -> Confirm the current Url contains “cremation-jewelry-finder”
    @Test
    public void checkJewelryFinder() {
        log.info("Select Jewelry Category from Header Menu Drop-Down");
        WebElement element = driver.findElement(By.linkText("Jewelry"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Jewelry")).click();
        log.info("Identify and Click on Jewelry Finder ");
        WebElement jewelryFinder = driver.findElement(By.className("category-finder-banner"));
        jewelryFinder.click();
        log.info("Confirm Jewelry Finder Page is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("cremation-jewelry-finder"));
    }

//test Memorial Finder Results are related to memorial products
//This process describes following steps:-> go to Homepage-> Hover Over Category Menu in Header  -> Click on  Comfort products-> Identify Finder on Page -> Click on Memorial
// -> Confirm the current Url contains “memorial-product-finder”
    @Test
    public void checkMemorialFinder() {
        log.info("Select Comfort Products Category from Header Menu Drop-Down");
        WebElement element = driver.findElement(By.linkText("Comfort Products"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Comfort Products")).click();
        log.info("Identify and Click on Memorial Finder ");
        WebElement memorialFinder = driver.findElement(By.className("category-finder-banner"));
        memorialFinder.click();
        log.info("Confirm Memorial Product Finder Page is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("memorial-product-finder"));
    }

//This process describes following steps:-> go to Homepage-> Hover Over Category Menu in Header  -> Click on Cremation Urns -> Identify Urn Finder -> Click on Urn Finder
// -> Select Person ->Select All Ashes -> Add 150 (pounds)-> Click Show Urns -> Confirm current url contains the relevant results

    @Test
    public void checKCremationUrnFinder() {
        log.info("Select Cremation Urns Category from Header Menu Drop-Down");
        WebElement element = driver.findElement(By.linkText("Cremation Urns"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("Cremation Urns")).click();
        log.info("Identify and Click on Cremtion Urn Finder ");
        WebElement cremationUrnFinder = driver.findElement(By.className("category-finder-banner"));
        cremationUrnFinder.click();
        log.info("Select People");
        List<WebElement> answer1Buttons = driver.findElement(By.id("UrnFinderQuery")).findElement(By.className("dialog-1")).findElements(By.tagName("button"));
        answer1Buttons.get(0).click();
        List<WebElement> answer2Buttons = driver.findElement(By.id("UrnFinderQuery")).findElement(By.className("dialog-2")).findElements(By.tagName("button"));
        log.info("Select All Ashes");
        answer2Buttons.get(0).click();
        log.info("Volume 150");
        WebElement poundInput = driver.findElement(By.id("volumeInput"));
        poundInput.click();
        poundInput.clear();
        poundInput.sendKeys("150");
        WebElement showUrns = driver.findElement(By.linkText("Show urns"));
        log.info("Show Urns");
        showUrns.click();
        log.info("Confirm urns for people are displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("popularity&action=results"));
    }

// Check Category Listing in Category Page
//This process describes following steps: go to Memorial Jewelry Page -> Check Category Type results -> Select First Category Result in List -> Click on Selected Category
// //-> validate that the page oppened is a category type  page
    @Test
    public void checkCategoryListings() {
        log.info("Test");
        log.info("open Memorial Jewelry Page ");
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        log.info("Identify Category Card Elements and Click on first card from list" );
        List<WebElement> productGrid = driver.findElements(By.tagName("a"));
        List<WebElement> categoryItems = new ArrayList<WebElement>();
        for (WebElement prod : productGrid)
            if (prod.getAttribute("class").contains("category-item"))
                categoryItems.add(prod);
        assertTrue(categoryItems.size() != 0);
        categoryItems.get(0).click();
        log.info("Confirm  Category Page is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("-c-"));
    }
 //Check Product Listing in Category Page
//This process describes following steps: go to Memorial Jewelry Page -> Check Product Type results -> Select First Product Result in List -> Click on Selected Product
//-> validate that the page oppened is a product type  page

    @Test
    public void checkProductListings() {
        log.info("Test");
        log.info("open Memorial Jewelry Page ");
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        log.info("Identify Product Card Elements and Click on first card from list" );
        List<WebElement> productGrid = driver.findElements(By.tagName("a"));
        List<WebElement> productItems = new ArrayList<WebElement>();
        for (WebElement prod : productGrid)
            if (prod.getAttribute("class").contains("product-item"))
                productItems.add(prod);
        assertTrue(productItems.size() != 0);
        productItems.get(0).click();
        log.info("Confirm  Product Page is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("-p-"));
    }

//Test Sort by rating  lowest first + filter by color
//This process describes following steps: Hover Over Categories Menu in header -> Click on Cremation Urns For People -> Identify Filter “Black”
// -> Sort By “review” option -> confirm that the products displayed are categorized by reviews descendent order
    @Test
    public void testSortAndFilter() {
        log.info("Test Sort by color and filter by reviews usage" );
        log.info("Select Cremation Urns -> People Category from Header Menu Drop-Down");
        WebElement cremationUrnsDroplist = driver.findElement(By.linkText("Cremation Urns"));
        Actions action = new Actions(driver);
        action.moveToElement(cremationUrnsDroplist).build().perform();
        driver.findElement(By.id("PeopleBanner")).findElement(By.tagName("a")).click();
        log.info("Select Color Black ");
        WebElement color = driver.findElement(By.partialLinkText("Black"));
        color.click();
        log.info("Click on Reviews Element");
        driver.findElement(By.linkText("Reviews")).click();
        List<WebElement> ratingList = driver.findElements(By.className("reviewers"));
        LinkedList<Double> ratings = new LinkedList<Double>();
        log.info("Confirm that the products are displayed on page in descendent order based on Reviews number ");
        for (WebElement ratingElement : ratingList) {
            String ratingString = ratingElement.getText().replace('(', ' ').replace(')', ' ').trim();
            ratings.add(Double.parseDouble(ratingString));
        }
        for (int i = 0; i < ratings.size() - 1; i++) {
            assertTrue(ratings.get(i) >= ratings.get(i + 1));
        }
    }

//Test Start Over Button
 //This process describes following steps: Hover Over Categories Menu in header -> Click on Cremation Urns For People -> Identify Filter “Black” ->Click on Start over button
// -> confirm that Cremation Urn Finder page displays

    @Test
    public void testStartOverButton() {
        log.info("Testing Start over Button usage" );
        log.info("Select Cremation Urns -> People Category from Header Menu Drop-Down");
        WebElement cremationUrnsDroplist = driver.findElement(By.linkText("Cremation Urns"));
        Actions action = new Actions(driver);
        action.moveToElement(cremationUrnsDroplist).build().perform();
        driver.findElement(By.id("PeopleBanner")).findElement(By.tagName("a")).click();
        log.info("Select Color Black ");
        WebElement color = driver.findElement(By.partialLinkText("Black"));
        color.click();
        log.info("Click Start Over ");
        WebElement startOver = driver.findElement(By.linkText("Start over"));
        startOver.click();
        log.info("Confirm Cremation Urn Finder is displayed in browser" );
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
