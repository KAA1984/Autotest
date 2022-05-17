import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FirstTest {
    private static WebDriver driver;

    public FirstTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000L));
    }

    @Test
    public void testSearchResultPresent_seleniumInput() {
        //GIVEN
        driver.get("https://www.google.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
        //WHEN
        input.sendKeys("QA automation");
        input.sendKeys(Keys.ENTER);
        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));


    }

    @Test
    public void testSearchResultPresent_JsInput() {
        //GIVEN
        driver.get("https://www.google.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
        //WHEN
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // set the text
        jsExecutor.executeScript("arguments[0].value='QA'", input);
        input.sendKeys(Keys.ENTER);

        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));


    }
    @Test
    public void shouldSearch_byId() {
        //GIVEN
        String expectedHeaderText = "Available relative locators";
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> headerElements = driver.findElements(By.id("not existing"));
        //THEN
        Assertions.assertTrue(headerElements.isEmpty());
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
    public void shouldSearch_byName() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement emailInputElement = driver.findElement(By.name("email"));
        //THEN
        Assertions.assertTrue(emailInputElement.isEnabled());
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
    public void shouldSearch_byClassName() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement submitButtonElement = driver.findElement(By.className("btn-primary"));
        //THEN
        Assertions.assertTrue(submitButtonElement.isEnabled());
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
    public void shouldSearch_byClassName_multipleElements() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        List<WebElement> inputElements = driver.findElements(By.className("form-control"));

        //THEN
        Assertions.assertEquals(2, inputElements.size());
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
    public void shouldSearch_byTagName() {
        //GIVEN
        int expectedUlsCount = 35;
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> headerElements = driver.findElements(By.tagName("ul"));
        //THEN
        Assertions.assertEquals(expectedUlsCount, headerElements.size());
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
    public void shouldSearch_byLink() {
        //GIVEN
        String expectedUrl = "https://www.selenium.dev/documentation/about/contributing/";
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        WebElement linkElement = driver.findElement(By.partialLinkText("contribution"));
        linkElement.click();
        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);
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
    public void shouldSearch_byCssSelector() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement inputElement = driver.findElement(By.cssSelector("input[type='Password']"));
        inputElement.sendKeys("verySecurePassword");
        //THEN
        Thread.sleep(1000L);
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
    public void shouldSearch_byCssSelector_parentChild() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements = driver.findElements(By.cssSelector("ul > li"));
        //THEN
        Assertions.assertEquals(177, elements.size());

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
                driver.findElements(By.cssSelector("#tabs-10 > li:nth-child(1) > a"));
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
    @Test
    public void byXpath() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/");
        WebElement headerElement = driver.findElement(By.xpath("(//div[@class='entry'])[3]//a"));
        headerElement.click();
        String currentUrl = driver.getCurrentUrl();
        //THEN
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("browser/");
    }

    @Test
    public void byRelativeLocator() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("test@test.com");
        Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
        Duration timeout = driver.manage().timeouts().getImplicitWaitTimeout();
        String currentTab = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(3000L);
        driver.switchTo().window(currentTab);
        //THEN
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));

    }

    @Test
    public void testClick() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");

        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("test@test.com");

        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1"));
        WebElement passwordInput = driver.findElement(passwordLocator);
        passwordInput.sendKeys("test");
        String classAttributeValue = passwordInput.getAttribute("class");
        String text = passwordInput.getText();

        //THEN
        boolean isEnabled = passwordInput.isEnabled();
        Assertions.assertTrue(isEnabled);
        boolean isDisplayed = passwordInput.isDisplayed();
        Assertions.assertTrue(isDisplayed);

        //WHEN
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]"));
        submitButton.click();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }

    @Test
    public void testSubmitForm() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");

        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("test@test.com");


        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1"));
        WebElement passwordInput = driver.findElement(passwordLocator);
        passwordInput.sendKeys("test");

        passwordInput.submit();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }

    @Test
    public void clickCheckbox() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://mdbootstrap.com/docs/standard/forms/checkbox/");
        WebElement checkboxElementInput = driver.findElement(By.xpath("//label[contains(.,' Default checkbox')]//preceding-sibling::input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", checkboxElementInput);

        //THEN
        Assertions.assertFalse(checkboxElementInput.isSelected());

        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", checkboxElementInput);
        Thread.sleep(3000L);
        //THEN
        Assertions.assertTrue(checkboxElementInput.isSelected());

    }


    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
