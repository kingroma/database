package com.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;

public class Ftp {
	/*
	private String svrIp;
    private String user;
    private String passwd;
    //private String defaultPath;
    */
    private String default_svrIp = "uws64-034.cafe24.com";
    private String default_user = "dj5427";
    private String default_passwd = "KMLee5427";
    
    
    /*
	public Ftp(String svrIp, String user, String passwd, String defaultPath) {
        this.svrIp = svrIp;
        this.user = user;
        this.passwd = passwd;
        //this.defaultPath = defaultPath;
	}
	*/
    
    public void init(Properties p) {
    	default_svrIp = p.getProperty("server_ip");
    	default_user = p.getProperty("user_name");
    	default_passwd = p.getProperty("password");
        //defaultPath = p.getProperty("default_path");
    }
    public boolean upload(InputStream fis, String targetFile)
            throws SocketException, IOException, Exception {
        
        //FileInputStream fis = null;
        
        org.apache.commons.net.ftp.FTPClient clnt = new org.apache.commons.net.ftp.FTPClient();
        clnt.setControlEncoding("utf-8");
        
        try {
            clnt.connect(default_svrIp);
            //clnt.setBufferSize(1024*1024);
            int reply = clnt.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new Exception("ftp connection refused");
            }
            
            clnt.setSoTimeout(1000 * 10);
            clnt.login(default_user, default_passwd);
            clnt.setFileType(FTP.BINARY_FILE_TYPE);
            
            
            clnt.enterLocalActiveMode();
            
            //clnt.enterLocalPassiveMode();
            //clnt.changeWorkingDirectory(defaultPath);
            //clnt.makeDirectory("");
            
            
            return clnt.storeFile(targetFile, fis);
        }catch(Exception e) {
        	
        }
        finally {
            if (clnt.isConnected()) {
                clnt.disconnect();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return false;
    }
    
    public boolean upload(File file, String targetFile)
            throws SocketException, IOException, Exception {
        
        FileInputStream fis = null;
        
        org.apache.commons.net.ftp.FTPClient clnt = new org.apache.commons.net.ftp.FTPClient();
        clnt.setControlEncoding("utf-8");
        
        try {
            clnt.connect(default_svrIp);
            //clnt.setBufferSize(1024*1024);
            int reply = clnt.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new Exception("ftp connection refused");
            }
            
            clnt.setSoTimeout(1000 * 10);
            clnt.login(default_user, default_passwd);
            clnt.setFileType(FTP.BINARY_FILE_TYPE);
            
            
            clnt.enterLocalActiveMode();
            
            //clnt.enterLocalPassiveMode();
            //clnt.changeWorkingDirectory(defaultPath);
            //clnt.makeDirectory("");
            
            fis = new FileInputStream(file);
            return clnt.storeFile(targetFile, fis);
        } finally {
            if (clnt.isConnected()) {
                clnt.disconnect();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }



    
}

