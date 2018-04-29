package cycleroutesafety;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageDatabase {
    
    private Connection conn = null;
    private String query = null;
    private ResultSet rs;
    private final String dbName     = "cycleroutes";
    private final String dbUser     = "ahorvath";
    private final String dbPassword = "dummy";
    private final String dbDomain = ("jdbc:mysql://localhost/"+dbName+"?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
    
    public int numberOfRoutes(){
        int i=0;
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from routes";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i=-1;
            while (rs.next()){
                ++i;
            }
            conn.close();
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
        return i;
    }
    
    public int numberOfMarkers(){
        int i=0;
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from routes";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i=-1;
            while (rs.next()){
                ++i;
            }
            conn.close();
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
        return i;
    }
    
    public int numberOfPois(){
        int i=0;
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from pois";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i=-1;
            while (rs.next()){
                ++i;
            }
            conn.close();
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
        return i;
    }
    
    public Route[] readRoutes(){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from routes";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            int i=0;
            while (rs.next()){
                ++i;
            }
            
            Route[] myRoute = new Route[i];
            
            query = "SELECT * from routes";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i=-1;
            while (rs.next()){
                ++i;
                myRoute[i] = new Route(
                                    rs.getInt("routeID"),
                                    rs.getString("routeName"),
                                    rs.getString("author"),
                                    rs.getString("startPoint"),
                                    rs.getString("finishPoint"),
                                    rs.getInt("routeLength"),
                                    rs.getString("lastUpdateTime")
                                );
                //System.out.println(myRoute[i].toString()); 
            }
            conn.close();
            return myRoute;
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
        Route[] myRoute = new Route[0];
        return myRoute;
    }
    
    public Poi[] readPois(){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from pois";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            int i=0;
            while (rs.next()){
                ++i;
            }
            
            Poi[] myPoi = new Poi[i];
            
            query = "SELECT * from pois";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i=-1;
            while (rs.next()){
                ++i;
                myPoi[i] = new Poi(
                                    rs.getInt("poiID"),
                                    rs.getString("destination"),
                                    rs.getInt("markerID"));
                System.out.println(myPoi[i].toString()); 
            }
            conn.close();
            return myPoi;
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
        Poi[] myPoi = new Poi[0];
        return myPoi;
    }
    
    public Route readRoute(int routeID){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from routes WHERE routeID = "+routeID;
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            Route myRoute = new Route(
                                rs.getInt("routeID"),
                                rs.getString("routeName"),
                                rs.getString("author"),
                                rs.getString("startPoint"),
                                rs.getString("finishPoint"),
                                rs.getInt("routeLength"),
                                rs.getString("lastUpdateTime")
                            );
                System.out.println(myRoute.toString()); 
            conn.close();
            return myRoute;
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
        Route myRoute = new Route();
        return myRoute;
    }
    
    public Marker[] readMarkers(){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from markers";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            int i=0;
            while (rs.next()){
                ++i;
            }
            
            Marker[] myMarker = new Marker[i];
            
            query = "SELECT * from markers";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i=-1;
            while (rs.next()){
                ++i;
                myMarker[i] = new Marker(
                                    rs.getInt("markerID"),
                                    rs.getString("description"),
                                    rs.getString("markerType"));
                System.out.println(myMarker[i].toString()); 
            }
            conn.close();
            return myMarker;
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
        Marker[] myMarker = new Marker[0];
        return myMarker;
    }
    
    public void createRoute(
                String routeName,
                String author,
                String startPoint,
                String finishPoint,
                int routeLength,
                String lastUpdateTime,
                boolean plannedRoute){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "INSERT INTO routes(routeName, "
                                        + "author, "
                                        + "startPoint, "
                                        + "finishPoint, "
                                        + "routeLength, "
                                        + "lastUpdateTime) "
                    + "VALUES('" + routeName + "', "
                             + "'" + author+"', "
                             + "'" + startPoint+"', "
                             + "'" + finishPoint+"', "
                             + "'" + routeLength+"', "
                             + "'" + lastUpdateTime+"')";
            System.out.println(query);
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
            query = "select last_insert_id() as last_id from routes";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            System.out.println(rs.getString("last_id"));
            conn.close();
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
    }
    
    public void createMarker(
                String description,
                String markerType){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "INSERT INTO markers(description, "
                                        + "markerType) "
                    + "VALUES('" + description + "', "
                             + "'" + markerType + "')";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
            conn.close();
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
    }
    
    public void modifyRoute(int routeID,
                            String routeName,
                            String author,
                            String startPoint,
                            String finishPoint,
                            int routeLength,
                            String lastUpdateTime,
                            boolean plannedRoute){
        try{
            int i=0; if (plannedRoute) i=1;
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "UPDATE routes SET "
                    + "routeName='" + routeName + "', "
                    + "author='" + author + "', "
                    + "startPoint='" + startPoint + "', "
                    + "finishPoint='" + finishPoint + "', "
                    + "routeLength='" + routeLength + "', "
                    + "lastUpdateTime='" + lastUpdateTime + "' "
                    + "WHERE routeID=" + routeID + "";
            PreparedStatement stm = conn.prepareStatement(query);
            System.out.println(query);
            if(stm.executeUpdate()!=1){
                System.out.println("Már van ilyen érték az adatbázisban!!!");
            }
        conn.close();

        }catch (SQLException e){

          System.out.println(e.getMessage());

        }
    }
    
    public void modifyPoi(int poiID,
                            String destination,
                            int markerID){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "UPDATE pois SET "
                    + "routeID='" + poiID + "', "
                    + "destination='" + destination + "', "
                    + "poiType='" + markerID + "', "
                    + "WHERE poiID=" + poiID + "";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
        conn.close();

        }catch (SQLException e){

          System.out.println(e.getMessage());

        }
    }
    
    public void deleteRoute(int routeID){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "DELETE FROM routes "
                    + "WHERE routeID = " + routeID + "";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
        conn.close();

        }catch (SQLException e){

          System.out.println(e.getMessage());

        }
    }
    
    public void deletePoi(int poiID){
        try{
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "DELETE FROM pois"
                    + "WHERE poiID = " + poiID + "";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
        conn.close();

        }catch (SQLException e){

          System.out.println(e.getMessage());

        }
    }
}
