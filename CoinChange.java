//Dynamic Programming Example
public class CoinChange {
   /*
    amount = 10
    coins [1, 5, 10, 25] - unlimited supply
    sol 1= 1 coin * 10
    sol 2= 1 coin * 5 + 5 coin * 1
    sol 3 = 5 coin * 2
    sol 4 = 10 coin * 1
    */
    public static void main(String [] args){
        int[] coins = {1,5,10,25};
        int amount = 10;
        Integer[][] memo = new Integer[amount+1][coins.length+1];//amount*coins.length
        int[][] dp = new int[coins.length+1][amount+1];
        System.out.println("Number of ways to get amount using Recursion: "+getNoOfWays(coins,amount,0));
        System.out.println("Number of ways to get amount using DP Top Down(Memoization+Lookup Array): "+getNoOfWaysMemo(coins,amount,0,memo));
        System.out.println("Number of ways to get amount using DP Bottom Up(Iterative+Tabulation): "+getNoOfWaysTab(coins,amount,dp));
   
    }
    
    //Using only Recursion
    private static int getNoOfWays(int[] coins, int amount, int index) {
        int ways = 0;

        if(index>=coins.length) return 0;
        if(amount<0) return 0;
        if(amount==0) return 1;

        //we need to trverse coins array
        int includingways=0;
        int excludingways = 0;
        if(coins[index]<=amount){
            includingways = getNoOfWays(coins, amount-coins[index], index);
        }
        excludingways = getNoOfWays(coins, amount, index+1);
        ways = includingways+excludingways;
        return ways;
    }
    
    //Using DP Top Down (Recursion+Lookup array)
    //Time Complexity: O(coins.length)(Amount)
    //Space Complexity: O(coins.length)(Amount)
    private static int getNoOfWaysMemo(int[] coins, int amount, int index, Integer[][] memo) {
        if(memo[amount][index]!=null) return memo[amount][index];
        int ways = 0;

        if(index>=coins.length) return 0;
        if(amount<0) return 0;
        if(amount==0) return 1;

        //we need to trverse coins array
        int includingways=0;
        int excludingways = 0;
        if(coins[index]<=amount){
            includingways = getNoOfWaysMemo(coins, amount-coins[index], index,memo);
        }
        excludingways = getNoOfWaysMemo(coins, amount, index+1,memo);
        ways = includingways+excludingways;
        memo[amount][index] = ways;
        return memo[amount][index];
    }


    //Using DP Bottom Up (Iterative+Tabulation)
    //Time Complexity: O(coins.length)(Amount)
    //Space Complexity: O(coins.length)(Amount)
    private static int getNoOfWaysTab(int[] coins, int amount, int[][] dp) {
        for(int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                int currentamount = j;
                int currentcoin = coins[i-1];
                //inclusion
                int include = 0;
                if(currentcoin<=currentamount){
                    include = dp[i][currentamount-currentcoin];
                }
                int exclude = dp[i-1][j];
                dp[i][j] = include+exclude;
                //exclusion

            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }

    
}

