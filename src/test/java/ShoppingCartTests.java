import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ShoppingCartTests {
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


    // Test Product Name in SC is same as product name on product page - not working
    @Test
    public void TestProductName() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        String productNameInSC = driver.findElement(By.className("name")).findElement(By.tagName("a")).getText();
        assertTrue(productName.contains(productNameInSC));
    }

    //Test click on product in shopping cart to go to product page - not working
    @Test
    public void TestRedirectionFromSC() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement productlinkInSC = driver.findElement(By.className("name"));
        productlinkInSC.click();
        String productNameRedirection = driver.findElement(By.id("ProductName")).getText();
        assertTrue(productName.contains(productNameRedirection));
    }


    //Test click on product image in shopping cart to go to product page.
    @Test
    public void TestProductImageInCart() {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement productImageInSC = driver.findElement(By.className("image"));
        productImageInSC.click();
        String productNameRedirection = driver.findElement(By.id("ProductName")).getText();
        assertTrue(productName.equals(productNameRedirection));

    }

    //Test increase product quantity  in shopping cart - not working
    @Test
    public void testProductQuantityIncrease() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement addToCartButtonLink = driver.findElement(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[4]/button"));
        addToCartButtonLink.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement increaseButton = driver.findElement(By.className("plus"));
        increaseButton.click();
        //WebElement valueElement = driver.findElement(By.className("quantity")).findElement(By.tagName("input"));
        //assertEquals("2", valueElement.getAttribute("value"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("noty_body"))));
        WebElement actualConfirmMessage = driver.findElement(By.className("noty_body"));
        assertTrue(actualConfirmMessage.getText().contains("Quantity successfully updated."));
    }

    private void assertEquals(String s, String value) {
    }

    //Test decrease  product quantity  in shopping cart.- not working
    @Test
    public void testProductQuantityDecrease() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement increaseButton = driver.findElement(By.className("plus"));
        increaseButton.click();
        WebElement decreaseButton = driver.findElement(By.className("minus"));
        decreaseButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("noty_body"))));
        WebElement actualConfirmMessage = driver.findElement(By.className("noty_body"));
        assertTrue(actualConfirmMessage.getText().contains("Quantity successfully updated."));
    }

    //Test remove product from shopping cart.
    @Test
    public void testRemoveItem() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement removeButton = driver.findElement(By.className("remove"));
        removeButton.click();
        WebElement actualConfirmMessage = driver.findElement(By.className("alert-success"));
        assertTrue(actualConfirmMessage.getText().contains("Item successfully removed"));
    }


    //Test Edit Options in SC - does not click on dropdownPlaqueStyle
    @Test
    public void testChangeOptions() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        List<WebElement> optionsDropdowns = driver.findElement(By.id("Options")).findElements(By.tagName("select"));
        WebElement choosePlaqueStyle = optionsDropdowns.get(0);
        Select dropdownPlaqueStyle = new Select(choosePlaqueStyle);
        dropdownPlaqueStyle.selectByIndex(1);
        optionsDropdowns = driver.findElement(By.id("Options")).findElements(By.tagName("select"));
        Select dropdownchooseArtwork = new Select(optionsDropdowns.get(1));
        dropdownchooseArtwork.selectByIndex(1);
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement editOptions = driver.findElement(By.className("edit-options"));
        editOptions.click();
        optionsDropdowns = driver.findElement(By.id("EditOptionsModal")).findElements(By.tagName("select"));
        dropdownchooseArtwork = new Select(optionsDropdowns.get(1));
        dropdownchooseArtwork.selectByIndex(2);
        WebElement saveButton = driver.findElement(By.id("EditOptionsModal")).findElement(By.className("btn-primary"));
        saveButton.click();
        String actualConfirmMessage = driver.findElement(By.className("alert-container")).getAttribute("innerText");
        assertTrue(actualConfirmMessage.contains("Item options were successfully updated."));
    }

    //Test  changing Delivery options-- get 0 goes to three days delivery
