package csc227Project;
public class PCB {

	String ProcessID;
	int ArrivalTime,  CPUBurst,  priority, StartTime, 
	TerminationTime, TurnAroundTime, WaitingTime, ResponseTime;
	int finishedBurst=0;
	boolean assigned=false;

	public PCB(String pid, int arrT, int BT, int priority){
		ProcessID = pid;
		ArrivalTime = arrT;
		CPUBurst = BT;
		this.priority = priority;
	}


	public void setArrivalT(int arrivalT) {
		this.ArrivalTime = arrivalT;
	}

	public void setPID(String PID) {
		this.ProcessID = PID;
	}

	public void setBurstT(int burstT) {
		CPUBurst = burstT;
	}

	public int getPriority() {
		return priority;
	}


	public int getArrivalT() {
		return ArrivalTime;
	}

	public int getBurstT() {
		return CPUBurst;
	}

	public String getPID() {
		return ProcessID;
	}

	public void setStartTime(int StartTime) {
		this.StartTime = StartTime;
	}

	public void setTerminationTime(int TerminationTime) {
		this.TerminationTime = TerminationTime;
	}

	public void setTurnAroundTime(int TurnAroundTime) {
		this.TurnAroundTime = TurnAroundTime;
	}

	public void setWaitingTime(int WaitingTime) {
		this.WaitingTime = WaitingTime;
	}

	public void setResponseTime(int ResponseTime) {
		this.ResponseTime = ResponseTime;
	}

	public int getStartTime() {
		return StartTime;
	}


	public int getTerminationTime() {
		return TerminationTime;
	}

	public int getTurnAroundTime() {
		return TurnAroundTime;
	}

	public int getWaitingTime() {
		return WaitingTime;
	}

	public int getResponseTime() {
		return ResponseTime;
	}

	@Override
	public String toString() {
		return String.format("ProcessID: %s \nPriority: %d \nArrivalTime: %d \nCPUburst: %d \nStartTime: %d \nTerminationTime: %d \nTurnAroundTime: %d \nWaitingTime: %d \nResponseTime: %d\n", 
				ProcessID, priority, ArrivalTime, CPUBurst, StartTime, TerminationTime, TurnAroundTime, WaitingTime, ResponseTime);
	}

}
