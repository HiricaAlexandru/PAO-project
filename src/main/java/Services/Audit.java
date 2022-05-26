/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 *
 * @author carja
 */
public class Audit {
    private static FileWriter fr = null;
    private static Audit audit = null;
    private static BufferedWriter br = null;
    private static LinkedList<String> commandName = null;
    private static LinkedList<LocalDateTime> dateTime = null;
    private Audit(){
        commandName = new LinkedList<>();
        dateTime = new LinkedList<>();
    }
    
    public static Audit getInstance() throws Exception{
        if(audit == null){
            
            try{
                fr = new FileWriter("audit.csv");
                br = new BufferedWriter(fr);
                br.write("name,timestamp\n");
                br.flush();
            }catch(IOException i){
                System.out.println("EROARE");

            }
        audit = new Audit();
            
        }
        return audit;
    }
    
    public static void writeToAudit(String toWriteLine) throws IOException{
        br.write(toWriteLine);
        commandName.add(toWriteLine);
        LocalDateTime dt = LocalDateTime.now();
        br.write(","+dt.toString()+'\n');
        br.flush();
    }

    protected void finalize() throws IOException{  
        br.close();
    }  
 
}
