package lox;

import java.util.List;

public interface LoxCallable {
  public int arity();
  Object call(Interpreter interpreter, List<Object> arguments);
}
