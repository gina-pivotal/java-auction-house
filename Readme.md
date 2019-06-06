Auction House
=============

The Auction House is a simple command-line application that accepts user input and plays out different auction strategies. For example, a Blind Auction accepts various players, prompts them for their bids and then prints out the user who gave the highest bid as the winner. 

The canned interview provides a naïve procedural implementation of the Blind Auction that deliberately makes as few design decisions as possible, other than injecting the IO streams to support testing. There are test cases for the Blind Auction and English Auction, though both test classes are incomplete. The tracker project includes stories for the Vickrey Auction and Dutch Auction, but there are no tests for these types.

The exercise is useful for assessing a candidate's approach to OO design, including questions such as:
 * How do they extract methods? 
 * Do they adhere to the balanced abstraction principle?
 * Do they extract classes?
 * How do they share logic between classes?
 * Do they understand the drawbacks of inheritance?
 * Do they make clear that they understand the compromises they are making in order to make progress?
 * Do they create tests to cover gaps in the provided test suite, or just continue blindly on?
 * How do they balance refactoring against making progress?


Getting started
---------------

- Import the tracker stories into a fresh Tracker project. 
- Ensure that the project is set up. 
- Verify that you can run the tests.  `./gradlew test`
- Understand the nuances of the different auction types.
- Read through the tests for each of the various types of auction. 
- Don't attempt an implementation yourself beforehand. Having too much knowledge of the exercise probably makes it harder to run, rather than easier!

Running Tests
-------------

```bash
./gradlew test
```

Running the App
---------------

```bash
./gradlew --console plain run
```

Tips:
-----

- Some interviewers like to delete the tests for the incomplete auction types.
- Ensure candidates understand that the point of the exercise is not to complete as many tests as possible. The approach is more important.
- There are many holes in the test suite. Encourage them to write tests to fill the holes (e.g. what happens if all users bid the same in the Blind auction, or less than the minimum price?)

