package com.innovationT;

import com.innovationT.section.jsch.TestJsch;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("[ main ] start");
		String user = "paul";
		String host = "192.168.56.101";
		String pass = "qwer1234";
		int port = 22;
		
		TestJsch tj = new TestJsch();
		
		
		try {
			tj.session = tj.getSessionConnect(user, host, pass, port);
			String command1 = "cat /asdb/safawef";
			String command2 = "whoami";
			String command3 = "cat ~/test/test.sh";
			String command4 = "whoami";
			String result1 = tj.sendCommand(command1);
			String result2 = "";
			String result3 = tj.sendCommand(command3);
			String result4 = "";
			if(result1.contains("No such file or directory")) {
				// System.out.println("문제있음");
				result2 = tj.sendCommand(command2);
			}else {
				// System.out.println("문제없음");
				result2 = "문제없음";
			}
			
			if(result3.contains("No such file or directory")) {
				// System.out.println("문제있음");
				result4 = tj.sendCommand(command4);
			}else {
				// System.out.println("문제없음");
				result4 = "문제없음";
			}
			
			System.out.println("문제있는경우");
			System.out.println("result1 below ----------");
			System.out.println(result1);
			System.out.println("\nresult2 below ----------");
			System.out.println(result2);
			
			System.out.println("문제없는경우");
			System.out.println("result1 below ----------");
			System.out.println(result3);
			System.out.println("\nresult2 below ----------");
			System.out.println(result4);
		} catch(Exception e) {
			System.out.println("[ main ] error");
			e.printStackTrace();
		} finally {
			if(!tj.ch.isClosed()) {
				tj.ch.disconnect();
			}
			if(tj.session.isConnected()) {
				tj.session.disconnect();
			}
		}
	}

}
