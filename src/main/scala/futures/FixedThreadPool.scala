package futures

import java.util.concurrent.{SynchronousQueue, ThreadPoolExecutor, TimeUnit}

import com.twitter.concurrent.NamedPoolThreadFactory

object FixedThreadPool {
  def apply(nThreads: Int, name: String, daemon: Boolean = true): ThreadPoolExecutor =
    new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS,new SynchronousQueue[Runnable],new NamedPoolThreadFactory(name, daemon))

}
