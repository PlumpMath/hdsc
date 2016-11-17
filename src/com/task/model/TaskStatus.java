package com.task.model;

public class TaskStatus {

	private int packetNumbers;
	private int tcpNumbers;
	private int udpNumbers;
	private int icmpNumbers;
	private int arpNumbers;
	private int trafficStatistics;
	private int packetSize;
	private String bpf;
	private int error;
	private int[] dataArray;

	public int[] getDataArray() {
		return dataArray;
	}

	public void setDataArray() {
		/*
		this.dataArray = new Object[]{
				String.valueOf(packetNumbers),
				String.valueOf(tcpNumbers),
				String.valueOf(udpNumbers),
				String.valueOf(icmpNumbers),
				String.valueOf(arpNumbers),
				String.valueOf(trafficStatistics),
				String.valueOf(packetSize)
		};
		*/
		this.dataArray = new int[]{
			packetNumbers,
			tcpNumbers,
			udpNumbers,
			icmpNumbers,
			arpNumbers,
			trafficStatistics,
			packetSize
		};
	}

	public int getPacketNumbers() {
		return packetNumbers;
	}
	public void setPacketNumbers(int packetNumbers) {
		this.packetNumbers = packetNumbers;
	}
	public int getTcpNumbers() {
		return tcpNumbers;
	}
	public void setTcpNumbers(int tcpNumbers) {
		this.tcpNumbers = tcpNumbers;
	}
	public int getUdpNumbers() {
		return udpNumbers;
	}
	public void setUdpNumbers(int udpNumbers) {
		this.udpNumbers = udpNumbers;
	}
	public int getIcmpNumbers() {
		return icmpNumbers;
	}
	public void setIcmpNumbers(int icmpNumbers) {
		this.icmpNumbers = icmpNumbers;
	}
	public int getArpNumbers() {
		return arpNumbers;
	}
	public void setArpNumbers(int arpNumbers) {
		this.arpNumbers = arpNumbers;
	}
	
	public int getTrafficStatistics() {
		return trafficStatistics;
	}

	public void setTrafficStatistics(int trafficStatistics) {
		this.trafficStatistics = trafficStatistics;
	}

	public int getPacketSize() {
		return packetSize;
	}
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}
	public String getBpf() {
		return bpf;
	}
	public void setBpf(String bpf) {
		this.bpf = bpf;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
}
