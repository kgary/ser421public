# ReachAPI

Instructions to run the API. For now the server is already deployed on a linux server provided by the sponsor.

In order to compile, build and deploy the API, you would need to have [Apache Ant](http://ant.apache.org) build tool installed on your system.

Once you've installed Ant on your system and can run the `ant` command from your command line, navigate to directory where this repository is cloned.

Make sure you update the `<property name="tomcat_webapps">` property in the **build.xml** file to have the tomcat webapps directory of the system you're deploying the API on.

Once you have the `tomcat_webapps` value properly set. Go to the command line and run `ant`.

This should invoke the **build.xml** which has a default `deploy` task which will recursively run all tasks it is dependent on.

Once this process has finished, you should find the WAR file of the API deployed in `tomcat_webapps`.

The recommended context name for the API is ReachAPI. Once the context is completely deployed on a server. You can access endpoints of the API using
`http://{server_address}}/ReachAPI/rest/{endpoint_name}`
