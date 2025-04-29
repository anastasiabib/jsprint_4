package Sprint_4;


import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.MainPage;
import pageObjects.OrderPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Тест для проверки всего флоу позитивного сценария оформления заказа
 */
@RunWith(Parameterized.class)
public class OrderPageTests {
    /**
     * Веб-драйвер
     */
    private WebDriver webDriver;

    /**
     * URL тестируемой страницы
     */
    private final String mainPageUrl = "https://qa-scooter.praktikum-services.ru";

    /**
     * Переменные для параметров теста - данных для оформления заказа
     */
    private final String name, surname, address, metro, phone, date, term, color, comment;

    /**
     * Сообщение об успешном оформлении заказа
     */
    private final String expectedOrderSuccessText = "Заказ оформлен";


    public OrderPageTests(
            String name,
            String surname,
            String address,
            String metro,
            String phone,
            String date,
            String term,
            String color,
            String comment
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
    }

    /**
     * Параметры для запуска теста
     */
    @Parameterized.Parameters(name = "Оформление заказа. Позитивный сценарий. Пользователь: {0} {1}")
    public static Object[][] setDataForOrder() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва, ул. Ленина, д. 10, кв. 24", "Новогиреево", "89166900930", "01.04.2025", "двое суток", "чёрный жемчуг", "Тест"},
                {"Василий", "Дроздов", "Москва, ул. Зеленая, д. 25, кв. 11", "Китай-город", "89167892190", "01.05.2025", "трое суток", "серая безысходность", "Ожидаю доставку без задержек"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "/Users/rubtsovv/Documents/projects/Sprint_4/target/classes/chromedriver");
        this.webDriver = new ChromeDriver(); // Тест упадет на подтверждении заказа
        this.webDriver.get(mainPageUrl);
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }


    /**
     * Тест для проверки процесса оформления заказа после нажатия на кнопку "Заказать" в шапке
     */
    @Test
    public void orderWithHeaderButtonWhenSuccess() {
        MainPage mainPage = new MainPage(this.webDriver);
        OrderPage orderPage = new OrderPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();
        mainPage.clickOrderButtonHeader();
        makeOrder(orderPage);

        MatcherAssert.assertThat(
                "Problem with creating a new order",
                orderPage.getNewOrderSuccessMessage(),
                containsString(this.expectedOrderSuccessText)
        );
    }

    /**
     * Тест для проверки процесса оформления заказа после нажатия на кнопку "Заказать" в теле сайта
     */
    @Test
    public void orderWithBodyButtonWhenSuccess() {
        MainPage mainPage = new MainPage(this.webDriver);
        OrderPage orderPage = new OrderPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();
        mainPage.clickOrderButtonBody();
        makeOrder(orderPage);

        MatcherAssert.assertThat(
                "Problem with creating a new order",
                orderPage.getNewOrderSuccessMessage(),
                containsString(this.expectedOrderSuccessText)
        );
    }

}