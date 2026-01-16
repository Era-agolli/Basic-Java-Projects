package LibraryManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<Member> members;
    private final String booksFile = "books.dat";
    private final String memberFile = "members.dat";

    public Library(){
        books = new ArrayList<>();
        members = new ArrayList<>();
        loadData();
    }

    public void addBook(Book book){
        books.add(book);
        saveData();
    }

    public void displayBooks(){
        if (books.isEmpty()){
            System.out.println("No books in library.");
            return;
        }
        for (Book book : books)
            System.out.println(book);
    }

    public void searchBook(String keyword){
        boolean found = false;
        for (Book book : books){
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found)
            System.out.println("No matching books found.");
    }

    public void lendedBook(int bookId,int memberId){
        for (Book book : books){
            if (book.getId() == bookId && !book.isLended()){
                book.lendedBook(memberId);
                System.out.println("Book lended successfully to Member " + memberId);
                saveData();
                return;
            }
        }
        System.out.println("Book not found or already lended.");
    }

    public void returnBook(int bookId){
        for (Book book : books){
            if (book.getId() == bookId && book.isLended()){
                book.returnBook();
                System.out.println("Book returned successfully");
                saveData();
                return;
            }
        }
        System.out.println("Book not found or not lended.");
    }

    public void addMember(Member member){
        members.add(member);
        saveData();
    }

    public void displayMembers(){
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member member : members)
            System.out.println(member);
    }

    private void saveData(){
        try (
            ObjectOutputStream oosBooks = new ObjectOutputStream(new FileOutputStream(booksFile));
            ObjectOutputStream oosMembers = new ObjectOutputStream(new FileOutputStream(memberFile))) {
            oosBooks.writeObject(books);
            oosMembers.writeObject(members);
            }
        catch (IOException e){
                System.out.println("Error saving data: " + e.getMessage());

            }
        }


        @SuppressWarnings("unchecked")
    private void loadData(){
        try (
                ObjectInputStream oisBooks = new ObjectInputStream(new FileInputStream(booksFile));
                ObjectInputStream oisMembers = new ObjectInputStream(new FileInputStream(memberFile))) {
            books = (List<Book>) oisBooks.readObject();
            members = (List<Member>) oisMembers.readObject();

        }
        catch (Exception e) {
            books = new ArrayList<>();
            members = new ArrayList<>();
        }
        }
    }

