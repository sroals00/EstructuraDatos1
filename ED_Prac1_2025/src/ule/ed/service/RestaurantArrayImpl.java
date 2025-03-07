package ule.ed.service;

import java.util.List;
import java.util.ArrayList;

public class RestaurantArrayImpl implements IRestaurant {

	// ATRIBUTOS
	
	private String name;
	private int nTables;
	private int maxCapacity; // máximo número de clientes admitidos
	private int nClients; // contador de clientes actuales en el restaurante

	private int discount;    // Descuento a aplicar (ejemplo: 10%)

	private Service[] tables; // array de servicios (cada servicio se corresponde con una mesa)
	                          


	// CONSTRUCTOR

	public RestaurantArrayImpl(String name, int nTables,int aforoMax, int discount){ 
		// Debe crear el array de mesas con todas las posiciones a null
		tables = new Service[nTables];
		this.name = name;
		this.nTables = nTables;
		this.maxCapacity = aforoMax;
		this.discount = discount;
		this.nClients = 0;
	}



	@Override
	public String getName() {
		return this.name;
		
	}



	@Override
	public int getMaxCapacity() {
		return this.maxCapacity;
	}



	@Override
	public int getNumberOfChildren() {
		int numberOfChildren = 0;
		for(Service service : tables) {
			if(service != null) {
				numberOfChildren += service.getNChildren();
			}
		}
		return numberOfChildren;
	}



	@Override
	public int getNumberOfPeople() {
		int numberOfPeople = 0;
		for(Service service : tables) {
			if(service != null) {
				numberOfPeople += service.getNPeople();
			}
		}
		return numberOfPeople;	
	}



	@Override
	public int getActualCapacity() {
		return this.maxCapacity - this.nClients;
	}



	@Override
	public int getNumberTablesOccupied() {
		int numberOfTablesOccupied = 0;
		for(Service service : tables) {
			if(service != null) {
				numberOfTablesOccupied++;
			}
		}
		return numberOfTablesOccupied;
	}



	@Override
	public int getNumberOfEmptyTables() {
		return this.nTables - getNumberTablesOccupied();
	}



	@Override
	public int getNumberOfTablesWithChildren() {
		int numberOfTablesWithChildren = 0;
		for(Service service : tables) {
			if(service != null && service.getNChildren() > 0) {
				numberOfTablesWithChildren++;
			}
		}
		return numberOfTablesWithChildren;
	}



	@Override
	public List<Integer> getNumbersOfEmptyTables() {
		List<Integer> emptyTables = new ArrayList<>();
		for(int i = 0; i < tables.length; i++) {
			if(tables[i] == null){
				emptyTables.add(i);
			}
		}
		return emptyTables;
	}



	@Override
	public Service getService(int ntable) {
		if (ntable >= 1 && ntable <= nTables) {
            return tables[ntable - 1]; // Las posiciones empiezan en '1'
        }
        return null;
	}



	@Override
	public void addDishToTable(int nTable, String name, double price, int count) {
		if (nTable >= 1 && nTable <= nTables) {
			if (tables[nTable - 1] == null) {
				tables[nTable - 1] = new Service(0, 0);
			}
			tables[nTable - 1].addDish(name, price, count);
		}
	}



	@Override
	public double getFinalPrice(int ntable) {
		if (ntable >= 1 && ntable <= nTables && tables[ntable - 1] != null) {
            return tables[ntable - 1].getTotalService() * (1 - discount / 100.0);
        }
        return 0.0;
	}



	@Override
	public double getFinalPriceRestaurant() {
		double total = 0.0;
		for(Service service : tables){
			if(service != null){
				total += service.getTotalService();;
			}
		}
		return total - (total * discount / 100);
    }



	@Override
	public boolean emptyTable(int nTable) {
		if(nTable >= 1 && nTable <= nTables && tables[nTables -1] != null){
			nClients -= tables[nTable - 1].getNPeople();
            tables[nTable - 1] = null;
            return true;
		}
		return false;
	}



	@Override
	public int occupyTable(int nPeople, int nChildren) {
		if (nClients + nPeople > maxCapacity) {
            return -1;
        }
        for (int i = 0; i < tables.length; i++) {
            if (tables[i] == null) {
                tables[i] = new Service(nPeople, nChildren);
                nClients += nPeople;
                return i + 1; // Las posiciones empiezan en '1'
            }
        }
        return -2;
	}
	
	@Override
	public boolean occupyTable(int nTable, int nPeople, int nChildren) {
		if (nTable >= 1 && nTable <= nTables && tables[nTable - 1] == null && nClients + nPeople <= maxCapacity) {
            tables[nTable - 1] = new Service(nPeople, nChildren);
            nClients += nPeople;
            return true;
        }
        return false;	
	}
	
}