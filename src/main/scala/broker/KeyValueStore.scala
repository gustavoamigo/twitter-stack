package broker

import com.twitter.concurrent.{Broker, Offer}
import com.twitter.util.{Await, Future}

class KeyValueStore[K,V] {
  private val getterReq = new Broker[K]
  private val getterRes = new Broker[Option[V]]
  private val setterReq = new Broker[(K, V)]

  private def loop(kv: Map[K, V]): Unit = {
    Offer.choose(
      getterReq.recv.map { k => getterRes.send(kv.get(k)).sync().map(_ => loop(kv)) },
      setterReq.recv.map { case (k,v) => loop(kv + ((k,v))) }
    ).sync()
  }

  loop(Map.empty)

  def get(k: K): Future[Option[V]] = {
    for {
      _ <- getterReq ! k
      v <- getterRes ?
    } yield {
      v
    }
  }

  def set(k: K, v: V): Future[Unit] = {
    setterReq ! (k,v)
  }
}

object KeyValueStoreTest extends App {
  val kvStore = new KeyValueStore[String, String]()
  val f = for {
    _ <- kvStore.set("a", "a_1")
    _ <- kvStore.get("a").map(println)
    _ <- kvStore.get("b").map(println)
    _ <- kvStore.set("a", "a_2")
    _ <- kvStore.get("a").map(println)
    _ <- kvStore.set("b", "b_2")
    _ <- kvStore.get("b").map(println)
  } yield ()

  Await.result(f)
}
