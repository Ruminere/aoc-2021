# Day 11: Dumbo Octopus
This was another problem that fit well with recursive DFS. I got to copy a lot of my procedures and methods from [Problem 9](https://github.com/Ruminere/aoc-2021/tree/main/Problem09), but that was the end of the easy portion. After that, however, I didn't realize I was supposed to keep a list of octopi that had already flashed until I ran the sample input (the 10x10 one, not the small 5x5 one; they give you two this time). It was quite annoying to debug this.

Here were my answers:
| Part 1 | Part 2 |
| :---: | :---: |
| 1705 | 265 |

## Part 1
I'm not going to try to copy this one down, just [read Part 1 on AoC](https://adventofcode.com/2021/day/11). There's too much formatting that I can't replicate on GitHub (or haven't learned how to replicate), it's too long, and Part 1 is available for everyone anyway. Although, hey, [dumbo octopi](https://www.youtube.com/watch?v=eih-VSaS2g0) are actually kind of cute.

## Part 2
It seems like the individual flashes aren't bright enough to navigate. However, you might have a better option: the flashes seem to be **synchronizing**!

In the example above, the first time all octopuses flash simultaneously is step **195**:

```
After step 193:
5877777777
8877777777
7777777777
7777777777
7777777777
7777777777
7777777777
7777777777
7777777777
7777777777

After step 194:
6988888888
9988888888
8888888888
8888888888
8888888888
8888888888
8888888888
8888888888
8888888888
8888888888

After step 195:
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
```

If you can calculate the exact moments when the octopuses will all flash simultaneously, you should be able to navigate through the cavern. **What is the first step during which all octopuses flash?**
