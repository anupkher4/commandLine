package com.SJSU;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> commandList = new ArrayList<String>();
        commandList.add("/bin/sh");
        commandList.add("-c");
        //commandList.add("cd /Users/anupkher/ ; ls -alt");
        commandList.add("sshpass -p 'pass' ssh admin@192.168.1.137 cd /data/etc/ | ls -alt");
        ProcessBuilder pb = new ProcessBuilder(commandList);
        Process process = null;
        int errCode = 0;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            process = pb.start();
            errCode = process.waitFor();
            System.out.println("Any errors?" + (errCode == 0 ? " NO" : " YES"));
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }
}
