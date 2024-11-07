import java.util.*;
class priority
{
  public static void main(String args[])
  {
      Scanner sc=new Scanner(System.in);
	  System.out.println("enter process :");
	  int n=sc.nextInt();
	  int p[]=new int[n];
	  int bt[]=new int[n];
	  int pt[]=new int[n];
	  int tat[]=new int[n];
	  int wt[]=new int[n];
	  int temp,pos=0;
	  
	  System.out.println("Enter burst time :");
	  for(int i=0;i<n;i++)
	  {
	     p[i]=i+1;
		 bt[i]=sc.nextInt();
	  }
	  
	  System.out.println("Enter priority time :");
	  for(int i=0;i<n;i++)
	  {
		 pt[i]=sc.nextInt();
	  }
	  
	  
	  for(int i=0;i<n;i++)
	  {
	     pos=i;
	      for(int j=i+1;j<n;j++)
		  {
		      if(pt[j]<pt[pos])
			  {
			     pos=j;
			  }
		  }
		  
	  temp=pt[pos];
	  pt[pos]=pt[i];
	  pt[i]=temp;
	  
	   temp=p[pos];
	  p[pos]=p[i];
	  p[i]=temp;
	  
	  temp=bt[pos];
	  bt[pos]=bt[i];
	  bt[i]=temp;
	  }
	  
	  wt[0]=0;
	  for(int i=1;i<n;i++)
	  {
	     wt[i]=wt[i-1]+bt[i-1];
	  }
	  
	  System.out.println("process\tbt\tpt\twt\ttat");
	  for(int i=0;i<n;i++)
	  {
	      tat[i]=bt[i]+wt[i];
		  System.out.println(p[i]+"\t"+bt[i]+"\t"+pt[i]+"\t"+wt[i]+"\t"+tat[i]);
	  }
	  
	 
  }

}