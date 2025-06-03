//Approach-1 (Using DFS)
//T.C : O(n), where n =  number of boxes, we don't visit any box more than once
//S.C : O(n)
class Solution {

    public int dfs(int box, int[] status, int[] candies,
                   int[][] keys, int[][] containedBoxes,
                   Set<Integer> visited, Set<Integer> foundBoxes) {
        
        if (visited.contains(box)) {
            return 0;
        }

        if (status[box] == 0) {
            foundBoxes.add(box);
            return 0;
        }

        visited.add(box);
        int candiesCollected = candies[box];

        for (int innerBox : containedBoxes[box]) {
            candiesCollected += dfs(innerBox, status, candies, keys, containedBoxes, visited, foundBoxes);
        }

        for (int boxKey : keys[box]) {
            status[boxKey] = 1; // mark as openable
            if (foundBoxes.contains(boxKey)) {
                candiesCollected += dfs(boxKey, status, candies, keys, containedBoxes, visited, foundBoxes);
            }
        }

        return candiesCollected;
    }
  public int maxCandies(int[] status, int[] candies,
                          int[][] keys, int[][] containedBoxes,
                          int[] initialBoxes) {

        int candiesCollected = 0;
        Set<Integer> visited = new HashSet<>();
        Set<Integer> foundBoxes = new HashSet<>();

        for (int box : initialBoxes) {
            candiesCollected += dfs(box, status, candies, keys, containedBoxes, visited, foundBoxes);
        }

        return candiesCollected;
    }
}

//Approach-2 (Using BFS)
//T.C : O(n), where n =  number of boxes, we don't visit any box more than once
//S.C : O(n)
import java.util.*;

class Solution {
    public int maxCandies(int[] status, int[] candies,
                          int[][] keys, int[][] containedBoxes,
                          int[] initialBoxes) {
        
        int candiesCollected = 0;
        Set<Integer> visited = new HashSet<>();
        Set<Integer> foundBoxes = new HashSet<>();
        Queue<Integer> que = new LinkedList<>();

        for (int box : initialBoxes) {
            foundBoxes.add(box);
            if (status[box] == 1) {
                que.offer(box);
                visited.add(box);
                candiesCollected += candies[box];
            }
        }

        while (!que.isEmpty()) {
            int box = que.poll();

            // Process contained boxes
            for (int innerBox : containedBoxes[box]) {
                foundBoxes.add(innerBox);
                if (status[innerBox] == 1 && !visited.contains(innerBox)) {
                    que.offer(innerBox);
                    visited.add(innerBox);
                    candiesCollected += candies[innerBox];
                }
            }

            // Process keys
            for (int boxKey : keys[box]) {
                status[boxKey] = 1; // mark as openable
                if (foundBoxes.contains(boxKey) && !visited.contains(boxKey)) {
                    que.offer(boxKey);
                    visited.add(boxKey);
                    candiesCollected += candies[boxKey];
                }
            }
        }

        return candiesCollected;
    }
}
