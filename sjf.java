import java.util.Scanner;

class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();

        // Process IDs, Burst times, Turnaround times, and Waiting times
        int pid[] = new int[n]; // Process IDs
        int bt[] = new int[n];  // Burst times
        int tat[] = new int[n]; // Turnaround times
        int wt[] = new int[n];  // Waiting times

        // Input burst times for each process
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1; // Process ID
            System.out.println("Enter burst time for process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }

        // Sorting processes based on burst time (using selection sort for simplicity)
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (bt[j] < bt[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap burst times and process IDs
            int temp = bt[i];
            bt[i] = bt[minIndex];
            bt[minIndex] = temp;

            temp = pid[i];
            pid[i] = pid[minIndex];
            pid[minIndex] = temp;
        }

        // Calculating waiting times
        wt[0] = 0; // First process has no waiting time
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
        }

        // Calculating turnaround times
        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + bt[i];
        }

        // Display results and calculate average times
        int totalWT = 0, totalTAT = 0;
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            totalWT += wt[i];
            totalTAT += tat[i];
            System.out.println(pid[i] + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }

        // Calculate and display average waiting and turnaround times
        float avgWT = (float) totalWT / n;
        float avgTAT = (float) totalTAT / n;
        System.out.println("Average waiting time: " + avgWT);
        System.out.println("Average turnaround time: " + avgTAT);

        sc.close();
    }
}
