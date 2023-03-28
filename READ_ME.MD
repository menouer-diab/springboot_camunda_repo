Trouble with H2 Database when creating a new DB

TODO:

1. Open the following file in home directory:
   - .h2.server.properties
   - add following lines:
   <nummer>(e.g. 20))=Generic H2 (Embedded)|org.h2.Driver|jdbc\:h2\:~/camunda|camunda
   <nummer + 1>=Generic H2 (Server)|org.h2.Driver|jdbc\:h2\:tcp\://localhost/~/camunda|camunda
2. Create an empty file using the DB-name:
    camunda.mv.db
3. Restart H2 Console and that's it!!

#########################################

Camunda:

- About BusinessKey: "Best Practice"
  https://camunda.com/blog/2018/10/business-key/

How does a Business Key Compare to Process Variables?
  Instead of using the business key, we can also link process instances to domain objects via
  process variables.
  The business key is special for two reasons:

Performance: Querying by business key is often more efficient than querying by a variable value.
For example, it does not require a join with the variables table.
Visibility: The business key is displayed prominently in applications like Camunda Cockpit.

###########################################

Issue with Java 17 ---> Ibatis has issues with Version >= 16

Issue solved using Java 15 for running the application