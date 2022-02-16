package org.demo.account.model;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewUserModel {

    public String name;
    public String email;
    public String month;
    public String day;
    public String year;
    public Faker faker;

    public NewUserModel(String email) {
        faker = new Faker();
        this.name = faker.name().fullName();
        this.email = email;
        Date d = faker.date().birthday(21, 65);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        year = String.valueOf(c.get(Calendar.YEAR));
        month = new SimpleDateFormat("MMMM").format(c.getTime());
        day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
    }
}
