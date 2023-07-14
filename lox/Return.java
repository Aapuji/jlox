package lox;

public class Return extends UnwindAst {
  public final Object value;
  
  public Return(Token token, Object value) {
    super(token);
    this.value = value;
  }
}
