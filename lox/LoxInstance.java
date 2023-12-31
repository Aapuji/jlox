package lox;

import java.util.HashMap;
import java.util.Map;

public class LoxInstance {
  private LoxClass loxClass;
  private final Map<String, Object> fields = new HashMap<>();

  public LoxInstance(LoxClass loxClass) {
    this.loxClass = loxClass;
  }

  public Object get(Token name) {
    if (fields.containsKey(name.lexeme)) {
      return fields.get(name.lexeme);
    }

    throw new RuntimeError(name, "Undefined propertie '" + name.lexeme + "'.");
  }

  @Override
  public String toString() {
    return "<" + loxClass + " instance>"; 
  }
}
