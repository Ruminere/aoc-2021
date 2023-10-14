# Day 18: Snailfish
This was an okay problem, though less so if you miss key details from the question. Make sure to look out for them, especially this one:

> During reduction, at most one action applies, after which the process returns to the top of the list of actions. For example, if **split** produces a pair that meets the **explode** criteria, that pair **explodes** before other **splits** occur.

### Answers
| Part 1 | Part 2 |
| :---: | :---: |
| 3574 | 4763 |

## Part 1
This is too long for me to copy and format. [Here it is on AoC.](https://adventofcode.com/2021/day/18)

## Part 2
You notice a second question on the back of the homework assignment:

What is the largest magnitude you can get from adding only two of the snailfish numbers?

Note that snailfish addition is not [commutative](https://en.wikipedia.org/wiki/Commutative_property) - that is, `x + y` and `y + x` can produce different results.

Again considering the last example homework assignment above:

```
[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
[[[5,[2,8]],4],[5,[[9,9],0]]]
[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
[[[[5,4],[7,7]],8],[[8,3],8]]
[[9,3],[[9,9],[6,[4,9]]]]
[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]
```

The largest magnitude of the sum of any two snailfish numbers in this list is **3993**. This is the magnitude of `[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]` + `[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]`, which reduces to `[[[[7,8],[6,6]],[[6,0],[7,7]]],[[[7,8],[8,8]],[[7,9],[0,6]]]]`.

**What is the largest magnitude of any sum of two different snailfish numbers from the homework assignment?**
