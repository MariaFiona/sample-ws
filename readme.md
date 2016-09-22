## Instructions
1. Start hsqldb in-memory db, e.g.

  ```
  java -cp hsqldb-2.3.2.jar org.hsqldb.Server -database.0 mem:sample-ws  -dbname.0 sample-ws
  ```

2. Run sample-ws.jar 

  ```
  java -jar sample.ws.jar
  ```

3. Run acceptance tests