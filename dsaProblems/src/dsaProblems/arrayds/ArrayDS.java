package dsaProblems.arrayds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
	    
}
