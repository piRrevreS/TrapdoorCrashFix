package me.pirrevres.trapdoorcrashfix;

public class Int {
    private int integer;

    public Int() {
        this.integer = 0;
    }

    public Int(int integer) {
        this.integer = integer;
    }

    public int intValue() {
        return this.integer;
    }

    public void setValue(int integer) {
        this.integer = integer;
    }

    public Int add(int integer) {
        this.integer += integer;
        return this;
    }
}