package ui;
        
import java.util.Scanner;
import static ui.Draft.pause;

public abstract class MenuUserLv00 {
    
    public void displayMenuLv(String role, String name){
    Scanner sc = new Scanner(System.in);
        boolean running = true;
        while(running){
            displayHeader(role, name);                  //header
            displayOption();                            //option
            displayLogOut();                            //option 0 (logout)
            System.out.println("Your action: ");        
            int choice = sc.nextInt();                  //input choice
            if (choice==0){                             //logout
                running = false;
                System.out.println("The system is logging out...");
                pause();
            } else {
                chooseOption(choice);
            }//ngoac if
        }//ngoac while
    }//ngoac ham
    
    public void displayHeader(String role, String name){
        System.out.println("---------------" + role + " Menu "+ "---------------");
        System.out.println("");
        System.out.println("Welcome " + role + " "+ name + "!");
        System.out.println("What do you want to do?");    
    }
    
    abstract void displayOption();
    
    public void displayLogOut(){
        System.out.println("0. Log out");    
    }    
    abstract void chooseOption(int choice);
    
}//class
