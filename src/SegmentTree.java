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

public class SegmentTree {

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
    private final int N = 10010;

    private int[] sum = new int[4 * N];
    private int[] a = new int[N];

    public void build(int x, int sl, int sr) {
      if (sl < sr) {
        int mid = (sl + sr) >> 1;
        build(x + x, sl, mid);
        build(x + x + 1, mid + 1, sr);
        sum[x] = sum[x + x] + sum[x + x + 1];
      } else {
        sum[x] = a[sl];
      }
    }

    public int getSum(int x, int sl, int sr, int ql, int qr) {
      if (sl >= ql && sr <= qr) {
        return sum[x];
      }
      int mid = (sl + sr) >> 1;
      int totalSum = 0;
      if (ql <= mid) {
        totalSum += getSum(x + x, sl, mid, ql, qr);
      }
      if (qr > mid) {
        totalSum += getSum(x + x + 1, mid + 1, sr, ql, qr);
      }
      return totalSum;
    }

    public void modify(int x, int sl, int sr, int pos, int val) {
      if (sl < sr) {
        int mid = (sl + sr) >> 1;
        if (pos <= mid) {
          modify(x + x, sl, mid, pos, val);
        } else {
          modify(x + x + 1, mid + 1, sr, pos, val);
        }
        sum[x] = sum[x + x] + sum[x + x + 1];
      } else {
        sum[x] = val;
      }
    }

    public void solve(InputReader in, PrintWriter out) {
      int n = in.nextInt();
      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      } 
      build(1, 0, n - 1);
      int q = in.nextInt();
      while (q-- > 0) {
        int cmd = in.nextInt();
        if (cmd == 0) {
          int l = in.nextInt();
          int r = in.nextInt();
          int ans = getSum(1, 0, n - 1, l, r);
          out.println(ans);
        } else {
          int pos = in.nextInt();
          int val = in.nextInt();
          modify(1, 0, n - 1, pos, val);
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
