# ads_util

## To use:

- In **build.gradle**: `compile('com.cedexis:ads_util:<version or "+">')`
    
   import com.cedexis.ads_util.YamlConfigRunner;

   ...

   YamlConfigRunner config = null;
   try {
       config = new YamlConfigRunner("config.yaml");
   } catch (Exception exception) {
       LOGGER.error("unable to load config", exception);
       System.exit(1);
   } 
        
   ... 
   
   // See 'config.yml' for source
   String str = config.getString("rootkey");
   List<Integer> list2 = yamlConfigRunner.getList("nested1/level1/array0");


## To build:

`./gradlew clean build`

## To deploy to mavenlocal:

`./gradlew publishToMavenLocal`

## To deploy to artifactory:

`./gradlew publish`
