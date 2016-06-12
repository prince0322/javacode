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
import java.util.Map;
import java.util.TreeMap;

public class Mapper {

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
    public void solve(InputReader in, PrintWriter out) {
      int n = in.nextInt();
      Map<Integer, Integer> cnt = new TreeMap<Integer, Integer>();
      for (int i = 0; i < n; i++) {
        int x = in.nextInt();
        if (cnt.containsKey(x)) {
          cnt.put(x, cnt.get(x) + 1);
        } else {
          cnt.put(x, 1);
        } 
      }
      int q = in.nextInt();
      for (int i = 0; i < q; i++) {
        int x = in.nextInt();
        if (cnt.containsKey(x)) {
          out.println(cnt.get(x));
        } else {
          out.println(0);
        }
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
