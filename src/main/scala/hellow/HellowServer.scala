package hellow

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}
import gustavoamigo.twitter.stack.thriftscala.Hello

object HellowServer {
  def main(args: Array[String]) {
    val server = Thrift.server.serveIface("localhost:8080", new Hello[Future] {
      def hi() = Future.value("hi")
    })
    Await.ready(server)
  }
}
