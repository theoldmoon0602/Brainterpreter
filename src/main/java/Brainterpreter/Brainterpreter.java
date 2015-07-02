package Brainterpreter;
import java.util.*;
public class Brainterpreter {
  private ArrayList<Character> tokens;
  private HashMap<Integer, Integer> jump_points;
  private String source;

  public Brainterpreter (String src) {
    source = src;
    tokens = new ArrayList<Character>();
    jump_points = new HashMap<Integer, Integer>();
  }

  private boolean IsValidChar(char c) {
    String valid_chars = "<>[]+-.,D";
    return valid_chars.indexOf(c) != -1;
  }
  public void Tokenize() {
    int jp_begin = 0;
    for (int i = 0; i < source.length(); ++i) {
      Character c = source.charAt(i);
      if (IsValidChar(c)) {
        if (c == '[') {
          jp_begin = i;
        } else if (c == ']') {
          jump_points.put(jp_begin, i);
          jump_points.put(i, jp_begin);
        }
        tokens.add(c);
      }
    }
  }
  public void Run() {
    int pc = 0;
    int sp = 0;
    ArrayList<Integer> tape = new ArrayList<Integer>(Arrays.asList(0));
    while (true) {
      if (pc == tokens.size()) {
        return;
      }
      switch(tokens.get(pc)) {
        case '+':
          tape.set(sp, tape.get(sp) + 1);
          break;
        case '-':
          tape.set(sp, tape.get(sp) - 1);
          break;
        case '.':
          System.out.print((char)tape.get(sp).intValue());
          break;
        case ',':
          try{
            tape.set(sp, (int)System.in.read());
          }catch(Exception e){
            // kill
          }
          break;
        case '>':
          sp++;
          if (tape.size() <= sp) {
            tape.add(0);
          }
          break;
        case '<':
          sp--;
          if (sp < 0) {
            sp = 0;
          }
          break;
        case '[':
          if (tape.get(sp) == 0) {
            pc = jump_points.get(pc);
          }
          break;
        case ']':
          if (tape.get(sp) != 0) {
            pc = jump_points.get(pc) ;
          }
          break;
        case 'D':
          // My Original Implement. Dump tape.
          for (int i = 0; i < tape.size(); ++i) {
            System.out.printf("index: %2d, value: %3d\n", i, tape.get(i));
          }
        default:
          break;
      }
      pc++;
    }
  }
}
