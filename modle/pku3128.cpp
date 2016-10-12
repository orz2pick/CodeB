/*  题目意思是：一个置换是否可以由另一个置换的平方得来的。一个置换的平方，原来偶数长的循环会被分裂成两段长度相等的循环，而奇数长的循环不会被分裂。题目只是问是否存在，所以只要看所给置换中偶数长的循环是否成对，否则就不能由一个置换的平方得来。

   补充：因为如果所给置换的循环是偶数，则肯定是由分裂过来的，那么一定是成对的，否则如果是奇数，那么有可能是原来是奇数，也有可能是原来的偶数分裂成两个奇数循环。
   Description

— I just bought Leonardo's secret notebook! Rare object collector Stan Ucker was really agitated but his friend, special investigator Sarah Kepticwas unimpressed.
— How do you know it is genuine?
— Oh, it must be, at that price. And it is written in the da Vinci code. Sarah browsed a few of the pages. It was obvious to her that the code was a substitution cipher, where each letter of the alphabet had been substituted by another letter.
— Leonardo would have written the plain-text and left it to his assistant to encrypt, she said. And he must have supplied the substitution alphabet to be used. If we are lucky, we can find it on the back cover! She turned up the last page and, lo and behold, there was a single line of all 26 letters of the alphabet:
QWERTYUIOPASDFGHJKLZXCVBNM
— This may be Leonardo's instructions meaning that each A in the plain-text was to be replaced by Q, each B withW, etcetera. Let us see... To their disappointment, they soon saw that this could not be the substitution that was used in the book. Suddenly, Stan brightened.
— Maybe Leonardo really wrote the substitution alphabet on the last page, and by mistake his assistant coded that line as he had coded the rest of the book. So the line we have here is the result of applying some permutation TWICE to the ordinary alphabet! Sarah took out her laptop computer and coded fiercely for a few minutes. Then she turned to Stan with a sympathetic expression.
— No, that couldn't be it. I am afraid that you have been duped again, my friend. In all probability, the book is a fake.

Write a program that takes a permutation of the English alphabet as input and decides if it may be the result of performing some permutation twice.
Input

The input begins with a positive number on a line of its own telling the number of test cases (at most 500). Then for each test case there is one line containing a permutation of the 26 capital letters of the English alphabet.
Output

For each test case, output one line containing Yes if the given permutation can result from applying some permutation twice on the original alphabet string ABC...XYZ, otherwise output No.
Sample Input

2
QWERTYUIOPASDFGHJKLZXCVBNM
ABCDEFGHIJKLMNOPQRSTUVWXYZ
Sample Output

No
Yes
*/
#include<stdio.h>
#include<string.h>
char s[27];
int a[27],f[27],c[27];
int ok()
{
    int i;
    for(i=0;i<26;i++)
        if(f[i])
        {
            int cnt=1;
            int b=a[i];
            f[i]=0;
            while(b!=i)
            {
                f[b]=0;
                b=a[b];
                cnt++;
            }
            c[cnt]++;
        }
    for(i=2;i<27;i+=2)
        if(c[i]%2)
            return 0;
    return 1;
}
int main()
{
    int i,t;
    scanf("%d",&t);
    while(t--)
    {
        scanf("%s",s);
        for(i=0;i<26;i++)
        {
            a[i]=s[i]-'A';
            f[i]=1;  c[i]=0;
        }
        puts(ok()?"Yes":"No");
    }
    return 0;
}
