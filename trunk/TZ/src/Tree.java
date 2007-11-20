import java.util.*;

public class Tree {
	NodeOp root;
	public static Hashtable<String, Variable> variable = new Hashtable<String, Variable>();
	public Tree(NodeOp node) {
		this.root = node;
	}
}
