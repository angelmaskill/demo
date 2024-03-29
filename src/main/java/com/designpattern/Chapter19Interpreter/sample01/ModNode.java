package com.designpattern.Chapter19Interpreter.sample01;

public class ModNode extends SymbolNode {
    public ModNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return super.left.interpret() % super.right.interpret();
    }
}