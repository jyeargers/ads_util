# ads_util

## To use:

- In **build.gradle**: 
 add dependency `compile('com.cedexis:ads_util:<version or "+">')`
    
```java
   import com.cedexis.ads_util.YamlConfigRunner;

   ...

   YamlConfigRunner config = null;
   try {
       config = new YamlConfigRunner("config.yaml");
   } catch (Exception exception) {
       LOGGER.error("unable to load config", exception);
       System.exit(1);
   }
```        
   ... 
   
   // See [config.yml](https://github.com/jyeargers/ads_util/blob/master/src/test/resources/config.yml) for source
```java
   String str = config.getString("rootkey");
   List<Integer> list2 = yamlConfigRunner.getList("nested1/level1/array0");
```

## To build:

`./gradlew clean build`

## To deploy to mavenlocal:

`./gradlew publishToMavenLocal`

## To deploy to artifactory:

`./gradlew publish`
