import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ProductPagesTests {
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

    //Test Cookie trail (  availability )
    @Test
    public void assertCookieTrail() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        assertTrue(isElementPresent(By.className("breadcrumb")));
    }

    //assert product name
    @Test
    public void assertProductName() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        assertTrue(isElementPresent(By.id("ProductName")));
    }

    //assert  product price
    @Test
    public void assertProductPrice() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        assertTrue(isElementPresent(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[1]")));
    }

    //  test  Expand and Scroll trough Product Image
    @Test
    public void assertProductFancyBox() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement imageLink = driver.findElement(By.id("pageProductCarousel"));
        imageLink.click();
        Thread.sleep(100);
        WebElement scrollLink = driver.findElement(By.className("carousel-control-next-icon"));
        Thread.sleep(100);
        scrollLink.click();
        Thread.sleep(100);
        scrollLink.click();
        assertTrue(scrollLink.isDisplayed());
    }

    // Add product to cart asert Just Added page contains same product
    @Test
    public void testAddToCart() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement addToCartButton = driver.findElement(By.className("AddToCartAction"));
        addToCartButton.click();
        String justAddedProductName = driver.findElement(By.className("details")).findElement(By.className("name")).getText();
        assertTrue(productName.contains(justAddedProductName));
    }

//Test Product Review Redirection

    @Test
    public void testReviews() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement reviewStars = driver.findElement(By.className("rating"));
        reviewStars.click();
        assertTrue(driver.getCurrentUrl().contains("#Reviews"));
    }

    // Test Low Price Guaranteed pop-up oppens and is usable
    @Test
    public void testLowPriceGuaranteedPopUp() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement LowPriceGuaranteedPopUpLink = driver.findElement(By.className("low-price-guarantee"));
        LowPriceGuaranteedPopUpLink.click();
        Thread.sleep(5000);
        assertTrue(driver.findElement(By.id("lowPriceGuaranteeModal")).getAttribute("class").contains("show"));
        WebElement link = driver.findElement(By.id("merchantsLink"));
        WebElement shippingCost = driver.findElement(By.id("merchantsShipping"));
        WebElement merchantsPrice = driver.findElement(By.id("merchantsPrice"));
        WebElement yourEmail = driver.findElement(By.id("email"));
        WebElement submitButton = driver.findElement(By.id("lowPriceGuaranteeModal")).findElement(By.className("btn-standard"));
        link.clear();
        link.sendKeys("http://www.theperfecturn.com/amethyst-grecian-marble-keepsake-the-perfect-urn-vault-p-2653.html");
        shippingCost.click();
        shippingCost.clear();
        shippingCost.sendKeys("10");
        merchantsPrice.click();
        merchantsPrice.clear();
        merchantsPrice.sendKeys("10");
        yourEmail.click();
        yourEmail.clear();
        yourEmail.sendKeys("ielena.gheorghe@email.com");
        submitButton.click();
        String actualConfirmMessage = driver.findElement(By.className("alert-container")).getAttribute("innerText");
        assertTrue(actualConfirmMessage.contains("Thank you. We have received your submission and will be responding promptly. During normal business hours of 8am - 5pm Central Time on Monday - Friday your Low Price Guarantee will reviewed within 1 business day."));
    }


    // test Product Index - Click on Volume available
    @Test
    public void testProductIndex() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement insideVolume = driver.findElement(By.id("ProductIndexNav")).findElement(By.tagName("a"));
        insideVolume.click();
        assertTrue(driver.getCurrentUrl().contains("Volume"));
    }
// Test Pinterest  Share

    @Test
    public void testPinterestShare() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String handle = driver.getWindowHandle();
        List<WebElement> socialButtons = driver.findElement(By.id("Sharing")).findElements(By.tagName("a"));
        socialButtons.get(1).click();
        for (String window : driver.getWindowHandles())
            driver.switchTo().window(window);
        assertTrue(driver.getCurrentUrl().contains("pinterest"));
    }

    //Test Asert Product information includes Product Details in Product Information Body
    @Test
    public void testProductInformation() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String information = driver.findElement(By.id("Information")).getText();
        String details = driver.findElement(By.id("Information")).findElement(By.className("details")).getText();
        assertTrue(information.contains(details));
    }

    // Test video overlay
    @Test
    public void testVideoOverlay() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement productVideoLink = driver.findElement(By.className("play-video"));
        productVideoLink.click();
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        assertTrue(driver.getCurrentUrl().contains("youtube"));
        driver.close();

    }

    // Test discount options (add to cart in bundle )
    @Test
    public void testDiscountOptions() {
        driver.get("http://www.theperfecturn.com/angel-wing-sterling-silver-cremation-jewelry-engravable-p-7120.html");
        WebElement DiscountOptionsLink = driver.findElement(By.id("qd-buy-5")).findElement(By.tagName("button"));
        DiscountOptionsLink.click();
        WebElement CartImageLink = driver.findElement(By.id("ShoppingCartIcon"));
        CartImageLink.click();
        String value = driver.findElement(By.className("item")).findElement(By.className("quantity")).findElement(By.tagName("input")).getAttribute("value");
        assertEquals("5", value);
    }
///does not work correct for assert !!!

    //Select  personalization options on product
    @Test
    public void testPersonalizationButonAvailable() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement personalizeButton = driver.findElement(By.linkText("Personalize"));
        personalizeButton.click();
        assertTrue(driver.getCurrentUrl().contains("Options"));
    }

    //Order Product with options (select  personalization options on product )
    @Test
    public void testOptionalPersonalization() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement choosePlaqueStyle = driver.findElement(By.id("select-1550"));
        Select dropdownPlaqueStyle = new Select(driver.findElement(By.id("select-1550")));
        dropdownPlaqueStyle.selectByIndex(1);
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        String justAddedProductName = driver.findElement(By.className("details")).findElement(By.className("name")).getText();
        assertTrue(productName.contains(justAddedProductName));
    }

    //Order Product with mandatory options (select  mandatory option on product )
    @Test
    public void testMandatoryPersonalization() {
        driver.get("http://www.theperfecturn.com/photo-engraved-pendant-gold-rectangle-p-2720.html");
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement chooseArtWork = driver.findElement(By.id("select-1186"));
        Select dropdownchooseArtWork = new Select(driver.findElement(By.id("select-1186")));
        dropdownchooseArtWork.selectByIndex(1);
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled','disabled')", addToCartButtonDown);
        addToCartButtonDown.click();
        String justAddedProductName = driver.findElement(By.className("details")).findElement(By.className("name")).getText();
        assertTrue(productName.contains(justAddedProductName));
    }

    //Test Photo Upload on product
    @Test
    public void testPhotoUpload() {
        driver.get("http://www.theperfecturn.com/photo-engraved-pendant-gold-rectangle-p-2720.html");
        String winHandleBefore = driver.getWindowHandle();
        WebElement uploadPhotolink = driver.findElement(By.id("upload-photo-tab"));
        uploadPhotolink.click();
        WebElement chooseFile = driver.findElement(By.id("PhotoUploadButton"));
        chooseFile.click();

    }
//to be improved


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
