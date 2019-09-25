import java.io.*;

public class Tour {
    Node head;

    // creates an empty tour
    public Tour()                                    
    {
        head = null;
    }

    // creates the 4-point tour a->b->c->d->a (for debugging)
    public Tour(Point a, Point b, Point c, Point d)  
    {

        head = new Node(a);
        Node bN = new Node(b);
        Node cN = new Node(c);
        Node dN = new Node(d);

        head.next = bN;
        bN.next = cN;
        cN.next = dN;
        dN.next = head;
    }

    public static String author(){
        return ("WANG, WILLIAM");
    }

    // returns the number of points in this tour
    public int size()
    {
        if(head == null){
            return 0;
        }
        int size = 1;
        Node current = head;
        while(current.next != head){
            size++;
            current = current.next;
        }
        return size;
    }

    // returns the length of this tour
    public double length()
    {
        if(head == null){
            return 0;
        }

        double length = 0;
        double distance;
        Node current = head;

        while(current.next != head){
            distance = current.p.distanceTo(current.next.p);
            length += distance;
            current = current.next;
        }
        length += current.p.distanceTo(head.p);


        return length;
    }

    // returns a string representation of this tour
    public String toString()
    {
        if (head == null) {
            return "";
        }
        else if (head.next == head) {
            return head.p.toString();
        }

        String s = "";
        Node current = head;
        while(current.next != head){
            s += current.p.toString() + "\n";
            current = current.next;
        }
        s += current.p.toString();
        return s;
    }

    // draws this tour to standard drawing
    public void draw()
    {
        Node current = head;
        boolean isFirst = true;
        while(current != head || isFirst) {
            isFirst = false;
            current.p.draw();
            current.next.p.draw();
            current.p.drawTo(current.next.p);
            current = current.next;
        }
    }

    // inserts p using the nearest neighbor heuristic
    public void insertNearest(Point p)
    {
        if (HandleBaseInsert(p)) { return; }

        double distance;
        double min = Integer.MAX_VALUE;

        Node current = head;
        Node minNode = current;
        boolean isFirst = true;
        while(current != head || isFirst == true) {
            isFirst = false;
            distance = current.p.distanceTo(p);
            if(distance < min){
                min = distance;
                minNode = current;
            }
            current = current.next;
        }

        Node s = new Node(p);

        Node tail = minNode.next;
        minNode.next = s;
        s.next = tail;
    }

    // inserts p using the smallest increase heuristic
    public void insertSmallest(Point p)                   
    {
        if (HandleBaseInsert(p)) { return; }

        Node current = head;
        boolean isFirst = true;
        double minLength = Double.MAX_VALUE;
        int minIndex = 0;
        Node tail;
        Node pN = new Node(p);
        int currentIndex = 0;

        while(current != head || isFirst == true){
            isFirst = false;
            tail = current.next;
            current.next = pN;
            pN.next = tail;
            double currentMin = length();

            if(currentMin < minLength){
                minIndex = currentIndex;
                minLength = currentMin;
            }

            current.next = tail;
            current = tail;
            currentIndex++;
        }

        current = head;
        for(int i = 0; i < minIndex; i++){
            current = current.next;
        }

        tail = current.next;
        current.next = pN;
        pN.next = tail;
    }

    private boolean HandleBaseInsert(Point p)
    {
        if (head == null)
        {
            head = new Node(p);
            head.next = head;
            return true;
        }

        if (head == head.next)
        {
            Node s = new Node(p);
            head.next = s;
            s.next = head;
            return true;
        }

        return false;
    }

   //tests this class by directly calling all constructors and methods
    public static void main(String[] args)
    {
        String path = "./input/tsp10.txt";
        String output = "./input/ans.txt";
        boolean useFile = true;
        boolean isSmallest = false;
        Tour tour;

        if (useFile) {
            tour = LoadFromFile(path, isSmallest);
        }
        else {
            Point a = new Point(100.0, 100.0);
            Point b = new Point(500.0, 100.0);
            Point c = new Point(500.0, 500.0);
            Point d = new Point(100.0, 500.0);

            tour = new Tour(a, b, c, d);
        }
        int size = tour.size();
        System.out.println("Number of points = " + size);

        double length = tour.length();
        System.out.println("Tour length = " + length);

        System.out.println(tour);

        WriteTourToFile(output, tour);
        tour.draw();
        StdDraw.filledSquare(400,400,30);
        StdDraw.show();
    }

    private static Tour LoadFromFile(String path, boolean isSmallest)
    {

        File f;
        FileReader fr;
        BufferedReader br;
        Tour tour = new Tour();

        try {
            f = new File(path);
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            double width;
            double height;

            int readResult = 0;
            String[] widthHeightLine = br.readLine().trim().split((" "));
            width = Double.parseDouble(widthHeightLine[0]);
            height = Double.parseDouble(widthHeightLine[1]);

            while (readResult == 0)
            {
                String currentLine;
                try {
                    currentLine = br.readLine().trim();
                }
                catch (Exception e) {
                    readResult = 1;
                    continue;
                }
                String[] currentXYPair = currentLine.split(" ");
                double x = Double.parseDouble(currentXYPair[0]);
                double y = Double.parseDouble(currentXYPair[1]);
                Point p = new Point(x, y);
                if (isSmallest) {
                    tour.insertSmallest(p);
                }
                else {
                    tour.insertNearest(p);
                }
            }
            br.close();
            fr.close();

            StdDraw.setCanvasSize((int)width, (int)height);

            StdDraw.setXscale(0, width);
            StdDraw.setYscale(0, height);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);

        }
        catch (Exception e) {
            System.out.println("Uh oh");
        }


        return tour;
    }

    private static void WriteTourToFile(String path, Tour tour) {
        File f;
        FileWriter fw;
        PrintWriter pw;
        try {
            f = new File(path);
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);

            pw.println(tour.toString());
            pw.println("Tour length = " + tour.length());
            pw.close();
            fw.close();
        }
        catch (Exception e) {
            System.out.println("Uh oh....");
        }
    }

    private class Node {
        private Point p;
        private Node next;
        public Node(){
            p = null;
            next = null;
        }

        public Node(Point p){
            this.p = p;
            next = null;
        }



        public Node(Point p, Node next){
            this.p = p;
            this.next = next;
        }

    }

}
