package dsaProblems.arrayds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ArrayDS {

	public List<Integer> returnDuplicates() {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(1);
		Collections.sort(list);
		List<Integer> l2 = new ArrayList<>();
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i) == list.get(i + 1)) {
				l2.add(list.get(i));
			}
		}
		return l2;
	}

	String replaceVowels(String s) {
		StringBuilder sb = new StringBuilder();
		List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

		for (char c : s.toCharArray()) {
			if (!vowels.contains(c)) {
				sb.append(String.valueOf(c));
			}
		}
		return sb.toString();
	}

	public int longestPalindromeSubseq(String s) {
		int[][] dp = new int[s.length()][s.length()];

		for (int i = s.length() - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][s.length() - 1];
	}

	public int maxEvents(int[][] events) {
		Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
		Queue<Integer> pq = new PriorityQueue<>(); // priority Queue by events expiry (end time)

		int d = 1, i = 0, n = events.length, res = 0;

		while (i < n || !pq.isEmpty()) {
			// remove exired events that we couldn't attend any more
			while (!pq.isEmpty() && pq.peek() < d)
				pq.poll();

			// add all the events happening on day d
			while (i < n && events[i][0] == d) {
				pq.offer(events[i++][1]);
			}
			// attend event which expires first
			if (!pq.isEmpty()) {
				pq.poll();
				res++;
			}

			d++;
		}

		return res;
	}

	public String frequencySort(String s) {
		List<Character> list = new ArrayList<>();
		for (char c : s.toCharArray()) {
			list.add(c);
		}
		Collections.sort(list, (a, b) -> Collections.frequency(list, b) - Collections.frequency(list, a));

		StringBuilder sb = new StringBuilder();
		for (char c : list) {
			sb.append(c);
		}
		return sb.toString();
	}

	public boolean carPooling(int[][] trips, int capacity) {

		int[] timeArray = new int[1001];
		for (int[] trip : trips) {
			timeArray[trip[1]] += trip[0];
			timeArray[trip[2]] -= trip[0];
			System.out.println(trip[1] + "->" + timeArray[trip[1]] + "\n" + trip[2] + "->" + timeArray[trip[2]] + "\n");
		}
		int usedCapacity = 0;
		for (int number : timeArray) {
			usedCapacity += number;
			if (usedCapacity > capacity) {
				return false;
			}
		}
		return true;

	}

	public String longestDiverseString(int a, int b, int c) {
		StringBuilder sb = new StringBuilder();
		int size = a + b + c;
		int A = 0, B = 0, C = 0;
		for (int i = 0; i < size; i++) {
			if ((a >= b && a >= c && A != 2) || (B == 2 && a > 0) || (C == 2 && a > 0)) {
				sb.append("a");
				a--;
				A++;
				B = 0;
				C = 0;
			} else if ((b >= a && b >= c && B != 2) || (A == 2 && b > 0) || (C == 2 && b > 0)) {
				sb.append("b");
				b--;
				B++;
				A = 0;
				C = 0;
			} else if ((c >= a && c >= b && C != 2) || (B == 2 && c > 0) || (A == 2 && c > 0)) {
				sb.append("c");
				c--;
				C++;
				A = 0;
				B = 0;
			}
		}
		return sb.toString();
	}

	public int findKthPositive(int[] arr, int k) {
		int left = 0, right = arr.length - 1;
		while (left <= right) {
			int pivot = left + (right - left) / 2;
			// If number of positive integers
			// which are missing before arr[pivot]
			// is less than k -->
			// continue to search on the right.
			if (arr[pivot] - pivot - 1 < k) {
				left = pivot + 1;
				// Otherwise, go left.
			} else {
				right = pivot - 1;
			}
		}

		// At the end of the loop, left = right + 1,
		// and the kth missing is in-between arr[right] and arr[left].
		// The number of integers missing before arr[right] is
		// arr[right] - right - 1 -->
		// the number to return is
		// arr[right] + k - (arr[right] - right - 1) = k + left
		return left + k;
	}

	public int missingElement(int[] nums, int k) {
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] - nums[0] - mid < k)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return nums[0] + k + left - 1;
	}

	public boolean wordPattern(String pattern, String s) {
		Map map = new HashMap();
		String[] arr = s.split(" ");
		if (arr.length != pattern.length()) {
			return false;
		}
		for (Integer i = 0; i < pattern.length(); ++i) {
			if (map.put(pattern.charAt(i), i) != map.put(arr[i], i))
				return false;
		}
		return true;
	}

	public int titleToNumber(String columnTitle) {
		int result = 0;
		for (int i = 0; i < columnTitle.length(); i++) {
			result = result * 26 + (columnTitle.charAt(i) - 'A' + 1);
		}
		return result;
	}

	public boolean isIsomorphic(String s, String t) {
		int[] m1 = new int[256], m2 = new int[256];
		int n = s.length();
		char[] s1 = s.toCharArray();
		char[] t1 = t.toCharArray();
		for (int i = 0; i < n; ++i) {
			if (m1[s1[i]] != m2[t1[i]])
				return false;
			m1[s1[i]] = i + 1;
			m2[t1[i]] = i + 1;
		}
		return true;
	}

	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (i > k)
				set.remove(nums[i - k - 1]);
			if (!set.add(nums[i]))
				return true;
		}
		return false;
	}

	public List<Integer> partitionLabels(String S) {
		Integer[] positions = new Integer[26];
		char[] chs = S.toCharArray();
		for (int i = 0; i < chs.length; i++)
			positions[chs[i] - 'a'] = i;
		List<Integer> resLs = new ArrayList<>();
		int pos = 0, end = 0, anchor = 0;
		while (pos < chs.length) {
			end = Math.max(positions[chs[pos] - 'a'], end);
			if (pos == end) {
				resLs.add(pos - anchor + 1);
				anchor = pos + 1;
			}
			pos++;
		}
		return resLs;
	}

	public int maxProfit(int[] inventory, int orders) {
		int profit = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (int n : inventory) {
			pq.add(n);
		}
		while (orders > 0 && !pq.isEmpty()) {
			int n = pq.poll();
			orders--;
			profit += n;
			if (orders == 0) {
				return profit % 1000000007;
			}
			if (!pq.isEmpty() && pq.peek() < n - 1) {
				pq.add(n - 1);
			}
		}
		return (profit % 1000000007);
	}

	static String chars[] = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public List<String> letterCombinations(String digits) {

		int n = digits.length();
		LinkedList<String> ans = new LinkedList<>();
		if (n == 0) {
			return ans;
		}
		ans.add("");
		for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			while (ans.peek().length() == i) {
				String t = ans.remove();
				for (char s : chars[x].toCharArray())
					ans.add(t + s);
			}
		}
		return ans;
		// findCombination(digits,0,"");
		// return list;
	}

	public List<List<String>> groupAnagrams(String[] strs) {

		List<List<String>> result = new ArrayList<>();

		HashMap<String, ArrayList<String>> map = new HashMap<>();
		ArrayList<String> al;
		for (int i = 0; i < strs.length; i++) {
			String s1 = strs[i];
			char ch[] = strs[i].toCharArray();

			Arrays.sort(ch);

			String ch1 = new String(ch);

			if (map.containsKey(ch1)) {
				al = map.get(ch1);
			} else {
				al = new ArrayList<>();
			}
			al.add(s1);
			map.put(ch1, al);
		}
		for (Map.Entry<String, ArrayList<String>> ans : map.entrySet()) {
			result.add(ans.getValue());
		}
		return result;
	}

	public int[] productExceptSelf(int[] nums) {// [1,2,3,4]
		int n = nums.length;
		int[] res = new int[n];
		// Calculate lefts and store in res.
		int left = 1;
		for (int i = 0; i < n; i++) {
			if (i > 0)
				left = left * nums[i - 1];
			res[i] = left;
		}
		// Calculate rights and the product from the end of the array.
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (i < n - 1)
				right = right * nums[i + 1];
			res[i] *= right;
		}
		return res;
	}

	public int findDuplicate(int[] nums) {
		int slow = nums[0];
		int fast = nums[nums[0]];
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		fast = 0;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;

	}

	public int longestSubstring(String s, int k) {

		if (s.length() < k)
			return 0;
		int[] count = new int[26];
		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < s.length(); i++) {
			if (count[s.charAt(i) - 'a'] >= k) {
				continue;
			}
			int j = i + 1;
			while (j < s.length() && count[s.charAt(j) - 'a'] < k) {
				j++;
			}
			return Math.max(longestSubstring(s.substring(0, i), k), longestSubstring(s.substring(j), k));
		}
		return s.length();
	}

	public int findMaxLength(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0)
				nums[i] = -1;
		}

		Map<Integer, Integer> sumToIndex = new HashMap<>();
		sumToIndex.put(0, -1);
		int sum = 0, max = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sumToIndex.containsKey(sum)) {
				max = Math.max(max, i - sumToIndex.get(sum));
			} else {
				sumToIndex.put(sum, i);
			}
		}

		return max;
	}

	public boolean isRobotBounded(String instructions) {

		int x = 0, y = 0, i = 0, d[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		for (int j = 0; j < instructions.length(); ++j)
			if (instructions.charAt(j) == 'R')
				i = (i + 1) % 4;
			else if (instructions.charAt(j) == 'L')
				i = (i + 3) % 4;
			else {
				x += d[i][0];
				y += d[i][1];
			}
		return x == 0 && y == 0 || i > 0;
	}

	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
		while (tx >= sx && ty >= sy) {
			if (tx > ty) {
				if (sy == ty) {
					return (tx - sx) % ty == 0;
				}
				tx %= ty;
			} else {
				if (sx == tx) {
					return (ty - sy) % tx == 0;
				}
				ty %= tx;
			}
		}
		return false;
	}

	public boolean isMatchR(String s, String p) {
		int is = 0, ip = 0, star = -1, match = 0;
		while (is < s.length()) {
			if (ip < p.length() && (p.charAt(ip) == '?' || p.charAt(ip) == s.charAt(is))) {
				ip++;
				is++;
			} else if (ip < p.length() && p.charAt(ip) == '*') {
				star = ip;
				match = is;
				ip++;
			} else if (star != -1) {
				ip = star + 1;
				match++;
				is = match;

			} else {
				return false;
			}
		}

		while (ip < p.length() && p.charAt(ip) == '*') {
			ip++;
		}

		return ip == p.length();
	}

	public boolean isMatch(String s, String p) {
		if (p.length() == 0) {
			return s.length() == 0;
		}
		if (p.length() > 1 && p.charAt(1) == '*') {
			if (isMatch(s, p.substring(2))) {
				return true;
			}
			if (s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
				return isMatch(s.substring(1), p);
			}
			return false;
		} else {
			if (s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
				return isMatch(s.substring(1), p.substring(1));
			}
			return false;
		}

	}

	public String minWindow(String s, String t) {
		if (t.length() > s.length())
			return "";
		Map<Character, Integer> map = new HashMap<>();
		for (char c : t.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int counter = map.size();

		int begin = 0, end = 0;
		int head = 0;
		int len = Integer.MAX_VALUE;

		while (end < s.length()) {
			char c = s.charAt(end);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);
				if (map.get(c) == 0)
					counter--;
			}
			end++;

			while (counter == 0) {
				char tempc = s.charAt(begin);
				if (map.containsKey(tempc)) {
					map.put(tempc, map.get(tempc) + 1);
					if (map.get(tempc) > 0) {
						counter++;
					}
				}
				if (end - begin < len) {
					len = end - begin;
					head = begin;
				}
				begin++;
			}

		}
		if (len == Integer.MAX_VALUE)
			return "";
		return s.substring(head, head + len);
	}

	public int firstMissingPositive(int[] nums) {
		int i = 0;
		while (i < nums.length) {
			int correct = nums[i] - 1;
			if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correct]) {
				int temp = nums[i];
				nums[i] = nums[correct];
				nums[correct] = temp;
			} else {
				i++;
			}
		}
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return nums.length + 1;
	}

	public String fractionToDecimal(int num, int den) {

		if (num == 0)
			return "0";

		StringBuilder res = new StringBuilder();

		// Negative sign is appended if either of num or den is negative
		res.append((num > 0) ^ (den > 0) ? "-" : "");

		// Get rid of signs and convert to long to prevent overflow
		long n = Math.abs((long) num);
		long d = Math.abs((long) den);

		// Append the integral part
		res.append(n / d);

		n %= d;
		if (n == 0)
			return res.toString(); // Cause there is no fraction

		// Append the fraction
		res.append(".");
		HashMap<Long, Integer> hm = new HashMap(); // Map will store remainders and their positions
		hm.put(n, res.length());
		while (n != 0) { // We will break in case of recurring fraction
			n *= 10;
			res.append(n / d);
			n %= d;

			Integer remainderIndex = hm.get(n);
			if (remainderIndex != null) { // We have a recurrence
				res.insert(remainderIndex, "(");
				res.append(")");
				return res.toString();
			} else {
				hm.put(n, res.length()); // Add for future checks
			}
		}
		return res.toString();
	}

	public String largestNumber(int[] nums) {
		String[] array = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
		Arrays.sort(array, (String s1, String s2) -> (s2 + s1).compareTo(s1 + s2));
		return Arrays.stream(array).reduce((x, y) -> x.equals("0") ? y : x + y).get();
	}

	public double myPow(double x, int n) {
		long pow = n;
		if (pow < 0) {
			pow = -1 * pow;
		}
		double ans = 1.0;
		while (pow > 0) {
			if (pow % 2 == 1) {
				ans = ans * x;
				pow = pow - 1;
			} else {
				x = x * x;
				pow = pow / 2;
			}
		}
		if (n < 0) {
			ans = (double) 1.0 / (double) ans;
		}
		return ans;
	}

	public int countPrimes(int n) {
		boolean[] notPrime = new boolean[n];
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (notPrime[i] == false) {
				count++;
				for (int j = 2; i * j < n; j++) {
					notPrime[i * j] = true;
				}
			}
		}

		return count;
	}
	public String addFraction(int num1,int den1,int num2,int den2) {
	
    int num=num1*den2+num2*den1;
    int den=den1*den2;
    int result=Math.abs(gcd(num,den));
    System.out.println(num/result+"/"+den/result);
    return num/result+"/"+den/result;
	}
	int gcd(int a,int b) {
		return (a==0||b==0)?a+b:gcd(b%a,a);
	}

}
