import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

public class Dfs {

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;
    InputReader in = new InputReader(inputStream);
    PrintWriter out = new PrintWriter(outputStream);
    MainTask solver = new MainTask();
    solver.solve(in, out);
    out.close();
  }

  static class MainTask {
    private static final int N = 100010;
    private static int[] mark = new int[N];
    private static int[] st = new int[N];
    private static int[] ft = new int[N];
    private static int timer = 0;
    private List<List<Integer>> g = new ArrayList<List<Integer>>();

    public void dfs(int cur) {
      mark[cur] = 1;
      st[cur] = timer++;
      for (int i = 0; i < g.get(cur).size(); i++) {
        int nbr = g.get(cur).get(i);
        if (mark[nbr] == 0) {
          dfs(nbr);
        }
      }
      ft[cur] = timer++;
    }

    public void solve(InputReader in, PrintWriter out) {
      int n = in.nextInt();
      int m = in.nextInt();
      for (int i = 0; i < n; i++) {
        g.add(new ArrayList<Integer>());
      }
      for (int i = 0; i < m; i++) {
        int u = in.nextInt();
        int v = in.nextInt();
        g.get(u).add(v);
        g.get(v).add(u);
      }
      for (int i = 0; i < n; i++) {
        if (mark[i] == 0) {
          dfs(i);
        }
      }
      for (int i = 0; i < n; i++) {
        out.println(i + " " + st[i] + " " + ft[i]);
      }
    }
  }

  static class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), 32768);
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
