import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.containsString;


public class OrderElementsTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        driver = new ChromeDriver();

    }
    @Test

    public void createFirstOrder() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
// время ожидания 3 сек
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("App_CookieButton__3cvqF")));
        OrderElements objOrderElements = new OrderElements(driver);
        objOrderElements.clickCookieButton();
// клик на кнопку "Заказать"-----------------------------
        objOrderElements.clickTopOrderButton();
// заполнение первой страницы заказа
        objOrderElements.fillFirstPage(
                "Ильин",
                "Виктор",
                "Пушкина",
                "+79173232323");
// заполнение второй страницы заказа
        objOrderElements.fillSecondPage(
                "Самокаткат");
// подтверждение оформления заказа
        objOrderElements.clickYesButton();
        String actualOrderText = objOrderElements.checkOrderIsDone();
        String exceptedOrderText = "Заказ оформлен";
        MatcherAssert.assertThat(actualOrderText, containsString(exceptedOrderText));
    }
    @Test

    public void createSecondOrder() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Home_FinishButton__1_cWm")));
        OrderElements objOrderElements = new OrderElements(driver);
        objOrderElements.clickCookieButton();
// Скролл вниз
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
// Оформление заказа через нижнюю кнопку --------------------------
        objOrderElements.clickBottomOrderButton();
        objOrderElements.fillFirstPage(
                "Абубакар",
                "Бандит",
                "Нохчинская",
                "+79242281488");
        objOrderElements.fillSecondPage(
                "Я не твоя мама, дон");
        objOrderElements.clickYesButton();
        String actualOrderText = objOrderElements.checkOrderIsDone();
        String exceptedOrderText = "Заказ оформлен";
        MatcherAssert.assertThat(actualOrderText, containsString(exceptedOrderText));
    }
    @After
    public void teardown () {
        driver.quit();
    }

}
