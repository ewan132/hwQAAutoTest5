package ru.netology;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ReplanDeliveryTest {

    private Faker faker;




    @Test
    void shouldReplanDelivery(){
        var validCity = DataGenerator.Registration.genUserCity("ru");
        var firstData = 3;
        var firstDataChange = DataGenerator.genDate(firstData);
        var secondData = 5;
        var secondDataChange = DataGenerator.genDate(secondData);



        open("http://localhost:9999/");

        $("[data-test-id='city'] input").val(validCity.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord( Keys.LEFT_CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(firstDataChange);
        $("[data-test-id='name'] input").val(DataGenerator.getUserName());
        $("[data-test-id='phone'] input").val(DataGenerator.getUserPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='date'] input").sendKeys(Keys.chord( Keys.LEFT_CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(secondDataChange);
        $("[data-test-id='replan-notification'] button").click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + secondDataChange))
                .shouldBe(visible, Duration.ofSeconds(15));






        }
}

