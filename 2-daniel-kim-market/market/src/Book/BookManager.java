package Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {
    private static final String FILE_NAME = "books.txt"; // 파일 이름
    private ArrayList<Book> bookList; // 책 목록
    private Scanner scanner;

    // 생성자: 파일에서 데이터를 로드
    public BookManager() {
        bookList = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadBooks();
    }

    // 책 추가
    public void addBook() {
        System.out.println("책 제목을 입력하세요:");
        String title = scanner.nextLine();

        System.out.println("저자를 입력하세요:");
        String author = scanner.nextLine();

        System.out.println("가격을 입력하세요:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // 버퍼 비우기

        bookList.add(new Book(title, author, price));
        System.out.println("책이 추가되었습니다!");
        saveBooks();
    }

    // 책 목록 출력
    public void listBooks() {
        if (bookList.isEmpty()) {
            System.out.println("저장된 책이 없습니다.");
        } else {
            System.out.println("저장된 책 목록:");
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println((i + 1) + ". " + bookList.get(i));
            }
        }
    }

    // 책 삭제
    public void deleteBook() {
        System.out.println("삭제할 책 제목을 입력하세요:");
        String title = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equalsIgnoreCase(title)) {
                bookList.remove(i);
                System.out.println("책이 삭제되었습니다!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("해당 제목의 책이 없습니다.");
        }
        saveBooks();
    }

    // 책 데이터를 파일에 저장
    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bookList);
        } catch (IOException e) {
            System.out.println("책 데이터를 저장하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 책 데이터를 파일에서 불러오기
    @SuppressWarnings("unchecked")
    private void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            bookList = (ArrayList<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("기존 데이터 파일이 없습니다. 새로 생성합니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("책 데이터를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 메인 메뉴
    public void mainMenu() {
        while (true) {
            System.out.println("\n=== 책 관리 프로그램 ===");
            System.out.println("1. 책 추가");
            System.out.println("2. 책 목록 보기");
            System.out.println("3. 책 삭제");
            System.out.println("4. 종료");
            System.out.print("메뉴를 선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 번호를 입력하세요.");
            }
        }
    }

    // 메인 실행 메서드
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.mainMenu();
    }
}