package sessions;

import java.io.IOException;
import java.rmi.Remote;
import java.util.Set;
import java.rmi.RemoteException;

import rental.CarType;

public interface IManagerSession extends Remote{
	public Set<String> getBestClient() throws RemoteException;
	public int getNumberOfReservationsForCarType(String carRentalName, String carType) throws RemoteException;
	public void registerCompany(String lookup) throws NumberFormatException, IOException,RemoteException;
	public boolean unregisterCompany(String name) throws RemoteException;
	public Set<String> getAllRentalCompanies() throws RemoteException;
	public CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year) throws RemoteException;
}
