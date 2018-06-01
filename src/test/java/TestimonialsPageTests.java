import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestimonialsPageTests {

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


    ///Test write a review from product page
    //Bug, takes you to shopping cart no confirmation message
    @Test
    public void clickWriteReview() throws InterruptedException {
        driver.get("http://www.theperfecturn.com/photo-engraved-pendant-gold-rectangle-p-2720.html");
        WebElement reviewButton = driver.findElement(By.linkText("Write a review"));
        reviewButton.click();

        WebElement yourName = driver.findElement(By.id("ReviewerName"));
        WebElement reviewTitle = driver.findElement(By.id("ReviewTitle"));
        WebElement comment = driver.findElement(By.id("ReviewBody"));
        List<WebElement> ratings = driver.findElement(By.className("rating-buttons")).findElements(By.tagName("label"));
        WebElement conditionsAccept = driver.findElement(By.className("material-icons"));
        WebElement submitButton = driver.findElement(By.className("btn-primary"));
        yourName.click();
        yourName.clear();
        yourName.sendKeys("Lara Croft");
        reviewTitle.click();
        reviewTitle.clear();
        reviewTitle.sendKeys("test");
        comment.click();
        comment.clear();
        comment.sendKeys("test thank you for order");
        ratings.get(4).click();
        conditionsAccept.click();
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        WebElement reCaptcha = driver.findElement(By.id("recaptcha-anchor"));
        reCaptcha.click();
        driver.switchTo().defaultContent();
        submitButton.click();
        WebElement confirmMessage = driver.findElement(By.id("CopyrightArea")).findElement(By.className("container")).findElement(By.className("noty_body"));
        assertTrue(confirmMessage.getText().contains("Quantity successfully updated."));

    }


    //Test read a complete review (read  more option)
    @Test
    public void clickReadMore() throws InterruptedException {
        driver.get("http://www.theperfecturn.com/photo-engraved-pendant-gold-rectangle-p-2720.html");
        WebElement readMoreButton = driver.findElement(By.className("read-more"));
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click();", readMoreButton);
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("testimonials"));
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
