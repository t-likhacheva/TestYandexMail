package org.example.help_methods;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WebDriverSettings {


    public static WebDriver driver;
    public static BaseMethodsSelenium baseMethods;

    public static String HOMEURL = "https://mail.yandex.ru/";
    public static Integer PAGELOADTIMEOUT = 15;

    /**
     * Инициация веб-драйвера
     */
    @BeforeTest
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/test/java/org/example/help_methods/chromedriver.exe");
        driver = new ChromeDriver();
        baseMethods = new BaseMethodsSelenium(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGELOADTIMEOUT, TimeUnit.SECONDS);

    }


    /**
     * Окончание работы, закрытие сессии
     */
    @AfterTest
    public static void tearDown() throws Exception {
        log.info("Выходим из UI");
        driver.quit();
    }
}


