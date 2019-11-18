package toOffer;

import java.util.*;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class LNode<T> {
    T data;
    LNode<T> next;
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

//栈的数组实现,还有链表也可以实现。
class ArrayStack<T> {
    private ArrayList<T> arr;
    private int stackSize;

    public ArrayStack() {
        stackSize = 0;
        arr = new ArrayList<>();
    }

    public boolean isEmpty() {
        return stackSize == 0;
    }

    public int size() {
        return stackSize;
    }

    public T top() {
        if (isEmpty()) {
            return null;
        }
        return arr.get(stackSize - 1);
    }

    public T pop() {
        if (stackSize > 0) {
            return arr.get(--stackSize);
        } else {
            System.out.println("栈已经为空");
            return null;
        }
    }

    public void push(T item) {
        arr.add(item);
        stackSize++;
    }
}

//队列的链表实现
class LinkedListQueue<T> {
    private LNode<T> pHead;
    private LNode<T> pEnd;

    public LinkedListQueue() {
        pEnd = pHead = null;
    }

    public boolean isEmpty() {
        return pHead == null;
    }

    public int size() {
        int size = 0;
        LNode p = pHead;
        while (p != null) {
            size++;
            p = p.next;
        }
        return size;
    }

    public void enQueue(T e) {
        LNode<T> p = new LNode<>();
        p.data = e;
        p.next = null;
        if (pHead == null) {
            pHead = pEnd = p;
        } else {
            pEnd.next = p;
            pEnd = p;
        }
    }

    public void deQueue() {
        if (pHead == null) return;
        pHead = pHead.next;
        if (pHead == null) pEnd = null;
    }

    public T getFront() {
        if (pHead == null) return null;
        return pHead.data;
    }

    public T getBack() {
        if (pEnd == null) {
            return null;
        }
        return pEnd.data;
    }

}

class DoubleLinkedListNode {
    public int val;
    public int key;
    public DoubleLinkedListNode pre;
    public DoubleLinkedListNode next;

    public DoubleLinkedListNode(int key, int value) {
        val = value;
        this.key = key;
    }
}

//LRU算法，要采用双向链表和hashmap(链表查找慢)。
class LRU {
    private int cachedSize;
    private int capacity;
    private Map<Integer, DoubleLinkedListNode> map = new HashMap<>();
    private DoubleLinkedListNode head;
    private DoubleLinkedListNode end;

    public LRU(int capacity) {
        this.capacity = capacity;
        cachedSize = 0;
    }

    //有的话返回链表节点，并设为头结点，否则返回-1
    public int get(int Key) {

        if (map.containsKey(Key)) {
            DoubleLinkedListNode node = map.get(Key);
            removeNode(node);
            setHead(node);
            return node.val;
        } else {
            return -1;
        }
    }

    //要考虑是头结点还有尾节点的情况。
    public void removeNode(DoubleLinkedListNode node) {
        DoubleLinkedListNode pre = node.pre;
        DoubleLinkedListNode next = node.next;
        if (pre != null) {
            pre.next = next;
        } else {
            head = next;
        }
        if (next != null) {
            next.pre = pre;
        } else {
            end = pre;
        }

    }

    public void setHead(DoubleLinkedListNode node) {
        node.next = head;
        node.pre = null;
        if (head != null) {
            head.pre = node;
        } else {
            head = node;
        }
        if (end == null) {
            end = node;
        }
    }

    //有的话就放到头上，当然还有更改。没有的话判断容量。
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            DoubleLinkedListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            setHead(node);
        } else {
            DoubleLinkedListNode node = new DoubleLinkedListNode(key, value);
            if (cachedSize < capacity) {
                setHead(node);
                map.put(key, node);
                cachedSize++;
            } else {
                map.remove(end.key);
                end = end.pre;
                if (end != null) {
                    end.next = null;
                }
                map.put(key, node);
                setHead(node);
            }
        }

    }
}

public class Class1 {

