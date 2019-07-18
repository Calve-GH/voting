<article class="markdown-body entry-content p-5" itemprop="text"><p>Design and implement a JSON API using Hibernate/Spring/SpringMVC <strong>without frontend</strong>.</p> 
<p>The task is:</p>
<p>Build a voting system for deciding where to have lunch.</p>
<ul>
<li>2 types of users: admin and regular users</li>
<li>Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)</li>
<li>Menu changes each day (admins do the updates)</li>
<li>Users can vote on which restaurant they want to have lunch at</li>
<li>Only one vote counted per user</li>
<li>If user votes again the same day:
<ul>
<li>If it is before 11:00 we asume that he changed his mind.</li>
<li>If it is after 11:00 then it is too late, vote can't be changed</li>
</ul>
</li>
</ul>
<p>Each restaurant provides new menu each day.</p>
<p>As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.</p>
<p>P.S.: you can use a project seed you find where all technologies are already preconfigured.</p>
<p>P.P.S.: Make sure everything works with latest version that is on github :)</p>
<p>P.P.P.S.: Asume that your API will used by a frontend developer to build frontend on top of that.</p>
</article>

<h3>Voting API</h3>
<table cellspacing="2" border="1" cellpadding="5">
	<thead>
		<tr><th></th><th></th></tr>
	</thead>
<tbody>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Get dishes</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/dishes/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>GET</strong></td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 200
			<strong>Content:</strong>
			<pre>
[{"id": 100003, "name": "Soup"},
{"id": 100004, "name": "French fries"},
{"id": 100005, "name": "Hamburger"},
{"id": 100006, "name": "Tea"},
{"id": 100007, "name": "Coffee"}]
			</pre>
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/dishes/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Create dish</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/dishes/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>POST</strong></td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 201
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td>
			<code>request body:{"name": "Soup"}</code>
			</br>
			<code>/rest/admin/dishes/</code>
		</td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Get dish</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/dishes/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>GET</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>dishId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 200
			<strong>Content:</strong>
			<pre>
{"id": 100003, "name": "Soup"}
			</pre>
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/dishes/100003/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Delete dish</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/dishes/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>DELETE</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>dishId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 204
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/dishes/100003/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Update dish</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/dishes/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>PUT</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>dishId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 204
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td>
			<code>request body:{"name": "New soup"}</code>
			</br>
			<code>/rest/admin/dishes/100003/</code>
		</td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Get restaurants</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/restaurants/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>GET</strong></td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 200
			<strong>Content:</strong>
			<pre>
[{"id": 100000, "name": "Sweet bobaleh"},
{"id": 100001, "name": "ITAKA"},
{"id": 100002, "name": "Hunter Village"}]
			</pre>
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/restaurants/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Create restaurant</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/restaurants/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>POST</strong></td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 201
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td>
			<code>request body:{"name": "New restaurant"}</code>
			</br>
			<code>/rest/admin/restaurants/</code>
		</td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Get restaurant</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/restaurants/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>GET</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>restaurantId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 200
			<strong>Content:</strong>
			<pre>
{"id": 100000, "name": "Sweet bobaleh"}
			</pre>
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/restaurants/100000/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Delete restaurant</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/restaurants/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>DELETE</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>restaurantId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 204
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/restaurants/100000/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Update restaurant</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/restaurants/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>PUT</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>restaurantId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 204
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td>
			<code>request body:{"name": "New restaurant"}</code>
			</br>
			<code>/rest/admin/restaurants/100000/</code>
		</td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Get menu items</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/items/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>GET</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>restaurantId=[Integer]</br>
		date=[yyyy-MM-dd] required=false</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 200
			<strong>Content:</strong>
			<pre>
