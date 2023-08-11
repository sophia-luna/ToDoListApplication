import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Main {
    public static void main(String[] args) {
        LinkedList<Task> list = CsvReader.read();
        list.sort(Comparator.naturalOrder());

        String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        for(Task task:list){
            if(!task.getAlarmMessage().equals("@") && task.getDueDate().equals(today)){
                System.out.println("\n*****************ALARME*****************");
                System.out.println(task.getName()+": "+task.getAlarmMessage());
                System.out.println("****************************************\n");
            }
        }
        while(true){
            System.out.println();
            Menu.displayMenu();
            Scanner sc=new Scanner(System.in);
            String input= sc.nextLine();
            if(!Validation.isMenuOptionValid(input)){
                System.out.println("Opção Inválida. Insira um número de 1 a 9.");
            }
            else{
                if(input.equals("1")){
                    Menu.displayList(list);
                }
                else if(input.equals("2")){
                    list.add(Menu.createTask());
                    list.sort(Comparator.naturalOrder());
                }
                else if(input.equals("3")){
                    Menu.displayList(list);
                    String n;
                    while(true){
                        System.out.println("Insira o número da tarefa que deseja alterar: ");
                        n=sc.nextLine();
                        if(Validation.isTask(list, n)) {
                            break;
                        }
                        else{
                            System.out.println("Opção Inválida.");
                        }
                    }
                    int chosen=Integer.parseInt(n);
                    chosen--;
                    Menu.alterTask(list, chosen);
                }
                else if(input.equals("4")){
                    Menu.displayList(list);
                    String n;
                    while(true){
                        System.out.print("Insira o número da tarefa que deseja excluir: ");
                        n=sc.nextLine();
                        if(Validation.isTask(list, n)) {
                            break;
                        }
                        else{
                            System.out.println("Opção Inválida.");
                        }
                    }
                    int chosen=Integer.parseInt(n);
                    chosen--;
                    Menu.deleteTask(list, chosen);
                }
                else if(input.equals("5")){
                    Menu.filterByCategory(list);
                }
                else if(input.equals("6")){
                    Menu.filterByStatus(list);
                }
                else if(input.equals("7")){
                    Menu.filterByDate(list);
                }
                else if(input.equals("8")){
                    Menu.displayList(list);
                    String n;
                    while(true){
                        System.out.print("Insira o número da tarefa que deseje que o alarme seja adicionado: ");
                        n=sc.nextLine();
                        if(Validation.isTask(list, n)) {
                            break;
                        }
                        else{
                            System.out.println("Opção Inválida.");
                        }
                    }
                    int chosen=Integer.parseInt(n);
                    chosen--;
                    String message;
                    System.out.print("Insira a mensagem do alarme: ");
                    message=sc.nextLine();
                    Menu.newAlarm(chosen, message, list);
                }
                else {
                    Menu.exit(list);
                    break;
                }
            }
        }
    }
}