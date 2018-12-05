package Screens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    File direct;
    File arquivo;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    String workingDir = System.getProperty("user.home");
    String time = new SimpleDateFormat("MM-YYYY").format(Calendar.getInstance().getTime());
    String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
 public void createDirect() throws IOException
 {
      direct = new File(workingDir + "\\Desktop\\Log");
       //tenta criar um novo diretorio
        try {
            //verifica se o diretorio Log não existe para poder criar
            if (!direct.exists()) {
                direct.mkdir();
            }
            
            //verifica se o diretorio Mês não existe para poder criar
            direct = new File(workingDir + "\\Desktop\\log\\" + time);
            try {
                if (!direct.exists()) {
                    direct.mkdir();
                }
                arquivo = new File(workingDir + "\\Desktop\\log\\" + time + "\\" + timeStamp + ".txt");
                //tenta criar o arquivo .txt
                try {
                    arquivo.createNewFile();
                } catch (IOException exception) {
                    Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, exception);

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
    }
     public void WriteLog(String log) throws IOException {
        try (BufferedWriter Writer = new BufferedWriter(new FileWriter(workingDir + "\\Desktop\\log\\" + time + "\\" + timeStamp + ".txt", true))) {
            Writer.write(log);
        }
        
 }
    
}
