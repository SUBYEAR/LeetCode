package com.leetcode.medium.review.binarytree;

import java.util.Arrays;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 */
public class SwordPointAtOffer33 {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length < 2) {
            return true;
        }

        int length = postorder.length;
        int root = postorder[length - 1];
        int bound = findLeft(postorder, root);

        for (int i = 0; i < bound; i++) {
            if (postorder[i] > root) {
                return false;
            }
        }
        for (int i = bound; i < length - 1; i++) {
            if (postorder[i] < root) {
                return false;
            }
        }
        int[] left = bound > 1 ? Arrays.copyOfRange(postorder, 0, bound - 1) : null;
        int[] right = Arrays.copyOfRange(postorder, bound, length - 1);
        return verifyPostorder(left) && verifyPostorder(right);
    }

    int findLeft(int[] postorder, int root) {
        int len = postorder.length;
        int i = 0;
        for (; i < len - 1; i++) {
            if (postorder[i] > root) {
                break;
            }
        }
        return i;
    }
}

// 简洁写法
//    public boolean verifyPostorder(int[] postorder) {
//        return recur(postorder, 0, postorder.length - 1);
//    }
//    boolean recur(int[] postorder, int i, int j) {
//        if(i >= j) return true;
//        int p = i;
//        while(postorder[p] < postorder[j]) p++;
//        int m = p;
//        while(postorder[p] > postorder[j]) p++;
//        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
//    }

// 后续遍历结果是
// [3,6,5,9,8,11,13,12,10]
// 从前往后不好看，我们来从后往前看
// [10,12,13,11,8,9,5,6,3]

// 如果你仔细观察会发现一个规律，就是挨着的两个数如果arr[i]<arr[i+1]，那么arr[i+1]一定是arr[i]的右子节点，这一点是毋庸置疑的，
// 我们可以看下上面的10和12是挨着的并且10<12，所以12是10的右子节点。同理12和13，8和9，5和6，他们都是挨着的，并且前面的都是小于后面的，
// 所以后面的都是前面的右子节点。如果想证明也很简单，因为比arr[i]大的肯定都是他的右子节点，如果还是挨着他的，肯定是在后续遍历中所有的右子节点
// 最后一个遍历的，所以他一定是arr[i]的右子节点。

// 我们刚才看的是升序的，再来看一下降序的（这里的升序和降序都是基于后续遍历从后往前看的，也就是上面蓝色数组）。如果arr[i]>arr[i+1]，
// 那么arr[i+1]一定是arr[0]……arr[i]中某个节点的左子节点，并且这个值是大于arr[i+1]中最小的。我们来看一下上面的数组，比如13，11是降序的，
// 那么11肯定是他前面某一个节点的左子节点，并且这个值是大于11中最小的，我们看到12和13都是大于11的，但12最小，所以11就是12的左子节点。
// 同理我们可以观察到11和8是降序，8前面大于8中最小的是10，所以8就是10的左子节点。9和5是降序，6和3是降序，都遵守这个规律。
//
// 根据上面分析的过程，很容易想到使用栈来解决。遍历数组的所有元素，如果栈为空，就把当前元素压栈。如果栈不为空，并且当前元素大于栈顶元素，
// 说明是升序的，那么就说明当前元素是栈顶元素的右子节点，就把当前元素压栈，如果一直升序，就一直压栈。当前元素小于栈顶元素，说明是倒序的，
// 说明当前元素是某个节点的左子节点，我们目的是要找到这个左子节点的父节点，就让栈顶元素出栈，直到栈为空或者栈顶元素小于当前值为止，
// 其中最后一个出栈的就是当前元素的父节点。我们来看下代码

// public boolean verifyPostorder(int[] postorder) {
//    Stack<Integer> stack = new Stack<>();
//    int parent = Integer.MAX_VALUE;
//    //注意for循环是倒叙遍历的
//    for (int i = postorder.length - 1; i >= 0; i--) {
//        int cur = postorder[i];
//        //当如果前节点小于栈顶元素，说明栈顶元素和当前值构成了倒序，
//        //说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
//        while (!stack.isEmpty() && stack.peek() > cur)
//            parent = stack.pop();
//        //只要遇到了某一个左子节点，才会执行上面的代码，才会更
//        //新parent的值，否则parent就是一个非常大的值，也就
//        //是说如果一直没有遇到左子节点，那么右子节点可以非常大
//        if (cur > parent)
//            return false;
//        //入栈
//        stack.add(cur);
//    }
//    return true;
// }
// 上面代码可能大家有点蒙的是if(cur>parent)这一行的判断。二叉搜索树应该是左子节点小于根节点，右子节点大于根节点，
// 但上面为什么大于父节点的时候要返回false，注意这里的parent是在什么情况下赋的值，parent并不一定都是父节点的值，相对于遇到了左子节点的时候
// 他是左子节点的父节点。如果是右子节点，parent就是他的某一个祖先节点，
// 并且这个右子节点是这个祖先节点的一个左子树的一部分，所以不能超过他，有点绕，慢慢体会。
//
//作者：sdwwld
//链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/di-gui-he-zhan-liang-chong-fang-shi-jie-jue-zui-ha/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。