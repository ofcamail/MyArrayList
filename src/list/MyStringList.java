package list;
import exceptions.*;

import java.util.Arrays;
public class MyStringList implements StringList {

    private String[] list;
    private int size;

    public MyStringList(int size) {
        if (size > 0) {
            this.list = new String[size];
        } else if (size == 0) {
            this.list = new String[10];
        } else {
            throw new IllegalArgumentException("Illegal size: " +
                    size);
        }
    }

    @Override
    public String add(String item) {
        if (list[list.length - 1] != null) {
            String[] list2 = list.clone();
            list = new String[list.length + 1];
            System.arraycopy(list2, 0, list, 0, list2.length);
            list[list.length - 1] = item;
            size++;
            return item;
        }
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = item;
                size++;
                break;
            }
        }
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index >= list.length) {
            throw new NonexistentIndexException("Индекс " + index + " не существует");
        }
        String[] temp = list.clone();
        if (list[list.length - 1] != null) {
            list = new String[list.length + 1];
        }
        list[index] = item;
        size++;
        System.arraycopy(temp, 0, list, 0, index);
        System.arraycopy(temp, index, list, index + 1, list.length - (index + 1));
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index < 0 || index >= list.length) {
            throw new NonexistentIndexException("Индекс " + index + " не существует");
        }
        list[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        boolean isElementNotInArray = false;
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(item)) {
                System.arraycopy(list, i + 1, list, i, list.length - i - 1);
                size--;
                return item;
            } else {
                isElementNotInArray = true;
            }
        }
        if (isElementNotInArray) {
            throw new ElementNotFoundException("Элемент " + item + " не найден");
        }
        return item;
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= list.length) {
            throw new NonexistentIndexException("Индекс " + index + " не существует");
        }
        if (list[index] == null) {
            throw new ElementNotFoundException("Элемент по индексу не найден");
        }
        String result = list[index];
        System.arraycopy(list, index + 1, list, index, list.length - index - 1);
        size--;
        return result;
    }

    @Override
    public boolean contains(String item) {
        for (String string : list) {
            if (string != null && string.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] != null && list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= list.length) {
            throw new NonexistentIndexException("Индекс " + index + " не существует");
        }
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new ListNotFoundException();
        }
        if (this.size() != otherList.size())
            return false;

        for (int i = 0; i < size(); i++) {
            if (!this.get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        int length = list.length;
        list = new String[length];
    }

    @Override
    public String[] toArray() {
        String[] result = new String[size];
        System.arraycopy(list, 0, result, 0, size);
        return result;
    }
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list,size));
    }
}