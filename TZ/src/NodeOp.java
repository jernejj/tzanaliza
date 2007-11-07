
public class NodeOp extends Node{
	private Operator op;

	public NodeOp() {
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	public NodeOp(char op) {
		this.left = null;
		this.right = null;
		this.parent = null;
		this.op = new Operator(op);
	}
	
	public char getOp() {
		return op.getOp();
	}
	
	public void setLeft(Node node) {
		this.left = node;
	}
	
	public Node getLeft() {
		return this.left;
	}

	public void setRight(Node node) {
		this.right = node;
	}
	
	public Node getRight() {
		return this.right;
	}	
}