[{"id":100018,"date":"2019-05-27","restaurant":{"id":100000},
"dish":{"id":100005,"name":"Hamburger"},"price":15.0},
{"id":100019,"date":"2019-05-27","restaurant":{"id":100000},
"dish":{"id":100006,"name":"Tea"},"price":2.0},
{"id":100020,"date":"2019-05-27","restaurant":{"id":100000},
"dish":{"id":100007,"name":"Coffee"},"price":3.0}]
			</pre>
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/items/?restaurantId=100000&date=2019-05-27</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Save menu items</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/items/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>POST</strong></td>
	</tr>
		<tr>
		<td><strong>URL Parameters</strong></td>
		<td>restaurantId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 201
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td>
			<code>request body:{"date":"2019-05-27",
								"items":
									[{"date":"2019-05-27","dish":{"id":100008,"name":"Juice"},"price":5.0},
									{"date":"2019-05-27","dish":{"id":100004,"name":"French fries"},"price":11.0}],
								"count":0}
			</code>
			</br>
			<code>/rest/admin/items/?restaurantId=100000</code>
		</td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Delete menu items</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/items/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>DELETE</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>restaurantId=[Integer]</br>
		date=[yyyy-MM-dd]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 204
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td>
			<code>/rest/admin/items/?restaurantId=100000&date=2019-05-27</code>
		</td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Get menu item</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/items/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>GET</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>menuItemId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 200
			<strong>Content:</strong>
			<pre>
{"id":100014,"date":"2019-05-27","restaurant":null,
"dish":{"id":100008,"name":"Juice"},"price":5.0}
			</pre>
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/items/100014/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Delete menu item</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/items/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>DELETE</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>menuItemId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 204
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/admin/items/100014/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Update menu item</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/admin/items/id/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>PUT</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>menuItemId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 204
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td>
			<code>request body:{"id":100014,"date":"2019-05-27",
			"restaurant":{"id":100000},"dish":{"id":100008,"name":"Juice"},"price":33.3}</code>
			</br>
			<code>/rest/admin/restaurants/100014/</code>
		</td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Get vote list</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/vote/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>GET</strong></td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 200
			<strong>Content:</strong>
			<pre>
[{"restaurant":{"id":100000,"name":"Sweet bobaleh",
"items":[{"id":100009,"date":"2019-05-27",
	"restaurant":{"id":100000},"dish":{"id":100003,"name":"Soup"},"price":5.0},{"id":100010,"date":"2019-05-27",
	"restaurant":{"id":100000},"dish":{"id":100004,"name":"French fries"},"price":10.0},{"id":100011,"date":"2019-05-27",
	"restaurant":{"id":100000},"dish":{"id":100005,"name":"Hamburger"},"price":15.0},{"id":100012,"date":"2019-05-27",
	"restaurant":{"id":100000},"dish":{"id":100006,"name":"Tea"},"price":2.0},{"id":100013,"date":"2019-05-27",
	"restaurant":{"id":100000},"dish":{"id":100007,"name":"Coffee"},"price":3.0}]},
"count":1},{
"restaurant":{"id":100001,"name":"ITAKA",
"items":[{"id":100015,"date":"2019-05-27",
	"restaurant":{"id":100001},"dish":{"id":100004,"name":"French fries"},"price":11.0},{"id":100014,"date":"2019-05-27",
	"restaurant":{"id":100001},"dish":{"id":100008,"name":"Juice"},"price":5.0}]},
"count":2},{
"restaurant":{"id":100002,"name":"Hunter Village",
"items":[{"id":100016,"date":"2019-05-27",
	"restaurant":{"id":100002},"dish":{"id":100004,"name":"French fries"},"price":10.0},{"id":100017,"date":"2019-05-27",
	"restaurant":{"id":100002},"dish":{"id":100005,"name":"Hamburger"},"price":16.0}]},
"count":0}]
			</pre>
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/vote/</code></td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Vote</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/vote/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>POST</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>restaurantId=[Integer]</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 201
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td>
			<code>/rest/vote/100000</code>
		</td>
	</tr>
	<tr><th></th><th></th></tr>
	<tr>
	<td><strong>Title</strong></td>
		<td><strong>Get vote history</strong></td>
	</tr>
	<tr>
		<td><strong>URL</strong></td>
		<td><code>/rest/vote/history/</code></td>
	</tr>
	<tr>
		<td><strong>Method</strong></td>
		<td><strong>GET</strong></td>
	</tr>
	<tr>
		<td><strong>URL Parameters</strong></td>
		<td>date=[yyyy-MM-dd] required=false</br>
		restaurantId=[Integer] required=false</td>
	</tr>
	<tr>
		<td><strong>Success Response</strong></td>
		<td>
			<strong>Code:</strong> 200
			<strong>Content:</strong>
			<pre>
[{"restaurant":{"id":100000,"name":"Sweet bobaleh",
"items":[{"id":100009,"date":"2019-05-26",
	"restaurant":{"id":100000},"dish":{"id":100003,"name":"Soup"},"price":5.0},{"id":100010,"date":"2019-05-26",
	"restaurant":{"id":100000},"dish":{"id":100004,"name":"French fries"},"price":10.0},{"id":100011,"date":"2019-05-26",
	"restaurant":{"id":100000},"dish":{"id":100005,"name":"Hamburger"},"price":15.0},{"id":100012,"date":"2019-05-26",
	"restaurant":{"id":100000},"dish":{"id":100006,"name":"Tea"},"price":2.0},{"id":100013,"date":"2019-05-26",
	"restaurant":{"id":100000},"dish":{"id":100007,"name":"Coffee"},"price":3.0}]},
"count":99}]
			</pre>
		</td>
	</tr>
	<tr>
		<td><strong>Sample Request</strong></td>
		<td><code>/rest/vote/history/?date=2019-05-26&restaurantId=100000</code></td>
	</tr>
