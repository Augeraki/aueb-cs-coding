import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Try{
    public static void main(String args[]) throws Exception{
        try{
            String file= args[0];
            
            BufferedReader buffer = new BufferedReader(new Filereader(file));
            buffer.mark(1000000000);
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
            int counter = 0;
            int index = 0;
            while((split = buffer.readLine()) != null){
                String pin[] = split.split(" ");
                if(index < shmeia){
                    synt_x[index] = pin[0];
                    synt_y[index] = pin[1];
                }
                counter++;
                index++;
            }

            //buffer.close();
            boolean eisodoi = true;
            
            for(int i = 0; i < shmeia; i++){
                if(Integer.parseInt(synt_x[i]) > 100 || Integer.parseInt(synt_x[i]) < 0 || Integer.parseInt(synt_y[i]) > 100 || Integer.parseInt(synt_y[i]) < 0){
                    eisodoi = false;
                    break;
                }
            }

            if(counter != shmeia || eisodoi == false){
                System.out.println("Asynepeia eisodou!");
                System.exit(0);
            }

            TwoDTree tree = new TwoDTree();
            for(int i = 0; i < shmeia; i++){
                Point point = new Point(Integer.parseInt(synt_x[i]), Integer.parseInt(synt_y[i]));
                tree.insert(point);
            }

            Scanner scan = new Scanner(System.in);
            int action;

            while(true){
                System.out.println("Select action!");
                System.out.println("1. Compute the size of the tree");
                System.out.println("2. Insert a new point");
                System.out.println("3. Search if a given point exists in the tree");
                System.out.println("4. Provide a query rectangle");
                System.out.println("5. Provide a query point");
                System.out.print("Action: ");
                action = scan.nextInt();
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
                        //tree.rangeSearch();
                        System.out.println("Mh ylopoihmenh \n");
                        break;
                    }
                    case 5: {
                        System.out.print("Input query's x coordinate: ");
                        int x_co = scan.nextInt(); 
                        System.out.print("Input queary's y coordinate: ");
                        int y_co = scan.nextInt();
                        //tree.nearestNeighbor();
                        System.out.println("Mh ylopoihmenh \n");
                        break;
                    }
                }
            }

        }catch (Exception e){throw e;}
    }

    /*
     

    if(isEmpty()){
            return false;
        }
        if(current.item == p){
            return true;
        }
        else if(p.x() < current.item.x()){
            current = current.l;
            if(current.item == p){
                return true;
            }
            else if(p.y() < current.item.y()){
                current = current.l;
                search(p);
            }
            else{
                current = current.r;
                search(p);
            }
        }
        else{
            current = current.r;
            if(current.item == p){
                return true;
            }
            else if(p.y() < current.item.y()){
                current = current.l;
                search(p);
            }
            else{
                current = current.r;
                search(p);
            }
        }
        return false;


        if(current != null){
            if(current.item == p){
                return true;
            }
            else if(p.x() < current.item.x()){
                current = current.l;
                if(current != null){
                    if(current.item == p){
                        return true;
                    }
                    else if(p.y() < current.item.y()){
                        current = current.l;
                        search(p);
                    }
                    else{
                        current = current.r;
                        search(p);
                    }
                }
            }
            else{
                current = current.r;
                if(current != null){
                    if(current.item == p){
                        return true;
                    }
                    else if(p.y() < current.item.y()){
                        current = current.l;
                        search(p);
                    }
                    else{
                        current = current.r;
                        search(p);
                    }
                }
            }
        }
        return false;



        

     */
}