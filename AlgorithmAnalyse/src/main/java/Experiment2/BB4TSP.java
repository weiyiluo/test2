package Experiment2;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

public class BB4TSP {

	int NoEdge = -1; //表示没有边
	private int minCost = Integer.MAX_VALUE; //当前最小代价
	private int[][] minEvery = {};
	public int getMinCost() {
		return minCost;
	}

	public void setMinCost(int minCost) {
		this.minCost = minCost;
	}
	
	public static void main(String[] args) {
		BB4TSP bb4TSP = new BB4TSP();
		int[][] b = { 
				{ -1, -1, -1, -1, -1 }, 
				{ -1, -1, 9, 19, 13 }, 
				{ -1, 21, -1, -1, 14 }, 
				{ -1, 1, 40, -1, 17 },
				{ -1, 41, 80, 10, -1 } 
				};
		int n = 4;
		System.out.println("Result is:" + bb4TSP.bb4TSP(b, n) );
		return ;
	}
	
	private LinkedList<HeapNode> heap = new LinkedList<HeapNode>();//存储活节点
	private Vector<Integer> bestH = new Vector<Integer>();         //最优节点排列
	
	
	@SuppressWarnings("rawtypes")
	public static class HeapNode implements Comparable{
		Vector<Integer> liveNode = new Vector<Integer>();		//城市排列
		int lcost; 						//代价的下界
		int level;						//当前层数
		//构造方法
		public HeapNode(Vector<Integer> node,int lb, int lev){
			liveNode.addAll(0, node);
			lcost = lb;
			level = lev;
		}
		
		@Override
		public int compareTo(Object x) {		//按照下界升序排列, 每一次pollFirst
			int xu=((HeapNode)x).lcost;
			if(lcost<xu) return -1;
			if(lcost==xu) return 0;
			return 1;
		}
		public boolean equals(Object x){
			return lcost==((HeapNode)x).lcost;
		}

	}
	


	public int computeLB(Vector<Integer> liveNode, int level, int[][] cMatrix)   //计算部分解的下界
	{
		//TODO
		int lcost =0;    //总的下界
		int average =0;
		int n = cMatrix.length-1;
		for(int i=1;i<=level-1;i++) {                           //先加上已确定的城市之间的路径，livenode储存从0到n-1
			lcost += cMatrix[liveNode.get(i)][liveNode.get(i+1)];
		}
		if(level == n && cMatrix[liveNode.get(level)][1] != -1) lcost +=  cMatrix[liveNode.get(level)][1];
		else if(level ==n && cMatrix[liveNode.get(level)][1] == -1) lcost = Integer.MAX_VALUE;
		else if(level != n) {
			for(int i=level;i<=n;i++) {               //再加上未确定城市的下界
				int node = liveNode.get(i);
				if(i != n) {
					average = (minEvery[node][0] + minEvery[liveNode.get(i+1)][1])/2;   //平均值设定为该节点的出度和下一个节点的入度相加再除以二
					lcost += average; 
				}
				else lcost +=  minEvery[1][1];     //最后一个节点只需要加上节点1的入度
			}
		}
		//System.out.println("lcost is "+ lcost);
		return lcost;
	}
	


	@SuppressWarnings("unchecked")
	public int bb4TSP(int[][] cMatrix, int n)
	{
		//构造初始节点
		Vector<Integer> liveNode = new Vector<Integer>() ;//城市排列
		liveNode.add(0);
		for(int i = 1; i<=n; i++) liveNode.add(i);
		int level = 2;										//0-level的城市是已经排好的
		minEvery = calculateMin(cMatrix);                   //计算每一个节点最小的入度和出度
		int lcost = computeLB(liveNode, level, cMatrix) ; //代价的下界
		HeapNode currentNode = new HeapNode(liveNode, lcost, 1);
		heap.add(currentNode);
		while(level <= n)
		{
			//TODO
			//参考优先队列，不停扩展节点,选取下一个节点
			if(level == n) {                   //到达叶子节点并且此时叶子节点的上界	
				if(currentNode.lcost < minCost) {
					//System.out.println("Arrived leaf!");
					bestH = currentNode.liveNode;
					minCost = currentNode.lcost;	


					break;
				}
			}else {
				for(int i=level;i<=n;i++) {
					if(Check(liveNode,level,i,cMatrix)) {                        //交换后可以满足条件
						Collections.swap(currentNode.liveNode, level, i);        //交换城市
						int temp =computeLB(currentNode.liveNode, level, cMatrix);
						  
							HeapNode node = new HeapNode(currentNode.liveNode,temp, level+1);
							//System.out.println("New Node added.Lcost is:" + temp);
							if(level+1 ==n) node.lcost =computeLB(currentNode.liveNode, level+1, cMatrix) ;
							heap.add(node);
						
						Collections.swap(currentNode.liveNode, level, i);        //恢复现场
					}
				}		
			}
			//heap.remove(currentNode);
			if(heap.isEmpty())break;
			Collections.sort(heap);
			currentNode = heap.pop();
			//System.out.println("There is a new Node out.Lcost is:" + currentNode.lcost +"\nNow level is:"+currentNode.level);
			level = currentNode.level;
		}
		return minCost;
	}
	
	private int[][] calculateMin(int[][] cMatrix){     //计算得出矩阵中每一行每一列的最小值，用于下界计算
		int[][] minEvery = new int[cMatrix.length][2]; //同时避免重复计算
		for(int i=0;i<cMatrix.length;i++) {
			int minRow = Integer.MAX_VALUE;
			int minColumn = Integer.MAX_VALUE;
			for(int j=0;j<cMatrix.length;j++) {
				if(cMatrix[i][j]<minRow && cMatrix[i][j]>0)minRow = cMatrix[i][j];
				if(cMatrix[j][i]<minColumn && cMatrix[j][i]>0)minColumn = cMatrix[j][i];
			}
			minEvery[i][0] = minRow;                 //minEvery[][0]储存每一行的最小值
			//System.out.println("第"+ i +"行最小值为:" + minRow);
			minEvery[i][1] = minColumn;              //minEvery[][1]储存每一列的最小值
			//System.out.println("第"+ i +"列最小值为:" + minColumn);
		}	
		return minEvery;
	}
	
	boolean Check(Vector<Integer> liveNode,int i,int j,int[][] cMatrix) {
		if(cMatrix[liveNode.get(i-1)][liveNode.get(j)] != -1) {
			return true;
		}
		else return false;	
	}
	
	
	
}
