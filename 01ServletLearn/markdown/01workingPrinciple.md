# springboot
在tomcat中 包含了servlet api
``` maven
    <dependency>
      <groupId>org.apache.tomcat.embed</groupId>
      <artifactId>tomcat-embed-core</artifactId>
      <version>10.1.7</version>
      <scope>compile</scope>
      <exclusions>
        <exclusion>
          <artifactId>tomcat-annotations-api</artifactId>
          <groupId>org.apache.tomcat</groupId>
        </exclusion>
      </exclusions>
    </dependency>
```