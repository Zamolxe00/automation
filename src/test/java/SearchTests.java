import junit.framework.TestCase;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SearchTests {

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

    //Test Keyword search in homepage header
//See all categories results
    @Test
    public void testSearchBoxCategories() throws InterruptedException {
        Actions builder = new Actions(driver);
        WebElement searchBox = driver.findElement(By.id("SearchInputs")).findElement(By.tagName("input"));
        searchBox.click();
        searchBox.sendKeys("urn");
        WebElement categoryResults = driver.findElement(By.partialLinkText("category results"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(categoryResults));
        builder.moveToElement(categoryResults).build().perform();
        assertTrue(driver.getCurrentUrl().contains("urn"));
    }

    //See all products results
    @Test
    public void testSearchBoxProducts() throws InterruptedException {
        Actions builder = new Actions(driver);
        WebElement searchBox = driver.findElement(By.id("SearchInputs")).findElement(By.tagName("input"));
        searchBox.click();
        searchBox.sendKeys("urn");
        WebElement categoryResults = driver.findElement(By.partialLinkText("product results"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(categoryResults));
        builder.moveToElement(categoryResults).build().perform();
        assertTrue(driver.getCurrentUrl().contains("urn"));
    }

    //Click on categories result  from suggestion list - not working
    @Test
    public void testSearchBoxCategoriesList() throws InterruptedException {
        Actions builder = new Actions(driver);
        WebElement searchBox = driver.findElement(By.id("SearchInputs")).findElement(By.tagName("input"));
        searchBox.click();
        searchBox.sendKeys("urn");
        WebElement categoryResults = driver.findElement(By.partialLinkText("category results"));
        List<WebElement> categoryResultsList = driver.findElement(By.className("categories")).findElements(By.tagName("a"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(categoryResults));
        categoryResultsList.get(0).click();
        assertTrue(driver.getCurrentUrl().contains("urn"));
    }

    //Click on specific products results from suggestion list - not working
//Test Advanced search in homepage footer
//Page available
    @Test
    public void testAdvancedSearch() throws InterruptedException {
        WebElement advancedSearchButton = driver.findElement(By.partialLinkText("Advanced Search"));
        advancedSearchButton.click();
        assertTrue(driver.getCurrentUrl().contains("search-advanced"));
    }

    //Display results
    @Test
    public void testAdvancedSearchResult() throws InterruptedException {
        WebElement advancedSearchButton = driver.findElement(By.partialLinkText("Advanced Search"));
        advancedSearchButton.click();
        WebElement searchCassette = driver.findElement(By.id("AdvancedSearchResults")).findElement(By.tagName("input"));
        WebElement searchButton = driver.findElement(By.id("AdvancedSearchResults")).findElement(By.className("btn-standard"));
        searchCassette.click();
        searchCassette.clear();
        searchCassette.sendKeys("sculpture");
        searchButton.click();
        assertTrue(driver.getCurrentUrl().contains("keywords=sculpture"));
    }

    //test category type results
    @Test
    public void testAdvancedSearchCategoryResult() throws InterruptedException {
        WebElement advancedSearchButton = driver.findElement(By.partialLinkText("Advanced Search"));
        advancedSearchButton.click();
        WebElement searchCassette = driver.findElement(By.id("AdvancedSearchResults")).findElement(By.tagName("input"));
        WebElement searchButton = driver.findElement(By.id("AdvancedSearchResults")).findElement(By.tagName("button"));
        searchCassette.click();
        searchCassette.sendKeys("sculpture");
        searchButton.click();
        List<WebElement> productGrid = driver.findElements(By.tagName("a"));
        List<WebElement> categoryItems = new ArrayList<WebElement>();
        for (WebElement prod : productGrid)
            if (prod.getAttribute("class").contains("category-item"))
                categoryItems.add(prod);
        TestCase.assertTrue(categoryItems.size() != 0);
        categoryItems.get(0).click();
        TestCase.assertTrue(driver.getCurrentUrl().contains("-c-"));
    }

    //test product  type results --not working
    @After
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}




