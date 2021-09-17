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
	            for (int j = i+1; j < s.length(); j++) {
	                if (s.charAt(i) == s.charAt(j)) {
	                    dp[i][j] = dp[i+1][j-1] + 2;
	                } else {
	                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
	                }
	            }
	        }
	        return dp[0][s.length()-1];
	    }
	   public int maxEvents(int[][] events) {
	        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
	        Queue<Integer> pq = new PriorityQueue<>(); //priority Queue by events expiry (end time)
	        
	        int d = 1, i = 0, n = events.length, res = 0;
	        
	        while(i < n || !pq.isEmpty()) {
	            //remove exired events that we couldn't attend any more
	            while(!pq.isEmpty() && pq.peek() < d) 
	            	pq.poll();
	            
	            //add all the events happening on day d
	            while(i < n && events[i][0] == d) {
	                pq.offer(events[i++][1]);
	            }
	            //attend event which expires first
	            if(!pq.isEmpty()) {
	                pq.poll(); 
	                res++;
	            }
	            
	            d++;
	        }
	        
	        return res;
	    }
	   
	   public String frequencySort(String s) {
	        List<Character> list=new ArrayList<>();
	        for(char c:s.toCharArray()){
	            list.add(c);
	        }
	        Collections.sort(list,(a,b)->Collections.frequency(list,b)-
	                                                      Collections.frequency(list,a));
	       
	   
	        StringBuilder sb=new StringBuilder();
	        for(char c:list){
	            sb.append(c);
	        }
	        return sb.toString();
	    }
	   
	   public boolean carPooling(int[][] trips, int capacity) {
	        
	          int[] timeArray = new int[1001];
	        for (int[] trip : trips) {
	            timeArray[trip[1]] += trip[0];
	            timeArray[trip[2]] -= trip[0];
	            System.out.println( trip[1]+"->"+timeArray[trip[1]]+"\n"+trip[2]+"->"+ timeArray[trip[2]]+"\n");
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
	        for(int i = 0; i < size; i++) {
	            if ((a >= b && a >= c && A != 2) || (B == 2 && a > 0) || (C == 2 && a > 0))  {
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
	        while(left <= right){
	            int mid = left + (right - left) / 2;
	            if(nums[mid] - nums[0] - mid < k)
	                left = mid + 1;
	            else 
	                right = mid - 1;
	        }
	        return nums[0] + k + left - 1;
	    }
	   
	   public boolean wordPattern(String pattern, String s) {
	        Map map=new HashMap();
	        String[] arr=s.split(" ");
	        if(arr.length!=pattern.length()){
	            return false;
	        }
	        for(Integer i=0;i<pattern.length();++i){
	        if (map.put(pattern.charAt(i), i) != map.put(arr[i], i))
	            return false;
	        }
	        return true;
	    }
	   
	   public int titleToNumber(String columnTitle) {
	        int result = 0;
	        for (int i = 0; i < columnTitle.length(); i++){
	            result = result * 26 + (columnTitle.charAt(i) - 'A' + 1);
	        }
	        return result;
	    }
	   
	   public boolean isIsomorphic(String s, String t) {
		   int[] m1=new int[256], m2= new int[256];
	        int n = s.length();
	        char[] s1=s.toCharArray();
	        char[] t1=t.toCharArray();
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
	        for(int i = 0; i < nums.length; i++){
	            if(i > k) 
	            	set.remove(nums[i-k-1]);
	            if(!set.add(nums[i])) 
	            	return true;
	        }
	        return false;
	    }
	   
	   public List<Integer> partitionLabels(String S) {
	        Integer[] positions = new Integer[26];
	        char[] chs = S.toCharArray ();
	        for (int i = 0; i < chs.length; i++)
	            positions[chs[i] - 'a'] = i;
	        List<Integer> resLs = new ArrayList<> ();
	        int pos = 0, end = 0, anchor = 0;
	        while (pos < chs.length) {
	            end = Math.max (positions[chs[pos] - 'a'], end);
	            if (pos == end) {
	                resLs.add (pos - anchor + 1);
	                anchor = pos + 1;
	            }
	            pos++;
	        }
	        return resLs;
	    }
	   
	   public int maxProfit(int[] inventory, int orders) {
	        int profit=0;
	        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
	        for(int n:inventory){
	            pq.add(n);
	        }
	        while(orders>0 && !pq.isEmpty()){
	            int n=pq.poll();
	            orders--;
	            profit+=n;
	            if(orders==0){
	                return profit % 1000000007;
	            }
	            if(!pq.isEmpty() && pq.peek()<n-1){
	                pq.add(n-1);
	            }
	        }
	        return (profit % 1000000007);
	    }
	   
	   static String chars[] =  {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	    public List<String> letterCombinations(String digits) {
	      
	      
	      int n=digits.length();
	      LinkedList<String> ans=new LinkedList<>(); 
	          if(n==0){
	            return ans;
	        }
	        ans.add("");
			for(int i =0; i<digits.length();i++){
				int x = Character.getNumericValue(digits.charAt(i));
				while(ans.peek().length()==i){
					String t = ans.remove();
					for(char s : chars[x].toCharArray())
						ans.add(t+s);
				}
			}
			return ans;
	     //  findCombination(digits,0,"");
	     //  return list;
	    }
	    
}
