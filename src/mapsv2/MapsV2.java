package mapsv2;

// Title: Greedy Map Colorization
//
// Authors: **InuyashaITB**
//
// Date Modified: 11/30/2015
//
// Description: This program takes an input of states, correlated with their adjacent 
//              states separated by spaces. It then also imports a list of Colors
//              to be used to colorize these states, while making sure that no two
//              adjacent states share the same color. Another rule that it follows
//              is that the last color imported will be the color used the least
//              number of times.
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class State{
    
    //Attributes
    //To be allocated later, in use with the colors.txt file
    static LinkedList<String> colors = new LinkedList<>();
    
    //These colors are defaulted if no colors are specified in the Colors.txt
    static int NOCOLOR = 0;
    static int BLUE = 1;
    static int GREEN = 2;
    static int RED = 3;
    static int YELLOW = 4;

    //The Map of states is simply for an abbreviation to Full-Name conversion
    static Map<String, String> states = new HashMap<>();

    //Specific attributes of states contains an abbreviation, color, and their
    //adjacent states
    public String abr;
    public String color;
    public LinkedList<String> adjacentStates;

    //Default constructor, if someone wanted to make an empty state
    State() {
        abr = null;
        color = "";
        adjacentStates = new LinkedList<>();

        makeKeyMap();
    }

    //Simple constructor that sets a state by its abbreviation
    State(String name) {
        this.abr = name;
        color = "";
        adjacentStates = new LinkedList<>();
        
        makeKeyMap();
    }
    
    //Builds the united states HashMap of Abbreviation to Full Name
    private void makeKeyMap(){
        
        states.put("AL", "Alabama");
        states.put("AK", "Alaska");
        states.put("AB", "Alberta");
        states.put("AS", "American Samoa");
        states.put("AZ", "Arizona");
        states.put("AR", "Arkansas");
        states.put("Armed Forces (AE)", "AE");
        states.put("Armed Forces Americas", "AA");
        states.put("Armed Forces Pacific", "AP");
        states.put("British Columbia", "BC");
        states.put("CA", "California");
        states.put("CO", "Colorado");
        states.put("CT", "Connecticut");
        states.put("DE", "Delaware");
        states.put("District Of Columbia", "DC");
        states.put("FL", "Florida");
        states.put("GA", "Georgia");
        states.put("Guam", "GU");
        states.put("Hawaii", "HI");
        states.put("ID", "Idaho");
        states.put("IL", "Illinois");
        states.put("IN", "Indiana");
        states.put("IA", "Iowa");
        states.put("KS", "Kansas");
        states.put("KY", "Kentucky");
        states.put("LA", "Louisiana");
        states.put("ME", "Maine");
        states.put("Manitoba", "MB");
        states.put("MD", "Maryland");
        states.put("MA", "Massachusetts");
        states.put("MI", "Michigan");
        states.put("MN", "Minnesota");
        states.put("MS", "Mississippi");
        states.put("MO", "Missouri");
        states.put("MT", "Montana");
        states.put("NE", "Nebraska");
        states.put("NV", "Nevada");
        states.put("New Brunswick", "NB");
        states.put("NH", "New Hampshire");
        states.put("NJ", "New Jersey");
        states.put("NM", "New Mexico");
        states.put("NY", "New York");
        states.put("Newfoundland", "NF");
        states.put("NC", "North Carolina");
        states.put("ND", "North Dakota");
        states.put("Northwest Territories", "NT");
        states.put("Nova Scotia", "NS");
        states.put("Nunavut", "NU");
        states.put("OH", "Ohio");
        states.put("OK", "Oklahoma");
        states.put("Ontario", "ON");
        states.put("OR", "Oregon");
        states.put("PA", "Pennsylvania");
        states.put("Prince Edward Island", "PE");
        states.put("Puerto Rico", "PR");
        states.put("Quebec", "PQ");
        states.put("RI", "Rhode Island");
        states.put("Saskatchewan", "SK");
        states.put("SC", "South Carolina");
        states.put("SD", "South Dakota");
        states.put("TN", "Tennessee");
        states.put("TX", "Texas");
        states.put("UT", "Utah");
        states.put("VT", "Vermont");
        states.put("Virgin Islands", "VI");
        states.put("VA", "Virginia");
        states.put("WA", "Washington");
        states.put("WV", "West Virginia");
        states.put("WI", "Wisconsin");
        states.put("WY", "Wyoming");
        states.put("Yukon Territory", "YT");
    }

    //This section imports a file called "colors.txt" so that the user can color
    //each state with whatever colors they want.
    public static boolean buildColors() {
        String runningDirectory = System.getProperty("user.dir");
        File f = new File(runningDirectory + "\\colors.txt");
        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) {
                    colors.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            colors.add("BLUE");
            colors.add("GREEN");
            colors.add("RED");
            colors.add("YELLOW");

            return false;
        }
    }

    //simply gets the abbreviation
    public String getName() {
        return abr;
    }

    //simply sets the abbreviation
    public void setName(String name) {
        this.abr = name;
    }

    //simply gets the Color
    public String getColor() {
        return color;
    }

    //simply sets the Color
    public void setColor(String color) {
        this.color = color;
    }

    //Simply adds a state to the adjacent list
    public void addAdj(String s) {
        this.adjacentStates.add(s);
    }

    //Returns a list of the adjacent states
    public LinkedList<String> getAdj() {
        return this.adjacentStates;
    }

    //Allows comporable states by means of their abbreviated names
    public int compareTo(State s) {
        return (this.abr.compareTo(s.abr));
    }

    //Allows the user to access the Map in order to retreive the Full Name of a 
    //given State by its Abbreviation
    public static String getFullName(String abrev) {
        return states.get(abrev);
    }

    //Same as above, but in a non-static (dynamic) method.
    public String getFullName() {
        return states.get(abr);
    }

    /**
     * This toString returns a string that is of the format 'State 
     * Abbreviation : Color'
     * @return
     */
    @Override
    public String toString() {
        return (abr + ":" + (color));
    }

    //Returns the actual Color Object of the state
    public static Color colorOf(String color) {
        try {
            return (Color) Color.class.getDeclaredField(color).get(null);
        } catch (Exception e) {
            return null; // ??
        }

    }
}

