package com.entertainment;

import java.util.Objects;

/*
 * Natural order is defined by brand (String), then by volume (int)
 * to be "consistent with equals()" we must use the same properties
 * for natural order that we use for equals() and hashCode().
 */

public class Television implements Comparable<Television> {

    private String brand = "Samsung";
    private int volume;

    private Tuner tuner = new Tuner();  // HAS-A Tuner - instantiated internally, not exposed

    //constructors
    public Television() {

    }

    public Television(String brand, int volume) {
        setBrand(brand);
        setVolume(volume);
    }

    //business methods

    public int getCurrentChannel() {
        return tuner.getChannel();  // delegate to contained Tuner object
    }

    public void changeChannel(int channel) {
        tuner.setChannel(channel);  // delegate to contained Tuner object
    }


    //accessor methods

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int hashCode() {
        // poorly-written has function, because it easily yields "has collisions"
        // a "hash collision" is when different objects has to the same value (dumb luck!)
        return Objects.hash(getBrand(), getVolume());
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        // only procee if 'obj' is a reference to another television object
        if (obj instanceof Television) {
            // downcast obj to more specific type Television, so we can call Television methods
            Television other = (Television) obj;

            // business equality is defined as brand and volume being the same

            result = Objects.equals(this.getBrand(), other.getBrand()) &&
                    this.getVolume() == other.getVolume();
        }
        return result;
    }



    @Override

    public int compareTo(Television other) {
        int result = this.getBrand().compareTo(other.getBrand());

        if(result == 0) {   //tied on brand, break the tie by volume
            result = Integer.compare(this.getVolume(), other.getVolume());
        }
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " brand = " + getBrand() + ", volume = " + getVolume() +
                ", currentChannel = " + getCurrentChannel();
    }
}