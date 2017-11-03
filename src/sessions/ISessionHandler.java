package sessions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISessionHandler extends Remote{
	void createReservationSession(String renter) throws RemoteException;
	void createManagerSession() throws RemoteException;
	void terminateReservationSession(String renter) throws RemoteException;
	
}
