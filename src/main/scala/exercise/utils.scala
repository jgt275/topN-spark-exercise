package main.scala.exercise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions.Window
import org.apache.log4j.Logger

import sys.process._
import java.net.URL
import java.io.{File, PrintWriter}
import java.io.{FileNotFoundException, IOException}

object utils {

  val logger = Logger.getLogger("UtilsLogger")

 /** Downloads gzipped text file from a URL and extracts if it is compressed.
  *
  * @param url URL path and filename.
  * @param session Existing spark session.
  *
  * @example val filepath=fileDownloader("http://path/file.gz")
  * @return String path value of downloaded file
  * @throws Exception upon error.
  */
  def fileDownloader(url: String, localpath: String): String = {

    // Sample url: server/path/filetoDownload.gz
    val filename = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('.')) //Extract filenmae without extension from url, Example: filetoDownload
    val extension = url.substring(url.lastIndexOf('.') + 1) //Extract file extension from url, Example: gz

    val saveAs = localpath + "/" + filename + "." + extension

    //Create folder if folder does not exist
    val dir: File = new File(localpath)
		if ( !(dir.exists))  {
		  dir.mkdir()
		}

    try {
      logger.info(s"Downloading file from $url")
      // Downloading file from url to local directory
      new URL(url) #> new File(saveAs) !!
    }
    catch {
      case fe: FileNotFoundException => println("Missing file exception")
      case ex: Exception => ex.printStackTrace()
    }

    if (extension == "gz") {
      logger.info("Source file is of type GZip, Extracting contents")
      s"gzip -d -k -f $saveAs".!
      return(localpath + "/" + filename)
      }
    else {
      return(saveAs)
      }
  }


 /** Computes the top N algorithm of a field grouped by a key.
  *
  * @param df Dataframe.
  * @param n User provided argument for top-N.
  * @param groupingKey Key field to aggregate by.
  * @param groupingVar Variable field to compute frequency on.
  *
  * @return Dataframe with key, frequency and top frequent values
  * @throws Exception upon error.
  */
  def topNbyKey(df: DataFrame, n: Int, groupingKey: String, groupingVar: String): DataFrame = {

      val groupedDF = df.groupBy(col(groupingKey), col(groupingVar))
                        .agg(count(col(groupingVar)).alias("frequency"))
                        .na.drop

      val window = Window.partitionBy(groupingKey).orderBy(desc("frequency"))

      return (groupedDF.withColumn("frequency_rank", row_number().over(window))
                       .where(col("frequency_rank")<= n)
                       .orderBy(col(groupingKey), col("frequency_rank")))
  }
}
