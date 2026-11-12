package ui_Dang_dang_sua;

import Attendance.AttendanceManager;
import Person.PersonManager;

public class UserAdminMenu extends MenuUserLv00 {

    private final AttendanceManager attendanceManager =
            AttendanceManager.getInstance();

    @Override
    public void displayOption() {
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display your attendance history");
        System.out.println("    [5] Display Account management");

    }

    @Override
    public void chooseOption(int choice) {
        ManagementMenu management = new ManagementMenu();

        switch (choice) {
            case 1:
                new PersonManager().displayPersonInformation(currentUser);
                Draft.displayMenuLine();
                Draft.pause();
                break;
            case 2:
                attendanceManager.checkInFromInput(activeZone);
                Draft.pause();
                break;
            case 3:
                attendanceManager.checkOutFromInput();
                Draft.pause();
                break;
            case 4:
                attendanceManager.History(currentUser.getId());
                Draft.pause();
                break;
            case 5:
                management.displayAccountManagementMenu();
                break;
            default:
                System.out.println("Invalid choice. Please select again.");
        }

        
    }
}