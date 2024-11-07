import java.util.*;
class fcfs
{
   public static void findwat(int p[],int n,int bt[],int wt[])
   {
        wt[0]=0;
        for(int i=1;i<n;i++)
		{
		    wt[i]=bt[i-1]+wt[i-1];
		}
   }
   
   public static void findtat(int p[],int n,int bt[],int wt[],int tat[])
   {
       for(int i=0;i<n;i++)
	   {
	       tat[i]=bt[i]+wt[i];
	   }
   }
   
   public static void avgtime(int p[],int n,int bt[])
   {
       int wt[]=new int[n];
	   int tat[]=new int[n];
	   int totw=0,tottat=0;
	   
	   findwat(p,n,bt,wt);
	   findtat(p,n,bt,wt,tat);
	   
	   System.out.println(" process "+ " burst " +" waiting " + " turnaround ");
	   for(int i=0;i<n;i++)
	   {
	       totw+=wt[i];
		   tottat+=tat[i];
		   System.out.println(""+(i+1)+"\t\t"+bt[i]+"\t\t"+wt[i]+"\t\t"+tat[i]);
	   }
	   
	   System.out.println("average waiting time :"+(float)totw/n);
	   System.out.println("average turnaround time :"+(float)tottat/n);
	   
   }
   
   public static void main(String args[])
   {
       int p[]={1,2,3};
	   int n=p.length;
	   int bt[]={10,5,8};
	   avgtime(p,n,bt);
   }

}