# Product Category

Microservice to manage hierarchical product categories using Micronaut framework and micronaut-data lib with flyway.

## To run the category service:
```shell
# From project root
./gradlew :category:run
```

## API

### Get all root categories
```shell
curl http://localhost:8086/category
```

Output:
```json
[{"id":"electronics","name":"Electronics","subCategories":[{"id":"computers","name":"Computers"},{"id":"smartphones","name":"Smartphones"},{"id":"accessories","name":"Accessories"}]}]
```
### Get details of a category by ID:
```shell
curl http://localhost:8086/category/electronics
```

Output:
```json
{"id":"electronics","name":"Electronics","subCategories":[{"id":"computers","name":"Computers"},{"id":"smartphones","name":"Smartphones"},{"id":"accessories","name":"Accessories"}]}
```

### Get Ancestors of a category by ID:
```shell
curl http://localhost:8086/category/wiredMouse/ancestors
```

Output:
```json
[{"id":"mouse","name":"Mouse"},{"id":"accessories","name":"Accessories"},{"id":"electronics","name":"Electronics"}]
```

### Get Children of a category by ID:
```shell
curl http://localhost:8086/category/electronics/children
```

Output:
```json
[{"id":"computers","name":"Computers"},{"id":"smartphones","name":"Smartphones"},{"id":"accessories","name":"Accessories"}]
```


