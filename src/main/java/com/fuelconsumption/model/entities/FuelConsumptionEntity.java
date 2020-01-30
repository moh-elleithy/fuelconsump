package com.fuelconsumption.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entity class which contains all the attributes,  mapped to the Fuel Consumption table
 * Each attribute has its own validation and error massages
 * 
 * @author mohamed.elleithy
 *
 */
@Entity
@Table(name = "FUEL_CONSUMPTION")
public class FuelConsumptionEntity {

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	@Column(name = "FUEL_TYPE")
	@NotBlank(message = "Fuel Type must be a valied word !!!")
	private String fuelType;

	@Column(name = "PRICE")
	@NotNull
	@Min(value = 1, message = "Price must be a bigger than 0 Euro !!!")
	private double price;

	@Column(name = "DATE")
	@NotNull(message = "Date must be enterted !!!")
	private Date date;

	@Column(name = "DRIVER_ID")
	@NotNull
	@NotBlank(message = "Driver ID must be a valied String !!!")
	private String driverId;

	@Column(name = "VOLUME")
	@NotNull
	@Min(value = 1, message = "Fuel voulum must be bigger than 0 Liter !!!")
	private double volume;

	public FuelConsumptionEntity() {

	}

	public FuelConsumptionEntity(Long iD, @NotBlank(message = "Fuel Type must be a valied word !!!") String fuelType,
			@NotNull @Min(value = 1, message = "Price must be a bigger than 0 Euro !!!") double price,
			@NotNull(message = "Date must be enterted !!!") Date date,
			@NotNull @NotBlank(message = "Driver ID must be a valied String !!!") String driverId,
			@NotNull @Min(value = 1, message = "Fuel voulum must be bigger than 0 Liter !!!") double voulum) {
		super();
		ID = iD;
		this.fuelType = fuelType;
		this.price = price;
		this.date = date;
		this.driverId = driverId;
		this.volume = voulum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

}
