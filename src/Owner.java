// Malika Taverdieva mata6399

public class Owner {

    private String ownerName;
    private OwnersListOfDogs ownedDogs = new OwnersListOfDogs();

    public Owner(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getName() {
        return ownerName;
    }

    public boolean addDogToOwner(Dog dog) {
        if (dog.getOwner() == null && dog.getOwner() != this) {
            ownedDogs.addDogToArray(dog);
            dog.setOwner(this);
            return true;
        }
        return false;
    }

    public void removeDogFromOwner(Dog dog) {
        ownedDogs.removeDogFromArray(dog);
        if (dog.getOwner() == this) {
            dog.setOwner(null);
        }
    }

    public boolean ownsDog(Dog dog) {
        return ownedDogs.isDogOwned(dog);
    }

    public String toString() {
        return ownerName + " [" + ownedDogs.getOwnersDogs() + "] ";
    }
}