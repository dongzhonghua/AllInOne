package toOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
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
        while (listNode != null) {
            list.add(0, listNode.val);
            listNode = listNode.next;
        }
        return list;
    }

    //重建二叉树
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConBTree(pre, 0, pre.length - 1, in, 0, in.length - 1);

    }

    public TreeNode reConBTree(int[] pre, int preleft, int preright, int[] in, int inleft, int inright) {
        if (preleft > preright || inleft > inright)//当到达边界条件时候返回null
            return null;
        //新建一个TreeNode
        TreeNode pRootOfTree = new TreeNode(pre[preleft]);
        //对中序数组进行输入边界的遍历
        for (int i = inleft; i <= inright; i++) {
            if (pre[preleft] == in[i]) {
                //重构左子树，注意边界条件
                pRootOfTree.left = reConBTree(pre, preleft + 1, preleft + i - inleft, in, inleft, i - 1);
                //重构右子树，注意边界条件
                pRootOfTree.right = reConBTree(pre, preleft + i + 1 - inleft, preright, in, i + 1, inright);
            }
        }
        return pRootOfTree;
    }

    //两个栈来实现一个队列
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);

    }

    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        Integer res = stack2.pop();

        return res;
    }


    public static int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] < array[i]) {
                return array[i + 1];
            }
        }
        return array[0];
    }

    public int Fibonacci(int n) {
        //List<Integer> fib = new ArrayList<Integer>();
        //fib.add(0);
        //fib.add(1);
        //for(int i=2;i<=n;i++){
        //   fib.add(fib.get(i-1)+fib.get(i-2));
        //}
        //return fib.get(n);
        return Fib(n);
    }

    public int Fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return Fib(n - 1) + Fib(n - 2);
    }

    public static void reOrderArray(int[] array) {
        //相对位置不变，稳定性
        //插入排序的思想
        int m = array.length;
        int k = 0;//记录已经摆好位置的奇数的个数,不如说是定下第一个偶数的位置，和技术的位置，差是多少，就交换多少。
        for (int i = 0; i < m; i++) {
            if (array[i] % 2 == 1) {
                int j = i;
                while (j > k) {//j >= k+1
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    j--;
                }
                k++;
            }
        }
    }

    //************好题目，两个指针，一个快的先移动k步，之后再太不移动，后一个得值返回。
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
            if (null == fast) return null;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //反转链表
    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        ListNode p = null;
        while (head.next != null) {
            p = head.next;
            head.next = pre;
            pre = head;
            head = p;
        }
        return pre;
    }
    //合并两个不递减链表
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode mergeHead = null;
        ListNode current = null;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                if (mergeHead == null) {
                    mergeHead = current = list1;
                } else {
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next;
            } else {
                if (mergeHead == null) {
                    mergeHead = current = list2;
                } else {
                    current.next = list2;
                    current = current.next;
                }
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            current.next = list2;
        } else {
            current.next = list1;
        }
        return mergeHead;
    }

    //a是不是b的子结构
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null || root1 == null) {
            return false;
        }
        return isSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);

    }

    public boolean isSubtree(TreeNode tree1, TreeNode tree2) {
        if (tree2 == null) {
            return true;
        }
        if (tree1 == null) {
            return false;
        }

        if (tree1.val != tree2.val) {
            return false;
        }
        return isSubtree(tree1.left, tree2.left) && isSubtree(tree1.right, tree2.right);

    }
    //二叉树的镜像
    public void Mirror(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    public static void main(String[] args) throws Exception {
        Class1 class1 = new Class1();
        int[] arr = {1, 4, 3, 6, 2, 8, 9};
        reOrderArray(arr);
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(3);
        ListNode list4 = new ListNode(4);
        ListNode list5 = new ListNode(5);
        ListNode list6 = new ListNode(6);
        list1.next = list3;
        list3.next = list5;
        list2.next = list4;
        list4.next = list6;
        class1.Merge(list1, list2);
    }
}
