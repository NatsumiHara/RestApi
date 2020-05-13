何を作ったか：REST API(GET,POST,DELETE,PUT)
H2detabase テーブル名:items
格納されているデータ:
INSERT INTO items(name, price, img_path) VALUES('りんご', 100,  'apple.jpg');
INSERT INTO items(name, price, img_path) VALUES('ばなな', 200, 'banana.jpg');
INSERT INTO items(name, price, img_path) VALUES('みかん', 300, 'mikan.jpg');
INSERT INTO items(name, price, img_path) VALUES('ぶどう', 400, 'glape.jpg');

アクセス:http://localhost:8080/api/items

<POSTコマンド>
$ curl http://localhost:8080/api/items -i -XPOST -H "Content-Type: application/json" -d "{\"name\":\"もも\",\"price\":\"1000\",\"imgPath\":\"peach.jpg\"}"
HTTP/1.1 201 

↓

結果:[{"id":1,"name":"りんご","price":100,"imgPath":"apple.jpg"},{"id":2,"name":"ばなな","price":200,"imgPath":"banana.jpg"},{"id":3,"name":"みかん","price":300,"imgPath":"mikan.jpg"},{"id":4,"name":"ぶどう","price":400,"imgPath":"glape.jpg"},{"id":5,"name":"もも","price":1000,"imgPath":"peach.jpg"}]
<DELETEコマンド>
$ curl http://localhost:8080/api/items/1 -i -XDELETE

↓

結果:[{"id":2,"name":"ばなな","price":200,"imgPath":"banana.jpg"},{"id":3,"name":"みかん","price":300,"imgPath":"mikan.jpg"},{"id":4,"name":"ぶどう","price":400,"imgPath":"glape.jpg"},{"id":5,"name":"もも","price":1000,"imgPath":"peach.jpg"}]
HTTP/1.1 204 
<PUTコマンド>
$ curl http://localhost:8080/api/items/4 -i -XPUT -H "Content-Type: application/json" -d "{\"name\":\"もも\",\"price\":\"1000\",\"imgPath\":\"peach.jpg\"}"
HTTP/1.1 200 

↓

結果:[{"id":2,"name":"ばなな","price":200,"imgPath":"banana.jpg"},{"id":3,"name":"みかん","price":300,"imgPath":"mikan.jpg"},{"id":4,"name":"もも","price":1000,"imgPath":"peach.jpg"},{"id":5,"name":"もも","price":1000,"imgPath":"peach.jpg"}]




