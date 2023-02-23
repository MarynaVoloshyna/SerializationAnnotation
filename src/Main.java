import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Cat cat = new Cat("Musia", 8, "Royal Canin");
        File file = new File("/Users/maryna/Desktop/FileSaver/Cat.txt");

        saver(cat, file);
        recovery(file);

    }


    public static void saver(Object object, File file) {
        Class<?> cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    if(Modifier.isPrivate(field.getModifiers())){
                        field.setAccessible(true);

                            objectOutputStream.writeObject(object);

                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("DONE!");
    }


    public static List<Object>  recovery( File file) {
    List<Object> list = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((file)))){
                list.add(objectInputStream.readObject());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }


}