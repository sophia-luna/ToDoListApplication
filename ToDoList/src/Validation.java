import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class Validation{
    final static String DATE_FORMAT = "dd/MM/yyyy";
    public static boolean isDateValid(String date){
        try{
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        }catch(ParseException e){
            return false;
        }
    }
    public static boolean isMenuOptionValid(String str){
        int aux;
        try{
            aux=Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            aux=0;
        }
        return aux>0 && aux<=8;
    }
    public static boolean isPriorityValid(String str){
        int aux;
        try{
            aux=Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            aux=0;
        }
        return aux>0 && aux<=5;
    }
    public static boolean isStatusValid(String str){
        int aux;
        try{
            aux=Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            aux=0;
        }
        return aux>0 && aux<=3;
    }
    public static boolean isAlterOptionValid(String str){
        int aux;
        try{
            aux=Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            aux=0;
        }
        return aux>0 && aux<=6;
    }
    public static boolean isTask(LinkedList<Task>list, String str){
        int aux;
        try{
            aux=Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            aux=0;
        }
        aux--;
        int s=list.size();
        return aux>=0 && aux<s;
    }
}
