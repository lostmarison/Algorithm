#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Mgraph {
   public:
    Mgraph(vector<int> a, int n, int e);  // 构造函数
    ~Mgraph() {}
    int MinEdge(vector<int>& adjvex,
                vector<int>& lowcost);  // 寻找最短边并返回顶点索引
    void Prim(int v);                   // Prim算法，从顶点v出发
    void print();                       // 辅助函数：打印邻接矩阵

   private:
    vector<int> vertex;        // 存放图中顶点的数组
    vector<vector<int>> edge;  // 存放图中边的数组（邻接矩阵）
    int vertexNum, edgeNum;    // 图的顶点数和边数
};

// 建立一个含有n个顶点e条边的图，顶点信息由a给出，边的信息由键盘输入，建立无向图
Mgraph::Mgraph(vector<int> a, int n, int e)
    : vertex(a), vertexNum(n), edgeNum(e), edge(n, vector<int>(n, INT_MAX)) {
    int i, j;    // 相邻顶点
    int weight;  // 相邻顶点权值
    for (int k = 0; k < edgeNum; ++k) {
        cin >> i >> j >> weight;  // 依次输入邻接点，权值
        edge[i][j] = weight;
        edge[j][i] = weight;  // 无向图满足对称性
    }
}

// 辅助函数：打印邻接矩阵
void Mgraph::print() {
    for (vector<int> row : edge) {
        for (int e : row) {
            // 若两个顶点不是相邻点，则权重为无穷（Inf）
            cout << ((e == INT_MAX) ? "Inf" : to_string(e)) << "\t";
        }
        cout << endl;
    }
}

int Mgraph::MinEdge(vector<int>& adjvex, vector<int>& lowcost) {
    int minedge = INT_MAX;
    int minIndex = -1;  // 初始化为-1或无效索引，表示尚未找到最小边
    for (int i = 0; i < vertexNum; ++i) {
        // 如果当前顶点已被访问，说明已被“连接”，不需要再比较
        if (adjvex[i] == 1) {
            continue;
        }
        if (lowcost[i] < minedge) {
            minedge = lowcost[i];
            minIndex = i;
        }
    }
    return minIndex;  // 返回最短边索引（顶点）
}

void Mgraph::Prim(int v) {
    int i, j, k;
    // 初始化
    vector<int> adjvex(edgeNum, 0);  // 访问情况（已知顶点集合）
    vector<int> lowcost(edgeNum);    // 候选最短边的权值
    for (i = 0; i < vertexNum; ++i) {
        lowcost[i] = edge[v][i];  // 权值
        adjvex[v] = 1;            // 将顶点v加入已知集合
    }
    cout << v << " ";  // 输出开始顶点
    // 找最近邻接点
    for (k = 1; k < vertexNum; ++k) {  // 还差vertexNum-1个顶点
        j = MinEdge(adjvex, lowcost);  // 最短边顶点
        cout << j << " ";              // 输出当前顶点
        adjvex[j] = 1;                 // 当前顶点加入已知顶点集合
        for (i = 0; i < vertexNum; ++i) {
            lowcost[i] = min(lowcost[i], edge[j][i]);  // 更新权值数组
        }
    }
}

int main() {
    vector<int> a = {0, 1, 2, 3, 4, 5};  // 顶点数组
    Mgraph graph(a, 6, 9);               // 6个顶点，8条边
    /*
    0 1 7
    0 2 1
    0 5 13
    1 2 8
    1 4 11
    2 3 9
    2 5 6
    3 4 10
    3 5 12
    */
    // 理论结果 0 2 5 1 3 4
    graph.Prim(1);  // 从0开始
    system("pause");
    return 0;
}
