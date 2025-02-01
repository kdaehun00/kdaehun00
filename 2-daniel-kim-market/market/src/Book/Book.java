package Book;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private double price;

    // 생성자
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter와 Setter
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "제목: " + title + ", 저자: " + author + ", 가격: " + price + "원";
    }
}
