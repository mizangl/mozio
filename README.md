# mozio
### Mozio Test 1 of 2

Jane and Alice each have a collection of stamps. Each stamp has a number that uniquely identifies it. 
In order to keep increasing their collections, they want to trade repeated ones, always keeping a spare, just in case. 
They are going to meet soon and exchange stamps. 
They agreed to bring all their repeated stamps that the other person doesn't have, or has only one.

Write a program to help Jane and Alice exchange stamps: 
it receives two unordered arrays of stamps that Jane and Alice has, and return two unordered arrays of stamps that Jane and Alice will get from the other side. 
Output order of stamps in each array is not relevant, however the first array is always Jane’s stamps, and the second array is always Alice’s stamps in both input and output.

| Input                                                     | Output                 |
|-----------------------------------------------------------|------------------------|
| { 1, 7, 3, 1, 7, 4, 5, 1, 7, 1 }, { 2, 3, 3, 2, 4, 3, 2 } | { 2, 3 }, { 1, 1, 7 }  |
| { 1, 2, 3, 4, 4 }, { 4, 4, 4, 5, 6, 7 }                   | { <empty>}, { <empty>} |
| { 5, 4, 4, 3, 3, 3, 3 }, { 1, 3 }                         | { <empty> }, { 3, 3 }  |
  
  
#  
### Mozio Test 2 of 2

#### Pizza delivery

A small pizza delivery company was looking for growth and wanted to develop their own pizza delivery app. They hired you to develop this app.

In order to keep this small, we'll only work on a small portion of that app: the menu and flavor selection, plus a confirmation screen.

Write an app that has the following features:

- A menu of pizza flavors (read from the JSON above) where the user can select the flavors they want. A pizza can have one flavor (full pizza of the same flavor) or two flavors (half/half)
- To keep the scope small, only one pizza can be chosen at a time. No need for a shopping cart
- Flavors have their own prices. Total pizza price is calculated based on the price of each half  (i.e. if a flavor costs $10, a half of this flavor will cost $5 and a full pizza of this flavor $10)

## Result

PizzaDelivery has separeted features on each package. It follows a simple version of MVI pattern, ViewModel acts as Reducers. It was considered use Use Case model, but I rejected it, since the scope is too small. Other thing I discarted was using Flow<T> as a communication interface between 'layers', I would consider it for features development,right now, it wasn't needed to apply their operator, and to keep the thing more simple just used the ApiResult.

I tried to keep the list of dependencies low, save for `Barista`, I was running out of time to write custom espresso matchers and assertions, I would preferer to do it to have more control on flaky test.
