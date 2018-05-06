package cycleroutesafety;

/**
 * A Marker object generally showing a location on the map, representing a POI
 * that is connected to it. A Marker be put to the map, removed, and onClick it
 * shows some general information about the given POI that it represents.
 * 
 * It has a
 * markerID for DB purposes
 * description that tells some information about the represented POI
 * markerType that tells info about the underlying POI like if it is a hazard,
 * an interesting place like a free-air pump or drinking water availability.
 * 
 * @author Andor
 */
public class Marker {

    private int markerID;
    private String description;
    private String markerType;

    public Marker() {

    }

    public Marker(int markerID,
            String description,
            String markerType) {
        this.markerID = markerID;
        this.description = description;
        this.markerType = markerType;
    }

    @Override
    public String toString() {
        return "[" + this.markerID + "; " + this.description + "; " + this.markerType + "]";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void seMarkerType(String markerType) {
        this.markerType = markerType;
    }

    public int getMarkerID() {
        return this.markerID;
    }

    public String getMarkerDescription() {
        return this.description;
    }

    public String getMarkerType() {
        return this.markerType;
    }
}
