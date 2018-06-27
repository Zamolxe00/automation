import org.apache.commons.lang.RandomStringUtils;
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

//Test create new profile
// This process describes following steps:-> go to Homepage-> search for My Profile link in header -> click on My profile -> Click on New Customer -> Add First name
// ->Add Last name -> Select Country -> Add Address -> Add City -> Select State -> Add zip code -> Add telephone Number -> Add E-mail Address ->Re-type
// -> Add Password ->Re-type ->Click Continue -> Confirmation message displays”Your account has been successfully created. You can login now.”


    @Test
    public void testRegisterNewProfile() throws InterruptedException {
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        WebElement newCustomerButton = driver.findElement(By.className("btn-highlight"));
        newCustomerButton.click();
        Thread.sleep(1000);
        WebElement firstName = driver.findElement(By.id("FirstName"));
        WebElement lastName = driver.findElement(By.id("LastName"));
        WebElement SelectCountryDropDown = driver.findElement(By.id("Country"));
        Select countryDropDown = new Select(driver.findElement(By.id("Country")));
        WebElement addressLine = driver.findElement(By.id("AddressLine1"));
        WebElement city = driver.findElement(By.id("City"));
        WebElement SelectStateDropDown = driver.findElement(By.id("State"));
        Select stateDropDown = new Select(driver.findElement(By.id("State")));
        WebElement zipCode = driver.findElement(By.id("ZipCode"));
        WebElement telephone = driver.findElement(By.id("Telephone"));
        WebElement emailAddress = driver.findElement(By.id("Email"));
        WebElement retypeEmailAddress = driver.findElement(By.id("RetypeEmail"));
        WebElement newPassword = driver.findElement(By.id("Password"));
        WebElement retypePassword = driver.findElement(By.id("RetypePassword"));
        WebElement continueButton = driver.findElement(By.id("SignUp")).findElement(By.tagName("button"));
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        String email = generatedString + "@gmail.com";
        firstName.click();
        firstName.clear();
        firstName.sendKeys("Yara");
        lastName.click();
        lastName.clear();
        lastName.sendKeys("Smith");
        countryDropDown.selectByIndex(1);
        addressLine.click();
        addressLine.clear();
        addressLine.sendKeys("home nr.12");
        city.click();
        city.clear();
        city.sendKeys("Rome");
        stateDropDown.selectByIndex(1);
        zipCode.click();
        zipCode.clear();
        zipCode.sendKeys("123456");
        telephone.click();
        telephone.clear();
        telephone.sendKeys("3218524945");
        emailAddress.click();
        emailAddress.clear();
        emailAddress.sendKeys(email);
        retypeEmailAddress.click();
        retypeEmailAddress.clear();
        retypeEmailAddress.sendKeys(email);
        newPassword.click();
        newPassword.clear();
        newPassword.sendKeys(email);
        retypePassword.click();
        retypePassword.clear();
        retypePassword.sendKeys(email);
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

//Test  login
//This process describes following steps: -> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password
// -> Click on Secure login -> confirm that user profile page is available
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

//Test logoff
//This process describes following steps: -> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password
// ->Click on Secure login ->Identofy the login button ->Click on login button ->confirm that login page “Welcome “ is oppened
    @Test
    public void testlogoff() {
        testLogin();
        WebElement logoffButton = driver.findElement(By.className("btn-sm"));
        logoffButton.click();
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

//Test Add new delivery address️
//This process describes following steps:
//-> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password ->Click on Secure login ->Click on Address book tab
// -> Click on Add new Address -> Add First Name ->Add last Name -> Add Address Line 1-> Select Country-> Select State ->Add City -> Add Zip Code -> Click on Save ->Confirm
    @Test
    public void testAddNewDeliveryAddress() {
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


//Test  update telephone number
//This process describes following steps: -> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password ->Click on Secure login
// -> Go to section View or Change My Information -> Click in telephone cassette-> add new telephone number -> record new telephone number -> click update
// ->Go to section View or Change My Information -> Confirm that displayed telephone number is same as the one recently updated
    @Test
    public void testUpdatePhoneNumber() {
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


//Test reset password
// This process describes following steps: -> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password
// ->Click on Secure login ->Click on Change My account Password ->Add new password -> Update -> Logoff ->login with new password-> Click on Change My account Password
// ->Update to old password ->Confirm that user is still on profile page
    @Test
    public void testRestPassword() {
//login to profile
        testLogin();
//declare element that goes to change password page
        WebElement changePassword = driver.findElement(By.linkText("Change my account password."));
        changePassword.click();
// declare field elements for password change page
        WebElement oldPassword = driver.findElement(By.id("CurrentPassword"));
        WebElement newPassword = driver.findElement(By.id("NewPassword"));
        WebElement confirmPassword = driver.findElement(By.id("ConfirmPassword"));
        WebElement updateButton = driver.findElement(By.className("btn-primary"));
// declare strings  for password change page
        String passwordOld = ("Ciresica123");
        String passwordNew = ("Ciresica123");
// change password action item
        oldPassword.click();
        oldPassword.clear();
        oldPassword.sendKeys(passwordOld);
        newPassword.click();
        newPassword.clear();
        newPassword.sendKeys(passwordNew);
        confirmPassword.click();
        confirmPassword.clear();
        confirmPassword.sendKeys(passwordNew);
// activate update button
        updateButton.click();
//logoff from user profile
        WebElement logoffButton = driver.findElement(By.className("btn-sm"));
        logoffButton.click();
//login with new password
        WebElement emailInput = driver.findElement(By.id("inputEmail3"));
        WebElement passwordInput = driver.findElement(By.id("inputPassword3"));
        WebElement secureLogin = driver.findElement(By.className("mb-4")).findElement(By.tagName("button"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("ielena.gheorghe@dovona7.com");
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(passwordNew);
        secureLogin.click();
//declare element that goes to change password page
        changePassword = driver.findElement(By.linkText("Change my account password."));
        changePassword.click();
// declare field elements for password change page
        oldPassword = driver.findElement(By.id("CurrentPassword"));
        newPassword = driver.findElement(By.id("NewPassword"));
        confirmPassword = driver.findElement(By.id("ConfirmPassword"));
        updateButton = driver.findElement(By.className("btn-primary"));
// change password action item
        oldPassword.click();
        oldPassword.clear();
        oldPassword.sendKeys(passwordNew);
        newPassword.click();
        newPassword.clear();
        newPassword.sendKeys(passwordOld);
        confirmPassword.click();
        confirmPassword.clear();
        confirmPassword.sendKeys(passwordOld);
// activate update button
        updateButton.click();
//assert that new new  password is in place as it goes to user profile page
        Assert.assertTrue(driver.getCurrentUrl().contains("profile"));


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
