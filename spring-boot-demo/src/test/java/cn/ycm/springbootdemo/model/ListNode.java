package cn.ycm.springbootdemo.model;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-25
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
