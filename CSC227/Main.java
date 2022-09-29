package csc227Project;
import java.io.*;
import java.util.*;

public class Main {

	static int P1, P2, BT; //P1: length of queue 1, P2: length of queue 2, BT: sum of processes' burst time
	static int q1counter, q2counter; //number of processes in each queue
	static PCB processes[], Q1[], Q2[];
	static LinkedList<String> ProcessesChart;
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int choice = 0;
		System.out.println("this is a process scheduling simulation program.");

		while(choice != 4) {
			System.out.println("What do you want to do next: ");
			System.out.println("1. Enter processes' information");
			System.out.println("2. Report detailed information about each process.");
			System.out.println("3. Report the average turnaround time, waiting time, and response time.");
			System.out.println("4. Exit the program.");
			choice = input.nextInt();
			switch(choice) {
			case 1:
				ProcessInfo();
				break;
			case 2:
				if(processes == null) {
					System.out.println("you need to enter processes first.");
					break;
				}
				PrintReport1();
				break;
			case 3:
				if(processes == null) {
					System.out.println("you need to enter processes first.");
					break;
				}
				PrintReport2();
				break;
			case 4:
				System.out.println("Goodbye.");
				break;
			default: choice = 0;
			}
		}
		input.close();
	}

	static void ProcessInfo() { //take user input and store it in processes array

		P1=0; P2=0; BT=0; q1counter=0; q2counter=0;
		ProcessesChart=null; processes=null; Q1=null; Q2=null;

		int numOfProcesses, priority, arrivalT, burstT;
		do {
			System.out.print("Enter the number of processes: ");
			numOfProcesses = input.nextInt();
		} while(numOfProcesses<=0);

		processes = new PCB[numOfProcesses];

		for (int i = 0; i < numOfProcesses; i++) {

			do {
				System.out.printf("Enter priority of process P%d: ", i);
				priority = input.nextInt();
			} while(priority != 1 && priority !=2);   

			do {
				System.out.printf("Enter arrival time of process P%d: ", i);
				arrivalT = input.nextInt();
			} while(arrivalT<0);

			do {
				System.out.printf("Enter burst time of process P%d: ", i);
				burstT = input.nextInt();
			} while(burstT<=0);

			BT += burstT;

			if (priority == 1)
				P1++;
			else
				P2++;

			processes[i] = new PCB(String.format("P%d", i), arrivalT, burstT, priority);
		}

		Q1 = new PCB[P1];
		Q2 = new PCB[P2];
		MLQ();
	}

	static void MLQ() { //multilevel queue scheduler with fixed preemption
		ProcessesChart = new LinkedList<String>();
		int timer=0;

		while(timer<BT) {
			assign(timer);
			if(Q1.length>0 && Q1[0] != null) {
				if(Q2.length>0 && Q2[0] != null && Q2[0].finishedBurst != 0)
					reschedule();

				Q1[0].setStartTime(timer);
				Q1[0].setTerminationTime(timer + Q1[0].getBurstT());
				Q1[0].setTurnAroundTime(Q1[0].getTerminationTime() - Q1[0].getArrivalT());
				Q1[0].setWaitingTime(Q1[0].getTurnAroundTime() - Q1[0].getBurstT());
				Q1[0].setResponseTime(Q1[0].getStartTime() - Q1[0].getArrivalT());

				int j = timer;
				timer += Q1[0].getBurstT();

				while(j<timer)
					ProcessesChart.add(j++, Q1[0].getPID());

				shift();
			}
			else if(Q2.length>0 && Q2[0]!=null) {
				if(Q2[0].finishedBurst == 0)
					Q2[0].setStartTime(timer);

				ProcessesChart.add(timer++, Q2[0].getPID());

				if(Q2[0].finishedBurst < Q2[0].getBurstT())
					Q2[0].finishedBurst++;

				if(Q2[0].finishedBurst == Q2[0].getBurstT()) {
					Q2[0].setTerminationTime(timer);
					Q2[0].setTurnAroundTime(Q2[0].getTerminationTime() - Q2[0].getArrivalT());
					Q2[0].setWaitingTime(Q2[0].getTurnAroundTime() - Q2[0].getBurstT());
					Q2[0].setResponseTime(Q2[0].getStartTime() - Q2[0].getArrivalT());
					reschedule();
				}
			}
			else {
				ProcessesChart.add(timer++, "  ");
				BT++;
			}
		}
	}
	
	static void shift() {
		for(int i=1; i<q1counter; i++) {
			Q1[i-1] = Q1[i];
		}
		Q1[--q1counter] = null;
	}

	static void reschedule() {
		PCB tempProcess = Q2[0];
		for(int i=1; i<q2counter; i++) {
			Q2[i-1] = Q2[i];
		}
		if(tempProcess.CPUBurst != tempProcess.finishedBurst) {
			Q2[q2counter-1] = tempProcess;
		}
		else {
			Q2[--q2counter] = null;
		}
	}

	static void assign(int arrival) { //assign the processes to the two queues
		PCB arrived[] = arrivedprocesses(arrival);
		int n = q2counter;
		for(PCB process : arrived) {
			if(Q1.length>0 && process.getPriority() == 1) {
				Q1[q1counter++] = process;
			}else if(Q2.length>0){
				Q2[q2counter++] = process;
			}
			process.assigned = true;
		}
		SJF();
		FCFS(n);
	}

	static PCB[] arrivedprocesses(int arrival) {
		int i=0;
		for(PCB process : processes) {
			if(process.getArrivalT()<=arrival && !process.assigned) {
				i++;
			}
		}

		PCB arrived[] = new PCB[i];
		i=0;
		for(PCB process : processes) {
			if(process.getArrivalT()<=arrival && !process.assigned) {
				arrived[i++] = process;
			}
		}

		return arrived;
	}

	static void SJF() { //shortest-job-first sorting algorithm
		int n = q1counter;
		for(int i=0; i<n; i++) {
			int min=i;
			for(int j=i+1; j<n; j++) {
				if(Q1[j].getBurstT() < Q1[min].getBurstT())
					min = j;
			}
			PCB temp = Q1[i];
			Q1[i] = Q1[min];
			Q1[min] = temp;
		}
	}
	
	static void FCFS(int n) { //first-come-first-served sorting algorithm
		for(int i=n; i<q2counter; i++) {
			int min=i;
			for(int j=i+1; j<q2counter; j++) {
				if(Q2[j].getArrivalT() < Q2[min].getArrivalT())
					min = j;
			}
			PCB temp = Q2[i];
			Q2[i] = Q2[min];
			Q2[min] = temp;
		}
	}

	static void PrintReport1() throws IOException {
		for(int i=0; i<processes.length; i++){
			System.out.println(processes[i].toString());
		}

		PrintWriter PF = new PrintWriter("Report1.txt");

		try {

			PF.printf("%-15s", "process ID");
			PF.printf("%-20s", "Process Priority");
			PF.printf("%-15s", "CPU burst");
			PF.printf("%-15s", "Arrival time");

			PF.printf("%-20s", "Start time");
			PF.printf("%-20s", "Termination time");
			PF.printf("%-30s", "Turn around time");
			PF.printf("%-20s", "Waiting time");
			PF.printf("%-20s\n", "Response time");

			for (int i=0; i<processes.length; i++) {
				PF.printf("%-15s", processes[i].getPID());
				PF.printf("%-20d", processes[i].getPriority());
				PF.printf("%-15d", processes[i].getBurstT());
				PF.printf("%-15d", processes[i].getArrivalT());

				PF.printf("%-20d", processes[i].getStartTime());
				PF.printf("%-20d", processes[i].getTerminationTime());
				PF.printf("%-30d", processes[i].getTurnAroundTime());
				PF.printf("%-20d", processes[i].getWaitingTime());
				PF.printf("%-20d\n", processes[i].getResponseTime());
			}

			PF.printf("\nScheduling order chart: [");
			for(int i=0; i<BT-1; i++)
				PF.printf("%s|", ProcessesChart.get(i));
			PF.printf("%s]", ProcessesChart.get(BT-1));

		}
		catch(Exception c) {}

		System.out.print("\nScheduling order chart: [");
		for(int i=0; i<BT-1; i++)
			System.out.printf("%s|", ProcessesChart.get(i));
		System.out.printf("%s]\n", ProcessesChart.get(BT-1));

		PF.close();
	}

	static void PrintReport2() throws IOException {
		double avgTurnAroundTime=0, avgWaitingTime=0, avgResponseTime=0;
		for(PCB process : processes) {
			avgTurnAroundTime += process.TurnAroundTime;
			avgWaitingTime += process.WaitingTime;
			avgResponseTime += process.ResponseTime;
		}
		avgTurnAroundTime = avgTurnAroundTime/processes.length;
		avgWaitingTime = avgWaitingTime/processes.length;
		avgResponseTime = avgResponseTime/processes.length;
		System.out.printf("average TurnAround Time: %.1f \naverage Waiting Time: %.1f \naverage Response Time: %.1f\n", avgTurnAroundTime, avgWaitingTime, avgResponseTime);

		PrintWriter PF = new PrintWriter("Report2.txt");
		PF.printf("%s%.1f\n", "average TurnAround Time: ", avgTurnAroundTime);
		PF.printf("%s%.1f\n", "average Waiting Time: ", avgWaitingTime);
		PF.printf("%s%.1f\n", "average Response Time: ", avgResponseTime);

		PF.close();
	}

}
