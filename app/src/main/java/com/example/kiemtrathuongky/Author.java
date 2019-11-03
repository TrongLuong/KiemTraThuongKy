package com.example.kiemtrathuongky;

public class Author {
    private int ida;
    private String namea;

    public Author() {
    }

    public Author(int ida, String namea) {
        this.ida = ida;
        this.namea = namea;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getNamea() {
        return namea;
    }

    public void setNamea(String namea) {
        this.namea = namea;
    }
}
