/*CARDS
Time Limit: 1000MS		Memory Limit: 10000K
Total Submissions: 1865		Accepted: 959
Description

Alice and Bob have a set of N cards labelled with numbers 1 ... N (so that no two cards have the same label) and a shuffle machine. We assume that N is an odd integer.
The shuffle machine accepts the set of cards arranged in an arbitrary order and performs the following operation of double shuffle : for all positions i, 1 <= i <= N, if the card at the position i is j and the card at the position j is k, then after the completion of the operation of double shuffle, position i will hold the card k.

Alice and Bob play a game. Alice first writes down all the numbers from 1 to N in some random order: a1, a2, ..., aN. Then she arranges the cards so that the position ai holds the card numbered ai+1, for every 1 <= i <= N-1, while the position aN holds the card numbered a1.

This way, cards are put in some order x1, x2, ..., xN, where xi is the card at the ith position.

Now she sequentially performs S double shuffles using the shuffle machine described above. After that, the cards are arranged in some final order p1, p2, ..., pN which Alice reveals to Bob, together with the number S. Bob's task is to guess the order x1, x2, ..., xN in which Alice originally put the cards just before giving them to the shuffle machine.
Input

The first line of the input contains two integers separated by a single blank character : the odd integer N, 1 <= N <= 1000, the number of cards, and the integer S, 1 <= S <= 1000, the number of double shuffle operations.
The following N lines describe the final order of cards after all the double shuffles have been performed such that for each i, 1 <= i <= N, the (i+1)st line of the input file contains pi (the card at the position i after all double shuffles).
Output

The output should contain N lines which describe the order of cards just before they were given to the shuffle machine.
For each i, 1 <= i <= N, the ith line of the output file should contain xi (the card at the position i before the double shuffles).
Sample Input

7 4
6
3
1
2
4
7
5
Sample Output

4
7
5
6
1
2
3

*/
#include<iostream>
using namespace std;
const int MAX=1001;
int a[MAX],b[MAX];
int main()
{
    int i,j,k,n,s;
    while(scanf("%d%d",&n,&s)!=EOF)
    {
        for(i=1;i<=n;i++)
            scanf("%d",&a[i]);
        //求目标循环结果存放在数组b[]中；
        b[1]=1;
        i=j=1;
        while(a[j]!=1)
        {
            j=a[j];
            b[++i]=j;
        }
        k=1;
        //求位移的步数
        for(i=1;i<=s;i++)
            k=(k*2)%n;
        //求开k次方运算,开方运算后的原始循环存放在数组a[]中；
        a[1]=b[1];
        j=1;
        for(i=2;i<=n;i++)
        {
            j+=k;
            if(j>n) j-=n;
            a[j]=b[i];
        }
        //原始循环转化为原始置换
        for(i=1;i<n;i++)
            b[a[i]]=a[i+1];
        b[a[n]]=a[1];
        for(i=1;i<=n;i++)
            printf("%d\n",b[i]);
    }
    return 0;
}
