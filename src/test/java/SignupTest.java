import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.NewCustomerPage;
import pages.SignupPage;

public class SignupTest {

    static WebDriver driver = null;

    SignupPage signupObjc;
    LoginPage logObjc;
    HomePage homObjc;
    NewCustomerPage customerObjc;


    String userId;
    String userPass;


    @BeforeTest
    public static void Setup () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/");
    }


    @Test (priority = 0)
    public void signupTest() {
        signupObjc = new SignupPage(driver);
        signupObjc.sendID("octoTest@gmail.com");
        signupObjc.submitID();
        userId = signupObjc.getID();
        userPass = signupObjc.getPass();
        driver.navigate().to("https://www.demo.guru99.com/V4/");
    }


    @Test (priority = 1)
    public void invalidLoginTest_1() {
        String label = "User or Password is not valid";
        logObjc = new LoginPage(driver);
        logObjc.enterID(" ");
        logObjc.enterPass(userPass);
        logObjc.login();
        Assert.assertEquals(label,logObjc.alertMessage());
        logObjc.handleAlert();
    }

    @Test (priority = 2)
    public void invalidLoginTest_2() {
        String label = "User or Password is not valid";
        logObjc = new LoginPage(driver);
        logObjc.enterID(userId);
        logObjc.enterPass(" ");
        logObjc.login();
        Assert.assertEquals(label,logObjc.alertMessage());
        logObjc.handleAlert();
    }

    @Test (priority = 3)
    public void invalidLoginTest_3() {
        String label = "User or Password is not valid";
        logObjc = new LoginPage(driver);
        logObjc.enterID("testID");
        logObjc.enterPass(userPass);
        logObjc.login();
        Assert.assertEquals(label,logObjc.alertMessage());
        logObjc.handleAlert();
    }

    @Test (priority = 4)
    public void invalidLoginTest_4() {
        String label = "User or Password is not valid";
        logObjc = new LoginPage(driver);
        logObjc.enterID(userId);
        logObjc.enterPass("testPassword");
        logObjc.login();
        Assert.assertEquals(label,logObjc.alertMessage());
        logObjc.handleAlert();
    }

    @Test (priority = 5)
    public void validLoginTest() {
        logObjc = new LoginPage(driver);
        logObjc.enterID(userId);
        logObjc.enterPass(userPass);
        logObjc.login();
    }

    @Test (priority = 6)
    public void goToNewCustomer() {
        homObjc = new HomePage(driver);
        homObjc.addNewCustomer();
    }

    @Test (priority = 7)
    public void emptyCustomerName() {
        String labelName = "Customer name must not be blank";
        customerObjc = new NewCustomerPage(driver);
        customerObjc.addCustomerName("");
        customerObjc.selectMale();
        customerObjc.dateOfBirth("02111998");
        customerObjc.setAddress("Nasr City");
        customerObjc.setCity("Cairo");
        customerObjc.setState("Cairo");
        customerObjc.setPin("001947");
        customerObjc.setMobileNumber("01116300821");
        customerObjc.setEmail("test@gmail.com");
        customerObjc.setPassword("testPassword");
        Assert.assertEquals(labelName,customerObjc.emptyCustomerName());
        customerObjc.reset();
    }

    @Test (priority = 8)
    public void addInValidPIN() {
        String labelPIN = "PIN Code must have 6 Digits";
        customerObjc = new NewCustomerPage(driver);
        customerObjc.addCustomerName("Omar Khaled");
        customerObjc.selectMale();
        customerObjc.dateOfBirth("02111998");
        customerObjc.setAddress("Nasr City");
        customerObjc.setCity("Cairo");
        customerObjc.setState("Cairo");
        customerObjc.setPin("1947");
        customerObjc.setMobileNumber("01116300821");
        customerObjc.setEmail("test@gmail.com");
        customerObjc.setPassword("testPassword");
        Assert.assertEquals(labelPIN,customerObjc.invalidPIN());
        customerObjc.reset();
    }

    @Test (priority = 9)
    public void addInValidNumber() {
        String labelNumber = "Characters are not allowed";
        customerObjc = new NewCustomerPage(driver);
        customerObjc.addCustomerName("Omar Khaled");
        customerObjc.selectMale();
        customerObjc.dateOfBirth("02111998");
        customerObjc.setAddress("Nasr City");
        customerObjc.setCity("Cairo");
        customerObjc.setState("Cairo");
        customerObjc.setPin("001947");
        customerObjc.setMobileNumber("test");
        customerObjc.setEmail("test@gmail.com");
        customerObjc.setPassword("testPassword");
        Assert.assertEquals(labelNumber,customerObjc.invalidNumber());
        customerObjc.reset();
    }


    @Test (priority = 10)
    public void addValidCustomer() {
        customerObjc = new NewCustomerPage(driver);
        customerObjc.addCustomerName("Omar Khaled");
        customerObjc.selectMale();
        customerObjc.dateOfBirth("02111998");
        customerObjc.setAddress("Nasr City");
        customerObjc.setCity("Cairo");
        customerObjc.setState("Cairo");
        customerObjc.setPin("001947");
        customerObjc.setMobileNumber("01116300821");
        customerObjc.setEmail("test@gmail.com");
        customerObjc.setPassword("testPassword");
        customerObjc.addNewCustomerSucess();
    }


    @AfterTest
    public static void close() {
        driver.quit();
    }

    }

