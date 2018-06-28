import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestPromotionsPage {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger log = Logger.getLogger(TestPromotionsPage.class);

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

//Test Homepage  Main Promo Banner
//This process describes following steps: Go to homepage -> check if Main Banner Is present ->
//Get banner hipelink -> Click on Banner ->Confirm that the link oppend is same as banner link

    @Test
    public void testMainPromoBanner() {
        log.info("Go to Homepage" );
        log.info("Check  Main Promo Banner " );
        log.info("If product banner is displayed, retain Main Promo Banner hiperlink , click on Main Promo Banner " );
        log.info("Confirm page oppened is similar to Main Promo banner Hyperlink" );
        assertTrue(isElementPresent(By.id("MainPromo")));
        WebElement MainPromoBannerLink = driver.findElement(By.id("MainPromo"));
        String bannerLink = driver.findElement(By.id("MainPromo")).findElement(By.tagName("a")).getAttribute("href");
        try {
            MainPromoBannerLink.click();
            assertTrue(driver.getCurrentUrl().contains(bannerLink));
        } catch (WebDriverException e) {
            assertTrue(MainPromoBannerLink.isDisplayed());
        }
    }

//Test Main Promo Second Banner
//This process describes following steps: Go to homepage -> check if second Main Banner Is present -> Get banner hipelink -> Click on Banner
// ->Confirm that the link oppend is same as banner link

    @Test
    public void testMainPromoSecondBanner() {
        log.info("Go to Homepage" );
        log.info("Check  Main Promo Second Banner" );
        log.info("If product banner is displayed, retain Main Promo Banner hiperlink , click on Main Promo Banner " );
        log.info("Confirm page oppened is similar to Main Promo banner Hyperlink" );
        assertTrue(isElementPresent(By.id("MainPromo")));
        List<WebElement> bannerLinks = driver.findElement(By.id("MainPromo")).findElements(By.tagName("a"));
        WebElement MainPromoBannerLink = bannerLinks.get(1);
        String bannerLink = bannerLinks.get(1).getAttribute("href");
        try {
            MainPromoBannerLink.click();
            Set<String> windows = driver.getWindowHandles();
            List<String> windowList = new ArrayList<>();
            for (String window : windows)
                windowList.add(window);
            driver.switchTo().window(windowList.get(windowList.size()-1));
            assertTrue(driver.getCurrentUrl().contains(bannerLink));
        } catch (WebDriverException e) {
            assertTrue(MainPromoBannerLink.isDisplayed());
        }
    }
//Test Daily Deal Banner
//This process describes following steps: Go to homepage ->  check for daily deal banner -> click on banner
//If no daily promotion avaialble confirm that promotions page oppens
//If daily product avaialble confirm that product link oppens

    @Test
    public void testDailyDealBanner() {
        log.info("Go to Homepage" );
        log.info("Check Daily Deal  Banner " );
        log.info("If Daily Deal banner is displayed, retain Banner hiperlink , click on  Banner " );
        log.info("Confirm page oppened is promotions page if no daily deal is active" );
        log.info("Confirm page oppened is  product page if  daily deal is active" );
        WebElement DailyDealBannerLink = driver.findElement(By.id("DailyDeal"));
        String productLink = DailyDealBannerLink.findElement(By.tagName("a")).getAttribute("href");

        try {
            DailyDealBannerLink.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            Set<String> windows = driver.getWindowHandles();
            List<String> windowList = new ArrayList<>();
            for (String window : windows)
                windowList.add(window);
            driver.switchTo().window(windowList.get(windowList.size()-1));
            try {
                wait.until(ExpectedConditions.urlContains("promotions"));
                assertTrue(driver.getCurrentUrl().contains("promotions"));
            } catch (TimeoutException e) {
                assertTrue(driver.getCurrentUrl().contains(productLink));
            }
        } catch (WebDriverException e) {
            assertTrue(DailyDealBannerLink.isDisplayed());
        }
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
