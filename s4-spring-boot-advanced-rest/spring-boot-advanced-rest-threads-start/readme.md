# Sequenziell  

Starten der Anwendung und folgende Messung durchführen. 

```
./ab.exe -n 3 http://localhost:8080/httpbin/block/3
```

Es kommen ca. 9 Sekunden raus 


# Embedded Tomcat 

Thread Pool auf 10 beschränken, sonst würde Tomcat das Ergebnis verfälschen. 

```
server:
  tomcat:
    threads:
      max: 10
```

# Parallel max. Threads    

Starten der Anwendung und folgende Messung durchführen. 

```
./ab.exe -n 10 -c 10 http://localhost:8080/httpbin/block/3
```

Es kommen ca. 3 Sekunden raus, siehe **Processing Time**. 



# Parallel 2 x max Threads   

Starten der Anwendung und folgende Messung durchführen. 

./ab.exe -n 10 -c 10 http://localhost:8080/httpbin/block/3
```

Es kommen ca. 6 Sekunden raus, siehe **Processing Time**. 

Das kommt daher, dass nur 10 Threads zur Verfügung stehen. 


# Virtuell Threads 

  ```
  
  spring:
  threads:
    virtual:
      enabled: true
  ```
  
Jetzt können mehr als 10 Threads erzeugt werden. 
    
  
```
./ab.exe -n 10 -c 10 http://localhost:8080/httpbin/block/3
```
   





