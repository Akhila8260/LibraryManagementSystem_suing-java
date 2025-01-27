package librarymanagementsystem;

		
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

		class Book {
		    String id, name, author;
		    boolean isIssued;
		    //parameterize constructor
		    Book(String id, String name, String author) {
		        this.id = id;
		        this.name = name;
		        this.author = author;
		        this.isIssued = false;
		    }
		}

		class User {
		    String username, password;
		    boolean isAdmin;

		    User(String username, String password, boolean isAdmin) {
		        this.username = username;
		        this.password = password;
		        this.isAdmin = isAdmin;
		    }
		}

		class Library {
		    ArrayList<Book> books = new ArrayList<>();
		    HashMap<String, User> users = new HashMap<>();
		    Scanner scanner = new Scanner(System.in);

		    Library() {
		        // Adding a default admin user
		        users.put("admin", new User("admin", "admin12", true));
		    }

		    void addBook() {
		        System.out.print("Enter Book ID: ");
		        String id = scanner.nextLine();
		        System.out.print("Enter Book Name: ");
		        String name = scanner.nextLine();
		        System.out.print("Enter Book Author: ");
		        String author = scanner.nextLine();
		        books.add(new Book(id, name, author));
		        System.out.println("Book added successfully!");
		    }

		    void issueBook() {
		        System.out.print("Enter Book ID to issue: ");
		        String id = scanner.nextLine();
		        for (Book book : books) {
		            if (book.id.equals(id) && !book.isIssued) {
		                book.isIssued = true;
		                System.out.println("Book issued successfully!");
		                return;
		            }
		        }
		        System.out.println("Book not available for issue!");
		    }

		    void returnBook() {
		        System.out.print("Enter Book ID to return: ");
		        String id = scanner.nextLine();
		        for (Book book : books) {
		            if (book.id.equals(id) && book.isIssued) {
		                book.isIssued = false;
		                System.out.println("Book returned successfully!");
		                return;
		            }
		        }
		        System.out.println("Book not issued or invalid ID!");
		    }

		    void viewBooks() {
		        System.out.println("Available Books:");
		        for (Book book : books) {
		            if (!book.isIssued) {
		                System.out.println(book.id + " - " + book.name + " by " + book.author);
		            }
		        }
		    }

		    void viewIssuedBooks() {
		        System.out.println("Issued Books:");
		        for (Book book : books) {
		            if (book.isIssued) {
		                System.out.println(book.id + " - " + book.name + " by " + book.author);
		            }
		        }
		    }

		    void addUser() {
		        System.out.print("Enter Username: ");
		        String username = scanner.nextLine();
		        System.out.print("Enter Password: ");
		        String password = scanner.nextLine();
		        System.out.print("Is Admin (true/false): ");
		        boolean isAdmin = scanner.nextBoolean();
		        scanner.nextLine(); // consume newline
		        users.put(username, new User(username, password, isAdmin));
		        System.out.println("User added successfully!");
		    }

		    boolean authenticateUser(String username, String password) {
		        User user = users.get(username);
		        return user != null && user.password.equals(password);
		    }

		    boolean isAdmin(String username) {
		        User user = users.get(username);
		        return user != null && user.isAdmin;
		    }
		}

		public class LibraryManagementSystem {
		    public static void main(String[] args) {
		        Library library = new Library();
		        Scanner scanner = new Scanner(System.in);
		        String username, password;
		        
		        // Authentication
		        System.out.print("Enter Username: ");
		        username = scanner.nextLine();
		        System.out.print("Enter Password: ");
		        password = scanner.nextLine();

		        if (!library.authenticateUser(username, password)) {
		            System.out.println("Invalid credentials!");
		            return;
		        }

		        int choice;
		        do {
		            System.out.println("\nLibrary Management System");
		            System.out.println("1. Add Book");
		            System.out.println("2. Issue Book");
		            System.out.println("3. Return Book");
		            System.out.println("4. View Available Books");
		            System.out.println("5. View Issued Books");
		            if (library.isAdmin(username)) {
		                System.out.println("6. Add User");
		            }
		            System.out.println("0. Exit");
		            System.out.print("Enter your choice: ");
		            choice = scanner.nextInt();
		            scanner.nextLine(); // consume newline

		            switch (choice) {
		                case 1:
		                    library.addBook();
		                    break;
		                case 2:
		                    library.issueBook();
		                    break;
		                case 3:
		                    library.returnBook();
		                    break;
		                case 4:
		                    library.viewBooks();
		                    break;
		                case 5:
		                    library.viewIssuedBooks();
		                    break;
		                case 6:
		                    if (library.isAdmin(username)) {
		                        library.addUser();
		                    } else {
		                        System.out.println("Invalid choice!");
		                    }
		                    break;
		                case 0:
		                    System.out.println("Exiting system.");
		                    break;
		                default:
		                    System.out.println("Invalid choice! Try again.");
		            }
		        } while (choice != 0);
		    }
		}

	


