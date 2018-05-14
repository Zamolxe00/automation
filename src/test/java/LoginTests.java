import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class LoginTests {
    //Login pages
//Login to My Profile with provided e-mail and password

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
    public void testLogin() {
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
        assertTrue(driver.getCurrentUrl().contains("profile"));
    }

    //Login pages
// test log off
    @Test
    public void testlogoff() {
        testLogin();
        WebElement logoffButton = driver.findElement(By.className("btn-sm"));
        logoffButton.click();
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    //Login pages
    //Test Add new delivery address
    @Test
    public void testAddDeliveryAddress() {
        testLogin();
        WebElement addressBookTab = driver.findElement(By.linkText("View or change entries in my address book."));
        addressBookTab.click();
        WebElement addNewAddress = driver.findElement(By.className("btn-primary"));
        addNewAddress.click();
        WebElement firstName = driver.findElement(By.id("FirstName"));
        WebElement lastName = driver.findElement(By.id("LastName"));
        WebElement addressLine = driver.findElement(By.id("AddressLine1"));
        WebElement selectDropDown1 = driver.findElement(By.id("Country"));
        Select dropdown1 = new Select(driver.findElement(By.id("Country")));
        WebElement selectDropDown2 = driver.findElement(By.id("State"));
        Select dropdown2 = new Select(driver.findElement(By.id("State")));
        WebElement city = driver.findElement(By.id("City"));
        WebElement zipCode = driver.findElement(By.id("ZipCode"));
        WebElement saveButton = driver.findElement(By.className("btn-primary"));
        firstName.click();
        firstName.clear();
        firstName.sendKeys("Yara");
        lastName.click();
        lastName.clear();
        lastName.sendKeys("Smith");
        addressLine.click();
        addressLine.clear();
        addressLine.sendKeys("home");
        dropdown1.selectByIndex(1);
        dropdown2.selectByIndex(1);
        city.click();
        city.clear();
        city.sendKeys("Rome");
        zipCode.click();
        zipCode.clear();
        zipCode.sendKeys("123456");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled','disabled')", saveButton);
        saveButton.click();
        assertTrue(driver.getCurrentUrl().contains("book"));
    }

    private void selectByIndex(int i) {
    }

    //Login pages
// test Test  update telephone number
    @Test
    public void testUpdatePhoneNUmber() {
        testLogin();
        WebElement informationTab = driver.findElement(By.linkText("View or change my information."));
        String telephonenr = ("123456789");
        informationTab.click();
        WebElement telephoneInput = driver.findElement(By.id("Telephone"));
        WebElement updateButton = driver.findElement(By.className("btn-primary"));
        telephoneInput.click();
        telephoneInput.clear();
        telephoneInput.sendKeys(telephonenr);
        updateButton.click();
        informationTab = driver.findElement(By.linkText("View or change my information."));

        informationTab.click();
        telephoneInput = driver.findElement(By.id("Telephone"));
        assertTrue(telephoneInput.getAttribute("value").equals(telephonenr));
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
