import junit.framework.TestCase;
import org.apache.log4j.Logger;
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

public class TestSearch {

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger log = Logger.getLogger(TestSearch.class);

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



//Test click on “See all x category results”
//This process describes following steps:
// ->  go to the search box on homepage-> introduce a keyword -> click on See All Category results from suggestion list
// assert that the link oppened contains  searched term (urn in this case )  related results.

    @Test
    public void testSearchBoxSeeAllCategoryResults() throws InterruptedException {
        log.info("Go to Homepage " );
        log.info("Go to Search Box in Header  " );
        log.info("Write urn " );
        Actions builder = new Actions(driver);
        WebElement searchBox = driver.findElement(By.id("SearchInputs")).findElement(By.tagName("input"));
        searchBox.click();
        searchBox.sendKeys("urn");
        log.info("Click on See Al Category Results " );
        WebElement categoryResults = driver.findElement(By.partialLinkText("category results"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(categoryResults));
        builder.moveToElement(categoryResults).build().perform();
        log.info("Confirm Urn Related Results  Page is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("urn"));
    }

// Test click on “See all x product  results “
//This process describes following steps:
// ->  go to the search box on homepage-> introduce a keyword -> click on  See All x product results from suggestion list
// -> assert that the link oppened contains  searched term (urn in this case )  related results.

    @Test
    public void testSearchBoxSeeAllProductsResult() throws InterruptedException {
        log.info("Go to Homepage " );
        log.info("Go to Search Box in Header  " );
        log.info("Write urn " );
        Actions builder = new Actions(driver);
        WebElement searchBox = driver.findElement(By.id("SearchInputs")).findElement(By.tagName("input"));
        searchBox.click();
        searchBox.sendKeys("urn");
        log.info("Click on See Al Category Results " );
        WebElement productResults = driver.findElement(By.partialLinkText("product results"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(productResults));
        builder.moveToElement(productResults).build().perform();
        log.info("Confirm Urn Related Results  Page is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("urn"));
    }

//Test open first category related  result
//This process describes following steps:
//-> go to the search box on homepage-> introduce a keyword -> click on first result from category suggestion list
//- > assert that the link oppened contains  searched term (urn in this case )  related results.

        @Test
    public void testSearchBoxCategoriesList() throws InterruptedException {
            log.info("Go to Homepage " );
            log.info("Go to Search Box in Header  " );
            log.info("Write urn " );
        Actions builder = new Actions(driver);
        WebElement searchBox = driver.findElement(By.id("SearchInputs")).findElement(By.tagName("input"));
        searchBox.click();
        searchBox.sendKeys("urn");
        WebElement categoryResults = driver.findElement(By.partialLinkText("category results"));
        List<WebElement> categoryResultsList = driver.findElement(By.className("categories")).findElements(By.tagName("a"));
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(categoryResults));
         log.info("Click on first result from category suggestion list" );
        categoryResultsList.get(0).click();
         log.info("Confirm that the link oppened contains  searched term (urn in this case )  related results.");
        assertTrue(driver.getCurrentUrl().contains("urn"));
    }

//Test go to advanced search page
//This process describes following steps:
//-> go to homepage-> search for advanced search link in footer  -> click on Advanced Search
//-> assert that the link oppened is the advanced search page

    @Test
    public void testAdvancedSearchPage() throws InterruptedException {
        log.info("Go to Homepage " );
        log.info("Click on Advanced Search link in footer " );
        WebElement advancedSearchButton = driver.findElement(By.partialLinkText("Advanced Search"));
        advancedSearchButton.click();
        log.info("Confirm Adavanced Search Page is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("search-advanced"));
    }

//Test Display  results in advanced search page
//This process describes following steps:
//-> go to homepage-> search for advanced search link in footer  -> click on Advanced Search -> go to search cassette in Advanced Search Page -> introduce a search term (sculpture)
//-> validate that results are related to "keywords=sculpture"

    @Test
    public void testAdvancedSearchResult() throws InterruptedException {
        log.info("Go to Homepage " );
        log.info("Click on Advanced Search link in footer " );
        WebElement advancedSearchButton = driver.findElement(By.partialLinkText("Advanced Search"));
        advancedSearchButton.click();
        WebElement searchCassette = driver.findElement(By.id("AdvancedSearchResults")).findElement(By.tagName("input"));
        WebElement searchButton = driver.findElement(By.id("AdvancedSearchResults")).findElement(By.className("btn-standard"));
        searchCassette.click();
        searchCassette.clear();
        log.info("Click in Search Casetter and introduce search term sculpture" );
        searchCassette.sendKeys("sculpture");
        log.info("Click on search Button " );
        searchButton.click();
        log.info("Confirm Adavanced Search Page by keywords=sculpture is displayed in browser" );
        assertTrue(driver.getCurrentUrl().contains("keywords=sculpture"));
    }

//Test category type results in Advanced search page
//This process describes following steps:
//-> search for advanced search link in footer  -> click on Advanced Search -> go to search cassette in Advanced Search Page-> introduce a search term (sculpture) -> open first result in category results
// -> validate that the page oppened is a category type  page

    @Test
    public void testAdvancedSearchCategoryResult() throws InterruptedException {
        log.info("Go to Homepage " );
        log.info("Click on Advanced Search link in footer " );
        WebElement advancedSearchButton = driver.findElement(By.partialLinkText("Advanced Search"));
        advancedSearchButton.click();
        WebElement searchCassette = driver.findElement(By.id("AdvancedSearchResults")).findElement(By.tagName("input"));
        WebElement searchButton = driver.findElement(By.id("AdvancedSearchResults")).findElement(By.tagName("button"));
        searchCassette.click();
        searchCassette.sendKeys("sculpture");
        log.info("Click on search Button " );
        searchButton.click();
        log.info("Identify the Category Card list in results" );
        List<WebElement> productGrid = driver.findElements(By.tagName("a"));
        List<WebElement> categoryItems = new ArrayList<WebElement>();
        for (WebElement prod : productGrid)
            if (prod.getAttribute("class").contains("category-item"))
                categoryItems.add(prod);
        TestCase.assertTrue(categoryItems.size() != 0);
        log.info("Click first Category Card in results" );
        categoryItems.get(0).click();
        log.info("Confirm category type page is displayed in browser" );
        TestCase.assertTrue(driver.getCurrentUrl().contains("-c-"));
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




