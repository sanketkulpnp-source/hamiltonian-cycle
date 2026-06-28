import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HamiltonianCycle {
    static int n=50; // total vertices
    static int sum = n*(n-1)/2; //since vertices start from 0
    static int graph[][] = new int[n][n];
    static boolean[][][] visited = new boolean[n+1][sum+1][sum+1];
    static List<ArrayList<int[]>> edges = new ArrayList<>();
    static class State{
        int last, left, right;
        ArrayList<Integer> ans; // answer

        public State(int last, int left, int right, ArrayList<Integer> ans) {
            this.last = last;
            this.left = left;
            this.right = right;
            this.ans= ans;
        }
    }
    private static void read() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("problem.txt"))) {
            String line;
            int cnt = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals("---"))
                    line = "-1";
                int i = cnt / n;
                int j = cnt % n;
                graph[i][j] = Integer.parseInt(line);
                cnt++;
            }
        }
    }
    private static void makeEdges(){
        for (int i = 0; i < n; i++) {
            ArrayList<int[]> inner = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0) {
                    int[] tmp = new int[2];
                    tmp[0] = i;
                    tmp[1] = j;
                    inner.add(tmp);
                }
            }
            edges.add(i,inner);
        }
    }

    public static void main(String[] args) throws IOException {
        read();
        makeEdges();
        solution();
    }
    private static void solution(){
        ArrayList<ArrayList<State>> dp = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(0);
        dp.get(0).add(new State(0,0,0, tmp));

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < dp.get(i - 1).size(); j++) {
                State prev = dp.get(i-1).get(j);
                ArrayList<int[]> paths = edges.get(prev.last);
                for (int k = 0; k < paths.size(); k++) {
                    int newLeft = paths.get(k)[0] + prev.left;
                    int newRight = paths.get(k)[1] + prev.right;
                    int newLast = paths.get(k)[1];

                    if (newRight>sum){
                        break;
                    }
                    if (newLeft != newRight && !visited[newLast][newLeft][newRight] && !prev.ans.contains(newLast)){
                        tmp = new ArrayList<>();
                        tmp.addAll(prev.ans);
                        tmp.add(newLast);
                        dp.get(i).add(new State(newLast,newLeft,newRight,tmp));
                        visited[newLast][newLeft][newRight] = true;
                    }
                    if (newLeft+newLast==newRight && newRight==sum && prev.ans.size()==n-1){
                        State state = dp.get(i).get(dp.get(i).size()-1);
                        if (graph[state.ans.get(0)][state.ans.get(state.ans.size()-1)]==0){
                            System.out.println("Cycle " + state.ans);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("No cycle");
    }
}
