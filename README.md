cdi-sessionscoped-login
=======================

Usage of a @SessionScoped login with TomEE and JPA.

I didn't found a working example with plain CDI-Beans, maybe i am totally wrong too,
but i think for the community and JavaEE/CDI-starters LIKE ME(!!!) its a helpful source.

Thanks to the internet and a lot of stuff i read in the last weeks.

Hint: i used TomEE 1.5.2-SNAPSHOT, just to have the latest "bugfixed" version.

EDIT: (2013-02-19)
After working in another project at work, i found a nice way to have the following
components to work smoothly:

maven, maven-surefire-plugin, maven-failsafe-plugin, jacoco and sonar

I made some unit-tests and some integration-tests, all in the SAME project and separated
by java-packages, makes it easier to create tests (e.g. if you have components that are
shared by that tests), just look at the pom.xml :)

Features in this project:
* CDI only beans
* integration-test and unit-test
* maven
* cdi-producer
* cdi-interceptors
* transaction (bmt via annotation)