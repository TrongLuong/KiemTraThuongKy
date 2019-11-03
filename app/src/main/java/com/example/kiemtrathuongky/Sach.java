package com.example.kiemtrathuongky;

public class Sach {
    private int id;
    private String title;
    private int ida;

    public Sach() {
    }

    public Sach(int id, String title, int ida) {
        this.id = id;
        this.title = title;
        this.ida = ida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }
}
