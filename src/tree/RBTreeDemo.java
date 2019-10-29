package tree;



public class RBTreeDemo {
	public static void main(String[] args) {
		RBTree tree = new RBTree();
		int arr[]= {10,11,7,6,8,9};
		for(int i=0;i<arr.length;i++) {
			tree.add(arr[i]);
		}
		tree.root.infixOrder();
	}
}
class RBTree{
	RbNode root;
	private RbNode getParent(RbNode node) {
		return root.searchParent(node);
	}
	public void add(int value) {
		if(root==null) {
			root=new RbNode(value);
		}else {
			RbNode node = new RbNode(value);
			root.add(node);
			afterInsert(node);
			root.color="black";
		}
	}
	public void RightRotate(RbNode node) {
		RbNode newNode = new RbNode(node.value);
		newNode.color=node.color;
		newNode.left=node.left.right;
		newNode.right=node.right;
		node.value=node.left.value;
		node.left=node.left.left;
		node.right=newNode;
	}
	public void leftRotate(RbNode node) {
		RbNode newNode = new RbNode(node.value);
		newNode.color=node.color;
		newNode.left=node.left;
		newNode.right=node.right.left;
		node.right.left=newNode;
		node.value=node.right.value;
		node.right=node.right.right;
		node.left=newNode;
	}
	void afterInsert(RbNode node) {
		
		node.color="red";
		while(node!=root&&node!=null&&getParent(node)!=null&&getParent(node).color=="red"&&node.color.equals("red")) {
			
			RbNode grand=getParent(getParent(node));
			if(grand==null) {
				break;
			}
			RbNode uncle=null;
			if(grand.left==getParent(node)) {
				uncle= grand.right;
			}else if(grand.right==getParent(node)){
				uncle= grand.left;
			}
			if(uncle.color.equals("red")) {
				getParent(node).color="black";
				uncle.color="black";
				grand.color="red";
			}else {
				if(grand.left.color.equals("red")) {//如果当前节点是左节点
					if(getParent(node).right==node) {
						leftRotate(getParent(node));
					}
					String color=grand.color;
					grand.color=getParent(node).color;
					getParent(node).color=color;
					RightRotate(grand);
					
				}else {
					if(getParent(node).left==node) {
						RightRotate(getParent(node));
					}
					String color=grand.color;
					grand.color=getParent(node).color;
					getParent(node).color=color;
					leftRotate(grand);
				}
			}
			node=getParent(node);
//			if(getParent(getParent(node))==null) {
//				break;
//			}
		}
		
	}
}
class RbNode {
	public void add(RbNode node) {
		if(this!=null) {
			if(this.value>node.value) {
				if(this.left==null) {
					this.left=node;
				}else {
					this.left.add(node);
				}
				
			}else {
				if(this.right==null) {
					this.right=node;
				}else {
					this.right.add(node);
				}	
			}
		}
		
	}
	public RbNode searchParent(RbNode node) {
		if(this.right!=null&&this.right.value==node.value||(this.left!=null&&this.left.value==node.value)) {
			return this;
		}else {
			if(node.value<this.value) {
				return this.left.searchParent(node);
			}else if(node.value>this.value) {
				return this.right.searchParent(node);
			}else {
				return null;
			}
		}
	}
	
	
	String color="black";
	RbNode left;
	RbNode right;
	int value;
	public RbNode(int value) {
		super();
		this.value = value;
	}
	public void infixOrder() {
		if(this.left!=null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right!=null) {
			this.right.infixOrder();
		}
	}
	public RbNode(String color) {
		super();
		this.color = color;
	}
	@Override
	public String toString() {
		return "RbNode [color=" + color + ", value=" + value + "]";
	}
}