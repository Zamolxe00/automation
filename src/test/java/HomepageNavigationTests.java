import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HomepageNavigationTests {
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

    @Test
    public void testClickHelpAndLogo() {
        WebElement helpLink = driver.findElement(By.linkText("Help"));
        helpLink.click();
        assertTrue(driver.getCurrentUrl().contains("help"));
        WebElement logo = driver.findElement(By.className("logo-area"));
        logo.click();
        assertTrue(driver.getCurrentUrl().contains("theperfecturn"));
    }

    @Test
    public void testClickPromotionsAndHome() {
        WebElement PromotionsLink = driver.findElement(By.linkText("Promotions"));
        PromotionsLink.click();
        assertTrue(driver.getCurrentUrl().contains("promotions"));
        WebElement HomeLink = driver.findElement(By.linkText("Home"));
        HomeLink.click();
        assertTrue(driver.getCurrentUrl().contains("theperfecturn"));
    }

    @Test
    public void testClickContactUs() {
        WebElement ContactUsLink = driver.findElement(By.linkText("Contact Us"));
        ContactUsLink.click();
        assertTrue(driver.getCurrentUrl().contains("contact-us"));
    }

    @Test
    public void testClickMyProfile() {
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        assertTrue(driver.getTitle().equalsIgnoreCase("login"));
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void testClickCartImage() {
        WebElement CartImageLink = driver.findElement(By.id("ShoppingCartIcon"));
        CartImageLink.click();
        assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    @Test
    public void testClickCartItem() {
        WebElement CartItemLink = driver.findElement(By.id("ShoppingCart"));
        CartItemLink.click();
        assertTrue(driver.getCurrentUrl().contains("cart"));
    }


    //row footer links
    @Test
    public void testCatalogRequest() {
        WebElement CatalogRequestLink = driver.findElement(By.partialLinkText("Catalog"));
        CatalogRequestLink.click();
        assertTrue(driver.getCurrentUrl().contains("catalog"));
    }


    @Test
    public void testAddressBook() {
        WebElement AddressBookLink = driver.findElement(By.linkText("Address Book"));
        AddressBookLink.click();
        assertTrue(driver.findElement(By.className("main-banner")).getText().contains("Welcome"));
    }

    @Test
    public void testSiteMap() {
        WebElement SiteMapLink = driver.findElement(By.partialLinkText("Map"));
        SiteMapLink.click();
        assertTrue(driver.getCurrentUrl().contains("sitemap"));

    }

    @Test
    public void testFacebookRedirection() {
        WebElement FacebookRedirectionLink = driver.findElement(By.partialLinkText("Facebook"));
        FacebookRedirectionLink.click();
        assertTrue(driver.getCurrentUrl().contains("facebook"));

    }

    @Test
    public void testPaymentMethodsBadgeAvailable() {
        WebElement PaymentMethodsBadgeAvailableLink = driver.findElement(By.className("payment-methods"));
        assertTrue(PaymentMethodsBadgeAvailableLink.isDisplayed());

    }

    //Content
    //Daily deal banner
    @Test
    public void testDailyDealBanner() {
        WebElement DailyDealBannerLink = driver.findElement(By.id("DailyDeal"));
        try {
            DailyDealBannerLink.click();
            assertTrue(driver.getCurrentUrl().contains("promotions"));
        } catch (WebDriverException e) {
            assertTrue(DailyDealBannerLink.isDisplayed());
        }
    }


    //Main Promo Banner
    @Test
    public void testMainPromoBanner() {
        assertTrue(isElementPresent(By.id("MainPromo")));
        WebElement MainPromoBannerLink = driver.findElement(By.id("MainPromo"));
        try {
            MainPromoBannerLink.click();
            assertTrue(driver.getCurrentUrl().contains("promotions"));
        } catch (WebDriverException e) {
            assertTrue(MainPromoBannerLink.isDisplayed());
        }

    }

    // 4 tell carousel
    @Test
    public void Assert4Tell() {
        assertTrue(isElementPresent(By.id("tout1_hm_4Tell")));
    }

    // row product promo
    @Test
    public void AssertRowProductPromo() {
        assertTrue(isElementPresent(By.linkText("Cremation Urns")));
    }

    @Test
    public void testSeeMore() {
        List<WebElement> seeMoreLinks = driver.findElements(By.linkText("See more"));
        seeMoreLinks.get(0).click();
        assertTrue(driver.getTitle().contains("Today's Specials"));
    }

    @Test
    public void testViewMore() {
        List<WebElement> viewMoreLinks = driver.findElements(By.linkText("View more"));
        viewMoreLinks.get(2).click();
        assertTrue(driver.getTitle().contains("Today's Jewelry Specials"));
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
