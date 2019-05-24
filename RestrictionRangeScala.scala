package com.demo.scala

/**
 * @author cloudera
 */
object RestrictionRangeScala {
  def main(args:Array[String]):Unit = {
    println("Hello")
    
        if(args != null && args.length%2==0) {
    val lowerBounds =new Array[Int]((args.length/2))
   val upperBounds = new Array[Int]((args.length/2))
    var  j :Int = 0; var k :Int=0 ; var i:Int=0
    for (i<-0 to (args.length-1)) {
      if (i % 2 == 0) {
         lowerBounds(j) = Integer.parseInt(args(i))
        j+=1;
         
         }
      else{
      upperBounds(k) = Integer.parseInt(args(i))
      k+=1
      }

    }
     println(" lowerBounds size " + lowerBounds.length);
     println(" upperBounds size " + upperBounds.length);
   // ConcurrentHashMap<Integer, Integer> optimizeRange = optimiseRange(lowerBounds, upperBounds);
     var optimizeRange = scala.collection.mutable.Map[Int, Int]()
   optimizeRange=  optimiseRange(lowerBounds,upperBounds):scala.collection.mutable.Map[Int,  Int]
    //printResult(optimizeRange);
        }
        else  printf("Error with input ........, "+args);
  }
  
  def optimiseRange(lowerBound:Array[Int],upperBound:Array[Int]):scala.collection.mutable.Map[Int,  Int]={
   var i:Int = lowerBound.length - 1;
    var j:Int = upperBound.length - 1;
    var intervalsModified = scala.collection.mutable.Map[Int, Int]();
    while (i >= 0 && j >= 0) {
      if  (lowerBound(i) <= upperBound(j)) {
        if (intervalsModified.size != 0) {
          val keySet= intervalsModified.keySet;
          val itr = keySet.iterator;
          // [94133,94133] [94200,94299] [94226,94399]
          while (itr.hasNext) {
            var lowerValue:Int = itr.next();
            var upperValue:Int = intervalsModified.getOrElse(lowerValue,0);
            if ((lowerBound(i) <= lowerValue)){
              intervalsModified.remove(lowerValue);
              intervalsModified.put(lowerBound(i), upperValue: Int);
            } else
              intervalsModified.put(lowerBound(i), upperBound(j));
          }
        } else
          intervalsModified.put(lowerBound(i), upperBound(j));
      }
      i-=1
      j-=1
    }

    return intervalsModified;

  }
  
  def printResult(data:scala.collection.mutable.Map[Int, Int]):Unit = {
    
           // [94133,94133] [94200,94299] [94226,94399]
         val set = data.keySet;
   val itr = set.iterator;
    while (itr.hasNext) {
      var lowerBound :Int= itr.next();
      System.out.println("[" + lowerBound + "," + data.get(lowerBound) + "]");
    }
  }

  } 
