package com.xzcai.study.test;

import javax.xml.stream.events.EndDocument;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author: created by mifang
 * @create: 2018-11-07
 **/
public class MainTest {
    public static void main(String[] args) throws IOException, ParseException {
        for(int i=0;i<1000;i++){
            char str = getRandomChar();
            System.out.println(str);
        }


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

    private static char getRandomChar() {
        String str = "";
        int hightPos; //
        int lowPos;

        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str.charAt(0);
    }
}
