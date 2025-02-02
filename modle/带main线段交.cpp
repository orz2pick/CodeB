/*
(A1-B1) × (B2-B1) * (B2-B1) × (A2-A1) >= 0
(B1-A1) × (A2-A1) * (A2-A1) × (B2-A1) >= 0
*/

#include<stdio.h>
#define min(a,b) a<b?a:b
#define max(a,b) a>b?a:b
typedef struct {
double x,y;
}Point;
Point A1,A2,B1,B2;
Point  A1B1, B2B1, A2A1, B2A1;
double xx(Point &s,Point &t)
{
    return (s.x*t.y+s.y*t.x);
}
int kua()                           //跨立实验
{
    A1B1.x=A1.x-B1.x;  A1B1.y=A1.y-B1.y;
    B2B1.x=B2.x-B1.x;  B2B1.y=B2.y-B1.y;
    A2A1.x=A2.x-A1.x;  A2A1.y=A2.y-A1.y;
    B2A1.x=B2.x-A1.x;  B2A1.y=B2.y-A1.y;
    if(xx(A1B1,B2B1)*xx(B2B1,A2A1)>=0)
    {
        A1B1.y=-A1B1.y;A1B1.x=-A1B1.x;
        if(xx(A1B1,A2A1)*xx(A2A1,B2A1)>=0)
            return 1;
        else
            return 0;
    }
    else
        return 0;
}
int main()
{
    Point A1,A2,B1,B2;
    int flag=1,i,j,a,b,c,d,e,f;
    while(1)
    {
        scanf("%lf%lf%lf%lf",&A1.x,&A1.y,&A2.x,&A2.y);
        scanf("%lf%lf%lf%lf",&B1.x,&B1.y,&B2.x,&B2.y);
        if( min(A1.x,A2.x) <= max(B1.x,B2.x) &&
            min(B1.x,B2.x) <= max(A1.x,A2.x) &&
            min(A1.y,A2.y) <= max(B1.y,B2.y) &&
            min(B1.y,B2.y) <= max(A1.y,A2.y)   )   //快速排斥实验
        {
            if(kua())
                printf("线段相交\n");
            else
                printf("线段不相交\n");
        }
        else
            printf("线段不相交\n");

    }
    return 0;
}
