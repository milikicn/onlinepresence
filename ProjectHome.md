# Introduction #

This is the Online Presence Ontology (OPO) code repository intended for storing tools we develop for generating and manipulating [OPO](http://online-presence.net) data.

## jOPO ##

In order to start generating OPO data from Java, you can use our jOPO library. jOPO is a set of POJO classes reflecting classes from the OPO ontology and annotated with [Jenabean](http://code.google.com/p/jenabean/) annotations. Jenabean allows us to work with plain POJO classes which are serialized to and deserialized from RDF.

### Using jOPO ###

In order to use jOPO, you can download a jar file from the [Downloads](Downloads.md) page. Or, if your application is using Maven, you can add following code snippets to your pom file:

```

<dependencies>
...
<dependency>
<groupId>net.onlinepresence

Unknown end tag for &lt;/groupId&gt;


<artifactId>jopo

Unknown end tag for &lt;/artifactId&gt;


<version>1.2.1

Unknown end tag for &lt;/version&gt;




Unknown end tag for &lt;/dependency&gt;


...


Unknown end tag for &lt;/dependencies&gt;



<repositories>
...
<repository>
<id>OPO Code Repository

Unknown end tag for &lt;/id&gt;


<url>http://onlinepresence.googlecode.com/svn/repo

Unknown end tag for &lt;/url&gt;




Unknown end tag for &lt;/repository&gt;


...


Unknown end tag for &lt;/repositories&gt;


```