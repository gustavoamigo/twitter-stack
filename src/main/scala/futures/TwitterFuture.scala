package futures

import com.twitter.util.{Await, Future}

object TwitterFuture extends App {

  val myFuture1 = Future {
    sleep(1)
    printCurrentThread("myFuture1")
  }

  val myFuture2 = myFuture1.map{_=> sleep(1); printCurrentThread("myFuture2"); ()}
  val myFuture3 = myFuture2.map{_=> sleep(1); printCurrentThread("myFuture3"); ()}

  Await.result(myFuture3)
}