    public void printLinkList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");

    }

    //二维有序数组查找
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

    //二维有序数组的另一种方法，更快。
    public static boolean findWithBinary(int[][] arr, int data) {
        int i = 0;
        int rows = arr.length;
        int columns = arr[0].length;
        int j = columns - 1;
        while (i < rows && j > 0) {
            if (arr[i][j] == data) return true;
            else if (arr[i][j] > data) --j;
            else ++i;
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
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
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

    //原有数组基础上重新排序
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
        ListNode next = null;
        while (head.next != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //递归的逆序输出，既要看局部又要看整体。
    public void ReversePrint(ListNode head) {
        if (head == null) return;
        ReversePrint(head.next);
        System.out.println(head.val);
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

    //子结构，一开始以为是相等。
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

    public boolean sameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val == root2.val) {
            return sameTree(root1.right, root2.right) && sameTree(root1.left, root2.left);
        } else return false;
    }

    //二叉树的镜像
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    //1.3两个单链表的和
    //再加上输出链表的
    public int TwoLinkSum(ListNode head1, ListNode head2) {
        int sum = 0;
        int carry = 0;
        int index = 1;
        ListNode head = new ListNode(0);
        ListNode next = head;
        head = next;
        while (head1 != null || head2 != null) {
            int h1 = 0, h2 = 0;
            if (head1 != null) {
                h1 = head1.val;
                head1 = head1.next;
            }
            if (head2 != null) {
                h2 = head2.val;
                head2 = head2.next;
            }
            next.next = new ListNode(((h1 + h2) % 10 + carry));
            next = next.next;

            sum += ((h1 + h2) % 10 + carry) * index;
            carry = (h1 + h2) / 10;
            index *= 10;
        }
        if (carry == 1) next.next = new ListNode(carry);
        next.next.next = null;
        printLinkList(head);
        return sum + index * carry;
    }

    //递归实现栈的翻转,???????????还没解决
    public static void moveBottomToTop(Stack<Integer> s) {
        if (s.empty()) return;
        int top1 = s.pop();
        if (!s.empty()) {
            //    递归处理不包含栈顶元素的子栈
            moveBottomToTop(s);
            int top2 = s.pop();
            //    交换栈顶元素与子栈栈顶元素
            s.push(top1);
            s.push(top2);
        } else {
            s.push(top1);
        }

    }

    public static void reverseStack(Stack<Integer> s) {
        if (s.empty()) return;
        moveBottomToTop(s);
        int top = s.pop();
        //   递归处理子栈
        reverseStack(s);
        s.push(top);
    }

    //是不是出栈序列
    public static boolean isPopSerial(String push, String pop) {
        if (push == null || pop == null) return false;
        int pushIndex = 0;
        int popIndex = 0;
        int pushLength = push.length();
        int popLength = pop.length();
        if (pushLength != popLength) return false;
        Stack<Character> stack = new Stack<>();
        while (pushIndex < pushLength) {
            stack.push(push.charAt(pushIndex));
            pushIndex++;
            while (!stack.empty() && stack.peek() == pop.charAt(popIndex)) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty() && popIndex == popLength;
    }
    //如何用O(1)时间复杂度求栈中的最小元素
    //用空间换时间，两个栈，第二个保存最小值，入栈时比较后入栈。出栈时如果是最小则出栈，所以醉笑栈保存的都是当前最小值。
    //pass

    //把一个有序数组构造一个完全二叉树中
    public static TreeNode arrayToTree(int[] arr, int start, int end) {
        TreeNode root = null;
        if (end >= start) {
            int mid = (start + end + 1) / 2;
            root = new TreeNode(arr[mid]);
            root.left = arrayToTree(arr, start, mid - 1);
            root.right = arrayToTree(arr, mid + 1, end);
        } else {
            return null;
        }
        return root;
    }

    //层序遍历,队列是个关键。
    public void levelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print("->" + node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    //前序遍历,和中序遍历是一样的，只不过打印的位置不一样，后序遍历两个栈，其他和前序遍历类似，层序遍历用队列。
    //先序遍历,非空访节点，入栈得左点，空点出栈得右点。
    public static void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                System.out.println(root.val);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
    }

    //后序遍历
    //后序遍历，和前序遍历反着来，左变右，右变左，新加一个栈存结果。
    public static void postOrder(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            System.out.println(node.val);
        }
    }

    //中序遍历并变成双向链表，l,r,代表前后
    //中序遍历,非空则入栈，入栈得左点，空点出栈访节点，节点变右点。
    private static TreeNode inOrderAndLinkedList(TreeNode root) {
        TreeNode pHead = new TreeNode(0);
        //pHead很重要，起到保留的作用
        //cur和pre这两个指针要理清楚，这两个指针每一次都要同时向后移动，pre在里面起到一个temp的作用。
        TreeNode cur = pHead;
        TreeNode pre = null;
        if (null == root) return pHead;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.println(root.val);
                cur.right = root;
                pre = cur;
                cur.right.left = pre;
                cur = cur.right;
                root = root.right;
            }
        }
        return pHead;
    }

    //二叉树的最大子树和,利用递归方法,递归还是想不出来。
    private static int maxSum = Integer.MIN_VALUE;

    public static int findMaxSubTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lmax = findMaxSubTree(root.left);
        int rmax = findMaxSubTree(root.right);
        //因为null返回的是零，所以这里也包含了叶子节点了。
        int sum = lmax + rmax + root.val;
        if (sum > maxSum) {
            maxSum = sum;
        }
        return sum;
    }
    //是否是二元查找树后序遍历的序列
    //这个主要是思路，还是递归的解法结合arr下标的变换。和前面好几道题的思路都非常像、
    //pass


    //数组------------------------------------------------------------------------------------------------------------------

    //数组中找出相同的元素
    //异或，相同为零，不同不为零。0与其他为其他。
    public int findRepeatNum(int[] arr) {
        int len = arr.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            res ^= arr[i];
        }
        for (int j = 0; j < len; j++) {
            res ^= j;
        }
        return res;
    }

    //数组里面的最大值最小值。
    public static void fidMaxMin(int[] arr) {
        //方法一：定义两个变量，为数组的第一个值。之后逐一比较，但是最坏情况是比较2n-2次。
        //方法二：分治算法。分成两个，大的放左边，小的放右边。
        int max = arr[0];
        int min = arr[0];
        int len = arr.length;
        int i = 0;
        for (i = 0; i < len - 1; i += 2) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        min = arr[0];
        for (i = 2; i < len; i += 2) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        max = arr[1];
        for (i = 3; i < len; i += 2) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //    数组个数是奇数个的情况需要特殊处理。
        if (len % 2 == 1) {
            if (max < arr[len - 1]) max = arr[len - 1];
            if (min > arr[len - 1]) min = arr[len - 1];
        }

    }

    //快速排序，找个基准数
    public static void quickSort(int[] arr, int low, int high) {
        int i, j, base, temp;
        if (low > high) return;
        i = low;
        j = high;
        base = arr[low];
        while (i < j) {
            //    先看右边，依次往左比较
            while (base <= arr[j] && i < j) {
                j--;
            }
            //    在看左边，依次往右比较
            while (base >= arr[i] && i < j) {
                i++;
            }
            //    如果条件满足交换
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        //此时i的位置绝对是比base小的，否则i到不了这个位置。
        arr[low] = arr[i];
        arr[i] = base;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }
    //找出第k小的数。可以用上面的快速排序思想，先分成三组，如果第i个数是k则返回，否则只需要递归某一部分就可以了。
    //也就是在快速排序上加几个判断。
    //pass

    //数组中两个元素的最小距离
    //动态规划问题，比较简单。思想就是保存之前的结果以用作下一次的计算。
    public static int minDistance(int[] arr, int num1, int num2) {
        if (arr == null || arr.length <= 0) {
            return Integer.MAX_VALUE;
        }
        int lastPos1 = -1;
        int lastPos2 = -2;
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num1) {
                lastPos1 = i;
                if (lastPos2 >= 0) {
                    minDis = Math.min(minDis, lastPos1 - lastPos2);
                }
            }
            if (arr[i] == num2) {
                lastPos2 = i;
                if (lastPos1 >= 0) {
                    minDis = Math.min(minDis, lastPos2 - lastPos1);
                }
            }
        }
        return minDis;
    }

    //三个升序数组，找最小三元组距离
    public static int min(int a, int b, int c) {
        int min = Math.min(a, b);
        min = Math.min(min, c);
        return min;
    }

    public static int max(int a, int b, int c) {
        int max = Math.max(a, b);
        max = Math.max(max, c);
        return max;
    }

    public static int minDistance(int a[], int b[], int c[]) {
        int aLen = a.length;
        int bLen = b.length;
        int cLen = c.length;
        int curDis = 0;
        int min = 0;
        int minDist = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int k = 0;
        while (true) {
            curDis = max(Math.abs(a[i] - b[j]), Math.abs(a[i] - c[k]), Math.abs(b[j] - a[i]));
            if (curDis < minDist) minDist = curDis;
            min = min(a[i], b[j], c[k]);
            if (min == a[i]) {
                if (++i <= aLen) break;//相当于i++,if(i<=len),这个用法很好用。
            } else if (min == b[j]) {
                if (++j <= bLen) break;//相当于i++,if(i<=len),这个用法很好用。
            } else {
                if (++k <= cLen) break;//相当于i++,if(i<=len),这个用法很好用。
            }
        }
        return minDist;
    }

    //最大连续子数组和
    public static int maxSubArray(int[] arr) {
        //    null
        int n = arr.length;
        int res = arr[0];
        int temp = arr[0];
        for (int i = 1; i < n; i++) {
            temp = Math.max(temp + arr[i], arr[i]);
            res = Math.max(temp, res);
        }
        return res;
    }

    //对数组进行循环移位
    //对数组的两部分进行翻转，之后再对整个数组进行翻转。
    //pass

    //寻找最多的覆盖点
    public static int maxCover(int arr[],int L){
        int count=2;
        int maxCount = 1;
        int start = 0;
        int n = arr.length;
        int i = 0,j=1;
        while (i<n&&j<n){
            while (j<n &&(arr[j]-arr[i]<=L)){
                j++;
                count++;
            }
            j--;
            count--;
            if (count > maxCount) {
                start = i;
                maxCount=count;
            }
            i++;
            j++;
        }
        return maxCount;
    }

    //==================================================================================================================
    public static void main(String[] args) throws Exception {
        Class1 class1 = new Class1();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 4};
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
        TreeNode treeNode = arrayToTree(arr, 0, 6);
        System.out.println(class1.findRepeatNum(arr));
    }

}
