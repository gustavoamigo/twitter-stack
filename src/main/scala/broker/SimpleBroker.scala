package broker

import com.twitter.concurrent.Broker
import com.twitter.util.Await

object SimpleBroker extends App {
  val b = new Broker[Int]
  def loop(): Unit = b.recv.sync().onSuccess{i => println(i); loop()}
  loop()
  val f = for {
    () <- b ! 1
    () <- b ! 2
  } yield ()
  Await.result(f)
}
