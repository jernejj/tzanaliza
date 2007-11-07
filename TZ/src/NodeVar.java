
public class NodeVar extends Node{
	private Variable var;

	public NodeVar() {
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	public NodeVar(String var) {
		this.left = null;
		this.right = null;
		this.parent = null;
		this.var = new Variable(var);
	}
	
	public String getVarName() {
		return var.getName();
	}
	
	public int	getVarValue() {
		return this.var.getValue();
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
	
	public void setParent(Node node) {
		this.parent = node;
	}
	
	public Node getParent() {
		return this.parent;
	}	
}
