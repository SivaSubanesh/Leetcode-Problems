/*
2455. Average Value of Even Numbers That Are Divisible by Three

Given an integer array nums of positive integers, return the average value of all even integers that are divisible by 3.

Note that the average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
*/

class Solution {
    public int averageValue(int[] nums) {
        int count=0;
        int sum=0;
        for(int n:nums){
            if(n%6==0){ 
                sum+=n;
                count++;
            }
        }
        return count==0?0:(sum/count);
    }
}