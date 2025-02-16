import java.util.LinkedList;
import java.util.Queue;

abstract class Animal {
    private String name;
    private int order;

    public Animal(String name) {
        this.name = name;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}

class AnimalShelter {
    private Queue<Dog> dogQueue;
    private Queue<Cat> catQueue;
    private int order;

    public AnimalShelter() {
        dogQueue = new LinkedList<>();
        catQueue = new LinkedList<>();
        order = 0;
    }

    public void enqueue(Animal animal) {
        animal.setOrder(order++);
        if (animal instanceof Dog) {
            dogQueue.add((Dog) animal);
        } else if (animal instanceof Cat) {
            catQueue.add((Cat) animal);
        }
    }

    public Animal dequeueAny() {
        if (dogQueue.isEmpty()) {
            return dequeueCat();
        } else if (catQueue.isEmpty()) {
            return dequeueDog();
        }

        Dog oldestDog = dogQueue.peek();
        Cat oldestCat = catQueue.peek();

        if (oldestDog.getOrder() < oldestCat.getOrder()) {
            return dogQueue.poll();
        } else {
            return catQueue.poll();
        }
    }

    public Dog dequeueDog() {
        return dogQueue.poll();
    }

    public Cat dequeueCat() {
        return catQueue.poll();
    }

    public boolean isEmpty() {
        return dogQueue.isEmpty() && catQueue.isEmpty();
    }
}

public class Main {
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        /* I have 5 pets.
         Lily is 6 months, Ruin is 2 1/2,
         Clyde & Bonnie are litter-mates (8yrs old)
         Valentine is 9yrs old */
        shelter.enqueue(new Dog("Lily"));
        shelter.enqueue(new Dog("Ruin"));
        shelter.enqueue(new Cat("Clyde"));
        shelter.enqueue(new Cat("Bonnie"));
        shelter.enqueue(new Cat("Valentine"));

        System.out.println("Adopted: " + shelter.dequeueAny().getName()); // Should adopt "Lily"
        System.out.println("Adopted: " + shelter.dequeueCat().getName()); // Should adopt "Ruin"
        System.out.println("Adopted: " + shelter.dequeueDog().getName()); // Should adopt "Clyde"
        System.out.println("Adopted: " + shelter.dequeueAny().getName()); // Should adopt "Bonnie"
        System.out.println("Adopted: " + shelter.dequeueAny().getName()); // Should adopt "Valentine"
    }
}
