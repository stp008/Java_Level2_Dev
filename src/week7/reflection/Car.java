package week7.reflection;


//import javax.annotation.PostConstruct;

import week7.reflection.di.Auto;
import week7.reflection.di.PostConstruct;

public class Car {

    @Auto(isRequired = true)
    private Engine engine;

    private Gear gear;


    public Car() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Post Init");
    }
    
    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", gear=" + gear +
                '}';
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }
}
