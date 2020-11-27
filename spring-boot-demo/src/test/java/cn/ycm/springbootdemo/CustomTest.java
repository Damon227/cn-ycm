package cn.ycm.springbootdemo;

import cn.ycm.springbootdemo.model.Animal;
import cn.ycm.springbootdemo.model.Person;
import cn.ycm.springbootdemo.model.Student;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-24
 */
public class CustomTest {
    @Test
    public void test1() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("a", 12, 1));
        students.add(new Student("b", 13, 2));

        List<Person> persons = students.stream().map(t -> new Person(t.getName(), t.getAge())).collect(Collectors.toList());
        System.out.println(persons.size());

    }

    @Test
    public void test2(){
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};

        System.out.println(findMedianSortedArrays(nums1, nums2));

        String s = "12345";
        System.out.println(s.substring(1, 3));
    }

    @Test
    public void test3(){
        LocalDate date = LocalDate.of(2020, 2,2);
        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-01")));
    }

    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums3 = new int[nums1.length + nums2.length];

        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while(index1 < nums1.length || index2 < nums2.length){
            Integer n1 = index1 < nums1.length ? nums1[index1] : null;
            Integer n2 = index2 < nums2.length ? nums2[index2] : null;

            if(n1 == null){
                nums3[index] = n2;
                index2++;
            }
            else if(n2 == null){
                nums3[index] = n1;
                index1++;
            }else{
                if(n1 < n2){
                    nums3[index] = n1;
                    index1++;
                }
                else if(n1 > n2){
                    nums3[index] = n2;
                    index2++;
                }
                else{
                    nums3[index] = n1;
                    nums3[++index] = n2;
                    index1++;
                    index2++;
                }
            }
            index++;
        }

        // 空数组
        if(nums3.length == 0){
            return 0;
        }
        // 偶数
        if(nums3.length % 2 == 0){
            return ((float) nums3[nums3.length/2-1] + nums3[nums3.length/2]) / 2;
        }
        // 奇数
        else{
            return nums3[nums3.length/2];
        }

    }
}
