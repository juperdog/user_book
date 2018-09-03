# book-order-service

Setup env :
- Mysql :
    docker run -p 3306:3306 --name book-order-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql:5.7.23
- Generate KeyStore :
    keytool -genkeypair -alias tomcat -keyalg RSA -keysize 2048 -keystore keystore.jks -validity 3650




Run :
mvn spring-boot:run

https://localhost:8443/users


Note:
Enable Https :
https://drissamri.be/blog/java/enable-https-in-spring-boot/
https://www.thomasvitale.com/https-spring-boot-ssl-certificate/