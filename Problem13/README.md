# Day 13: Transparent Origami
I randomly woke up at 2:51 AM today, tried to fall asleep for a few minutes, and got curious about the AoC problem. I checked it, brainstormed some solutions, and concluded that it would require me to write solutions out by pencil (after all, according to my thoughts, there was folding downwards, folding to the right, and possibly folding unevenly), so I went back to sleep.

I reread today's problem again on the way back from school. I first noticed that I would only have to fold up and left, which I'd missed on my first read. This time, however, I also looked at my input, scrolled to the bottom, and realized that there was no need for me to worry about the folding being uneven. At least for me, the first x fold is `655`, the second is `327`, the third is `163`, the fourth is `81`, and the fifth is `40`. The board's initial x is thus `1311`, and after the first fold it is `655`. Notice that 655 is right in the middle of 1311, and that 327 is right in the middle of 655. This pattern holds true for the rest of the x folds, and all of the y folds. This greatly simplified the problem for me, and I know that had I attempted this problem at 3 in the morning, I would probably have not noticed this pattern at all and would have tried to experiment with folding unevenly.

The coding process itself wasn't bad. I created a new Fold class for organizational purposes, and spent the next thirty-so minutes trying to initialize everything. The fact that x was horizontal and y vertical (instead of vice-versa) messed me up several times while trying to plot the initial points correctly. In fact, it took me longer to initiate everything than it took to code the folding logic (which wasn't hard at all).

Given the nature of the problem description for Part 1, I knew that Part 2 was almost definitely going to ask me to fold everything, so I did. (After all, my input had a vertical fold first, and the sample input had a horizontal fold first, so I thought I might as well code both functions and be done with it.) I was greeted with some random characters, but wasn't too surprised since I figured that that was going to be my answer to Part 2. Then I coded the specific method to count the visible points for Part 1. (Basically meaning, I solved Part 2 before solving Part 1!)

I thought this problem was a better version of Problem 5. It was extremely interesting that the input itself had something to offer in terms of coming up with solution logic, since I'm pretty used to simply importing the file after looking at the first two lines. I think it would've taken me way, way longer to solve this problem had I not looked at the input and figured the pattern out. Although I still liked yesterday's problem more, this problem is definitely a favorite.

### Answers
| Part 1 | Part 2 |
| :---: | :---: |
| 693 | UCLZRAZU |

## Part 1
You reach another volcanically active part of the cave. It would be nice if you could do some kind of thermal imaging so you could tell ahead of time which caves are too hot to safely enter.

Fortunately, the submarine seems to be equipped with a thermal camera! When you activate it, you are greeted with:

`Congratulations on your purchase! To activate this infrared thermal imaging camera system, please enter the code found on page 1 of the manual.`

Apparently, the Elves have never used this feature. To your surprise, you manage to find the manual; as you go to open it, page 1 falls out. It's a large sheet of [transparent paper](https://en.wikipedia.org/wiki/Transparency_(projection))! The transparent paper is marked with random dots and includes instructions on how to fold it up (your puzzle input). For example:

```
6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5
```

The first section is a list of dots on the transparent paper. `0,0` represents the top-left coordinate. The first value, `x`, increases to the right. The second value, `y`, increases downward. So, the coordinate `3,0` is to the right of `0,0`, and the coordinate `0,7` is below `0,0`. The coordinates in this example form the following pattern, where `#` is a dot on the paper and `.` is an empty, unmarked position:

```
...#..#..#.
....#......
...........
#..........
...#....#.#
...........
...........
...........
...........
...........
.#....#.##.
....#......
......#...#
#..........
#.#........
```

Then, there is a list of **fold instructions**. Each instruction indicates a line on the transparent paper and wants you to fold the paper **up** (for horizontal `y=...` lines) or **left** (for vertical `x=...` lines). In this example, the first fold instruction is `fold along y=7`, which designates the line formed by all of the positions where `y` is `7` (marked here with `-`):

```
...#..#..#.
....#......
...........
#..........
...#....#.#
...........
...........
-----------
...........
...........
.#....#.##.
....#......
......#...#
#..........
#.#........
```

Because this is a horizontal line, fold the bottom half **up**. Some of the dots might end up overlapping after the fold is complete, but dots will never appear exactly on a fold line. The result of doing this fold looks like this:

```
#.##..#..#.
#...#......
......#...#
#...#......
.#.#..#.###
...........
...........
```

Now, only 17 dots are visible.

Notice, for example, the two dots in the bottom left corner before the transparent paper is folded; after the fold is complete, those dots appear in the top left corner (at `0,0` and `0,1`). Because the paper is transparent, the dot just below them in the result (at `0,3`) remains visible, as it can be seen through the transparent paper.

Also notice that some dots can end up **overlapping**; in this case, the dots merge together and become a single dot.

The second fold instruction is fold along `x=5`, which indicates this line:

```
#.##.|#..#.
#...#|.....
.....|#...#
#...#|.....
.#.#.|#.###
.....|.....
.....|.....
```

Because this is a vertical line, fold **left**:

```
#####
#...#
#...#
#...#
#####
.....
.....
```

The instructions made a square!

The transparent paper is pretty big, so for now, focus on just completing the first fold. After the first fold in the example above, **17** dots are visible - dots that end up overlapping after the fold is completed count as a single dot.

**How many dots are visible after completing just the first fold instruction on your transparent paper?**

## Part 2
Finish folding the transparent paper according to the instructions. The manual says the code is always **eight capital letters**.

**What code do you use to activate the infrared thermal imaging camera system?**
