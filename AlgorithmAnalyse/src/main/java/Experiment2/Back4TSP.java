package Experiment2;

public class Back4TSP {

	int NoEdge = -1;
	int bigInt = Integer.MAX_VALUE;
	int[][] a; // �ڽӾ���
	int cc = 0; // �洢��ǰ����
	int bestc = bigInt;// ��ǰ���Ŵ���
	int[] x; // ��ǰ��
	int[] bestx;// ��ǰ���Ž�
	int n = 0; // �������
	
	private void backtrack(int i) {//iΪ��ʼ���
		if (i > n) {
			//TODO
			bestc = cc;
			bestx = x;
		} else {
			//TODO
			for(int j = i;j<=n;j++) {            //��ÿ����ȵĳ��м��������н��н���
				if(j!=i)swap(i,j);
				if(check(i) && cc<bestc) {       //ǰһ���ڵ㵽��ǰ�ڵ���ͨ·
					backtrack(i+1);
					if(i==n)cc -= a[x[n]][1];    //�ָ�cc
					cc -= a[x[i-1]][x[i]];
				}
				if(j!=i)swap(i,j);           //�ָ��ֳ�
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
		if(pos!=n) {                             //�������һ������ʱ��ֻ�迼�Ǹó�������һ�����м��Ƿ��б�
			if(a[x[pos-1]][x[pos]] != -1) {      //�б�ʱ����µ�ǰ����
				cc += a[x[pos-1]][x[pos]];
				return true;
			}
			return false;	                     //�����ڱ�ʱ����false
		}
		else {                                   //�����һ������ʱ�����迼�Ǹó��кͳ��������Ƿ��б�
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
