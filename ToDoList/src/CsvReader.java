import java.io.*;
import java.util.LinkedList;

public class CsvReader {
    public static final String delimiter = ",";
    public static final String csvFile = "/home/sophia/ToDoListApplication/ToDoList/src/toDoList.csv";
    static LinkedList<Task> list=new LinkedList<Task>();
    public static LinkedList<Task> read(){
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] data;
            while((line = br.readLine()) != null) {
                data = line.split(delimiter);
                //System.out.println(data[0]+' '+data[1]+' '+data[2]+' '+data[3]+' '+data[4]+' '+data[5]);
                Task task=new Task(data[0], data[1], data[2], data[3], data[4], data[5]);
                list.add(task);
            }
            br.close();
        } catch(IOException ioe) {
            //attempting to access a file that does not exist at the specified location
            System.out.println("File not found");
        }
        return list;
    }
}

