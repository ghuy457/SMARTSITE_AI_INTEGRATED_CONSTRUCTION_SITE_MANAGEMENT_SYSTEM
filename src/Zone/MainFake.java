package Zone;

import java.io.IOException;
import java.util.Scanner;

public class MainFake {

    public static void main(String[] args) {
        // Khởi tạo ZoneManager2 (Tự động gọi loadFromFile trong Constructor)
        ZoneManager2 zm = new ZoneManager2();

        System.out.println("==================================================");
        System.out.println("   TEST PROGRAM: RANDOM ZONE CHECK-IN SYSTEM");
        System.out.println("==================================================");

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== ZONE MANAGER TEST MENU =====");
            System.out.println("[1] Display All Loaded Zones (displayList)");
            System.out.println("[2] RANDOM Check-in Zone (assignRandomActiveZone)");
            System.out.println("[3] View Current Area Information (View activeDemoZone)");
            System.out.println("[4] Sort Zone List by ID (sortListById)");
            System.out.println("[5] SAVE Data to File (saveToFile)");
            System.out.println("[6] RELOAD Data from File (loadFromFile)");
            System.out.println("[0] Exit Program");
            System.out.print("➔ Choose option [0-6]: ");

            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    System.out.println("\n--- [TEST]: Display All Loaded Zones ---");
                    zm.displayList();
                    break;

                case "2":
                    System.out.println("\n--- [TEST]: Simulating Sign In & Random Check-in ---");
                    Zone randomZone = zm.getRandomActiveZone();
                    if (randomZone != null) {
                        System.out.println("[SUCCESS] System randomly assigned you to a Zone!");
                        System.out.println("➔ Checked-in ZoneID   : " + randomZone.getZoneId());
                        System.out.println("➔ Checked-in ZoneName : " + randomZone.getZoneName());
                        System.out.println("➔ Allowed Role       : " + randomZone.getAllowedRole());
                  
                          System.out.println("➔ Is Restricted      : " + (randomZone.isRestricted() ? "YES" : "NO"));
                        
                        if (randomZone instanceof RestrictedZone) {
                            RestrictedZone rz = (RestrictedZone) randomZone;
                            System.out.println("➔ Security Passcode  : " + rz.getPinCode());
                        }
                    } else {
                        System.out.println("[!] Check-in failed. Zone list is empty!");
                    }
                    break;

                case "3":
                    System.out.println("\n--- [TEST]: View Current Area Information ---");
                    Zone current = zm.getActiveZone();
                    if (current != null) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("         CURRENT LOCATION INFORMATION");
                        System.out.println("--------------------------------------------------");
                        System.out.println("Zone ID           : " + current.getZoneId());
                        System.out.println("Zone Name         : " + current.getZoneName());
                        System.out.println("Details           : " + current.getZoneDetails());
                        System.out.println("Allowed Role      : " + current.getAllowedRole());
                        System.out.println("Is Restricted     : " + (current.isRestricted() ? "YES" : "NO"));
                        if (current instanceof RestrictedZone) {
                            RestrictedZone rz = (RestrictedZone) current;
                            System.out.println("Pin Code          : " + rz.getPinCode());
                        }
                        System.out.println("--------------------------------------------------");
                    } else {
                        System.out.println("[!] You are not checked in any zone yet. Please choose Option [2] first!");
                    }
                    break;

                case "4":
                    System.out.println("\n--- [TEST]: Sort List by Zone ID ---");
                    zm.sortZonesById();
                    zm.displayList();
                    break;

                case "5":
                    System.out.println("\n--- [TEST]: Save Data to File ---");
                    zm.saveToFile();
                    break;

                case "6":
                    System.out.println("\n--- [TEST]: Reload Data from File ---");
                    try {
                        zm.loadFromFile("data/Zone_fake.txt");
                    } catch (IOException e) {
                        System.out.println("[Error]: Failed to reload file: " + e.getMessage());
                    }
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting test program. Goodbye!");
                    break;

                default:
                    System.out.println("[Error]: Invalid option!");
                    break;
            }
        }
        sc.close();
    }
}