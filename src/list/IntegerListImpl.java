package list;

import exceptions.ElementNotFoundException;
import exceptions.ListNotFoundException;
import exceptions.NonexistentIndexException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] integers;
    private int size;


    public IntegerListImpl(int size) {
        if (size > 0) {
            this.integers = new Integer[size];
        } else if (size == 0) {
            this.integers = new Integer[10];
        } else {
            throw new IllegalArgumentException("" + size);
        }
    }


    @Override
    public Integer add(Integer item) {
        grow();
        integers[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkIndex(index);
        grow();
        Integer[] temp = Arrays.copyOf(integers, size);
        System.arraycopy(temp, 0, integers, 0, index);
        integers[index] = item;
        System.arraycopy(temp, index, integers, index + 1, temp.length - index);
        size++;
        return item;
    }

    private void grow() {
        if (integers[integers.length - 1] != null) {
            integers = Arrays.copyOf(integers, integers.length * 3 / 2);
        }
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndex(index);
        integers[index] = item;
        return item;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new NonexistentIndexException("Индекс не существует в листе");
        }
    }

    @Override
    public Integer remove(Integer item) {
        if (!contains(item)) {
            throw new ElementNotFoundException("Элемент  не найден");
        }
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] != null && integers[i].equals(item)) {
                System.arraycopy(integers, i + 1, integers, i, integers.length - i - 1);
                size--;
            }
        }
        return item;
    }


    @Override
    public Integer remove(int index) {
        checkIndex(index);
        if (integers[index] == null) {
            throw new ElementNotFoundException("Элемент по индексу не найден");
        }
        Integer result = integers[index];
        System.arraycopy(integers, index + 1, integers, index, integers.length - index - 1);
        size--;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        quickSort(0, size - 1);
        return containsBinary(item);
    }

    private boolean containsBinary(int item) {
        int min = 0;
        int max = integers.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == integers[mid]) {
                return true;
            }

            if (item < integers[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] != null && integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = integers.length - 1; i >= 0; i--) {
            if (integers[i] != null && integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        if (integers[index] == null) {
            throw new ElementNotFoundException("Элемент не существует");
        }
        return integers[index];
    }

    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new ListNotFoundException();
        }
        if (this.size() != otherList.size()) return false;
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
        int length = integers.length;
        integers = new Integer[length];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(integers, size));
    }

    public void sortBubble() {
        for (int i = 0; i < integers.length - 1; i++) {
            for (int j = 0; j < integers.length - 1 - i; j++) {
                if (integers[j] > integers[j + 1]) {
                    swapElements(integers, j, j + 1);
                }
            }
        }
    }

    public void sortSelection() {
        for (int i = 0; i < integers.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < integers.length; j++) {
                if (integers[j] < integers[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(integers, i, minElementIndex);
        }
    }

    public void sortInsertion() {
        for (int i = 1; i < integers.length; i++) {
            int temp = integers[i];
            int j = i;
            while (j > 0 && integers[j - 1] >= temp) {
                integers[j] = integers[j - 1];
                j--;
            }
            integers[j] = temp;
        }
    }


    private void swapElements(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quickSort(int low, int high) {
        if (integers.length == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        int el = integers[middle];
        int i = low, j = high;
        while (i <= j) {
            while (integers[i] < el) {
                i++;
            }
            while (integers[j] > el) {
                j--;
            }
            if (i <= j) {
                int temp = integers[i];
                integers[i] = integers[j];
                integers[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(low, j);
        if (high > i)
            quickSort(i, high);
    }
}