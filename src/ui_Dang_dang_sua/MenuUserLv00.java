package ui_Dang_dang_sua;

import java.util.Scanner;
import Person.Person;
import Zone.Zone;
import Zone.ZoneManager2;

public abstract class MenuUserLv00 {

    // Khai báo protected để tất cả các lớp con (Lv01, Lv02, Lv03) đều dùng chung được
    protected Person currentUser;
    ZoneManager2 zm = ZoneManager2.getInstance();
    Zone activeZone = zm.getRandomActiveZone();

    public void displayMenuLv(Person currentUser) {
        // Lưu lại dữ liệu truyền vào
        this.currentUser = currentUser;

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayHeader();                            // Hiển thị lời chào và role
            displayOption();                            // Hiển thị danh sách chức năng (lớp con tự định nghĩa)
            System.out.println("");
            displayLogOut();                            // Hiển thị: 0. Log out

            System.out.print(Draft.BLU + "    ➔ Please enter your action : " + Draft.BAS);
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                if (!sc.hasNextLine()) {
                    return;
                }
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED + "Please enter a valid number!" + Draft.BAS));
                Draft.pause();
                continue;
            }

            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println("    ➔ The system is logging out...");
                Draft.pause();
            } else {
                chooseOption(choice);                   // Xử lý chức năng đã chọn
            }
        } // while
    } // displayMenuLv

    public void displayHeader() {
        String userRole = currentUser.getRole();

        Draft.displayMenuLine();
        Draft.displayMenuHeader();
        Draft.displayMenuLine();
        Draft.displayMenuGreeting();
        if (activeZone != null) {
            System.out.println(Draft.center("CURRENT LOCATION: " + activeZone.getZoneName() + " (" + activeZone.getZoneId() + ")"));
        } else {
            System.out.println(Draft.center("CURRENT LOCATION: No active zone assigned"));
        }
        Draft.displayMenuLine();
        System.out.println("");
        System.out.println(Draft.center(userRole + " Menu "));
        System.out.println("");
    }

    abstract void displayOption();

    public void displayLogOut() {
        System.out.println("    [0] Log out");
    }

    abstract void chooseOption(int choice);

} // class
