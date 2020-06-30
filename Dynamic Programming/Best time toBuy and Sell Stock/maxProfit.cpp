#include<iostream>
#include<vector>
using namespace std;

/** dp[i] represents the maximum profit you can get based on the prefix-subarray "prices[0,..,i]"
  * @param pre_min is the minimum price in the subarray "prices[0,...,i]", which is used for latter 
  * update (if we don't record this, we have to scan the previously items at each iteration, leading
  * to a O(n^2) time complexity
  * @equation dp[i] = max (dp[i-1], prices[i] - pre_min) 
  * this means that you can either buy and sell your stocks before the day "i", or you can sell it 
  * exactly in the "i" day.
  */ 
int BestTimeToBuyStock (vector<int>& prices) {
	if (prices.empty()) return 0;
 	int pre_min = prices[0];
	int n = prices.size();
	int dp[n] = {};
	
	for (int i = 1; i < n; i++) {
		pre_min = min (pre_min, prices[i]);
		dp[i] = max (dp[i-1], prices[i] - pre_min);
	}
	
	return dp[n-1];
}

int main() {
	int number, temp;
	vector<int> prices;
	cin >> number;
	while (number > 0) {
		cin >> temp;
		prices.push_back(temp);
		number--;
	}
	
	cout << BestTimeToBuyStock(prices);

	return 0;
}



