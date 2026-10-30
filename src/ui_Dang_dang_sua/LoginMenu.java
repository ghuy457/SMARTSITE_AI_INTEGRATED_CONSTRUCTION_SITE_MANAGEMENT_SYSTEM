
   package ui_Dang_dang_sua;

import java.util.Scanner;
import Person.Person;
import Person.PersonManager;
import java.io.IOException;
import Validatable.CheckPassword;
import Validatable.InputScanner;
import Zone.Zone;
import Zone.ZoneManager2;
import static ui_Dang_dang_sua.Draft.clearConsoleIDE;
import static ui_Dang_dang_sua.Draft.displayMenuGreeting;
import static ui_Dang_dang_sua.Draft.displayMenuHeader;
import static ui_Dang_dang_sua.Draft.displayMenuLine;
   
public class LoginMenu {
    private PersonManager pm = PersonManager.getInstance(); // Khai bao them doi tuong pm moi
    private final CheckPassword checker = new CheckPassword();
    private boolean loadPeople;
    private static boolean peopleLoaded;
     ZoneManager2 zm = ZoneManager2.getInstance();
     Zone activeZone = zm.getRandomActiveZone();

    // Constructor mặc định tự khởi tạo Manager nếu chưa có
    public LoginMenu() {
        this.pm = PersonManager.getInstance(); 
        this.loadPeople = true;
    }
    public LoginMenu(PersonManager pm) {
        this.pm = pm;
    }  
    
    public void displayLoginMenu() {
        // Đọc dữ liệu trước khi nhận thông tin đăng nhập.
        try {
            if (loadPeople && !peopleLoaded) {
                pm.loadFromFile("data/Person.txt");
            }
            checker.loadFromFile("data/accounts.txt");
        } catch (IOException e) {
            System.out.println(Draft.center("[!] Cannot load login data: " + e.getMessage()));
            return;
        }
        
        Scanner sc = InputScanner.getScanner();
        boolean running = true;
        while (running){
            displayMenuLine();//in gạch
            displayMenuHeader();//in banner
            displayMenuLine();//in gạch
            displayMenuGreeting();//in lời chào
             if (activeZone != null) {
                System.out.println(Draft.center("CURRENT LOCATION: " + activeZone.getZoneName() + " (" + activeZone.getZoneId() + ")"));
            } else {
                System.out.println(Draft.center("CURRENT LOCATION: No active zone assigned"));
            }
            displayMenuLine();//in gạch
            System.out.println("");
            System.out.println(Draft.center("[!] NOTICE: Please enter your code and password below!!!"));
            System.out.println("");
            // String code 
            System.out.print("     >>> Enter your code: ");
            if (!sc.hasNextLine()) return;
            String code = sc.nextLine().trim();

            /* if (!CheckPassword.checrekCode(code)) {
            System.out.println("[!]Invalid code format");
            continue;
            }*/
           
            System.out.print("     >>> Enter your password: ");
            if (!sc.hasNextLine()) return;
            String password = sc.nextLine();
            if (!checker.check(code, password)) {
           System.out.println(Draft.center(Draft.RED + "Login fail!" + Draft.BAS));
           Draft.pause();
           Draft.clearConsoleIDE();
           continue;
        } 
           Person currentUser = pm.findPersonByCode(code);

              if (currentUser == null) {
                System.out.println(Draft.center(Draft.RED+"Login fail!"+Draft.BAS));
                Draft.pause();
                Draft.clearConsoleIDE();
                continue;
            }


            System.out.println(Draft.center(Draft.GRN+"[+] Login successful!"+Draft.BAS));
            System.out.println(Draft.center("[+] Welcome, " +Draft.BLU+ currentUser.getFullName() +Draft.BAS+ " [" + currentUser.getRole() + "]"));
            System.out.println("");
            Draft.displayMenuLine();
            Draft.pause();
            Draft.clearConsoleIDE();
            
            String role = currentUser.getRole();
            if (role == null) {
                System.out.println(Draft.center(Draft.RED+"[!] Account has no role assigned."+Draft.BAS));
                return;
            }
        // Điều hướng Menu theo Role
            switch (role.trim().toUpperCase()) {
                case "SITE_MANAGER":
                    clearConsoleIDE();
                    TempSession.setCurrentUser(currentUser); // Luu gia tri nguoi dung vao de sai cho viec checkin
                                                               //checkout
                    new UserSiteManagerMenu().displayMenuLv(currentUser);
                    TempSession.clear();
                    running = false;
                    break;
                case "SAFETY_OFFICER":
                    clearConsoleIDE();
                    TempSession.setCurrentUser(currentUser);
                    new UserSafetyOfficerMenu().displayMenuLv(currentUser);
                    TempSession.clear();
                    running = false;
                    break;
                case "WORKER":
                    clearConsoleIDE();
                    TempSession.setCurrentUser(currentUser);
                    new UserWorkerMenu().displayMenuLv(currentUser);
                    running = false;
                    break;
                case "CONTRACTOR":
                    clearConsoleIDE();
                    TempSession.setCurrentUser(currentUser);
                    new UserContractorMenu().displayMenuLv(currentUser);
                    TempSession.clear();
                    running = false;
                    break;
                case "VISITOR":
                    clearConsoleIDE();
                    TempSession.setCurrentUser(currentUser);
                    new UserVisitorMenu().displayMenuLv(currentUser);
                     TempSession.clear();
                    running = false;
                    break;
                default:
                    System.out.println(Draft.center(Draft.RED+"[!] Unknown role: " + role + Draft.BAS));
                    Draft.pause();
            }
        }//while running for menu
    }//displayLoginMenu
    
}//class
