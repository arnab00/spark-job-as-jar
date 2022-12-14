package it.biswas.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.api.java.function.FilterFunction;

public class SparkJobAsJarApplication {

	public static void main(String[] args) {
		System.out.println("Ok");

		String logFile = "C:/spark/spark-3.3.0-bin-hadoop3/README.md"; // Should be some file on your system
		SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
		Dataset<String> logData = spark.read().textFile(logFile).cache();

		long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
		long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();

		System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

		spark.stop();

	}

}
