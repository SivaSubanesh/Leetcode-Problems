/*
397. Integer Replacement

Given a positive integer n, you can apply one of the following operations:

If n is even, replace n with n / 2.
If n is odd, replace n with either n + 1 or n - 1.
Return the minimum number of operations needed for n to become 1.
*/
/* Check out Collataz Conjecture */
class Solution {
    public int integerReplacement(int n) {
        int ans=0;
        long num=n;
        while(num>1){
            if(num%2==0) num/=2;
            else{
                if(num%4==1 || num==3) num--;
                else num++;
            }
            ans++;
        }
        return ans;
    }
}