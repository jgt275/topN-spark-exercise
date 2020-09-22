package main.scala.exercise

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.log4j._

object Driver {
  
  val logger = Logger.getLogger("DriverLogger")
  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  
  def main(args: Array[String]) {
		
		val spark = SparkSession.builder.appName("TopN").master("local[*]").getOrCreate()
		
		import spark.implicits._
		
		//Check if all arguments are available
		if (args.length != 2) {
		  logger.error("Requires exactly 2 input arguments - url (String), N (Int)")
		  sys.exit(0)
		}

		// Retrieve filepath of dataset from compressed download
		val filepath = utils.fileDownloader(url=args(0), localpath="/tmp/data")
		println(s"Input data file: $filepath")
	
    logger.info("Reading input file")
    val df = spark.read
                  .option("header","false").option("sep", " ")
                  .csv(filepath)
                  
    //Set shuffle partitions based on input data           
		spark.conf.set("spark.sql.shuffle.partitions", 2*df.rdd.getNumPartitions)
                               
    val df_2 = df.select($"_c0".as("visitor")
                          ,to_date(ltrim(element_at(split($"_c3",":"),1),"["), "dd/MMM/yyyy").as("date") //Keep only date
                          ,element_at(split($"_c5"," "),2).as("url") //Keep only visited URL
                          ,$"_c6".as("response")
                          )                                
		
		println(s"-- Displaying top-${args(1)} frequent visitor by day ranked from most to least and their frequency --")
		val freq_visitor = utils.topNbyKey(df=df_2, n=args(1).toInt, groupingKey="date", groupingVar="visitor")
		freq_visitor.show(false)
		freq_visitor.explain
		
		println(s"-- Displaying top-${args(1)} frequent url by day ranked from most to least and their frequency --")
		var freq_url = utils.topNbyKey(df=df_2, n=args(1).toInt, groupingKey="date", groupingVar="url")
		freq_url.show(false)
    freq_url.explain
		
		logger.info("Closing spark session")
		spark.stop()
	}
  
}