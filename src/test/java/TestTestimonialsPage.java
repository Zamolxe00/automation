import org.apache.log4j.Logger;
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

public class TestTestimonialsPage {

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger log = Logger.getLogger(TestTestimonialsPage.class);

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

 //Test write a review option from product page
 //This process describes following steps:  //-> go to PM3720 -> Identify the Write a review section -> Click on Write a review ->New page oppens -> Add user name -> add review title
 // -> add comment -> add ratings -> accept site conditions checkbox -> click on recaptcha -> Click on Submit button -> Confirm Display message is
 // “Thank you. Your review has been submitted and is now awaiting approval."

    @Test
    public void clickWriteReview() throws InterruptedException {
        log.info("Test write a review option from product page link ");
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
        driver.switchTo().frame(driver.findElement(By.tagName("Iframe")));
        WebElement reCaptcha = driver.findElement(By.id("re-captcha-accessible-status")).findElement(By.className("rc-anchor"));
        reCaptcha.click();
        driver.switchTo().defaultContent();
        submitButton.click();
        WebElement confirmMessage = driver.findElement(By.id("CopyrightArea")).findElement(By.className("container")).findElement(By.className("noty_body"));
        assertTrue(confirmMessage.getText().contains("Thank you. Your review has been submitted and is now awaiting approval."));

    }
//Test go to testimonial page from product page
//This process describes following steps:
//-> go to PM3720 -> Identify the testimonial o right sidebar -> Click on Read More a review section -> Confirm the current Url contains “testimonails”
    @Test
    public void clickReadMore() throws InterruptedException {
        log.info("Test go to testimonial page from product page");
        log.info("go to PM3720");
        driver.get("http://www.theperfecturn.com/photo-engraved-pendant-gold-rectangle-p-2720.html");
        log.info("Click on Read More element on the page ");
        WebElement readMoreButton = driver.findElement(By.className("read-more"));
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click();", readMoreButton);
        Thread.sleep(1000);
        log.info("Testimonials  Page is displayed in browser" );
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
