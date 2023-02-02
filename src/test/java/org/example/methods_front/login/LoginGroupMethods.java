package org.example.methods_front.login;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.BaseMethodsSelenium;
import org.example.help_methods.WebDriverSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Классы помошники для страниц логина
 */
@Slf4j
public class LoginGroupMethods extends WebDriverSettings {

    public static BaseMethodsSelenium baseMethods;
    public static LoginGroupMethods loginGroupMethods;

    public LoginGroupMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * Кнопка Войти в почту
     */
    @FindBy(xpath = "//span[contains(text(),'Войти в Почту')]/..")
    public static WebElement button_login;

    /**
     * Кнопка Выбрать почту для ввода логина
     */
    @FindBy(xpath = "//span[contains(text(),'Почта')]/..")
    public static WebElement button_selectMail;
    /**
     * Поле ввода почтового логина
     */
    @FindBy(id = "passp-field-login")
    public static WebElement input_login;
    /**
     * Кнопка Войти
     */
    @FindBy(id = "passp:sign-in")
    public static WebElement button_enter;
    @FindBy(id = "passp-field-passwd")
    /**Поле ввода пароля */
    public static WebElement input_pass;
    public static String LOGIN = "test0.testovy";
    public static String PASSWORD = "fotjavatest";



    /**
     * ввод логина и пароля (через аннотации и переменные класса )
     */
    public static void login() {

        baseMethods = new BaseMethodsSelenium(driver);
        loginGroupMethods = new LoginGroupMethods(driver);
        driver.get(HOMEURL);
        log.info("Открыли стартовую страницу");
        if (button_login == null) {
            log.info("error button_login is null");
            return;
        }
        button_login.click();
        log.info("Перешли на страницу логина");

        if (button_selectMail != null) {
            button_selectMail.click();
            input_login.sendKeys(LOGIN);
            button_enter.click();
        }
        input_pass.sendKeys(PASSWORD);
        button_enter.click();

    }

    /**
     * ввод логина и пароля (через методы вебдрайвера)
     */
    public static void login1() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        driver.get(HOMEURL);
        log.info("Открыли стартовую страницу");

        driver.findElement(By.xpath("//span[contains(text(),'Войти в Почту')]/..")).click();
        System.out.println("Перешли на страницу логина333");
        WebElement loginField = driver.findElement(By.xpath("//span[contains(text(),'Почта')]/.."));
        if (loginField.isDisplayed()) {
            loginField.click();
            System.out.println("Выбрали почту");
            driver.findElement(By.id("passp-field-login")).sendKeys(LOGIN);
            driver.findElement(By.id("passp:sign-in")).click();
            System.out.println("Ввели логин ");
        }
        driver.findElement(By.id("passp-field-passwd")).sendKeys(PASSWORD);
        driver.findElement(By.id("passp:sign-in")).click();
        System.out.println("Ввели пароль");


    }

    public static void logout() {

        driver.quit();
    }
}
