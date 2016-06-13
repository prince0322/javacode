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

public class StringSearch {

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
    private int[] b = new int[N];

    public boolean naiveSearch(String text, String pattern) {
      char[] t = text.toCharArray();
      char[] p = pattern.toCharArray();
      int lent = text.length();
      int lenp = pattern.length();
      for (int i = 0; i < lent; i++) {
        boolean isMatched = true;
        for (int j = 0; j < lenp; j++) {
          if (i + j >= lent ||  p[j] != t[i + j]) {
            isMatched = false;
          }
        }
        if (isMatched) {
          return true;
        }
      }
      return false;
    }

    public void kmpProcess(String pattern) {
      char[] p = pattern.toCharArray();
      int lenp = pattern.length();
      b[0] = -1;
      int i = 0, k = -1;
      while (i < lenp) {
        while (k >= 0 && p[k] != p[i]) {
          k = b[k];
        }
        i++;
        k++;
        b[i] = k;
      }
    }

    public ArrayList<Integer> kmpSearch(String text, String pattern) {
      char[] p = pattern.toCharArray();
      char[] t = text.toCharArray();
      int lent = text.length();
      int lenp = pattern.length();
      kmpProcess(pattern);
      int i = 0, k = 0;
      ArrayList<Integer> ret = new ArrayList<Integer>();
      while (i < lent) {
        while (k >= 0 && p[k] != t[i]) {
          k = b[k];
        }
        i++;
        k++;
        if (k == lenp) {
          ret.add(i - lenp);
          k = b[k];
        }
      }
      return ret;
    }

    public void solve(InputReader in, PrintWriter out) {
      String text = in.next();
      String pattern = in.next();
      if (naiveSearch(text, pattern)) {
        out.println("YES");
      } else {
        out.println("NO");
      }
      ArrayList<Integer> matchedPos = kmpSearch(text, pattern);
      for (int i = 0; i < matchedPos.size(); i++) {
        out.println(matchedPos.get(i));
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
