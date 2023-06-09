package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScooterHomePage {
    private WebDriver driver;

    /** Метод открытия URL */
    public void openURL(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    /** Верхняя кнопка "Заказать"*/
    private final By topOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");

    /** Метод для клика на Верхнюю кнопку "Заказать"*/
    public void clickTopOrderButton(){
        driver.findElement(topOrderButton).click();
    }

    /** Нижняя кнопка "Заказать"*/
    private final By downOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']");

    /** Метод для клика на Нижнюю кнопку "Заказать"*/
    public void clickDownOrderButton(){
        WebElement button = driver.findElement(downOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    /** Конструктор класса */
    public ScooterHomePage(WebDriver driver){
        this.driver = driver;
    }

    /** Метод для нажатия на вопрос */
    public void clickQuestion(String questionNum){
        WebElement question = driver.findElement(By.xpath(".//div[@id='accordion__heading-" + questionNum + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();
    }

    /** Метод для получения текста ответа на вопрос */
    public String waitForAnswerAndCheckText(String answerNum){
        WebElement answerForQuestion = driver.findElement(By.xpath(".//div[@id='accordion__panel-" + answerNum + "']/p"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", answerForQuestion);
        return answerForQuestion.getText();
    }
}
