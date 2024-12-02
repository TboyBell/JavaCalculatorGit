import java.io.*;

public class History {
    
    public static void main(String[] args) {

       

        File file = new File("Traceback.txt");

        if (file.exists()) {

            try {
                FileWriter writer = new FileWriter("Traceback.txt");
                writer.write("22 * 44 \n = 968");
                writer.write("\n");
                writer.close();
            } catch (Exception e) {
                // TODO: handle exception
            }

        }


        try {
            FileReader reader = new FileReader("Traceback.txt");
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Damn Sorry Bro");

                }
        









    }
    
    
    
}
