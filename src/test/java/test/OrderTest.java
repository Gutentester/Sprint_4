package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.OrderPage;
import pages.ScooterHomePage;



@RunWith(Parameterized.class)
public class OrderTest {
    WebDriver driver = new ChromeDriver();
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String desiredDate;
    private final String rentPeriod;
    private final String colour;
    private final String comment;

    private final String expect;

    //Конструктор класса с соответствующими аргументами
    public OrderTest(String name, String surname, String address,
                     String metroStation, String phoneNumber, String desiredDate,
                     String rentPeriod, String colour, String comment, String expect){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.desiredDate = desiredDate;
        this.rentPeriod = rentPeriod;
        this.colour = colour;
        this.comment = comment;
        this.expect = expect;

    }

    @Parameterized.Parameters //Параметры для теста (маняем параметры и прогоняем различные кейсы)
    public static Object[][] getOrder(){
        return new Object[][]{
                {"Антон", "Репкин", "Барнаул", "Спартак", "89059991111", "15.06.2023", "1", "black", "Курьер, давай быстрее!", "Заказ оформлен"},
                {"Татьяна", "Репкина", "Заринск", "Динамо", "89059997777", "16.06.2023", "2", "grey", "Я люблю кататься на самокате", "Заказ оформлен"},
        };
    }


    @Test //Кейс с использованием кнопки "Заказать", которая находится вверху страницы
    public void makeOrderAndCheckResultByTopButton(){

        ScooterHomePage scooterHomePage = new ScooterHomePage(driver);
        OrderPage orderPage = new OrderPage(driver);

        scooterHomePage.openURL();
        scooterHomePage.clickTopOrderButton();
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.metroStationSelect(metroStation);
        orderPage.setPhoneNumber(phoneNumber);
        orderPage.clickNextButton();
        orderPage.setDate(desiredDate);
        orderPage.rentPeriodSelect(rentPeriod);
        orderPage.colourCheckBoxSelect(colour);
        orderPage.setComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.checkOrderStatus(expect);
        driver.quit();
    }

    @Test //Кейс с использованием кнопки "Заказать", которая находится внизу страницы
    public void makeOrderAndCheckResultByDownButton(){

        ScooterHomePage scooterHomePage = new ScooterHomePage(driver);
        OrderPage orderPage = new OrderPage(driver);

        scooterHomePage.openURL();
        scooterHomePage.clickDownOrderButton();
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.metroStationSelect(metroStation);
        orderPage.setPhoneNumber(phoneNumber);
        orderPage.clickNextButton();
        orderPage.setDate(desiredDate);
        orderPage.rentPeriodSelect(rentPeriod);
        orderPage.colourCheckBoxSelect(colour);
        orderPage.setComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.checkOrderStatus(expect);
        driver.quit();
    }
}
