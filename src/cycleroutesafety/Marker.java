package cycleroutesafety;

public class Marker {
    private int markerID;
    private String description;
    private String markerType;
    
    public Marker(){
        
    }
    
    public Marker(int markerID,
                String description,
                String markerType){
        this.markerID = markerID;
        this.description = description;
        this.markerType = markerType;
    }
    
    @Override
    public String toString(){
        return "["+this.markerID+"; "+this.description+"; "+this.markerType+"]";
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void seMarkerType(String markerType){
        this.markerType = markerType;
    }
    
    public int getMarkerID(){
        return this.markerID;
    }
    
    public String getMarkerDescription(){
        return this.description;
    }
    
    public String getMarkerType(){
        return this.markerType;
    }
}
 