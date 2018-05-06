package cycleroutesafety;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 
 * 
 * @author Andor
 */
public class ManageDatabase {

    private Connection conn = null;
    private String query = null;
    private ResultSet rs;
    private final String dbName = "cycleroutes";
    private final String dbUser = "ahorvath";
    private final String dbPassword = "dummy";
    private final String dbDomain = ("jdbc:mysql://localhost/" + dbName + "?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

    /**
     * 
     * @return the number of routes stored in the DB's ROUTE table
     */
    public int numberOfRoutes() {
        int i = 0;
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from routes";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i = -1;
            while (rs.next()) {
                ++i;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    /**
     *
     * @return the number of markers stored in the DB's MARKER table
     */
    public int numberOfMarkers() {
        int i = 0;
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from markers";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i = -1;
            while (rs.next()) {
                ++i;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    /**
     *
     * @return the number of pois stored in the DB's POIS table
     */
    public int numberOfPois() {
        int i = 0;
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from pois";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i = -1;
            while (rs.next()) {
                ++i;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    /**
     * 
     * @return an array of Route objects, that contains ALL the routes from the 
     * database.
     */
    public Route[] readRoutes() {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from routes";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            int i = 0;
            while (rs.next()) {
                ++i;
            }

            Route[] myRoute = new Route[i];

            query = "SELECT * from routes";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i = -1;
            while (rs.next()) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Route[] myRoute = new Route[0];
        return myRoute;
    }

    /**
     * Scans the DB's POI table
     * - to see how many data we have, counting it's rows
     * - declare an array of POI objects
     * @return an array of Poi objects, containing ALL the POIs that are stored
     * in the database.
     */
    public Poi[] readPois() {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            // scans over whole table to see how many data we have (lines)
            query = "SELECT * from pois";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            int i = 0;
            while (rs.next()) {
                ++i;
            }

            Poi[] myPoi = new Poi[i];

            query = "SELECT * from pois";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i = -1;
            while (rs.next()) {
                ++i;
                myPoi[i] = new Poi(
                        rs.getInt("poiID"),
                        rs.getString("destination"),
                        rs.getInt("markerID"));
                System.out.println(myPoi[i].toString());
            }
            conn.close();
            return myPoi;
        } catch (SQLException e) {
            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();
            System.out.println(today + " " + now + " FATAL  Database related error. SQLException is: " + e.getMessage());
// throw FATAL, let it die...
        }
        Poi[] myPoi = new Poi[0];
        return myPoi;
    }

    /**
     * Reads the particular route from the DataBase that's ID is given as 
     * parameter.
     * 
     * @param routeID
     * @return Route object, filled with data from the DB's corresponding row
     * from the ROUTES table.
     */
    public Route readRoute(int routeID) {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from routes WHERE routeID = " + routeID;
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Route myRoute = new Route();
        return myRoute;
    }

    /**
     * Scans the DB's MARKERS table
     * - to see how many data we have, counting it's rows
     * - declare an array of Marker objects
     * @return an array of Marker objects, containing ALL the Markers that are 
     * stored in the database.
     */
    public Marker[] readMarkers() {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "SELECT * from markers";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            int i = 0;
            while (rs.next()) {
                ++i;
            }

            Marker[] myMarker = new Marker[i];

            query = "SELECT * from markers";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            i = -1;
            while (rs.next()) {
                ++i;
                myMarker[i] = new Marker(
                        rs.getInt("markerID"),
                        rs.getString("description"),
                        rs.getString("markerType"));
                System.out.println(myMarker[i].toString());
            }
            conn.close();
            return myMarker;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Marker[] myMarker = new Marker[0];
        return myMarker;
    }

    /**
     * Creates a new Route object according to the parameters, then persists it
     * to the DB with a new ID.
     * 
     * @param routeName
     * @param author
     * @param startPoint
     * @param finishPoint
     * @param routeLength
     * @param lastUpdateTime
     * @param plannedRoute
     */
    public void createRoute(
            String routeName,
            String author,
            String startPoint,
            String finishPoint,
            int routeLength,
            String lastUpdateTime,
            boolean plannedRoute) {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "INSERT INTO routes(routeName, "
                    + "author, "
                    + "startPoint, "
                    + "finishPoint, "
                    + "routeLength, "
                    + "lastUpdateTime) "
                    + "VALUES('" + routeName + "', "
                    + "'" + author + "', "
                    + "'" + startPoint + "', "
                    + "'" + finishPoint + "', "
                    + "'" + routeLength + "', "
                    + "'" + lastUpdateTime + "')";
            System.out.println(query);
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
            query = "select last_insert_id() as last_id from routes";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            System.out.println(rs.getString("last_id"));
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new Marker object according to the parameters, then persists it
     * to the DB with a new ID.
     * 
     * @param description
     * @param markerType
     */
    public void createMarker(
            String description,
            String markerType) {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "INSERT INTO markers(description, "
                    + "markerType) "
                    + "VALUES('" + description + "', "
                    + "'" + markerType + "')";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Modify an existing route's DB entry with the values in it's parameters.
     * 
     * @param routeID
     * @param routeName
     * @param author
     * @param startPoint
     * @param finishPoint
     * @param routeLength
     * @param lastUpdateTime
     * @param plannedRoute
     */
    public void modifyRoute(int routeID,
            String routeName,
            String author,
            String startPoint,
            String finishPoint,
            int routeLength,
            String lastUpdateTime,
            boolean plannedRoute) {
        try {
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
            if (stm.executeUpdate() != 1) {
                System.out.println("Már van ilyen érték az adatbázisban!!!");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Modify an existing poi's DB entry with the values in it's parameters.
     * 
     * @param poiID
     * @param destination
     * @param markerID
     */
    public void modifyPoi(int poiID,
            String destination,
            int markerID) {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "UPDATE pois SET "
                    + "routeID='" + poiID + "', "
                    + "destination='" + destination + "', "
                    + "poiType='" + markerID + "', "
                    + "WHERE poiID=" + poiID + "";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
            conn.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    /**
     * Deletes an entry from the DB, where the route ID is equal to the one 
     * given as the parameter.
     * 
     * @param routeID
     */
    public void deleteRoute(int routeID) {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "DELETE FROM routes "
                    + "WHERE routeID = " + routeID + "";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes an entry from the DB, where the poi ID is equal to the one 
     * given as the parameter.
     * 
     * @param poiID
     */
    public void deletePoi(int poiID) {
        try {
            conn = DriverManager.getConnection(dbDomain, dbUser, dbPassword);
            query = "DELETE FROM pois"
                    + "WHERE poiID = " + poiID + "";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
