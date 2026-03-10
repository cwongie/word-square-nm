# word-square-nm
Word square challenge in Java

# Word Square Solver
## How to run
```
mvn compile exec:java -Dexec.args='4 eeeeddoonnnsssrv'
```
## How to run tests
```
mvn test
```
## Approach
[Brief description of your algorithm]

## Observations 

    List<Character> letters = List.of('e','e','e','e','d','d','o','o','n','n','n','s','s','s','r','v');
    
    1. First row and column important. Eliminates bulk of the letters as we know the char positions will match 
    2. Column (down -> up) + row (left -> right) is a mirror e.g. ESOROSE
    2. Overlap at 'R' corner position [0][0]. Some letters are USED more times than they are COUNTED in the letter pool
    3. DIAGONAL - GRID IS SYMMETRICAL (top-left to bottom-right)

    R O S E
    O V E N
    S E N D
    E N D S

    4. 'R,V,N,S' - Overlap points where letter is the same (e.g. row 1 at col 3, row 3 at col 1)

    grid[row][col] == grid[col][row]

    5. Diagonal shared letters are used TWICE and counted ONCE, must satisfy two words simultaneously. 
    6. All other letters used ONCE
    7. Diagonal letters appear an odd number of times in the list