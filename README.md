### Some good interview questions reference sites
#### 1. https://www.toptal.com/java/interview-questions

### Java Interview Questions
1. What are different types of exceptions? Give examples. Name some catchable exceptions.
2. What are the data structures in java (list/map/set collections)

   Collection -> List and Set

   List -> ArrayList, LinkedList, Vector, Stack

   Set -> HashSet, LinkedHashSet, TreeSet, EnumSet

   Map -> HashMap, TreeMap, ConcurrentMap, Hashtable, LinkedHashMap, Properties, WeakHashMap, Attributes
   
4. What is the difference between stack and queue?
5. What are different types of Map data structure?
6. Do we still use HashTables in Java? If so, why?

   There are several differences between HashMap and Hashtable in Java:

   Hashtable is synchronized, whereas HashMap is not. This makes HashMap better for non-threaded applications, as unsynchronized Objects typically perform better       than synchronized ones.

   Hashtable does not allow null keys or values. HashMap allows one null key and any number of null values.

   One of HashMap's subclasses is LinkedHashMap, so in the event that you'd want predictable iteration order (which is insertion order by default), you could easily swap out the HashMap for a LinkedHashMap. This wouldn't be as easy if you were using Hashtable.

   Since synchronization is not an issue for you, I'd recommend HashMap. If synchronization becomes an issue, you may also look at ConcurrentHashMap.

7. Do you know about TreeMap? What is the difference between HashMap and TreeMap? If I want to get an element in TreeMap, will I be comparing with each key in the map?
8. Do you know algorithm complexity (Big-0) in retrieving elements from HashMap and TreeMap?
9. What happens when springboot is starting up and encounters annotations (Dependency
Iniection/Autowiring)?
10. What is there is same class and bean name used for auto-wire in two different services? (Use @Qualifier, @Primary)
11. What CICD tool have you used? Have you used Concourse or anything other than Jenkins? What were the exact tasks performed in the CICD tool that you have used?
12. Debug performance issues in application. What kind of tools are used to identify the cause of slowdown?
13. Can you explain the difference between service and component?
14. Have you worked with Integration Flows (Kafka)?
15. Have you worked on API documentation? (Swagger)
16. What version of Java have you used?
17. Any other language used in previous projects?
18. What kind of databases have you worked with. Any experience with Cassandra/bigdata/NoSql?
19. Any experience with AWS? Anything specific to lambda, SQS, Kinesis?
20. Have vou worked with terraform?
21. Any experience with container-based platforms?
22. How to convert XML to JSON? What issues can occur while conversion? IS there anything in XML that cannot be converted to JSON?


## TODO
1. Learn Java concurrency classes in Java 8 https://docs.oracle.com/javase/tutorial/essential/concurrency/

