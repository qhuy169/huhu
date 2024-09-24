package hunre.it.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testExistingUsername() {
        driver.get("http://127.0.0.1:5500/test.html");
        WebElement username = driver.findElement(By.id("username"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Đăng Ký']"));

        username.sendKeys("existinguser");
        email.sendKeys("existinguser@example.com");
        password.sendKeys("StrongPassword123");
        registerButton.click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertTrue(message.getText().contains("Tên người dùng đã tồn tại."), "Test Case 1 Failed");
    }

    @Test
    public void testWeakPassword() {
        driver.get("http://127.0.0.1:5500/test.html");
        WebElement username = driver.findElement(By.id("username"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Đăng Ký']"));

        username.sendKeys("newuser");
        email.sendKeys("newuser@example.com");
        password.sendKeys("weak");
        registerButton.click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertTrue(message.getText().contains("Mật khẩu không đủ mạnh."), "Test Case 2 Failed");
    }

    @Test
    public void testSuccessfulRegistration() {
        driver.get("http://127.0.0.1:5500/test.html");
        WebElement username = driver.findElement(By.id("username"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Đăng Ký']"));

        username.sendKeys("newuser");
        email.sendKeys("newuser@example.com");
        password.sendKeys("StrongPassword123");
        registerButton.click();
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertTrue(message.getText().contains("Đăng ký thành công!"), "Test Case 3 Failed");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}