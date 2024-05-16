package edu.praktikum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirstOrderPage {
    private final WebDriver driver;

    //Поле "Имя"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // Поле "Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле "Станция метро"
    private final By stationField = By.xpath(".//input[@placeholder='* Станция метро']");
    // Элемент списка "Станция метро"
    String stationFieldItem = "(.//div[contains(@class, 'Order_Text__2broi') and text()='%s'])[1]";
    // Поле "Телефон"
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Далее']");

    public FirstOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнить поле "Имя"
    public FirstOrderPage fillNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    // Заполнить поле "Фамилия"
    public FirstOrderPage fillSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
        return this;
    }

    // Заполнить поле "Адрес"
    public FirstOrderPage fillAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
        return this;
    }

    // Заполнить поле "Станция метро"
    public FirstOrderPage fillStationField(String station) {
        driver.findElement(stationField).click();
        driver.findElement(stationField).sendKeys(station);
        driver.findElement(By.xpath(String.format(stationFieldItem, station))).click();
        return this;
    }

    // Заполнить поле "Телефон"
    public FirstOrderPage fillPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
        return this;
    }

    // Нажать на кнопку "Далее"
    public FirstOrderPage clickNextButton() {
        driver.findElement(nextButton).click();
        return this;
    }

}

