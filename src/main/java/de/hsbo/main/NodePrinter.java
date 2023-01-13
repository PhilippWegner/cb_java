package de.hsbo.main;

import de.hsbo.inter.BinaryNode;
import de.hsbo.inter.Node;
import de.hsbo.inter.UnaryNode;
import de.hsbo.inter.ValueNode;

public class NodePrinter {
    private Node root;

    public NodePrinter(Node root) {
        this.root = root;
    }

    public void print() {
        print(root, "");
    }

    private void print(Node node, String indent) {
        if (node instanceof ValueNode) {
            ValueNode valueNode = (ValueNode) node;
            System.out.println(indent + valueNode.value);
        } else if (node instanceof UnaryNode) {
            UnaryNode unaryNode = (UnaryNode) node;
            System.out.println(indent + unaryNode.operator);
            print(unaryNode.right, indent + "\t");
        } else if (node instanceof BinaryNode) {
            BinaryNode binaryNode = (BinaryNode) node;
            System.out.println(indent + binaryNode.operator);
            print(binaryNode.left, indent + "\t");
            print(binaryNode.right, indent + "\t");
        }
    }
}
