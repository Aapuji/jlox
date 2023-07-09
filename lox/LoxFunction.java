package lox;

import java.util.List;

public class LoxFunction implements LoxCallable {
  private final Stmt.Function declaration;
  private final Environment closure;

  public LoxFunction(Stmt.Function declaration, Environment closure) {
    this.closure = closure;
    this.declaration = declaration;
  }

  @Override
  public int arity() {
    return declaration.params.size();
  }

  @Override
  public Object call(Interpreter interpreter, List<Object> arguments) {
    Environment environment = new Environment(closure);
    
    for (int i = 0; i < declaration.params.size(); i++) {
      environment.define(
        declaration
          .params
          .get(i)
          .lexeme, 
        arguments.get(i)
      );
    }

    try {
      interpreter.executeBlock(declaration.body, environment); // Executed code
    } catch (Return returnValue) { // Returns from function.
      return returnValue.value;
    }

    return null;
  }

  @Override
  public String toString() {
    return "<fun " + declaration.name.lexeme + ">";
  }
}