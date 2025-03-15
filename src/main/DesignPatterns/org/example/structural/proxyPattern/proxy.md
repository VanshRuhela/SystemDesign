## PROXY Design Pattern

### Uses
1. Client Request - Proxy - Actual DB/Server ( here the proxy can filter/block requests)
2. Caching - Proxy will check if it has been previously called.
3. Pre & post processing : if we want to pre process a request i.e do some tasks and then pass the object ahead - and then again do some tasks (post process)
4. when we use a bean we use a proxy - springboot uses a proxy for the bean
