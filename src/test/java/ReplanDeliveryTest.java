import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ReplanDeliveryTest {

    private Faker faker;

    @BeforeEach
    void setUpAll(){
        faker = new Faker(new Locale("ru"));
    }
    private String localDateFromCard (long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldReplanDelivery(){
        var validCity = DataGenerator.Registration.genUserCity("ru");
        String date = localDateFromCard(3, "dd.MM.yyyy");
        String name = faker.name().lastName() + " " + faker.name().firstName();
        String phone = faker.phoneNumber().phoneNumber();

            open("http://localhost:9999/");

        $("[data-test-id='city'] input").val(validCity.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord( Keys.LEFT_CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").val(name);
        $("[data-test-id='phone'] input").val(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] button").click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + date))
                .shouldBe(visible, Duration.ofSeconds(15));




        }
}

