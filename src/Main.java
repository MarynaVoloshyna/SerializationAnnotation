import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {

        Cat cat = new Cat("Musia", 8, "Royal Canin");
        File file = new File("/Users/maryna/Desktop/FileSaver/Cat.txt");

        saver(cat, file);
        Cat cat1 = recovery(file);
        System.out.println(cat1);



    }


    public static void saver(Object object, File file) {
        Class<?> cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        String temp = "";


        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    if(Modifier.isPrivate(field.getModifiers())){
                        field.setAccessible(true);

                            temp =  field.getName() + "-"
                                    + field.get(object) + System.lineSeparator();
                            printWriter.write(temp);

                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("DONE!");
    }


    public static Cat recovery( File file) throws IllegalAccessException, FileNotFoundException, ClassCastException {
        Cat cat = new Cat();
        Scanner scanner = new Scanner(file);

        Class<?> cls = Cat.class;
        Field[] fields = cls.getDeclaredFields();
        for (;scanner.hasNext();){
            String [] temp = scanner.next().split("-");
            for (Field field: fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                        if (field.getName().equals(temp[0])) {
                            field.set(cat, temp[1]);
                            }
                        }
                    }
                }
            }

        return cat;
    }
}