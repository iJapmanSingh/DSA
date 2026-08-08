class Solution {
    static class Fenwick {
        int[] tree;
        Fenwick(int n) {
            tree = new int[n + 1];
        }
        void add(int index, int value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }
        int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }
    }
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length ;
        long[] prefix = new long[n + 1];
        for(int i =0 ; i < n ; i++){
            if(nums[i] % 2 == 0){
                prefix[i + 1] = prefix[i] + b;
            }else{
                prefix[i + 1] = prefix[i] - a;
            }
        }
        //cordinate compression
        long[] sorted = prefix.clone();
        Arrays.sort(sorted);
        int m = 0 ;
        for(long x : sorted){
            if(m == 0 || sorted[m-1] != x){
                sorted[m] = x ;
                m++;
            }
        }

        Fenwick fenwick = new Fenwick(m);
        long answer = 0 ;
        int previousCount = 0 ;

        for(long current : prefix){
            //find rank of current prefix sum
            int rank = lowerBound(sorted , m , current) + 1;

            //number of previous prefix sum < current
            int smaller = fenwick.query(rank - 1);
            
            //number of previous prefix sum >= current
            int greaterOrEqual = previousCount - smaller;

            answer += greaterOrEqual;

            //Insert current prefix sum
            fenwick.add(rank , 1);

            previousCount++ ;
        } 
        return answer ;
    }
    static int lowerBound(long[] arr, int size, long target) {

        int left = 0;
        int right = size;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}




/*
===========================================================
ALGORITHM
===========================================================

At this point, the array has already been transformed:

    even -> +b
    odd  -> -a

So we need to count subarrays whose sum <= 0.


1. PREFIX SUM
-----------------------------------------------------------

Create prefix sums.

For a subarray from (i + 1) to j:

    sum = prefix[j] - prefix[i]

We need:

    prefix[j] - prefix[i] <= 0

Therefore:

    prefix[i] >= prefix[j]

So the problem becomes:

    For every prefix[j],
    count how many PREVIOUS prefix sums are >= prefix[j].

We process prefix sums from left to right.


2. WHY COORDINATE COMPRESSION?
-----------------------------------------------------------

Prefix sums can be large and negative, for example:

    [-1000000000, -5, 0, 37, 1000000000]

A Fenwick Tree works with small positive indices:

    1, 2, 3, 4, 5

We only care about the ORDER of prefix sums, not their
actual values.

So sort all prefix sums and assign them ranks:

    -1000000000 -> 1
    -5          -> 2
     0          -> 3
     37         -> 4
     1000000000 -> 5

This is coordinate compression.

Equal values get the same rank.

Example:

    [-5, -5, 0, 7, 7]

becomes:

    -5 -> 1
     0 -> 2
     7 -> 3


3. FINDING THE RANK
-----------------------------------------------------------

For every prefix sum, we need to know its compressed rank.

The prefix sums are stored in sorted order.

Use binary search to find the position of the current
prefix sum in the sorted unique array.

That position + 1 is its Fenwick Tree index because
Fenwick Tree uses 1-based indexing.

Example:

    sorted = [-5, -2, 0, 7]

    current = 0

    position = 2
    Fenwick rank = 3


4. WHAT DOES THE FENWICK TREE STORE?
-----------------------------------------------------------

The Fenwick Tree stores the FREQUENCY of prefix-sum ranks
that we have already seen.

For example, suppose we have already processed:

    ranks = [2, 3, 3, 5]

Then the Fenwick Tree represents their frequencies.

Its two important operations are:

    add(rank, 1)
        -> one more prefix sum with this rank has appeared.

    query(rank)
        -> how many previous prefix sums have rank <= rank?


5. PROCESS PREFIX SUMS FROM LEFT TO RIGHT
-----------------------------------------------------------

For the current prefix sum:

    current = prefix[j]

Find its compressed rank.

We need:

    number of previous prefix sums >= current


6. FIND HOW MANY ARE SMALLER
-----------------------------------------------------------

Suppose current has rank = R.

Ask the Fenwick Tree:

    query(R - 1)

This gives the number of previous prefix sums whose
rank is less than R.

Because ranks preserve ordering:

    rank < R

means:

    prefix sum < current


7. GET HOW MANY ARE >= CURRENT
-----------------------------------------------------------

Let:

    previousCount = number of prefix sums already processed

Let:

    smaller = number of previous prefix sums < current

Every previous prefix sum is either:

    < current

or:

    >= current

Therefore:

    greaterOrEqual = previousCount - smaller

These are exactly the prefix sums that satisfy:

    previous prefix >= current prefix

and therefore create a subarray whose sum <= 0.

Add this number to the answer.


8. INSERT CURRENT PREFIX SUM
-----------------------------------------------------------

After calculating the answer for the current prefix,
insert its rank into the Fenwick Tree.

IMPORTANT:

    Query first
    Insert second

because the current prefix must NOT be considered a
"previous" prefix sum.

Then increase previousCount.


9. COMPLETE FLOW
-----------------------------------------------------------

Prefix sums
     ↓
Sort all prefix sums
     ↓
Remove duplicates
     ↓
Coordinate compression
     ↓
For every prefix sum:
     ↓
Binary search its compressed rank
     ↓
Fenwick Tree tells how many previous ranks < current rank
     ↓
previousCount - smaller
     ↓
number of previous prefix sums >= current prefix
     ↓
add to answer
     ↓
insert current rank into Fenwick Tree


TIME COMPLEXITY
-----------------------------------------------------------

Building prefix sums:       O(n)

Sorting for compression:    O(n log n)

Binary search for ranks:    O(n log n)

Fenwick operations:         O(n log n)

Total:                      O(n log n)

Space:                      O(n)


KEY IDEA
-----------------------------------------------------------

The difficult part is recognizing:

    subarray sum <= 0
           ↓
    prefix[i] >= prefix[j]
           ↓
    count previous prefix sums >= current prefix sum

Coordinate compression converts prefix sums into ranks,
and Fenwick Tree efficiently counts the previous ranks.
===========================================================
*/
    
    
