package com.fuelconsumption.controllers;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fuelconsumption.business.services.FuelConsumptionService;
import com.fuelconsumption.model.entities.FuelConsumptionEntity;
import com.fuelconsumption.model.repo.FuelConsumptionGroupInterface;

/**
 * The endpoints API REST services for the Fuel consumption module
 * 
 * @author mohamed.elleithy
 *
 */

@RestController
@RequestMapping(value = "/FuelConsumptions")
public class FuelConsumptionsController {

	@Autowired
	private FuelConsumptionService FCS;

	@RequestMapping(value = "/allFuels", method = RequestMethod.GET)
	public List<FuelConsumptionEntity> getAllFuels() {
		return this.FCS.getAllFuels();
	}

	/**
	 * This method is to add new object to the database after doing some validation
	 * on the coming data
	 * 
	 * @param FuelConsumptionEntity which is an object and Spring Boot REST can map
	 *                              those attributes for the specified object
	 * 
	 * @return return a string which a message to tell the frontend, that the adding
	 *         new object succeed or failed
	 * 
	 */

	@RequestMapping(value = "/addFuel", method = RequestMethod.POST)
	public String addFuel(@RequestBody FuelConsumptionEntity FCE) {
		if (FCE.getDate() == null)
			FCE.setDate(new Date()); // if coming date is null, assume it's today
		String isValied = validatFuelData(FCE);
		if (isValied.equals("passed")) {
			this.FCS.saveFuel(FCE);
			return "Fuel Consumption added successfully !!!";
		}
		return isValied;
	}

	/**
	 * Adding a bulk of Fuels by JSON objects
	 * 
	 * @param FCEList : list of JSON objects which Spring REST will maps to
	 *                FuelConsumptionEntity objects
	 * 
	 */

	@RequestMapping(value = "/addFuelBulk", method = RequestMethod.POST)
	public void addFuel(@RequestBody List<FuelConsumptionEntity> FCEList) {
		this.FCS.saveFuelBulk(FCEList);
	}

	@RequestMapping(value = "/updateFuel", method = RequestMethod.PUT)
	public String updateFuel(@RequestBody FuelConsumptionEntity FCE) {
		if (this.FCS.existById(FCE.getID()) == true) {
			this.FCS.saveFuel(FCE);
			return "fuel updated successfully !!!";
		} else
			return "wanted fuel is not exist !!!";
	}

	/**
	 * Delete the wanted object from the database
	 * 
	 * @param id : the id of the wanted object to delete it
	 * @return string is to tell the frontend user that the deletion operation has
	 *         succeed or failed
	 */
	@RequestMapping(value = "/deleteFuel/{id}", method = RequestMethod.DELETE)
	public String deleteFuel(@PathVariable(value = "id") Long id) {
		if (this.FCS.existById(id) == true) {
			this.FCS.deleteFuel(id);
			return "fuel deleted successfully !!!";
		} else
			return "wanted fuel is not exist !!!";
	}

	/**
	 * Calculating the total money spent per month for one driver or for all drivers
	 * if no driver ID received
	 * 
	 * @param monthId
	 * @param driverId
	 * @return
	 */
	@RequestMapping(value = "/getTotalMoneySpentPerMonth{monthId , driverId}", method = RequestMethod.GET)
	public double getTotalMoneyPerMonth(@RequestParam(value = "monthId") int monthId,
			@RequestParam(value = "driverId", required = false) String driverId) {
		if (monthId < 0 || monthId > 12) {
			throw new RuntimeException("Please enter a valied month between 0 and 12");
		} else {
			return this.FCS.getTotalMoneyPerMonth(monthId, driverId);
		}
	}
	
	
	/**
	 * Calculating the total money spent per month for one driver or for all drivers
	 * if no driver ID received
	 * 
	 * @param monthId
	 * @param driverId
	 * @return
	 */
	@RequestMapping(value = "/getTotalMoneySpentGroupedByMonth{driverId}", method = RequestMethod.GET)
	public Map<YearMonth, Double>  getTotalMoneySpentGroupedByMonth(
			@RequestParam(value = "driverId", required = false) String driverId) {

			return this.FCS.getTotalMoneySpentGroupedByMonth(driverId);
	}

	/**
	 * Retrieving all the fuels records for a specific month and for one driver or
	 * for all drivers if no driver ID received
	 * 
	 * @param monthId
	 * @param driverId
	 * @return
	 */
	@RequestMapping(value = "/getAllFuelsPerMonth{monthId , driverId}", method = RequestMethod.GET)
	public List<FuelConsumptionEntity> getAllFuelsPerMonth(@RequestParam(value = "monthId") int monthId,
			@RequestParam(value = "driverId", required = false) String driverId) {
		if (monthId < 0 || monthId > 12) {
			throw new RuntimeException("Please enter a valied month between 0 and 12");
		} else {
			return this.FCS.getAllFuelsPerMonth(monthId, driverId);
		}
	}

	/**
	 * Grouping all the Fuels consumption records in the database by fuel type and
	 * calculate the "total volume", "average price" and "total price"
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllFuelsGroupedByType", method = RequestMethod.GET)
	public List<FuelConsumptionGroupInterface> getAllFuelsGroupedByType() {
		return this.FCS.getAllFuelsGroupedByType();
	}

	String validatFuelData(FuelConsumptionEntity FCE) {
		if (FCE.getDriverId() == null || FCE.getDriverId().trim().equals(""))
			return ("Driver ID is not valied !!!");
		if (FCE.getFuelType() == null || FCE.getFuelType().trim().equals(""))
			return ("Fuel Type is not valied !!!");
		if (FCE.getPrice() <= 0)
			return ("Price must be bigger than 0 !!!");
		if (FCE.getVolume() <= 0)
			return ("Volume must be bigger than 0 !!!");
		return "passed";
	}

}
