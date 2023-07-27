package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private static final Logger logger = Logger.getLogger(OrderPage.class);

    /**
     * Драйвер для страницы заказа самоката
     */
    private WebDriver driver;

    /**
     * Поле для ввода Имени
     */
    private By nameInput = By.xpath(".//input[@placeholder='* Имя']");

    /**
     * Поле для ввода Фамилии
     */
    private By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");

    /**
     * Поле для ввода Адреса
     */
    private By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    /**
     * Поле для выбора Станции метро
     */
    private By metroStationInput = By.xpath(".//div[@Class='select-search']");

    /**
     * Поле для ввода Номера телефона
     */
    private By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    /**
     * Кнопка Далее
     */
    private By nextButton = By.xpath(".//button[text()='Далее']");

    /**
     * Поле выбора Даты
     */
    private By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    /**
     * Комментарий для курьера
     */
    private By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    /**
     * Кнопка Заказать
     */
    private By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[2]");

    /**
     * Кнопка Да
     */
    private By yesButton = By.xpath(".//button[text()='Да']");

    /**
     * Надпись Заказ оформлен
     */
    private By orderStatusInfo = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    /**
     * Метод для нажатия на треугольник в поле Срок аренды
     */
    private By dropDownInRentInput = By.xpath(".//span[@class='Dropdown-arrow']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод ввода Данных о заказчике
     */
    public void setCustomerData(String name, String surname, String address, String metroStation, String phoneNumber) {
        //driver.findElement(nameInput).click();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroStationInput).click();
        WebElement station = driver.findElement(By.xpath(".//div[text()='" + metroStation + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", station);
        station.click();
        driver.findElement(phoneInput).sendKeys(phoneNumber);
    }

    /**
     * Метод для клика на Кнопку "Далее"
     */
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    /**
     * Метод ввода Данных о заказе
     */

    public void setRentData(String desiredDate, String Period, String colour, String comment) {
        driver.findElement(dateInput).sendKeys(desiredDate);
        driver.findElement(dateInput).click();
        driver.findElement(dropDownInRentInput).click();
        WebElement rentPeriod = driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[" + Period + "]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", rentPeriod);
        rentPeriod.click();
        WebElement scooterColour = driver.findElement(By.xpath(".//label[@for='" + colour + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scooterColour);
        scooterColour.click();
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    /**
     * Метод для клика на Кнопку "Заказать"
     */
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    /**
     * Метод для клика на Кнопку "Да"
     */
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    /**
     * Метод проверки того, что Заказ оформлен
     */
    public void checkOrderStatus(String expect) throws Exception {
        try {
            new WebDriverWait(driver, 5).until(driver -> (driver.findElement(orderStatusInfo).getText().contains(expect)));
        } catch (TimeoutException e) {
            logger.error("Ошибка! Заказ не оформлен!");
        } finally {
            new WebDriverWait(driver, 5).until(driver -> (driver.findElement(orderStatusInfo).getText().contains(expect)));
        }
    }
}
