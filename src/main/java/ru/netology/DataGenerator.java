package ru.netology;

import lombok.Value;

import java.util.Random;

public class DataGenerator {
    public static String genCity(){
       var cities = new String[]{ "Владимир", "Рязань", "Саратов", "Волгоград", "Воронеж",
        "Ижевск", "Воронеж", "Курск", "Мурманск", "Пенза", "Самара",
        "Томск", "Черкесск", "Чита" };
    return cities[new Random().nextInt(cities.length)];
    }

    public static class Registration{
        private Registration(){

        }

            public static UserInfo genUserCity(String locale){
                return new UserInfo(genCity());
            }
        }
@Value
public static class UserInfo{
    String city;
    }
}
