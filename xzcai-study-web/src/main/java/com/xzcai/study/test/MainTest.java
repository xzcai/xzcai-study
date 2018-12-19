package com.xzcai.study.test;

import javax.xml.stream.events.EndDocument;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: created by mifang
 * @create: 2018-11-07
 **/
public class MainTest {
    public static void main(String[] args) throws IOException, ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sfDay = new SimpleDateFormat("yyyy-MM-dd");
            String today = sfDay.format(new Date());

            String begin = "2018-11-08";
            String end="2018-11-08";
            if(today.compareTo(begin)<0){
                begin=today+" 00:00:00";
            }
            if(today.compareTo(end)<0){
                end=today+" 23:59:59";
            }
            long be = sf.parse(begin).getTime()/1000;
            long en = sf.parse(end).getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }



        Fruit fruit=new Fruit();
        Fruit apple = new Apple();

        Plate<? extends Fruit> plate=null;
        while (true){
            int n = System.in.read();
            switch (n)
            {
                case 49:
                    plate= new Plate<>(new Apple());
                    break;
                case 10:
                    plate=new Plate<>(new Fruit());
                    break;
            }

            System.out.println(plate.get().what());
        }




    }
}
