Project requirements:
- Java 8+ 
- Idea to work in (Intelij in my case)
- PostgreSQL server connected do server given below
- Application server with configurations already prepared to deploy war file (I used https://wildfly.org/downloads/ standalone module) 


In the project files:

src/main/resources/hibernate.cfg.xml there is username and password for postgerSQL server needed:

```
<property name="connection.username">***TYPE YOUR USERNAME***</property>
<property name="connection.password">***TYPE YOUR PASSWORD***</property>
```

In pom.xml

In line 115 if doucment:


```
<outputDirectory>E:/wildfly-16.0.0.Final/standalone/deployments</outputDirectory>
```


War file for application server will be deployed in given directory. It needs to be changed to servers directory, that will deploy war.

To deploy war file use maven build check: clean, install and run the application.

To access application go to address: http://localhost:8080/LestStart-1.0-SNAPSHOT/StudentUIServlet
