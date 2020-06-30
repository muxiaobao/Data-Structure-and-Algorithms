#include<iostream>
#include<vector>
using namespace std;

int nextChild (vector<int>& weights, int root) {
	int l = root * 2;
	int r = l + 1;
	int smallest = root;
	if (l < weights.size() && weights[l] < weights[root]) 
		smallest = l;
	if (r < weights.size() && weights[r] < weights[l]) smallest = r; 
	return smallest;
}

void Adjust (vector<int>& weights, int root) {
	int smallest = nextChild(weights, root);
	if (smallest == root) return;
	while (weights[root] > weights[smallest]) {
		swap (weights[root], weights[smallest]);
		root = smallest;
		smallest = nextChild(weights, root);
	}
}

void buildMinHeap(vector<int>& weights) {
	int n = weights.size() - 1;
	
	for (int i = n/2; i > 0; i--)
		Adjust(weights, i);
}

int kthSmallest(vector<int>& weights, int k) {
	// build a min-heap
	buildMinHeap(weights);
	// pop the first k-1 smallest elements out of the min-heap
	for (int i = 1; i < k; i++) {
		int end = weights.size()-1;
		swap(weights[1], weights[end]);
		weights.pop_back();
		Adjust(weights, 1);
	}
	
	return weights[1];
}

int main() {
	vector<int> weights;
	// used to froward the index range to [1,...,n]
	weights.push_back(-1);
	int amount, k, num;
	cin >> amount;
	cin >> k;
	while (amount > 0) {
		cin >> num;
		weights.push_back(num);
		amount--;
	}
	
	cout << kthSmallest(weights, k);
		
	return 0;
}



