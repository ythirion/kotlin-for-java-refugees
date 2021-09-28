# Day 2
## [Challenge](https://adventofcode.com/2020/day/2)
We need to confirm that passwords meet the corporate policy.
```text
1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc
```
Each line contains the `password policy` and the `password`.
![img.png](img/1.png)
Our task is to check that the password :
* is valid
* conforms to the given policy

> The policies are different in the first and the second parts of the challenge.

### Part 1
The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid.
![img.png](img/2.png)
For example, 1-3 a means that the password must contain `a` at least once and at most 3 times.  
```text
1-3 a: abcde is valid : contains 1 `a` within the limits of the 3 occurences
1-3 b: cdefg is invalid : no `b` within the limits
2-9 c: ccccccccc is valid : contains 9 `c` within the limits of the 9 occurences
```



