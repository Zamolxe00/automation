import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HomepageTests {
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
    public void testClickHome() {
        WebElement HomeLink = driver.findElement(By.linkText("Home"));
        HomeLink.click();
        assertTrue(driver.getCurrentUrl().contains("theperfecturn"));
    }


    @Test
    public void testClickHelp() {
        WebElement helpLink = driver.findElement(By.linkText("Help"));
        helpLink.click();
        assertTrue(driver.getCurrentUrl().contains("help"));
    }

    @Test
    public void testClickPromotions() {
        WebElement PromotionsLink = driver.findElement(By.linkText("Promotions"));
        PromotionsLink.click();
        assertTrue(driver.getCurrentUrl().contains("promotions"));
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
        WebElement SeeMoreLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[3]/a"));
        SeeMoreLink.click();
        assertTrue(driver.getCurrentUrl().contains("specials"));

    }

    @Test
    public void testViewMore() {
        WebElement ViewMoreLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[3]/div[1]/a"));
        ViewMoreLink.click();
        assertTrue(driver.getCurrentUrl().contains("jewelry"));

    }


    // Product Page Test
    // test cookie trail

    @Test
    public void assertCookieTrail() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        assertTrue(isElementPresent(By.xpath("//*[@id=\"MainContainer\"]/ol")));

    }

// test product details
//test product name

    @Test
    public void assertProductName() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        assertTrue(isElementPresent(By.id("ProductName")));
    }


    //test product price


    @Test
    public void assertProductPrice() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        assertTrue(isElementPresent(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[1]")));
    }


// test product main image


    @Test
    public void assertProductMainImage() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        assertTrue(isElementPresent(By.xpath("//*[@id=\"pageProductCarousel\"]/div[2]/div[8]/img")));
    }

//  test Product Expand  Image

    @Test
    public void assertProductFancyBox() throws InterruptedException {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement imageLink = (driver.findElement(By.xpath("//*[@id=\"pageProductCarousel\"]/div[2]/div[1]/img")));
        imageLink.click();
        Thread.sleep(10000);
        assertTrue(driver.findElement(By.className("carousel-control-next-icon")).isDisplayed());
    }

//test Product Scroll Image

    @Test
    public void assertProductScrollImage() throws InterruptedException {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement imageLink = (driver.findElement(By.xpath("//*[@id=\"pageProductCarousel\"]/div[2]/div[1]/img")));
        imageLink.click();
        Thread.sleep(10000);
        WebElement scrollLink = driver.findElement(By.className("carousel-control-next-icon"));
        scrollLink.click();
        assertTrue(driver.findElement(By.className("img-fluid")).isDisplayed());
    }

    // test Add to cart button
    @Test
    public void testAddToCart() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement addToCartButtonLink = driver.findElement(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[4]/button"));
        addToCartButtonLink.click();
        assertTrue(driver.getCurrentUrl().contains("product"));

    }


    // Test Product Review
    @Test
    public void testProductReview() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        assertTrue(isElementPresent(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[2]")));
    }


    // Test Product Review

    @Test
    public void testProductReviewLink() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement reviewLink = driver.findElement(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[2]/a"));
        reviewLink.click();
    }

// to assert that goes to review section


    // test it oppens pop up low price guarantee
    @Test
    public void testLowPriceGuaranteedPopUp() throws InterruptedException {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement LowPriceGuaranteedPopUpLink = driver.findElement(By.className("low-price-guarantee"));
        LowPriceGuaranteedPopUpLink.click();
        Thread.sleep(5000);
        assertTrue(driver.findElement(By.id("lowPriceGuaranteeModal")).getAttribute("class").contains("show"));
    }


    // test Product Index available
    @Test
    public void testProductIndex() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        assertTrue(isElementPresent(By.id("ProductIndexNav")));
    }


    //   Test Product Volume
    @Test
    public void testProductVolume() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        assertTrue(isElementPresent(By.id("Volume")));

    }

// Test Pinterest  Share

    @Test
    public void testPinterestShare() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        String handle = driver.getWindowHandle();
        List<WebElement> socialButtons = driver.findElement(By.id("Sharing")).findElements(By.tagName("a"));
        socialButtons.get(0).click();
        for (String window : driver.getWindowHandles())
            driver.switchTo().window(window);
        assertTrue(driver.findElement(By.id("content")).getAttribute("class").contains("pinterest"));
    }


    //Test Product information
    @Test
    public void testProductInformation() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        assertTrue(isElementPresent(By.id("Information")));
    }


    // Test video overlay
    @Test
    public void testVideoOverlay() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement productVideoLink = driver.findElement(By.id("//*[@id=\"pageProductCarousel\"]/a/span"));
        productVideoLink.click();
        assertTrue(driver.getCurrentUrl().contains("youtube"));
    }

