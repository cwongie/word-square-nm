# Word Square Solver

A Java command-line program that solves n*n word squares. Given a grid size and 
a set of letters, it finds an arrangement where every row and column spells 
a valid English word when you read from left to right or from top to
bottom, with the requirement that the words you spell in each column and row of the same
number are the same word.

## Example

Input:
```
4 eeeeddoonnnsssrv
```

Output:
```
rose
oven
send
ends
```

## How to Run

### Prerequisites
- Java 21 or higher
- Maven 3.9 or higher

### Build
```
mvn clean package
```

### Run
```
java -jar target/word-square.jar "4 eeeeddoonnnsssrv"
```

### Run Tests
```
mvn test
```

## Challenge Inputs
```
4 aaccdeeeemmnnnoo
5 aaaeeeefhhmoonssrrrrttttw
5 aabbeeeeeeeehmosrrrruttvv
7 aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy
```

## Approach and Design
The solution is broken into four classes, each with a single responsibility:

- **Dictionary** — loads and queries the word list
- **WordSquare** — represents the grid as it is being built
- **WordSquareSolver** — implements the backtracking search algorithm
- **App** — entry point, reads command line input and prints the solution

### Algorithm
    The solver works row by row, using the word square's rules to eliminate bad choices early.

    The key pattern is that in a word square, the word in row 1 must start with the same letter as the second letter of row 0's word. 
    Row 2 must start with the third letter of row 0's word. And so on.

    After placing each word, we know exactly what letter every future row must start with. This is called a column prefix — the 
    letters built up so far in each column.

    The steps are:

    1. Find all valid words of the required length from the available letters
    2. Filter to only words that start with the correct column prefix
    3. Place a candidate word in the current row
    4. Check every remaining incomplete column. Does the dictionary have ANY word that starts with the letters this column has built up so far? 
    — if a column has no possible valid words, this path is a dead end
    5. If valid — move to the next row and repeat
    6. If not valid — remove the word just placed and try the next candidate instead (this is the backtracking step)
    7. If all candidates fail for a row — go back to the previous row and try a different word there too

    This means the solver avoids wasting time exploring paths that are already impossible, which makes it efficient for even larger grids

## Observations for optimisation

    While approaching the problem I identified several patterns and boundaries that could help solve this more efficently 

    1. First word (row) simultaneously determines the first column. Eliminates bulk of the letters as we know the char positions will match 
    2. Column (down -> up) + row (left -> right) is a mirror e.g. ESOROSE
    3. Overlap at 'R' corner position [0][0]. Some letters are USED more times than they are COUNTED in the letter pool
    4. GRID IS SYMMETRICAL - At the diagonal 'R,V,N,S' (top-left to bottom-right)

    R O S E
    O V E N
    S E N D
    E N D S

    5. grid[row][col] == grid[col][row]

    6. Diagonal shared letters are used TWICE and counted ONCE, must satisfy two words simultaneously (where row index == column index)
    7. All other letters used ONCE
    8. Diagonal letters appear an odd number of times in the provided character list

    For example, provided `eeeeddoonnnsssrv`:
    ```
    e = 4 (even) → not diagonal
    d = 2 (even) → not diagonal
    o = 2 (even) → not diagonal
    n = 3 (odd)  → diagonal 
    s = 3 (odd)  → diagonal 
    r = 1 (odd)  → diagonal 
    v = 1 (odd)  → diagonal 
    ```
    This immediately constrains 4 positions before any search begins.

## Potential optimisation
    A more efficient solver could:
    1. Count how many times each letter appears in the input. Any letter appearing an odd number of times must be a diagonal letter
    2. Place the first word using a diagonal letter at position [0][0]
    3. Derive column 0 immediately being the same as row 0
    4. Fill remaining diagonal positions next as they are most constrained [n++][n++]
    5. Fill remaining positions last

    This would reduce the search space significantly compared to the current 
    row-by-row approach, particularly for larger grids like the 7x7 challenge input.

## Dictionary Source
    [enable1.txt](http://norvig.com/ngrams/enable1.txt) — a list of 172,820 
    valid English words.
