package futures

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object ScalaFutureCustomEC extends App {

  val blockingEC = ExecutionContext
    .fromExecutor(FixedThreadPool(10, "blocking-io"))

  val nonBlockingEC = ExecutionContext
    .fromExecutor(FixedThreadPool(10, "non-blocking-io"))

  val myFuture1 = Future {
    sleep(1)
    printCurrentThread("myFuture1")
  }(nonBlockingEC)

  val myFuture2 = myFuture1.map{_=> sleep(1); printCurrentThread("myFuture2"); ()}(blockingEC)
  val myFuture3 = myFuture2.map{_=> sleep(1); printCurrentThread("myFuture3"); ()}(blockingEC)

  Await.result(myFuture3, Duration.Inf)

}
