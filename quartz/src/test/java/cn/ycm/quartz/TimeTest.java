package cn.ycm.quartz;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.time.LocalDate;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-12
 */
public class TimeTest {
    @Test
    public void test1(){
        System.out.println(LocalDate.parse(MessageFormat.format("{0}-{1}-{2}", "2020", "2", "3")));
        System.out.println(LocalDate.of(2020, 2, 2));
    }
}
