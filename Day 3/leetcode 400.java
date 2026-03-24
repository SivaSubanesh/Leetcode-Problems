class Solution {
    public int findNthDigit(int n) {
       if(n<=9) return n;
       long count=9;
       long digit=1;
       long start=1;
       while(n>(digit*count))
       {
        n-=(digit*count);
        digit++;
        count*=10;
        start*=10;

       } 
       long num= start+(n-1)/digit;
       String str= Long.toString(num);
       int ind=(n-1)%(int)digit;
       char ch=str.charAt(ind);
       return ch-'0';
    }
}