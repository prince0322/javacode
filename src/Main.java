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

public class Main {

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
    private final int N = 155;

    int[] content = new int[N];

    public void drop(int x, int lev, int cur, int fullContent) {
      if (cur > 0) {
        if (content[x] < fullContent) {
          if (cur >= fullContent - content[x]) {
            cur -= (fullContent - content[x]);
            content[x] = fullContent;
          }  else {
            content[x] += cur;
            cur = 0;
          }
        } else {
          int tillTotal = (cur) * (cur + 1) / 2;
          int now = x - tillTotal + lev;
          int left = now + tillTotal;
          drop(left, lev + 1, cur / 2, fullContent);
          drop(left + 1, lev + 1, cur / 2, fullContent);
        }
      }
    }

    public void solve(InputReader in, PrintWriter out) {
      int n = in.nextInt();
      int t = in.nextInt();
      int fullContent = (1 << (n - 1));
      for (int i = 0; i < t; i++) {
        drop(0, 1, fullContent, fullContent);
      }  
      int ans = 0;
      for (int i = 0; i < N; i++) {
        if (content[i] == fullContent) {
          ans++;
        }
      }
      out.println(ans);
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
