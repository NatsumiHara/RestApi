package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.domain.Item;
import com.example.service.ItemService;

@RestController
@RequestMapping("api/items")
public class ItemRestController {
	@Autowired
	ItemService itemService;
	
	/*GET*/
	
	@GetMapping
	List<Item> getItems(){

		List<Item> customers = itemService.findAll();
		return customers;
	}
	
	/*POST*/
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Item postltem(@RequestBody Item item) {
		return itemService.create(item);
	}
	
	/*DELETE*/
	
	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteItem(@PathVariable Integer id) {
		itemService.delete(id);
	}
	
	/*PUT*/
	
	@PutMapping(path = "{id}")
	Item putItem(@PathVariable Integer id, @RequestBody Item item) {
		item.setId(id);
		return itemService.update(item);
	}
	
	

}
