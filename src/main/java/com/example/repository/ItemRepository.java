package com.example.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Item;


public interface ItemRepository extends JpaRepository<Item,Integer>{

List<Item> findByPrice(Integer price);
List<Item> findByNameAndPrice(String name, Integer price);
	

	

	

}

/*DBアクセス用*/
/*JpaRepository<扱うEntityクラス,プライマリキーの型>*/
/*中身を書かなくてもJpaRepositoryを継承するだけでitemににアクセスするための基本的な機能が用意される*/