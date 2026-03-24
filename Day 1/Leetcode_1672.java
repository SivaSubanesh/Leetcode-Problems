class Solution {
    public int maximumWealth(int[][] accounts) {
        int maxi=0;
        for(int i=0; i<accounts.length;i++){
            int sum=0;
            for(int j=0;j<accounts[i].length;j++){
                sum=sum+accounts[i][j];

            }
            maxi=Math.max(maxi,sum);
        }
        return maxi;
    }
}