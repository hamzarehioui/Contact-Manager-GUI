/*
* DataStorageSettings Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp;

import TypeValues.MediaType;
import TypeValues.NetworkType;

public class DataStorageSettings {

    // Attributes
    private MediaType mediaType;
    private NetworkType networkType;
    private boolean on;

    // Constructor
    public DataStorageSettings(MediaType mediaType, NetworkType networkType) {
        this.mediaType = mediaType;
        this.networkType = networkType;
        this.on = true;
    }

    // Getters & Setters
    // MEDIA TYPE
    public MediaType getMediaType() {
        return mediaType;
    }
    // NETWORK TYPE

    public NetworkType getNetworkType() {
        return networkType;
    }
    // BOOLEAN

    public boolean isOn() {
        return on;
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    // To String
    @Override
    public String toString() {
        String autoD = (this.isOn() == true) ? "ON" : "OFF";
        return "Auto-Download of " + getMediaType() + " using " + getNetworkType() + " is " + autoD + ".\n";
    }

}
