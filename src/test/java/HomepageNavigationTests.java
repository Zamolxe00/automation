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


//Test header
//Test Click Help Link and Header Logo
//This process describes following steps:
//-> go to Homepage-> search for Help link in header  -> click on Help-> assert that the link oppened is the Help page
//-> Search for Header Logo ->Click on Logo-> assert that the page oppened is the Homepage

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
//This process describes following steps:
// -> go to Homepage-> search for Promotions link in header  -> click on Promotions > assert that the link oppened is the Promotions  page
//-> Search for Home link in header ->Click on Home > assert that the page oppened is the Homepage

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
//This process describes following steps:
// -> go to Homepage-> search for Contact us  link in header -> click on Contact us
//-> assert that the link oppened is the Contact Us  page

    @Test
    public void testClickContactUs() {
        WebElement ContactUsLink = driver.findElement(By.linkText("Contact Us"));
        ContactUsLink.click();
        assertTrue(driver.getCurrentUrl().contains("contact-us"));
    }

//Test Click My Profile  Link goes to correct page
//This process describes following steps:
//-> go to Homepage-> search for My Profile link in header -> click on My profile
//-> assert that the link oppened is the Login page anf that the page title is Login

    @Test
    public void testClickMyProfile() {
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        assertTrue(driver.getTitle().equalsIgnoreCase("login"));
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

//Test Click Cart icon  goes to correct page
//This process describes following steps:
//-> go to Homepage-> search for Cart icon  in header -> click on cart icon
//-> assert that the link oppened is the Shopping Cart pag

    @Test
    public void testClickCartIcon() {
        WebElement CartImageLink = driver.findElement(By.id("ShoppingCartIcon"));
        CartImageLink.click();
        assertTrue(driver.getCurrentUrl().contains("cart"));
    }

//Test Click Cart Link  goes to correct page
//This process describes following steps:
// -> go to Homepage-> search for “x items” in your cart link in header -> click on “x items” link
//-> assert that the link oppened is the Shopping Cart page

    @Test
    public void testClickCartLink() {
        WebElement CartItemLink = driver.findElement(By.id("ShoppingCart"));
        CartItemLink.click();
        assertTrue(driver.getCurrentUrl().contains("cart"));
    }

//Test Header Live Chat
//This process describes following steps:
// -> go to Homepage-> search for “Live Chat”   in header -> click on Live Chat
//-> confirm that live chat box oppens

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
//This process describes following steps:
// -> go to Homepage-> search for “Bizrate icon ”   in header -> click on Bizrate
//-> confirm that new page oppen is the Bizrate page

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

//Test Footer
//Test footer links (user not logged)
//This process describes following steps:
// -> go to Homepage-> search for Address Book  in footer -> click Address Book
//-> confirm that redirects to Login Page -> Search for Site Map in footer -> Click on Site Map
//-> Confirm that sitemap page oppens

    @Test
    public void testFooterLinks() {
        WebElement AddressBookLink = driver.findElement(By.linkText("Address Book"));
        AddressBookLink.click();
        assertTrue(driver.findElement(By.className("main-banner")).getText().contains("Welcome"));
        WebElement SiteMapLink = driver.findElement(By.partialLinkText("Map"));
        SiteMapLink.click();
        assertTrue(driver.getCurrentUrl().contains("sitemap"));
    }

//Test Footer Social Media Badges ( Facebook))️
//This process describes following steps:
//-> go to Homepage-> search for “Facebook  icon ”   in footer -> click on Facebook
//-> confirm that new page oppen is the Perfect Memorials Facebook page

    @Test
    public void testFacebookRedirection() {
        WebElement FacebookRedirectionLink = driver.findElement(By.partialLinkText("Facebook"));
        FacebookRedirectionLink.click();
        assertTrue(driver.getCurrentUrl().contains("facebook.com/PerfectMemorials"));
    }

//Test Footer Bussiness partners (TrustWave)
//This process describes following steps:
//-> go to Homepage-> search for “TrustWave icon ”   in footer -> click on TrustWave icon
//-> confirm that trustWave  box oppens -> closes the tab

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

// Test Footer Payment Methods (availability)
//This process describes following steps:
//-> go to Homepage-> search for Payment Methods   in footer -> confirm that Payment Methods are displayed in Homepage footer

    @Test
    public void testPaymentMethodsBadgeAvailable() {
        WebElement PaymentMethodsBadgeAvailableLink = driver.findElement(By.className("payment-methods"));
        assertTrue(PaymentMethodsBadgeAvailableLink.isDisplayed());
    }

//Content
//Test  4tell:1 Solution Implemented
//This process describes following steps:
// -> go to Homepage-> search 4Tell Carousel -> confirm that the 4Tell carousel is  displayed in Homepage

    @Test
    public void Assert4Tell() {
        assertTrue(isElementPresent(By.id("tout1_hm_4Tell")));
    }

//Test  Product Promo
//Test Select See More Option
// This process describes following steps:
//-> go to Homepage-> search for “See More” list   -> click on first “See more” option on Homepage
//-> assert that the page oppened is the Today's Specials page

    @Test
    public void testSeeMore() {
        List<WebElement> seeMoreLinks = driver.findElements(By.linkText("See more"));
        seeMoreLinks.get(0).click();
            assertTrue(driver.getTitle().contains("Today's Specials"));
    }

//Test Select View More Option
//This process describes following steps:
//-> go to Homepage-> search for “View More ” list   -> click on third “View more” option on Homepage
//-> assert that the page oppened is the Today's Jewelry Specials page

    @Test
    public void testViewMore() {
        List<WebElement> viewMoreLinks = driver.findElements(By.linkText("View more"));
        viewMoreLinks.get(2).click();
        assertTrue(driver.getTitle().contains("Today's Jewelry Specials"));
    }

//Test Select Click here for Bio Urns  (includes link confirmation)
//This process describes following steps:
//-> go to Homepage-> search for Bio Urns banners  list   -> click on first Bio Urns  option on Homepage
//-> assert that the page oppened is the Biodegradable EcoUrn Urns page

    @Test
    public void testBioUrns() {
    List<WebElement> bioUrnsBanners = driver.findElements(By.className("banner"));
       bioUrnsBanners.get(0).click();
    assertTrue(driver.getTitle().contains("Biodegradable EcoUrn Urns"));}


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
