import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestProductPages {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger log = Logger.getLogger(TestProductPages.class);

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

//Test Product Details
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product
//-> assert that the product displays Breadcrumb, Product Name, Product Price

    @Test
    public void assertProductDetails() {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Confirm following elements are displayed on page :Breadcrumb, Product Name, Product Price");
        assertTrue(isElementPresent(By.className("breadcrumb")));
        assertTrue(isElementPresent(By.id("ProductName")));
        assertTrue(isElementPresent(By.className("product-price")));
    }

//Test Product Image
//This process describes following steps:
// -> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> click on Prduct image -> Image  Expands ->
//Scroll trough Product Image -> assert that scroll is available

    @Test
    public void testProductImageOptions() throws InterruptedException {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Product Image, Scroll in Carousel");
        WebElement imageLink = driver.findElement(By.id("pageProductCarousel"));
        imageLink.click();
        Thread.sleep(100);
        WebElement scrollLink = driver.findElement(By.className("carousel-control-next-icon"));
        Thread.sleep(100);
        scrollLink.click();
        Thread.sleep(100);
        scrollLink.click();
        log.info("Confirm Image Carousel is Displayed and Usable");
        assertTrue(scrollLink.isDisplayed());
    }

//Test add to cart product
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> identify the add to cart product
// -> click on add to cart -> asert Just Added page contains same product that was added in previous page
    @Test
    public void testAddToCart() {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Register product name ");
        String productName = driver.findElement(By.id("ProductName")).getText();
        log.info("Click on Add to Cart ");
        WebElement addToCartButton = driver.findElement(By.className("AddToCartAction"));
        addToCartButton.click();
        log.info("Confirm That In Just Added Page Product Name is same as on Product Page ");
        String justAddedProductName = driver.findElement(By.className("details")).findElement(By.className("name")).getText();
        assertTrue(productName.contains(justAddedProductName));
    }

//Test Click on product  Review
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> identify ratings link
// -> click on “x customer reviews”-> asert the curent URL reads "#Reviews”meaning that the cursor jumped to Rating section on product page

    @Test
    public void testReviews() {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on x Customer Reviews");
        WebElement reviewStars = driver.findElement(By.className("rating"));
        reviewStars.click();
        log.info("Confirm  #Reviews is displayed on Url meaning that the cursor jumped to Rating section on product page");
        assertTrue(driver.getCurrentUrl().contains("#Reviews"));
    }

//Test Low Price Guaranteed pop-up submit request✔️
//This process describes following steps:
// -> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> identify Low Price Link
// -> Click on Low Price Link -> confirm that pop-up oppens
//-> add concurrent product link -> add concurrent product shipping cost -> add merchants price -> add user e-mail -> click on submit button -> confirm registration message display :
// "Thank you. We have received your submission and will be responding promptly. During normal business hours of 8am - 5pm Central Time on Monday - Friday your Low Price Guarantee will reviewed within 1 business day."
    @Test
    public void testLowPriceGuaranteedPopUp() throws InterruptedException {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement LowPriceGuaranteedPopUpLink = driver.findElement(By.className("low-price-guarantee"));
        log.info("Click on Low Price quarantee");
        LowPriceGuaranteedPopUpLink.click();
        Thread.sleep(5000);
        assertTrue(driver.findElement(By.id("lowPriceGuaranteeModal")).getAttribute("class").contains("show"));
        WebElement link = driver.findElement(By.id("merchantsLink"));
        WebElement shippingCost = driver.findElement(By.id("merchantsShipping"));
        WebElement merchantsPrice = driver.findElement(By.id("merchantsPrice"));
        WebElement yourEmail = driver.findElement(By.id("email"));
        WebElement submitButton = driver.findElement(By.id("lowPriceGuaranteeModal")).findElement(By.className("btn-standard"));
        log.info(" Add concurrent product link");
        link.clear();
        link.sendKeys("http://www.theperfecturn.com/amethyst-grecian-marble-keepsake-the-perfect-urn-vault-p-2653.html");
        log.info("Shipping Cost 10");
        shippingCost.click();
        shippingCost.clear();
        shippingCost.sendKeys("10");
        log.info("Mercant Price 10");
        merchantsPrice.click();
        merchantsPrice.clear();
        merchantsPrice.sendKeys("10");
        log.info(" Your e-mail ielena.gheorghe@email.com");
        yourEmail.click();
        yourEmail.clear();
        yourEmail.sendKeys("ielena.gheorghe@email.com");
        log.info(" Click on Submit Button ");
        submitButton.click();
        String actualConfirmMessage = driver.findElement(By.className("alert-container")).getAttribute("innerText");
        log.info("Confirmation message is displayed in browser:Thank you. We have received your submission and will be responding promptly. During normal business hours of 8am - 5pm Central Time on Monday - Friday your Low Price Guarantee will reviewed within 1 business day." );
        assertTrue(actualConfirmMessage.contains("Thank you. We have received your submission and will be responding promptly. During normal business hours of 8am - 5pm Central Time on Monday - Friday your Low Price Guarantee will reviewed within 1 business day."));
    }


//Test Product Index -Click on Inside Volume Option
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> identify Volume option in Product Index
// -> Click on Volume -> confirm the curent URL reads "#Volume” meaning that the cursor jumped to volume section on product page
    @Test
    public void testProductIndex() {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Inside Volume from Product Index");
        WebElement insideVolume = driver.findElement(By.id("ProductIndexNav")).findElement(By.tagName("a"));
        insideVolume.click();
        log.info("Confirm the curent URL reads #Volume meaning that the cursor jumped to volume section on product page" );
        assertTrue(driver.getCurrentUrl().contains("Volume"));
    }

 //Test Pinterest Share
//This process describes following steps: -> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category
// -> click on product -> identify Pinterest button -> click on Pinterest button -> New window oppens -> Confirm New window loads Pinterest page

    @Test
    public void testPinterestShare() {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String handle = driver.getWindowHandle();
        log.info("Identify The Pinterest save Button and click");
        List<WebElement> socialButtons = driver.findElement(By.id("Sharing")).findElements(By.tagName("a"));
        socialButtons.get(1).click();
        for (String window : driver.getWindowHandles())
            driver.switchTo().window(window);
        log.info("Confirm Url oppened is Pinterest Page ");
        assertTrue(driver.getCurrentUrl().contains("pinterest"));
    }


//Product with video overlay
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product ->  identify video link
// -> click on video link -> confirm new tab oppens -> confirm new tab is Youtube ->  Test video overlay
    @Test
    public void testVideoOverlay() {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click Product Image Overlay ");
        WebElement productVideoLink = driver.findElement(By.className("play-video"));
        productVideoLink.click();
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        log.info("Confirm Youtube product description page is oppened in New Tab");
        assertTrue(driver.getCurrentUrl().contains("youtube"));
        driver.close();
    }

//Product with Discount Options (add to cart in bundle)
//This process describes following steps:  go to PM8120 -> identify Discount options(5) -> Click on Add to cart -> Identify cart Icon in Header -> Click on Cart Icon
// -> Confirm quantity value is same (5) in SC
    @Test
    public void testDiscountOptionsShopInBundle() {
        log.info("Open PM8120 product page");
        driver.get("http://www.theperfecturn.com/angel-wing-sterling-silver-cremation-jewelry-engravable-p-7120.html");
        log.info("Identfy the 5+ Quantity Add to Cart Button and Click ");
        WebElement DiscountOptionsLink = driver.findElement(By.id("qd-buy-5")).findElement(By.tagName("button"));
        DiscountOptionsLink.click();
        log.info("Click on Cart Image from Header ");
        WebElement CartImageLink = driver.findElement(By.id("ShoppingCartIcon"));
        CartImageLink.click();
        log.info("Confirm quantity value is same (5) in SC ");
        String value = driver.findElement(By.className("item")).findElement(By.className("quantity")).findElement(By.tagName("input")).getAttribute("value");
        assertEquals("5", value);
    }

//Select  personalization options on product ️
//This process describes following steps: -> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product
// -> Identify the Personalize Button -> Click on Personalize button -> confirm the curent URL reads "#Options” meaning that the cursor jumped to Product Options section on product page
    @Test
    public void testPersonalizationButonAvailable() {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Personalize Button");
        WebElement personalizeButton = driver.findElement(By.linkText("Personalize"));
        personalizeButton.click();
        log.info("Confirm the curent URL reads #Options meaning that the cursor jumped to Product Options section on product page" );
        assertTrue(driver.getCurrentUrl().contains("Options"));
    }

 //Order Product with Personalization button
//This process describes following steps:  go to PM8120 -> Identify the Personalize Button -> Click on Personalize button -> Select One option from drop list -> Click Add to cart
// -> confirm Just Added page contains same product that was added in previous page
    @Test
    public void testUseOptionalPersonalization() {
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        log.info("Select first Option from Choose Plaque Style :Yes, Plaque ...");
        List<WebElement> optionsDropdowns = driver.findElement(By.id("Options")).findElements(By.tagName("select"));
        WebElement choosePlaqueStyle = optionsDropdowns.get(0);
        Select dropdownPlaqueStyle = new Select(choosePlaqueStyle);
        dropdownPlaqueStyle.selectByIndex(1);
        log.info("Select first  Option from Choose Art Work  :Rose...");
        optionsDropdowns = driver.findElement(By.id("Options")).findElements(By.tagName("select"));
        Select dropdownchooseArtwork = new Select(optionsDropdowns.get(1));
        dropdownchooseArtwork.selectByIndex(1);
        log.info("Click on Add To Cart");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        String justAddedProductName = driver.findElement(By.className("details")).findElement(By.className("name")).getText();
        log.info("Confirm that Same Product is displayed");
        assertTrue(productName.contains(justAddedProductName));
    }

  //Order Product with mandatory options
  //This process describes following steps:  go to PM8120 -> Identify the Personalize Button -> Click on Personalize button -> Select One option from drop list -> Click Add to cart
  // -> confirm Just Added page contains same product that was added in previous page
    @Test
    public void testMandatoryPersonalization() {
        log.info("Go to PM3720");
        driver.get("http://www.theperfecturn.com/photo-engraved-pendant-gold-rectangle-p-2720.html");
        log.info("Select first  Option from Choose Art Work  : Traditional Cross -mandatory option ");
        String productName = driver.findElement(By.id("ProductName")).getText();
        List<WebElement> optionsDropdowns = driver.findElement(By.id("Options")).findElements(By.tagName("select"));
        WebElement chooseArtWork = optionsDropdowns.get(2);
        Select dropdownPlaqueStyle = new Select(chooseArtWork);
        dropdownPlaqueStyle.selectByIndex(1);
        optionsDropdowns = driver.findElement(By.id("Options")).findElements(By.tagName("select"));
        Select dropdownchooseArtwork = new Select(optionsDropdowns.get(1));
        dropdownchooseArtwork.selectByIndex(1);
        log.info("Click on Add to Cart ");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled','disabled')", addToCartButtonDown);
        addToCartButtonDown.click();
        String justAddedProductName = driver.findElement(By.className("details")).findElement(By.className("name")).getText();
        log.info("Confirm that Same Product is displayed");
        assertTrue(productName.contains(justAddedProductName));
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
