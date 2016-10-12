import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.io.*;
public class ARC{
    public static void main(String[]args){
        Button myButtonGUI=new Button();//声明并创建按钮对象
        myButtonGUI.setVisible(true);
    }
}
class Button extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final int Width=1000;
    public static final int Height=500;
    int num_Frame=21;
    TextArea talarea = new TextArea(25,100);
    BigInteger []N=new  BigInteger[100] ;
    BigInteger []e=new  BigInteger[100];
    BigInteger []c=new  BigInteger[100];
    String []ch =new String[]{"Read","1.检查共模","2.检查共用素因子","3.检查弱素数因子","4.检查|p-q|","5.CRT","6.低解密攻击","7.Rho算法","Clear"};
    Button(){
        Container conPane=getContentPane();
        setSize(Width,Height); 
        setTitle("ARC");
        conPane.setBackground(Color.orange);//背景橘色
        conPane.setLayout(new FlowLayout());//采用FlowLayout布局
        talarea.setEditable(true);
        conPane.add(talarea);
        JButton read=new JButton(ch[0]);
        read.addActionListener(this);//给Read按钮注册监视器
        conPane.add(read);//在窗口添加Read按钮
        JButton c1=new JButton(ch[1]);//c1命名为ch[1] = "1.检查共模"
        c1.addActionListener(this);//给c1按钮注册监视器
        conPane.add(c1);//在窗口添加c1按钮
        JButton c2 = new JButton(ch[2]);
        c2.addActionListener(this);
        conPane.add(c2);
        JButton c3 = new JButton(ch[3]);
        c3.addActionListener(this);
        conPane.add(c3);
        JButton c4 = new JButton(ch[4]);
        c4.addActionListener(this);
        conPane.add(c4);
        JButton c5 = new JButton(ch[5]);
        c5.addActionListener(this);
        conPane.add(c5);
        JButton c6 = new JButton(ch[6]);
        c6.addActionListener(this);
        conPane.add(c6);
        JButton c7 = new JButton(ch[7]);
        c7.addActionListener(this);
        conPane.add(c7);
        JButton c8 = new JButton(ch[8]);
        c8.addActionListener(this);
        conPane.add(c8);
    }
    public void actionPerformed (ActionEvent even){//实现接口处理事件的方法
        Container conPane=getContentPane();
        int ac = 0;//ac 表示第几个按钮
        for(int i=0;i<=8;i++)
        {
            if(even.getActionCommand().equals(ch[i]))
            {
                ac = i;// ac 表示第几个按钮
                break;
            }
        }
        switch(ac)
        {
            case 0:
                num_Frame = read(N,e,c);
                conPane.setBackground(Color.RED);
                talarea.append("RSA模数有"+num_Frame+"个。 \n");
                talarea.append("RSA加密指数有"+num_Frame+"个。 \n");
                talarea.append("RSA密文有"+num_Frame+"个。 \n");
                pln("RSA模数有"+num_Frame+"个。 ");
                pln("RSA加密指数有"+num_Frame+"个。 ");
                pln("RSA密文有"+num_Frame+"个。 \n");
                break;
            case 1:
			    conPane.setBackground(Color.GREEN);
                talarea.append("1.进行共模攻击。。。\n\n检查是否存在共模。。。\n");
                pln("1.进行共模攻击。。。\n\n检查是否存在共模。。。\n");
                check_same_N(N,e,c,num_Frame);
                talarea.append("本次检查找不出参数。\n");
                break;
            case 2:
                conPane.setBackground(Color.YELLOW);
                pln("\n2.检查是否存在模数共用素因子的情况:\n");
                talarea.append("\n2.检查是否存在模数共用素因子的情况:\n");
                travel_gcd(N,e,c,num_Frame);
                break;
            case 3:
                conPane.setBackground(Color.BLACK);
                pln("\n3.检查模数的因子是否有弱素数素因子(Pollard 的p-1算法):");
                talarea.append("\n3.检查模数的因子是否有弱素数素因子(Pollard 的p-1算法):\n");
                String inputValue = JOptionPane.showInputDialog(null,"Please input a upper bound of B-value.","ARC",1);
                int bounded = Integer.parseInt(inputValue);
                p_1(N,e,c,num_Frame,bounded);
                talarea.append("\n");
                break;
            case 4:
                conPane.setBackground(Color.RED);
                pln("\n4.检查N的两个素因子之差是否大于B(即检查|p-q|是否过于小):");
                talarea.append("\n4.检查N的两个素因子之差是否大于B(即检查|p-q|是否过于小):\n");
                int boundd = 1;
                inputValue = JOptionPane.showInputDialog(null,"Please input a upper bound of |p-q|.","ARC",1);
                boundd = Integer.parseInt(inputValue);
                p_q_is_small(N,e,c,num_Frame,boundd);
                pln("\n");
                break;
            case 5:
                conPane.setBackground(Color.WHITE);
            	talarea.append("\n5.进行CRT定理攻击（检查模数两两互素后）:\n");
                System.out.println("\n5.进行CRT定理攻击（检查模数两两互素后）:");
                CRT(N,e,c,num_Frame);
                pln("本次检查找不出参数。\n");
                talarea.append("本次检查找不出参数。\n");
				break;
            case 6:
                conPane.setBackground(Color.PINK);
                System.out.println("\n6.检查解密指数是否过低:");
                talarea.append("\n6.检查解密指数是否过低:\n");
                lowd(N,e,c,num_Frame);
                pln("\n");
                talarea.append("\n");
                break;
            case 7:
                conPane.setBackground(Color.PINK);
                talarea.append("\n7.用Pollar的Rho分解N的算法进行尝试:\n进行Rho算法攻击检查:\n");
                pln("\n7.用Pollar的Rho分解N的算法进行尝试:");
                System.out.println("进行Rho算法攻击检查:");
                Rho(N,e,c,num_Frame);
                pln("\n");
                talarea.append("\n");
                break;
            case 8:
            	talarea.setText("");
            	break;
            default:
                JOptionPane.showConfirmDialog(null, "Yes");
                pln("请输入1～7之间的整数。\n");
                break;
        }
    }
