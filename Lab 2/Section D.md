# 1. Space used by full vs sparse representation

For an m × n matrix with k non-zero elements:

Full 2D representation

Every element is stored, including zeros.

$$ \boxed{m \times n} $$

So, the space required is O(mn).

Sparse triple representation

Only the non-zero elements are stored. Each non-zero element requires three values:

(row, column, value)

Therefore:

$$ \boxed{3k} $$

So, the space required is O(k).

If we also count the optional header containing rows, columns, and number of non-zero elements, it would be 3k + 3. But the question asks for the representation in terms of k, so 3k is the main answer.

2. Testing with two 6 × 6 matrices

A 6 × 6 matrix contains:

$$ 6 \times 6 = 36 $$

total elements.

Matrix A: Roughly 80% zero

Example:

0 0 5 0 0 0
0 8 0 0 0 0
0 0 0 0 3 0
0 0 0 7 0 0
0 0 0 0 0 9
2 0 0 0 0 0
Total elements = 36
Zero elements = 29
Non-zero elements = 7
Zero percentage ≈ 80.6%
Sparse representation = 7 × 3 = 21 stored values
Full representation = 36 stored values
Representation	Elements stored
Full 2D	36
Sparse triples	21

So, the sparse representation saves:

$$ 36 - 21 = \boxed{15} $$

values.

Matrix B: Fewer than 20% zero

Example:

1 2 3 4 5 6
7 8 9 10 11 12
13 14 15 16 17 18
19 20 21 22 23 24
25 26 27 28 29 30
31 32 33 34 35 0
Total elements = 36
Zero elements = 1
Non-zero elements = 35
Zero percentage ≈ 2.8%
Sparse representation = 35 × 3 = 105 stored values
Full representation = 36 stored values
Representation	Elements stored
Full 2D	36
Sparse triples	105

Here, sparse representation actually uses more space because every non-zero value requires three pieces of information.

3. When does sparse representation stop saving space?

For a full matrix:

$$ mn $$

values are stored.

For sparse representation:

$$ 3k $$

values are stored.

Sparse representation saves space when:

$$ 3k < mn $$

Therefore:

$$ k < \frac{mn}{3} $$

So the number of non-zero elements should be less than about 33.3% of the total elements.

Answer

The sparse representation stops saving space at roughly 33% non-zero elements.

For example, with a 36-element matrix:

$$ 36/3 = 12 $$

So:

Less than 12 non-zero elements → sparse saves space.
Around 12 non-zero elements → approximately equal.
More than 12 non-zero elements → full representation uses less space.

Our two tests demonstrate this:

Matrix A: 7 non-zero → sparse uses 21 vs 36.
Matrix B: 35 non-zero → sparse uses 105 vs 36.

Note: This is based purely on the number of stored integer fields and ignores Java ArrayList/object overhead. In an actual Java program, object and collection overhead can make the practical break-even point different.

4. Real-world example: Graph adjacency matrices

A graph represented using an adjacency matrix is a natural example of a sparse matrix.

Suppose there are 1,000 cities represented as vertices. The matrix has:

$$ 1000 \times 1000 = 1,000,000 $$

entries.

However, each city may have direct roads to only a small number of other cities. Therefore, most pairs of cities have no direct connection, so most entries contain 0.

For example:

    A B C D
A   0 1 0 0
B   1 0 1 0
C   0 1 0 1
D   0 0 1 0

Only existing connections contain non-zero values, while the majority of possible connections are absent and represented by zeros.