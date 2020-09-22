package main.scala.exercise

import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import org.apache.log4j._

class UtilitiesTest extends FunSuite with BeforeAndAfterEach {
  
  private val master = "local[*]"
  private val appName = "ScalaExerciseTest"

  var spark : SparkSession = _

  override def beforeEach(): Unit = {
     //Initiate spark session
     spark = new SparkSession.Builder().appName(appName).master(master).getOrCreate()
     
     val logger = Logger.getLogger("TestCaseLogger")
     Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
     
  }
  
  test("Downloading file from a URL") {
    val downloadURL = "ftp://ita.ee.lbl.gov/traces/NASA_access_log_Aug95.gz"
    
    val path = utils.fileDownloader(url=downloadURL, localpath="/tmp/data")
    assert(new java.io.File(path).exists)
    
  }
  
  test("Computing top-N frequent by key") {
    val sparkSession = spark
    import sparkSession.implicits._
    
    val df = Seq(("OH","Cleveland"), ("OH","Cleveland"), ("OH","Columbus"), ("OH","Cleveland"), ("OH","Columbus"), ("NC","Charlotte"), ("NC","Charlotte"), ("NC","Raleigh"), ("NC","Raleigh"), ("OH","Cincinnati")).toDF("state", "city")
    val n = 2
    
    val result = utils.topNbyKey(df=df, n=n, groupingKey="state", groupingVar="city")   
    // Retrieve max value from top N ranking
    val result_max_N = result.agg(max("frequency_rank")).head().getInt(0)
    
    assert(result_max_N == n)
  }
  
  override def afterEach(): Unit = {
     spark.stop()
 }
}