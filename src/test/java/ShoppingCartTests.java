import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ShoppingCartTests {
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
    public void testRemoveItem() throws InterruptedException {
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
        assertTrue(actualConfirmMessage.getText().contains("Item successfully removed"));
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
