package com.example.service;

import java.util.List;

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
