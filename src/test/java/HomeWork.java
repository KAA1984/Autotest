import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void testRegistrationForm() {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/registration");

        WebElement nameLocator = driver.findElement(By.xpath("//input[@name='name']"));
        nameLocator.sendKeys("Andrey");

        WebElement lastNameLocator = driver.findElement(By.xpath("//input[@name='last_Name']"));
        lastNameLocator.sendKeys("Kalistratov");

        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");

        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("test");

        passwordInput.submit();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/login", currentUrl);
    }

    @Test
    public void clickCheckbox() {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://mdbootstrap.com/docs/standard/forms/checkbox/");
        WebElement checkboxElementInput = driver.findElement(By.id("inlineCheckbox1"));
        //THEN
       Assertions.assertFalse(checkboxElementInput.isSelected());
        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", checkboxElementInput);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
        //THEN
        Assertions.assertTrue(checkboxElementInput.isSelected());

    }

    @Test
    public void clickRadiobutton() {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
        WebElement radiobuttonElementInput = driver.findElement(By.xpath("//input[@id='flexRadioDefault1']"));
        //THEN
        Assertions.assertTrue(radiobuttonElementInput.isEnabled());
        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", radiobuttonElementInput);
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000L));
        //THEN
        Assertions.assertTrue(radiobuttonElementInput.isSelected());

    }
    @Test
    public void clickRadiobuttonJS() {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://www.javascripttutorial.net/javascript-dom/javascript-radio-button/");
        driver.switchTo().frame(0);
        WebElement radiobuttonElementInput = driver.findElement(By.xpath("//input[@id='xs']"));
        //THEN
        Assertions.assertTrue(radiobuttonElementInput.isEnabled());
        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", radiobuttonElementInput);
        //THEN
        Assertions.assertTrue(radiobuttonElementInput.isSelected());

    }
    @Test
    public void testLoginForm() {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/registration");

        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        //THEN
        Assertions.assertTrue(emailInput.isDisplayed());

        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        //THEN
        Assertions.assertTrue(passwordInput.isDisplayed());

    }
    @Test
    public void testLogin() {
        //GIVEN
        String actualUrl= "http://online-sh.herokuapp.com/products";
        //WHEN
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement elementButton = driver.findElement(By.className("btn-primary"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
        elementButton.click();
        String currentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(currentUrl,actualUrl);

    }
    @Test
    public void linkToAddProductsPage() throws InterruptedException {
        String expectedResultLlink = "http://online-sh.herokuapp.com/products/add";
        new WebDriverWait(driver, Duration.ofMillis(500L));
        driver.manage().window().maximize();
        //driver.get("http://online-sh.herokuapp.com/products");
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("test");
        passwordInput.submit();
        WebElement addProductsButton = driver.findElement(By.xpath("(//a[@href='/products/add'])"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()", addProductsButton);
        Assertions.assertEquals(expectedResultLlink, driver.getCurrentUrl());
    }

    @Test
    public void addingOfTheProducts() throws InterruptedException {
        String expectedResultLlink = "http://online-sh.herokuapp.com/products";
        new WebDriverWait(driver, Duration.ofMillis(500L));
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/products/add");
        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("test");
        passwordInput.submit();
        WebElement addProductsButton = driver.findElement(By.xpath("(//a[@href='/products/add'])"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()", addProductsButton);
        WebElement productNamefield = driver.findElement(By.id("exampleInputProduct1"));
        WebElement productPricefield = driver.findElement(By.id("exampleInputPrice1"));
        productNamefield.sendKeys("Test");
        productPricefield.sendKeys("1");
        productPricefield.submit();
        Assertions.assertEquals(expectedResultLlink, driver.getCurrentUrl());
    }

    @Test
    public void updatingOfTheProducts() {

        //String actualNameProducts = "TestUpdated2";
        String actualLink = "http://online-sh.herokuapp.com/products";
        new WebDriverWait(driver, Duration.ofMillis(500L));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/products/update?id=127");

        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("test");
        passwordInput.submit();

        WebElement updateButton = driver.findElement(By.xpath("//button[text() = 'Update']/preceding-sibling::input[@value='127']"));

        updateButton.submit();

        WebElement prodactNamefield = driver.findElement(By.id("exampleInputProduct1"));
        WebElement prodactPricefield = driver.findElement(By.id("exampleInputPrice1"));

        prodactNamefield.clear();
        prodactNamefield.sendKeys("TestUpdated7");

        prodactPricefield.clear();
        prodactPricefield.sendKeys("4");

        prodactPricefield.submit();

        WebElement updatedElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[text()[contains(., 'TestUpdated7')]])")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[td/text()='TestUpdated7']//td[2]/text()")));

        //WebElement updatedElement = driver.findElement(By.xpath("//*[contains(text(), '2022-05-30T11:04:21.594343')]"));
        //WebElement updatedElement = driver.findElement(By.xpath("//tr[td/text()='TestUpdated7']//td[2]/text()"));
        //String expectedNameProducts = "TestUpdated2";
        Assertions.assertTrue(updatedElement.isDisplayed());
    }

    @Test
    public void deleteOfTheProducts() throws InterruptedException {

        //String actualNameProducts = "2022-05-27T09:52:50.722818";

        new WebDriverWait(driver, Duration.ofMillis(500L));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/products");
        Actions actions = new Actions(driver);
        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("test");
        passwordInput.submit();
        //driver.switchTo().window("//button[text() = 'Delete']/preceding-sibling::input[@value='84']");

        WebElement deleteButton = driver.findElement(By.xpath("(//button[@class ='btn btn-outline-danger'][1])"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@class ='btn btn-outline-danger'][1])")));
        deleteButton.submit();
        //((JavascriptExecutor) driver).executeScript("arguments[0].click()", deleteButton);
        //boolean isElementActive = deleteButton.getDomAttribute("button type").contains("Delete");
       // Assertions.assertTrue(isElementActive);
        //((JavascriptExecutor) driver).executeScript("arguments[0].click()", deleteButton);
        //Assertions.assertTrue(deleteButton.isEnabled());

        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", deleteButton);
        //((JavascriptExecutor) driver).executeScript("arguments[0].click()", deleteButton);
        //Assertions.assertTrue(deleteButton.isSelected());
         //actions.moveToElement(deleteButton).click().perform();
        //deleteButton.submit();

        //String expectedNameProducts = null;
        //Assertions.assertEquals(expectedNameProducts,actualNameProducts);
        //Assertions.assertTrue(deleteButton.isSelected());
    }
    @Test
    public void lofOutFromTheProductsPage(){

        String actualNameProductsPage = "http://online-sh.herokuapp.com/login";

        new WebDriverWait(driver, Duration.ofMillis(500L));
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/products");

        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("test");
        passwordInput.submit();
        //driver.switchTo().window("//button[text() = 'Delete']/preceding-sibling::input[@value='84']");

        WebElement logOutButton = driver.findElement(By.xpath("//a[@href ='/logout']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href ='/logout']"))).click();

       // Actions action= new Actions(driver);
       // action.click();
        //boolean isElementActive = logOutButton.getAttribute("class").contains("Delete");
        //Assertions.assertTrue(isElementActive);
        //action.moveToElement(logOutButton).build().perform();
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", logOutButton);
       // boolean isElementActive = logOutButton.getAttribute("class").contains("Delete");
        //Assertions.assertTrue(isElementActive);
       //((JavascriptExecutor) driver).executeScript("arguments[0].click()", logOutButton);
        //logOutButton.submit();

        String expectedPage = driver.getCurrentUrl();
        Assertions.assertEquals(expectedPage,actualNameProductsPage);
    }

    @Test
    public void checkAvailableOfTheProductsTable(){
        new WebDriverWait(driver, Duration.ofMillis(500L));
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("test");
        passwordInput.submit();
        //driver.get("http://online-sh.herokuapp.com/products");

        List<WebElement> tableElements = driver.findElements(By.tagName("table"));

        org.assertj.core.api.Assertions.assertThat(tableElements.size()).isEqualTo(1);

    }

    @Test
    public void checkProductCountInTheProductTable(){
        new WebDriverWait(driver, Duration.ofMillis(500L));
        driver.manage().window().maximize();
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("test");
        passwordInput.submit();
        //driver.get("http://online-sh.herokuapp.com/products");

        List<WebElement> tableElements = driver.findElements(By.tagName("table"));
        List<WebElement> actualTableRows = tableElements.get(0).findElements(By.tagName("tbody")).
                get(0).findElements(By.tagName("tr"));
        System.out.println(actualTableRows.get(1).getText());
        org.assertj.core.api.Assertions.assertThat(actualTableRows.size()).isEqualTo(25);
        org.assertj.core.api.Assertions.assertThat(actualTableRows.get(1).getText()).contains("2022-06-04T03:12:25.592317");


    }


    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
