package ule.ed.service;

import java.util.ArrayList;

public class Service {
	 private ArrayList<Dish> order;
	 private int nChildren;
	 private int nPeople;
	 
	 public Service(int nPeople, int nChildren) {
		 this.nPeople = nPeople;
		 this.nChildren = nChildren;
		 this.order = new ArrayList<>();
		 }
	 
	 
	 /**
	  * Metodo que devuelve el numero de ninyos en una mesa
	  * @return nChildren
	  */
	 public int getNChildren(){
		 return this.nChildren;
	 }
	 
	 /**
	  * Metodo que devuelve el numero de personas de una mesa
	  * @return nPeople
	  */
	 
	 public int getNPeople() {
		 return this.nPeople;
	 }
	 
	 public ArrayList<Dish> getOrder(){
		 return this.order;
	 }
	 
	 public void addDish(String name, double price, int count) {
		Dish dish = new Dish(name, price);
		dish.setCount(count);
		order.add(dish);
	}
	 //Calcula el total de este servicio, tiene que recorrer el array de platos calculando el total de cada plato, teniendo en cuenta que hay una cantidad de esos platos
	 // total de un plato = count * price (número de platos * cantidad de platos)
	 
	 public double getTotalService() {
		double total = 0.0;
		for (Dish dish : order) {
			total += dish.getPrice() * dish.getCount();
		}
		return total;
	 }
	 
	 @Override
		public String toString() {
			return "{Servicio:" + nPeople +"personas," +nChildren + "niños, total= "+ getTotalService() + "}";
		}

	}