</tbody>
</table>
<h3>CURL</h3>
<p><strong>Get all dishes:</br></strong>
curl http://localhost:8080/voting/rest/admin/dishes/ -u ivanov@gmail.com:1234567</p>
<p><strong>Save new dish:</br></strong>
curl -H "Content-Type: application/json;charset=UTF-8" -X POST -d '"Coca Cola"' http://localhost:8080/voting/rest/admin/dishes/ -u ivanov@gmail.com:1234567</p>
<p><strong>Get dish by id:</br></strong>
curl http://localhost:8080/voting/rest/admin/dishes/100003 -u ivanov@gmail.com:1234567</p>
<p><strong>Delete dish by id:</br></strong>
curl -X DELETE http://localhost:8080/voting/rest/admin/dishes/100003 -u ivanov@gmail.com:1234567</p>
<p><strong>Update dish by id:</br></strong>
curl -X PUT -H "Content-Type: application/json" -d '"Cream Soup"' http://localhost:8080/voting/rest/admin/dishes/100003 -u ivanov@gmail.com:1234567</p>
<p><strong>Get all restaurants:</br></strong>
curl http://localhost:8080/voting/rest/admin/restaurants/ -u ivanov@gmail.com:1234567</p>
<p><strong>Save new restaurant:</br></strong>
curl -H "Content-Type: application/json;charset=UTF-8" -X POST -d '"Coco Bongo"' http://localhost:8080/voting/rest/admin/restaurants/ -u ivanov@gmail.com:1234567</p>
<p><strong>Get restaurant:</br></strong>
curl http://localhost:8080/voting/rest/admin/restaurants/100000 -u ivanov@gmail.com:1234567</p>
<p><strong> Delete restaurant by id:</br></strong>
curl -X DELETE http://localhost:8080/voting/rest/admin/restaurants/100000 -u ivanov@gmail.com:1234567</p>
<p><strong>Update restaurant by id:</br></strong>
curl -X PUT -H "Content-Type: application/json" -d '"New restaurant"' http://localhost:8080/voting/rest/admin/restaurants/100000 -u ivanov@gmail.com:1234567</p>
<p><strong>Get menu items by restaurantId and date:</br></strong>
curl http://localhost:8080/voting/rest/admin/items/?restaurantId=100000&date=2019-05-27 -u ivanov@gmail.com:1234567</p>
<p><strong>Save menu items:</br></strong>
curl -H "Content-Type: application/json;charset=UTF-8" -X POST -d '{"date":"2019-05-21","items":[{"date":"2019-05-27","dish":{"id":100008,"name":"Juice"},"price":5.0},{"date":"2019-05-27","dish":{"id":100004,"name":"French fries"},"price":11.0}],"count":0}' http://localhost:8080/voting/rest/admin/items/ -u ivanov@gmail.com:1234567</p>
<p><strong>Delete items by restaurantId and date:</br></strong>
curl -X DELETE http://localhost:8080/voting/rest/admin/items/?restaurantId=100000&date=2019-05-27 -u ivanov@gmail.com:1234567</p>
<p><strong>Get menu item by id:</br></strong>
curl http://localhost:8080/voting/rest/admin/items/100014 -u ivanov@gmail.com:1234567</p>
<p><strong>Delete menu item by id:</br></strong>
curl -X DELETE http://localhost:8080/voting/rest/admin/items/100014 -u ivanov@gmail.com:1234567</p>
<p><strong>Update menu item by id:</br></strong>
curl -X PUT -H "Content-Type: application/json" -d '{"id":100014,"date":"2019-07-18","restaurant":{"id":100000},"dish":{"id":100008,"name":"Juice"},"price":33.3}' http://localhost:8080/voting/rest/admin/items/100014 -u ivanov@gmail.com:1234567</p>
<p><strong>Get vote list:</br></strong>
curl http://localhost:8080/voting/rest/vote/ -u ivanov@gmail.com:1234567</p>
<p><strong>Vote by restaurant:</br></strong>
curl -X POST http://localhost:8080/voting/rest/vote/100000/ -u ivanov@gmail.com:1234567</p>
<p><strong>Get history by date:</br></strong>
curl http://localhost:8080/voting/rest/vote/history/?date=2019-05-27 -u ivanov@gmail.com:1234567</p>
