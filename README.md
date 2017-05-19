# simple rpc famework based on java
+ basic skills
    - spring fameworkrelated
    - netty 
    - zk(docker zk recommended)
    - kryo
    
    
## key feature

## who can benefit from this project?
  yes,there are lots of rpc famewroks been used in producting env.This project is facing for those who want to learn how 
  an rpc famework works and really wanna build himself rpc famework.
  
  This rpc famework is short but contains every necessary part that you may want to know.
    
## how to start up (try) this distributed rpc famework

### env ready
base on **macos**,you can follow those tips.

+ zk
  - zk version >= 3.5.1
  - you can use docker to install zk env
  ```
  1 docker pull containersol/zookeeper
  2 docker run --name my_zookeeper -p 2181:2181  containersol/zookeeper
  ```
  you can use `brew` to install `zookeeper` (brew install zookeeper) and use [docker-toolbox](https://www.docker.com/products/docker-toolbox) 
  to install docker.
  - zkCli. Use zkCli to test wheather the zk env has been start up.
  ```
    zkCli -server 127.0.0.1:2181
  ```
  
### start up the rpc test demo

0 start the zookeeper in localhost
1 install the rpc-core and simplerpc-spring in local maven resporitory 
```
  cd core
   mvn clean install -Dmaven.test.skip=true 
  
  cd spring 
   mvn clean install -Dmaven.test.skip=true 
   
  
```
2 strat up the `SpringServerConfig` in `testdemo` package,then start up the `SpringClientConfig`

3 open the url:[localhost:8081/test/hello?say=123](localhost:8081/test/hello?say=123) in your browser ,enjoy 


  


