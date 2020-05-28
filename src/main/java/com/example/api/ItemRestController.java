package com.example.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Item;
import com.example.service.ItemService;

@RestController
@RequestMapping("api/items")
public class ItemRestController {
	@Autowired /*ItemServiceのインスタンス作成*/
	ItemService itemService;
	
	/*GET*/
	
	@GetMapping
	List<Item> getItems(){
		List<Item> customers = itemService.findAll(); /*JpaRepositoryのfindAllを呼び出しitemをListで取得*/
		return customers;//全件検索結果をリターン
	}
	
	@GetMapping(path = "{id}")
	Optional<Item> getItem(@PathVariable("id") Integer id) {
		return itemService.findById(id);
	}
	
	@GetMapping("findByPrice")
	List<Item> getItemPrice(@RequestParam("price") Integer price){
		List<Item> customer = itemService.findByPrice(price); 
		return customer;
	}
	
	@GetMapping("findByNameAndPrice")
	List<Item> getItemNamePrice(@RequestParam("name") String name, @RequestParam("price") Integer price){
		System.out.println(name);
		System.out.println(price);
		List<Item> ctr = itemService.findByNameAndPrice(name, price); 
		return ctr;
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
