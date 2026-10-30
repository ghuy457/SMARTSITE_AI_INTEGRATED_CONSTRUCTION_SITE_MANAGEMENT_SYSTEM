package Zone;

import Validatable.CheckId;
import Validatable.CheckName;
import Validatable.CheckDetails;
import Validatable.CheckCode;
import Validatable.InputScanner;
import java.util.ArrayList;
import java.util.Scanner;
//import ham load from file;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import ui_Dang_dang_sua.Draft;

public class ZoneManager2 {

    private ArrayList<Zone> list;
    private Zone activeZone;
    private static ZoneManager2 instance;

    public ZoneManager2() {
        this.list = new ArrayList<>();
        try {
            this.loadFromFile("data/Zone.txt");
            System.out.println("Load data successfully!");
        } catch (IOException e) {
            System.out.println("[Notice]: No existing data file found or failed to load. Starting with an empty list.");
        }
    }

    public static ZoneManager2 getInstance() {
        if (instance == null) {
            instance = new ZoneManager2();
        }
        return instance;
    }

    public Zone getRandomActiveZone() {
        if (list == null || list.isEmpty()) {
            System.out.println("[Warning]: Zone list is empty! Cannot select a random zone.");
            return null;
        }

        if (this.activeZone == null) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(list.size());
            this.activeZone = list.get(randomIndex);
        }

