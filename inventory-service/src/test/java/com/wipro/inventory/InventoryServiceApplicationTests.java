package com.wipro.inventory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryServiceApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void test01ContextLoads() {
		assertNotNull("Rest Template not found", restTemplate);
	}
	
	@Test
	public void test02GetSKUs() {
		ResponseEntity<List> res=restTemplate.getForEntity("/skus", List.class);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(0, res.getBody().size());
	}
	
	@Test
	public void test03AddSKU() {
		SKU sku=new SKU();
		sku.setName("one");
		sku.setCount(5);
		sku.setPrice(5.5);
		sku.setProductId(1);
		ResponseEntity<SKU> res=restTemplate.postForEntity("/skus", sku, SKU.class);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(sku.getName(), res.getBody().getName());
		assertTrue(res.getBody().getId()!=null);
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test04getSKUs() {
		ResponseEntity<List> res=restTemplate.getForEntity("/skus", List.class);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(1, res.getBody().size());
	}
	
	@Test
	public void test05UpdateSKU() {
		SKU sku=new SKU();
		sku.setName("pencil");
		sku.setProductId(1);
		HttpEntity<SKU> entity=new HttpEntity<SKU>(sku);
		ResponseEntity<SKU> res=restTemplate.exchange("/skus/1",HttpMethod.PUT, entity, SKU.class);		
		assertEquals(HttpStatus.OK, res.getStatusCode());		
	}
	
	@Test
	public void test06DeleteSKU() {
		SKU sku=new SKU();
		HttpEntity<SKU> entity=new HttpEntity<SKU>(sku);
		ResponseEntity<SKU> res=restTemplate.exchange("/skus/1",HttpMethod.DELETE,entity,SKU.class);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(1, res.getBody().getId());
	}
	
	
	

}
