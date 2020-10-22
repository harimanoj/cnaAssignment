package com.wipro.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SKUController {

	static AtomicInteger ID = new AtomicInteger();
	List<SKU> skus = new ArrayList<SKU>();

	
	@GetMapping("/skus")
	public List<SKU> getSKUs() {
		return skus;
	}
	

	@PostMapping("/skus")
	public ResponseEntity<?> addSKU(@Valid @RequestBody SKU newSku, BindingResult result) {
		try {
			if (result.hasErrors()) {
				List<FieldError> errors = result.getFieldErrors();
				String message = null;
				for (FieldError error : errors) {
					message = error.getDefaultMessage() + System.lineSeparator();
				}
				return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
			}
			newSku.setId(ID.incrementAndGet());
			skus.add(newSku);
			return new ResponseEntity<SKU>(newSku, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/skus/{id}")
	public ResponseEntity<?> getSKU(@PathVariable Integer id) {
		for (SKU sku : skus) {
			if (sku.getId() == id) {
				return new ResponseEntity<SKU>(sku, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("SKU not Found", HttpStatus.NOT_FOUND);

	}
	

	@PutMapping("/skus/{id}")
	public ResponseEntity<?> updateSKU(@PathVariable Integer id, @Valid @RequestBody SKU newSKU, BindingResult result) {
		try {
			if (result.hasErrors()) {
				List<FieldError> errors = result.getFieldErrors();
				String message = null;
				for (FieldError error : errors) {
					message = error.getDefaultMessage() + System.lineSeparator();
				}
				return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
			}

			for (SKU sku : skus) {
				if (sku.getId() == id) {
					sku.setCount(newSKU.getCount());
					sku.setDescription(newSKU.getDescription());
					sku.setName(newSKU.getName());
					sku.setPrice(newSKU.getPrice());
					sku.setProductId(newSKU.getProductId());
					return new ResponseEntity<SKU>(sku, HttpStatus.OK);
				}
				
			}
			return new ResponseEntity<String>("SKU not Found", HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@DeleteMapping("/skus/{id}")
	public ResponseEntity<?> deleteSKU(@PathVariable Integer id) {
		for (SKU sku : skus) {
			if (sku.getId() == id) {
				skus.remove(sku);
				return new ResponseEntity<SKU>(sku, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("SKU not Found", HttpStatus.NOT_FOUND);

	}

}
