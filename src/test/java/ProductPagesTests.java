import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ProductPagesTests {
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
        assertTrue(driver.getCurrentUrl().contains("pinterest"));
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
