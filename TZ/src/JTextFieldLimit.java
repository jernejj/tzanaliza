 import javax.swing.text.*;
 
 public class JTextFieldLimit extends PlainDocument {
   private int limit;
   
   JTextFieldLimit(int limit) {
    super();
    this.limit = limit;
    }
  
   public void insertString
     (int offset, String  str, AttributeSet attr)
       throws BadLocationException {
    if (str == null) return;

    if ((getLength() + str.length()) <= limit && (str.equals("1") || str.equals("0")) ) {;
      super.insertString(offset, str, attr);
      }
    }
 }