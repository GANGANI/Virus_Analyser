/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusanalyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.logging.Logger;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.FileHandler;

/**
 *
 * @author Gangani
 */

public class Virus_Analyser {
    private final String viruses_file_location = "virus_store.csv";
    
    private final String[][] virus;
    
    public Virus_Analyser(){
        virus = loadRecognisedViruses(); 
    }
    
    public Virus analyseFile(File file){

        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
           String checksum = getChecksumFile(md5, file);

            for(int r=0;r<5000;r++){
                if(checksum.equals(virus[r][1])){
                    return new Virus(virus[r][0], virus[r][1], virus[r][2].split(" ")[0], 
                            virus[r][3], virus[r][4], virus[r][5]);
                }
            }       
        } catch (NoSuchAlgorithmException | IOException ex) {
            Logger.getLogger(Virus_Analyser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String[][] loadRecognisedViruses(){
        
        String[][] viruses=new String[5000][6];
        
        try {
            String[] virus_data;
            String line;
            BufferedReader bReader=new BufferedReader(new FileReader(viruses_file_location));
            int i=0;
            while(true){
                line = bReader.readLine();
                if(line==null){
                    break;
                }
                virus_data=line.split(",");
                
                System.arraycopy(virus_data, 0, viruses[i], 0, 5);
                
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return viruses;
    }
        
    private  String getChecksumFile(MessageDigest digestMsg, File file) throws IOException
    {
        try (FileInputStream finput = new FileInputStream(file)) {
            int byteCount = 0;
            byte[] array = new byte[1024];
            while ((byteCount = finput.read(array)) != -1) {
                digestMsg.update(array, 0, byteCount);
            }
        }
        StringBuilder string = new StringBuilder();
        byte[] byteArray = digestMsg.digest();

        for(int i=0; i< byteArray.length ;i++)
        {
            string.append(Integer.toString((byteArray[i] & 0xff) + 0x100, 16).substring(1));
        }

        return string.toString();
    }
    
}
