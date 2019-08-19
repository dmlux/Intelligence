package de.intelligence.data;

public class Label<T> {

    private T value;

    public Label(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