public  void Rho(BigInteger []N,  BigInteger []e,  BigInteger[]c,int num_Frame)
{
    BigInteger one = new BigInteger("1");
    String inputValue = JOptionPane.showInputDialog(null,"输入function的初始值x0：","ARC",1);
    BigInteger x =new BigInteger(inputValue);
    for(int f =0;f<num_Frame;f++)
        {	
        	BigInteger p = null;
            int num = 0,i=10000;
            for(int j=1;j<=8;j++)
            {
            	num =0;i=100;
            	BigInteger x1=fun(j,x,N[f]);
            	p = x1.subtract(x).gcd(N[f]);
            	while(p.compareTo(one)==0)
            	{
            		num++;
            		x = fun(j,x,N[f]);
            		x1 = fun(j,fun(j,x1,N[f]),N[f]);
            		p = x1.subtract(x).gcd(N[f]);
            		if(num==i)
            		{
            			break;//i = i*10;
            		}
            	}
            }
                if(p.compareTo(N[f])==0)
                {
                	talarea.append("Fail at Frame"+f+"\n");
                    System.out.println("Fail at Frame"+f);
                }
                else if(p.compareTo(one)!=0){
                	talarea.append("Find parameter:\n");
                    pln("Find parameter:");
                    talarea.append("p="+p.toString(16)+"\n");
                    System.out.println("p="+p.toString(16));
                    BigInteger q = N[i].divide(p);
			    	BigInteger phn = p.subtract(one).multiply(q.subtract(one));
			    	BigInteger d = ni_yuan(e[i],phn);
			    	talarea.append("q="+q+"\nphn="+phn+"\nd="+d+"\n");
			    	System.out.println("q="+q+"\nphn="+phn+"\nd="+d+"\n");
                }
                else{
                    System.out.println("Fail at Frame"+f);
                    talarea.append("Fail at Frame"+f+"\n");
                }
        }
}
public  BigInteger same_N(BigInteger []e,int i,int j,BigInteger []N,BigInteger []c)
{
    BigInteger zero = new BigInteger("0");
    BigInteger a = e[i],b=e[j];
    BigInteger r0 = a,x0 = BigInteger.ONE,y0 = BigInteger.ZERO;//(a,1,0)
    BigInteger r1 = b,x1 = BigInteger.ZERO,y1 = BigInteger.ONE;//(b,0,1)
    BigInteger m = zero ;
    BigInteger r2 = a.divide(b),x2 = x0.subtract(x1.multiply(r2)),y2=y0.subtract(y1.multiply(r2));
    r2 = r0.subtract(r1.multiply(r2));
    while(r0.subtract(r1.multiply(r0.divide(r1))).compareTo(zero)>0){
            BigInteger q = r0.divide(r1);
            x2 = x0.subtract(x1.multiply(q));
            y2=y0.subtract(y1.multiply(q));
            r2 = r0.subtract(r1.multiply(q));
            r0 = r1;x0=x1;y0=y1;
            r1=r2;y1=y2;x1=x2;
            
    }//now x1*e[0]+y1*e[4] = gcd(e[0],e[4]) = 1;
    if(x1.compareTo(zero)==-1){
        c[i] = ni_yuan(c[i],N[i]);
        x1 = x1.negate();
        }
    if(y1.compareTo(zero)==-1){
        c[j] = ni_yuan(c[j],N[j]);
        y1 = y1.negate();
    }

        m = powr(c[i],x1,N[i]).multiply(powr(c[j],y1,N[j])).mod(N[i]);
        return m;//System.out.println(m.toString(16));
}
public  void check_same_N(BigInteger []N,  BigInteger []e,  BigInteger[]c,int num_Frame)
{
	int number = num_Frame*(num_Frame+1)/2,now_num =0;
    int bi_li =0,current =0,gong_mo = 0;
    for(int i=0;i<num_Frame-1;i++)
    {
        for(int j=i+1;j<num_Frame;j++)
        {
            	now_num++;
                bi_li = 100 * now_num/number;
                if(bi_li==(current+1))
                {
                    current++;
                    talarea.append("*");
                    System.out.print("*");
                }
                if(N[i].compareTo(N[j])==0)
                {
                	gong_mo++;
                }
        }
    }
    talarea.append("\nThere are "+gong_mo+" paris of N have the same value.\n");
    System.out.println("\nThere are "+gong_mo+" paris of N have the same value.");
    gong_mo = 0;
    for(int i=0;i<num_Frame-1;i++)
    {
        for(int j=i+1;j<num_Frame;j++)
        {
            if(N[i].compareTo(N[j])==0)
            {
            	talarea.append("The Frame"+i+" and Frame"+j+" have the same N,if we  assume they have same original message.\n");
                talarea.append("Then the original message is:\n"+ same_N(e,i,j,N,c).toString(16)+"\n");
            	System.out.println("The Frame"+i+" and Frame"+j+" have the same N,if we  assume they have same original message.");
                System.out.println("Then the original message is:\n"+ same_N(e,i,j,N,c).toString(16));
            }
        }
    }
}
public  void travel_gcd(BigInteger []N,  BigInteger []e,  BigInteger[]c,int num)
{
    BigInteger one = new BigInteger("1");
	for(int i =0;i<num-1;i++)
		for(int j=i+1;j<num;j++)
		{
			if(N[i].gcd(N[j]).compareTo(BigInteger.ONE)>0&&N[i].compareTo(N[j])!=0)
			{
				talarea.append("发现Frame"+i+"的模数与Frame"+j+"的模数共用相同的素因子\n");
				talarea.append("Frame"+i+":\np="+N[i].gcd(N[j]).toString(16)+"\n");
				talarea.append("q= "+N[i].divide(N[i].gcd(N[j])).toString(16)+"\n");
				talarea.append("phn= "+N[i].gcd(N[j]).subtract(one).multiply(N[i].divide(N[i].gcd(N[j])).subtract(one)).toString(16)+"\n");
				talarea.append("d= "+ni_yuan(e[i],N[i].gcd(N[j]).subtract(one).multiply(N[i].divide(N[i].gcd(N[j])).subtract(one))).toString(16)+"\n");
				talarea.append("\nFrame"+j+":\np="+N[j].gcd(N[i]).toString(16)+"\n");
				talarea.append("q= "+N[j].divide(N[i].gcd(N[j])).toString(16)+"\n");
				talarea.append("phn= "+N[i].gcd(N[j]).subtract(one).multiply(N[j].divide(N[i].gcd(N[j])).subtract(one)).toString(16)+"\n");
				talarea.append("d= "+ni_yuan(e[j],N[i].gcd(N[j]).subtract(one).multiply(N[j].divide(N[i].gcd(N[j])).subtract(one))).toString(16)+"\n");
				System.out.println("发现Frame"+i+"的模数与Frame"+j+"的模数共用相同的素因子");
				System.out.println("Frame"+i+":\np="+N[i].gcd(N[j]).toString(16));
				System.out.println("q= "+N[i].divide(N[i].gcd(N[j])).toString(16));
		        System.out.println("phn= "+N[i].gcd(N[j]).subtract(one).multiply(N[i].divide(N[i].gcd(N[j])).subtract(one)).toString(16));
				System.out.println("d= "+ni_yuan(e[i],N[i].gcd(N[j]).subtract(one).multiply(N[i].divide(N[i].gcd(N[j])).subtract(one))).toString(16));
				System.out.println("\nFrame"+j+":\np="+N[j].gcd(N[i]).toString(16));
				System.out.println("q= "+N[j].divide(N[i].gcd(N[j])).toString(16));
		        System.out.println("phn= "+N[i].gcd(N[j]).subtract(one).multiply(N[j].divide(N[i].gcd(N[j])).subtract(one)).toString(16));
				System.out.println("d= "+ni_yuan(e[j],N[i].gcd(N[j]).subtract(one).multiply(N[j].divide(N[i].gcd(N[j])).subtract(one))).toString(16));
			}
		}
}
public  void p_1(BigInteger []N,  BigInteger []e,  BigInteger[]c,int num,int bounded)
{
	BigInteger one = BigInteger.ONE,two=new BigInteger("2"),a=two;int i=2;
	Boolean flag = false;
	for(int j=0;j<num;j++)
	{
		a = two;i=2;
	    flag = false;
		for(;i<=5;i++)
		{
		    flag = false;
			if(a.gcd(N[j]).compareTo(one)>0)
			{
				talarea.append("第"+j+"个模数的质因子p不是强素数，p="+a.gcd(N[j]).toString(16)+"\n");
				System.out.println("第"+j+"个模数的质因子p不是强素数，p="+a.gcd(N[j]).toString(16));
				flag = true;
				break;
			}
			BigInteger s = a.subtract(one);
			for(int B=2;B<=bounded;B++)
			{
				s = powr_int(s.add(one),B,N[j]).subtract(one).mod(N[j]);
				BigInteger d = s.gcd(N[j]);
				if(d.compareTo(one)==0)
					continue;
				else if(d.compareTo(N[j])==0)
					break;
				else
				{
					System.out.println("第"+j+"个模数的质因子p不是强素数，p="+d.toString(16));
					System.out.println("q= "+N[j].divide(d).toString(16));
		            System.out.println("phn= "+d.subtract(one).multiply(N[j].divide(d).subtract(one)).toString(16));
				    System.out.println("d= "+ni_yuan(e[j],d.subtract(one).multiply(N[j].divide(d).subtract(one))).toString(16));
				    talarea.append("第"+j+"个模数的质因子p不是强素数，p="+d.toString(16)+"\n");
				    talarea.append("q= "+N[j].divide(d).toString(16)+"\n");
				    talarea.append("phn= "+d.subtract(one).multiply(N[j].divide(d).subtract(one)).toString(16)+"\n");
				    talarea.append("d= "+ni_yuan(e[j],d.subtract(one).multiply(N[j].divide(d).subtract(one))).toString(16)+"\n");
					flag = true;i++;
					break;
				}
			}
			if(flag)
			{
				break;
		    }
	    }
		if(!flag){
			    talarea.append(j+" 的p-1攻击失败。\n");
			    System.out.println(j+" 的p-1攻击失败。");
		   }
	}
}
public  void p_q_is_small(BigInteger []N,BigInteger []e,BigInteger []c,int num,int bound )
{
	BigInteger u,v,one = new BigInteger("1"),two =new BigInteger("1000");
	Boolean allsave= true;
	for(int i=0;i<num;i++)
	{
		u = pow_1(N[i],two).add(one);
		Boolean part_N =true;
		while(bound>=0)
		{
		    bound--;
			v = u.pow(2).subtract(N[i]);
			if(pow_1(v,two).pow(2).compareTo(v)==0)
			{
				allsave = false;
				part_N = false;
				talarea.append("Frame"+i+" is not safe.\n");
				System.out.println("Frame"+i+" is not safe.");
				BigInteger p,q,phn,d;
				p = pow_1(v,two).add(u).divide(two);
				q = N[i].divide(p);
				phn = p.subtract(one).multiply(q.subtract(one));
				d = ni_yuan(e[i],phn);
				System.out.println("p="+p+"\nq="+q+"\nphn="+phn+"\nd="+d+"\n");
				talarea.append("p="+p+"\nq="+q+"\nphn="+phn+"\nd="+d+"\n");
                break;
			}
			u=u.add(one);
		}
		if(part_N)
		{
			System.out.println("Frame"+i+" is safe.");
			talarea.append("Frame"+i+" is safe.\n");
		}
	}
	if(allsave)
	{
		System.out.println("All ps and qs are safe.");
		talarea.append("All ps and qs are safe.\n");
	}
}
public  void CRT(BigInteger []N,  BigInteger []e,  BigInteger[]c,int num)
{
	BigInteger ten = new BigInteger("10");
	for(int i= 0;i<num-1;i++)
		for(int j=i+1;j<num;j++)
			if( e[i].compareTo(e[j])==0 && e[i].compareTo(ten)<=0)
			{
				talarea.append("Frame"+i+" and Frame"+j+" have same small e="+e[i]+"\n");
				talarea.append("现在我们假设他们原文相同，应用CRT获取原文是：\n");
				System.out.println("Frame"+i+" and Frame"+j+" have same small e="+e[i]);
				System.out.println("现在我们假设他们原文相同，应用CRT获取原文是：");
				BigInteger M = N[i].multiply(N[j]);
				BigInteger ans = c[i].multiply(N[j]).multiply(ni_yuan(N[j],N[i])).add(c[j].multiply(N[i]).multiply(ni_yuan(N[i],N[j]))).mod(M);
				talarea.append(ans.toString(16)+"\n");
				talarea.append("If this message do make sense,the assume(原文相同) is right.\n");
				System.out.println(ans.toString(16));
				System.out.println("If this message do make sense,the assume(原文相同) is right.\n");
			}
}
public  void lowd(BigInteger []n,  BigInteger []e,  BigInteger[]c,int number)
{
    BigInteger four = new BigInteger("4"),two = new BigInteger("2"),one = new BigInteger("1"),zero = new BigInteger("0");
            for(int i=0;i<number;i++)
            {
                Boolean find_d = false;
                talarea.append("Frame"+i+":\n");
                System.out.println("Frame"+i+":");
                BigInteger d ,k,phn,delta,p,q;
                BigInteger r0=n[i];
                BigInteger r1 = e[i],         q1 = zero,         P1 = q1,                      Q1 = one;
                BigInteger r2 = r0.mod(r1),q2 = r0.divide(r1),P2 = q1.multiply(q2).add(one),Q2 = q2;
                BigInteger r3 = r1.mod(r2),q3 = r1.divide(r2),P3 = P2.multiply(q3).add(P1), Q3 = Q2.multiply(q3).add(Q1);
                while(r3.compareTo(zero)!=0)
                {
                 r3 = r1.mod(r2);q3 = r1.divide(r2);P3 = P2.multiply(q3).add(P1); Q3 = Q2.multiply(q3).add(Q1);
                 if(P3.gcd(Q3).compareTo(one)==0)
                 {
                      k = P3;d = Q3;
                      phn = e[i].multiply(d).subtract(one);
                      phn = phn.divide(k);
                      delta = n[i].subtract(phn).add(one);
                      delta = delta.pow(2);
                      delta = delta.subtract(n[i].multiply(four));
                      delta = pow_1(delta,two);
                      p = n[i].subtract(phn).add(one).add(delta);
                      p = p.divide(two);
                      q = n[i].subtract(phn).add(one).subtract(delta);
                      q = q.divide(two);
                      if(p.multiply(q).compareTo(n[i])==0)
                      {
                          find_d = true;
                          BigInteger m = powr(c[i],d,n[i]);
                          talarea.append("Frame"+i+"的m: \n");
                          talarea.append(m.toString(16)+"\n");
                          talarea.append("p="+p+"\nq="+q+"\nphn="+phn+"\nd="+d+"\n\n");
                          System.out.println("Frame"+i+"的m: ");
                          System.out.println(m.toString(16));
                          System.out.println("p="+p+"\nq="+q+"\nphn="+phn+"\nd="+d+"\n");
                      }
                 }
                 r1 = r2;   q1 = q2;    P1 = P2;    Q1 = Q2;
                 r2 = r3;   q2 = q3;    P2 = P3;    Q2 = Q3;
                }
                if(!find_d){
                    pln("d isn't low.");
                    talarea.append("d isn't low.\n");
                }
          }
}
public  void p(BigInteger []N,  BigInteger []e,  BigInteger[]c,int num)
{
	for(int i=0;i<num;i++)
	{
		System.out.println(i);
		System.out.println(N[i].toString(16)+'\n'+e[i].toString(16));
		System.out.println(c[i].toString(16)+"\n\n");
	}
}
public  BigInteger fun(int i,BigInteger x,BigInteger n)
{
   BigInteger one = BigInteger.ONE;
   if(i==1)
	   return x.pow(2).add(one).mod(n);
   else 
	   return x.multiply(fun(i-1,x,n)).mod(n);
}
public  BigInteger pow_1(BigInteger a,BigInteger e){
    BigInteger zero = BigInteger.ZERO,l = zero,r = a.shiftRight(100),mid=zero ,one = BigInteger.ONE;
    while(r.compareTo(l.add(one)) >0)
    {
    	mid = r.add(l);
        mid = mid.shiftRight(1);
        if(powc(mid,e,a))
            r = mid;
        else
            l = mid;
    }
    if( !powc(mid,e,a))
        return r;
    else return l;
}
public  void pln(String a)
{
	System.out.println(a);
}
public  BigInteger ni_yuan(BigInteger a,BigInteger b){
    BigInteger zero = BigInteger.ZERO;
    BigInteger r0 = a,x0 = BigInteger.ONE,y0 = BigInteger.ZERO;//(a,1,0)
    BigInteger r1 = b,x1 = BigInteger.ZERO,y1 = BigInteger.ONE;//(b,0,1)
    if(b.compareTo(zero)==0){
        return a;    
    }
    else{
    BigInteger r2 = a.divide(b),x2 = x0.subtract(x1.multiply(r2)),y2=y0.subtract(y1.multiply(r2));//()
    r2 = r0.subtract(r1.multiply(r2));
    while(r0.subtract(r1.multiply(r0.divide(r1))).compareTo(zero)>0){
        BigInteger q = r0.divide(r1);
        x2 = x0.subtract(x1.multiply(q));
        y2=y0.subtract(y1.multiply(q));
        r2 = r0.subtract(r1.multiply(q));
        r0 = r1;x0=x1;y0=y1;
        r1=r2;y1=y2;x1=x2;
    }
    x1 = x1.mod(b);
    return x1;
    }
}
public  BigInteger powr(BigInteger a,BigInteger x,BigInteger n)// cal the a^x mod(n)
{
        BigInteger res = new BigInteger("1"),zero = new BigInteger("0"),one = new BigInteger("1"),two =new BigInteger("2");
        while(x.compareTo(zero)>0)//        while(x>0)
        {
            if(x.mod(two).compareTo(one)==0)
                res = res.multiply(a).mod(n);      // res =res *a
            a = a.pow(2).mod(n);
            x = x.shiftRight(1);
        }
        return res;
    }
