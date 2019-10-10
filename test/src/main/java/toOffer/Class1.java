package toOffer;

import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Class1 {


    public static boolean Find(int target, int[][] array) {
        int length = array.length;
        int number = array[0].length;
        if (number == 0) return false;
        for (int i = 0; i < length; i++) {
            int low = 0, high = number - 1;

            if (target < array[i][number - 1] || target > array[i][0]) {
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (target == array[i][mid]) {
                        return true;
                    } else if (target < array[i][mid]) {
                        high = mid - 1;
                    } else if (target > array[i][mid]) {
                        low = mid + 1;
                    }
                }
            }
        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {
        int i = str.length();
        String res = "";
        for (int j = 0; j < i; j++) {
            if (str.charAt(j) == ' ') {
                res += "%20";
            } else {
                res += str.charAt(j);
            }
        }
        return res;
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode!=null){
            list.add(0,listNode.val);
            listNode = listNode.next;
        }
        return list;
    }
    public static void main(String[] args) throws Exception {
        ListNode listNode = new ListNode(1);
        System.out.println(printListFromTailToHead(listNode));
    }
}
