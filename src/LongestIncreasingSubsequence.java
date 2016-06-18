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
import java.util.Collections;

public class LongestIncreasingSubsequence {

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

    // will be used to trace the path of LIS
    private int[] p = new int[N];

    // will contain the index of the element who can sit at (position i) in LIS
    private int[] id = new int[N];

    public List<Integer> getLis(List<Integer> a) {
      int n = a.size();
      List<Integer> tmpLis = new ArrayList<Integer>();
      for (int i = 0; i < n; i++) {
        int low = 0, high = (int)tmpLis.size();
        while(low < high) {
          int mid = (low + high) >> 1;
          if (a.get(i) <= tmpLis.get(mid)) {
            high = mid;
          } else {
            low = mid + 1;
          }
        }
        if (low == tmpLis.size()) {
          tmpLis.add(a.get(i));
        } else {
          tmpLis.set(low, a.get(i));
        }
        p[i] = (low > 0) ? id[low - 1] : -1;
        id[low] = i;
      }
      int sizeofTmpLis = tmpLis.size();
      int tracer = -1;
      if (tmpLis.size() > 0) {
        tracer = id[sizeofTmpLis - 1];
      }
      List<Integer> lis = new ArrayList<Integer>();
      while (tracer >= 0) {
        lis.add(a.get(tracer));
        tracer = p[tracer];
      }
      Collections.reverse(lis);
      return lis;
    }

    public void solve(InputReader in, PrintWriter out) {
      int n = in.nextInt();
      List<Integer> a = new ArrayList<Integer>();
      for (int i = 0; i < n; i++) {
        int x = in.nextInt();
        a.add(x);
      }
      List<Integer> lis = getLis(a);
      for (int z : lis) {
        out.println(z);
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
