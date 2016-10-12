#include <iostream>
#include <algorithm>
#include <stack>
#include <cstdio>
#define maxn 100001
using namespace std;
struct point{
    int x,y,num;
    bool ji_xing;
}p[maxn],q[maxn];
int n;   point top1,top2;   //
stack <point> A,B;
char ToLeft(point A,point B,point C)//find location of C relative to vector AB
{
    point a ,b;
    a.x = B.x-A.x;  a.y=B.y-A.y;
    b.x = C.x-A.x;  b.y=C.y-A.y;
    int t=a.x*b.y-a.y*b.x;
    if(t==0)  return 'm';
    else if(t>0)  return 'l';
    else  return 'r';
}
int d(point a,point b){
    return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
}
bool cmp(point a,point b){
    if(ToLeft(p[0],a,b)=='l')
        return true;
    else if(ToLeft(p[0],a,b)=='m')
    {
        if (d(p[0],b)>d(p[0],a)) {
            return true;
        }
        else
            return false;
    }
    else
        return false;
}
void swapp(point *a,point *b){
    point c;
    c=*a;
    *a=*b;
    *b=c;
}
void init(){
    cin>>n;
    cin>>p[0].x>>p[0].y;
    p[0].num=1;p[0].ji_xing=false;
    for(int i=2;i<=n;i++){
        cin>>p[i-1].x>>p[i-1].y;
        p[i-1].num=i;
        p[i-1].ji_xing=false;
        if(p[i-1].y<p[0].y)
            swapp(&p[i-1],&p[0]);
    }
    sort(p+1,p+n,cmp);
}
void pp(point a){
    printf("(%d,%d)\n",a.x,a.y);
}
int main()
{
//    freopen("in.txt","r",stdin);
    init();
    // pre sort
    for(int i=0;i<n;i++)
        //printf("%d\t%d\n",p[i].x,p[i].y);
        
        if (n<=2) {
            cout<<n<<endl;
            return 0;
        }
    p[0].ji_xing=p[1].ji_xing=true;
    A.push(p[0]);//pp(A.top());
    A.push(p[1]);//pp(A.top());
    // for(int i=0;i<n;i++)
    // {
    //   pp(p[i]);
    // }
    // cout<<endl;
//    cout<<ToLeft(p[0],p[1],p[2])<<endl;
    for(int i=2;i<n;i++)
    {
        point top1 = A.top();
        A.pop();
        point top2 = A.top();
        A.push(top1);
        if(ToLeft(top2,top1,p[i])=='l'){
            p[i].ji_xing=true;
            A.push(p[i]);
        }
        
        if(ToLeft(top2,top1,p[i])=='m')
        {
            p[i].ji_xing=true;
            if(B.empty()||p[i].x!=B.top().x||p[i].y!=B.top().y)
                B.push(p[i]);
            
            
            A.push(p[i]);
//            cout<<"p["<<i<<"].jixing= "<<p[i].ji_xing<<" ";pp(p[i]);
        }
        else if(ToLeft(top2,top1,p[i])=='r')
        {
            top1.ji_xing=false;
            if(B.empty()||top1.x!=B.top().x||top1.y!=B.top().y)
                B.push(top1);
            A.top().ji_xing=false;
            A.pop();
            
            i--;
        }
    }
    int ans = 1;
    int cnt = 0;
    while(!A.empty())
    {
        ans=ans*A.top().num%(n+1);
//        cout<<A.top().ji_xing<<" ";pp(A.top());
        q[cnt++]=A.top();
        A.pop();
    }
//    while (!B.empty()) {
//        cout<<B.top().num<<" B: ";pp(B.top());
//        B.pop();
//    }
    while (!B.empty()) {
        point Btop = B.top();
//        cout<<B.top().num<<" B: ";pp(B.top());
        B.pop();
        for(int i=0;i<cnt;i++)
        {
            if(ToLeft(q[i], q[(i+1)%cnt], Btop)=='m'&&(!Btop.ji_xing))
            {
//                cout<<"Btop's jixing = "<<Btop.ji_xing<<endl;
                A.push(Btop);
                break;
            }
        }
    }
    while (!A.empty()) {
        ans = ans*A.top().num % (n+1);
//        cout<<A.top().num<<" ";pp(A.top());
        A.pop();
    }
    cout<<ans<<endl;
    return 0;
}
