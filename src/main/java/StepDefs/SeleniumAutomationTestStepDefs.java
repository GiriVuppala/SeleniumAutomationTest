package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.*;

import java.time.Duration;


public class SeleniumAutomationTestStepDefs {

    WebDriver driver ;
    NavigationPage navigationPage;
    HomePage homePage ;
    LoginPage loginPage;
    ConfigReader configReader = new ConfigReader();

    @Before
    public void setUpBrowser() throws Exception {

        String os = System.getProperty("os.name").toLowerCase();
        String browser = configReader.getBrowser();
        if(browser.equalsIgnoreCase("firefox")) {
            if (os.contains("mac")) {
                System.setProperty("webdriver.gecko.driver", "browsers/geckodriver");
            } else {
                System.setProperty("webdriver.gecko.driver", "browsers/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("chrome"))
        {
            if(os.contains("mac")) {
                System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
            }else {
                System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
            }
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("edge"))
        {
            if(os.contains("mac")) {
                System.setProperty("webdriver.edge.driver", "browsers/msedgedriver");
            }else {
                System.setProperty("webdriver.edge.driver", "browsers/msedgedriver.exe");
            }
            driver = new EdgeDriver();
        }
        else
        {
            throw new Exception("Browser not found");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @io.cucumber.java.en.Given("^open browser and navigate to url$")
    public void openBrowserAndNavigateToUrl() throws Exception {

        navigationPage = new NavigationPage(driver);
        navigationPage.NavigateToURL();
        navigationPage.verifyHomePage();

    }


    @When("login with {string} and {string}")
    public void loginWithAnd(String usrName, String pwd) {

        loginPage = new LoginPage(driver);
        loginPage.Login(usrName,pwd);

    }

    @Then("select item with highest price and verify the item in cart")
    public void selectItemWithHighestPriceAndVerifyTheItemInCart() {

        homePage = new HomePage(driver);
        homePage.addHighestPriceItemToCart();
        homePage.verifyCartItemPrice();
    }

    @After
    public void quitBrowser()
    {
        driver.close();
        driver.quit();
    }
}