public  BigInteger powr_int(BigInteger a,int x,BigInteger n)// cal the a^x mod(n)
{
    BigInteger res = new BigInteger("1");
    while(x>0)//        while(x>0)
    {
        if(x%2==1)
            res = res.multiply(a).mod(n);      // res =res *a
        a = a.pow(2).mod(n);
        x =x/2;
    }
    return res;
}
public  Boolean powc(BigInteger a,BigInteger e,BigInteger b){
    BigInteger one = BigInteger.ONE,zero = BigInteger.ZERO,two= new BigInteger("2");
    BigInteger op = new BigInteger("10001",16);
    if(e.compareTo(op)!=0)
    {
           BigInteger res = one;
           while(e.compareTo(zero)>0){
               if(e.mod(two).compareTo(one)==0)
                   res = res.multiply(a);
               a = a.pow(2);
               e = e.divide(two);    //e>>=1;
           }
           if(res.compareTo(b)==1)
           return true;
           else 
           return false;
    }
    else
    {
        BigInteger res = a;
        for(int j = 1;j<=16;j++)
        {
            a = a.pow(2);
            if(a.compareTo(b)==1)
                return true;
         }
        if(res.multiply(a).compareTo(b)==1)
          return true;
    }
    return false;
}
public  int read(  BigInteger []N,  BigInteger []e,  BigInteger[]c){
    String fname = "sub.txt";
    int num = 0;
    File f = new File(fname);
    BufferedReader r = null;
    try{
              r = new BufferedReader(new FileReader(f));
              String t = null;
              int line = 1;
              int frame =0;
              while((t = r.readLine())!=null){
                if(line==4){
                    line-=4;
                    frame++;
                }
                else if(line==1)
                {
                    N[frame] = new  BigInteger(t,16);
                    num++;
                }
                else if(line == 2)
                    e[frame] = new  BigInteger(t,16);
                else if(line == 3)
                    c[frame] = new  BigInteger(t,16);
                line++;
        }
        }catch (IOException ee){
                ee.printStackTrace();
            }
    return num;
}
}