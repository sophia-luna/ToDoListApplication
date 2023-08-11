import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<Task> list = CsvReader.read();
        list.sort(Comparator.naturalOrder());
        while(true){
            Menu.displayMenu();
            Scanner sc=new Scanner(System.in);
            String input= sc.nextLine();
            if(!Validation.isMenuOptionValid(input)){
                System.out.println("Opção Inválida. Insira um número de 1 a 8.");
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
                        System.out.println("Insira o número da tarefa que deseja excluir: ");
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
                else {
                    try {
                        CsvWriter.write(list);
                    } catch (IOException e) {
                        System.out.println("File Error");
                    }
                    break;
                }
            }
        }
    }
}