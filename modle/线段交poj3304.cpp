#include<iostream>
#include<cmath>
#include <cstdio>
using namespace std;
struct pint
{
    double x, y;
}zzy[200];
int eps(double xx)
{
    if (fabs(xx) <= 0.00000001) return 0;
    else if (xx > 0) return 1;
    else return -1;
}
double direction(pint p1, pint p2, pint p3)
{
    return (p3.x - p1.x) * (p2.y - p1.y) - (p2.x - p1.x) * (p3.y - p1.y);
}
bool insect(pint p1, pint p2, pint p3, pint p4) // 判断直线与线段是否相交，p1, p2是直线
{
    double d3 = direction(p1, p2, p3);
    double d4 = direction(p1, p2, p4);
    if (eps(d3) * eps(d4) < 0 || eps(d3) == 0 || eps(d4) == 0) return true;
    return false;
}
bool equals(pint p1, pint p2)
{
    return (sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y * p2.y)) <= 0.00000001);
}
int t, n;
int main()
{
    scanf("%d", &t);
    while(t--)
    {
        scanf("%d", &n);int m = n * 2;
        for(int i = 0; i < m; i++)
            scanf("%lf%lf", &zzy[i].x, &zzy[i].y);
        bool flag = false;
        for(int i = 0; i < m && !flag; i++)
            for(int j = i + 1; j < m && !flag; j++)
                if (!equals(zzy[i], zzy[j]))
                {
                    bool flag2 = true;
                    for(int k = 0; k < n; k++)
                        if (!insect(zzy[i], zzy[j], zzy[2 * k], zzy[2 * k + 1]))
                        {
                            flag2 = false;
                            break;
                        }
                    flag = flag2;
                }
        if (flag) printf("Yes!\n");
        else printf("No!\n");
    }
    return 0;
}
