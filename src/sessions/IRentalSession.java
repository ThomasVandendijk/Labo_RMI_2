package sessions;

import java.rmi.Remote;

import java.util.Date;
import java.util.List;
import java.util.Set;

import rental.CarType;
import rental.Quote;
import java.rmi.RemoteException;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

public interface IRentalSession extends Remote{
    Quote createQuote(ReservationConstraints constraints,String guest) throws ReservationException,RemoteException;
    List<Quote> getCurrentQuotes() throws RemoteException;
    List<Reservation> confirmQuotes() throws ReservationException, RemoteException;
    Set<CarType> getAllAvailableCarTypes(Date start, Date end) throws RemoteException;
    String getCheapestCarType(Date start, Date end, String region) throws RemoteException;
}
