package cycleroutesafety;

public class Route {
    private int routeID;
    private String routeName;
    private String author;
    private String startPoint;
    private String finishPoint;
    private int routeLength;
    private String lastUpdateTime;
    
    public Route(){
        
    }
    
    public Route(int routeID,
                String routeName,
                String author,
                String startPoint,
                String finishPoint,
                int routeLength,
                String lastUpdateTime){
        this.routeID = routeID;
        this.routeName = routeName;
        this.author = author;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.routeLength = routeLength;
        this.lastUpdateTime = lastUpdateTime;
    }
    
    @Override
    public String toString(){
        return "["+this.routeID+"; "+this.routeName+"; "+this.author+"; "+this.startPoint+"; "+this.finishPoint+"; "+this.routeLength+"; "+this.lastUpdateTime+"]";
    }
    
    public void setRouteName(String routeName){
        this.routeName = routeName;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }
    
    public void setStartPoint(String startPoint){
        this.startPoint = startPoint;
    }
    
    public void setFinishPoint(String finishPoint){
        this.finishPoint = finishPoint;
    }
    
    public void setRouteLength(int routeLength){
        this.routeLength = routeLength;
    }
    
    public void setLastUpdateTime(String lastUpdateTime){
        this.lastUpdateTime = lastUpdateTime;
    }
    
    public int getRouteID(){
        return this.routeID;
    }
    
    public String getRouteName(){
        return this.routeName;
    }
    
    public String getAuthor(){
        return this.author;
    }
    
    public String getStartPoint(){
        return this.startPoint;
    }
    
    public String getFinishPoint(){
        return this.finishPoint;
    }
    
    public int getRouteLength(){
        return this.routeLength;
    }
    
    public String getLastUpdateTime(){
        return this.lastUpdateTime;
    }
}