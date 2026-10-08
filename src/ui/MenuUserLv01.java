package ui;

import static ui.Draft.pause;

public class MenuUserLv01 extends MenuUserLv02{
    
    @Override
    public void displayOption(){
        super.displayOption();
        System.out.println("6. Show all history attendance");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (6):
                System.out.println("Function not complete yet!");
                pause();
                break;
            default:
                super.chooseOption(choice);
                break;
        }
        
    }
    
}
