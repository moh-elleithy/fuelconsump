package com.fuelconsumption.business.services;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuelconsumption.model.entities.FuelConsumptionEntity;
import com.fuelconsumption.model.repo.FuelConsumptionGroupInterface;
import com.fuelconsumption.model.repo.FuelConsumptionRepo;

/**
 * Business service class which contains all the logic code which must called to
 * ensure the data consistency at writing and right data retrieval
 * 
 * @author Mohamed Elleithy
 *
 */

@Service
public class FuelConsumptionService {

	private FuelConsumptionRepo fuelConsRepo;

	@Autowired
	public FuelConsumptionService(FuelConsumptionRepo fuelConsRepo) {
		this.fuelConsRepo = fuelConsRepo;
	}

	public void deleteFuel(Long id) {
		this.fuelConsRepo.deleteById(id);
	}

	public void saveFuel(FuelConsumptionEntity FCE) {
		this.fuelConsRepo.save(FCE);
	}

	public boolean existById(Long id) {
		return this.fuelConsRepo.existsById(id);
	}

	/**
	 * This method is for getting the total money spent on fuels happened in a
	 * specific month
	 * 
	 * @param monthId : the month id number, which required to retrieve total money
	 *                of fuels happened
	 * @return Total Amount of money per that month
	 */

	public double getTotalMoneyPerMonth(int monthId, String driverId) {
		double totalAmount = 0;
		for (FuelConsumptionEntity fce : getAllFuelsPerMonth(monthId, driverId)) {
			totalAmount += (fce.getPrice() * fce.getVolume());
		}
		return totalAmount;
	}

	/**
	 * This method is for getting all fuels happened in a specific month
	 * 
	 * @param monthId : the month id number, which required to retrieve fuels for
	 * @return Total Amount per that month
	 */

	public List<FuelConsumptionEntity> getAllFuelsPerMonth(int monthId, String driverId) {
		List<FuelConsumptionEntity> FCList = new ArrayList<>();
		Iterable<FuelConsumptionEntity> results = this.fuelConsRepo.findAll();
		Calendar cal = Calendar.getInstance();
		if (driverId == null || driverId.trim().equals("")) {
			results.forEach(fc -> {
				cal.setTime(fc.getDate());
				int month = cal.get(Calendar.MONTH);
				if ((month + 1) == monthId)
					FCList.add(fc);
			});
		} else {
			results.forEach(fc -> {
				cal.setTime(fc.getDate());
				int month = cal.get(Calendar.MONTH);
				if ((month + 1) == monthId && fc.getDriverId().equals(driverId))
					FCList.add(fc);
			});
		}

		return FCList;

	}

	/**
	 * Grouping Fuels for every month for all records at the database
	 * 
	 * @param driverId
	 * @return
	 */
	public Map<YearMonth, Double> getTotalMoneySpentGroupedByMonth(String driverId) {
		List<FuelConsumptionEntity> FCList = new ArrayList<>();
		Iterable<FuelConsumptionEntity> results = this.fuelConsRepo.findAll();
		if (null == driverId || driverId.trim().equals(""))
			results.forEach(fc -> FCList.add(fc));
		else
			results.forEach(fc -> {
				if (fc.getDriverId().equals(driverId))
					FCList.add(fc);
			});
		/**
		 * using stream and collectors to loop over all fuels and extract the month &
		 * Year (unique) and group elements using YearMonth and extract price and
		 * accumulating it
		 */
		Map<YearMonth, Double> map = FCList.stream().collect(Collectors.groupingBy(
				element -> YearMonth.from(element.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
				TreeMap::new, Collectors.summingDouble(element -> element.getPrice())));

		return map;

	}

	/**
	 * This method is to get all data about all fuels ever happened in the system
	 * 
	 * @return List<FuelConsumptionEntity> : list of fuels objects with all the
	 *         required data
	 */
	public List<FuelConsumptionEntity> getAllFuels() {
		List<FuelConsumptionEntity> FCList = new ArrayList<>();
		Iterable<FuelConsumptionEntity> results = this.fuelConsRepo.findAll();
		results.forEach(fc -> {
			FCList.add(fc);
		});
		return FCList;
	}

	public List<FuelConsumptionGroupInterface> getAllFuelsGroupedByType() {
		return this.fuelConsRepo.getFuelsGroupedByFuelType();
	}

	public void saveFuelBulk(List<FuelConsumptionEntity> FCEList) {
		this.fuelConsRepo.saveAll(FCEList);
	}

}
