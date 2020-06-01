package com.example.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Item;
import com.example.main.RestApiApplication;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RestApiApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // メインクラスがどこにあるか指定する
//DEFINED_PORT
public class ItemRestControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	int port;
	
	/*GET*/

	@Test
	public void getItemsTest() throws Exception {
//		List<Item> items = this.restTemplate.getForObject("http://localhost:" + port + "/api/items",
//				List.class);
		String url= "http://localhost:" + port + "/api/items";
		ObjectMapper objectMapper = new ObjectMapper();
		List<Item> items = objectMapper.convertValue(this.restTemplate.getForObject(url, Object.class),
				new TypeReference<List<Item>>() {
				});//オブジェクトに復元する時Listになって返ってきていないのでListに変換する
//		List<Item> items = this.restTemplate.getForObject(url, List.class);
//		List<Item> list = new ArrayList<Item>();
//		Item item = new Item();
//		expected.add(item);
		
//		for(Item value : items) {
//		    assertThat(value.get).isEqualTo(list);
//		}
		assertThat(items.size()).isEqualTo(4);
		System.out.println("★");
	}
	
	@Test
	public void getItemTest() throws Exception {
//		System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/api/items/1",
//				Optional.class));
		String url = "http://localhost:" + port + "/api/items/1";
		Item item = this.restTemplate.getForObject(url, Item.class);
//		Item expected = new Item();
//		expected.setId(1);	//getId()でidのみでチェックしてるため、idだけsetする。
		assertThat(item.getId()).isEqualTo(1); // AssertionsクラスのassertThatはstaticなのでnewができない。なのでimportでメソッドassertThatまで定義する
		System.out.println("★");
	}

	@Test
	public void getItemPriceTest() throws Exception {
//		System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/api/items/findByPrice?price=200",
//				List.class));
		String url = "http://localhost:" + port + "/api/items/findByPrice?price=200";
//		@SuppressWarnings("unchecked")
//		List<Item> itemPrice = (List<Item>)this.restTemplate.getForObject(url, Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Item> itemPrice = objectMapper.convertValue(this.restTemplate.getForObject(url, Object.class),
				new TypeReference<List<Item>>() {
				});//オブジェクトに復元する時Listになって返ってきていないのでListに変換する
//		System.out.println(itemPrice.get(0).getPrice());
//		itemPrice.forEach(item->assertThat(item.getPrice()).isEqualTo(200));
//for (Item item : itemPrice) {
//	
//    assertThat(item.getPrice()).isEqualTo(200);
//}
//		List<Item> expected = new ArrayList<Item>();//Itemが入るListの型を作成（itemが入ってはいない）
////		((Item) expected).setPrice(200);	
//		Item item =new Item();//Itemクラスをnew
//		item.setPrice(200);//itemにprice200を入れる
//		expected.add(item);//Listにitem(200)を入れる（ある分）
		for(Item value : itemPrice) { //コロン右側にはループ処理を行いたいListを指定し左側にはひとつずつ取得した要素の値を代入
		    assertThat(value.getPrice()).isEqualTo(200);
		}
//		assertThat(itemPrice.getPrice()).isEqualTo(((Item) expected).getPrice());
		System.out.println("★");
	}

	@Test
	public void getItemNamePriceTest() throws Exception {
//		System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/api/items/findByNameAndPrice?name=みかん&price=300",
//				List.class));
		String url = "http://localhost:" + port + "/api/items/findByNameAndPrice?name=みかん&price=300";
		ObjectMapper objectMapper = new ObjectMapper();
		List<Item> itemNamePrice = objectMapper.convertValue(this.restTemplate.getForObject(url, Object.class),
				new TypeReference<List<Item>>() {
				});
//		Item itemName = this.restTemplate.getForObject(url, Item.class);
//		Item expected = new Item();
//		expected.setName("みかん");
//		expected.setPrice(300);
		for(Item value : itemNamePrice) { //コロン右側にはループ処理を行いたいListを指定し左側にはひとつずつ取得した要素の値を代入
			assertThat(value.getName()).isEqualTo("みかん");
			assertThat(value.getPrice()).isEqualTo(300);
		}
		System.out.println("★");
	}
	
	/*POST*/
	
	@Test
	public void postItemTest() throws Exception {
		String url= "http://localhost:" + port + "/api/items";
	    Item item =new Item();
	    item.setName("もも");//"{\"name\":\"もも\",\"price\":\"1000\",\"imgPath\":\"peach.jpg\"}"
	    item.setPrice(1000);
	    item.setImgPath("peach.jpg");
		
		Item postItem = this.restTemplate.postForObject(url, item, Item.class);
		assertThat(postItem.getId()).isEqualTo(5);
		System.out.println("★");
	}
	
	/*DELETE*/
	
	@Test
	public void deleteItemTest() throws Exception {
//		this.restTemplate.delete("http://localhost:" + port + "/api/items/1");
		String url= "http://localhost:" + port + "/api/items/1";
		this.restTemplate.delete(url);//指定されたURLにあるリソースを削除
		//Item deleteItem = this.restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Item.class);
		Item deleteItem = this.restTemplate.getForObject(url, Item.class);
//		Item expected = new Item();
//		expected.setId(1);	//getId()でidのみでチェックしてるため、idだけsetする。
		assertNull(deleteItem);
//		assertThat(deleteItem.getId()).isNull();		
		System.out.println("★");
	}
	
	/*PUT*/
	
	@Test
	public void putItemTest() throws Exception {
		String url= "http://localhost:" + port + "/api/items/4";
//		this.restTemplate.delete(url);
		 Item item =new Item();
		   	item.setId(4);
		    item.setName("もも");//"{\"name\":\"もも\",\"price\":\"1000\",\"imgPath\":\"peach.jpg\"}"
		    item.setPrice(1000);
		    item.setImgPath("peach.jpg");
		    this.restTemplate.put(url, item);//指定されたオブジェクトをURLにPUTして、新しいリソースを作成
//		    Item putItem = this.restTemplate.postForObject(url, item, Item.class);
		    URI uri =new URI(url);
		    RequestEntity<Item> requestEntity = RequestEntity
		    		.put(uri)
		    		.body(item);
		    this.restTemplate.exchange(requestEntity, Item.class);
		    Item putItem = this.restTemplate.getForObject(url, Item.class); 							
//		    System.out.println(putItem.getBody());
			assertThat(putItem.getName()).isEqualTo("もも");
			System.out.println(putItem);
			System.out.println("★");
	}

}
