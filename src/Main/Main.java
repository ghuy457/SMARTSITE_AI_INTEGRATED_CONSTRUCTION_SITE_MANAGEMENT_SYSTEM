package Main;

import java.io.IOException;
import ui_Dang_dang_sua.WelcomeMenu;

/**
 *
 * @author Minh_Khang
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Attendance.AttendanceManager.getInstance().loadFromFile();
        WelcomeMenu welcomemenu = new WelcomeMenu(); //welcome.displayWelcomeMenu();
        welcomemenu.displayWelcomeMenu();
    }
}
