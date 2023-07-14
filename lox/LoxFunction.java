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
    } catch (UnwindAst unwound) { // Returns from function.
      if (unwound instanceof Return) {  
        return ((Return) unwound).value;
      }

      if (unwound instanceof Break) {
        Lox.runtimeError(new RuntimeError(unwound.token, "Break statements must be within a loop."));
      }
    }

    return null;
  }

  @Override
  public String toString() {
    return "<fun " + declaration.name.lexeme + ">";
  }
}