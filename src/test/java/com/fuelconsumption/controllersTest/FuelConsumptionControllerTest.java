package com.fuelconsumption.controllersTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fuelconsumption.business.services.FuelConsumptionService;
import com.fuelconsumption.controllers.FuelConsumptionsController;
import com.fuelconsumption.model.entities.FuelConsumptionEntity;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(FuelConsumptionsController.class)
public class FuelConsumptionControllerTest {
	
	@MockBean
	private FuelConsumptionService fuelConsumptionService;
	@Autowired
	private MockMvc mockMvc ;
	
	/**
	 * perform a test on getting all fuels record back from the database
	 * @throws Exception
	 */
	@Test
	public void getAllFuels() throws Exception
	{
		List<FuelConsumptionEntity> mockFuelConsumptionsList =  new ArrayList<>() ;
		FuelConsumptionEntity mockFuelConsumptionEntity = new FuelConsumptionEntity();
		mockFuelConsumptionEntity.setDate(new Date());
		mockFuelConsumptionEntity.setDriverId("123");
		mockFuelConsumptionEntity.setFuelType("Super");
		mockFuelConsumptionEntity.setID(1L);
		mockFuelConsumptionEntity.setPrice(5.43);
		mockFuelConsumptionEntity.setVolume(9);
		mockFuelConsumptionsList.add(mockFuelConsumptionEntity);
		
		given(fuelConsumptionService.getAllFuels()).willReturn(mockFuelConsumptionsList);
		this.mockMvc.perform(get("/FuelConsumptions/allFuels")).andExpect(status().isOk());
		
	}
	

}
