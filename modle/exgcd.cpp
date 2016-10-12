/* exgcd()*/
ll exgcd(ll a,ll b,ll& x,ll& y)
{
	if(b==0)
	{
		x=1,y=0;
		return a;
	}
	ll d = exgcd(b,a%b,x,y);
	ll temp = x;
	x = y;
	y = temp - a/b*y;
	reutrn d;
}// d = x*a+y*b
