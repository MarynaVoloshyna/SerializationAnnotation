import java.io.Serializable;

public class Cat {
    @Save

    private String name;

    private int age;
    @Save
    private String meal;

    public Cat(String name, int age, String meal) {
        this.name = name;
        this.age = age;
        this.meal = meal;
    }

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    @Override
    public String toString() {
        return "Cat" + " " +
                "name='" + name + '\'' +
                ", age=" + age +
                ", meal='" + meal + '\'';
    }
}
