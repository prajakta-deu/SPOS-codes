import java.util.*;
class round
{
   public static void main(String args[])
   {
	   int rp=0;
	   Scanner sc=new Scanner(System.in);
	   System.out.println("enter the process :");
	   int n=sc.nextInt();
	    rp=n;
	   int bt[]=new int[n];
	   int run[]=new int[n];
	   int tat[]=new int[n];
	   int wt[]=new int [n];
	   int q=0,time=0;
	   
	   System.out.println("enter burst time");
	   for(int j=0;j<n;j++)
	   {
		   bt[j]=sc.nextInt();
		   run[j]=bt[j];
		   wt[j]=0;
	   }
	    System.out.println("enter quantum time");
		q=sc.nextInt();
		
		int i=0;
		while(rp!=0)
		{
			if(run[i]>q)
			{
				run[i]=run[i]-q;
				time=time+q;
				System.out.println("process"+i+"time"+time);
			}
			else if((run[i]<q)&&(run[i]>0))
			{
				 time=time+run[i];
				 run[i]=run[i]-run[i];
				 tat[i]=time;
				 rp--;
				 System.out.println("process"+i+"time"+time);
			}
			i++;
			if(i==n)
			{
				i=0;
			}
		}
		
		for(int j=0;j<n;j++)
		{
			wt[j]=tat[j]-bt[j];
			System.out.println(tat[j]);
			System.out.println("");
			System.out.println(wt[j]);
			
		}
	   
   }
}