package Experiment2;

public class Back4TSP {

	int NoEdge = -1;
	int bigInt = Integer.MAX_VALUE;
	int[][] a; // 邻接矩阵
	int cc = 0; // 存储当前代价
	int bestc = bigInt;// 当前最优代价
	int[] x; // 当前解
	int[] bestx;// 当前最优解
	int n = 0; // 顶点个数
	
	private void backtrack(int i) {//i为初始深度
		if (i > n) {
			//TODO
			bestc = cc;
			bestx = x;
		} else {
			//TODO
			for(int j = i;j<=n;j++) {            //对每个深度的城市及后续城市进行交换
				if(j!=i)swap(i,j);
				if(check(i) && cc<bestc) {       //前一个节点到当前节点有通路
					backtrack(i+1);
					if(i==n)cc -= a[x[n]][1];    //恢复cc
					cc -= a[x[i-1]][x[i]];
				}
				if(j!=i)swap(i,j);           //恢复现场
			}
		}

	}
	
	private void swap(int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}
	
	public boolean check(int pos) {
		//TODO
		if(pos!=n) {                             //不是最后一个城市时，只需考虑该城市与上一个城市间是否有边
			if(a[x[pos-1]][x[pos]] != -1) {      //有边时则更新当前花费
				cc += a[x[pos-1]][x[pos]];
				return true;
			}
			return false;	                     //不存在边时返回false
		}
		else {                                   //是最后一个城市时，还需考虑该城市和出发城市是否有边
			if((a[x[pos-1]][x[pos]] != -1)&&(a[x[pos]][1] != -1)) {
				cc += a[x[pos-1]][x[pos]] + a[x[pos]][1];
				return true;
			}
		return false;
		}
	}
	
	public void backtrack4TSP(int[][] b, int num) {
		n = num;
		x = new int[n + 1];
		for (int i = 0; i <= n; i++)
			x[i] = i;
		bestx = new int[n + 1];
		a = b;
		backtrack(2);
	}

}
