package com.declanbuckley.neighFURhood;
class   Pet       
{
    // set
        private boolean isMissing;
        private String petName;
        private Owner owner;
        private String petType;
        private double weight;


        
    // constructor for pet class (default)
        public Pet(){
            isMissing = false;
            petName = "N/A";
            owner = new Owner("N/A", "N/A", "N/A");
            petType = "N/A";
            weight = 0.0;
            
        }
    // constructor(w/ parameters)
        public Pet(boolean m,String n, Owner o, String p, double w){
            isMissing = m;
            petName = n;
            owner = o;
            petType = p;
            weight = w;
            owner.getPets().add(this); // Add this pet to the owner's list of pets

        }

    // getters
        public boolean getMissing(){
            return isMissing;
        }
        public String getName(){
            return petName;
        }
        public Owner getOwner(){
            return owner;
        }
        public String getType(){
            return petType;
        }
        public double getWeight(){
            return weight;
        }
        
        
    // setters
        public void changeMissingStatus(boolean newStatus){
            isMissing = newStatus;
        }
        public void changeName(String newName){
            petName = newName;
        }
        public void changeOwner(Owner newOwner){
            owner = newOwner;
        }
        public void changeType(String newType){
            petType = newType;
        }
        public void changeWeight(double newWeight){
            weight = newWeight;
        }
    public void changeInfo(){
        
    }

    @Override
    public String toString(){
        // Returns a string representation of the pet's information
        return "Pet Information:\n"
        + "--------------------------------\n"
        + "  Missing: " + isMissing + "\n"
        + "  Name: " + petName + "\n"
        + "  Owner: " + owner.getName() + "\n"
        + "  Type: " + petType + "\n"
        + "  Weight: " + weight + " lbs" + "\n"
        + "--------------------------------\n";
    }
    
}