public class MapsV2 {
    
    //Attributes
    //Defined colors for ANSI outputs (Does not work in normal .txt files, but does work in UNIX systems)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static NavigableMap<String, Integer> colorMap = new TreeMap<>();
    LinkedList<String> countColors;

    //There shall be no more than 49 adjacent states in America. This can be modified if necesarry to add more depending on country.
    //However, due to this example ONLY including America, 49 shall be the max. (Including DC)
    static LinkedList<String> adj = new LinkedList<>();
    LinkedList<State> states;

    //Constructor allocates space for the linkedlist of sates
    //For a better explanation of what a "State" is defined as, look at State.java
    public MapsV2() {
        State.buildColors();
        this.states = new LinkedList<>();
        this.countColors = new LinkedList<>();
    }

    //Create the map of States objects
    private void buildMap() {
        String[] temp;
        //java.util.Arrays.sort(adj);
        State.buildColors();
        for (String key : adj) //Build the LL of states + adjacents
        {
            temp = key.split(" ");
            State s = new State();
            s.setName(temp[0]);
            int count = 0;
            for (String a : temp) // Adds the Adjacents to each state
            {
                if (a != null && count != 0) // Possible null entries from array, ignore them
                {
                    s.addAdj(a);
                }
                count++;
            }

            states.add(s);

        }
    }

    //Colorize algorithm
    private boolean Colorize() {

        for (State s : states) {
            LinkedList<String> usedColors = new LinkedList();
            LinkedList<String> temp = s.getAdj();
            if (s.getColor().equals("")) //No color defined yet
            {
                for (String key : temp) {
                    for (State k : states) {
                        if (k.getName().equals(key)) {
                            usedColors.add(k.getColor());
                        }
                    }
                } //Completed used Colors list

                for (String color : s.colors) {

                    if (!usedColors.contains(color)) {
                        s.setColor(color);
                        countColors.add(color);
                        break;
                    }

                }
                if (s.getColor().equalsIgnoreCase("")) {
                    //If the color does not set, we did not colorize properly
                    return false;
                }

            }
        }
        //At this point we have gone through the entire set of adj states
        //All states have been colored at this point
        return true;
    }

    /**
     * Returns the State List with its corresponding State and Color in string
     * format
     *
     * @return
     */
    @Override
    public String toString() {
        String STUB = "", COLOR = "";
        for (State s : states) {
            COLOR = "";
            if (s.getColor().equalsIgnoreCase("BLACK")) {
                COLOR = ANSI_BLACK;
            }
            if (s.getColor().equalsIgnoreCase("RED")) {
                COLOR = ANSI_RED;
            }
            if (s.getColor().equalsIgnoreCase("GREEN")) {
                COLOR = ANSI_GREEN;
            }
            if (s.getColor().equalsIgnoreCase("YELLOW")) {
                COLOR = ANSI_YELLOW;
            }
            if (s.getColor().equalsIgnoreCase("BLUE")) {
                COLOR = ANSI_BLUE;
            }
            if (s.getColor().equalsIgnoreCase("PURPLE")) {
                COLOR = ANSI_PURPLE;
            }
            if (s.getColor().equalsIgnoreCase("CYAN")) {
                COLOR = ANSI_CYAN;
            }
            if (s.getColor().equalsIgnoreCase("WHITE")) {
                COLOR = ANSI_WHITE;
            }

            STUB += COLOR + s.toString() + ANSI_RESET + "\n";
        }
        return STUB;
    }

