package Experiment2;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

public class BB4TSP {

	int NoEdge = -1; //��ʾû�б�
	private int minCost = Integer.MAX_VALUE; //��ǰ��С����
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
	
	private LinkedList<HeapNode> heap = new LinkedList<HeapNode>();//�洢��ڵ�
	private Vector<Integer> bestH = new Vector<Integer>();         //���Žڵ�����
	
	
	@SuppressWarnings("rawtypes")
	public static class HeapNode implements Comparable{
		Vector<Integer> liveNode = new Vector<Integer>();		//��������
		int lcost; 						//���۵��½�
		int level;						//��ǰ����
		//���췽��
		public HeapNode(Vector<Integer> node,int lb, int lev){
			liveNode.addAll(0, node);
			lcost = lb;
			level = lev;
		}
		
		@Override
		public int compareTo(Object x) {		//�����½���������, ÿһ��pollFirst
			int xu=((HeapNode)x).lcost;
			if(lcost<xu) return -1;
			if(lcost==xu) return 0;
			return 1;
		}
		public boolean equals(Object x){
			return lcost==((HeapNode)x).lcost;
		}

	}
	


	public int computeLB(Vector<Integer> liveNode, int level, int[][] cMatrix)   //���㲿�ֽ���½�
	{
		//TODO
		int lcost =0;    //�ܵ��½�
		int average =0;
		int n = cMatrix.length-1;
		for(int i=1;i<=level-1;i++) {                           //�ȼ�����ȷ���ĳ���֮���·����livenode�����0��n-1
			lcost += cMatrix[liveNode.get(i)][liveNode.get(i+1)];
		}
		if(level == n && cMatrix[liveNode.get(level)][1] != -1) lcost +=  cMatrix[liveNode.get(level)][1];
		else if(level ==n && cMatrix[liveNode.get(level)][1] == -1) lcost = Integer.MAX_VALUE;
		else if(level != n) {
			for(int i=level;i<=n;i++) {               //�ټ���δȷ�����е��½�
				int node = liveNode.get(i);
				if(i != n) {
					average = (minEvery[node][0] + minEvery[liveNode.get(i+1)][1])/2;   //ƽ��ֵ�趨Ϊ�ýڵ�ĳ��Ⱥ���һ���ڵ���������ٳ��Զ�
					lcost += average; 
				}
				else lcost +=  minEvery[1][1];     //���һ���ڵ�ֻ��Ҫ���Ͻڵ�1�����
			}
		}
		//System.out.println("lcost is "+ lcost);
		return lcost;
	}
	


	@SuppressWarnings("unchecked")
	public int bb4TSP(int[][] cMatrix, int n)
	{
		//�����ʼ�ڵ�
		Vector<Integer> liveNode = new Vector<Integer>() ;//��������
		liveNode.add(0);
		for(int i = 1; i<=n; i++) liveNode.add(i);
		int level = 2;										//0-level�ĳ������Ѿ��źõ�
		minEvery = calculateMin(cMatrix);                   //����ÿһ���ڵ���С����Ⱥͳ���
		int lcost = computeLB(liveNode, level, cMatrix) ; //���۵��½�
		HeapNode currentNode = new HeapNode(liveNode, lcost, 1);
		heap.add(currentNode);
		while(level <= n)
		{
			//TODO
			//�ο����ȶ��У���ͣ��չ�ڵ�,ѡȡ��һ���ڵ�
			if(level == n) {                   //����Ҷ�ӽڵ㲢�Ҵ�ʱҶ�ӽڵ���Ͻ�	
				if(currentNode.lcost < minCost) {
					//System.out.println("Arrived leaf!");
					bestH = currentNode.liveNode;
					minCost = currentNode.lcost;	


					break;
				}
			}else {
				for(int i=level;i<=n;i++) {
					if(Check(liveNode,level,i,cMatrix)) {                        //�����������������
						Collections.swap(currentNode.liveNode, level, i);        //��������
						int temp =computeLB(currentNode.liveNode, level, cMatrix);
						  
							HeapNode node = new HeapNode(currentNode.liveNode,temp, level+1);
							//System.out.println("New Node added.Lcost is:" + temp);
							if(level+1 ==n) node.lcost =computeLB(currentNode.liveNode, level+1, cMatrix) ;
							heap.add(node);
						
						Collections.swap(currentNode.liveNode, level, i);        //�ָ��ֳ�
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
	
	private int[][] calculateMin(int[][] cMatrix){     //����ó�������ÿһ��ÿһ�е���Сֵ�������½����
		int[][] minEvery = new int[cMatrix.length][2]; //ͬʱ�����ظ�����
		for(int i=0;i<cMatrix.length;i++) {
			int minRow = Integer.MAX_VALUE;
			int minColumn = Integer.MAX_VALUE;
			for(int j=0;j<cMatrix.length;j++) {
				if(cMatrix[i][j]<minRow && cMatrix[i][j]>0)minRow = cMatrix[i][j];
				if(cMatrix[j][i]<minColumn && cMatrix[j][i]>0)minColumn = cMatrix[j][i];
			}
			minEvery[i][0] = minRow;                 //minEvery[][0]����ÿһ�е���Сֵ
			//System.out.println("��"+ i +"����СֵΪ:" + minRow);
			minEvery[i][1] = minColumn;              //minEvery[][1]����ÿһ�е���Сֵ
			//System.out.println("��"+ i +"����СֵΪ:" + minColumn);
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
