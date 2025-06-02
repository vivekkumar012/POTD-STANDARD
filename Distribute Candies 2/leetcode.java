// Optimised approach
class Solution {
    public long distributeCandies(int n, int limit) {
        long ways = 0;
        int ch1min = Math.max(0, n-2*limit);
        int ch1max = Math.min(n, limit);

        for(int i=ch1min; i<=ch1max; i++) {
            int rem = n-i;
            //ab 2 child wala case 
            int ch2min = Math.max(0, rem-limit);
            int ch2max = Math.min(rem, limit);

            ways += ch2max-ch2min+1;
        }
        return ways;
    }
}
