package com.example.uxin.myapplication;


/**
 * 求一个单向链表中环的节点个数
 * Created by shuipingyue@uxin.com on 2020/12/18.
 */
class LinkCircleCount {
    public static void main(String[] args) {

        LinkNode node = creatNode();
//        isCircle(node);
        int loopCount = getLoopCount(node);
        System.out.println(loopCount);
        LinkNode fisrtLoopNode = getFisrtLoopNode(node);
        System.out.println(fisrtLoopNode.value);
    }

    private static LinkNode creatNode(){
        LinkNode node = new LinkNode();
        node.value = 1;

        LinkNode node1 = new LinkNode();
        node1.value = 2;
        node.next = node1;

        LinkNode node2 = new LinkNode();
        node2.value = 5;
        node1.next = node2;

        LinkNode node3 = new LinkNode();
        node3.value = 2;
        node2.next = node3;
        node3.next = node1;

        return node;

    }

    private static class LinkNode{
        private int value;
        private LinkNode next;
    }


    private static boolean isCircle(LinkNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        LinkNode slow = head;
        LinkNode quick = head.next.next;
        while (slow != null && quick != null) {
            if ( slow == quick) {
                break;
            }
            slow = slow.next;
            if (quick.next != null){
                quick = quick.next.next;
            } else {
                break;
            }
        }
        if (slow == quick) {
            int circleCount = getCircleCount(slow);
            System.out.println(circleCount);
        }

        return false;
    }

    private static int getCircleCount(LinkNode head) {
        int count = 0;
        LinkNode next = head.next;
        while (next != null) {
            count++;
            if (next.equals(head)) {
                return count;
            }
            next = next.next;
        }
        return 0;
    }



    private static int getLoopCount(LinkNode head){
        if (head == null || head.next == null) {
            return -1;
        }

        if (head == head.next) {
            return -1;
        }

        int count = 0;
        LinkNode fast = head,slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            count ++;
            if (slow == fast) {
                return count;
            }
        }

        return -1;
    }

    /**
     * 相遇的节点
     * 快的指针一次走2个节点，慢的指针一次走1个节点
     * 相遇后，快的指针走的节点数 - 慢的指针走的节点数 = 环的个数
     * 假设环的个数为k，慢的指针走了k次，是在第k+1节点那，往后退一步，正好在第k个节点上
     * 此时 前面的非环节点个数+慢指针走过的环内的节点个数 = k
     * 慢指针走过的环内的节点个数 + 慢指针未走过的环内的节点个数 = k
     * 也就是  前面的非环节点个数 = 慢指针未走过的环内的节点个数；
     * 现在慢指针在第k+1个节点上，也就是 慢指针未走过的环内的节点个数 + 1 = 前面的非环节点个数
     *
     * @param head
     * @return
     */
    private static LinkNode getFisrtLoopNode(LinkNode head) {
        LinkNode meetNode = null;

        int count = 0;
        LinkNode fast = head,slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            count ++;
            if (fast == slow) {
                meetNode = fast;
                break;
            }
        }

        if (meetNode == null) {
            return meetNode;
        }

        while (head != meetNode) {
            head = head.next;
            if (head == meetNode){
                break;
            }
            meetNode = meetNode.next;
        }

        return meetNode;
    }




}
