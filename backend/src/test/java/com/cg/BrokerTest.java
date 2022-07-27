package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.entity.Broker;
import com.cg.entity.Property;
import com.cg.service.IBrokerService;
/******************************************************************************
 * 
 * @author 			T. CHAITANYA
 * Description 		It is a testing class that tests all the methods of Broker
 * Version			1.0
 * Created Date		30-MAR-2021
 ******************************************************************************/
@SpringBootTest
public class BrokerTest extends AbstractTest{
	@Autowired
	IBrokerService iSer;
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	/*****************************************************************************
	 * Method			Test Method for get Broker
	 * Description 		To check the working of get Broker method in Broker Module 
	 * Created By		T. CHAITANYA
	 * Created Date		30-MAR-2021
	 ******************************************************************************/
	@Test
	public void getBroker() throws Exception{
		String uri = "/real-estate-broker-application/broker/id/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		String content = mvcResult.getResponse().getContentAsString();
		Broker bro = super.mapFromJson(content, Broker.class);
		assertEquals("Jagan",bro.getBroName());
	}
	
	/*********************************************************************************
	 * Method			Test Method for get All Broker
	 * Description 		To check the working of get All Broker method in Broker Module
	 * Created By		T. CHAITANYA
	 * Created Date		30-MAR-2021
	 *********************************************************************************/
	@Test
	public void getAllBroker() throws Exception{
		String uri = "/real-estate-broker-application/broker/all";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		String content = mvcResult.getResponse().getContentAsString();
		Broker brokerList[] = super.mapFromJson(content, Broker[].class);
		assertEquals("Baskar",brokerList[1].getBroName());
	}
	
	/********************************************************************************
	 * Method			Test Method for create Broker
	 * Description 		To check the working of create Broker method in Broker Module 
	 * Created By		T. CHAITANYA
	 * Created Date		30-MAR-2021
	 *********************************************************************************/
	@Test
	public void createBroker() throws Exception{
		String uri = "/real-estate-broker-application/broker/add";
		List<Property> p = new ArrayList<Property>();
		Broker bro = new Broker();
		bro.setPassword("Ramu*&67");
		bro.setRole("Broker");
		bro.setMobile("9876784567");
		bro.setEmail("ramu7765@gmail.com");
		bro.setCity("Delhi");
		bro.setBroName("Ram Suresh");
		bro.setProperties(p);
		String inputJson = super.mapToJson(bro);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Broker broker = super.mapFromJson(content, Broker.class);
		assertEquals("Broker", broker.getRole());
	}
	
	/********************************************************************************
	 * Method			Test Method for update Broker
	 * Description 		To check the working of update Broker method in Broker Module
	 * Created By		T. CHAITANYA
	 * Created Date		30-MAR-2021
	 ********************************************************************************/
	@Test
	public void updateBroker() throws Exception{
		
		String uri = "/real-estate-broker-application/broker/update";
		String uri2 = "/real-estate-broker-application/broker/id/3";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri2)).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Broker b = super.mapFromJson(content, Broker.class);
		b.setCity("Chennai");
		String inputJson = super.mapToJson(b);
		MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult1.getResponse().getStatus();
		assertEquals(200, status);
		String content1 = mvcResult1.getResponse().getContentAsString();
		Broker b1 = super.mapFromJson(content1, Broker.class);
		assertEquals("Chennai", b1.getCity());
	}
	
	/********************************************************************************
	 * Method			Test Method for delete Broker
	 * Description 		To check the working of delete Broker method in Broker Module
	 * Created By		T. CHAITANYA
	 * Created Date		30-MAR-2021
	 *********************************************************************************/
	@Test
	public void deleteBroker() throws Exception{
		String uri = "/real-estate-broker-application/broker/remove/20";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		String content = mvcResult.getResponse().getContentAsString();
		Broker bro = super.mapFromJson(content, Broker.class);
		assertEquals("Vizayanagaram",bro.getCity());
	}
}
