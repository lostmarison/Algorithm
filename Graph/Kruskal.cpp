#include <algorithm>  // 用于std::sort
#include <iostream>   // 用于输入输出
#include <vector>     // 用于动态数组
using namespace std;

// 定义边结构体
struct EdgeType {
    int from;    // 边的起点
    int to;      // 边的终点
    int weight;  // 边的权重
};

// 定义图类
class EdgeGraph {
   public:
    EdgeGraph(vector<int> a, int n, int Kruskale);  // 构造函数，初始化图
    ~EdgeGraph() {}  // 析构函数，这里不需要特别处理
    void Kruskal();  // Kruskal算法实现

   private:
    vector<int> vertex;      // 顶点数组
    vector<EdgeType> edge;   // 边的数组
    int vertexNum, edgeNum;  // 顶点数和边数
    vector<int> parent;      // 用于存储并查集的父节点
    void edgeSort();         // 对边按权重进行排序
    int findRoot(vector<int>& parent,
                 int v);  // 在并查集中查找根节点（代表元素）
};

// 构造函数实现
EdgeGraph::EdgeGraph(vector<int> a, int n, int e)
    : vertex(a), vertexNum(n), edgeNum(e) {
    // 从标准输入读取边信息并添加到edge数组中
    for (int i = 0; i < edgeNum; ++i) {
        EdgeType p;
        cin >> p.from >> p.to >> p.weight;
        edge.push_back(p);
    }
}

// 对边的权值进行排序的实现
void EdgeGraph::edgeSort() {
    sort(edge.begin(), edge.end(), [](const EdgeType& a, const EdgeType& b) {
        return a.weight < b.weight;
    });
}

// 在并查集中查找根节点的实现
int EdgeGraph::findRoot(vector<int>& parent, int v) {
    int t = v;
    while (parent[t] != -1) {  // 当parent[t]为-1时，表示t是根节点
        t = parent[t];
    }
    return t;
}

// Kruskal算法的实现
void EdgeGraph::Kruskal() {
    int num = 0, i, vex1, vex2;
    edgeSort();  // 对边进行排序
    // 初始化并查集
    for (int i = 0; i < vertexNum; ++i) {
        parent.push_back(-1);
    }
    // 遍历排序后的边，选择不构成环的最小权重边
    for (num = 0, i = 0; num < vertexNum - 1; ++i) {
        vex1 = findRoot(parent, edge[i].from);  // 查找边的起点的根节点
        vex2 = findRoot(parent, edge[i].to);  // 查找边的终点的根节点
        // 如果边的起点和终点不在同一个集合中，则选择这条边
        if (vex1 != vex2) {
            cout << "(" << edge[i].from << "," << edge[i].to << ")"
                 << " " << edge[i].weight << endl;
            parent[vex2] = vex1;  // 合并两个集合
            num++;                // 选择的边数加一
        }
    }
}

int main() {
    // 创建一个包含6个顶点的图，并告知有9条边需要输入
    vector<int> vertex = {0, 1, 2, 3, 4, 5};
    EdgeGraph graph(vertex, 6, 9);
    graph.Kruskal();  // 执行Kruskal算法
    system("pause");
    return 0;
}
