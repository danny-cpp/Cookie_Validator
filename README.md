<h2 align="center">ECE 325: Object-Oriented Programming</h2>
<h1 align="center">Assignment 1: Using Standard Libraries</h1>
<h2 align="center">Danh Nguyen</h2>

<h3> 1. Introduction </h3>
  <p>
    Remote repository can be found here: <a href="https://github.com/danny-cpp/Days_old">https://github.com/danny-cpp/Days_old</a></br></br>
    This program is intended to calculate the days since the date of birth of a person (days old). This program leverage pre-existing standard library Calendar (java.util.Calendar). To highlight the encapsulation aspect of the OOP methodology, everything that can be implemented will not be hard-code again, and those are implemented must not be tampered with.</br></br>
    To be considered a well-functioning product, it must handle error and exception well. 
  </p>
  
<h3> 2. Design </h3>
  <p>
    The program must have an effective range, meaning it must be designed on a specific domain:
  </p>
  <ul>  
        <li> If the birth date is later than today date, it is invalid. </li>
        <li> If it is earlier than October 15, 1582; it is not supported. Since we use <a href="https://en.wikipedia.org/wiki/Gregorian_calendar">Gregorian Calendar</a>. </li>
        <li> Invalid date is passed, it must either throw exception or recognize the error.
  </ul>
  <p>
    We have:</br>
    <i>Number of days old = Days left of birth year + Days between birth year and now + The day of this year</i></br></br>
    Details will be in the code itself
  </p>

<h3> 3. Instruction </h3>
  <p>
    Unzip the <i>CountDaysPackage.zip</i> and copy to your source location. Include: <br><br>
    >> import days_old.CountDays; <br>
    >> import days_old.evaluate_exceptions.*; <br><br>
    Package include usable class <strong>CountDays</strong> along with 2 methods:
     <ul>  
        <li> <strong>countingDays()</strong> </li>
        <li> <strong>dateParser(String s)</strong> </li>
    </ul>
  </p>
  <p>
    Create CountDays object via contructor:<br><br>
    <strong><i>>> CountDays data = new Countdays(Calendar start, Calendar end); </i></strong><br><br>
    Exception <i>EndDateExceedsBeginDateException</i> is thrown if <i>start</i> is earlier than <i>end</i>. Exception <i>BeginningDateExceedsLimit</i> is also thrown if <i>start</i> is earlier than October 15, 1582.
   
  </p>
  <p>
    Method <i><strong>countingDays</strong></i> will find the difference between the the dates.
  </p>
  <p>
    Method <i><strong>dateParser(String s)</strong></i> will parse a string under the format "yyyy-MM-dd" to as <i>Calendar object</i><br><br>
    Exception InvalidDate will be thrown if the date does not exist (e.g. 1999-02-29, 2000-11-31)
  </p>
  
<h3> 4. Implementation </h3>
  <p>
    <i>DaysOld.java</i> is the implementation of the <i>CountDays</i> class. Method <i><strong>days(String s)</strong></i> will evaluate the days difference
    between that date and today (by calling Java API). All exceptions are handled. Method returns void, but will print out report via
    console.
  </p>
  
<h3> 5. Acknowledgement </h3>
  <p>
    This package implemented the <i>Calendar class</i> (java.util.Calendar).
  </p>
  
<h3> 6. License </h3>
    <p>
        Package <i>days_old</i> is written by Danh Nguyen. Copyright © 2020. All rights reserved.
    </p>

___    
<h3>About the author</h3>
  <p> 
    My name is Danh Nguyen and I'm Computer Engineering student at the University of Alberta with great interest in algorithm and data science. 
    For more information and contact, you can find me at my <a href="https://www.linkedin.com/in/danh-h-nguyen/">Linkedin</a> or email me at 
    <a href="mailto:danh@ualberta.ca">danh@ualberta.ca</a> .
  </p>
  