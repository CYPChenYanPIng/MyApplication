package com.example.uxin.myapplication;

import java.util.ArrayList;

/**
 *
 * 123,12,求和
 * 数字是存在单向链表中，两个单向链表
 * 例如：1->2->3
 *      1->2，求和
 * Created by shuipingyue@uxin.com on 2020/12/17.
 */
class TwoLinkNumSum {

    public static void main(String[] args) {
        NumberNode numberLinkedOneHead = createNumberLinked(3);
        NumberNode numberLinkedTwoHead = createNumberLinked(2);
//        traversalLink(numberLinkedOneHead);
//        traversalLink(numberLinkedTwoHead);

//        NumberNode numberNode = reverseNode(numberLinkedOneHead);
//        NumberNode numberNode1 = reverseNode(numberLinkedTwoHead);
        NumberNode numberNode = reverseLink(numberLinkedOneHead);
        NumberNode numberNode1 = reverseLink(numberLinkedTwoHead);
//        traversalLink(numberNode);
//        traversalLink(numberNode1);
        NumberNode numberNode2 = sumLinked(numberNode, numberNode1);
        NumberNode numberNode3 = reverseNode(numberNode2);
        traversalLink(numberNode3);

        ArrayList<Integer>  list = new ArrayList<>();

    }


    /**
     * 123,12,求和
     */
    private static class NumberNode{
        private int value;
        private NumberNode next;
    }

    private static NumberNode createNumberLinked(int num){
        NumberNode head = new NumberNode();
        head.value = 1;
        NumberNode next;
        NumberNode current = head;
        for (int i = 2; i <= num; i++) {
            next = new NumberNode();
            next.value = i;
            current.next = next;
            current = next;
        }
        return head;
    }

    /**
     * 单向链表的逆序
     */
    private static NumberNode reverseNode(NumberNode head){
        NumberNode reversenext = new NumberNode();
        reversenext.value = head.value;
        NumberNode reverseHead = reversenext;
        head = head.next;
        while (head != null) {
            reverseHead = new NumberNode();
            reverseHead.value = head.value;
            reverseHead.next = reversenext;
            reversenext = reverseHead;
            head = head.next;
        }

        return reverseHead;
    }

    /**
     * 单向链表的反转
     * @param head
     * @return
     */
    private static NumberNode reverseLink(NumberNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        NumberNode pre = head;
        NumberNode cur = head.next;
        while (cur != null){
            NumberNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        head.next = null;
        return pre;
    }

    private static NumberNode sumLinked(NumberNode headOne,NumberNode headTwo){
        if (headOne == null && headTwo != null) {
            return headTwo;
        }
        if (headTwo == null && headOne != null) {
            return headOne;
        }
        if (headOne == null && headTwo == null) {
            return null;
        }
        NumberNode sumHead = new NumberNode();
        sumHead.value = headOne.value+headTwo.value;

        int addFlag = 0;
        if (sumHead.value > 9) {
            addFlag = sumHead.value / 10;
            sumHead.value = sumHead.value % 10;
        }


        headOne = headOne.next;
        headTwo = headTwo.next;
        NumberNode sumNext;
        NumberNode current = sumHead;
        while (headOne != null && headTwo != null) {
            sumNext = new NumberNode();
            sumNext.value = headOne.value + headTwo.value +addFlag;
            if (sumNext.value > 9) {
                addFlag = sumNext.value / 10;
                sumNext.value = sumNext.value % 10;
            }
            current.next = sumNext;
            current = sumNext;
            headOne = headOne.next;
            headTwo = headTwo.next;
        }

        while (headTwo != null) {
            headTwo.value = headTwo.value + addFlag;
            addFlag = headTwo.value / 10;
            headTwo.value = headTwo.value % 10;
            current.next = headTwo;

            if (addFlag <= 0) {
                break;
            }
            current = headTwo;
            headTwo = headTwo.next;
        }

        if (headOne == null && headTwo != null) {
            headTwo.value = headTwo.value + addFlag;
            // value需要处理进位

            current.next = headTwo;
        } else if (headOne != null && headTwo == null) {
            headOne.value = headOne.value + addFlag;
            current.next = headOne;
        } else if (addFlag > 0) {
            sumNext = new NumberNode();
            sumNext.value = addFlag;
            current.next = sumNext;

        }

        return sumHead;
    }

    private static void traversalLink(NumberNode head){

        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
    }



}