        return this.activeZone;
    }

    public Zone getActiveZone() {
        return activeZone;
    }

    public void addZone() {
        Scanner sc = Validatable.InputScanner.getScanner();
        int n;
        while (true) {
            System.out.print("    Input the number of zones needed to add: ");
            try {
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0) {
                    System.out.println("    [Error]: The number must be greater than 0!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("    [Error]: Invalid number input!");
                continue;
            }

            for (int i = 1; i <= n; i++) {
                System.out.println("\n                                                  --- Zone [" + i + "] ---");
                String id;

                while (true) {
                    id = CheckId.checkZoneId();
                    if (findZoneById(id) != null) {
                        System.out.println("                      [Error]: Zone ID [" + id + "] already exists! Try again.");
                        continue;
                    }
                    break;
                }

                String name = CheckName.checkZoneName();
                String description = CheckDetails.checkZoneDetails();

                String allowedRole = chooseAllowedRole();
                if (allowedRole == null) {
                    System.out.println("Đã hủy thao tác thêm Zone.");
                    return;
                }

                try {
                    Zone z = new Zone(false, id, name, description, allowedRole);

                    if (z.isValid()) {
                        this.list.add(z);
                        System.out.println("Add Zone [" + id + "] successfully!");
                    } else {
                        System.out.println("Zone data is invalid.");
                        i--;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("[Error]: " + e.getMessage());
                    i--;
                }
            }
            break;
        }
    }

    public Zone findZoneById(String zoneId) {
        for (Zone z : list) {
            if (z.getZoneId().equalsIgnoreCase(zoneId)) {
                return z;
            }
        }
        return null;
    }

    public void deleteZoneById() {
        Scanner scanner = InputScanner.getScanner();
        String zoneId = CheckId.checkZoneId();
        Zone z = this.findZoneById(zoneId);

        if (z == null) {
            System.out.println(Draft.center(Draft.RED + "Please enter the Zone ID!" + Draft.BAS));
            return;
        }

        System.out.print("    Are you sure you want to delete this Zone[" + z + "]? [Y/N]: ");
        String YN = scanner.nextLine().trim();
        do {
            switch (YN) {
                case "n":
                    System.out.println(Draft.center("Cancel deleting mod!"));
                    System.out.println(Draft.center("No one has been removed!"));
                    return;
                case "y":
                    list.remove(z);
                    System.out.println(Draft.center("Deleting sucessfully!"));
                    return;
                default:
                    System.out.println(Draft.center(Draft.RED + "Please enter a valid choice!" + Draft.BAS));
            }
        } while (true);
    }

    public void updateZoneById() {
        String zoneId = "    ➔ " + CheckId.checkZoneId();
        Zone z = this.findZoneById(zoneId);

        if (z != null) {
            System.out.println("    Current Zone Info: " + z);

            String newName = CheckName.checkZoneName();
            String newDescription = CheckDetails.checkZoneDetails();

            try {
                z.setZoneName(newName);
                z.setZoneDetails(newDescription);
                System.out.println(Draft.center(Draft.GRN + "Update Zone [" + zoneId + "] successfully!" + Draft.BAS));
            } catch (IllegalArgumentException e) {
                System.out.println(Draft.center(Draft.RED + "[Error]: Update failed! " + e.getMessage() + Draft.BAS));
            }
        } else {
            System.out.println(Draft.center(Draft.RED + "[Error]: Zone ID [" + zoneId + "] not found!" + Draft.BAS));
        }
    }

    public void displayList() {
        if (list.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "[ERROR] Zone list is empty now! Add more Zone(s)." + Draft.BAS));
            return;
        }
        System.out.println("");
        System.out.println(Draft.center("--- List of Zones ---"));
        System.out.println("");
        for (Zone z : this.list) {
            System.out.println(z);
        }
    }

    public void sortZonesById() {
        if (list == null || list.isEmpty()) {
            System.out.println("[Warning]: Zone list is empty. Nothing to sort.");
            return;
        }
        list.sort((z1, z2) -> z1.getZoneId().compareToIgnoreCase(z2.getZoneId()));

        System.out.println("-> Successfully sorted zone list by Zone ID!");
    }

    public void addRestrictedZone() {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            //System.out.print("Allowed role (WORKER/SAFETY_OFFICER/CONTRACTOR/VISITOR): ");
            System.out.println("Input the number of zones needed to add: ");
            try {
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0) {
                    System.out.println(Draft.center(Draft.RED + "[Error]: The number must be greater than 0!" + Draft.BAS));
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED + "[Error]: Invalid number input!" + Draft.BAS));
                continue;
            }

            for (int i = 1; i <= n; i++) {
                System.out.println(Draft.center("--- Restricted Zone [" + i + "] ---"));

                String id;
                while (true) {
                    id = CheckId.checkZoneId();
                    if (findZoneById(id) != null) {
                        System.out.println(Draft.center(Draft.RED + "[Error]: Zone ID [" + id + "] already exists! Try again!" + Draft.BAS));
                        continue;
                    }
                    break;
                }

                String name = CheckName.checkZoneName();
                String description = CheckDetails.checkZoneDetails();
                String pinCode = CheckCode.checkPinCode();

                String allowedRole = chooseAllowedRole();
                if (allowedRole == null) {
                    System.out.println("Đã hủy thao tác thêm Restricted Zone.");
                    return;
                }

                try {
                    RestrictedZone rz = new RestrictedZone(id, name, description, allowedRole, pinCode);

                    if (rz.isValid()) {
                        this.list.add(rz);
                        System.out.println("Restricted Zone [" + id + "] added successfully!");
                    } else {
                        System.out.println("Restricted Zone data is invalid.");
                        i--;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("[Error]: " + e.getMessage());
                    i--;
                }
            }
            break;
        }
    }

    public void displayRestrictedZonesOnly() {
        boolean found = false;
        System.out.println("\n                                          --- List of Restricted Zones ---");
        for (Zone z : this.list) {
            if (z.isAccessRestricted()) {
                System.out.println(z);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No restricted zones found.");
        }
    }

    public void loadFromFile(String fileName) throws IOException {
        ArrayList<Zone> loaded = new ArrayList<>();

        try ( BufferedReader reader = Files.newBufferedReader(
                Paths.get(fileName), StandardCharsets.UTF_8)) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s*\\|\\s*", -1);
                try {
                    Zone zone = null;
                    String type = parts[0].trim();

                    if ("ZONE".equalsIgnoreCase(type) && parts.length == 5) {
                        String id = parts[1].trim();
                        String name = parts[2].trim();
                        String details = parts[3].trim();
                        String allowedRole = parts[4].trim();

                        zone = new Zone(id, name, details, allowedRole);
                    } else if ("REST".equalsIgnoreCase(type) && parts.length == 6) {
                        String id = parts[1].trim();
                        String name = parts[2].trim();
                        String details = parts[3].trim();
                        String allowedRole = parts[4].trim();
                        String pinCode = parts[5].trim();

                        zone = new RestrictedZone(id, name, details, allowedRole, pinCode);
                    } else {
                        System.out.println("[Warning]: Skipped line " + lineNumber + " due to invalid column count.");
                        System.out.println(zone);
                        continue;
                    }

                    if (zone.isValid()) {
                        loaded.add(zone);
                    } else {
                        System.out.println("[Warning]: Skipped invalid Zone object at line " + lineNumber + ".");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("[Error]: Skipped faulty line " + lineNumber + ": " + e.getMessage());
                }
            }
        }

        this.list.clear();
        this.list.addAll(loaded);
        System.out.println("-> Successfully loaded " + this.list.size() + " zone(s) from file [" + fileName + "]!");
    }

    public void saveToFile() {
        if (list == null || list.isEmpty()) {
            System.out.println("[Warning]: List is empty. Skipping save to avoid clearing existing file data.");
            return;
        }
        try {
            PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("data/Zone.txt"), StandardCharsets.UTF_8));
            for (Zone z : list) {
                pw.println(z.toFileString());
            }
            System.out.println("-> Saved data successfully!");
            pw.close();
        } catch (IOException e) {
            System.out.println("[Error]: " + e.getMessage());
        }
    }

    private String chooseAllowedRole() {
        Scanner sc = Validatable.InputScanner.getScanner();

        while (true) {
            System.out.println("Allowed role:");
            System.out.println("1. WORKER");
            System.out.println("2. SAFETY_OFFICER");
            System.out.println("3. CONTRACTOR");
            System.out.println("4. VISITOR");
            System.out.print("Choose role [1-4]: ");

            if (!sc.hasNextLine()) {
                return null;
            }

            switch (sc.nextLine().trim()) {
                case "1":
                    return "WORKER";
                case "2":
                    return "SAFETY_OFFICER";
                case "3":
                    return "CONTRACTOR";
                case "4":
                    return "VISITOR";
                default:
                    System.out.println("Please choose from 1 to 4.");
            }
        }
    }
}
