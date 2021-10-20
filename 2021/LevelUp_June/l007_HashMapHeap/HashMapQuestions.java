import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HashMapQuestions {

    // 128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        for (int ele : nums)
            map.add(ele);

        int len = 0;
        for (int ele : nums) {
            if (!map.contains(ele))
                continue;

            int prev = ele - 1, next = ele + 1;
            map.remove(ele);
            while (map.contains(prev))
                map.remove(prev--);
            while (map.contains(next))
                map.remove(next++);

            len = Math.max(len, next - prev - 1);
        }

        return len;
    }

    // 781
    public int numRabbits(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length, ans = 0;

        for (int ele : arr) {
            if (!map.containsKey(ele)) {
                ans += 1 + ele;
                map.put(ele, 1);
            } else {
                map.put(ele, map.get(ele) + 1);
            }

            if (map.get(ele) == ele + 1)
                map.remove(ele);
        }

        return ans;

    }

    // 1218
    public int longestSubsequence(int[] arr, int d) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        for (int ele : arr) {
            map.put(ele, map.getOrDefault(ele - d, 0) + 1);
            maxLen = Math.max(maxLen, map.get(ele));
        }

        return maxLen;

    }

    // 1424
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();

        int maxDiag = 0;
        int len = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int idx = i + j;
                map.putIfAbsent(idx, new LinkedList<>());
                map.get(idx).addFirst(nums.get(i).get(j)); // map[idx].push_front(ele);

                maxDiag = Math.max(maxDiag, idx);
                len++;
            }
        }

        int[] ans = new int[len];
        int idx = 0;
        for (int i = 0; i <= maxDiag; i++) {
            LinkedList<Integer> list = map.get(i);
            while (list.size() != 0) {
                ans[idx++] = list.removeFirst();
            }
        }

        return ans;

    }

    // 1027
    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++)
            dp[i] = new HashMap<>();

        int len = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int diff = A[i] - A[j];
                int currLen = dp[i].getOrDefault(diff, 0);
                int newLen = dp[j].getOrDefault(diff, 1) + 1;

                dp[i].put(diff, Math.max(currLen, newLen));
                len = Math.max(len, dp[i].get(diff));
            }
        }

        return len;
    }

}