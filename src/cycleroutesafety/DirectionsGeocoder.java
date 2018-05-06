/*
 * Copyright (c) 2000-2017 TeamDev Ltd. All rights reserved.
 * Use is subject to Apache 2.0 license terms.
 */
package cycleroutesafety;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.DirectionsRequest;
import com.teamdev.jxmaps.DirectionsResult;
import com.teamdev.jxmaps.DirectionsRouteCallback;
import com.teamdev.jxmaps.DirectionsStatus;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.TravelMode;
import com.teamdev.jxmaps.swing.MapView;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Defines the map manager tools, like from field, to field, control panels,
 * while also configures them. Initializes map shown.
 * configures the map to a default location when the program is starting up
 *
 * @author Andor, used the example by Vitaly Eremenko
 */
public class DirectionsGeocoder extends MapView implements ControlPanel {

    private static final Color FOREGROUND_COLOR = new Color(0xBB, 0xDE, 0xFB);

    // declaring a default center for the map to show while starting up the
    // program
    private final JTextField fromField;
    private final JTextField toField;
    private final String defaultFrom = "Budapest, Pázmány Péter stny. 1a, 1117 Magyarország";
    private final String defaultTo = "";//defaultFrom;
    private final int PREFERRED_HEIGHT = 169;
    private final double PREFERRED_ZOOM = 15.0; // was 12

    public final JPanel controlPanel;

