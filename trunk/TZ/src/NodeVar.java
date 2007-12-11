
public class NodeVar extends Node{
	private Variable var;

	public NodeVar() {
		this.left = null;
		this.right = null;
	}

	public NodeVar(String var) {
		this.left = null;
		this.right = null;
		this.var = new Variable(var);
	}
	
	public NodeVar(Variable var){
		this.left = null;
		this.right = null;
		this.var = var;
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
}
