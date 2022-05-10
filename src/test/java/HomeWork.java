import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class HomeWork {
    private static WebDriver driver;

    public HomeWork() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000L));
    }

    @Test
    public void shouldSearch_byId_Alerts() {
        //GIVEN
        String expectedHeaderTextAlerts = "Available relative locators";
        //WHEN
        driver.get("https://alerts.in.ua/");
        WebElement headerElement = driver.findElement(By.id("errorMessage"));
        //THEN
        Assertions.assertTrue(headerElement.isEnabled());
    }

    @Test
    public void shouldSearch_byName_Alerts() {
        //GIVEN
        //WHEN
        driver.get("https://alerts.in.ua/");
        WebElement emailInputElement = driver.findElement(By.name("viewport"));
        //THEN
        Assertions.assertTrue(emailInputElement.isEnabled());
    }

    @Test
    public void shouldSearch_byClassName_Alerts() {
        //GIVEN
        //WHEN
        driver.get("https://alerts.in.ua/");
        WebElement submitButtonElement = driver.findElement(By.className("map-header"));
        //THEN
        Assertions.assertTrue(submitButtonElement.isEnabled());
    }

    @Test
    public void shouldSearch_byClassName_multipleElements_Alerts() {
        //GIVEN
        //WHEN
        driver.get("https://alerts.in.ua/");
        List<WebElement> inputElements = driver.findElements(By.className("screen"));

        //THEN
        Assertions.assertEquals(5, inputElements.size());
    }
    @Test
    public void shouldSearch_byTagName_Alerts() {
        //GIVEN
        int expectedUlsCount = 226;
        //WHEN
        driver.get("https://alerts.in.ua/");
        List<WebElement> headerElements = driver.findElements(By.tagName("g"));
        //THEN
        Assertions.assertEquals(expectedUlsCount, headerElements.size());
    }

    @Test
    public void shouldSearch_byLink_Alerts() {
        //GIVEN
        String expectedUrl = "https://t.me/air_alert_ua";
        //WHEN
        driver.get("https://alerts.in.ua/");
        WebElement linkElement = driver.findElement(By.partialLinkText("Повітряна тривога"));
        linkElement.click();
        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);
    }

    @Test
    public void shouldSearch_byLink_Minfin() throws InterruptedException {
        //GIVEN
        String expectedUrl = "https://minfin.com.ua/currency/";
        //WHEN
        driver.get("https://minfin.com.ua/");
        WebElement linkElement = driver.findElement(By.partialLinkText("Валюта"));
        linkElement.click();
        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);
    }

    @Test
    public void shouldSearch_byCssSelector_Minfin() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("https://minfin.com.ua/ua/");
        WebElement clickElement = driver.findElement(By.cssSelector("div.js-toggle-auth"));
        clickElement.click();
        //THEN
        Thread.sleep(1000L);
    }

    @Test
    public void shouldSearch_byCssSelector_parentChild_Minfin() {
        //GIVEN
        //WHEN
        driver.get("https://minfin.com.ua/");
        List<WebElement> elements = driver.findElements(By.cssSelector("div > a"));
        //THEN
        Assertions.assertEquals(357, elements.size());

    }

    @Test
    public void shouldSearch_byCssSelector_complexStructure() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements =
                driver.findElements(By.cssSelector("#tabs-10 > li:nth-child(2) > a"));
        //THEN
        Assertions.assertEquals(1, elements.size());
        boolean isElementActive = elements.get(0).getAttribute("class").contains("active");
        Assertions.assertTrue(isElementActive);

    }

    @Test
    public void shouldSearch_byXpath_Alerts() {
        //GIVEN
        //WHEN
        driver.get("https://alerts.in.ua/");
        WebElement element = driver.findElement(By.xpath("//div[@id='map']"));
        //THEN
        Assertions.assertTrue(element.isEnabled());

    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