    //Simply reads the state adjacent list and fills our object 
    //with the necessary information
    private static boolean LoadStates(String n) {
        File f = new File("adjacent.txt");
        if (f.exists()) {
            System.out.println("Loading " + n);
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) {
                    adj.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Loading Complete\n");
            return true;
        } else {
            System.out.println("Could not find " + f.getAbsolutePath());
            return false;
        }
    }

    //This method is not in use for this project, however is saved here for demonstration purposes
    private void XMLColorize() {
        //1. Create the frame.
        JFrame frame = new JFrame("FrameDemo");

//2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//4. Size the frame.
        frame.pack();
        int frameWidth = 800;
        int frameHeight = 600;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);

        JPanel panel = new JPanel();

        String[] XML = new String[5000];
        try {
            int i = 0;
            String line = "";

            BufferedReader br = new BufferedReader(new FileReader(new File("states.xml")));
            while ((line = br.readLine()) != null) {
                XML[i] = line;
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        double[] arr1 = new double[]{0.0, 1.1, 2.2};
        double[] arr2 = new double[]{0.0, 1.1, 2.2};
        for (State s : states) {
            try {
                String hex = "";
                try {
                    Color aColor = (Color) Color.class.getField(s.getColor().toUpperCase()).get(null);
                    hex = String.format("#%02x%02x%02x", aColor.getRed(), aColor.getGreen(), aColor.getBlue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int i = 0;
                while (true) {
                    if (XML[i].contains(s.getFullName())) {
                        XML[i] = "<state name =\"" + s.getFullName() + "\" colour=\""
                                + hex + "\" >";
                        drawPoly(panel.getGraphics(), arr1, arr2);
                        frame.getContentPane().add(panel);
                        frame.add(panel);
                        break;

                    }
                    i++;
                }
            } catch (Exception E) {
            };

        }

//5. Show the GUI (Again, not in use for this project)
        frame.setVisible(true);
    }

    //This is also not in use for the project
    private void drawPoly(Graphics g, double[] x1Points, double[] y1Points) {
        Graphics2D g2 = (Graphics2D) g;
        // draw GeneralPath (polygon)

        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);

        polygon.moveTo(x1Points[0], y1Points[0]);

        for (int index = 1; index < x1Points.length; index++) {
            polygon.lineTo(x1Points[index], y1Points[index]);
        }

        polygon.closePath();
        g2.draw(polygon);
    }

    //Optionally sorts the Adjacent List by number of adjacent states
    public void sortAdjacentList() {
        int[] countAdj = new int[adj.size()];
        int i = 0;
        String[] t;
        for (String s : adj) {
            t = s.split(" ");
            countAdj[i] = t.length;
            i++;
        }

        for (i = 0; i < adj.size(); i++) {
            int temp = countAdj[i];
            String temp2 = adj.get(i);
            int j;
            for (j = i - 1; j >= 0 && temp > countAdj[j]; j--) {
                countAdj[j + 1] = countAdj[j];
                adj.set(j + 1, adj.get(i));
            }
            countAdj[j + 1] = temp;
            adj.set(j + 1, temp2);
        }
    }

    //Method attributed by professor S. Pham
    public static boolean writeFile(MapsV2 O) {
        PrintWriter pw = null;
        try {
            File file = new File("Output.txt");
            file.delete();
            FileWriter fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
            for (State a : O.states) {
                pw.println(a.toString());
            }
            pw.println();
            pw.println(colorMap);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
            return true;
        }
    }

    public static void decolorize(MapsV2 map) {
        for (State a : map.states) {
            a.setColor("");
        }
        map.countColors.clear();
    }

    //The main method shows the implementation of the Maps coloring algorithm.
    public static void main(String[] args) {
        String FileName = "adjacent.txt";
        if (!LoadStates(FileName)) {
            System.exit(-1);
        }

        final MapsV2 map = new MapsV2();
        map.buildMap();
        int iteration = 0;
        long startTime = System.nanoTime();
        while (true) {
            
            while (!map.Colorize()) {
                decolorize(map);
                Collections.shuffle(map.states);
            }
            for (String col : State.colors) {
                colorMap.put(col, 0);
            }

            for (State st : map.states) {
                colorMap.put(st.color, colorMap.get(st.color) + 1);
            }
            
            if(iteration == 0){
                iteration = colorMap.lastEntry().getValue() + 1;
            }
            long duration = (System.nanoTime() - startTime)/1000000;
            if (colorMap.lastEntry().getValue() < iteration) {
                System.out.println(map);
                System.out.println(colorMap);
                if (writeFile(map)) {
                    System.out.println("Map output to --> \"output.txt\"");
                } else {
                    System.out.println("Failed to output map");
                }
                iteration = colorMap.lastEntry().getValue();
                decolorize(map);
                Collections.shuffle(map.states);
                
            } else {
                decolorize(map);
                Collections.shuffle(map.states);
            }
            if(duration > 60*1000 || iteration <= 0){
                System.out.println("End reached, ending program");
                break;
            }
        }

    }

}