    /**
     * 
     */
    public DirectionsGeocoder() {
        controlPanel = new JPanel();

        fromField = new JTextField(defaultFrom);
        toField = new JTextField(defaultTo);

        configureControlPanel();

        System.out.println("## DEBUG ## before setting on map ready..." );
        // Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
        // the map object is ready to use. Current implementation of onMapReady customizes the map object.
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                System.out.println("## DEBUG ## before checking       status == MapStatus.MAP_STATUS_OK" );
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    final Map map = getMap();
                    System.out.println("## DEBUG ## status == MapStatus.MAP_STATUS_OK" );
                    // Setting the map center
                    map.setCenter(new LatLng(41.85, -87.65));
                    // Setting initial zoom value
                    map.setZoom(PREFERRED_ZOOM);
                    // Creating a map options object
                    MapOptions options = new MapOptions();
                    // Creating a map type control options object
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    // Changing position of the map type control
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    // Setting map type control options
                    options.setMapTypeControlOptions(controlOptions);
                    // Setting map options
                    map.setOptions(options);
                    
                    //performGeocode(defaultFrom);

                    com.teamdev.jxmaps.Marker marker = new com.teamdev.jxmaps.Marker(map);
                    // Setting marker position
                    marker.setPosition(map.getCenter());
                    map.addEventListener("click", new MapMouseEvent() {
                        @Override
                        public void onEvent(com.teamdev.jxmaps.MouseEvent mouseEvent) {
                            // Creating a new marker
                            final com.teamdev.jxmaps.Marker marker = new com.teamdev.jxmaps.Marker(map);
                            // Move marker to the position where user clicked
                            marker.setPosition(mouseEvent.latLng());

                            // Adding event listener that intercepts clicking on marker
                            marker.addEventListener("click", new MapMouseEvent() {
                                @Override
                                public void onEvent(com.teamdev.jxmaps.MouseEvent mouseEvent) {
                                    // Removing marker from the map
                                    marker.remove();
                                }
                            });
                        }
                    });
                }
                
            }
        });
        
    }
    
    public JTextField getFromField(){
        return fromField;
    }
    
    public JTextField getToField(){
        return fromField;
    }
    
    @Override
    public JComponent getControlPanel() {
        return controlPanel;
    }

    /**
     * Creates, defines the Directions control-panel that will let the user
     * give start and finish points of a route. It also creates the control-panel
     * itself with the required buttons, icons, etc.
     * 
     */
    @Override
    public void configureControlPanel() {
        performGeocode(fromField.getText());
        
        controlPanel.setBackground(Color.white);
        controlPanel.setLayout(new BorderLayout());

        JPanel demoControlPanel = new JPanel(new GridBagLayout());
        demoControlPanel.setBackground(new Color(61, 130, 248));

        Font robotoPlain13 = new Font("Roboto", 0, 13);
        fromField.setForeground(FOREGROUND_COLOR);
        toField.setForeground(FOREGROUND_COLOR);

        fromField.setFont(robotoPlain13);
        toField.setFont(robotoPlain13);

        fromField.setOpaque(false);
        toField.setOpaque(false);

        fromField.setBorder(new UnderscoreBorder());
        toField.setBorder(new UnderscoreBorder());

        fromField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculateDirection();
                performGeocode(fromField.getText());
                updateFromFieldText(fromField.getText());
            }
        });
        
        toField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculateDirection();
                performGeocode(toField.getText());
                updateToFieldText(toField.getText());
            }
        });

        //!!!meg kell figyelni, történt-e változás, különben értelmetlen mindig kiírni ^
        
        // defining the Directions controller tools and their usage, while also 
        // defining their layouts
        JLabel fromIcon = new JLabel(new ImageIcon(DirectionsGeocoder.class.getResource("res/from.png")));
        JLabel dotsIcon = new JLabel(new ImageIcon(DirectionsGeocoder.class.getResource("res/dots.png")));
        JLabel toIcon = new JLabel(new ImageIcon(DirectionsGeocoder.class.getResource("res/to.png")));
        JLabel changeIcon = new JLabel(new ImageIcon(DirectionsGeocoder.class.getResource("res/change.png")));
        changeIcon.setToolTipText("Reverse starting point and destination");
        changeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String from = fromField.getText();
                String to = toField.getText();
                fromField.setText(to);
                toField.setText(from);
                calculateDirection();
            }
        });

        demoControlPanel.add(fromIcon, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(22, 30, 0, 0), 0, 0));
        demoControlPanel.add(dotsIcon, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(2, 33, 0, 0), 0, 0));
        demoControlPanel.add(toIcon, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(6, 30, 25, 0), 0, 0));

        demoControlPanel.add(fromField, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(19, 22, 0, 0), 0, 0));
        demoControlPanel.add(toField, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(3, 22, 0, 0), 0, 0));

        demoControlPanel.add(changeIcon, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 22, 0, 22), 0, 0));

        controlPanel.add(demoControlPanel, BorderLayout.NORTH);
    }

    /**
     * Makes preferred height the pre-configured value.
     * @return
     */
    @Override
    public int getPreferredHeight() {
        return PREFERRED_HEIGHT;
    }

    class UnderscoreBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(FOREGROUND_COLOR);
            g.drawLine(0, height - 1, width, height - 1);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 5, 0);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.bottom = 5;
            return insets;
        }
    }

    public void calculateDirection() {
        // Getting the associated map object
        final Map map = getMap();
        // Creating a directions request
        DirectionsRequest request = new DirectionsRequest();
        // Setting of the origin location to the request
        request.setOriginString(fromField.getText());
        // Setting of the destination location to the request
        request.setDestinationString(toField.getText());
        // Setting of the travel mode
        request.setTravelMode(TravelMode.DRIVING);
        // Calculating the route between locations
        getServices().getDirectionService().route(request, new DirectionsRouteCallback(map) {
            @Override
            public void onRoute(DirectionsResult result, DirectionsStatus status) {
                // Checking of the operation status
                if (status == DirectionsStatus.OK) {
                    // Drawing the calculated route on the map
                    if(fromField.getText().equals(toField.getText())){
                        map.getDirectionsRenderer().setDirections(result);
                        performGeocode(fromField.getText());
                    }
                    else{
                        map.getDirectionsRenderer().setDirections(result);
                    }
                } else {
                    //!!! azért jelezni valahogy, ha nem sikerül a térképen útvonalat tervezni!
                    JOptionPane.showMessageDialog(DirectionsGeocoder.this, "Hiba az útvonalban\nkérem helyes adatokat adjon meg!");
                    performGeocode(defaultFrom);
                }
            }
        });
    }
    
    public static void addText(JTextArea messageBar, String text){
        messageBar.setText(messageBar.getText() + "\n" + text);
    }
    
    /**
     * Converts a pair of from address, to address to the following string:
     * <From: fromAddress> <To: toAddress> with adding the marks.
     * 
     * @param textFrom
     * @param textTo
     * @return the complete String
     */
    public String textFromTo(String textFrom, String textTo){
        return "<From: " + textFrom + "> <To: " + textTo + ">";
    }
    
    /**
     * Creates a route according to the current state of the UI, saving the 
     * route to the database. It also sends a status message to the message bar
     * on the map.
     * 
     * @param messageBar
     */
    public void createRoute(JTextArea messageBar){
        ManageDatabase manageDatabase = new ManageDatabase();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String textFrom = fromField.getText();
        String textTo = toField.getText();
        manageDatabase.createRoute(textFromTo(textFrom,textTo), 
                "ahorvath", textFrom, textTo , 1, dateFormat.format(date), true);
        String text = "Sikeres mentés: " + "[From:] " + textFrom + " [To:] " + textTo;
        addText(messageBar, text);
        JOptionPane.showMessageDialog(null, text);
    }
    
    /**
     * Creates a marker in the DataBase with it's given description and 
     * markerType
     * 
     * @param messageBar
     * @param description
     * @param markerType
     */
    public void createMarker(JTextArea messageBar, String description, String markerType){
        ManageDatabase manageDatabase = new ManageDatabase();
        manageDatabase.createMarker(description, markerType);
        String text = "Sikeres mentés: " + markerType;
        addText(messageBar, text);
        JOptionPane.showMessageDialog(null, text);
    }
    
    /**
     * Modifying an existing (the currently displayed) route with the current
     * start and end-points
     * 
     * @param route
     * @param messageBar
     */
    public void modifyRoute(Route route, JTextArea messageBar){
        ManageDatabase manageDatabase = new ManageDatabase();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String textFrom = fromField.getText();
        String textTo = toField.getText();
        manageDatabase.modifyRoute(route.getRouteID(),textFromTo(textFrom,textTo), 
                "ahorvath", textFrom, textTo, 1, dateFormat.format(date), true);
        String text = "Sikeres mentés:\n"
                + "Erről:\n" + route.getRouteName() + "\nErre:\n[From:] " + textFrom + " [To:] " + textTo;
        addText(messageBar, text);
        JOptionPane.showMessageDialog(null, text);
    }
    
    public void openRoute(Route route, JTextArea messageBar){
        fromField.setText(route.getStartPoint());
        toField.setText(route.getFinishPoint());
        calculateDirection();
        String text = "Sikeres betöltés: " + route.getRouteName();
        addText(messageBar, text);
        JOptionPane.showMessageDialog(null, text);
    }
    
    public void deleteRoute(Route route, JTextArea messageBar){
        ManageDatabase manageDatabase = new ManageDatabase();
        manageDatabase.deleteRoute(route.getRouteID());
        String text = "Sikeres törlés:\n"
                + textFromTo(route.getStartPoint(),route.getFinishPoint())
                + "\n(ID " + route.getRouteID() + ")";
        addText(messageBar,text);
        JOptionPane.showMessageDialog(null, text);
    }

    static void loadAndRegisterCustomFonts() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, DirectionsGeocoder.class.getResourceAsStream("res/Roboto-Bold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, DirectionsGeocoder.class.getResourceAsStream("res/Roboto-Medium.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, DirectionsGeocoder.class.getResourceAsStream("res/Roboto-Regular.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, DirectionsGeocoder.class.getResourceAsStream("res/Roboto-Thin.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, DirectionsGeocoder.class.getResourceAsStream("res/Roboto-Light.ttf")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private void performGeocode(String text) {
        // Getting the associated map object
        final Map map = getMap();
        // Creating a geocode request
        GeocoderRequest request = new GeocoderRequest();
        // Setting address to the geocode request
        request.setAddress(text);

        // Geocoding position by the entered address
        getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
            @Override
            public void onComplete(GeocoderResult[] results, GeocoderStatus status) {
                // Checking operation status
                if ((status == GeocoderStatus.OK) && (results.length > 0)) {
                    // Getting the first result
                    GeocoderResult result = results[0];
                    // Getting a location of the result
                    LatLng location = result.getGeometry().getLocation();
                    // Setting the map center to result location
                    //map.setCenter(location);
                    // Creating a marker object
                    //Marker marker = new Marker(map);
                    // Setting position of the marker to the result location
                    //marker.setPosition(location);
                    // Creating an information window
                    InfoWindow infoWindow = new InfoWindow(map);
                    // Putting the address and location to the content of the information window
                    infoWindow.setContent("<b>" + result.getFormattedAddress() + "</b><br>" + location.toString());
                    // Moving the information window to the result location
                    infoWindow.setPosition(location);
                    // Showing of the information window
                    infoWindow.open(map);
                }
            }
        });
    }
    
    public void updateFromFieldText(String text){
        // Getting the associated map object
        final Map map = getMap();
        // Creating a geocode request
        GeocoderRequest request = new GeocoderRequest();
        // Setting address to the geocode request
        request.setAddress(text);

        // Geocoding position by the entered address
        getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
            @Override
            public void onComplete(GeocoderResult[] results, GeocoderStatus status) {
                // Checking operation status
                if ((status == GeocoderStatus.OK) && (results.length > 0)) {
                   GeocoderResult result = results[0];
                   fromField.setText(result.getFormattedAddress());
                }
            }
        });
    }
    
    public void updateToFieldText(String text){
        // Getting the associated map object
        final Map map = getMap();
        // Creating a geocode request
        GeocoderRequest request = new GeocoderRequest();
        // Setting address to the geocode request
        request.setAddress(text);

        // Geocoding position by the entered address
        getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
            @Override
            public void onComplete(GeocoderResult[] results, GeocoderStatus status) {
                // Checking operation status
                if ((status == GeocoderStatus.OK) && (results.length > 0)) {
                   GeocoderResult result = results[0];
                   toField.setText(result.getFormattedAddress());
                }
            }
        });
    }
}
