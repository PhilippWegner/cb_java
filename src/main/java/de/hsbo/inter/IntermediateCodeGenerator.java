package de.hsbo.inter;

import java.util.ArrayList;
import java.util.List;

public class IntermediateCodeGenerator {
    private Node root;
    private List<String> intermediateCode;

    public IntermediateCodeGenerator(Node root) {
        this.root = root;
        this.intermediateCode = new ArrayList<>();
    }

    public List<String> generate() {
        generate(root);
        return intermediateCode;
    }

    private void generate(Node node) {
        if (node instanceof ValueNode) {
            ValueNode valueNode = (ValueNode) node;
            intermediateCode.add("PUSH" + "\t\t" + valueNode.value);
        } else if (node instanceof UnaryNode) {
            UnaryNode unaryNode = (UnaryNode) node;
            generate(unaryNode.right);
            intermediateCode.add("OPERATOR" + "\t" + unaryNode.operator);
        } else if (node instanceof BinaryNode) {
            BinaryNode binaryNode = (BinaryNode) node;
            generate(binaryNode.left);
            generate(binaryNode.right);
            intermediateCode.add("OPERATOR" + "\t" + binaryNode.operator);
        } 
    }

    public void print() {
        for (String line : intermediateCode) {
            System.out.println(line);
        }
    }
}
