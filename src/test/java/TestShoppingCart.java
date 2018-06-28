import org.apache.log4j.Logger;
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

public class TestShoppingCart {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger log = Logger.getLogger(TestLogin.class);

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
        log.info("Test Click on  Product Name in SC is same as Product name on Product Page ");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Get product name ");
        String productName = driver.findElement(By.id("ProductName")).getText();
        log.info("Click on Add to cart");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info("Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info("Get Product Name in SC");
        String productNameInSC = driver.findElement(By.className("name")).findElement(By.tagName("a")).getText();
        log.info("Confirm Product Name in SC is same as Product name on Product Page ");
        assertTrue(productName.contains(productNameInSC));
    }

//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->  Click on product name in SC
// -> confirm that same name is displayed in the current Product Page Oppened
    @Test
    public void TestRedirectionFromSC() {
        log.info("Test Click on  Item name  in  SC");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Get product name ");
        String productName = driver.findElement(By.id("ProductName")).getText();
        log.info("Click on Add to cart");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info("Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info("Click on  Product Name in SC");
        WebElement productlinkInSC = driver.findElement(By.className("name"));
        log.info("Get product name ");
        productlinkInSC.click();
        String productNameRedirection = driver.findElement(By.id("ProductName")).getText();
        log.info("Confirm Product Name in new page is same as Product name on initial Product Page ");
        assertTrue(productName.contains(productNameRedirection));
    }


//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->	identify product image-> click on product image
// -> confirm that same name is displayed in the current Product Page Oppened
    @Test
    public void TestProductImageInCart() {
        log.info("Test Click on  Item Image  in  SC");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Get product name ");
        String productName = driver.findElement(By.id("ProductName")).getText();
        log.info("Click on Add to cart");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info("Click on  Product Image  in SC");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        WebElement productImageInSC = driver.findElement(By.className("image"));
        productImageInSC.click();
        log.info("Get product name ");
        String productNameRedirection = driver.findElement(By.id("ProductName")).getText();
        log.info("Confirm Product Name in new page is same as Product name on initial Product Page ");
        assertTrue(productName.equals(productNameRedirection));

    }

//Test increase product quantity  in shopping cart.
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->	identify  the increase quantity (“+” ) option -> click on “+”
// -> confirm that “Quantity successfully updated” message is displayed

    @Test
    public void testProductQuantityIncrease() throws InterruptedException {
        log.info("Test Increase Item Quantity in  SC");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Add to cart");
        WebElement addToCartButtonLink = driver.findElement(By.xpath("//*[@id=\"AddToCartForm\"]/div[1]/div[2]/div[4]/button"));
        addToCartButtonLink.click();
        log.info("Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info("Identify the increase button and Click ");
        WebElement increaseButton = driver.findElement(By.className("plus"));
        increaseButton.click();
        //WebElement valueElement = driver.findElement(By.className("quantity")).findElement(By.tagName("input"));
        //assertEquals("2", valueElement.getAttribute("value"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("noty_body"))));
        WebElement actualConfirmMessage = driver.findElement(By.className("noty_body"));
        log.info("Get confirmation message Quantity successfully updated.");
        assertTrue(actualConfirmMessage.getText().contains("Quantity successfully updated."));
    }

    private void assertEquals(String s, String value) {
    }

//Test decrease product quantity  in shopping cart.
//
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->	identify  the increase quantity (“+” ) option -> click on “+”
// -> identify that decrease quantity (“-”) option -> click on “-” ->-> confirm that “Quantity successfully updated” message is displayed
    @Test
    public void testProductQuantityDecrease() throws InterruptedException {
        log.info("Test Decrease Item Quantity in  SC");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Add to cart");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info("Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info("Identify the increase button and Click ");
        WebElement increaseButton = driver.findElement(By.className("plus"));
        increaseButton.click();
        log.info("Identify the decrease  button and Click ");
        WebElement decreaseButton = driver.findElement(By.className("minus"));
        decreaseButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("noty_body"))));
        WebElement actualConfirmMessage = driver.findElement(By.className("noty_body"));
        log.info("Get confirmation message Quantity successfully updated.");
        assertTrue(actualConfirmMessage.getText().contains("Quantity successfully updated."));
    }


//Test remove product from shopping cart.
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option -> Identify the remove buton -> Click on review button
// ->Confirm that “Item successfully removed” message is displayed
    @Test
    public void testRemoveItem() throws InterruptedException {
        log.info("Test Remove Item from  SC");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Add to cart");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info("Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info("Identify the Remove  button and Click ");
        WebElement removeButton = driver.findElement(By.className("remove"));
        removeButton.click();
        WebElement actualConfirmMessage = driver.findElement(By.className("alert-success"));
        log.info("Get confirmation message:  Item successfully removed");
        assertTrue(actualConfirmMessage.getText().contains("Item successfully removed"));
    }


