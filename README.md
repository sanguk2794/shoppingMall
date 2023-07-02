# shoppingMall
- Build tool : Gradle 7.6.1, Groovy
- Front office : 未実装
- Back office : 実装中 → Java, Spring boot 2.7.3, Spring Security with JWT, Thymeleaf
- API : 実装中 → Java, Spring boot 2.7.3, Spring Security with JWT, JPA, QueryDSL
- DB: H2

- HTML template : [SB Admin 2](https://startbootstrap.com/theme/sb-admin-2)

# Project Structure
~~~ shell
$ gradle -q projects

Root project 'project-shopping-mall'
+--- Project ':applications' - net.toyproject.mall:applications:0.0.1-SNAPSHOT
|    +--- Project ':applications:api-server' - net.toyproject.mall:api-server:0.0.1-SNAPSHOT
|    +--- Project ':applications:backoffice-server' - net.toyproject.mall:backoffice-server:0.0.1-SNAPSHOT
|    \--- Project ':applications:frontoffice-server' - net.toyproject.mall:frontoffice-server:0.0.1-SNAPSHOT
+--- Project ':services' - net.toyproject.mall:services:0.0.1-SNAPSHOT
|    +--- Project ':services:co' - net.toyproject.mall:co:0.0.1-SNAPSHOT
|    +--- Project ':services:co-api' - net.toyproject.mall:co-api:0.0.1-SNAPSHOT
|    +--- Project ':services:member' - net.toyproject.mall:member:0.0.1-SNAPSHOT
|    +--- Project ':services:member-api' - net.toyproject.mall:member-api:0.0.1-SNAPSHOT
|    +--- Project ':services:prod' - net.toyproject.mall:prod:0.0.1-SNAPSHOT
|    \--- Project ':services:prod-api' - net.toyproject.mall:prod-api:0.0.1-SNAPSHOT
\--- Project ':shared' - net.toyproject.mall:shared:0.0.1-SNAPSHOT
     +--- Project ':shared:base' - net.toyproject.mall:base:0.0.1-SNAPSHOT
     +--- Project ':shared:base-api' - net.toyproject.mall:base-api:0.0.1-SNAPSHOT
     \--- Project ':shared:common' - net.toyproject.mall:common:0.0.1-SNAPSHOT
~~~
