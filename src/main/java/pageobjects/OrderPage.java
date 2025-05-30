package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class OrderPage {
    /** Веб-драйвер */
    private final WebDriver webDriver;

    /** Форма заказа */
    private final By orderForm = By.xpath(".//div[starts-with(@class, 'Order_Form')]");

    /** Поле для ввода имени */
    private final By nameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Имя')]");

    /** Поле для ввода фамилии */
    private final By surnameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Фамилия')]");

    /** Поле для ввода адреса */
    private final By addressInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Адрес')]");

    /** Поле для ввода метро*/
    private final By metroInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Станция метро')]");

    /** Поле для ввода телефона */
    private final By phoneInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Телефон')]");

    /** Выпадающий список для списка доступных станций метро */
    private final By metroList = By.className("select-search__select");

    /** Список доступных для выбора станций метро */
    private final By metroListItems = By.xpath(".//div[@class='select-search__select']//div[starts-with(@class,'Order_Text')]");

    /** Кнопка "Далее" */
    private final By nextButton = By.xpath(".//div[starts-with(@class, 'Order_NextButton')]/button");

    /** Выбранная в календарике дата */
    private final By dateSelected = By.className("react-datepicker__day--selected");

    /** Поле для ввода даты */
    private final By dateInput = By.xpath(".//div[starts-with(@class, 'react-datepicker__input-container')]//input");

    /** Обёртка для выпадающего списка сроков аренды */
    private final By termDropdownRoot = By.className("Dropdown-root");

    /** Список доступных для выбора срока аренды */
    private final By termDropdownOption = By.className("Dropdown-option");

    /** Список доступных для выбора цветов */
    private final By colorLabels = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]//label");

    /** Поле для ввода комментария курьеру*/
    private final By commentInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Комментарий')]");

    /** Кнопка "Заказать" */
    private final By orderButton = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[not(contains(@class,'Button_Inverted'))]");

    /** Кнопка "Да" в окошке с подтверждением заказа */
    private final By acceptOrderButton = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//button[not(contains(@class,'Button_Inverted'))]");

    /** Текст об успешном оформлении заказа во всплывающем окошке */
    private final By newOrderSuccessMessage = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//div[(starts-with(@class,'Order_ModalHeader'))]");

    /**Конструктор для класса OrderPage*/

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Метод для ожидания загрузки формы заказа
     */
    public void waitForLoadForm() {
        new WebDriverWait(this.webDriver, 3)
                .until(ExpectedConditions.visibilityOf(this.webDriver.findElement(this.orderForm)));
    }

    /**
     * Метод для ожидания загрузки элемента страницы
     */
    private void waitForElementLoad(By elementToLoad) {
        new WebDriverWait(this.webDriver, 3)
                .until(ExpectedConditions.visibilityOf(this.webDriver.findElement(elementToLoad)));

    }

    /**
     * Метод для установки значения в поле "Имя"
     */
    public void setName(String name) {
        this.webDriver.findElement(this.nameInput).sendKeys(name);
    }

    /**
     * Метод для установки значения в поле "Фамилия"
     */
    public void setSurname(String surname) {
        this.webDriver.findElement(this.surnameInput).sendKeys(surname);
    }

    /**
     * Метод для установки значения в поле "Адрес"
     */
    public void setAddress(String address) {
        this.webDriver.findElement(this.addressInput).sendKeys(address);
    }

    /**
     * Метод для установки значения в поле "Станция метро"
     */
    public void setMetro(String metro) {
        this.webDriver.findElement(this.metroInput).sendKeys(metro);
        this.waitForElementLoad(this.metroList);
        this.chooseElementFromDropdown(this.metroListItems, metro);
    }

    /**
     * Метод для установки значения в поле "Телефон"
     */
    public void setPhone(String phone) {
        this.webDriver.findElement(this.phoneInput).sendKeys(phone);
    }

    /**
     * Метод для нажатия на кнопку "Далее" для продолжения оформления заказа
     */
    public void clickNextButton() {
        this.webDriver.findElement(this.nextButton).click();
    }

    /**
     * Метод для установки значения в поле "Дата"
     */
    public void setDate(String date) {
        this.webDriver.findElement(this.dateInput).sendKeys(date);
        this.waitForElementLoad(this.dateSelected);
        this.clickDateSelected();
    }

    /**
     * Метод для установки значения в поле "Срочность аренды"
     */
    public void setTerm(String termToChoose) {
        this.clickTermDropdown();
        this.chooseElementFromDropdown(this.termDropdownOption, termToChoose);
    }

    /**
     * Метод для установки значения в поле "Цвет"
     */
    public void setColor(String colorToChoose) {
        this.chooseElementFromDropdown(this.colorLabels, colorToChoose);
    }

    /**
     * Метод для установки значения в поле "Комментарий"
     */
    public void setComment(String comment) {
        this.webDriver.findElement(this.commentInput).sendKeys(comment);
    }

    /**
     * Метод для оформления заказа
     */
    public void makeOrder() {
        this.clickOrderButton();
        this.waitForElementLoad(this.acceptOrderButton);
        this.clickAcceptOrderButton();
    }

    /**
     * Метод для получения сообщения об успешном оформлении заказа
     */
    public String getNewOrderSuccessMessage() {
        return this.webDriver.findElement(this.newOrderSuccessMessage).getText();
    }

    /**
     * Метод для нажатия на кнопку "Заказать"
     */
    private void clickOrderButton() {
        this.webDriver.findElement(this.orderButton).click();
    }

    /**
     * Метод для нажатия на кнопку подтверждения заказа
     */
    private void clickAcceptOrderButton() {
        this.webDriver.findElement(this.acceptOrderButton).click();
    }

    /**
     * Метод для выбора элемента выпадающего списка
     */
    private void chooseElementFromDropdown(By dropdownElements, String elementToChoose) {
        List<WebElement> elementsFiltered = this.webDriver.findElements(dropdownElements);
        for (WebElement element : elementsFiltered) {
            if (element.getText().equals(elementToChoose)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Метод для нажатия на выбранную дату в календаре
     */
    private void clickDateSelected() {
        this.webDriver.findElement(this.dateSelected).click();
    }
    /**
     * Метод для раскрытия списка сроков аренды
     */
    private void clickTermDropdown() {
        this.webDriver.findElement(this.termDropdownRoot).click();
    }
    /**
     * Метод, описывающий процедуру оформления заказа
     */
    private void makeOrder(OrderPage orderPage) {
        orderPage.waitForLoadForm();

        orderPage.setName(this.name);
        orderPage.setSurname(this.surname);
        orderPage.setAddress(this.address);
        orderPage.setMetro(this.metro);
        orderPage.setPhone(this.phone);

        orderPage.clickNextButton();

        orderPage.setDate(this.date);
        orderPage.setTerm(this.term);
        orderPage.setColor(this.color);
        orderPage.setComment(this.comment);

        orderPage.makeOrder();
    }
}