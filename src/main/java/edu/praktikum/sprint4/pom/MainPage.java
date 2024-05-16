package edu.praktikum.sprint4.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final static String URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;

    //кнопка "Заказать" в шапке главной страницы
    private final String orderButton = ".//div[contains(@class, '%s')]/button[text()='Заказать']";

    //блок со списком вопросов
    private final By blockOfQuestions = By.cssSelector(".accordion");

    // Вопросы из списка "Вопросы о важном"
    private final String questionsAccordionItems = ".//div[contains(@class, 'accordion__button') and text()='%s']";

    // Ответы из списка "Вопросы о важном"
    private final By answersAccordionItems = By.xpath(".//div[contains(@class, 'accordion__panel') and not(@hidden)]");

    //кнопка принятия куки
    private final By cookiesButton = By.cssSelector("#rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Кликнуть по кнопке "Заказать" (верхняя или нижняя)
    public MainPage clickOrderButton(String button) {
        driver.findElement(By.xpath(String.format(orderButton, button))).click();
        return this;
    }

    //метод открытия главной страницы
    public MainPage open() {
        driver.get(URL);
        driver.manage().window().fullscreen();
        return this;
    }

    //метод приняти куки
    public MainPage closeCookiesMessage() {
        driver.findElement(cookiesButton).click();
        return this;
    }

    // Проскроллить до "Вопросы о важном"
    public MainPage scrollToQuestion() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(blockOfQuestions));
        return this;
    }

    //метод нажатия стрелки раскрывающегося списка
    public MainPage clickOnQuestion(String question) {

        driver.findElement(By.xpath(String.format(questionsAccordionItems, question))).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(answersAccordionItems));
        return this;
    }

    //метод проверки отображения правильного текста ответа
    public boolean isCorrectAnswerDisplayed(String answer) {
        return answer.equals(driver.findElement(answersAccordionItems).getText());
    }

}