

    import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class Menu {
    private String[] menuOptions = {"Exit", "show pet info", "show pet list","Find Pet", "Register as missing", "Register as found", "Change Pet info", "Change Owner info", "Add Pet"};
    private PetArray list;
    private Scanner scanner;
    private Owner owner;


    public Menu(Scanner scanner,PetArray l) {
        this.scanner = scanner;
        list = l;
    }
    public enum MenuOption {
        EXIT(0),DISPLAY_PET(1),SHOW_LIST(2),FIND_PET(3),REGISTER_MISSING(4),REGISTER_FOUND(5),CHANGE_PET_INFO(6),CHANGE_OWNER_INFO(7),ADD_PET(8);
    private int id;
    
    MenuOption(int i){
        id = i;

    }
    public int getId(){
        return this.id;
    }
    
    public static MenuOption fromOptionId(int optionId){
        for(MenuOption m: MenuOption.values()){
            if(m.getId() == optionId){
                return m;
            }
        }
        return EXIT;
    }
    }

    /**
     * Starts the menu and handles user input.
     * keeps running until the user chooses to exit.
     */
    public void executeMenu() {
        int id = -1;
        boolean control = true;
        while (control){
        printMenu();
        String optionId = scanner.next();
        String result = optionId.replaceAll("[^0-9]", "");
        // Check if the result is empty or not a number
        if(result.isEmpty()){
            System.out.println("Invalid input. Please enter a number. ( 0 through " + (menuOptions.length - 1) + " )");
            executeMenu();
        } else {
            id = parseInt(result);
        }
        if( id < 0 || id >= menuOptions.length){
            System.out.println("Invalid input. Please enter a number. ( 0 through " + (menuOptions.length - 1) + " )");
            executeMenu();
        }
        if(id == 0){
            control = false;
        }
        MenuOption choice = MenuOption.fromOptionId(id);
        handleOption(choice);
     }
    }
    /**
     * Handles the user's menu choice and executes the corresponding action.
     * @param choice The user's selected menu option.
     */
    public void handleOption(MenuOption choice){
        switch (choice){
            case EXIT:
                exit();
                break;
            case DISPLAY_PET:
                for(Pet p : owner.getPets()){
                    System.out.println(p);
                }
                System.out.println("Press enter to continue");
                scanner.nextLine(); 
                scanner.nextLine();
                break;
            case FIND_PET:
                System.out.print("What is the name of the animal you wish to find:");
                String toFind = scanner.next();
                Pet found = list.findAndReturn(toFind); 
                if(found == null){
                    System.out.println("No pet found with that name");
                } else{
                    petPrint(found);
                }
                break;
            case SHOW_LIST:
                String[] options = {"", "Missing Pets", "Dogs", "Cats", "Other Pets"};
                ArrayList<Pet> arr = new ArrayList<>();
                int listChoice = listPrompt();
                for(Pet p : list.getPetList()) {
                    if (listChoice == 2 && p.getMissing()) {
                        arr.add(p);
                    } else if (listChoice == 3 && p.getType().equalsIgnoreCase("Dog")) {
                        arr.add(p);
                    } else if (listChoice == 4 && p.getType().equalsIgnoreCase("Cat")) {
                        arr.add(p);
                    } else if (listChoice == 5 && !(p.getType().equalsIgnoreCase("Dog") || p.getType().equalsIgnoreCase("Cat"))) {
                        arr.add(p);
                    } else if(listChoice == 1) {
                        arr.add(p);
                    }
                }
                System.out.println("Here is the list of " + options[listChoice-1] + " in the system:");
                printList(arr);
                System.out.print("please enter the index of the pet you wish to see more information on (press 0 to return to menu): ");
                int i = scanner.nextInt();
                if(i != 0){
                    findPet(i-1,arr);
                } else{
                    System.out.println("Returning to menu");
                    break;
                }
                break;
            case REGISTER_MISSING:
                changeStatus(true);
                break;
            case REGISTER_FOUND:
                changeStatus(false);
                break;
            case CHANGE_PET_INFO:
                Pet toChange = selectPet();
                System.out.println("What would you like to change \n 1: Name \n 2: Weight");
                int change = scanner.nextInt();
                if(change == 1){
                    System.out.println("What is the new name?");
                    toChange.changeName(scanner.next());
                }else if(change == 2){
                    System.out.println("What is there new weight?");
                    toChange.changeWeight(scanner.nextDouble());
                } else{
                    System.out.println("invalid input detected. Returning to menu");
                }
                break;
            case CHANGE_OWNER_INFO:
                System.out.println("-----------------------");
                System.out.println("What would you like to change \n 1: Name \n 2: Phone Number \n 3: Address");
                System.out.println("------------------------");
                int changeOwner = scanner.nextInt();
                scanner.nextLine();
                if(changeOwner == 1){
                    System.out.println("You are currently: " + owner.getName());
                    System.out.println("What is your new name?");
                    owner.changeName(scanner.nextLine());
                }else if(changeOwner == 2){
                    System.out.println("You are currently: " + owner.getPhoneNumber());
                    System.out.println("What your new phone number?");
                    owner.changePhoneNumber(scanner.nextLine());
                }else if(changeOwner == 3){
                    System.out.println("You are currently: " + owner.getAddress());
                    System.out.println("What your new address?");
                    owner.changeAddress(scanner.nextLine());
                } else{
                    System.out.println("invalid input detected. Returning to menu");
                }
                break;
            case ADD_PET:
                scanner.nextLine();
                System.out.println("Please enter the name of the new pet:");
                String nextName = scanner.nextLine();
                System.out.println("Please enter the type of pet:");
                String nextType = scanner.nextLine();
                System.out.println("Please enter the weight of the pet in lbs (Numbers only!!!!):");
                double nextWeight = scanner.nextDouble();
                Pet newPet = new Pet(false, nextName, owner, nextType, nextWeight);
                list.addPet(newPet);


        }
    }
    /**
     * Greets the user and prompts for their name to create an account.
     */
    public void greet() {
        System.out.println("Welcome to the Pet Tracker!");
        System.out.println("------------------------");
        System.out.println("[1] Create a new account");
        System.out.println("[2] Use Existing account");
        System.out.println("-------------------------");
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            if (choice == 1) {
                accountCreation();
            } else if (choice == 2) {
                System.out.print("Email:");
                String email = scanner.next();
                System.out.print("Password:");
                String password = scanner.next();
                for( Owner o : Owner.getOwners()){
                    if(o.getEmail().equals(email) && o.getPassword().equals(password)){
                        owner = o;
                        System.out.println("Welcome back " + owner.getName());
                        return;
                    }
                    System.out.println("Invalid email or password. Please try again.");
                    greet();
                }
            
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
            greet();
        }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
            greet();
        }
    }
    /**
     * Prompts the user for their information to create an account.
     * @param n The name of the owner.
     */
    public void accountCreation(){
        scanner.nextLine(); // Clear the newline character
        System.out.println("Please Enter your Email:");
        String email = scanner.nextLine();
        System.out.println("Please Enter your new Password:");
        String password = scanner.nextLine();
        System.out.println("Please Enter your phone number:");
        String p = scanner.nextLine();
        System.out.println("Please enter your address:");
        String a = scanner.nextLine();
        System.out.println("Please enter your name:");
        String n = scanner.nextLine();
        owner = new Owner(email, password,n,p,a);
        System.out.println("Please Enter the name of your pet:");
        String pn = scanner.nextLine();
        System.out.println("Please Enter the Type of pet you have:");
        String t = scanner.nextLine();
        System.out.println("Please enter your pets weight in lbs(Numbers only!!!!):");
        double w = scanner.nextDouble();
        Pet toAdd = new Pet(false,pn,owner,t,w);
        list.addPet(toAdd);

    }
    /**
     * Prints a list of pets in a formatted manner.
     * @param toPrint The ArrayList of Pet objects to print.
     */
    public void printList(ArrayList<Pet> toPrint){
        System.out.println("-----------------------");
        for(int i = 0; i < toPrint.size(); i++){
            Pet p = toPrint.get(i);
            System.out.println("[" + (i+1) + "] " + p.getName() + ": " + p.getType() + " | " + p.getOwner().getName());
        }
        System.out.println("-----------------------");
    }
    /**
     * Prints the details of a pet and prompts the user to view the owner's information.
     * @param p The Pet object to print.
     */
    public void petPrint(Pet p){
        System.out.println(p);
        System.out.print("Would you like to see the owner information? (Y/N):");
        String response = scanner.next().toUpperCase();
        if(response.equals("Y")){
            System.out.println("Owner Information:");
            System.out.println("----------------------");
            System.out.println("Name: " + p.getOwner().getName());
            System.out.println("Phone: " + p.getOwner().getPhoneNumber());
            System.out.println("Address: " + p.getOwner().getAddress());
            System.out.println("----------------------");
        } else {
            System.out.println("Returning to menu.");
        }
        System.out.print("press enter to continue: ");
        scanner.nextLine();
        scanner.nextLine();
    }
    /**
     * Prompts the user to select a list of pets to view.
     * @return The user's choice as an integer.
     */
    public int listPrompt(){
        System.out.println("What would you like to see? \n 1: All Pets \n 2: Missing Pets \n 3: Dogs \n 4: Cats \n 5: Other Pets");
        int choice = scanner.nextInt();
        return choice;
    }
    
    /**
     * Prompts the user to select a pet from the owner's list of pets.
     * If there is only one pet, it automatically selects that pet.
     * @return The selected Pet object.
     */
    public Pet selectPet(){
        if(owner.getPets().size() == 1){
            return owner.getPets().get(0);
        }
        System.out.println("Which pet would you like to change?");
        for(Pet p : owner.getPets()){
            System.out.println("[" + (owner.getPets().indexOf(p)+1) + "] " + p.getName() + ": " + p.getType() + " | " + p.getOwner().getName());
        }
        int i = scanner.nextInt();
        while(i < 1 || i > owner.getPets().size()){
            System.out.println("Invalid selection. Try again.");
            i = scanner.nextInt();
        }
            return owner.getPets().get(i-1);
    }
    /**
     * Finds a pet by index in the provided ArrayList and prints its information.
     * @param i The index of the pet to find.
     * @param arr The ArrayList containing pets.
     */
    public void findPet(int i, ArrayList<Pet> arr){
        if(i < 0 || i >= arr.size()){
            System.out.println("Invalid index. Please try again.");
            return;
        }
        petPrint(arr.get(i));
    }
    /**
     * Changes the missing status of a pet.
     * @param status The new missing status to set for the pet.
     */
    public void changeStatus(boolean status){
        Pet pet = selectPet();
        pet.changeMissingStatus(status);
        System.out.println("Status changed successfully.");

    }

    /**
     * Prints the menu header and menu options.
     */
    private void printMenu() {
        System.out.println();
        System.out.println("                  --Main Menu--");
        System.out.println("   Please select an option from the menu below");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.print(i + ": ");
            System.out.println(menuOptions[i]);
        }
        System.out.println("-------------------------------------------------");
        System.out.print("Please enter your selection: ");
    }
    

    /**
     * Prints an exit statement and closes the scanner object.
     */
    private void exit() {
        System.out.println("Exiting now. Goodbye.");
        scanner.close();
    }
}



