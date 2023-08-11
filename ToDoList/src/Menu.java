import javax.sound.midi.Soundbank;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {
    public static void displayMenu() {
        System.out.println("    Menu");
        System.out.println("1. Listar tarefas");
        System.out.println("2. Criar nova tarefa");
        System.out.println("3. Alterar tarefa existente");
        System.out.println("4. Excluir tarefa");
        System.out.println("5. Filtrar tarefas por categoria");
        System.out.println("6. Filtrar tarefas por status");
        System.out.println("7. Filtrar tarefas por data");
        System.out.println("8. Sair");
    }

    public static void displayList(LinkedList<Task> list) { //option 1
        int i = 1;
        if (list.isEmpty()) {
            System.out.println("Lista de tarefas vazia.");
        } else {
            for (Task task : list) {
                System.out.println("Tarefa " + i + ':');
                System.out.println(task);
                System.out.println();
                i++;
            }
        }
    }

    public static Task createTask() { //option 2
        System.out.println("Criando nova tarefa...");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome da tarefa: ");
        String name = sc.nextLine();

        System.out.print("Descrição da tarefa: ");
        String desc = sc.nextLine();

        String date;
        while (true) {
            System.out.print("Data de entrega da tarefa no formato DD/MM/YYYY: ");
            date = sc.nextLine();
            if (Validation.isDateValid(date)) {
                break;
            } else {
                System.out.println("Data Inválida.");
            }
        }

        String prior;
        while (true) {
            System.out.print("Prioridade da tarefa (1-5): ");
            prior = sc.nextLine();
            if (Validation.isPriorityValid(prior)) {
                break;
            } else {
                System.out.println("Input Inválido. Insira um número de 1 a 5.");
            }
        }

        System.out.print("Categoria da tarefa: ");
        String categ = sc.nextLine();

        String status;
        while (true) {
            System.out.println("Escolha o status da tarefa: ");
            System.out.println("1. To Do");
            System.out.println("2. Doing");
            System.out.println("3. Done");
            status = sc.nextLine();
            if (Validation.isStatusValid(status)) {
                if (status.equals("1")) {
                    status = "To Do";
                } else if (status.equals("2")) {
                    status = "Doing";
                } else {
                    status = "Done";
                }
                break;
            } else {
                System.out.println("Opção Inválida. Insira um número de 1 a 3.");
            }
        }
        return new Task(name, desc, date, prior, categ, status);
    }

    public static void alterTask(LinkedList<Task> list, int chosen) { //option 3
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Alterando tarefa...");
            System.out.println("Escolha qual detalhe da tarefa deseja alterar:");
            System.out.println("1. Nome");
            System.out.println("2. Descrição");
            System.out.println("3. Data de entrega");
            System.out.println("4. Prioridade");
            System.out.println("5. Categoria");
            System.out.println("6. Status");

            input = sc.nextLine();
            if (Validation.isAlterOptionValid(input)) {
                break;
            } else {
                System.out.println("Opção Inválida. Insira um número de 1 a 6.");
            }
        }
        if (input.equals("1")) {
            System.out.print("Novo Nome: ");
            String name = sc.nextLine();
            list.get(chosen).setName(name);
        } else if (input.equals("2")) {
            System.out.print("Nova Descrição: ");
            String desc = sc.nextLine();
            list.get(chosen).setDescription(desc);
        } else if (input.equals("3")) {
            String date;
            while (true) {
                System.out.print("Nova Data de Entrega no formato DD/MM/YYYY: ");
                date = sc.nextLine();
                if (Validation.isDateValid(date)) {
                    break;
                } else {
                    System.out.println("Data Inválida.");
                }
            }
            list.get(chosen).setDueDate(date);
        } else if (input.equals("4")) {
            String prior;
            while (true) {
                System.out.print("Nova prioridade da tarefa (1-5): ");
                prior = sc.nextLine();
                if (Validation.isPriorityValid(prior)) {
                    break;
                } else {
                    System.out.println("Input Inválido. Insira um número de 1 a 5.");
                }
            }
            list.get(chosen).setPriority(prior);
            list.sort(Comparator.naturalOrder());
        } else if (input.equals("5")) {
            System.out.print("Nova Categoria: ");
            String category = sc.nextLine();
            list.get(chosen).setCategory(category);
        } else {
            String status;
            while (true) {
                System.out.println("Escolha novo status da tarefa: ");
                System.out.println("1. To Do");
                System.out.println("2. Doing");
                System.out.println("3. Done");
                status = sc.nextLine();
                if (Validation.isStatusValid(status)) {
                    if (status.equals("1")) {
                        status = "To Do";
                    } else if (status.equals("2")) {
                        status = "Doing";
                    } else {
                        status = "Done";
                    }
                    break;
                } else {
                    System.out.println("Opção Inválida. Insira um número de 1 a 3.");
                }
            }
            list.get(chosen).setStatus(status);
        }
    }

    public static void deleteTask(LinkedList<Task> list, int chosen) { //option 4
        list.remove(chosen);
        chosen++;
        System.out.println("Tarefa " + chosen + " excluída...\n");
    }

    public static void filterByCategory(LinkedList<Task> list) { //option 5
        System.out.print("Insira a categoria que deseja buscar: ");
        Scanner sc = new Scanner(System.in);
        String category = sc.nextLine();
        int i = 0;
        System.out.println("Listando tarefas com filtro: Categoria " + category + " ...\n");
        for (Task task : list) {
            if (task.getCategory().equals(category)) {
                System.out.println(task);
                i++;
            }
        }
        if (i == 0) {
            System.out.println("    Nenhuma tarefa encontrada.");
        }
    }

    public static void filterByStatus(LinkedList<Task> list) { //option 6
        Scanner sc = new Scanner(System.in);
        String status;
        while (true) {
            System.out.println("Selecione o status que deseja buscar: ");
            System.out.println("1. To Do");
            System.out.println("2. Doing");
            System.out.println("3. Done");
            status = sc.nextLine();
            if (Validation.isStatusValid(status)) {
                if (status.equals("1")) {
                    status = "To Do";
                } else if (status.equals("2")) {
                    status = "Doing";
                } else {
                    status = "Done";
                }
                break;
            } else {
                System.out.println("Opção Inválida. Insira um número de 1 a 3.");
            }
        }
        int i = 0;
        System.out.println("Listando tarefas com filtro: Status " + status + " ...\n");
        for (Task task : list) {
            if (task.getStatus().equals(status)) {
                System.out.println(task);
                i++;
            }
        }
        if (i == 0) {
            System.out.println("    Nenhuma tarefa encontrada.");
        }
    }

    public static void filterByDate(LinkedList<Task> list) { //option 7
        Scanner sc = new Scanner(System.in);
        String date;
        while (true) {
            System.out.println("Insira a data que deseja buscar no formato DD/MM/YYYY: ");
            date = sc.nextLine();
            if (Validation.isDateValid(date)) {
                break;
            } else {
                System.out.println("Data Inválida.");
            }
        }
        int i = 0;
        System.out.println("Listando tarefas com filtro: Data de entrega " + date + " ...\n");
        for (Task task : list) {
            if (task.getDueDate().equals(date)) {
                System.out.println(task);
                i++;
            }
        }
        if (i == 0) {
            System.out.println("    Nenhuma tarefa encontrada.");
        }
    }
    public static void exit(){

    }
}