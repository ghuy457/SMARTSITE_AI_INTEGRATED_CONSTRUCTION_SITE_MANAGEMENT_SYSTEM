
package ui;

import java.util.Scanner;

public class Draft {
    
    public static void clearConsoleIDE() { //in dòng trống để làm mới menu (lụm trên Gemini)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    //displayWelcomeMenu(); hiển thị Welcome Menu
    
    //displayLoginMenu; hiển thị Login Menu
    
    //displayMenuLv(): hiển thị Menu chung chứa các chức năng
    //displayHeader(); hiển thị Header của Menu
    //displayOption(); hiển thị các lựa chọn tùy thuộc vào role
    //displayLogOut(); hiển thị chức năng Logout
    //chooseOption(); xử lý lựa chọn chức năng


    public static void pause() { // ngưng màn hình
        System.out.println("\nPress [ENTER] to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}

