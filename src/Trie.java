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

public class Trie {

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
    private final static int N = 100010;
    private int[][] next = new int[N][26];
    private boolean[] isEnd = new boolean[N];
    private int donate = 0;

    public void add(String str) {
      char[] z = str.toCharArray();
      int len = str.length();
      int pos = 0;
      for (int i = 0; i < len; i++) {
        int x = (int)(z[i] - 'a');
        if (next[pos][x] == 0) {
          next[pos][x] = ++donate;
        }
        pos = next[pos][x];
      }
      isEnd[pos] = true; 
    }

    public boolean exist(String str) {
      char[] z = str.toCharArray();
      int len = str.length();
      int pos = 0;
      for (int i = 0; i < len; i++) {
        int x = (int)(z[i] - 'a');
        if (next[pos][x] == 0) {
          return false;
        }
        pos = next[pos][x];
      }
      return isEnd[pos];
    }

    public void solve(InputReader in, PrintWriter out) {
      add("ram");
      out.println(exist("sita"));
      out.println(exist("ram"));
      add("sita");
      out.println(exist("sita"));
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
