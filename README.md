# shoppingMall
- Build tool : Gradle 7.6.1, Groovy
- Front office : 未実装
- Back office : Java, Spring boot 2.7.3, Spring Security with JWT, Thymeleaf
- API : Java, Spring boot 2.7.3, Spring Security with JWT, JPA, QueryDSL
- DB: H2

- HTML template : [SB Admin 2](https://startbootstrap.com/theme/sb-admin-2)

# Project Structure
~~~ shell
$ gradle -q projects

Root project 'project-shopping-mall'
+--- Project ':applications' - null (net.toyproject.mall:applications:0.0.1-SNAPSHOT)
|    +--- Project ':applications:api-server' - null (net.toyproject.mall:api-server:0.0.1-SNAPSHOT)
|    +--- Project ':applications:backoffice-server' - null (net.toyproject.mall:backoffice-server:0.0.1-SNAPSHOT)
|    \--- Project ':applications:frontoffice-server' - null (net.toyproject.mall:frontoffice-server:0.0.1-SNAPSHOT)
+--- Project ':services' - null (net.toyproject.mall:services:0.0.1-SNAPSHOT)
|    +--- Project ':services:member' - null (net.toyproject.mall:member:0.0.1-SNAPSHOT)
|    \--- Project ':services:member-api' - null (net.toyproject.mall:member-api:0.0.1-SNAPSHOT)
\--- Project ':shared' - null (net.toyproject.mall:shared:0.0.1-SNAPSHOT)
     +--- Project ':shared:base' - null (net.toyproject.mall:base:0.0.1-SNAPSHOT)
     +--- Project ':shared:base-api' - null (net.toyproject.mall:base-api:0.0.1-SNAPSHOT)
     \--- Project ':shared:common' - null (net.toyproject.mall:common:0.0.1-SNAPSHOT)
~~~
