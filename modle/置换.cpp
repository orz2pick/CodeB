#include <iostream>
#include <algorithm>
#include <limits>
using namespace std;
typedef long long ll;
const int maxn = 100002;
ll a[maxn],b[maxn],hash[maxn];
int n;
bool visit[maxn];
int main()
{
  cin>>n;
  // memset(visit,false,sizeof(visit));
  for (size_t i = 0; i < maxn; i++) {
    visit[i]=false;
  }
  for(int i=0;i<n;i++){
    cin>>a[i];b[i]=a[i];

  }

  for(int i=0;i<n;i++)
      hash[b[i]]=i;
  sort(b,b+n);
  ll ans = 0;
  for(int i=0;i<n;i++)
  {
    int id = i,len=0,min=maxn+1;
    ll sum=0;
    int start = a[id];
    if(!visit[i])
    {
      while(true)
      {
        visit[id]=true;
        len++;
        min = min<a[id]?min:a[id];
        sum+=a[id];
        id=hash[b[id]];
        if(start == a[id])
        break;
      }
      ll max1 = sum-min+(len-1)*min;
      ll max2 = sum+min+(len+1)*b[0];
      ans = ans+(max1<max2?max1:max2);
    }
  }
  cout<<ans<<endl;

  return 0;
}
