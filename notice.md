## 注意事项
* 本地连接
   * 将application-dev.properties对应的value换成自己电脑的配置
   * 将application.properties的value换成dev
   * 将pom.xml中的code1替换成code2

 * 打war包
    * 将application.properties的value换成prod
    * 将pom.xml中的code2替换成code1
    * maven视窗双击package
    

      //code1
      <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <exclusions>
                    <exclusion>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-tomcat</artifactId>
                    </exclusion>
                </exclusions>
      </dependency>
      
      //code2
      <dependency>
    		<groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <!--<exclusions>
           	    <exclusion>
           		   <groupId>org.springframework.boot</groupId>
           		   <artifactId>spring-boot-starter-tomcat</artifactId>
           		</exclusion>
           	</exclusions>-->
       </dependency>
    

 

 

