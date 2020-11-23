package cn.ycm.springbootdemo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-23
 */
public class TimeTest {
    @Test
    public void test1(){
        LocalDate date = LocalDate.now();
//        LocalDate beginTime = LocalDate.of(date.getYear(), date.getDayOfMonth(), date.getDayOfMonth());
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
    }
}
