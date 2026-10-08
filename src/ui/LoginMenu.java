
package ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoginMenu {
    public void displayLoginMenu(){
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("================================================================================");
            System.out.println("");
            System.out.println("      ___           ___           ___           ___           ___");
            System.out.println("     /\\  \\         /\\  \\         /\\  \\         /\\  \\         /\\  \\");
            System.out.println("    /::\\  \\       /::\\  \\       /::\\  \\       /::\\  \\       /::\\  \\");
            System.out.println("   /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\");
            System.out.println("  /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\");
            System.out.println(" /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\");
            System.out.println(" \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/");
            System.out.println("  \\:\\__\\        \\:\\__\\        \\:\\__\\        \\:\\__\\        \\:\\__\\");
            System.out.println("   \\/__/         \\/__/         \\/__/         \\/__/         \\/__/");
            System.out.println("");
            System.out.println("================================================================================");
            System.out.println("                         WELCOME TO THE SSMARTSITE");
            System.out.println("                      Version 1.0.0 | Terminal Edition");
            // Forrmateed HH/MM/SS: Giờ:Phút:Giây
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                LocalTime now = LocalTime.now();
                // In ghi đè lên dòng cũ bằng '\r' trên Console/Terminal
            System.out.println("\r                              Time: " + now.format(formatter));         
            System.out.println("================================================================================");
            System.out.println("");
            System.out.println("        [!] NOTICE: Please enter your user name and password below!!!");
            System.out.println("");
            System.out.println("   >>> Enter your username  ");
            String userName = sc.nextLine();
            System.out.println("   >>> Enter your password  ");
            String password = sc.nextLine();
            System.out.println("User name: " + userName + "; password: " + password);
            
            //String role = getRole(userName, password); //kiểm tra role để gọi menu tương ứng
            //getRole()
            String role = "";
            switch (userName){
                case ("Dang"):
                    role = "Site Manager";
                    break;
                case ("Khang"):
                    role = "Worker";
                    break;
                case ("Huy"):
                    role = "Visitor";
                    break;
                default:
                    System.out.println("Account not found!");
            }
            
            switch (role){
                case ("Visitor"):
                    MenuUserLv03 menuVisitor = new MenuUserLv03();
                    menuVisitor.displayMenuLv(role, userName);
                    running = false;
                    break;
                case ("Worker"):
                    MenuUserLv03 menuWorker = new MenuUserLv02();
                    menuWorker.displayMenuLv(role, userName);
                    running = false;
                    break;
                case ("Contractor"):
                    MenuUserLv02 menuContractor = new MenuUserLv02();
                    menuContractor.displayMenuLv(role, userName);
                    running = false;
                    break;
                case ("Safety Officier"):
                    MenuUserLv02 menuSafetyOfficer = new MenuUserLv02();
                    menuSafetyOfficer.displayMenuLv(role, userName);
                    running = false;
                    break;
                case ("Site Manager"):
                    MenuUserLv01 menuSiteManager = new MenuUserLv01();
                    menuSiteManager.displayMenuLv(role, userName);
                    running = false;
                    break;
            }//switch
        
        }//while(running)
    }//dislplayLoginMenu()
}//class
