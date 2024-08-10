## Aggregator Microservice with GraphQL And Rest Support

## Discovery Service
Uses `spring-cloud-starter-netflix-eureka-server` dependency.

### To run the discovery server: 
```shell
# From project root
./gradlew :discovery:bootRun
```

### Access the discovery server UI here
http://127.0.0.1:8761/

## Product Service

Product Microservice exposing endpoints to get all the available products or get a product detail by ID.

### To run the product service:
```shell
# From project root
./gradlew :product:bootRun
```

### API

- List API 
```shell
curl http://localhost:8070/product
```

Output:
```json
[{"id":"smartphone","name":"Smartphone","description":"High-end smartphone with 128GB storage and 6GB RAM."},{"id":"laptop","name":"Laptop","description":"15-inch laptop with 512GB SSD and 16GB RAM."},{"id":"headphones","name":"Headphones","description":"Wireless noise-cancelling over-ear headphones."},{"id":"smartwatch","name":"Smartwatch","description":"Fitness tracking smartwatch with heart rate monitor."},{"id":"tablet","name":"Tablet","description":"10-inch tablet with 64GB storage and stylus support."},{"id":"camera","name":"Camera","description":"Mirrorless camera with 24.2MP sensor and 4K video recording."},{"id":"speaker","name":"Speaker","description":"Bluetooth portable speaker with 12-hour battery life."}]
```
- Detail API:
```shell
curl http://localhost:8070/product/mouse
```

Output:
```json
{"id":"mouse","name":"Mouse","description":"Ergonomic wireless mouse with adjustable DPI."}
```
## Customer Service
Customer Microservice exposing endpoints to get all the registered customers or get a customer detail by ID.

### To run the Customer service:
```shell
# From project root
./gradlew :customer:bootRun
```

### API

- List API
```shell
curl http://localhost:8060/customer
```
Output:

```json
[{"id":1001,"name":"John Doe","address":{"city":"New York","zipCode":"10001"}},{"id":1002,"name":"Jane Smith","address":{"city":"Los Angeles","zipCode":"90001"}},{"id":1003,"name":"Bob Johnson","address":{"city":"Chicago","zipCode":"60601"}},{"id":1004,"name":"Alice Williams","address":{"city":"Houston","zipCode":"77001"}},{"id":1005,"name":"Michael Brown","address":{"city":"Phoenix","zipCode":"85001"}},{"id":1006,"name":"Emily Davis","address":{"city":"Philadelphia","zipCode":"19019"}},{"id":1007,"name":"David Miller","address":{"city":"San Antonio","zipCode":"78201"}}]
```
- Detail API
```shell
curl http://localhost:8060/customer/1002
```

Output:
```json
{"id":1002,"name":"Jane Smith","address":{"city":"Los Angeles","zipCode":"90001"}}
```

## Order Service
Order Microservice exposing endpoints to get all the orders or get an order's detail by ID.

### To run the Order service:
```shell
# From project root
./gradlew :order:bootRun
```

### API

- List API
```shell
curl http://localhost:8050/order
```

Output:
```json
[{"id":1,"couponCode":"SAVE10","customerId":"1001","dateCreated":"2024-08-10T09:53:56.748+00:00","lastUpdated":"2024-08-10T09:53:56.748+00:00","items":[{"id":1,"productId":"smartphone","quantity":1,"price":699.99},{"id":2,"productId":"wirelesscharger","quantity":2,"price":29.99},{"id":3,"productId":"bluetoothbuds","quantity":1,"price":99.99}]},{"id":2,"couponCode":"DISCOUNT20","customerId":"1002","dateCreated":"2024-08-10T09:53:56.752+00:00","lastUpdated":"2024-08-10T09:53:56.752+00:00","items":[{"id":4,"productId":"laptop","quantity":1,"price":1299.99},{"id":5,"productId":"monitor","quantity":1,"price":399.99},{"id":6,"productId":"wirelesskeyboard","quantity":1,"price":49.99}]}]
```

- Detail API
```shell
curl http://localhost:8050/order/1
```

Output:

```json
{"id":1,"couponCode":"SAVE10","customerId":"1001","dateCreated":"2024-08-10T09:53:56.748+00:00","lastUpdated":"2024-08-10T09:53:56.748+00:00","items":[{"id":1,"productId":"smartphone","quantity":1,"price":699.99},{"id":2,"productId":"wirelesscharger","quantity":2,"price":29.99},{"id":3,"productId":"bluetoothbuds","quantity":1,"price":99.99}]}
```

## Aggregate Service
Aggregate Microservice exposes API to provide aggregation result from multiple microservices

### To run the Order service:
```shell
# From project root
./gradlew :aggregate:bootRun
```

### API

- Detail API
```shell
curl http://localhost:8080/aggregate/1
```

Output:

```json
{"id":1,"couponCode":"SAVE10","customer":{"id":1001,"name":"John Doe","address":{"city":"New York","zipCode":"10001"}},"items":[{"id":1,"product":{"id":"smartphone","name":"Smartphone","description":"High-end smartphone with 128GB storage and 6GB RAM."},"quantity":1,"price":699.99},{"id":2,"product":{"id":"wirelesscharger","name":"Wireless Charger","description":"Fast wireless charger compatible with all Qi devices."},"quantity":2,"price":29.99},{"id":3,"product":{"id":"bluetoothbuds","name":"Bluetooth Earbuds","description":"True wireless Bluetooth earbuds with noise isolation."},"quantity":1,"price":99.99}]}
```