// bug, unauthorized when not logged in
    @Test
    public void testChangeDelivery() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement choosePlaqueStyle = driver.findElement(By.id("select-1550"));
        Select dropdownPlaqueStyle = new Select(driver.findElement(By.id("select-1550")));
        dropdownPlaqueStyle.selectByIndex(1);
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        List<WebElement> deliveryOptions = driver.findElements(By.className("delivery-options"));
        deliveryOptions.get(0).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("noty_body"))));
        String actualConfirmMessage = driver.findElement(By.className("noty_body")).getAttribute("innerText");
        assertTrue(actualConfirmMessage.contains("The shipping method successfully updated to `Free Shipping`"));
    }

    // Test Apply cupon  when user is not logged in
    @Test
    public void testAddCouponNotLoggedIn() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement choosePlaqueStyle = driver.findElement(By.id("select-1550"));
        Select dropdownPlaqueStyle = new Select(driver.findElement(By.id("select-1550")));
        dropdownPlaqueStyle.selectByIndex(1);
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement cuponSpace = driver.findElement(By.id("CouponInput"));
        cuponSpace.click();
        cuponSpace.clear();
        cuponSpace.sendKeys("JORJTEST");
        WebElement applyButton = driver.findElement(By.id("CouponSubmitButton"));
        applyButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("noty_body")));
        String actualConfirmMessage = driver.findElement(By.className("noty_body")).getAttribute("innerText");
        assertTrue(actualConfirmMessage.contains("Unauthorized action. Please log in."));
    }


    //Test Apply cupon  when user is logged in
    @Test
    public void testAddCupon() throws InterruptedException {
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        WebElement emailInput = driver.findElement(By.id("inputEmail3"));
        WebElement passwordInput = driver.findElement(By.id("inputPassword3"));
        WebElement secureLogin = driver.findElement(By.className("mb-4")).findElement(By.tagName("button"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("ielena.gheorghe@dovona7.com");
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("Ciresica123");
        secureLogin.click();
        driver.get("http://www.theperfecturn.com");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement choosePlaqueStyle = driver.findElement(By.id("select-1550"));
        Select dropdownPlaqueStyle = new Select(driver.findElement(By.id("select-1550")));
        dropdownPlaqueStyle.selectByIndex(1);
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement cuponSpace = driver.findElement(By.id("CouponInput"));
        cuponSpace.click();
        cuponSpace.clear();
        cuponSpace.sendKeys("JORJTEST");
        WebElement applyButton = driver.findElement(By.id("CouponSubmitButton"));
        applyButton.click();
        String actualConfirmMessage = driver.findElement(By.className("noty_body")).getAttribute("innerText");
        assertTrue(actualConfirmMessage.contains("The coupon has been successfully applied."));
    }

//Test Checkout option  to continue Checkout process result user not logged

    @Test
    public void SCcheckoutOption() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement choosePlaqueStyle = driver.findElement(By.id("select-1550"));
        Select dropdownPlaqueStyle = new Select(driver.findElement(By.id("select-1550")));
        dropdownPlaqueStyle.selectByIndex(1);
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement checkoutButton = driver.findElement(By.linkText("Checkout"));
        checkoutButton.click();
        assertTrue(driver.getCurrentUrl().contains("login"));
    }


    // Test Click Amazon for Amazon pay result - to be modified when implemented
    @Test
    public void amazonCheckout() throws InterruptedException {
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        String productName = driver.findElement(By.id("ProductName")).getText();
        WebElement choosePlaqueStyle = driver.findElement(By.id("select-1550"));
        Select dropdownPlaqueStyle = new Select(driver.findElement(By.id("select-1550")));
        dropdownPlaqueStyle.selectByIndex(1);
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement amazonButton = driver.findElement(By.linkText("Amazon"));
        amazonButton.click();
        assertTrue(driver.getCurrentUrl().contains("perfect"));
    }

    @After
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
