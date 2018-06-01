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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HomepageNavigationTests {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setUpPath() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
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


    //Test header
// Test Click Help Link and Header Logo
    @Test
    public void testClickHelpAndLogo() {
        WebElement helpLink = driver.findElement(By.linkText("Help"));
        helpLink.click();
        assertTrue(driver.getCurrentUrl().contains("help"));
        WebElement logo = driver.findElement(By.className("logo-area"));
        logo.click();
        assertTrue(driver.getCurrentUrl().contains("theperfecturn"));
    }

    //Test Click Promotions Link and Home Link
    @Test
    public void testClickPromotionsAndHome() {
        WebElement PromotionsLink = driver.findElement(By.linkText("Promotions"));
        PromotionsLink.click();
        assertTrue(driver.getCurrentUrl().contains("promotions"));
        WebElement HomeLink = driver.findElement(By.linkText("Home"));
        HomeLink.click();
        assertTrue(driver.getCurrentUrl().contains("theperfecturn"));
    }

    //Test Click Contact us Link goes to correct page
    @Test
    public void testClickContactUs() {
        WebElement ContactUsLink = driver.findElement(By.linkText("Contact Us"));
        ContactUsLink.click();
        assertTrue(driver.getCurrentUrl().contains("contact-us"));
    }

    //Test Click My Profile  Link goes to correct page
    @Test
    public void testClickMyProfile() {
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        assertTrue(driver.getTitle().equalsIgnoreCase("login"));
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    //Test Click Cart image  goes to correct page
    @Test
    public void testClickCartImage() {
        WebElement CartImageLink = driver.findElement(By.id("ShoppingCartIcon"));
        CartImageLink.click();
        assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    //Test Click Cart Link  goes to correct page
    @Test
    public void testClickCartItem() {
        WebElement CartItemLink = driver.findElement(By.id("ShoppingCart"));
        CartItemLink.click();
        assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    //Test Header Oppens Live Chat
    @Test
    public void testHeaderLiveChatClick() throws InterruptedException {
        WebElement LiveChatLink = driver.findElement(By.className("live-chat-support-banner"));
        String winHandleBefore = driver.getWindowHandle();
        LiveChatLink.click();
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
        assertTrue(driver.getCurrentUrl().contains("chat"));
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    //Test Header Badges (Bizrate Redirection)
    @Test
    public void testBizrateRedirection() throws InterruptedException {
        WebElement likeButton = driver.findElement(By.id("header-bizrate-second-link"));
        String winHandleBefore = driver.getWindowHandle();
        likeButton.click();
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
        assertTrue(driver.getCurrentUrl().contains("bizratesurveys"));
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    // Test footer links (redirection correct)
//Catalog request
    @Test
    public void testCatalogRequest() {
        WebElement CatalogRequestLink = driver.findElement(By.partialLinkText("Catalog"));
        CatalogRequestLink.click();
        assertTrue(driver.getCurrentUrl().contains("catalog"));
    }

    // Address Book
    @Test
    public void testAddressBook() {
        WebElement AddressBookLink = driver.findElement(By.linkText("Address Book"));
        AddressBookLink.click();
        assertTrue(driver.findElement(By.className("main-banner")).getText().contains("Welcome"));
    }
//SiteMap

    @Test
    public void testSiteMap() {
        WebElement SiteMapLink = driver.findElement(By.partialLinkText("Map"));
        SiteMapLink.click();
        assertTrue(driver.getCurrentUrl().contains("sitemap"));

    }

    //  Test Footer Social Media Badges ( Facebook)
    @Test
    public void testFacebookRedirection() {
        WebElement FacebookRedirectionLink = driver.findElement(By.partialLinkText("Facebook"));
        FacebookRedirectionLink.click();
        assertTrue(driver.getCurrentUrl().contains("facebook"));

    }

    // Test Footer Bussiness partners (TrustWave)
    @Test
    public void testTrustWaveRedirection() throws InterruptedException {
        WebElement TrustWaveRedirection = driver.findElement(By.id("trustwaveSealImage"));
        WebDriverWait wait = new WebDriverWait(driver, 100);
        String winHandleBefore = driver.getWindowHandle();
        TrustWaveRedirection.click();
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
        assertTrue(driver.getCurrentUrl().contains("sealserver.trustwave.com"));
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    @Test
    public void testPaymentMethodsBadgeAvailable() {
        WebElement PaymentMethodsBadgeAvailableLink = driver.findElement(By.className("payment-methods"));
        assertTrue(PaymentMethodsBadgeAvailableLink.isDisplayed());

    }

    //Content
// Test  4tell
// Solution Implemented
    @Test
    public void Assert4Tell() {
        assertTrue(isElementPresent(By.id("tout1_hm_4Tell")));
    }

// Test  Product Promo
// Test Select See  More Option

    @Test
    public void testSeeMore() {
        List<WebElement> seeMoreLinks = driver.findElements(By.linkText("See more"));
        seeMoreLinks.get(0).click();
        assertTrue(driver.getTitle().contains("Today's Specials"));
    }

    // Test Select View More Option
    @Test
    public void testViewMore() {
        List<WebElement> viewMoreLinks = driver.findElements(By.linkText("View more"));
        viewMoreLinks.get(2).click();
        assertTrue(driver.getTitle().contains("Today's Jewelry Specials"));
    }

//Test Select Click here for Bio Urns
//    @Test
    //   public void testBioUrns() {
    //   WebElement bioDegradableUrnsHere = driver.findElement(By.className("banner")).findElement(By.cssSelector(.));
//}

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
