/*Description

Bob and Alice started to use a brand-new encoding scheme. Surprisingly it is not a Public Key Cryptosystem, but their encoding and decoding is based on secret keys. They chose the secret key at their last meeting in Philadelphia on February 16th, 1996. They chose as a secret key a sequence of n distinct integers, a1 ; . . .; an, greater than zero and less or equal to n. The encoding is based on the following principle. The message is written down below the key, so that characters in the message and numbers in the key are correspondingly aligned. Character in the message at the position i is written in the encoded message at the position ai, where ai is the corresponding number in the key. And then the encoded message is encoded in the same way. This process is repeated k times. After kth encoding they exchange their message.

The length of the message is always less or equal than n. If the message is shorter than n, then spaces are added to the end of the message to get the message with the length n.

Help Alice and Bob and write program which reads the key and then a sequence of pairs consisting of k and message to be encoded k times and produces a list of encoded messages.
Input

The input file consists of several blocks. Each block has a number 0 < n <= 200 in the first line. The next line contains a sequence of n numbers pairwise distinct and each greater than zero and less or equal than n. Next lines contain integer number k and one message of ascii characters separated by one space. The lines are ended with eol, this eol does not belong to the message. The block ends with the separate line with the number 0. After the last block there is in separate line the number 0.
Output

Output is divided into blocks corresponding to the input blocks. Each block contains the encoded input messages in the same order as in input file. Each encoded message in the output file has the lenght n. After each block there is one empty line.
Sample Input
10
4 5 3 7 2 8 1 6 10 9
1 Hello Bob
1995 CERC
0
0
Sample Output
BolHeol  b
C RCE
*/
#include<stdio.h>
#include<string.h>
int main()
{
    int i,n,j,k,t,len;
    int pos[210],c[210];//初始数组。c是周期数组。
    char s[210],ans[210];//a接受字符、res是结果。
    while(scanf("%d",&n),n)
    {
        for(i=1;i<=n;i++)
            scanf("%d",&pos[i]);
        memset(c,0,sizeof(c));
        for(i=1;i<=n;i++)
        {
            j=i;
            while(pos[j]!=i)//寻找周期。
                j=pos[j], c[i]++;
            c[i]++;
        }
        while(scanf("%d",&k),k)
        {
            getchar();
            gets(s+1);
            len=strlen(s+1);
            while(len<n)  //填充字符串
                s[++len]=' ';
            for(i=1;i<=n;i++)//模拟。
            {
                t=k%c[i];//由周期性，可以得到较少的模拟次数。
                j=i;
                while(t--)
                    j=pos[j];
                ans[j]=s[i];
            }
            ans[n+1]=0;
            puts(ans+1);
        }
        puts("");
    }
    return 0;
}
