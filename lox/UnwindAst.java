package lox;

public class UnwindAst extends RuntimeException {
  public final Token token;

  public UnwindAst(Token token) {
    super(null, null, false, false);
    this.token = token;
  }
}
