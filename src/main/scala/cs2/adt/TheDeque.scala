package cs2.adt


class TheDeque[A: Manifest] extends Deque[A] {

  private class Node(val data: A, var prev: Node, var next: Node)
  private var head: Node = null
  private var tail: Node = null
  var length:Int = 0 // figure out what index things are at.

  def prepend(elem: A):Unit = {
    if (length == 0) {
      head = new Node(elem, null, null) // creates new node
      tail = head //head was updated otherwise tail will reset head to null
      length += 1 // bumps up length by 1 to keep track
    } else {
      head.next = new Node(elem, head, null)
      head = head.next
      length += 1
    }
  }
  def append(elem: A):Unit = {
    if (length == 0) {
        tail = new Node(elem, null, null)
        head = tail
        length += 1
    } else {
        tail.prev = new Node(elem, null, tail)
        tail = tail.prev
        length += 1
    }
}

    def front(): A = {// should return AND remove a single element from the logical "beginning" of the Deque
        if(isEmpty()) {
            throw new RuntimeException("DQ is Empty")
        }
        else {
            val tmp = head.data
            head = head.prev
            length -= 1
            tmp
        }
    }
    def back(): A = {// should return AND remove a single element from the logical "end" of the Deque
        if(isEmpty()) {
            throw new RuntimeException("DQ is Empty")
        }
        else {
            val tmp = tail.data
            tail = tail.next 
            length -= 1
            tmp
        }
    }
    def peekFront(): A = {// should return BUT NOT remove a single element from the logical "beginning"
        if (isEmpty()) {
            throw new RuntimeException("DQ is Empty")
        }
        else {
            head.data
        }
    }
    def peekBack(): A = {// should return BUT NOT remove a single element from the logical "end"
        if (isEmpty()) {
            throw new RuntimeException("DQ is Empty")
        }
        else {
            tail.data
        }
    }
    def len():Int = {
        length
    }
    def isEmpty(): Boolean = {// should return true if the Deque contains no elements, and false otherwise
        var empty = (length == 0) //based on a boolean whether length is equal to 0
        empty
    }
    
}

    