//Test Edit options in SC
//This process describes following steps:
//-> go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option -> Identify the Edt options buton ->Click on Edit Options -> Change option
// -> Confirm that “Item options were successfully updated” message is displayed
    @Test
    public void testEditProductOptionsInSC() throws InterruptedException {
        log.info("Test Edit Product Options In SC");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Select First Option from  choosePlaqueStyle drop-list");
        List<WebElement> optionsDropdowns = driver.findElement(By.id("Options")).findElements(By.tagName("select"));
        WebElement choosePlaqueStyle = optionsDropdowns.get(0);
        Select dropdownPlaqueStyle = new Select(choosePlaqueStyle);
        dropdownPlaqueStyle.selectByIndex(1);
        optionsDropdowns = driver.findElement(By.id("Options")).findElements(By.tagName("select"));
        log.info("Select First Option from   chooseArtwork drop-list");
        Select dropdownchooseArtwork = new Select(optionsDropdowns.get(1));
        dropdownchooseArtwork.selectByIndex(1);
        log.info("Click on Add to cart");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info("Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info("Identify the Edit Options  button and Click ");
        WebElement editOptions = driver.findElement(By.className("edit-options"));
        editOptions.click();
        log.info("Identify the EditOptionsModal and Select second option from drop-down ");
        optionsDropdowns = driver.findElement(By.id("EditOptionsModal")).findElements(By.tagName("select"));
        dropdownchooseArtwork = new Select(optionsDropdowns.get(1));
        dropdownchooseArtwork.selectByIndex(2);
        WebElement saveButton = driver.findElement(By.id("EditOptionsModal")).findElement(By.className("btn-primary"));
        log.info("Identify the Save button and Click ");
        saveButton.click();
        String actualConfirmMessage = driver.findElement(By.className("alert-container")).getAttribute("innerText");
        log.info("Confirmation message : Item options were successfully updated.");
        assertTrue(actualConfirmMessage.contains("Item options were successfully updated."));
    }

//Test  changing Delivery options when user is logged in
//This process describes following steps: //Go to Homepage-> go to My profile ->Login -> Go to Homepage ->   identify the three category columns available in Homepage -> Select first product
// from first category -> click on product -> get product name  -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->  Identify the update
// delivery options section -> click on new delivery option ->  Confirm that “The shipping method successfully updated"” message is displayed
    @Test
    public void testChangeDeliveryOptionInSC1() throws InterruptedException {
        log.info("Test Change  Options In SC when user is logged in");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        log.info("Click on My profile ");
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        WebElement emailInput = driver.findElement(By.id("inputEmail3"));
        WebElement passwordInput = driver.findElement(By.id("inputPassword3"));
        WebElement secureLogin = driver.findElement(By.className("mb-4")).findElement(By.tagName("button"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        log.info("Add email address ielena.gheorghe@dovona7.com");
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("ielena.gheorghe@dovona7.com");
        log.info("Add password Ciresica123");
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("Ciresica123");
        log.info("Click on Secure login ");
        secureLogin.click();
        log.info("Go to homepage");
        driver.get("http://www.theperfecturn.com");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Get Product Name");
        String productName = driver.findElement(By.id("ProductName")).getText();
        log.info("Get  Plaque Style");
        WebElement choosePlaqueStyle = driver.findElement(By.id("select-1550"));
        Select dropdownPlaqueStyle = new Select(driver.findElement(By.id("select-1550")));
        dropdownPlaqueStyle.selectByIndex(1);
        log.info("Click on Add To Cart ");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        log.info(" Click on View Shopping Cart ");
        viewShoppingCart.click();
        List<WebElement> deliveryOptions = driver.findElements(By.className("delivery-options"));
        log.info(" Click on First Option in Delivery methods list on SC Page ");
        deliveryOptions.get(0).click();
      //  WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("noty_body"))));
        String actualConfirmMessage = driver.findElement(By.className("noty_body")).getAttribute("innerText");
        log.info("Confirmation message :The shipping method successfully updated");
        assertTrue(actualConfirmMessage.contains("The shipping method successfully updated"));
    }

//Test  changing Delivery options when user is not logged in
//This process describes following steps:
//Go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name  -> identify the Add to cart
// button -> Click on Add to Cart -> Click on View Shopping Cart Option ->  Identify the update delivery options section -> click on new delivery option ->  Confirm that “Unauthorized action.” message is displayed
    @Test
    public void testChangeDeliveryOptionInSC2() throws InterruptedException {
        log.info("Test  changing Delivery options when user is not logged in");
        log.info("Go to homepage");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Get Product Name");
        String productName = driver.findElement(By.id("ProductName")).getText();
        log.info("Get  Plaque Style");
        WebElement choosePlaqueStyle = driver.findElement(By.id("select-1550"));
        Select dropdownPlaqueStyle = new Select(driver.findElement(By.id("select-1550")));
        dropdownPlaqueStyle.selectByIndex(1);
        log.info("Click on Add To Cart ");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info(" Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info(" Click on First Option in Delivery methods list on SC Page ");
        List<WebElement> deliveryOptions = driver.findElements(By.className("delivery-options"));
        deliveryOptions.get(0).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("noty_body"))));
        String actualConfirmMessage = driver.findElement(By.className("noty_body")).getAttribute("innerText");
        log.info("Warning message : Unauthorized action. Please log in.");
        assertTrue(actualConfirmMessage.contains("Unauthorized action. Please log in."));
    }

//Test Apply cupon  when user is not logged in
//This process describes following steps:
//Go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product -> get product name
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option ->  Identify the Cupon field ->Add cupon ->Click on Submit
// -> Confirm that “Unauthorized action. Please log in.” message is displayed
    @Test
    public void testAddCouponUserNotLoggedIn() throws InterruptedException {
        log.info("Test Apply cupon  when user is not logged in️");
        log.info("Go to homepage");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Add To Cart ");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info(" Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info(" Click on Cupon Input and add JORJTEST");
        WebElement cuponSpace = driver.findElement(By.id("CouponInput"));
        cuponSpace.click();
        cuponSpace.clear();
        cuponSpace.sendKeys("JORJTEST");
        log.info(" Click on Submit Button ");
        WebElement applyButton = driver.findElement(By.id("CouponSubmitButton"));
        applyButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("noty_body")));
        String actualConfirmMessage = driver.findElement(By.className("noty_body")).getAttribute("innerText");
        log.info("Warning message : Unauthorized action. Please log in.");
        assertTrue(actualConfirmMessage.contains("Unauthorized action. Please log in."));
    }


