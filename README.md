# Skip List Implementation

## Project Overview
This project provides a robust implementation of the **Skip List** data structure. Skip Lists offer a probabilistic alternative to balanced trees, allowing efficient search, insertion, and deletion operations, with expected time complexity of **O(log n)**. The project includes the full implementation of required and optional operations of a Skip List. 

Two main files are provided:
1. **`SkipList.java`**: Contains the Skip List data structure implementation, with all core and optional methods implemented.
2. **`SkipListDriver.java`**: A driver program that tests the Skip List functionality with various input modes, including file input, interactive input, and random operation generation for benchmarking.

## Skip List
A **Skip List** is a layered linked list where each element may be included in higher layers with a certain probability. This layered structure allows for logarithmic expected time complexity for operations like search, insertion, and deletion, making Skip Lists a compelling alternative to balanced trees.

### Key Operations Implemented in `SkipList.java`:
- **add(x)**: Adds the element `x` to the Skip List. If `x` already exists, it replaces the current element. Returns `true` if the addition is successful, otherwise `false`.
- **contains(x)**: Checks whether the element `x` is present in the Skip List. Returns `true` if `x` is found, otherwise `false`.
- **remove(x)**: Removes the element `x` from the Skip List. Returns the removed element if successful, otherwise returns `null`.
- **size()**: Returns the total number of elements in the Skip List.
- **isEmpty()**: Returns `true` if the Skip List is empty, otherwise `false`.

### Optional Operations (Also Implemented in `SkipList.java`):
- **ceiling(x)**: Finds the smallest element greater than or equal to `x`.
- **first()**: Returns the first element of the Skip List.
- **floor(x)**: Finds the largest element smaller than or equal to `x`.
- **last()**: Returns the last element of the Skip List.
- **get(n)**: Returns the element at index `n` (0-based indexing).
  - **getLinear(n)**: Retrieves the element at index `n` with O(n) time complexity.
  - **getLog(n)**: Retrieves the element at index `n` in expected O(log n) time complexity.
- **iterator()**: Provides an iterator for traversing the Skip List in sorted order.
- **rebuild()**: Reorganizes the elements in the list into a perfect skip list, emulating binary search in a sorted array for improved search efficiency.

### Time Complexity:
- **add, remove, contains**: Expected **O(log n)**.
- **size, isEmpty**: **O(1)**.
- **getLinear(n)**: **O(n)**.
- **getLog(n)**: Expected **O(log n)**.

### Sample Input File:
```
Add 10
Add 20
Contains 15
Remove 10
Ceiling 18
First
Floor 12
Last
End
```

### Skip List Code Files:
- **`SkipList.java`**: Contains the Skip List implementation with all core and optional methods implemented.
- **`SkipListDriver.java`**: A driver program that facilitates testing through file input, interactive mode, or random operation generation.

### Instructions for Running the Project:
1. **Compilation**:
   ```bash
   javac SkipList.java SkipListDriver.java
   ```
2. **Execution**:
   - **File Input**:
     ```bash
     java SkipListDriver input.txt
     ```
   - **Interactive Mode**:
     ```bash
     java SkipListDriver
     ```
   - **Random Mode**:
     ```bash
     java SkipListDriver n 1000000
     ```
     If `"n"` followed by a number is passed as an argument, the driver will generate `n` random operations (`Add`, `Remove`, `Contains`) on the Skip List, allowing performance
     benchmarking for a large number of operations.

### Conclusion:
This project provides a full-featured Skip List implementation with comprehensive testing options. The random operation mode allows for large-scale performance testing, making it ideal for benchmarking the Skip List against other data structures.
