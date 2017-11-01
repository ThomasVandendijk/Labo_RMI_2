package sessions;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rental.Car;
import rental.CarRentalCompany;
import rental.CarType;
import rental.ICarRentalCompany;
import rental.Reservation;
import rentalagency.RentalStore;

public class ManagerSession implements IManagerSession{
    
	
    public Set<String> getBestClient() throws RemoteException{
    	Map<String, ICarRentalCompany> companies = RentalStore.getRentals();
    	Map<String, Integer> nbReservationsPerCustomer = new HashMap<String, Integer>();
    	for(String key: companies.keySet()){
    		ICarRentalCompany company = companies.get(key);
    		company.getCustomersPerCompany(nbReservationsPerCustomer);
    	}
    	return getHighestValue(nbReservationsPerCustomer);
    }
    
    private Set<String> getHighestValue(Map<String,Integer> hashMap){
        Set<String> keySet = hashMap.keySet();
        List<String> solutionKeys = new ArrayList<String>();
        for(String key: keySet){
            if(solutionKeys.isEmpty() || hashMap.get(key)>hashMap.get(solutionKeys.get(0))){
                solutionKeys.removeAll(solutionKeys);
                System.out.println("the current renter: " + key);
                solutionKeys.add(key);
            }
            else if(hashMap.get(key)==hashMap.get(solutionKeys.get(0))){
            	System.out.println("the current renter: " + key);
            	solutionKeys.add(key);
            }
        }
        Set<String> solutionSet = new HashSet<String>(solutionKeys);
        return solutionSet;
    }
    
    private CarType getHighestValuedCarType(Map<CarType,Integer> hashMap){
        Set<CarType> keySet = hashMap.keySet();
        CarType highestCarType = null;
        for(CarType key: keySet){
            if(highestCarType == null || hashMap.get(key)>hashMap.get(highestCarType)){
                highestCarType = key;
            }
        }
        return highestCarType;
    }
    
    public int getNumberOfReservationsForCarType(String carRentalName, String carType) throws RemoteException{
    	ICarRentalCompany company = RentalStore.getRental(carRentalName);
    	return company.getReservationCartype(carType);
    	
    } 

	@Override
	public void registerCompany(String lookup) throws NumberFormatException, IOException {
		RentalStore.registerCompany(lookup);
	}
	

	@Override
	public boolean unregisterCompany(String name) {
		return RentalStore.unregisterCompany(name);
	}

	@Override
	public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
	}

	@Override
	public CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year)
			throws RemoteException {
		ICarRentalCompany company = RentalStore.getRental(carRentalCompanyName);
		Map<CarType, Integer> nbOfReservationsPerCarType = company.getReservationForEachCarType(year);
		for(CarType cartype:nbOfReservationsPerCarType.keySet()){
			System.out.println(cartype.getName() + " has " + nbOfReservationsPerCarType.get(cartype) + " reservations");
		}
		return getHighestValuedCarType(nbOfReservationsPerCarType);
	}
}
