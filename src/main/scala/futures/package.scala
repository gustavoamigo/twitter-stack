/**
  * Created by root on 1/2/17.
  */
package object futures {
  def printCurrentThread(txt: String) = println(s"Running $txt at ${Thread.currentThread.getName}")
  def sleep(second:Long) = Thread.sleep(second * 1000)
}
