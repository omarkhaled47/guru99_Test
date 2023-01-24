package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage {

    WebDriver driver = null;

    By emailID = By.name("emailid");
    By submitID = By.name("btnLogin");

    By UserID = By.xpath("/html/body/table/tbody/tr[4]/td[2]");

    By UserPass = By.xpath("/html/body/table/tbody/tr[5]/td[2]");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendID (String text) {
        driver.findElement(emailID).sendKeys(text);
    }

    public void submitID () {
        driver.findElement(submitID).click();
    }

    public String getID () {
        String ID = driver.findElement(UserID).getText();
        return ID;
    }

    public String getPass () {
        String pass = driver.findElement(UserPass).getText();
        return pass;
    }

}
