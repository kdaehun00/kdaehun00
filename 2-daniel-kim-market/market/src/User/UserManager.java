package User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private static final String INFO_FILE = "users.txt";
    private List<User> users;
    private Scanner sc = new Scanner(System.in);

    public UserManager() {
        this.users = load(); // 사용자 정보 로드
    }

    public void register() {
        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();

        String id;
        while (true) {
            System.out.print("사용하실 아이디를 입력하세요: ");
            id = sc.nextLine();
            if (isIDAvailable(id)) {
                System.out.println("사용 가능한 아이디입니다!\n");
                break;
            } else {
                System.out.println("이미 사용 중인 아이디입니다.");
            }
        }

        String pw;
        while (true) {
            System.out.print("사용하실 비밀번호를 입력하세요 (8자 이상, 대문자 1개 이상 포함): ");
            pw = sc.nextLine();
            if (!isPWAvailable(pw)) {
                System.out.println("비밀번호 조건을 충족하지 않습니다.");
                continue;
            }

            System.out.print("비밀번호 확인: ");
            String confirmPW = sc.nextLine();
            if (pw.equals(confirmPW)) {
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.\n");
            }
        }

        save(name, id, pw);
        System.out.println("회원가입이 완료되었습니다.");
    }

    private static boolean isIDAvailable(String id) {
        List<User> users = load();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPWAvailable(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
                break;
            }
        }
        return hasUpperCase;
    }

    public boolean login() {
        System.out.print("아이디를 입력하세요: ");
        String id = sc.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String pw = sc.nextLine();

        List<User> users = load();
        for (User user : users) {
            if (user.getId().equals(id) && user.getPw().equals(pw)) {
                System.out.printf("%s 님 환영합니다!\n", user.getName());
                return true;
            }
        }

        System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
        return false;
    }

    private static void save(String name, String id, String pw) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INFO_FILE, true))) {
            writer.write(name + "," + id + "," + pw);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("사용자 정보를 저장하는 동안 오류가 발생했습니다.");
        }
    }

    private static List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INFO_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], parts[2]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("저장된 사용자 정보가 없습니다.");
        } catch (IOException e) {
            System.out.println("사용자 정보를 불러오는 동안 오류가 발생했습니다.");
        }
        return users;
    }
}
