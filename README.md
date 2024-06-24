
#### 打包

	mvn clean package dockerfile:build
	
#### 部署

	docker-compose -f docker/docker-compose.yml up -d
	
#### 測試 book-service

	http://localhost:8080/v1/author/author-id-1/book/book-id-1

#### 測試 Eureka

	http://localhost:8070/

#### 測試 gatewayserver 定義的路由

	http://localhost:8072/actuator/gateway/routes