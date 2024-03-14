// Malika Taverdieva mata6399

import java.util.Arrays;

public class OwnersListOfDogs {

    private Dog[] ownersDogs = new Dog[0];

    public void addDogToArray(Dog dog) {
        if (dog != null && !isDogOwned(dog)) {
            // create a copy of the array with an additional index
            Dog[] copyOfDogArray = Arrays.copyOf(ownersDogs, ownersDogs.length + 1);
            // update the original array
            ownersDogs = copyOfDogArray;
            // add element into the empty last index
            ownersDogs[ownersDogs.length - 1] = dog;
        }
    }

    public void removeDogFromArray(Dog dog) {
        // find index of the element
        int i = findDogInArray(dog);
        if (i >= 0) {
            // create an array to copy the remaining elements into, while removing the element
            Dog[] copyOfArr = new Dog[ownersDogs.length - 1];
            // copy elements from original array into copyArray
            // starting from the beginning and stopping at the index of the element to be removed
            System.arraycopy(ownersDogs, 0, copyOfArr, 0, i);
            // copying everything from index+1 till the end of the array
            System.arraycopy(ownersDogs, i + 1, copyOfArr, i, ownersDogs.length - i - 1);
            // update the original array
            ownersDogs = copyOfArr;
        }
    }

    public int findDogInArray(Dog dog) {
        for (int i = 0; i < ownersDogs.length; i++) {
            if (dog == ownersDogs[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean isDogOwned(Dog dog) {
        for (int i = 0; i < ownersDogs.length; i++)
            if (dog == ownersDogs[i])  {
                return true;
            }
        return false;
    }

    public String getOwnersDogs() {
        String ownedDogs = "";
        for (int i = 0; i < ownersDogs.length; i++) {
            String dog = ownersDogs[i].getName();
            ownedDogs = ownedDogs.concat(dog);
            if (i != ownersDogs.length - 1) {
                ownedDogs = ownedDogs.concat(", ");
            }
        }
        return ownedDogs;
    }

    public String toString() {
        return ownersDogs.toString();
    }

}