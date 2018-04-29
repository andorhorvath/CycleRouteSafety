package cycleroutesafety;

public class Poi {
    private int poiID;
    private String destination;
    private int markerID;
    
    public Poi(){
        
    }
    
    public Poi(int poiID,
                String destination,
                int markerID){
        this.poiID = poiID;
        this.destination = destination;
        this.markerID = markerID;
    }
    
    @Override
    public String toString(){
        return "["+this.poiID+"; "+this.destination+"; "+this.markerID +"]";
    }
    
    public void setDestination(String destination){
        this.destination = destination;
    }
    
    public void setPoiType(int markerID){
        this.markerID = markerID;
    }
    
    public int getPoiID(){
        return this.poiID;
    }
    
    public String getDestination(){
        return this.destination;
    }
    
    public int getPoiType(){
        return this.markerID;
    }
}