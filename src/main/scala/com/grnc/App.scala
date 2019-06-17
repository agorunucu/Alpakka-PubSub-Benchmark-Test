package com.grnc

import java.time.LocalDateTime
import java.util.Base64

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.googlecloud.pubsub.scaladsl.GooglePubSub
import akka.stream.alpakka.googlecloud.pubsub.{PubSubConfig, PubSubMessage, PublishRequest}
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object App extends Config{

  implicit private val system:       ActorSystem       = ActorSystem("test-bd")
  implicit private val materializer: ActorMaterializer = ActorMaterializer()

  def main(args: Array[String]) ={

    val topic = "test-dummy-topic-2"
    val config = PubSubConfig(projectId, clientEmail, Helper.privateKeyToString(privateKey))

    // 1M event, 3:30 min:second
    // 1M event, 3:42
    // started  @ 15:29:20
    // finished @ 15:33:02
    Source(1 to 1000000)
      .map(count => PubSubMessage(messageId = count.toString, data = new String(Base64.getEncoder.encode(("PubSub Heavy Test " + count.toString).getBytes))))
      //.groupedWithin(100, 100.milliseconds)
      .map(r => PublishRequest.apply(List(r)))
      .via{
        println(s"Start: ${LocalDateTime.now()}")
        GooglePubSub.publish(topic, config)
      }
      .runWith(Sink.seq)

      .onComplete{
        case Success(_)         =>
          println(s"Finish: ${LocalDateTime.now()}")
          System.exit(0)
        case Failure(exception) =>
          exception.printStackTrace()
          System.exit(1)
      }

  }
}
