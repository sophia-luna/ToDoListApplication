import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CsvWriter {
    public static void  write(LinkedList<Task> list) throws IOException {
        String csvFile = "/home/sophia/ToDoListApplication/ToDoList/src/toDoList.csv";
        File file=new File(csvFile);
        FileWriter fileWriter = new FileWriter(file);
        for(Task task:list)
        {
            StringBuilder line = new StringBuilder();
            line.append(task.getName());
            line.append(',');
            line.append((task.getDescription()));
            line.append(',');
            line.append(task.getDueDate());
            line.append(',');
            line.append(task.getPriority());
            line.append(',');
            line.append(task.getCategory());
            line.append(',');
            line.append(task.getStatus());
            line.append(',');
            line.append(task.getAlarmMessage());
            line.append("\n");
            fileWriter.write(line.toString());
        }
        fileWriter.close();
    }
}
