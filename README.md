# hamiltonian-cycle
Hamiltonian cycle in polynomial time coplexity - O(n^6)
1. Download [TSP Solver and Generator](https://tspsg.com/en/download)
2. Open TSP solver -> settings -> preferences -> task
3. Check symmetric mode.
4. In task generation, write minimum random value = 0 and maximum value = 1
5. Uncheck fractional random values and click ok.
6. Change cities as per your need. you can select maximum 50 cictes.
7. Click on solve button at the bottom right corner.
8. It will open solution tab from where you have to copy "Variant #1 Task matrix of 0s and 1s" and paste in problem.txt.
9. Open problem.txt. Delete that blank row at the very beginning of file problem.txt
10. Run HamiltonianCycle.java and it will print Hamiltonian cycle is present or not.
