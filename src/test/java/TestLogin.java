import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class TestLogin {
    //Login pages
//Login to My Profile with provided e-mail and password

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

//Test create new profile
// This process describes following steps:-> go to Homepage-> search for My Profile link in header -> click on My profile -> Click on New Customer -> Add First name
// ->Add Last name -> Select Country -> Add Address -> Add City -> Select State -> Add zip code -> Add telephone Number -> Add E-mail Address ->Re-type
// -> Add Password ->Re-type ->Click Continue -> Confirmation message displays”Your account has been successfully created. You can login now.”


    @Test
    public void testRegisterNewProfile() throws InterruptedException {
        log.info("This test involves : Register new user profile");
        log.info("Declare View My Profile Element and Click");
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        WebElement newCustomerButton = driver.findElement(By.className("btn-highlight"));
        log.info("Declare New Customer Element and Click");
        newCustomerButton.click();
        Thread.sleep(1000);
        log.info("Declare Field Elements to be filled  and Click");
        WebElement firstName = driver.findElement(By.id("FirstName"));
        WebElement lastName = driver.findElement(By.id("LastName"));




      //  Select dropdowncountry = new Select(chooseCountry);
       /// dropdowncountry.selectByIndex(1);


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
        log.info("firstName :Yara");
        firstName.click();
        firstName.clear();
        firstName.sendKeys("Yara");
        lastName.click();
        lastName.clear();
        log.info("lastName :Smith");
        lastName.sendKeys("Smith");
        log.info("America");
        countryDropDown.selectByIndex(1);
        addressLine.click();
        addressLine.clear();
        log.info("Address : home nr. 12");
        addressLine.sendKeys("home nr.12");
        log.info("City :Rome");
        city.click();
        city.clear();
        city.sendKeys("Rome");
        stateDropDown.selectByIndex(1);
        log.info("Zip Code 123456");
        zipCode.click();
        zipCode.clear();
        zipCode.sendKeys("123456");
        log.info("Telephone 3218524945 ");
        telephone.click();
        telephone.clear();
        telephone.sendKeys("3218524945");
        log.info("generate e-mail address and introduce value ");
        emailAddress.click();
        emailAddress.clear();
        emailAddress.sendKeys(email);
        log.info("Retype e-mail");
        retypeEmailAddress.click();
        retypeEmailAddress.clear();
        retypeEmailAddress.sendKeys(email);
        log.info("Generate new password ");
        newPassword.click();
        newPassword.clear();
        newPassword.sendKeys(email);
        log.info("retype Password");
        retypePassword.click();
        retypePassword.clear();
        retypePassword.sendKeys(email);
        continueButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

//Test  login
//This process describes following steps: -> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password
// -> Click on Secure login -> confirm that user profile page is available
    @Test
    public void testLogin() {
        log.info("This test involves : login to user account");
        log.info("Declare View My Profile Element and Click");
        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();
        log.info("Declare Field Elements to be filled  and Click");
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
        log.info("Declare Secure Login Element and Click");
        secureLogin.click();
        assertTrue(driver.getCurrentUrl().contains("profile"));
        log.info("Confirm My Profile  displayed in browser ");
    }

//Test logoff
//This process describes following steps: -> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password
// ->Click on Secure login ->Identofy the login button ->Click on login button ->confirm that login page “Welcome “ is oppened
    @Test
    public void testlogoff() {
        log.info("This test involves : login to user account-> logoff from user account ");
        log.info("User Login on Login Page ");
        testLogin();
        log.info("Declare update Element and Click");
        WebElement logoffButton = driver.findElement(By.className("btn-sm"));
        logoffButton.click();
        log.info("Confirm Login Page is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

//Test Add new delivery address️
//This process describes following steps:
//-> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password ->Click on Secure login ->Click on Address book tab
// -> Click on Add new Address -> Add First Name ->Add last Name -> Add Address Line 1-> Select Country-> Select State ->Add City -> Add Zip Code -> Click on Save ->Confirm
    @Test
    public void testAddNewDeliveryAddress() {
        log.info("This test involves : login to user account,add new delivery address and confirmation message for update ");
        log.info("User Login on Login Page ");
        testLogin();
        log.info("Declare View or change entries in my address book element and Click");
        WebElement addressBookTab = driver.findElement(By.linkText("View or change entries in my address book."));
        addressBookTab.click();
        log.info("Declare Add new address element  and click");
        WebElement addNewAddress = driver.findElement(By.className("btn-primary"));
        addNewAddress.click();
        log.info("Declare Field Elements to be filled  and Click");
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
        log.info("First Name Yara");
        firstName.click();
        firstName.clear();
        firstName.sendKeys("Yara");
        log.info("Last Name Smith");
        lastName.click();
        lastName.clear();
        lastName.sendKeys("Smith");
        log.info("Address line :home");
        addressLine.click();
        addressLine.clear();
        addressLine.sendKeys("home");
        log.info("Country United States");
        dropdown1.selectByIndex(1);
        log.info("State Alabama");
        dropdown2.selectByIndex(1);
        log.info("City Rome");
        city.click();
        city.clear();
        city.sendKeys("Rome");
        log.info("Zip Code 123456");
        zipCode.click();
        zipCode.clear();
        zipCode.sendKeys("123456");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled','disabled')", saveButton);
        log.info("Declare Save  Element and Click");
        saveButton.click();
        log.info("Declare confirmation message on page ");
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
        log.info("This test involves : login to user account, update telephone number and confirm new alue is displayed");
        log.info("User Login on Login Page ");
        testLogin();
        log.info("Declare View or change my information Element and Click");
        WebElement informationTab = driver.findElement(By.linkText("View or change my information."));
        String telephonenr = ("123456789");
        informationTab.click();
        log.info("Declare Telephone Element and add information");
        WebElement telephoneInput = driver.findElement(By.id("Telephone"));
        WebElement updateButton = driver.findElement(By.className("btn-primary"));
        telephoneInput.click();
        telephoneInput.clear();
        telephoneInput.sendKeys(telephonenr);
        log.info("Declare update Element and Click");
        updateButton.click();
        informationTab = driver.findElement(By.linkText("View or change my information."));
        informationTab.click();
        log.info("Declare View or change my information Element and Click");
        telephoneInput = driver.findElement(By.id("Telephone"));
        assertTrue(telephoneInput.getAttribute("value").equals(telephonenr));
        log.info("Check and confirm that telephone number value is same as the new one added ");
    }


//Test reset password
// This process describes following steps: -> go to Homepage-> search for My Profile link in header -> click on My profile -> Add user account ->Add password
// ->Click on Secure login ->Click on Change My account Password ->Add new password -> Update -> Logoff ->login with new password-> Click on Change My account Password
// ->Update to old password ->Confirm that user is still on profile page
    @Test
    public void testRestPassword() {
        log.info("This test involves : login to user profile -> Change Password -> Logoff -> Login With new Password -> Restore old password -> login to user profile with new password");
        log.info("User Login on Login Page ");
        testLogin();
        log.info("Declare Change My Account Password Element and Click");
        WebElement changePassword = driver.findElement(By.linkText("Change my account password."));
        changePassword.click();
        log.info("Declare the elements involved in password change : Current Password ,New Password,Confirm Password, Update Button ");
        WebElement oldPassword = driver.findElement(By.id("CurrentPassword"));
        WebElement newPassword = driver.findElement(By.id("NewPassword"));
        WebElement confirmPassword = driver.findElement(By.id("ConfirmPassword"));
        WebElement updateButton = driver.findElement(By.className("btn-primary"));
        log.info ("Declare passwordOld and passwordNew strings  ");
        String passwordOld = ("Ciresica123");
        String passwordNew = ("Ciresica123");
        log.info ("Execute the change password action item for passwordNew");
        oldPassword.click();
        oldPassword.clear();
        oldPassword.sendKeys(passwordOld);
        newPassword.click();
        newPassword.clear();
        newPassword.sendKeys(passwordNew);
        confirmPassword.click();
        confirmPassword.clear();
        confirmPassword.sendKeys(passwordNew);
        log.info("Click on the Update button to update to passwordNew ");
        updateButton.click();
        log.info("Logoff from user account ");
        WebElement logoffButton = driver.findElement(By.className("btn-sm"));
        logoffButton.click();
        log.info("User login on Login Pgae with passwordNew");
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
        log.info("Declare Change My Account Password Element");
        changePassword = driver.findElement(By.linkText("Change my account password."));
        changePassword.click();
        log.info("Declare the elements involved in password change : Current Password ,New Password,Confirm Password, Update Button ");
        oldPassword = driver.findElement(By.id("CurrentPassword"));
        newPassword = driver.findElement(By.id("NewPassword"));
        confirmPassword = driver.findElement(By.id("ConfirmPassword"));
        updateButton = driver.findElement(By.className("btn-primary"));
        log.info ("Execute the change passwordOld action item");
        oldPassword.click();
        oldPassword.clear();
        oldPassword.sendKeys(passwordNew);
        newPassword.click();
        newPassword.clear();
        newPassword.sendKeys(passwordOld);
        confirmPassword.click();
        confirmPassword.clear();
        confirmPassword.sendKeys(passwordOld);
        log.info("Click on the Update button to update to new password. In order to keep all tests working we need to change the password from the passwordNew  established one to passwordOld");        updateButton.click();
        log.info("Confirm that User is able to login after the second password change");
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
