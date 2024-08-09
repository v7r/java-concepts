Which of statements are true for Java Locks ?

A. The tryLock() method returns a boolean indicator immediately regardless if it has or has not managed to acquire the lock

B. The tryLock () method returns a boolean indicator immediately if it has managed to acquire the lock, otherwise it waits for the lock acquisition.

C. The lock () method returns a boolean indicator immediately if it has managed to acquire the lock,
otherwise it waits for the lock acquisition.

D. The Lock () method returns a boolean indicator immediately regardless if it has or has not
managed to acquire the lock

Answer: A
Refer: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html

B - false
c - false. lock does not return boolean.
d - false. lock does not return boolean.

---


