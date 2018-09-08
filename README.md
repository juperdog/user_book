# book-order-service

Setup env :
- Mysql :
    docker run -p 3306:3306 --name book-order-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql:5.7.23
- Generate KeyStore :
    keytool -genkeypair -alias tomcat -keyalg RSA -keysize 2048 -keystore keystore.jks -validity 3650

Run Application :
mvn clean spring-boot:run

Note:
Enable Https :
https://drissamri.be/blog/java/enable-https-in-spring-boot/
https://www.thomasvitale.com/https-spring-boot-ssl-certificate/


Manual :
1. Login to get $accessToken
curl -i -kX POST https://localhost:8443/login \
  -d '{
 "username" : "user",
 "password" : "password"
}'

2. Get User
curl -X GET \
  https://localhost:8443/users \
  -H 'authorization: Bearer $accessToken'


3. Get Books
curl -X GET https://localhost:8443/books

4. Create new user
curl -X POST \
  https://localhost:8443/users \
  -H 'content-type: application/json' \
  -d '{
	"username" : "user",
	"password" : "password",
	"date_of_birth" : "10/07/1987"
}'

5. Post order
curl -X POST \
  https://localhost:8443/users/orders \
  -H 'authorization: Bearer $accessToken' \
  -H 'content-type: application/json' \
  -d '{
	"orders" : [8]
}'

6. Delete User
curl -X DELETE \
  https://localhost:8443/users \
  -H 'authorization: Bearer $accessToken' \