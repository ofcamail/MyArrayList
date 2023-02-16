import list.*;

public class Main {
    public static void main(String[] args) {
        IntegerListImpl list = new IntegerListImpl(3);
        list.add(3);
        list.add(1);
        list.add(5);
        list.contains(5);
        System.out.println(list);
    }
}