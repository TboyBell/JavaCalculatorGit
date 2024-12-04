import java.io.*;
import java.time.*;

public class DocumentLogger {

    static File file = new File("C:\\Users\\Belln\\Phase2\\JavaCalculatorGIT\\JavaCalculator\\Traceback.txt");

    public static void main(String[] args) throws IOException {

        Backend extendBackend = new Backend();


        try {
            if (extendBackend.isRunning) {
                if (file.createNewFile()) {
                    System.out.println("Traceback file created successfully.");
                } else {
                    System.out.println("Traceback file already exists.");
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error handling the file: " + e.getMessage());
        }




    }

    public void deleteTracebackFile() {
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Traceback file deleted successfully.");
            } else {
                System.out.println("Failed to delete traceback file.");
            }
        } else {
            System.out.println("No traceback file to delete.");
        }
    }
    


    public void logTraceback(double num1, double num2, char operator, double result) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        // Format the log entry as requested
        String logEntry = String.format(
                "%s - %s%n%.2f %c %.2f%n    =%.2f%n%n",
                date, time, num1, operator, num2, result);

        // Use try-with-resources to write to the file
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(logEntry);
            writer.append("Log Completed\n\n");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    
    public void logTraceback(String operation, double result) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        // Format the log entry as requested
        String logEntry = String.format(
                "%s - %s%n%s%n    =%.2f%n%n",
                date, time, operation, result);

        // Use try-with-resources to write to the file
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(logEntry);
            writer.append("Log Completed\n\n");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
}
