# Day 1 Exercises

### Data Structures

 - Create a sequence of integers from 1 to 10 (inclusive)
 - Create a sequence of integers from 1 to 10 (exclusive)
 
 - Take first n elements of this sequence
 - Drop n elements from this sequence
 
 - Multiply every element of the sequence by n (n could be anything)
 - Filter odd / even numbers from this sequence
 - Count the odds / evens in this list (count function)
 - Sum / Multiply all the elements of the list (using fold / reduce)
 
 - Find the max / min of the sequence
 - Convert the elements of the seq into a comma (any separator) separated string
 - Find if a particular element belongs to this seq
 
 ### Functions
 
  - Write a function which returns a Unit (void)
  
  - Write an overloaded function multiply, which takes two Int params and an optional int parameter (multiplier). If
    multiplier is specified then multiply the product of two params with it, else multiply by 1.
    
  - Write a function (sum) which takes variable arguments (and call it). Call it by specifying parameter names.
  
  - Write a function (divide) which returns an Either[String, Int]. If the divisor is zero, it should return error
    message "cannot divide by zero", else should return the quotient.
    
  - Write a function (divide) which returns a Try[Int]. If the divisor is zero, it should throw an exception
    "cannot divide by zero", else should return the quotient.
 