//Test Apply cupon  when user is logged in️
//This process describes following steps:
//Go to Homepage-> go to My profile ->Login -> Go to Homepage -> identify the three category columns available in Homepage -> Select first product from first category -> click on product
// -> get product name  -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option -> Identify the
//Edt options buton ->Click on Edit Options -> Change option -> Confirm that “Item options were successfully updated” message is displayed
    @Test
    public void testAddCuponUserLoggedIn() throws InterruptedException {
        log.info("Test Apply cupon  when user is logged in️");
        log.info("Click on My profile ");
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        WebElement emailInput = driver.findElement(By.id("inputEmail3"));
        WebElement passwordInput = driver.findElement(By.id("inputPassword3"));
        WebElement secureLogin = driver.findElement(By.className("mb-4")).findElement(By.tagName("button"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        log.info("Add email address ielena.gheorghe@dovona7.com");
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("ielena.gheorghe@dovona7.com");
        log.info("Add password Ciresica123");
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("Ciresica123");
        log.info("Click on Secure login ");
        secureLogin.click();
        log.info("Click on Secure login ");
        log.info("Go to homepage");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Add To Cart ");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info(" Click on View Shopping Cart ");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info(" Click on Cupon Input and add JORJTEST");
        WebElement cuponSpace = driver.findElement(By.id("CouponInput"));
        cuponSpace.click();
        cuponSpace.clear();
        cuponSpace.sendKeys("JORJTEST");
        log.info(" Click on Submit Button ");
        WebElement applyButton = driver.findElement(By.id("CouponSubmitButton"));
        applyButton.click();
        String actualConfirmMessage = driver.findElement(By.className("noty_body")).getAttribute("innerText");
        log.info("Confirmation message :The coupon has been successfully applied.");
        assertTrue(actualConfirmMessage.contains("The coupon has been successfully applied."));
    }
//Test Checkout option  to continue Checkout process result user not logged️
//This process describes following steps:
//Go to Homepage-> identify the three category columns available in Homepage -> Select first product from first category -> click on product ->
// -> identify the Add to cart button -> Click on Add to Cart -> Click on View Shopping Cart Option -> Identify the Checkout buton ->Click on Checkout -> Confirm that Login page is displayed

    @Test
    public void SCcheckoutOption() throws InterruptedException {
        log.info("Test Click on Checkout when user is not logged");
        log.info("Click on First Product from the list reffering to Cremation urns on Homepage");
        List<WebElement> categoryProducts = driver.findElements(By.className("category-items"));
        categoryProducts.get(0).click();
        log.info("Click on Add to cart ");
        WebElement addToCartButtonDown = driver.findElement(By.className("AddToCartAction"));
        addToCartButtonDown.click();
        log.info("Click on View SC");
        WebElement viewShoppingCart = driver.findElement(By.linkText("View Shopping Cart"));
        viewShoppingCart.click();
        log.info("Click on CheckOut Button");
        WebElement checkoutButton = driver.findElement(By.linkText("Checkout"));
        checkoutButton.click();
        log.info("Confirm Login Page is displayed in browser");
        assertTrue(driver.getCurrentUrl().contains("login"));
    }


    // Test Click Amazon for Amazon pay result - to be modified when implemented
    @Test
    public void amazonCheckout() throws InterruptedException {
        log.info("Test Click Amazon for Amazon pay result - to be modified when implemented");
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
