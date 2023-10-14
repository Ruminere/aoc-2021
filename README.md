# AdventOfCode2021
You are currently viewing the **AdventOfCode2021** repository, or, well, a reupload of it sans the more personal README content.

I made this repository back when I was not great at naming folders, so they have a capital P.

## About this Repository
The original problems can be found [here](https://adventofcode.com/2021 "Advent of Code 2021"). All code found here are solutions to those questions.

### Folder Content
Most of the solutions are written in Java, although Problems 1 and 2 contain solutions in Python (back when I was still experimenting with the language).

It also includes an `input.txt` file.

Lastly, there's a README file with my thoughts on the problem at the very top, my solutions, and then the problems themselves.

### Running the Code
- I put my site-generated puzzle inputs either in `input.txt` or near the top of the code.
- The general syntax is `java ProblemXX`.
  - However, if you wish to run Problems 1 or 2 in Python, run `python3 Problem0X.py`.
  - Java code for Problems 1 through 7 use the old format: `java Problem0X input.txt [1/2]`.

## Extras
### Longest Runtimes Hall of ~~Shame~~ Fame
*Mainly so I can laugh at myself. I'm surprised there aren't more entries.*\
The requirements used to be that the code had to make me worried that nothing was happening in the background or that it would take forever (so around 5 minutes or so). However, because there's barely anything on here and everything runs relatively quickly, it's now anything that takes 1 second or longer to finish.
1. [**Problem 21 Part 2**](https://github.com/Ruminere/aoc-2021/tree/main/Problem21): 0m 2.482s. The recursion takes a while (after all, it has to go through 77879203 cases, if I'm correct).

#### Honorable Mentions
1. [**Problem 03 Part 2**](https://github.com/Ruminere/aoc-2021/tree/main/Problem03): 15m 5.521s. This happened when running a while loop that never incremented and thus could never satisfy the ending condition. The corrected code takes <0.3s.

### Favorite Problems
Interesting how these are the first three powers of 2, but multiplied by 6.
1. [**Problem 24**](https://github.com/Ruminere/aoc-2021/tree/main/Problem24): it could be either very annoying or very interesting to solve, depending on your tastes. You do have to notice quite a few things, though!
2. [**Problem 12**](https://github.com/Ruminere/aoc-2021/tree/main/Problem12): a recursion problem, but a nice recursion problem! Seemed scary at the beginning, but got pretty straightforward once I started coding everything out.
3. [**Problem 06**](https://github.com/Ruminere/aoc-2021/tree/main/Problem06): simple yet deceptive.
