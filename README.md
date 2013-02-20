cdi-sessionscoped-login
=======================

This example should demonstrate the usage of a login with @SessionScoped-CDI-bean on TomEE.

I didn't found a working example with plain CDI-Beans, maybe i am totally wrong too,
but i think for the community and JavaEE/CDI-starters LIKE ME(!!!) its a helpful source.

After working in another project at work, i found a nice way to have the following
components to work smoothly:

maven, maven-surefire-plugin, maven-failsafe-plugin, jacoco and sonar

I made some unit-tests and some integration-tests, all in the SAME project and separated
by java-packages, makes it easier to create tests (e.g. if you have components that are
shared by that tests), just look at the pom.xml :)

Features in this project:
* maven
* CDI only beans
  * cdi-producer
  * cdi-interceptors
  * transaction (bmt via annotation)
* testing framework: TestNG
* JSF 2.0
* JPA / JTA
* integration-test and unit-test in the same project
* jacoco configured for unit-test
* jacoco configured for integration-test
* @Inject only (not @EJB !)
