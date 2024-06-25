package util;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage {

    WebDriver driver;
    ConfigReader configReader = new ConfigReader();


    public NavigationPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //step 1 navigate to the website
    public void NavigateToURL() throws Exception {

        driver.get(configReader.getURL());
    }

    public void verifyHomePage() throws Exception {

        String expTitle = configReader.getTitle();
        String actTitle = driver.getTitle();
        Assert.assertEquals(expTitle,actTitle);
    }

}
