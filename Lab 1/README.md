# Part 1: Acceptance Criteria
- Input: The list of numbers is 8, 3, 15, 6, 2.
- Output: The program finds the largest number (15), counts how many comparisons it makes (4), and prints the numbers in sorted order (2, 3, 6, 8, 15).
- Data Structure Used: An integer array is used to store the numbers.
If the input becomes 100 times bigger: Finding the largest number will take proportionally more time because every number must be checked once. Bubble sort will take much longer because it compares many pairs of numbers.

# Part 2: Acceptance Criteria
- Input: The tasks are Task1, Task2, Task3, Task4, Task5.
- Output: The stack completes the tasks in reverse order, while the queue completes them in the same order they arrive. A printer should use a queue.
- Data Structure Used: A Stack and a Queue are used.
If the input becomes 100 times bigger: The program will take proportionally more time because each task is processed only once, whether it is a stack or a queue.

# Part 4: Acceptance Criteria
- Input: The program runs a single loop and a nested loop with the given values.
- Output: The single loop runs 5 times, the single loop with n = 20 runs 20 times, the nested loop runs 25 times, and with n = 10 it runs 100 times.
- Data Structure Used: No data structure is used. Only for loops and variables are needed.
If the input becomes 100 times bigger: A single loop takes proportionally more time because it runs once for each value. A nested loop grows much faster because every value is compared with every other value. 
