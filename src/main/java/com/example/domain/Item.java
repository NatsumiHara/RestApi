package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity /*テーブル*/
@Table(name = "items") /*テーブル名*/
@Data /*@Getter@Setter@ToString@EqualsAndHashCode@RequiredArgsConstructorが付与された状態になる*/
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

	private static final long serialVersionUID = 1L; //同一クラスかを判定するものがserialVersionUID 説明:https://teratail.com/questions/113411

	@Id /*プライマリキーとなる*/
	@GeneratedValue(strategy = GenerationType.IDENTITY) /*ID自動生成*/
	private Integer id;
	@Column(nullable = false) /*カラム名設定*/
	private String name;
	@Column(nullable = false)
	private Integer price;
	private String imgPath;

}
