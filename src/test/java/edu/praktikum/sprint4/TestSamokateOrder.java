package edu.praktikum.sprint4;

import edu.praktikum.sprint4.pom.FirstOrderPage;
import edu.praktikum.sprint4.pom.MainPage;
import edu.praktikum.sprint4.pom.SecondOrderPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestSamokateOrder extends BaseClass {

    private final String entryPoint;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String dateOfDelivery;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;

    public TestSamokateOrder(String entryPoint, String name, String surname, String address, String station, String phone, String dateOfDelivery, String rentalPeriod, String scooterColor, String comment) {
        this.entryPoint = entryPoint;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.dateOfDelivery = dateOfDelivery;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] fillingOrderFields() {
        return new Object[][]{
                // Этот тест упадет в Chrome из-за бага (нет сообщения об успешном заказе), но пройдет успешно в Firefox
                {"Header", "Александр", "Малина", "ул. Сезам, 10", "Митино", "79297078015", "26.05.2024", "семеро суток", "grey", "Комментарий"},
                // Этот тест упадет из-за бага: в поле "Фамилия" нельзя ввести двойную фамилию
                {"Home", "Василий", "Склодовская-Кюри", "ул. Польская, 150", "Университет", "79297078015", "16.08.25", "сутки", "black", ""},
                // Этот тест упадет из-за латинских имени и фамилии
                {"Home", "Ruslan", "Ruslanov", "ул. Польская, 150к1", "Университет", "79297078015", "2025/01/19", "двое суток", "grey", ""},
                // Позитивный тест без выбора цвета самоката
                {"Home", "Мария", "Кюри", "ул. Польская, 150к1", "Университет", "79297078015", "2025/01/19", "двое суток", "", ""},
        };
    }

    @Test
    public void scooterOrder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .closeCookiesMessage()
                .clickOrderButton(entryPoint);

        FirstOrderPage firstOrderPage = new FirstOrderPage(driver);
        firstOrderPage.fillNameField(name)
                .fillSurnameField(surname)
                .fillAddressField(address)
                .fillStationField(station)
                .fillPhoneField(phone)
                .clickNextButton();

        SecondOrderPage secondOrderPage = new SecondOrderPage(driver);
        secondOrderPage.fillDateOfDeliveryField(dateOfDelivery)
                .fillRentalPeriodField(rentalPeriod)
                .fillScooterColorCheckBox(scooterColor)
                .fillCommentField(comment)
                .clickOrderButton()
                .clickYesOrderButton();

        assertTrue(secondOrderPage.isSuccessOrderMessageShowed());
    }

}