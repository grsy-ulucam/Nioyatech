package pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepdefinitions.ProjectSteps;
import utilities.ConfigReader;
import utilities.Driver;

public class ProjectPage {
    private WebDriver driver;

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='lui_6']")
    private WebElement email;

    @FindBy(xpath = "(//div[@role='button'])[2]")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@id='lui_4']")
    private WebElement password;

    @FindBy(xpath = "(//div[@role='button'])[4]")
    private WebElement loginButton;

    @FindBy(xpath="//div[@aria-label=\"User settings\"]" )
    private WebElement userSettings;

    @FindBy(xpath="(//div[@class=\"SingleSelectMenuItem\"])[3]" )
    private  WebElement test2Button;

    @FindBy(className = "TextInputBase SearchTextInput TopbarSearchTypeahead-searchInput")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@id='projects']/span")
    private WebElement projectButton;

    @FindBy(linkText = "First Project")
    private WebElement firstProject;

    @FindBy(xpath = "//div[@aria-label=\"Task-1\"]")
    private WebElement task;

    public void loginPage(){
        email.sendKeys(ConfigReader.getProperty("mail"));
        continueButton.click();
        password.sendKeys(ConfigReader.getProperty("password"));
        loginButton.click();
    }
    public void checkFirstProject() {
        userSettings.click();
        test2Button.click();
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[1].toString());
        searchBox.sendKeys(ProjectSteps.name + Keys.ENTER);
        projectButton.click();
        Assert.assertTrue(firstProject.isDisplayed());
    }

   public  void  checkFirstProjectTasks(){

       userSettings.click();
       test2Button.click();
       Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[1].toString());
       searchBox.sendKeys(ProjectSteps.firstTaskName + Keys.ENTER);
       Assert.assertTrue(task.getText().contains(ProjectSteps.firstTaskName));
   }
}
