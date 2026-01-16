package LibraryManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main (String []  args){
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true){
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1.Add a new book");
            System.out.println("2.Show all books");
            System.out.println("3.Search for a book");
            System.out.println("4.Lend a book to a member");
            System.out.println("5.Return a book");
            System.out.println("6.Register a new member");
            System.out.println("7.Show all members");
            System.out.println("8.Exit Program");
            System.out.println("Please enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3:
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    library.searchBook(keyword);
                    break;
                case 4:
                    System.out.print("Enter Book ID to lend: ");
                    int issueId = scanner.nextInt();
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    library.lendedBook(issueId, memberId);
                    break;
                case 5:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                case 6:
                    System.out.print("Enter Member ID: ");
                    int mId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();
                    library.addMember(new Member(mId, name));
                    break;
                case 7:
                    library.displayMembers();
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default: System.out.println("Invalid choice."); }
            }
        }
    }

