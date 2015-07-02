import Brainterpreter.*;
class Example {
  public static void main(String[] args) {
    Brainterpreter interp = new Brainterpreter(BrainHelper.MakeString("Hello") + "<<<<<.>.>.>.>.");
    interp.Tokenize();
    interp.Run();
  }
}
