package com.alvaro.bankapi.shared.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static Integer LocateToInt(LocalDate localDate){
        return Integer.parseInt(localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }


    public static LocalDate IntToLocalDate(int value){

        if (String.valueOf(value).length() != 8){
            throw new IllegalArgumentException("Wrong Format");
        }

        //Extraer año, mes y dia
        int year  = value / 10000;
        int month = (value / 100) % 100;
        int day = value % 100;
        return LocalDate.of(year, month, day);
    }

    public static LocalTime convertBigDecimalToLocalTime(BigDecimal time){

        if (time == null){
            throw new RuntimeException("El valor del tiempo no puede ser nulo");
        }

        int timeInt = time.intValueExact();

        //Extraer las horas, minutos y segundos
        int horas = timeInt / 10000;
        int minutos =  (timeInt / 100) % 100;
        int segundos = timeInt % 100;

        //Verificacion que las horas, minutos y segundo sean validos
        if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59 || segundos < 0 || segundos > 59){
            throw new RuntimeException("El formato de tiempo no es valido HHMMSS");
        }
        return LocalTime.of(horas,minutos,segundos);
    }

    public static BigDecimal convertLocalDateToBigDecimal(LocalDate date){

        if (date == null){
            throw new RuntimeException("La fecha no puede ser nula");
        }

        //Formato AAAAMMDD
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        String dateAsString = String.format("%04d%02d%02d", year, month, day);

        return  new BigDecimal(dateAsString);

    }


}
