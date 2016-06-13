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
import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueImpl {

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
      // Initial capacity can't be zero in PriorityQueue will return IllegalArgument Exception
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
        @Override
        public int compare(Integer x, Integer y) {
          int xx = x.intValue();
          int yy = y.intValue();
          return yy - xx;
        }
      });
      int n = in.nextInt();
      for (int i = 0; i < n; i++) {
        int x = in.nextInt();
        pq.offer(x);
      }
      for (int i = 0; i < n; i++) {
        int x = pq.remove();
        out.println(x);
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