// Test discount options (bundle shop)

    @Test
    public void testDiscountOptions() {
        driver.get("http://www.theperfecturn.com/angel-wing-sterling-silver-cremation-jewelry-engravable-p-7120.html");
        WebElement DiscountOptionsLink = driver.findElement(By.id("qd-buy-5")).findElement(By.tagName("button"));
        DiscountOptionsLink.click();
        assertTrue(driver.getCurrentUrl().contains("product"));
    }

    //Product with options (confirm personalization options available )
    @Test
    public void testPersonalizationButonAvailable() {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement productOptions = driver.findElement(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[4]/a"));
        productOptions.click();
        assertTrue(isElementPresent(By.xpath("//*[@id=\"Options\"]/div[1]")));
    }

    //Order Product with options (select  personalization options on product )
    @Test
    public void testOptionalPersonalization() {
        driver.get("http://www.theperfecturn.com/dog-bone-photo-engraved-pendant-silver-p-2304.html");
        Select dropdown = new Select(driver.findElement(By.id("select-1023")));
        dropdown.selectByVisibleText("Block Font (+$9.95)");
        WebElement addToCartButtonDown = driver.findElement(By.xpath("//*[@id=\"Options\"]/div[2]/button"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Options")));
        addToCartButtonDown.click();
        assertTrue(isElementPresent(By.className("added-item")));
    }


    //Order Product with mandatory options (select  mandatory option on product )
    //this includes checking of the SC
    @Test
    public void testMandatoryPersonalization() {
        driver.get("http://www.theperfecturn.com/photo-engraved-pendant-gold-rectangle-p-2720.html");
        WebElement selectDropDown = driver.findElement(By.id("Options")).findElement(By.className("marked")).findElement(By.tagName("select"));
        Select dropdown = new Select(driver.findElement(By.id("Options")).findElement(By.className("marked")).findElement(By.tagName("select")));
        dropdown.selectByIndex(1);
        selectDropDown.click();
        selectDropDown.click();
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled','disabled')", addToCartButtonDown);
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonDown));
        addToCartButtonDown.click();
        assertTrue(isElementPresent(By.className("added-item")));
        WebElement CartImageLink = driver.findElement(By.id("ShoppingCartIcon"));
        CartImageLink.click();
        assertTrue(isElementPresent(By.linkText("Photo Engraved Pendant - Gold Rectangle")));
    }

    //photo upload
//
    @Test
    public void testPhotoUpload() {
        driver.get("http://www.theperfecturn.com/photo-engraved-pendant-gold-rectangle-p-2720.html");
        WebElement uploadPhotolink = driver.findElement(By.id("upload-photo-tab"));
        uploadPhotolink.click();
        WebElement chooseFile = driver.findElement(By.id("PhotoUploadButton"));
        chooseFile.click();
        //       chooseFile.sendKeys("C:\Users\VirginiaElena\Desktop.gif");

    }

    //Test increase product quantity  in shopping cart.
    @Test
    public void testProductQuantityIncrease() throws InterruptedException {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement addToCartButtonLink = driver.findElement(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[4]/button"));
        addToCartButtonLink.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement increaseButton = driver.findElement(By.className("plus"));
        WebDriverWait wait = new WebDriverWait(driver, 100);
        increaseButton.click();
//        String value = driver.findElement(By.className("form-control")).getAttribute("value");
//        assertEquals("2", value);
    }


//Test decrease  product quantity  in shopping cart.

    @Test
    public void testProductQuantityDecrease() throws InterruptedException {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement addToCartButtonLink = driver.findElement(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[4]/button"));
        addToCartButtonLink.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement decreaseButton = driver.findElement(By.className("minus"));
        WebDriverWait wait = new WebDriverWait(driver, 100);
        decreaseButton.click();
//        String value = driver.findElement(By.className("form-control")).getAttribute("value");
//        assertEquals("1", value);
    }


    //Test remove product from shopping cart.
//does not work :(
    @Test
    public  void testRemoveItem() throws InterruptedException {
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Homepage\"]/div[3]/div[1]/div[2]/a/div[1]/img"));
        productLink.click();
        WebElement addToCartButtonLink = driver.findElement(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[4]/button"));
        addToCartButtonLink.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        WebDriverWait wait = new WebDriverWait(driver, 100);
        viewShoppingCart.click();
        Thread.sleep(1000);
        WebElement removeButton = driver.findElement(By.className("remove"));
        Thread.sleep(1000);
        removeButton.click();
        WebElement actualConfirmMessage = driver.findElement(By.className("alert-success"));
        Alert popUp = driver.switchTo().alert();
        popUp.accept();
        assertEquals(" Item successfully removed.",actualConfirmMessage.getText());

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
