import java.util.Scanner;
import Book.*;
import User.*;

public class main {
    private static Scanner sc = new Scanner(System.in);
    private static UserManager userManager = new UserManager(); // UserManager 인스턴스 생성
    private static BookManager manager = new BookManager();
    public static void main(String[] args) {
        while (true) {
            System.out.println("[1] 로그인 [2] 회원가입 [3] 종료"); // 화면 구성
            System.out.print("메뉴를 선택하세요: ");
            int choice = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    boolean isLoginSuccessful = userManager.login(); // 인스턴스를 사용하여 메서드 호출
                    if(isLoginSuccessful){
                        manager.mainMenu();
                    }
                    break;
                case 2:
                    userManager.register(); // 인스턴스를 사용하여 메서드 호출
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 선택이 아닙니다.\n");
                    break;
            }
        }
    }
}
