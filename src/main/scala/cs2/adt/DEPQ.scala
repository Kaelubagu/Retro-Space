package cs2.adt

class DEPQ[A <% Ordered[A]] extends DoubleEndPriorityQueue[A] {


  private class Node(var data:A, var below:Node, var above:Node)
  private var top:Node = null
  private var bottom:Node = null
  private var length:Int = 0

  //Place your implementation here
  def isEmpty():Boolean = {
    var empty = (length == 0) //boolean statement (forcing it to be equal to the outcome of a boolean statement.) or that particular statement inside of the parenthesis
    empty
  }
  def add(elem: A):Unit = {
    if(length == 0) {
      top = new Node(elem, null, null)
      bottom = top 
      length += 1
    } else {
        var smolboirover = top
      if(smolboirover.data < elem) {
          smolboirover.above = new Node(elem, smolboirover, null)
          top = smolboirover.above
          length += 1
      }
      else {
        while(smolboirover.below != null && smolboirover.below.data > elem) {
          smolboirover = smolboirover.below
          length += 1
        }
        if (smolboirover.below == null) {
          smolboirover.below = new Node(elem, null, smolboirover)
          bottom = smolboirover.below
          length += 1
        }
        else {
          smolboirover.below = new Node(elem, smolboirover.below, smolboirover)
          length += 1
        }
      }
    }
  }
  def peekMax():A = { //peeking the maximum value //top is always the maximum value
    if (isEmpty) {
      throw new RuntimeException("Depeque is empty...")
    }
    top.data //returns the top data
  }
  def max():A = {
    if(isEmpty) {
      throw new RuntimeException("Depequeue is empty...")
    }
    else { //return stuff
      val tmp = top.data
      top = top.below 
      length -= 1
      tmp
    }
  }
  def peekMin():A = { //bottom is always the minimum value.
    if (isEmpty) {
      throw new RuntimeException("Depeque is empty...")
    }
    bottom.data
  } 
  def min():A = {
    if (isEmpty) {
      throw new RuntimeException("Depeque is empty...")
    }
    else {
      val tmp = bottom.data
      bottom = bottom.above 
      length -= 1
      tmp
    }
  }
}