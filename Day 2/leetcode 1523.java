class Solution {
    public int countOdds(int low, int high) {
     int val=(high-low)+1;
        if(low%2!=0 && high%2!=0)
        return val/2 +1;
    else
    return val/2;  
    
    }}