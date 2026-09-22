import java.util.List;
import java.util.ArrayList;

class Solution {
    private static class Node {
        int prod = 1;
        int[] remain;

        Node(int k) {
            remain = new int[k];
        }
    }

    private int n;
    private int k;
    private Node[] tree;

    private Node merge(Node left, Node right) {
        Node res = new Node(k);
        res.prod = (left.prod * right.prod) % k;

        for (int r = 0; r < k; ++r) {
            res.remain[r] = left.remain[r];
        }

        for (int r = 0; r < k; ++r) {
            if (right.remain[r] > 0) {
                int newR = (left.prod * r) % k;
                res.remain[newR] += right.remain[r];
            }
        }
        return res;
    }

    private void build(int[] nums, int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node(k);
            int val = nums[l] % k;
            tree[idx].prod = val;
            tree[idx].remain[val] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(nums, 2 * idx + 1, l, mid);
        build(nums, 2 * idx + 2, mid + 1, r);
        tree[idx] = merge(tree[2 * idx + 1], tree[2 * idx + 2]);
    }

    private void update(int idx, int l, int r, int pos, int val) {
        if (l == r) {
            int modVal = val % k;
            tree[idx].prod = modVal;
            for (int i = 0; i < k; ++i) {
                tree[idx].remain[i] = 0;
            }
            tree[idx].remain[modVal] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (pos <= mid) {
            update(2 * idx + 1, l, mid, pos, val);
        } else {
            update(2 * idx + 2, mid + 1, r, pos, val);
        }
        tree[idx] = merge(tree[2 * idx + 1], tree[2 * idx + 2]);
    }

    private Node query(int idx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[idx];
        }
        int mid = l + (r - l) / 2;
        if (qr <= mid) {
            return query(2 * idx + 1, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * idx + 2, mid + 1, r, ql, qr);
        }

        Node left = query(2 * idx + 1, l, mid, ql, qr);
        Node right = query(2 * idx + 2, mid + 1, r, ql, qr);
        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(nums, 0, 0, n - 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(0, 0, n - 1, idx, val);
            Node res = query(0, 0, n - 1, start, n - 1);
            ans[i] = res.remain[x];
        }

        return ans;
    }
}