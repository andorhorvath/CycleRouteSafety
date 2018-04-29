package cycleroutesafety;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static final int MIN_ZOOM = 0;
    public static final int MAX_ZOOM = 21;
    public static Route[] allRoutes;
    public static Marker[] allMarkers;
    public static Poi[] allPois;
    private static final int ZOOM_VALUE = 10; //map.html value
    public static Color off = Color.lightGray;
    public static Color on = Color.white;
    public static String markerType = "res/dots.png";
    
    public static void addText(JTextArea messageBar, String text){
        messageBar.setText(messageBar.getText() + "\n" + text);
    }
    
    public static void main(String[] args) {
        
        ManageDatabase manageDatabase = new ManageDatabase();
        
        DirectionsGeocoder.loadAndRegisterCustomFonts();
        final DirectionsGeocoder map = new DirectionsGeocoder();
        
        final JTextField addressBar = new JTextField();
        addressBar.setPreferredSize(new Dimension(300,30));
        
        JTextArea messageBar = new JTextArea();
        messageBar.setText("Betöltés kész!");
        
        JPanel messagePanel = new JPanel();
        messagePanel.add(messageBar);
        
        final JTextField loadedRoute = new JTextField();
        loadedRoute.setEditable(false);
        String text = map.textFromTo(map.getFromField().getText(),map.getToField().getText());
        loadedRoute.setText(text);

        JPanel addressPane = new JPanel(new FlowLayout());
        addressPane.add(new JLabel(" Betöltött útvonal: "));
        addressPane.add(loadedRoute);
        
        JButton createRoute = new JButton("Mentés új útvonalként");
        createRoute.setPreferredSize(new Dimension(150, 30));
        createRoute.setBorder(null);
        createRoute.setBackground(new Color(66,133,244));
        createRoute.setForeground(Color.WHITE);
        createRoute.setOpaque(true);

        
        JButton modifyRoute = new JButton("Módosítások mentése");
        modifyRoute.setPreferredSize(new Dimension(150, 30));
        modifyRoute.setBorder(null);
        modifyRoute.setForeground(Color.WHITE);
        modifyRoute.setBackground(new Color(66,133,244));
        //modifyRoute.setEnabled(false);
        modifyRoute.setOpaque(true);
        allRoutes = manageDatabase.readRoutes();
        
        Choice allRoutesList = new Choice();
        allRoutesList.setPreferredSize(new Dimension(200, 60));
        allRoutesList.setForeground(Color.WHITE);
        allRoutesList.setBackground(new Color(66,133,244));
        allRoutesList.add("----- Válassz! -----");
        for(int i=0; i<allRoutes.length; ++i){
            allRoutesList.add(allRoutes[i].getRouteName());
        }
        
        JButton openRoute = new JButton("Útvonal betöltése");
        openRoute.setPreferredSize(new Dimension(150, 30));
        openRoute.setBorder(null);
        openRoute.setForeground(Color.WHITE);
        openRoute.setBackground(new Color(66,133,244));
        openRoute.setOpaque(true);
        
        JButton deleteRoute = new JButton("Útvonal törlése");
        deleteRoute.setPreferredSize(new Dimension(150, 30));
        deleteRoute.setBorder(null);
        deleteRoute.setForeground(Color.WHITE);
        deleteRoute.setBackground(new Color(66,133,244));
        deleteRoute.setOpaque(true);
        
        JButton clearRoute = new JButton("Betöltés törlése");
        clearRoute.setPreferredSize(new Dimension(150, 30));
        clearRoute.setBorder(null);
        clearRoute.setForeground(Color.WHITE);
        clearRoute.setBackground(new Color(66,133,244));
        clearRoute.setOpaque(true);
        
        createRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                map.createRoute(messageBar);
                allRoutes = manageDatabase.readRoutes();
                allRoutesList.removeAll();
                allRoutesList.add("----- Válassz! -----");
                for(int i=0; i<allRoutes.length; ++i){
                    allRoutesList.add(allRoutes[i].getRouteName());
                }
                //az utolsó elem kiválasztása
                allRoutesList.select(allRoutesList.getItem(allRoutesList.getItemCount()-1));
            }
        });
        
        modifyRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int i = allRoutesList.getSelectedIndex();
                if(i>0){
                    //!!! Beolvasni a weboldalról az adatokat!!!
                    map.modifyRoute(allRoutes[i-1], messageBar);
                    allRoutes = manageDatabase.readRoutes();
                    allRoutesList.removeAll();
                    allRoutesList.add("----- Válassz! -----");
                    for(int j=0; j<allRoutes.length; ++j){
                        allRoutesList.add(allRoutes[j].getRouteName());
                    }
                    allRoutesList.select(i);
                    loadedRoute.setText(allRoutesList.getSelectedItem());
                }
            }
        });
        
        openRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int i=allRoutesList.getSelectedIndex();
                if(i>0){
                    loadedRoute.setText("");
                    map.openRoute(allRoutes[i-1],messageBar);
                    //modifyRoute.setEnabled(true);
                    loadedRoute.setText(allRoutesList.getSelectedItem());
                }
                else{
                    addText(messageBar,"Nincs mit megnyitni");
                    JOptionPane.showMessageDialog(null, "Nincs mit megnyitni!");
                    modifyRoute.setEnabled(false);
                    loadedRoute.setText("");
                    //modifyRoute.setEnabled(false);
                }
            }
        });
        
        deleteRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // sorszám kiolvasása
                int i = allRoutesList.getSelectedIndex();
                if(i>0){
                    map.deleteRoute(allRoutes[i-1], messageBar);
                }
                else{
                    String text = "Nincs törlésre kijelölt elem.";
                    addText(messageBar,text);
                    JOptionPane.showMessageDialog(null, text);
                }
                allRoutes = manageDatabase.readRoutes();
                allRoutesList.removeAll();
                allRoutesList.add("----- Válassz! -----");
                for(i=0; i<allRoutes.length; ++i){
                    allRoutesList.add(allRoutes[i].getRouteName());
                }
            }
        });
        
        clearRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //az első elem kiválasztása
                allRoutesList.select(allRoutesList.getItem(1));
                loadedRoute.setText("");
            }
        });
        
        allRoutesList.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int i = allRoutesList.getSelectedIndex();
                if(i>0){
                    openRoute.setEnabled(true);
                    modifyRoute.setEnabled(true);
                    deleteRoute.setEnabled(true);
                }
                else{
                    openRoute.setEnabled(false);
                    modifyRoute.setEnabled(false);
                    deleteRoute.setEnabled(false);
                }
            }
        });
        
        //createRoute.setEnabled(false);
        //openRoute.setEnabled(false);
        //modifyRoute.setEnabled(false);
        //deleteRoute.setEnabled(false);
        
        JPanel routeTools = new JPanel();
        routeTools.add(createRoute);
        routeTools.add(new JLabel("Műveletek meglévő útvonalakkal"));
        routeTools.add(allRoutesList);
        routeTools.add(openRoute);
        routeTools.add(modifyRoute);
        routeTools.add(deleteRoute);
        routeTools.add(clearRoute);
        routeTools.setLayout(new GridLayout(routeTools.getComponentCount(),1,1,1));
        
        allMarkers = manageDatabase.readMarkers();
        JPanel allMarkersList = new JPanel();
        int row = (int)Math.floor(allMarkers.length/4);
        if(row<1) row=1;
        int col = (int)Math.floor(allMarkers.length/row);
        allMarkersList.setLayout(new GridLayout(col,row,1,1));
        JButton[] markers = new JButton[allMarkers.length];
        for(int i=0; i<allMarkers.length; ++i){
            markers[i] = new JButton();
        }
        for(int i=0; i<allMarkers.length; ++i){
            JButton marker = markers[i];
            //System.out.println("## DEBUG ## allMarkers[i].getMarkerType() (i-edik korre): " + allMarkers[i].getMarkerType());
            //String stringem = allMarkers[i].getMarkerType();
            ImageIcon icon = new ImageIcon(DirectionsGeocoder.class.getResource(allMarkers[i].getMarkerType()));
            //ImageIcon icon = new ImageIcon(allMarkers[i].getMarkerType());
            marker.setIcon(icon);
            marker.setBackground(off);
            markers[i].setOpaque(true);
        }
        for(int i=0; i<allMarkers.length; ++i){
            JButton marker = markers[i];
            int ii=i;
            marker.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(marker.getBackground().equals(off)){
                        marker.setBackground(on);
                        marker.setSelected(true);
                        addText(messageBar, "Marker lerakása: " + allMarkers[ii].getMarkerDescription());
                        for(int j=0; j<markers.length; ++j){
                            if(j!=ii){
                                JButton m = markers[j];
                                m.setBackground(off);
                                m.setSelected(false);
                            }
                        }
                        //!!! megfelelő markertípus bekapcsolása, majd kattintásnál a térképen hozzá kell adni h azt rakja be
                        markerType = allMarkers[ii].getMarkerType();
                    }
                    else{
                        marker.setBackground(off);
                        marker.setSelected(false);
                        addText(messageBar, "Nincs kijelölve Marker");
                    }
                }
            });
        }
        for(int i=0; i<allMarkers.length; ++i){
            allMarkersList.add(markers[i]);
        }
        
        JPanel addMarker = new JPanel();
        JLabel desc = new JLabel("Leírás");
        JTextField description = new JTextField();
        description.setPreferredSize(new Dimension(150, 30));
        JLabel type = new JLabel("Képfájl helye (pl. res/dots.png)");
        JTextField markerType = new JTextField();
        markerType.setPreferredSize(new Dimension(150, 30));
        JButton saveMarker = new JButton("Mentés");
        addMarker.add(desc);
        addMarker.add(description);
        addMarker.add(type);
        addMarker.add(markerType);
        addMarker.add(saveMarker);
        addMarker.setLayout(new GridLayout(5,1));
        addMarker.setVisible(false);
        
        JButton createMarker = new JButton("Markertípus felvétele");
        createMarker.setPreferredSize(new Dimension(150, 30));
        createMarker.setBorder(null);
        createMarker.setForeground(Color.WHITE);
        createMarker.setBackground(new Color(66,133,244));
        createMarker.setOpaque(true);
        
        createMarker.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                addMarker.setVisible(true);
            }
        });
        
        saveMarker.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                map.createMarker(messageBar,description.getText(),markerType.getText());
                addMarker.setVisible(false);
            }
        });
        
        /*JButton deleteMarker = new JButton("Markertípus törlése");
        deleteMarker.setPreferredSize(new Dimension(150, 30));
        deleteMarker.setBorder(null);
        deleteMarker.setForeground(Color.WHITE);
        deleteMarker.setBackground(new Color(66,133,244));
        deleteMarker.setOpaque(true);*/
        
        JButton refreshPois = new JButton("Markerek frissítése a térképről");
        refreshPois.setPreferredSize(new Dimension(150, 30));
        refreshPois.setBorder(null);
        refreshPois.setForeground(Color.WHITE);
        refreshPois.setBackground(new Color(66,133,244));
        refreshPois.setOpaque(true);
        
        JPanel poiTools = new JPanel();
        poiTools.add(new JLabel("Markerek kezelése: "));
        poiTools.add(allMarkersList);
        poiTools.add(createMarker);
        //poiTools.add(deleteMarker);
        poiTools.add(refreshPois);
        poiTools.setLayout(new GridLayout(poiTools.getComponentCount(),1,1,1));
        
        JPanel allTools = new JPanel(new GridLayout(3,1));
        allTools.add(routeTools);
        allTools.add(poiTools);
        allTools.add(addMarker);
        
        //!!! meg kell valósítani a gombok tiltását, csak másképp
        /*browser.addTitleListener(new TitleListener() {
            @Override
            public void onTitleChange(TitleEvent te) {
                if(browser.getTitle().equals("Google Térkép")){
                    createRoute.setForeground(Color.LIGHT_GRAY);
                    createRoute.setEnabled(false);
                }
                else{
                    createRoute.setForeground(Color.WHITE);
                    createRoute.setEnabled(true);
                }
            }
        });*/
        
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(1200,600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(map, BorderLayout.CENTER);
        loadedRoute.setPreferredSize(new Dimension(frame.getWidth(),30));
        frame.add(addressPane, BorderLayout.NORTH);
        frame.add(allTools, BorderLayout.EAST);
        messageBar.setLineWrap(true);
        messageBar.setEditable(false);
        JScrollPane scrollV = new JScrollPane (messageBar);
        scrollV.setPreferredSize(new Dimension(900,60));
        scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollV,BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        
        new OptionsWindow(map, new Dimension(300, 100)) {
            @Override
            public void initContent(JWindow contentWindow) {
                frame.add(map.controlPanel,BorderLayout.WEST);
            }
        };
    }
    
}
