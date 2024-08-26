import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int noOfTaxies = 4;
        Taxi[] taxies = new Taxi[noOfTaxies];
        createTaxi(taxies);

        boolean isLooping = true;

        while(isLooping)
        {
            System.out.println("\n\n******************************* TAXI BOOKING SYSTEM *******************************");
            System.out.println("1. Booking \n2. Taxi details \n3. Exit\n");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("********** Booking Taxi **********");
                    System.out.println("Points  :  A B C D E F");

                    System.out.print("Enter Start point : ");
                    char start = sc.next().charAt(0);

                    System.out.print("Enter Drop point : ");
                    char drop = sc.next().charAt(0);

                    if(start < 'A' || start > 'F' || drop < 'A' || drop > 'F')
                    {
                        System.out.println("Start and drop point should be in [A / B / C / D / E / F]");
                        return;
                    }

                    System.out.print("Enter Pick up time (0 - 23) : ");
                    int time = sc.nextInt();
                    if(time < 0 || time > 23)
                    {
                        System.out.println("Time should between 0 & 23");
                    }

                    bookTaxi(start, drop, time, taxies);
                    break;

                case 2:
                    printTaxiStatus(taxies);
                    System.out.println();
                    printTaxiDetails(taxies);
                    break;

                case 3:
                    isLooping = false;
                    System.out.println("Exit............");
                    break;
            
                default:
                    isLooping = false;
                    break;
            }
        }
        sc.close();
    }

    private static void bookTaxi(char start, char drop, int time, Taxi[] taxies) {
        Taxi[] freeTaxies = findFreeTaxies(taxies, start, time);
        if(freeTaxies.length == 0)
        {
            System.out.println("No taxies are allocated");
            return;
        }

        int selectedTaxi = selectTaxi(freeTaxies) - 1;
        int travelDistance = Math.abs(start - drop) * 15;

        int tripAmount = 0;
        if(travelDistance > 0){
            tripAmount = 100 + (travelDistance - 5) * 10;
        }
        int amount = taxies[selectedTaxi].totalEarnings + tripAmount;
        int dropTime = (time + Math.abs(start - drop)) % 24;

        Taxi taxi = taxies[selectedTaxi];

        String tripDetail = "  " + taxi.taxiId + "          " + taxi.taxiId + "             " + taxi.taxiId + "          " + start + "       " + drop + "        " + time + "          " + dropTime + "         " + tripAmount;
        // String tripDetail = String.format("%d%4d%4d%4d%4s%4s%4d%4d", taxi.taxiId, taxi.taxiId, taxi.taxiId, start, drop, time, dropTime, tripAmount);
        taxies[selectedTaxi].setDetails(true, drop, amount, dropTime, tripDetail);
        System.out.println("----------------------------------------------------- Taxi " + (selectedTaxi+1) + " Booked");
    }
    
    private static int selectTaxi(Taxi[] freeTaxies) {
        Taxi selectedOne = freeTaxies[0];

        for(Taxi t : freeTaxies)
        {
            if(t.totalEarnings < selectedOne.totalEarnings)
            {
                selectedOne = t;
            }
        }

        return selectedOne.taxiId;
    }

    private static Taxi[] findFreeTaxies(Taxi[] taxies, char start, int time) {
        int n = taxies.length;
        boolean[] indexes = new boolean[n+1];
        int count = 0;
        int nearestDistance = Integer.MAX_VALUE;
        for(Taxi t : taxies)
        {
            int timeDistance = time - t.freeTime;
            int pointDistance = Math.abs(t.currentPoint - start);
            if(Math.abs(t.currentPoint - start) < nearestDistance && timeDistance >= 0 && timeDistance >= pointDistance)
            {
                nearestDistance = Math.abs(t.currentPoint - start);
            }
        }

        for(Taxi t : taxies)
        {
            int timeDistance = time - t.freeTime;
            if(timeDistance < 0)
            {
                continue;
            }
            int pointDistance = Math.abs(t.currentPoint - start);

            if(timeDistance >= pointDistance && nearestDistance == pointDistance)
            {
                indexes[t.taxiId] = true;
                count++;
            }
        }

        Taxi[] freetaxies = new Taxi[count];

        for(int i = 1; i <= n; i++)
        {
            if(indexes[i])
            {
                freetaxies[--count] = taxies[i-1];
            }
        }

        return freetaxies;
    }

    private static void printTaxiDetails(Taxi[] taxies) {
        for(Taxi t : taxies)
        {
            t.taxiDetail();;
        }
    }

    private static void printTaxiStatus(Taxi[] taxies) {
        for(Taxi t : taxies)
        {
            t.taxiStatus();
        }
    }

    private static void createTaxi(Taxi[] taxies) {
        int n = taxies.length;

        for(int i = 0; i < n; i++)
        {
            taxies[i] = new Taxi();
        }
    }    
}
