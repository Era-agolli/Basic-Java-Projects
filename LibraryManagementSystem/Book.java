package LibraryManagementSystem;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private boolean isLended;
    private int lendedToMemberId;

    public Book(int id,String title,String author){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isLended = false;
        this.lendedToMemberId = -1;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public boolean isLended(){
        return isLended;
    }

    public int getLendedToMemberId(){
        return lendedToMemberId;
    }

    public void lendedBook(int memberId){
        isLended = true;
        lendedToMemberId = memberId;
    }

    public void returnBook(){
        isLended = false;
        lendedToMemberId = -1;
    }

    @Override
    public String toString(){
        return id + " | " + title + " | " + author + " | " +
                (isLended ? "Lended to Member" + lendedToMemberId : "Available" );
    }
}
