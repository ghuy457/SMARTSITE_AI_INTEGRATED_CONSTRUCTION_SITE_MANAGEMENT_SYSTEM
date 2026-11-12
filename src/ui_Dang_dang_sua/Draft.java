package ui_Dang_dang_sua;
import Zone.RestrictedZone;
import Zone.Zone;
import Zone.ZoneManager2;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Draft {

    public static final String RED = "\u001B[31m";
    public static final String GRN = "\u001B[32m";
    public static final String BLU = "\u001B[34m";
    public static final String BAS = "\u001B[0m";
    public static final int CONSOLE_WIDTH = 120;

    //làm mới trang bằng cách in dòng mới
    public static void clearConsoleIDE() { //in dòng trống để làm mới menu (lụm trên Gemini)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    //làm mới màn hình (Gehihi gợi ý)
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pause() {
        Scanner scanner = new Scanner(System.in);
        Draft.displayMenuLine();
        System.out.println(Draft.center("Press [ENTER] to continue..."));
        Draft.displayMenuLine();
        scanner.nextLine();
        Draft.clearConsoleIDE();
    }

    public static void functionIsNotCompletedYet() {
        System.out.println(Draft.center(Draft.RED + "Function is not completed yet!" + Draft.BAS));
        Draft.pause();
        Draft.clearConsoleIDE();
    }

    ;
    
    
    
    //in đường kẻ ngang
    public static void displayMenuLine() {
        System.out.println("========================================================================================================================");
    }

    //hiển thị banner (có màu)
    public static void displayMenuHeader() {

        String header
                = RED + "_  __                                       " + BAS + "" + BLU + "  ____                                  " + BAS + "" + GRN + " _   _             \n"
                + GRN + "  | |/ /  _ __     __ _   _ __     __ _         " + BAS + "" + RED + "|  _ \\    __ _   _ __     __ _         " + BAS + "" + BLU + "| | | |  _   _  __  __\n"
                + BLU + "  | ' /  | '_ \\   / _` | | '_ \\   / _` |        " + BAS + "" + GRN + "| | | |  / _` | | '_ \\   / _` |        " + BAS + "" + RED + "| |_| | | | | | \\ \\/ /\n"
                + RED + "  | . \\  | | | | | (_| | | | | | | (_| |        " + BAS + "" + BLU + "| |_| | | (_| | | | | | | (_| |        " + BAS + "" + GRN + "|  _  | | |_| |   / / \n"
                + GRN + "|_|\\_\\ |_| |_|  \\__,_| |_| |_|  \\__, |        " + BAS + "" + RED + "|____/   \\__,_| |_| |_|  \\__, |        " + BAS + "" + BLU + "|_| |_|  \\__,_|  /_/\n"
                + BLU + "                                  |___/              " + BAS + "" + GRN + "                    |___/                " + BAS + "" + RED + "              \n" + BAS;
        printCenteredBlock(header);
    }


    //hiển thị lời chào
    public static void displayMenuGreeting() {
        System.out.println(Draft.center("WELCOME TO THE S-SMARTSITE"));
        System.out.println(Draft.center("Version 3.6.9 | Limited Edition")); //Version Semester.Lab.TimeConfig
        // Forrmateed HH/MM/SS: Giờ:Phút:Giây
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime now = LocalTime.now();
        // In ghi đè lên dòng cũ bằng '\r' trên Console/Terminal
        System.out.println(Draft.center("Time: " + now.format(formatter)));
    }

    public static void displayWelcomeMenuOption() {
        System.out.println("");
        String option
                = "[!] NOTICE: The system is ready to work. Please choose one of the option below\n"
                + "\n"
                + "+-------------------------------------------------+\n"
                + "| 1. Sign in                                      |\n"
                + "| 2. View area information                        |\n"
                + "| 3. Exit                                         |\n"
                + "+-------------------------------------------------+\n";
        printCenteredBlock(option);
    }

    private static int getVisibleLength(String text) {
        if (text == null) {
            return 0;
        }
        String plainText = text.replaceAll("\\u001B\\[[;\\d]*m", "");
        return plainText.length();
    }

    //lấy giữa 1 dòng
    public static String center(String text) {
        if (text == null) {
            return "";
        }
        int visibleLen = getVisibleLength(text);
        if (visibleLen >= CONSOLE_WIDTH) {
            return text;
        }

        int padding = (CONSOLE_WIDTH - visibleLen) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            sb.append(" ");
        }
        sb.append(text);
        return sb.toString();
    }

    //lấy giữa n dòng
    public static void printCenteredBlock(String block) {
        if (block == null) {
            return;
        }
        String[] lines = block.split("\n");
        for (String line : lines) {
            System.out.println(center(line));
        }
    }

    public static void displayProfile(String ID, String Name, String Role, String Status) {
        System.out.println("  ┌────────────────────────────────────────────────────────┐");
        System.out.printf("  │  🆔  ID     : %-40s │%n" + ID);
        System.out.printf("  │  👤  Name   : %-40s │%n" + Name);
        System.out.printf("  │  💼  Role   : %-40s │%n" + Role);
        System.out.printf("  │  🟢  Status : %-33s │%n" + Status);
        System.out.println("  └────────────────────────────────────────────────────────┘");
    }

    public static void displayBannerRES(Zone activeZone) {
        Scanner sc = Validatable.InputScanner.getScanner();

        RestrictedZone rz = (RestrictedZone) activeZone;
        Draft.displayMenuLine();
        System.out.println(Draft.center(Draft.RED + "[!] SECURITY CHECKPOINT: RESTRICTED ZONE DETECTED!" + Draft.BAS));
        System.out.println(Draft.center("Location: " + rz.getZoneName() + " (" + rz.getZoneId() + ")"));
        System.out.println(Draft.center("Enter PIN Code to proceed."));
        Draft.displayMenuLine();
        int countWrong = 0;

        while (true) {
            System.out.println("");
            System.out.print("    >>> Enter Restricted Zone PIN [ABCxxx] : ");
            String inputPin = sc.nextLine().trim();
            System.out.println("");

            if (rz.getPinCode().equals(inputPin)) {
                System.out.println(Draft.center(Draft.GRN + "[+] Access Granted to Welcome Menu!" + Draft.BAS));

                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            } else {
                System.out.println("");
                System.out.println(Draft.center(Draft.RED + "[Error]: Incorrect PIN Code! Please try again." + Draft.BAS));
                countWrong++;
                System.out.println("");
                System.out.println(Draft.center(Draft.RED + "You have entered the wrong PIN " + countWrong + " times." + Draft.BAS));
                System.out.println(Draft.center(Draft.RED + "When you entered wrong 3 times, the system will end ! \n" + Draft.BAS));
                System.out.println("");
                Draft.displayMenuLine();
                if (countWrong >= 3) {
                    System.out.println("");
                    System.out.println(Draft.center(Draft.RED + "[!] You have entered the wrong PIN 3 times. Access denied! Exiting system..." + Draft.BAS));
                    System.exit(0);
                }
            }
            System.out.println("");
        }
    }

 

    public static void displayIncidentInProgress(Zone activeZone) {
        System.out.println(Draft.center(Draft.RED + "[!] SECURITY CHECKPOINT: Zone [" + activeZone.getZoneId() + "] currently has an IN_PROGRESS incident!" + Draft.BAS));
    }
    
    public static void displayBanner() {
        Draft.displayMenuLine();//in gạch
        Draft.displayMenuHeader();//in banner
        Draft.displayMenuLine();//in gạch
        Draft.displayMenuGreeting();//in lời chào
        Draft.displayMenuLine();//in gạch    
    }

    public static void displayCurrentZone(Zone activeZone) {
        Draft.displayMenuLine();
        if (activeZone != null) {
            System.out.println(Draft.center("CURRENT LOCATION: " + activeZone.getZoneName() + " (" + activeZone.getZoneId() + ")"));
        } else {
            System.out.println(Draft.center("CURRENT LOCATION: No active zone assigned"));
        }
        Draft.displayMenuLine();
    }

    public static int inputAction(Scanner sc) {
        Draft.displayWelcomeMenuOption();
        System.out.println("");
        System.out.print("   >>> Enter your action[1-3]: ");

        //nhập và kiểm tra phải số không
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
            Draft.displayMenuLine();
        } catch (NumberFormatException e) {
            // Nếu nhập chữ hoặc ký tự, bắt lỗi ở đây
            System.out.println(Draft.center(Draft.RED + "Please enter a valid number [1-3]!" + Draft.BAS));
            Draft.displayMenuLine();
            Draft.pause();
        }
        return choice;
    }

    public static void doAction(int choice, ZoneManager2 zm, boolean running) {
        switch (choice) {
            case (1):
                Draft.clearConsoleIDE();
                LoginMenu menuLogin = new LoginMenu();
                menuLogin.displayLoginMenu();
                break;
            case (2):
                System.out.println("");
                System.out.println(Draft.center("CURRENT LOCATION INFORMATION"));
                System.out.println("");
                System.out.println("");

                Zone current = zm.getActiveZone();

                if (current != null) {
                    System.out.println(Draft.center("Zone ID: " + current.getZoneId()));
                    System.out.println(Draft.center("Zone Name : " + current.getZoneName()));
                    System.out.println(Draft.center("Type : " + (current.isRestricted() ? Draft.RED + "Restricted Zone" + Draft.BAS : Draft.GRN + "Normal Zone" + Draft.BAS)));
                } else {
                    System.out.println(Draft.center(Draft.RED + "No active zone assigned at the moment!" + Draft.BAS));
                }

                System.out.println();
                System.out.println("");
                Draft.pause();
                break;
            case (3):
                //turnOff();
                System.out.println(Draft.center("See you later!"));
                running = false;
                Draft.pause();
                System.exit(0);
                break;
            default:
                System.out.println(Draft.center(Draft.RED + "Invalid choice!!!" + Draft.BAS));
                Draft.pause();
                break;
        }//switch-case choice
    }

}
