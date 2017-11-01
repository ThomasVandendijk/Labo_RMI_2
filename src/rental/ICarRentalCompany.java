package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICarRentalCompany extends Remote{
	
	public String getName() throws RemoteException;
	public List<String> getRegions() throws RemoteException;
	public boolean hasRegion(String region) throws RemoteException;
	public Collection<CarType> getAllCarTypes() throws RemoteException;
	public CarType getCarType(String carTypeName) throws RemoteException;
	public boolean isAvailable(String carTypeName, Date start, Date end) throws RemoteException;
	public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
	public Quote createQuote(ReservationConstraints constraints, String client) throws ReservationException, RemoteException;
	public Reservation confirmQuote(Quote quote) throws ReservationException, RemoteException;
	public void cancelReservation(Reservation res) throws RemoteException;
	public List<Reservation> getRenterReservations(String renter) throws RemoteException;
	public int getNumberOfReservationsForCarType(String carTypeName) throws RemoteException;
	public Map<String, Integer> getReservationForEachCarType() throws RemoteException;
	public Map<String, Integer> getCustomersPerCompany(Map<String, Integer> nbReservationsPerCustomer) throws RemoteException;
	public int getReservationCartype(String carType) throws RemoteException;
	public Map<CarType, Integer> getReservationForEachCarType(int year) throws RemoteException;

}
