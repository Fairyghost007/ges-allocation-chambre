package gesucad.core;


import java.util.List;
import java.util.Scanner;

public  abstract class ViewImpl<T>   implements View<T>{
    //  protected Scanner scanner;

    protected Scanner scanner=new Scanner(System.in);
    @Override
      public void affiche(List<T> datas) {
      for (T data : datas) {
           System.out.println(data);   
      }
    } 

    @Override
    public void delete(T object) {
        // Default implementation for delete, can be overridden in subclasses
        System.out.println("Deleting object: " + object);
    }
}
