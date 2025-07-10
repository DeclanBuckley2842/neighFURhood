import java.util.ArrayList;
public class PetArray  // Change ClassName to your class name
{
    private ArrayList<Pet> petList = new ArrayList<Pet>();
   
  // constructor
    public PetArray(){
       petList = new ArrayList<Pet>();
    }
  // adds a pet to the list at a specified index or at the end of the list
    public void addPet(int i, Pet p){
        petList.add(i,p);
    }
    public void addPet(Pet p){
      petList.add(p);
    }
    // returns the pet at the specified index
    public Pet find(int i){
      return petList.get(i);
    }
    // getter for the petList
    public ArrayList<Pet> getPetList(){
      return petList;
    }
    // returns the pet at a given index
    public int getIndex(Pet p){
      return petList.indexOf(p);
    }
  // Write a print() method using an enhanced for loop
    public void print(){
        for(Pet p: petList){
            System.out.println(p);
        }
    }
  // Write a findAndPrint(attribute) method using an enhanced for loop
     public void findAndPrint(String name){
        for(Pet p: petList){
            if(p.getName().equals(name)){
                
                System.out.println(p);
            }
        }
    }
    // returns the pet with the given name, or null if not found
    public Pet findAndReturn(String name){
        for(Pet p: petList){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null; // Return null if no pet is found with the given name
    }
  
}
