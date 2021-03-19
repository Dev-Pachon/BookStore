
package model;

public class Shelv {
    private String id;
    private int books;

    public Shelv(String id, int books) {
        this.id = id;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }
}
