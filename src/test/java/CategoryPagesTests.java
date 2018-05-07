import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class CategoryPagesTests {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setUpPath() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
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
    public void checkCategoryTitlePresent () {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        assertTrue(isElementPresent(By.className("category-title")));
    }

// category pages
//Test Click Category Title goes to description

    @Test
    public void checkCategoryDescription () {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        WebElement categoryDescription = driver.findElement(By.xpath("//*[@id=\"MainContainer\"]/h3/a"));
        categoryDescription.click();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        assertTrue(driver.getCurrentUrl().contains("categoryDescription"));

    }


    //    test cookie trail
    @Test
    public void checkYouAreHereIsPresent () {
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
    public void checkProductImage() {
        driver.get("http://www.theperfecturn.com/memorial-jewelry");
        WebElement productCard = driver.findElement(By.className("product-item col-4 d-flex flex-column"));
        WebElement productTitle = driver.findElement(By.className("name"));
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
