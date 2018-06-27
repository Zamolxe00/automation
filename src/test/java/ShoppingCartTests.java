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


// This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> identify the Add to cart button
// -> Click on Add to Cart -> Click on View Shopping Cart Option -> Confirm that same product is displayed in SC
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

//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->  Click on product name in SC
// -> confirm that same name is displayed in the current Product Page Oppened
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


//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->	identify product image-> click on product image
// -> confirm that same name is displayed in the current Product Page Oppened
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

//Test increase product quantity  in shopping cart.
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->	identify  the increase quantity (“+” ) option -> click on “+”
// -> confirm that “Quantity successfully updated” message is displayed

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

//Test decrease product quantity  in shopping cart.✔️
//
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->	identify  the increase quantity (“+” ) option -> click on “+”
// -> identify that decrease quantity (“-”) option -> click on “-” ->-> confirm that “Quantity successfully updated” message is displayed
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
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option -> Identify the remove buton -> Click on review button
// ->Confirm that “Item successfully removed” message is displayed
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


//Test Edit options in SC
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option -> Identify the Edt options buton ->Click on Edit Options -> Change option
// -> Confirm that “Item options were successfully updated” message is displayed
    @Test
    public void testEditProductOptionsInSC() throws InterruptedException {
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

//Test  changing Delivery options when user is logged in
//This process describes following steps: //Go to Homepage-> go to My profile ->Login -> Go to Homepage ->   identify the three category columns available in Homepage -> Select first product
// from first category -> click on product -> get product name  -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->  Identify the update
// delivery options section -> click on new delivery option ->  Confirm that “The shipping method successfully updated"” message is displayed
    @Test
    public void testChangeDeliveryOptionInSC1() throws InterruptedException {
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
        List<WebElement> deliveryOptions = driver.findElements(By.className("delivery-options"));
        deliveryOptions.get(0).click();
      //  WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("noty_body"))));
        String actualConfirmMessage = driver.findElement(By.className("noty_body")).getAttribute("innerText");
        assertTrue(actualConfirmMessage.contains("The shipping method successfully updated"));
    }

//Test  changing Delivery options when user is not logged in
//This process describes following steps:
//Go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name  -> identify the Add to cart
// button -> Click on Add to Cart -> Click on View Shopping Cart Option ->  Identify the update delivery options section -> click on new delivery option ->  Confirm that “Unauthorized action.” message is displayed
    @Test
    public void testChangeDeliveryOptionInSC2() throws InterruptedException {
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
        assertTrue(actualConfirmMessage.contains("Unauthorized action. Please log in."));
    }

//Test Apply cupon  when user is not logged in
//This process describes following steps:
//Go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->  Identify the Cupon field ->Add cupon ->Click on Submit
// -> Confirm that “Unauthorized action. Please log in.” message is displayed
    @Test
    public void testAddCouponUserNotLoggedIn() throws InterruptedException {
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


//Test Apply cupon  when user is logged in️
//This process describes following steps:
//Go to Homepage-> go to My profile ->Login -> Go to Homepage -> identify the three category columns available in Homepage -> Select first product from first category -> click on product
// -> get product name  -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option -> Identify the
//Edt options buton ->Click on Edit Options -> Change option -> Confirm that “Item options were successfully updated” message is displayed
    @Test
    public void testAddCuponUserLoggedIn() throws InterruptedException {
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
//Test Checkout option  to continue Checkout process result user not logged✔️
//This process describes following steps:
//Go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option -> Identify the Checkout buton ->Click on Checkout -> Confirm that Login page is displayed

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
