package com.innovationT.section.jsch;

import java.io.ByteArrayOutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class TestJsch {
	public Session session = null;
	public Channel ch = null;
	
	
	public Session getSessionConnect(String user, String host, String pass, int port) {
		JSch jsch = new JSch(); 
		try {
			session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	session.setConfig(config);
			session.setPassword(pass);
			session.connect();
			System.out.println("[ getSessionConnect ] connected");
		}catch(Exception e) {
			System.out.println("[ getSessionConnect ] error");
			e.printStackTrace();
		}		
		return session;
	}
	
	// 세션 닫기
	public void closeSession() {
		System.out.println("[ closeSession ] session close");
		this.session.disconnect();
	}	
	
	// 커맨드 전송명령
	public String sendCommand(String command) {
		StringBuilder sb = new StringBuilder();
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			this.ch = session.openChannel("exec");
			this.ch.setOutputStream(baos);
			((ChannelExec)this.ch).setErrStream(baos);
			((ChannelExec)this.ch).setCommand(command);
			
	        this.ch.connect();
	        while(true){
                if(this.ch.isClosed()){
                    break;
                }
            }
	        
	        this.ch.disconnect();
	        
	        sb.append(baos.toString());
		} catch(Exception e) {
			
		} finally {
			this.ch.disconnect();
		}
       
        return sb.toString();
	}
	
}
