// Malika Taverdieva mata6399

import java.util.ArrayList;

public class DogRegistry {

    private InputReader inputReader = new InputReader();
    private ArrayList<Dog> dogList = new ArrayList<>();
    private ArrayList<Owner> ownerList = new ArrayList<>();

    private void registerNewDog() {
        String name = inputReader.stringInput("Name");
        while (name.isEmpty()) {
            System.out.println("Error: the input cannot be empty");
            name = inputReader.stringInput("Name");
        }
        String breed = inputReader.stringInput("Breed");
        while (breed.trim().isEmpty()) {
            System.out.println("Error: the input cannot be empty");
            breed = inputReader.stringInput("Breed");
        }
        int age = inputReader.intInput("Age");
        int weight = inputReader.intInput("Weight");
        Dog dog = new Dog(name, breed, age, weight);
        dogList.add(dog);
    }

    private void increaseAge() {
        String name = inputReader.stringInput("Enter the name of the dog");
        Dog dog = findDog(name);
        if (dog == null || dogList.isEmpty()) {
            System.out.print("Error: No dog with such name found");
        } else {
            dog.increaseAgeByOne();
            System.out.println(name + " is now one year older");
        }
    }

    private Dog findDog(String name) {
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name)) {
                return dog;
            }
        }
        return null;
    }

    private void listDogs() {
        if (dogList.isEmpty()) {
            System.out.print("Error: no dogs in register");
        } else {
            double smallestTailLength = inputReader.doubleInput("Smallest tail length to display");
            ArrayList<Dog> selectedDogs = new ArrayList<>();
            sortDogs();
            for (Dog dog : dogList) {
                if (dog.getTailLength() >= smallestTailLength) {
                    selectedDogs.add(dog);
                }
            }
            if (selectedDogs.size() == 0) {
                System.out.print("Error: no dog has a tail that long");
            } else {
                System.out.println("The following dogs has such a large tail:");
                for (Dog dog : selectedDogs) {
                    System.out.println(dog);
                }
            }
        }
    }

    private int sortDogs() {
        int i = 0;
        for (int index = 0; index < dogList.size(); index++) {
            int indexToSwap = findSmallestDog(index);
            swapDogs(index, indexToSwap);
            if (index != indexToSwap) {
                i++;
            }
        }
        return i;
    }

    private int findSmallestDog(int index) {
        int smallestDog = index;
        for (int i = index + 1; i < dogList.size(); i++) {
            int comparison = compareDogs(dogList.get(smallestDog), dogList.get(i));
            if (comparison == 1) {
                smallestDog = i;
            }
        }
        return smallestDog;
    }

    private int compareDogs(Dog firstDog, Dog secondDog) {
        int tailComparison = compareTailLength(firstDog, secondDog);
        if (tailComparison != 0) {
            return tailComparison;
        }
        return compareNames(firstDog, secondDog);
    }

    private int compareTailLength(Dog firstDog, Dog secondDog) {
        if (firstDog.getTailLength() > secondDog.getTailLength()) {
            return 1;
        }
        if (firstDog.getTailLength() < secondDog.getTailLength()) {
            return -1;
        }
        return 0;
    }

    private int compareNames(Dog firstDog, Dog secondDog) {
        int i = firstDog.getName().compareToIgnoreCase(secondDog.getName());
        if (i > 0) {
            return 1;
        }
        return 0;
    }

    private void swapDogs(int x, int y) {
        Dog dog = dogList.get(y);
        dogList.set(y, dogList.get(x));
        dogList.set(x, dog);
    }

    private void removeDog() {
        String dogName = inputReader.stringInput("Enter the name of the dog");
        while (dogName.isEmpty()) {
            System.out.print("Error: the name can't be empty");
            dogName = inputReader.stringInput("Enter the name of the dog");
        }
        Dog dog = findDog(dogName);
        if (dog == null || dogList.isEmpty()) {
            System.out.print("Error: No dog with such name found");
        } else if (dog.getOwner() == null) {
            dogList.remove(dog);
        } else {
            Owner owner = dog.getOwner();
            owner.removeDogFromOwner(dog);
            dogList.remove(dog);
        }
        System.out.print(dogName + " is removed from the register");
    }

    private Owner registerNewOwner() {
        String ownerName = inputReader.stringInput("Name");
        while (ownerName.isEmpty()) {
            System.out.println("Error: the input cannot be empty");
            ownerName = inputReader.stringInput("Name");
        }
        Owner owner = new Owner(ownerName);
        addOwnerToList(owner);
        System.out.println(owner.getName() + " added to register");
        return owner;
    }

    private void addOwnerToList(Owner newOwner) {
        ownerList.add(newOwner);
    }

    private Owner findOwner(String ownerName) {
        for (Owner owner : ownerList) {
            if (owner.getName().equalsIgnoreCase(ownerName)) {
                return owner;
            }
        }
        return null;
    }

    private void giveDog() {
        String dogName = inputReader.stringInput("Enter the name of the dog");
        while (dogName.isEmpty()) {
            System.out.print("Error: the name can't be empty");
            dogName = inputReader.stringInput("Enter the name of the dog");
        }
        Dog dog = findDog(dogName);
        if (dog == null) {
            System.out.println("Error: no dog with that name");
        } else if (dog.getOwner() != null) {
            System.out.println("Error: the dog already has an owner");
        } else {
            String ownerName = inputReader.stringInput("Enter the name of the owner");
            while (ownerName.isEmpty()) {
                System.out.print("Error: the name can't be empty");
                ownerName = inputReader.stringInput("Enter the name of the owner");
            }
            Owner owner = findOwner(ownerName);
            if (owner == null) {
                System.out.println("Error: no owner with that name");
            } else {
                if (owner.addDogToOwner(dog)) {
                    System.out.println(ownerName + " now owns " + dogName);
                } else {
                    System.out.println("Error: the dog already has an owner");
                }
            }
        }
    }

    private void listOwners() {
        if (ownerList.size() != 0) {
            for (Owner owner : ownerList) {
                System.out.print(owner);
            }
            System.out.println();
        } else {
            System.out.println("Error: no owners in register");
        }
    }

    private void removeOwnedDog() {
        String dogName = inputReader.stringInput("Enter the name of the dog");
        while (dogName.isEmpty()) {
            System.out.print("Error: the name can't be empty");
            dogName = inputReader.stringInput("Enter the name of the dog");
        }
        Dog dog = findDog(dogName);
        if (dog == null) {
            System.out.println("Error: there is no such dog");
        } else if (dog != null) {
            Owner owner = dog.getOwner();
            if (owner == null) {
                System.out.println("Error: " + dog.getName() + " is not owned by anyone");
            } else {
                dog.removeOwnerOfDog();
                System.out.println(dog.getName() + " is removed from " + owner.getName());
            }
        }
    }

    private void removeOwner() {
        String ownerName = inputReader.stringInput("Enter the name of the owner");
        while (ownerName.isEmpty()) {
            System.out.print("Error: the name can't be empty");
            ownerName = inputReader.stringInput("Enter the name of the owner");
        }
        Owner owner = findOwner(ownerName);
        if (owner != null) {
            ownerList.remove(owner);
            removeOwnersListOfDogs(owner);
            System.out.println(owner.getName() + " is removed from the register");
        } else {
            System.out.println("Error: there is no such owner");
        }
    }

    private void removeOwnersListOfDogs(Owner owner) {
        ArrayList<Dog> ownersDogs = new ArrayList<>();
        for (Dog dog : dogList) {
            if (owner.ownsDog(dog)) {
                ownersDogs.add(dog);
                owner.removeDogFromOwner(dog);
            }
        }
        for (Dog dog : ownersDogs) {
            dogList.remove(dog);
        }
    }

    public static void main(String[] args) { new DogRegistry().runProgram(); }

    private void runProgram() {
        startUpProgram();
        runCommandLoop();
        shutDownProgram();
    }

    private void startUpProgram() {
        System.out.println("Welcome!");
    }

    private void commandMenu() {
        System.out.println("Following commands are available: ");
        System.out.println("* register new dog");
        System.out.println("* list dogs");
        System.out.println("* increase age");
        System.out.println("* remove dog");
        System.out.println("* register new owner");
        System.out.println("* give dog");
        System.out.println("* list owners");
        System.out.println("* remove owned dog");
        System.out.println("* remove owner");
        System.out.println("* exit");
    }

    private void runCommandLoop() {
        String command;
        commandMenu();
        do {
            command = handleCommandMenu();
        } while (!command.equalsIgnoreCase("exit"));
    }

    private String handleCommandMenu() {
        String command = inputReader.stringInput("\nCommand");
        switch (command) {
            case "register new dog":
                registerNewDog();
                break;
            case "list dogs":
                listDogs();
                break;
            case "increase age":
                increaseAge();
                break;
            case "remove dog":
                removeDog();
                break;
            case "register new owner":
                registerNewOwner();
                break;
            case "give dog":
                giveDog();
                break;
            case "list owners":
                listOwners();
                break;
            case "remove owned dog":
                removeOwnedDog();
                break;
            case "remove owner":
                removeOwner();
                break;
            case "exit":
                break;
            default:
                System.out.println("Error: no such option");
        }
        return command;
    }

    private void shutDownProgram() {
        System.out.println("Goodbye!");
    }
}