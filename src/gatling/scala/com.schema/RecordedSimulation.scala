package com.schema

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.util.Random

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("PostmanRuntime/7.3.0")

  val headers_0 = Map(
    "Content-Type" -> "application/json",
    "Postman-Token" -> "27b43b1f-3250-4a35-ac8e-1bf39d4e5fc6",
    "cache-control" -> "no-cache")

  val headers_1 = Map(
    "Postman-Token" -> "47a6ed4f-0e31-42e7-a109-f0e0e011910c",
    "cache-control" -> "no-cache")

  val uri1 = "http://localhost:8080/product"

  val feeder = Iterator.continually(Map(
    "productId" -> Random.alphanumeric.take(20).mkString,
    "name" -> Random.alphanumeric.take(20).mkString,
    "category" -> Random.alphanumeric.take(20).mkString,
    "brand" -> Random.alphanumeric.take(20).mkString,
    "description" -> Random.alphanumeric.take(20).mkString,
    "manufacturer" -> Random.alphanumeric.take(20).mkString
  ))

  object SerializeProduct {

    val serializeProduct =

      exec(http("CreateSchemaOrgProduct")
        .post("/product")
        .headers(headers_0)
        .body(
          StringBody(
            """
				             {
				                 "productId": "${productId}",
				                 "name": "${name}",
				                 "category": "${category}",
				                 "brand": "${brand}",
				                 "description": "${description}",
				                 "manufacturer": "${manufacturer}"
				             }
				          """)).asJson
      )
        .pause(1)
        .exec(http("DeserializeProduct")
          .get("/product/${productId}")
          .headers(headers_1))
  }

  val scn = scenario("RecordedSimulation").feed(feeder).exec(SerializeProduct.serializeProduct)


  setUp(scn.inject(rampUsers(5000) during (10 seconds))).protocols(httpProtocol)
}
