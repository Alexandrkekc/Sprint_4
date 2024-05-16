package edu.praktikum.sprint4.pom;

import org.openqa.selenium.*;

public class SecondOrderPage {
    private final WebDriver driver;

    // Поле "Когда привезти самокат"
    private final By dateOfDeliveryField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Выбранная дата в поле "Когда привезти самокат"
    private final By chosenDate = By.xpath(".//div[@tabindex=0 and @role='button']");
    // Поле "Срок аренды"
    private final By rentalPeriodField = By.xpath(".//div[contains(@class, 'Dropdown-control')]");
    // Выбранный элемент в списке "Срок аренды"
    private final String chosenNumberOfDays = ".//div[contains(@class, 'Dropdown-option') and text()='%s']";
    // Поле "Цвет самоката"
    private final String scooterColorCheckBox = "input#%s";
    // Поле "Комментарий для курьера"
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text() = 'Заказать']");
    // Кнопка "Да" при оформлении заказа»
    private final By yesOrderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text() = 'Да']");
    // Сообщение об успешном заказе
    private final By successOrderMessage = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ')]");
    // Кнопка "Посмотреть статус" в сообщении об успешном заказе
    private final By checkStatusSuccessMessage = By.xpath(".//div[contains(@class, 'Order_NextButton__1_rCA')]");

    public SecondOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнить поле "Когда привезти самокат"
    public SecondOrderPage fillDateOfDeliveryField(String date) {
        driver.findElement(dateOfDeliveryField).click();
        driver.findElement(dateOfDeliveryField).sendKeys(date);
        driver.findElement(chosenDate).click();
        return this;
    }

    // Заполнить поле "Срок аренды"
    public SecondOrderPage fillRentalPeriodField(String period) {
        driver.findElement(rentalPeriodField).click();
        WebElement chosenPeriodField = driver.findElement(By.xpath(String.format(chosenNumberOfDays, period)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", chosenPeriodField);
        chosenPeriodField.click();
        return this;
    }

    // Заполнить поле "Цвет самоката"
    public SecondOrderPage fillScooterColorCheckBox(String color) {
        if (!color.isEmpty())
            driver.findElement(By.cssSelector(String.format(scooterColorCheckBox, color))).click();
        return this;
    }

    // Заполнить поле "Комментарий"
    public SecondOrderPage fillCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }

    // Нажать на кнопку "Заказать"
    public SecondOrderPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }

    // Нажать на кнопку "Да" при оформлении заказа»
    public SecondOrderPage clickYesOrderButton() {
        driver.findElement(yesOrderButton).click();
        return this;
    }

    // Проверить, появилось ли сообщение об успешном заказе
    public boolean isSuccessOrderMessageShowed() {
        return driver.findElement(successOrderMessage).isDisplayed() &&
                driver.findElement(checkStatusSuccessMessage).isDisplayed();
    }
}
