package futures

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object ScalaFutureDefaultEC extends App {

  val myFuture1 = Future {
    sleep(1)
    printCurrentThread("myFuture1")
  }

  val myFuture2 = myFuture1.map{_=> sleep(1); printCurrentThread("myFuture2"); ()}
  val myFuture3 = myFuture2.map{_=> sleep(1); printCurrentThread("myFuture3"); ()}
  val myFuture4 = Future {
    sleep(1)
    printCurrentThread("myFuture4")
  }

  Await.result(myFuture3, Duration.Inf)
  Await.result(myFuture4, Duration.Inf)
}
