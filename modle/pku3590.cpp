/*  /把n分解成若干个数，使得他们的lcm最大。
在所取的数都是素数幂的时候是最大的，
所以可以用递归来枚举所有的分解情况，而且由于要输出序最小的，所以对于剩下的数可以直接单独都作为一个循环，这样就可以使得序最小了。
此外，这道题目需要注意求最大的lcm的时候不能用dp来做，因为这个具有后效性，局部最优不一定使得全局最优
Description

Any case of shuffling of n cards can be described with a permutation of 1 to n. Thus there are totally n! cases of shuffling. Now suppose there are 5 cards, and a case of shuffle is <5, 3, 2, 1, 4>, then the shuffle will be:

Before shuffling：1, 2, 3, 4, 5
The 1st shuffle：5, 3, 2, 1, 4
The 2nd shuffle：4, 2, 3, 5, 1
The 3rd shuffle：1, 3, 2, 4, 5
The 4th shuffle：5, 2, 3, 1, 4
The 5th shuffle：4, 3, 2, 5, 1
The 6th shuffle：1, 2, 3, 4, 5(the same as it is in the beginning)

You'll find that after six shuffles, the cards' order returns the beginning. In fact, there is always a number m for any case of shuffling that the cards' order returns the beginning after m shuffles. Now your task is to find the shuffle with the largest m. If there is not only one, sort out the one with the smallest order.

Input

The first line of the input is an integer T which indicates the number of test cases. Each test case occupies a line, contains an integer n (1 ≤ n ≤ 100).

Output

Each test case takes a line, with an integer m in the head, following the case of shuffling.
　

Sample Input

2
1
5
Sample Output

1 1
6 2 1 4 5 3
*/代码

#include<iostream>
#include<algorithm>
using namespace std;
const int N=105;
bool hash[N];
int p[N],lp;
void prim()
{
    memset(hash,true,sizeof(hash));
    lp=0;
    for(int i=2;i<N;i++)
    {
        if(hash[i])
        {
            p[lp++]=i;
            for(int j=i*i;j<N;j+=i)
                hash[j]=false;
        }
    }
}
int step[N],maxm,cycle[N],lc;
void dfs(int remain,int k)
{
    if(remain<p[k])
    {
        int m=1;
        for(int i=0;i<k;i++)
            if(step[i])
                m*=step[i];
        if(m>maxm)
        {
            maxm=m;
            lc=0;
            for(int i=0;i<k;i++)
                if(step[i])
                    cycle[lc++]=step[i];
            while(remain--)
                cycle[lc++]=1;
        }
    }else{
        step[k]=0;
        dfs(remain,k+1);
        for(step[k]=p[k];step[k]<=remain;step[k]*=p[k])
            dfs(remain-step[k],k+1);
    }
}
int main()
{
    int t,n;
    prim();
    scanf("%d",&t);
    while(t--){
        scanf("%d",&n);
        if(n==1)
        {
            printf("1 1\n");
            continue;
        }
        maxm=1; lc=0;
        dfs(n,0);
        sort(cycle,cycle+lc);
        printf("%d",maxm);
        int k=1,tmp;
        for(int i=0;i<lc;i++)
        {
            tmp=k++;
            for(int j=1;j<cycle[i];j++)
                printf(" %d",k++);
            printf(" %d",tmp);
        }
        puts("");
    }
    return 0;
}
