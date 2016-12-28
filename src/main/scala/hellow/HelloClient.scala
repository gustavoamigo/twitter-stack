package hellow

import com.twitter.finagle.Thrift
import com.twitter.util.Await
import gustavoamigo.twitter.stack.thriftscala
import gustavoamigo.twitter.stack.thriftscala.Hello

/**
  * Created by root on 12/27/16.
  */
object HelloClient {
  def main(args: Array[String]) {

    val client = Thrift.client.newIface[Hello.FutureIface]("localhost:8080")
    val f = client.hi()
    f.onSuccess { response =>
      println("Received response: " + response)
    }
    f.onFailure( t =>
      println("Failed: " + t.fillInStackTrace())
    )
    Await.result(f)
    println("finished")
  }

}
