Compute top N most frequent visitors and urls by day from downloaded file

### Instructions
1. Clone the repository
2. Navigate to root directory of cloned repo
3. Build docker file

  `docker build -t scala-exercise:latest .`
4. Run spark application within docker image

  The following command displays the top-5 frequent data

  ```docker run -ti scala-exercise:latest spark-submit /scala_exercise/jars/spark_exercise.jar ftp://ita.ee.lbl.gov/traces/NASA_access_log_Jul95.gz 5```

##### Package: `sbt clean assembly`
##### Test Case Execution: `sbt test`

### Sample Result

```
-- Displaying top-5 frequent visitor by day ranked from most to least and their frequency --
+----------+--------------------+---------+--------------+
|date      |visitor             |frequency|frequency_rank|
+----------+--------------------+---------+--------------+
|1995-07-01|piweba3y.prodigy.com|623      |1             |
|1995-07-01|piweba4y.prodigy.com|547      |2             |
|1995-07-01|alyssa.prodigy.com  |536      |3             |
|1995-07-01|disarray.demon.co.uk|463      |4             |
|1995-07-01|piweba1y.prodigy.com|456      |5             |
|1995-07-02|piweba3y.prodigy.com|960      |1             |
|1995-07-02|alyssa.prodigy.com  |578      |2             |
|1995-07-02|piweba1y.prodigy.com|432      |3             |
|1995-07-02|disarray.demon.co.uk|366      |4             |
|1995-07-02|www-d4.proxy.aol.com|343      |5             |
|1995-07-03|piweba3y.prodigy.com|1067     |1             |
|1995-07-03|134.83.184.18       |413      |2             |
|1995-07-03|alyssa.prodigy.com  |368      |3             |
|1995-07-03|news.ti.com         |340      |4             |
|1995-07-03|www-b6.proxy.aol.com|311      |5             |
|1995-07-04|piweba3y.prodigy.com|1199     |1             |
|1995-07-04|alyssa.prodigy.com  |828      |2             |
|1995-07-04|pcrb.ccrs.emr.ca    |460      |3             |
|1995-07-04|piweba1y.prodigy.com|452      |4             |
|1995-07-04|piweba2y.prodigy.com|451      |5             |
+----------+--------------------+---------+--------------+
only showing top 20 rows

-- Displaying top-5 frequent url by day ranked from most to least and their frequency --
+----------+-----------------------------------------------+---------+--------------+
|date      |url                                            |frequency|frequency_rank|
+----------+-----------------------------------------------+---------+--------------+
|1995-07-01|/images/NASA-logosmall.gif                     |3977     |1             |
|1995-07-01|/images/KSC-logosmall.gif                      |3530     |2             |
|1995-07-01|/shuttle/countdown/count.gif                   |2690     |3             |
|1995-07-01|/shuttle/countdown/                            |2654     |4             |
|1995-07-01|/shuttle/missions/sts-71/sts-71-patch-small.gif|1632     |5             |
|1995-07-02|/images/NASA-logosmall.gif                     |3416     |1             |
|1995-07-02|/images/KSC-logosmall.gif                      |3099     |2             |
|1995-07-02|/shuttle/countdown/count.gif                   |2305     |3             |
|1995-07-02|/shuttle/countdown/                            |2285     |4             |
|1995-07-02|/shuttle/missions/sts-71/sts-71-patch-small.gif|1451     |5             |
|1995-07-03|/images/NASA-logosmall.gif                     |5614     |1             |
|1995-07-03|/images/KSC-logosmall.gif                      |4655     |2             |
|1995-07-03|/shuttle/countdown/count.gif                   |3666     |3             |
|1995-07-03|/shuttle/countdown/                            |3534     |4             |
|1995-07-03|/images/ksclogo-medium.gif                     |2296     |5             |
|1995-07-04|/images/NASA-logosmall.gif                     |3860     |1             |
|1995-07-04|/images/KSC-logosmall.gif                      |3594     |2             |
|1995-07-04|/shuttle/countdown/count.gif                   |2622     |3             |
|1995-07-04|/shuttle/countdown/                            |2586     |4             |
|1995-07-04|/images/MOSAIC-logosmall.gif                   |1547     |5             |
+----------+-----------------------------------------------+---------+--------------+
only showing top 20 rows
```
