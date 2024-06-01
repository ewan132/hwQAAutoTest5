package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    public static String genDate (int change) {
        return LocalDate.now().plusDays(change).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String genCity(){
       var cities = new String[]{ "Владимир", "Рязань", "Саратов", "Волгоград", "Воронеж",
        "Ижевск", "Воронеж", "Курск", "Мурманск", "Пенза", "Самара",
        "Томск", "Черкесск", "Чита" };
    return cities[new Random().nextInt(cities.length)];
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String getUserName(){
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getUserPhone(){
        return  faker.phoneNumber().phoneNumber();
    }

    public static class Registration{
        private Registration(){

        }

            public static UserInfo genUserCity(String city){
                return new UserInfo(genCity(), getUserName(),getUserPhone());
            }
        }
@Value
public static class UserInfo{
    String city;
    String name;
    String phone;
    }
}
