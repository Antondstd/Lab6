import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Human extends aCreature implements Comparable<Human>{
    enum BootsTypeOn {
        None,
        Sneakers,
        Sandals;
    }
    enum StandsOn {
        Parket,
        Asphalt,
        Concrete;
    }
    enum State {
        Angry,
        OutOfBreath,
        Relaxed

    }
    private String name;
    private Date appeared = new java.util.Date();
    private int weight;
    private boolean flyingType;
    private Gender gender;
    private BootsTypeOn boots;
    private StandsOn standOn;
    private State state = State.Relaxed;

    Human (String name, Gender gender, boolean flyingType, BootsTypeOn boots, StandsOn floor, int weight){
        this.name  = name;
        this.gender = gender;
        this.flyingType = flyingType;
        this.boots = boots;
        standOn = floor;
        /*try {
            this.birth = dateFormat.parse(birth);
        }catch (ParseException e) {
            e.printStackTrace();
        }*/
        this.weight  = weight;
        System.out.println("Создан персонаж с именем " + name + " и полом " + gender);
    }
    Human (String name, Gender gender, boolean flyingType, BootsTypeOn boots, StandsOn floor){
        this.name  = name;
        this.gender = gender;
        this.flyingType = flyingType;
        this.boots = boots;
        standOn = floor;
        System.out.println("Создан персонаж с именем " + name + " и полом " + gender);
    }
    Human (String name, Gender gender, boolean flyingType, BootsTypeOn boots){
        this.name  = name;
        this.gender = gender;
        this.flyingType = flyingType;
        this.boots = boots;
        System.out.println("Создан персонаж с именем " + name + " и полом " + gender);
    }
    Human (String name, Gender gender, boolean flyingType){
        this.name  = name;
        this.gender = gender;
        this.flyingType = flyingType;
        System.out.println("Создан персонаж с именем " + name + " и полом " + gender);
    }
    Human (String name, boolean flyingType){
        this.name  = name;
        this.gender = null;
        this.flyingType = flyingType;
        System.out.println("Создан персонаж с именем " + name + " и полом " + gender);
    }
    private int countNotGiveUp = 0;

    public int getCountNotGiveUp() {
        return countNotGiveUp;
    }

    public void setCountNotGiveUp(int countNotGiveUp) {
        this.countNotGiveUp = countNotGiveUp;
    }
    public void getShoes(){
        if (this.boots == BootsTypeOn.None)
            System.out.println(name + " стоит босиком на " + standOn);
    }
    public Gender getGender() throws NoGenderException{
        if (gender == null){
            throw new NoGenderException();
        }
        else {
            return gender;
        }
    }
    public String getName(){
        return name;
    }
    public boolean getFlyingType(){
        return flyingType;
    }

    public void setState(State state) {
        this.state = state;
        System.out.println("Состояние персонажа " + name + " изменен на " + state);
    }

    @Override
    public String toString(){
        String info = "Имя: " + this.name + " Пол: " + this.gender + " Возможность летать: " + this.flyingType + " Ботинки: " + this.boots + " Локация: " + this.standOn + " Дата появления: " + this.appeared;
        return info;
    }

    public void setDate(){
        this.appeared = new java.util.Date();
    }

    public int compareTo(Human h){

        return name.compareTo(h.getName());
    }
}