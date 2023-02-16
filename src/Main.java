import java.util.ArrayList;
import java.util.Arrays;
import list.MyStringList;
public class Main {
    public static void main(String[] args) {

        MyStringList myStringList = new MyStringList(10);
        myStringList.add("1");
        myStringList.add("2");
        myStringList.add("3");
        myStringList.add("4");
        myStringList.add("5");
        myStringList.add("6");
        myStringList.add("7");
        myStringList.add("8");
        myStringList.add("9");
        myStringList.add("10");
        myStringList.remove("1");


        myStringList.add(6, "7");
        System.out.println(myStringList);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        System.out.println(list);
    }
}