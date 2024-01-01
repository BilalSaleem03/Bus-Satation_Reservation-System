import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
public class Bus_Reservation
{
    static Scanner input = new Scanner(System.in);
    static String [] s = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
    static String [] destination = new String[20];
    static int [] time = new int[20];
    static String [] time_string = new String[20];
    static String [] []reserved = new String [20] [20];  
    static String [] name = new String[20];
    static long [] cnic = new long[20];
    static long [] phone = new long[20];
    static int [] seats = new int [20];
    static int res = 0;
    static int next_reservation = 0;
    static String selected_seat;
    //static int record_check=0;


    static void display_seats()
    {
        
        System.out.println("|"+s[16]+"  "+s[17]+"          "+s[18]+"  "+s[19]+"|\n"+
                           "|"+s[12]+"  "+s[13]+"          "+s[14]+"  "+s[15]+"|\n"+
                           "|"+s[8]+"   "+s[9]+"          "+s[10]+"  "+s[11]+"|\n"+
                           "|"+s[4]+"    "+s[5]+"          "+s[6]+"    "+s[7]+"|\n"+
                           "|"+s[0]+"    "+s[1]+"          "+s[2]+"    "+s[3]+"|\n");
    }
    

    static void reservation(int res,int seats[]){ 
        input.nextLine();
        for (int i = 0 ; i<seats[next_reservation] ; i++)
        {
            boolean check =true;
            while(true){
                System.out.println("\n\nAvailable seats are :");
                display_seats();
                System.out.println("Select your seat:");
                selected_seat = input.nextLine(); 
                
                for(int j =0 ; j<20 ;j++){
                    if (s[j].equals(selected_seat)){
                        s[j]="B";
                        check = false;

                    }
                }
                if (check){
                    System.out.println("Seat not available!!\nTry again!!");
                    continue;
                }
                else{
                    reserved[next_reservation] [res]=selected_seat;
                    res++;
                    break;
                }
            }
            try
            {
                FileWriter filewrite2 = new FileWriter("bus_reservation.txt");
                filewrite2.write(selected_seat);
                filewrite2.write("\n");
                
                filewrite2.close();
                    
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    } 
   
    static void check(int seats[] , String time_string[] ,String destination[] ,long cnic[]  , long phone [], String name[] , String reserved[][]  ){
        long check_cnic;
        System.out.println("Enter your CNIC # :");
        while(true)
        {
            try
            { 
                check_cnic = input.nextLong();
                break;
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("Enter Valid CNIC# ");
                input.nextLine();

            }
        }
        
        int record_check=0;
        for(int d=0 ;d<20;d++)
        {
            if (check_cnic == cnic[d])
            {
                System.out.println("\n" );
                System.out.println("Name : "+name[d] );
                System.out.println("CNIC # : "+cnic[d] );
                System.out.println("Contact Number : "+phone[d] );
                System.out.println("Seat(s) you Reserved are :");
                for (int b = 0 ; b<seats[d] ;b++){
                    System.out.println(reserved[d] [b] );
                }
                System.out.println("Faizabad to  "+destination[d] +" via Moterway");
                System.out.println("Bus Departure Time is  "+time_string[d] );
                record_check = 1;
                System.out.println("\n");
            }
        }
        if (record_check !=1){
            System.out.println("No Record Found!!!\n");
        }
    }
    public static void main(String[] args)
    {
        //creating file
        File myFile = new File("bus_reservation.txt");
        try{
            myFile.createNewFile();
        }
        catch(Exception e){
            System.out.println(e);
        }
        

        System.out.println("\n\n.....*****Welcome to Subhan Bus Station Faizabad*****.....\n\n");
        while (true){
            //reservation
            System.out.println("Enter 1 for Reservation :\nEnter 2 to check Your Reservation:\nEnter 3 to check recent reservation:\nEnter any other key to exit :");
            int choice =input.nextInt();
            if (choice==1){
                //select destination
                input.nextLine();
                while(true){
                    System.out.println("\n\nPlease select your Destination :\nAvailable Destinations are Faisalabad,Multan,Lahore,Peshawar and Sahiwal");
                    
                    destination[next_reservation] = input.nextLine().toLowerCase();  
                    //filewrite.write(destination[next_reservation] );
                                      
                    if (!(destination[next_reservation].equals( "faisalabad")) && !(destination[next_reservation].equals( "multan")) && !(destination[next_reservation].equals( "peshawar")) && !(destination[next_reservation].equals( "lahore")) && !(destination[next_reservation].equals( "sahiwal"))){
                        System.out.println("Destination not available!!\nPlease try again!");
                        continue;
                    }
                    else{
                        break;
                    }
                }
            

                //select time slot
                while(true)
                {
                    System.out.println("\n\nPlease select your Time Slot : \nEnter 1 for 2:30am \nEnter 2 for 7:30am \nEnter 3 for 11:00am \nEnter 4 for 4:00pm \nEnter 5 for 7:00pm \nEnter 6 for 10:00pm ");
                    time[next_reservation] = input.nextInt();
                    if ((time[next_reservation] !=1)&&(time[next_reservation] !=2)&&(time[next_reservation] !=3)&&(time[next_reservation] !=4)&&(time[next_reservation] !=5)&&(time[next_reservation] !=6))
                    {
                        System.out.println("Time slot not available !!Please try again!");
                        continue;
                    }
                    else
                    {
                        switch(time[next_reservation])
                        {
                            case 1:
                            time_string[next_reservation] = "2:30am";
                            break;
                            case 2:
                            time_string[next_reservation] = "7:30am";
                            break;
                            case 3:
                            time_string[next_reservation] = "11:00am";
                            break;
                            case 4:
                            time_string[next_reservation] = "4:00pm";
                            break;
                            case 5:
                            time_string[next_reservation] = "7:00pm";
                            break;
                            case 6:
                            time_string[next_reservation] = "10:00pm";
                            break;
                        }
                        break;
                    }
                        
                        
                    
                }
                //No_of_seats();

                while(true)
                {
                    try
                    {
                        System.out.println("\n\nEnter your name : ");
                        name[next_reservation] = input.nextLine();
                        name[next_reservation] = input.nextLine();
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        input.nextLine();

                    }
                }
        
                System.out.println("Enter your CNIC # : ");
                while(true)
                {                    
                    try
                    {                        
                        cnic[next_reservation] = input.nextLong();
                        
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        System.out.println("Enter valid CNIC# ");
                        input.nextLine();
                        continue;
                    }
                }
                
                System.out.println("Enter your PHONE# : ");
                while(true)
                {
                    try
                    {                       
                        phone[next_reservation] = input.nextLong();
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        System.out.println("Enter valid PHONE# ");
                        input.nextLine();
                    }
                }

                
                while(true)
                {
                    System.out.println("Enter number of seats you want to reserve:");
                    seats[next_reservation] = input.nextInt();
                    if(seats[next_reservation]>0 && seats[next_reservation]<=20)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid Input!!!!");
                    }
                }
                res=0;
                reservation(res,seats);

                try
                {
                    FileWriter filewrite = new FileWriter("bus_reservation.txt");
                    filewrite.write(name[next_reservation] );
                    filewrite.write("\n");
                    filewrite.write(time_string[next_reservation] );
                    filewrite.write("\n");
                    filewrite.write(destination[next_reservation] );
                    filewrite.write("\n");
                    filewrite.close();
                    
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                
                
                System.out.println("\nReservation successful....\n\n\n");
                next_reservation++;
            }
            

            else if (choice==2){
                check( seats ,time_string ,destination, cnic , phone ,name , reserved );
            }

            else if(choice==3)
            {
                File myfile = new File("bus_reservation.txt");
                try{
                    Scanner fileinput = new Scanner(myfile);
                    while(fileinput.hasNextLine())
                    {
                        String line = fileinput.nextLine();
                        System.out.println(line);
                    }
                    System.out.println("\n");
                    fileinput.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }

            else{
                System.exit(0);
            }
        }
    }
}