package futures

import com.twitter.util.{Await, FuturePool}

object TwitterFuturePool extends App {
  val executor = FixedThreadPool(10, "blocking-io")
  val pool = FuturePool(executor)

  val myFuture1 = pool {
    sleep(1)
    printCurrentThread("myFuture1")
  }

  val myFuture2 = myFuture1.map{_=> sleep(1); printCurrentThread("myFuture2"); ()}
  val myFuture3 = myFuture2.map{_=> sleep(1); printCurrentThread("myFuture3"); ()}

  Await.result(myFuture3)
}
