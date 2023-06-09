package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderPage {
    /** Драйвер для страницы заказа самоката*/
    private WebDriver driver;

    /** Поле для ввода Имени*/
    private By nameInput = By.xpath(".//input[@placeholder='* Имя']");

    /** Метод для ввода Имени*/
    public void setName(String value){
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).sendKeys(value);
    }

    /** Поле для ввода Фамилии*/
    private By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");

    /** Метод для ввода Фамилии*/
    public void setSurname(String value){
        driver.findElement(surnameInput).sendKeys(value);
    }

    /** Поле для ввода Адреса @Class='Input_Input__1iN_Z Input_Error__1Tx5d Input_Responsible__1jDKN' and */
    private By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    /** Метод для ввода Адреса*/
    public void setAddress(String value){
        driver.findElement(addressInput).sendKeys(value);
    }

    /** Поле для выбора Станции метро*/
    private By metroStationInput = By.xpath(".//div[@Class='select-search']");

    /** Метод выбора Станции метро*/
    public void metroStationSelect(String name){
        driver.findElement(metroStationInput).click();
        WebElement station = driver.findElement(By.xpath(".//div[text()='" + name + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", station);
        station.click();
    }

    /** Поле для ввода Номера телефона*/
    private By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    /** Метод для ввода Номера телефона*/
    public void setPhoneNumber(String value){
        driver.findElement(phoneInput).sendKeys(value);
    }

    /** Кнопка Далее*/
    private By nextButton = By.xpath(".//button[text()='Далее']");

    /** Метод для клика на Кнопку "Далее"*/
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

    /** Поле выбора Даты*/
    private By dateInput = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div/div/input");

    /** Метод для ввода Даты*/
    public void setDate(String value){
        driver.findElement(dateInput).sendKeys(value);
        driver.findElement(dateInput).click();
    }

    /** Комментарий для курьера*/
    private By commentForCourier = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/input");

    /** Метод для ввода Комментария */
    public void setComment(String value){
        driver.findElement(commentForCourier).sendKeys(value);
    }


    /** Кнопка Заказать*/
    private By orderButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");

    /** Метод для клика на Кнопку "Заказать"*/
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }

    /** Кнопка Да*/
    private By yesButton = By.xpath(".//button[text()='Да']");

    /** Метод для клика на Кнопку "Да"*/
    public void clickYesButton(){
        driver.findElement(yesButton).click();
    }

    /** Надпись Заказ оформлен*/
    private By orderStatusInfo = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    /** Метод проверки того, что Заказ оформлен*/
    public void checkOrderStatus(String expect) {
        String info = driver.findElement(orderStatusInfo).getText();
        System.out.println("ОЖИДАЕМЫЙ СТАТУС ЗАКАЗА:" + expect);
        System.out.println("ФАКТИЧЕСКИЙ СТАТУС ЗАКАЗА:" + info);
        new WebDriverWait(driver, 5).until(driver -> (driver.findElement(orderStatusInfo).getText().contains(expect)));
    }

    /** Метод для нажатия на треугольник в поле Срок аренды*/
    private By dropDownInRentInput = By.xpath(".//span[@class='Dropdown-arrow']");
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    /** Метод выбора Срока аренды*/
    public void rentPeriodSelect(String index) {
        driver.findElement(dropDownInRentInput).click();
        WebElement rentPeriod = driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[" + index + "]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", rentPeriod);
        rentPeriod.click();
    }

    /** Метод выбора цвета самоката */
    public void colourCheckBoxSelect(String index) {
        WebElement colour = driver.findElement(By.xpath(".//label[@for='" + index + "']" ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", colour);
        colour.click();
    }
}