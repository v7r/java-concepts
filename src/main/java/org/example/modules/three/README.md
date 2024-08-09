
````
abstract sealed interface SInt permits Story, Art {
    default String getTitle() { 
        return "Book Title" ;
    }
｝

Which set of class definitions compiles?
A. interface Story extends STnt {}
   interface Art extends SInt {}
B. public interface Story extends SInt {}
   public interface Art extends SInt {}
C. sealed interface Storty extends sInt {} 
   non-sealed class Art implements SInt {}
D. non-sealed interface Story extends SInt {}
   class Art implements SInt {}
E. non-sealed interface Story extends SInt {}
   non-sealed interface Art extends SInt {}
   
````
Answer: E

Refer the example in this package.
