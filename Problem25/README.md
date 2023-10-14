# Day 25: Sea Cucumber
(I'm writing this on December 25.)

I woke up at 6:56 AM since I somehow always wake up early on Christmas without an alarm. Then I unwrapped my presents. For any other Rubik's puzzle collectors out there: the [MoYu Puppet Cube 2](https://www.thecubicle.com/products/moyu-puppet-cube-ii?pr_prod_strat=copurchase&pr_rec_pid=4708444766291&pr_ref_pid=4708444700755&pr_seq=uniform) feels *amazing*. I have it scrambled on my desk, and turning is amazing, and it looks awesome and scary at the same time, and I have no idea how to even approach a solve.

Knowing that Day 25s tend to be easier than a lot of the surrounding problems, I decided to boot up my computer and quickly do the problem. I was very happy that I did not have to code anything to convert the input to code since I already had that framework from [Problem 11](https://github.com/Ruminere/aoc-2021/blob/main/Problem11/Problem11.java), as this was once again using a rectangular map. (Now that I think about it, [there's](https://github.com/Ruminere/aoc-2021/tree/main/Problem05) [been](https://github.com/Ruminere/aoc-2021/tree/main/Problem09) [quite](https://github.com/Ruminere/aoc-2021/tree/main/Problem11) [a](https://github.com/Ruminere/aoc-2021/tree/main/Problem12) [few](https://github.com/Ruminere/aoc-2021/tree/main/Problem13) [graph](https://github.com/Ruminere/aoc-2021/tree/main/Problem15) [problems](https://github.com/Ruminere/aoc-2021/tree/main/Problem23) this year.)

I made my `moveRight()` and `moveDown()` functions return booleans, so that the function itself moves the cucumbers around and says if any cucumbers moved or not. It was deceptively simple. The functions worked as intended, the strong water current testing example (the one with 4 steps) worked perfectly, and it was time for the sample input... which gave me 60.

I couldn't find the logic error in my code, so I thought it was simply a counting error. I ran my input and got `465`, so I answered with `463` on the site. Of course, I got it wrong.

I then replaced my input with the sample input again, then decided to print the map after every step (one `moveRight()` then one `moveDown()`). But then I noticed that my map started to be different from the intended map after step 2. I then realized that the horizontal cucumbers moved at the same time, and the vertical cucumbers moved at the same time. But my code moved the top cucumbers first and the bottom cucumbers last, which screwed up my `moveDown()` logic. I didn't know if it also screwed up my `moveRight()` logic, but I decided to change that too for consistency and because it wouldn't take that much time anyway (they are almost identical).

Thus, I made a `newMap` that was filled completely with `.` characters (empty), then fill it in as I went. Then I would still keep a boolean value, but instead of just returning the boolean I'd also return the 2D Character array. Then I returned the array and boolean inside of an `Object[]` array. It did not take too long afterwards for me to get the answer.

I was worried that I would get a [Hall of Shame](https://github.com/Ruminere/aoc-2021#longest-runtimes-hall-of-shame-fame) entry since the input looks big, but it didn't happen this time. (I'm honestly surprised there's no legitimate entries!)

This was a fun problem! It wasn't too easy, but was still simple enough. I literally spent more time writing this README than solving the problem.

And by the way, Merry Christmas and happy holidays!

### Answers
| Part 1 | Part 2 |
| :---: | :---: |
| 367 | N/A |

## Part 1
This is it: the bottom of the ocean trench, the last place the sleigh keys could be. Your submarine's experimental antenna **still isn't boosted enough** to detect the keys, but they **must** be here. All you need to do is **reach the seafloor** and find them.

At least, you'd touch down on the seafloor if you could; unfortunately, it's completely covered by two large herds of [sea cucumbers](https://en.wikipedia.org/wiki/Sea_cucumber), and there isn't an open space large enough for your submarine.

You suspect that the Elves must have done this before, because just then you discover the phone number of a deep-sea marine biologist on a handwritten note taped to the wall of the submarine's cockpit.

"Sea cucumbers? Yeah, they're probably hunting for food. But don't worry, they're predictable critters: they move in perfectly straight lines, only moving forward when there's space to do so. They're actually quite polite!"

You explain that you'd like to predict when you could land your submarine.

"Oh that's easy, they'll eventually pile up and leave enough space for-- wait, did you say submarine? And the only place with that many sea cucumbers would be at the very bottom of the Mariana--" You hang up the phone.

There are two herds of sea cucumbers sharing the same region; one always moves **east** (`>`), while the other always moves **south** (`v`). Each location can contain at most one sea cucumber; the remaining locations are **empty** (`.`). The submarine helpfully generates a map of the situation (your puzzle input). For example:

```
v...>>.vv>
.vv>>.vv..
>>.>v>...v
>>v>>.>.v.
v>v.vv.v..
>.>>..v...
.vv..>.>v.
v.v..>>v.v
....v..v.>
```

Every **step**, the sea cucumbers in the east-facing herd attempt to move forward one location, then the sea cucumbers in the south-facing herd attempt to move forward one location. When a herd moves forward, every sea cucumber in the herd first simultaneously considers whether there is a sea cucumber in the adjacent location it's facing (even another sea cucumber facing the same direction), and then every sea cucumber facing an empty location simultaneously moves into that location.

So, in a situation like this:

`...>>>>>...`

After one step, only the rightmost sea cucumber would have moved:

`...>>>>.>..`

After the next step, two sea cucumbers move:

`...>>>.>.>.`

During a single step, the east-facing herd moves first, then the south-facing herd moves. So, given this situation:

```
..........
.>v....v..
.......>..
..........
```

After a single step, of the sea cucumbers on the left, only the south-facing sea cucumber has moved (as it wasn't out of the way in time for the east-facing cucumber on the left to move), but both sea cucumbers on the right have moved (as the east-facing sea cucumber moved out of the way of the south-facing sea cucumber):

```
..........
.>........
..v....v>.
..........
```

Due to **strong water currents** in the area, sea cucumbers that move off the right edge of the map appear on the left edge, and sea cucumbers that move off the bottom edge of the map appear on the top edge. Sea cucumbers always check whether their destination location is empty before moving, even if that destination is on the opposite side of the map:

```
Initial state:
...>...
.......
......>
v.....>
......>
.......
..vvv..

After 1 step:
..vv>..
.......
>......
v.....>
>......
.......
....v..

After 2 steps:
....v>.
..vv...
.>.....
......>
v>.....
.......
.......

After 3 steps:
......>
..v.v..
..>v...
>......
..>....
v......
.......

After 4 steps:
>......
..v....
..>.v..
.>.v...
...>...
.......
v......
```

To find a safe place to land your submarine, the sea cucumbers need to stop moving. Again consider the first example:

```
Initial state:
v...>>.vv>
.vv>>.vv..
>>.>v>...v
>>v>>.>.v.
v>v.vv.v..
>.>>..v...
.vv..>.>v.
v.v..>>v.v
....v..v.>

After 1 step:
....>.>v.>
v.v>.>v.v.
>v>>..>v..
>>v>v>.>.v
.>v.v...v.
v>>.>vvv..
..v...>>..
vv...>>vv.
>.v.v..v.v

After 2 steps:
>.v.v>>..v
v.v.>>vv..
>v>.>.>.v.
>>v>v.>v>.
.>..v....v
.>v>>.v.v.
v....v>v>.
.vv..>>v..
v>.....vv.

After 3 steps:
v>v.v>.>v.
v...>>.v.v
>vv>.>v>..
>>v>v.>.v>
..>....v..
.>.>v>v..v
..v..v>vv>
v.v..>>v..
.v>....v..

After 4 steps:
v>..v.>>..
v.v.>.>.v.
>vv.>>.v>v
>>.>..v>.>
..v>v...v.
..>>.>vv..
>.v.vv>v.v
.....>>vv.
vvv>...v..

After 5 steps:
vv>...>v>.
v.v.v>.>v.
>.v.>.>.>v
>v>.>..v>>
..v>v.v...
..>.>>vvv.
.>...v>v..
..v.v>>v.v
v.v.>...v.

...

After 10 steps:
..>..>>vv.
v.....>>.v
..v.v>>>v>
v>.>v.>>>.
..v>v.vv.v
.v.>>>.v..
v.v..>v>..
..v...>v.>
.vv..v>vv.

...

After 20 steps:
v>.....>>.
>vv>.....v
.>v>v.vv>>
v>>>v.>v.>
....vv>v..
.v.>>>vvv.
..v..>>vv.
v.v...>>.v
..v.....v>

...

After 30 steps:
.vv.v..>>>
v>...v...>
>.v>.>vv.>
>v>.>.>v.>
.>..v.vv..
..v>..>>v.
....v>..>v
v.v...>vv>
v.v...>vvv

...

After 40 steps:
>>v>v..v..
..>>v..vv.
..>>>v.>.v
..>>>>vvv>
v.....>...
v.v...>v>>
>vv.....v>
.>v...v.>v
vvv.v..v.>

...

After 50 steps:
..>>v>vv.v
..v.>>vv..
v.>>v>>v..
..>>>>>vv.
vvv....>vv
..v....>>>
v>.......>
.vv>....v>
.>v.vv.v..

...

After 55 steps:
..>>v>vv..
..v.>>vv..
..>>v>>vv.
..>>>>>vv.
v......>vv
v>v....>>v
vvv...>..>
>vv.....>.
.>v.vv.v..

After 56 steps:
..>>v>vv..
..v.>>vv..
..>>v>>vv.
..>>>>>vv.
v......>vv
v>v....>>v
vvv....>.>
>vv......>
.>v.vv.v..

After 57 steps:
..>>v>vv..
..v.>>vv..
..>>v>>vv.
..>>>>>vv.
v......>vv
v>v....>>v
vvv.....>>
>vv......>
.>v.vv.v..

After 58 steps:
..>>v>vv..
..v.>>vv..
..>>v>>vv.
..>>>>>vv.
v......>vv
v>v....>>v
vvv.....>>
>vv......>
.>v.vv.v..
```

In this example, the sea cucumbers stop moving after **58** steps.

Find somewhere safe to land your submarine. **What is the first step on which no sea cucumbers move?**

## Part 2
Suddenly, the experimental antenna control console lights up:

`Sleigh keys detected!`

According to the console, the keys are **directly under the submarine**. You landed right on them! Using a robotic arm on the submarine, you move the sleigh keys into the airlock.

Now, you just need to get them to Santa in time to save Christmas! You check your clock - it **is** Christmas. There's no way you can get them back to the surface in time.

Just as you start to lose hope, you notice a button on the sleigh keys: **remote start**. You can start the sleigh from the bottom of the ocean! You just need some way to **boost the signal** from the keys so it actually reaches the sleigh. Good thing the submarine has that experimental antenna! You'll definitely need **50 stars** to boost it that far, though.

The experimental antenna control console lights up again:

```
Energy source detected.
Integrating energy source from device "sleigh keys"...done.
Installing device drivers...done.
Recalibrating experimental antenna...done.
Boost strength due to matching signal phase: 1 star
```

Only **49 stars** to go.
