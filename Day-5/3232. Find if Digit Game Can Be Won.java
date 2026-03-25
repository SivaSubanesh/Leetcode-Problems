/*
3232. Find if Digit Game Can Be Won

You are given an array of positive integers nums.

Alice and Bob are playing a game. In the game, Alice can choose either all single-digit numbers or all double-digit numbers from nums, and the rest of the numbers are given to Bob. Alice wins if the sum of her numbers is strictly greater than the sum of Bob's numbers.

Return true if Alice can win this game, otherwise, return false.
 */

class Solution {
    public boolean canAliceWin(int[] nums) {
        int sum=0;
        for(int n:nums){
            if(n<10) sum+=n;
            else sum-=n;
        }
        return sum!=0;
    }
}