package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	@Autowired
	ItemRepository itemRepository;

	/*Get*/
	public List<Item> findAll() {
		return itemRepository.findAll();
	}
	
	/*GETid*/

	public Optional<Item> findById(Integer id) {
		return itemRepository.findById(id);
	}
	
	/*GETprice*/
	
	public List<Item> findByPrice(Integer price){
		return itemRepository.findByPrice(price);
	}
	
	/*GETname+price*/
	
	public List<Item> findByNameAndPrice(String name, Integer price){
		return itemRepository.findByNameAndPrice(name, price);
	}
		
	/*POST*/
	public Item create(Item item) {
		return itemRepository.save(item);
	}
	
	/*DELETE*/
	public void delete(Integer id) {
		itemRepository.deleteById(id);
	}
	
	/*PUT*/
	
	public Item update(Item item) {
		return itemRepository.save(item);
	}

	
	

}
