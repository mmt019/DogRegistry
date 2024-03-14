// Malika Taverdieva mata6399

public class Dog {

    private static final double DACHSHUND_TAIL_LENGTH_EXCEPTION = 3.7;
    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public void increaseAgeByOne() {
        age++;
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        if (breed.equalsIgnoreCase("Tax") || breed.equalsIgnoreCase("Dachshund")) {
            tailLength = DACHSHUND_TAIL_LENGTH_EXCEPTION;
        } else {
            tailLength = (age * weight) / 10.0;
        }
        return tailLength;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void removeOwnerOfDog() {
        if (this.owner != null) {
            this.owner.removeDogFromOwner(this);
            this.owner = null;
        }
    }

    private String printOwner() {
        if (this.owner != null) {
            return ", owned by " + this.owner.getName();
        } else {
            return "";
        }
    }

    public String toString() {
        return "* " + name + " (" + breed + ", " + age + " years old, " + weight +
                " kg, " + getTailLength() + " cm tail" + printOwner() + ")";
    }

}