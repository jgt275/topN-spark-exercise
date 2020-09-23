Compute top N most frequent visitors and urls by day from downloaded file

### Instructions
1. Clone the repository
2. Navigate to root directory of cloned repo
3. Build docker file

  ```docker build -t scala-exercise:latest .```

4. Run spark application within docker image

  The following command displays the top-5 frequent data

  ```docker run -ti scala-exercise:latest spark-submit /scala_exercise/target/scala-2.11/spark_exercise.jar ftp://ita.ee.lbl.gov/traces/NASA_access_log_Jul95.gz 5```

**Package**: ```sbt clean assembly```
**Test Case Execution**: ```sbt test```

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
...
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
...
+----------+-----------------------------------------------+---------+--------------+
only showing top 20 rows
```

Do the following step to compile the spark application while building the docker image\
  &nbsp;&nbsp;&nbsp;1. Add '#' to beginning of line 39 to comment it\
  &nbsp;&nbsp;&nbsp;2. Remove '#' from beginning of line 42 to uncomment it\
  &nbsp;&nbsp;&nbsp;3. Perform steps 3 and 4 from instructions section
