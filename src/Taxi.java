import java.util.ArrayList;
import java.util.List;

public class Taxi {
    private static int taxiCount = 1;
    int taxiId;
    boolean booked;
    char currentPoint;
    int totalEarnings;
    int freeTime;
    List<String> trips;

    public Taxi()
    {
        taxiId = taxiCount++;
        booked = false;
        currentPoint = 'A';
        totalEarnings = 0;
        freeTime = 6;
        trips = new ArrayList<>();
    }

    public void setDetails(boolean booked, char currentPoint, int totalEarnings, int freeTime, String tripDetails)
    {
        this.booked = booked;
        this.currentPoint = currentPoint;
        this.totalEarnings = totalEarnings;
        this.freeTime = freeTime;
        trips.add(tripDetails);
    }

    public void taxiStatus()
    {
        System.out.println("Taxi ID : " + this.taxiId + 
                            " Total Earnings : " + this.totalEarnings + 
                            " Current Point : " + this.currentPoint + 
                            " Free Time : " + this.freeTime);
    }

    public void taxiDetail()
    {
        System.out.println("Taxi : " + this.taxiId + "  Total Earnings : " + this.totalEarnings);
        int n = trips.size();
        System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
        System.out.println("-------------------------------------------------------------------------------------");
        for(int i = 0; i < n; i++)
        {
            System.out.println(trips.get(i));
        }
        System.out.println();
    }
}