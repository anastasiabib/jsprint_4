package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    /** Веб-драйвер */
    private final WebDriver webDriver;

    /** Заголовок для раскрывающегося блока */
    private final By accordionHeaders = By.className("accordion__heading");

    /** Абзац в раскрывающемся блоке */
    private final By accordionItems = By.xpath(".//div[@class='accordion__panel']/p");

    /** Кнопка оформления заказа в шапке сайта */
    private final By orderButtonHeader = By.xpath(".//div[starts-with(@class, 'Header_Nav')]//button[starts-with(@class, 'Button_Button')]");

    /** Кнопка оформления заказа в теле сайта */
    private final By orderButtonBody = By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button')]");

    /** Кнопка "Да все привыкли" для принятия куки */
    private final By cookieAcceptButton = By.id("rcc-confirm-button");

    /**
     * Конструктор класса MainPage. Создает новый экземпляр образа главной страницы
     */
    public MainPage(WebDriver driver) {
        this.webDriver = driver;
    }

    /**
     * Метод для ожидания загрузки элемента аккордеона
     */
    public void waitForLoadItem(int index) {
        new WebDriverWait(this.webDriver, 3)
                .until(ExpectedConditions.visibilityOf(this.webDriver.findElements(this.accordionItems).get(index)));
    }

    /**
     * Метод для нажатия на кнопку "Да все привыкли" для принятия куки
     */
    public void clickOnCookieAcceptButton() {
        this.webDriver.findElement(this.cookieAcceptButton).click();
    }

    /**
     * Метод для получения текста на заголовке блока в аккордеоне
     */
    public String getAccordionHeaderText(int index) {
        return this.webDriver.findElements(this.accordionHeaders).get(index).getText();
    }

    /**
     * Метод для получения текста из раскрывающегося блока в аккордеоне
     */
    public String getAccordionItemText(int index) {
        return this.webDriver.findElements(this.accordionItems).get(index).getText();
    }

    /**
     * Метод для нажатия на заголовок блока в аккордеоне
     */
    public void clickAccordionHeader(int index) {
        this.webDriver.findElements(this.accordionHeaders).get(index).click();
    }

    /**
     * Метод для проверки раскрытия блока аккордеона
     */
    public boolean isAccordionItemDisplayed(int index) {
        return this.webDriver.findElements(this.accordionItems).get(index).isDisplayed();
    }

    /**
     * Метод для нажатия на кнопку оформления заказа в правом верхнем углу сайта
     */
    public void clickOrderButtonHeader() {
        this.webDriver.findElement(this.orderButtonHeader).click();
    }

    /**
     * Метод для нажатия на кнопку оформления заказа в середине главной страницы сайта
     */
    public void clickOrderButtonBody() {
        this.webDriver.findElement(this.orderButtonBody).click();
    }
}