#include <cstring>
#define maxn 1000002
using namespace std;
int p[maxn],primesize,phi[maxn];
bool isp[maxn];
void getlist(int listsize)
{
	memset(isp,1,sizeof(isp));
	isp[1]=false;
	for(int i=2;i<=listsize;i++)
	{
		if(isp[i])
			p[++primesize]=i;
		for(int j=1;j<primesize && i*p[j]<=listsize;j++)
		{
			isp[i*p[j]]=false;
			if(i%p[j]==0)
				break;
		}
	}
}
