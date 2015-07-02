package Brainterpreter;

public class BrainHelper {
  public static String MakeChar(char c) {
    String code = "";
    for (int i = 0; i < (int)c; ++i) {
      code += '+';
    }
    return code;
  }
  public static String MakeString(String s) {
    String code = "";
    for (int i = 0; i < s.length(); ++i) {
      code += MakeChar(s.charAt(i)) + '>';
    }
    return code;
  }
  public static String MakePrintString(String s) {
    String code = MakeString(s);
    for (int i = 0; i < s.length(); ++i) {
      code += '<';
    }
    for (int i = 0; i < s.length() - 1; ++i) {
      code += ".>";
    }
    return code + '.';
  }
}
