package lox;

import java.util.List;
import java.util.Map;

public class LoxClass implements LoxCallable {
  public final String name;

  public LoxClass(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "<class " + name + ">";
  }

  @Override
  public Object call(Interpreter interpreter, List<Object> arguments) {
    LoxInstance instance = new LoxInstance(this);
    return instance;
  }

  @Override
  public int arity() {
    return 0;
  }
}
