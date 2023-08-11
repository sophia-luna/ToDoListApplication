public class Task implements Comparable<Task> {
    private String name;
    private String description;
    private String dueDate;
    private String priority;
    private String category;
    private String status;

    Task(String name, String description, String dueDate, String priority, String category, String status) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.status = status;
    }
    @Override
    public String toString() {
        return "   -Nome: " + name +
                "\n   -Descrição: " + description +
                "\n   -Data de Entrega: " + dueDate +
                "\n   -Prioridade: " + priority +
                "\n   -Categoria: " + category +
                "\n   -Status: " + status;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getDueDate() {
        return dueDate;
    }
    public String getPriority(){
        return priority;
    }
    public String getCategory(){
        return category;
    }
    public String getStatus(){
        return status;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setDueDate(String date){
        this.dueDate=date;
    }
    public void setPriority(String priority){
        this.priority=priority;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setStatus(String status){
        this.status=status;
    }
    @Override
    public int compareTo(Task task) {
        int thisAux=Integer.parseInt(this.priority);
        int thatAux=Integer.parseInt(task.getPriority());
        return thatAux-thisAux;
    }
}