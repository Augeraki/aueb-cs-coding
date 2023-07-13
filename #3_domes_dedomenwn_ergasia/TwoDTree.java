import java.io.FileReader;
import java.util.SortedMap;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class TwoDTree{


    public static void main(String args[]) throws Exception{
        try{
            String file= args[0];
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            buffer.mark(1000000);
            int grammes = 0;

            while(buffer.readLine() != null){
                grammes++;
            }

            buffer.reset();
            String line;
            line = buffer.readLine();
            int shmeia = Integer.parseInt(line);

            if((shmeia + 1) != grammes){
                System.out.println("Asynepeia eisodou!");
                System.exit(0);
            }

            String synt_x[] = new String[shmeia];
            String synt_y[] = new String[shmeia];
            String split;
            for(int i = 0; i < shmeia; i++){
                split = buffer.readLine();
                String pin[] = split.split(" ");
                synt_x[i] = pin[0];
                synt_y[i] = pin[1];
            }
            buffer.close();
            boolean eisodoi = true;
            
            for(int i = 0; i < shmeia; i++){
                if(Integer.parseInt(synt_x[i]) > 100 || Integer.parseInt(synt_x[i]) < 0 || Integer.parseInt(synt_y[i]) > 100 || Integer.parseInt(synt_y[i]) < 0){
                    eisodoi = false;
                    break;
                }
            }

            if(synt_x.length != shmeia || eisodoi == false){
                System.out.println("Asynepeia eisodou!");
                System.exit(0);
            }

            TwoDTree tree = new TwoDTree();
            for(int i = 0; i < shmeia; i++){
                Point point = new Point(Integer.parseInt(synt_x[i]), Integer.parseInt(synt_y[i]));
                tree.insert(point);
            }

            while(true){
                System.out.println("Select action!");
                System.out.println("1. Compute the size of the tree");
                System.out.println("2. Insert a new point");
                System.out.println("3. Search if a given point exists in the tree");
                System.out.println("4. Provide a query rectangle");
                System.out.println("5. Provide a query point");
                System.out.println("6. Quit");
                System.out.print("Action: ");
                Scanner scan = new Scanner(System.in);
                int action = scan.nextInt();
                System.out.println();
                while (action < 1 || action > 5) {
                    System.out.println("Input must be 1-5.");
                    System.out.print("Action: ");
                    action = scan.nextInt();
                }
                switch (action){
                    case 1: {
                        System.out.println("Tree size is equal to " + tree.size() + "\n");
                        break;
                    }
                    case 2: {
                        System.out.print("Input x coordinate: ");
                        int x_co = scan.nextInt(); 
                        System.out.print("Input y coordinate: ");
                        int y_co = scan.nextInt();
                        tree.insert(new Point(x_co, y_co));
                        System.out.println("Point inserted \n");
                        break;
                    }
                    case 3: {
                        System.out.print("Input x coordinate: ");
                        int x_co = scan.nextInt(); 
                        System.out.print("Input y coordinate: ");
                        int y_co = scan.nextInt();
                        boolean yparxei = tree.search(new Point(x_co, y_co));
                        if(yparxei){System.out.println("It does exist! \n");}
                        else{System.out.println("It does not exist! \n");}
                        break;
                    }
                    case 4: {
                        System.out.print("Input rectangles xmax: ");
                        int xmax = scan.nextInt();
                        System.out.print("Input rectangles xmin: ");
                        int xmin = scan.nextInt();
                        System.out.print("Input rectangles ymax: ");
                        int ymax = scan.nextInt();
                        System.out.print("Input rectangles ymin: ");
                        int ymin = scan.nextInt();
                        List<Point>in_range=tree.rangeSearch(new Rectangle(xmin,xmax,ymin,ymax));
                        System.out.println("points in given Rectangle"+ in_range.toString());
                        System.out.println("Mh ylopoihmenh \n");
                        break;
                    }
                    case 5: {
                        System.out.print("Input query's x coordinate: ");
                        int x_co = scan.nextInt(); 
                        System.out.print("Input queary's y coordinate: ");
                        int y_co = scan.nextInt();
                        Point b = new Point(x_co,y_co);
                        Point a= tree.nearestNeighbor(b);
                        if(!(tree.isEmpty())){
                        System.out.println("point is:"+a.toString()+"distance from queried point is "+ b.distanceToSquare(a));}
                        else{System.out.println("empty tree \n");}
                        //System.out.println("Mh ylopoihmenh \n");
                        break;
                    }
                    case 6: {
                        System.out.println("bye \n");
                        break;

                    }
                   
                }
            }
        }catch (Exception e){throw e;}
    }


    private class TreeNode{
        Point item;
        TreeNode l;
        TreeNode r;
      

        
        

        TreeNode(Point x){
            this.item = x;
            l=null;
            r=null;
        }     
        /*public Treenode L(){
            return l;
        }
        public Treenode R(){
            return r;
        }*/
    }

    private TreeNode head;
    private int numberOfPoints;

    TwoDTree(){
        numberOfPoints = 0;
    }

    TwoDTree(TreeNode node){
        head = node;
        numberOfPoints = 0;
    }

    public boolean isEmpty(){
        if(head == null){
            return false;
        }
        return true;
    }

    public int size(){
        return numberOfPoints;
    }

    public void insert(Point p){
        head = insertPoint(true, p, head);
    }

    private TreeNode insertPoint(boolean x, Point p, TreeNode node){
        if(node == null){
            numberOfPoints++;
            return new TreeNode(p);
        }
        else if(node.item != null){
            if(node.item.x() == p.x() && node.item.y() == p.y()){
                System.out.println("yparxei");
                return node;
            }
        }
        if(x){
            if(p.x() < node.item.x()){
                node.l = insertPoint(false, p, node.l);
            }
            else{
                node.r = insertPoint(false, p, node.r);
            }
        }
        else{
            if(p.y() < node.item.y()){
                node.l = insertPoint(true, p, node.l);
            }
            else{
                node.r = insertPoint(true, p, node.r);
            }
        }
        return node;
    }

    public boolean search(Point p){
        return searchPoint(true, p, head);
    }


    private boolean searchPoint(boolean x, Point p, TreeNode node){
        if(this.isEmpty()){
            return false;
        }
        TwoDTree newTree;
        if(p.x() == node.item.x() && p.y() == node.item.y()){
            return true;
        }
        else{
            if(x){
                if(p.x() < node.item.x()){
                    newTree = new TwoDTree(node.l);
                }
                else{
                    newTree = new TwoDTree(node.r);
                }
                return newTree.searchPoint(false, p, newTree.head);
            }
            else{
                if(p.y() < node.item.y()){
                    newTree = new TwoDTree(node.l);
                }
                else{
                    newTree = new TwoDTree(node.r);
                }
                return newTree.searchPoint(true, p, newTree.head);
            }
        }
    }


    public List<Point> rangeSearch(Rectangle r){
        List<Point> points = new List<Point>();
        
        if(this.isEmpty()){return points;
        }else{
            Rectangle domain = new Rectangle(0, 100, 0, 100);
            //we parse our list as an args because the recursive nature of the function will create logn^2- one item lists 
            //due to the amount of recursive calls
            return rangeSearchR(r,domain,0,points,head);

        }
        
    }

        

    
    private List<Point> rangeSearchR(Rectangle r,Rectangle domain,int even,List<Point> p,TreeNode N){
        if(N==null){return p;}//base condition 
        if(r.contains(N.item)){
            p.insertAtFront(N.item);
        }
        if(!domain.intersects(r)){//if the domain doesnt intersect the orthogonal query the will be no further points to return 
          return p;//all possible points within the rectagle queried have been obtained  
        }
        else{
            //even=getHeight(head);
            if((even%2)==0){
                //compare based on x if the height of node is even this block will be executed every other recursive call
                Rectangle newdoml= new Rectangle(domain.xmin(),N.item.x(),domain.ymin(),domain.ymax());//domain left of head node Xε[domainmin,head.x]
                Rectangle newdomr=new Rectangle(N.item.x(),domain.xmax(),domain.ymin(),domain.ymax());//right head node Xε[head.x,domainmax]
                //even=getHeight(h.l);
                rangeSearchR(r,newdoml,even+1,p,N.l);
                rangeSearchR(r,newdomr,even+1,p,N.r);
            }
            else{//compare based on y
                Rectangle newdoml=new Rectangle(domain.xmin(),domain.xmax(),N.item.y(),domain.ymax());
                Rectangle newdomr= new Rectangle(domain.xmin(),domain.xmax(),domain.ymin(),N.item.y());
                //implement Node height to parse as argument even in this recursive range search method
                rangeSearchR(r,newdoml,even+1,p,N.l);
                rangeSearchR(r,newdomr,even+1,p,N.r);
                }
        }
        return p;
    }
    public Point nearestNeighbor(Point p){
        
        if(head==null){return new Point(-1,-1);}
        Point min_distp=head.item;
        //double min_dist= min_distp.distanceToSquare(p);
        Rectangle domain= new Rectangle(0,100,0,100);

        return nearestNeighboR(p,min_distp,true,domain,this.head);

    }
    private Point nearestNeighboR(Point p ,Point min,boolean x,Rectangle domain,TreeNode N){
        if(N==null){return min;}//base cond
        if(N.item.distanceToSquare(p)<p.distanceToSquare(min)){
            min=N.item;
        }
        
        if(p.distanceToSquare(min)<domain.distanceTo(p)){return min;
        }
        else{
            if(x){
                Rectangle newdoml= new Rectangle(domain.xmin(),N.item.x(),domain.ymin(),domain.ymax());//domain left of head node Xε[domainmin,head.x]
                Rectangle newdomr=new Rectangle(N.item.x(),domain.xmax(),domain.ymin(),domain.ymax());//right head node Xε[head.x,domainmax]
                min=nearestNeighboR(p,min,false,newdoml,N.l);
                min=nearestNeighboR(p,min,false,newdomr,N.r);
            }else{
                Rectangle newdoml= new Rectangle(domain.xmin(),N.item.x(),domain.ymin(),domain.ymax());//domain left of head node Xε[domainmin,head.x]
                Rectangle newdomr=new Rectangle(N.item.x(),domain.xmax(),domain.ymin(),domain.ymax());//right head node Xε[head.x,domainmax]
                min=nearestNeighboR(p,min,true,newdoml,N.l);
                min=nearestNeighboR(p,min,true,newdomr,N.r);
            }
        }
        return min;  
    }
}

