////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2015 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////
package com.puppycrawl.tools.checkstyle.checks.indentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.Utils;

/**
 * This class checks line-wrapping into definitions and expressions. The line-wrapping indentation
 * should be not less then value of the lineWrappingIndentation parameter.
 *
 * @author maxvetrenko
 */
public abstract class LineWrappingHandler
{
    /**
     * The current instance of <code>IndentationCheck</code> class using this handler. This field
     * used to get access to private fields of IndentationCheck instance.
     */
    private final IndentationCheck indentCheck;

    /**
     * Base node from which line wrapping starts.
     */
    private final DetailAST baseNode;

    /**
     * User's value of line wrapping indentation.
     */
    private final int lineWrapIndent;

    /**
     * Should Check force strict condition in line wrapping case.
     */
    private final boolean forceStrictCondition;

    /**
     * Returns base node.
     * @return base node.
     */
    protected final DetailAST getBaseNode()
    {
        return baseNode;
    }

    /**
     * Returns line wrapping indentation.
     * @return line wrapping indentation.
     */
    protected final int getLineWrappingIndent()
    {
        return lineWrapIndent;
    }

    /**
     * Returns IndentationCheck instance.
     * @return IndentationCheck instance.
     */
    protected final IndentationCheck getIndentCheck()
    {
        return indentCheck;
    }

    /**
     * Returns "forceStrictCondition" option value.
     * @return "forceStrictCondition" option value.
     */
    protected final boolean getForceStrictCondition()
    {
        return forceStrictCondition;
    }

    /**
     * Creates LineWrappingHandler instace.
     * @param instance
     *        instance of IndentationCheck.
     * @param baseNode
     *        base node from which line wrapping starts.
     */
    public LineWrappingHandler(IndentationCheck instance, DetailAST baseNode)
    {
        indentCheck = instance;
        this.baseNode = baseNode;
        lineWrapIndent = indentCheck.getLineWrappingIndentation();
        forceStrictCondition = indentCheck.getForceStrictCondition();
    }

    /**
     * Check indentation of subtree nodes.
     */
    public abstract void checkIndentation();

    /**
     * Returns correct indentation.
     * @return correct indentation for current expression.
     */
    protected int getCurrentIndentation()
    {
        return getLineStart(baseNode) + lineWrapIndent;
    }

    /**
     * Checks indentation of specified subtrees.
     * @param subtrees
     *        list of subtrees to check.
     */
    protected void checkSubtreesIndentation(List<DetailAST> subtrees)
    {
        final SortedMap<Integer, DetailAST> firstNodesOnLines = collectFirstNodes(subtrees);
        final DetailAST firstNode = firstNodesOnLines.get(firstNodesOnLines.firstKey());
        if (firstNode.getType() == TokenTypes.AT) {
            checkAnnotationIndentation(firstNode, firstNodesOnLines);
        }

        // First node should be removed because it was already checked before.
        firstNodesOnLines.remove(firstNodesOnLines.firstKey());
        final int currentIndent = getCurrentIndentation();

        for (DetailAST node : firstNodesOnLines.values()) {
            if (isIfInsideElse(node)) {
                checkAndLog(node.getParent(), currentIndent);
            }
            else {
                if (isMethodCall(node)) {
                    node = getMethodCallStartNode(node);
                }
                checkAndLog(node, currentIndent);
            }
        }
    }

    /**
     * Checks whether node is "if" inside "else"
     * @param node to check.
     * @return true, if given node is "if" inside "else".
     */
    protected static boolean isIfInsideElse(final DetailAST node)
    {
        return node.getType() == TokenTypes.LITERAL_IF
            && node.getParent().getType() == TokenTypes.LITERAL_ELSE;
    }

    /**
     * Traverses subtrees and builds map lineNo-to-firstNodeOnLine.
     * @param subtrees
     *        list of subtrees to traverse.
     * @return lineNumber-to-firstNodeOnLine map.
     */
    private SortedMap<Integer, DetailAST> collectFirstNodes(
        List<DetailAST> subtrees)
    {
        final SortedMap<Integer, DetailAST> lineToNodeMap = new TreeMap<>();
        final Queue<DetailAST> nodesQueue = new LinkedList<>();

        lineToNodeMap.put(baseNode.getLineNo(), baseNode);
        nodesQueue.addAll(subtrees);
        while (!nodesQueue.isEmpty()) {
            final DetailAST currentNode = nodesQueue.remove();

            if (isMethodCall(currentNode)) {
                final DetailAST methodCallStartNode =
                    getMethodCallStartNode(currentNode);

                addNodeIfCloserToLineStart(lineToNodeMap, methodCallStartNode);
            }
            else if (!isExceptionalTokenType(currentNode)) {
                addNodeIfCloserToLineStart(lineToNodeMap, currentNode);
            }

            if (shouldProcessNodeSubtree(currentNode)) {
                nodesQueue.addAll(getNodeChildren(currentNode));
            }
        }
        return lineToNodeMap;
    }

    /**
     * Checks whether node is of exceptional type, so that is must not be considered when building
     * lineNo-to-firstNodeOnLine map.
     * @param node
     *        node to check.
     * @return true, if node of type TokenTypes.ARRAY_INIT.
     */
    private static boolean isExceptionalTokenType(DetailAST node)
    {
        final int type = node.getType();
        return type == TokenTypes.ARRAY_INIT;
    }

    /**
     * Checks whether node subtree must be traversed.
     * @param node
     *        node to check.
     * @return true, if line wrapping check was called for this node of if node`s type is not
     *         specially handled.
     */
    protected final boolean shouldProcessNodeSubtree(DetailAST node)
    {
        return node == baseNode || !isSpeciallyHandledType(node.getType());
    }

    /**
     * Checks whether node type is specially handled by some special handler.
     * @param type to check.
     * @return true, if given token type is handled specially.
     */
    protected final boolean isSpeciallyHandledType(final int type)
    {
        return indentCheck.getHandlerFactory().isHandledType(type);
    }

    /**
     * Get all node children.
     * @param node
     *        node to collect children for.
     * @return list of node children.
     */
    protected static List<DetailAST> getNodeChildren(DetailAST node)
    {
        if (node.getChildCount() == 0) {
            return Collections.emptyList();
        }
        else {
            final List<DetailAST> children = new ArrayList<>(node.getChildCount());

            DetailAST child = node.getFirstChild();
            while (child != null) {
                children.add(child);
                child = child.getNextSibling();
            }

            if (isMethodCall(node)) {
                children.remove(0); //remove pre-ELIST element
            }
            return children;
        }
    }

    /**
     * Checks whether node is of type TokenTypes.METHOD_CALL.
     * @param node
     *        node to check.
     * @return true, if node`s type is TokenTypes.METHOD_CALL.
     */
    private static boolean isMethodCall(DetailAST node)
    {
        return node.getType() == TokenTypes.METHOD_CALL;
    }

    /**
     * Adds node to map if it closer to line start than node already present in map.
     * @param linesToNodesMap map to add node to.
     * @param offeredNode node to add.
     */
    private static void addNodeIfCloserToLineStart(
        SortedMap<Integer, DetailAST> linesToNodesMap, DetailAST offeredNode)
    {
        final int line = offeredNode.getLineNo();
        final DetailAST firstNodeOnLine = linesToNodesMap.get(line);
        if (firstNodeOnLine == null
            || offeredNode.getColumnNo() <= firstNodeOnLine.getColumnNo())
        {
            linesToNodesMap.put(line, offeredNode);
        }
    }

    /**
     * Checks line wrapping into annotations.
     * @param atNode
     *        at-clause node.
     * @param firstNodesOnLines
     *        map which contains first nodes as values and line numbers as keys.
     */
    private void checkAnnotationIndentation(DetailAST atNode,
        SortedMap<Integer, DetailAST> firstNodesOnLines)
    {
        final int currentIndent = atNode.getColumnNo() + lineWrapIndent;
        final int firstNodeIndent = atNode.getColumnNo();
        final Collection<DetailAST> values = firstNodesOnLines.values();
        final DetailAST lastAnnotationNode = getLastAnnotationNode(atNode);
        final int lastAnnotationLine = lastAnnotationNode.getLineNo();
        final int lastAnnotattionColumn = lastAnnotationNode.getColumnNo();

        final Iterator<DetailAST> itr = values.iterator();
        while (itr.hasNext() && firstNodesOnLines.size() > 1) {
            final DetailAST node = itr.next();

            if (node.getLineNo() < lastAnnotationLine
                || node.getLineNo() == lastAnnotationLine
                && node.getColumnNo() <= lastAnnotattionColumn)
            {
                final DetailAST parentNode = node.getParent();
                if (node.getType() == TokenTypes.AT
                    && parentNode.getParent().getType() == TokenTypes.MODIFIERS)
                {
                    checkAndLog(node, firstNodeIndent);
                }
                else {
                    checkAndLog(node, currentIndent);
                }
                itr.remove();
            }
            else {
                break;
            }
        }
    }

    /**
     * Finds and returns last annotation node.
     * @param atNode
     *        first at-clause node.
     * @return last annotation node.
     */
    private DetailAST getLastAnnotationNode(DetailAST atNode)
    {
        DetailAST lastAnnotation = atNode.getParent();
        while (lastAnnotation.getNextSibling() != null
            && lastAnnotation.getNextSibling().getType() == TokenTypes.ANNOTATION)
        {
            lastAnnotation = lastAnnotation.getNextSibling();
        }
        return lastAnnotation.getLastChild();
    }

    /**
     * Logs warning message if indentation is incorrect.
     * @param currentNode
     *        current node which probably invoked an error.
     * @param correctIndent
     *        correct indentation.
     */
    protected void checkAndLog(DetailAST currentNode, int correctIndent)
    {
        if (startsLine(currentNode) && currentNode.getLineNo() != baseNode.getLineNo())
        {
            final int currenNodeIndent = getLineStart(currentNode);
            if (forceStrictCondition && currenNodeIndent != correctIndent
                || currenNodeIndent < correctIndent)
            {
                logWarningMessage(currentNode, correctIndent);
            }
        }
    }

    /**
     * Returns method call start node.
     * @param methCall
     *        method call node.
     * @return method call start node.
     */
    protected static DetailAST getMethodCallStartNode(DetailAST methCall)
    {
        DetailAST methCallFirstChild = methCall.getFirstChild();
        while (methCallFirstChild.getFirstChild() != null) {
            methCallFirstChild = methCallFirstChild.getFirstChild();
        }
        return methCallFirstChild;
    }

    /**
     * Logs warning message for given node.
     * @param currentNode
     *        node which violates indentation rules.
     * @param correctIndent
     *        correct indent for node.
     */
    protected final void logWarningMessage(DetailAST currentNode, int correctIndent)
    {
        indentCheck.indentationLog(currentNode.getLineNo(),
            "indentation.error", currentNode.getText(),
            currentNode.getColumnNo(), correctIndent);
    }

    /**
     * Determines if the given expression is at the start of a line.
     * @param ast
     *        the expression to check
     * @return true if it is, false otherwise
     */
    protected final boolean startsLine(DetailAST ast)
    {
        return getLineStart(ast) == expandedTabsColumnNo(ast);
    }

    /**
     * Get the start of the line for the given expression.
     * @param ast
     *        the expression to find the start of the line for
     * @return the start of the line for the given expression
     */
    protected final int getLineStart(DetailAST ast)
    {
        final String line = indentCheck.getLine(ast.getLineNo() - 1);
        return getLineStart(line);
    }

    /**
     * Get the start of the specified line.
     * @param line
     *        the specified line number
     * @return the start of the specified line
     */
    protected final int getLineStart(String line)
    {
        for (int start = 0; start < line.length(); start++) {
            final char c = line.charAt(start);

            if (!Character.isWhitespace(c)) {
                return Utils.lengthExpandedTabs(
                    line, start, indentCheck.getIndentationTabWidth());
            }
        }
        return 0;
    }

    /**
     * Get the column number for the start of a given expression, expanding tabs out into spaces in
     * the process.
     * @param ast
     *        the expression to find the start of
     * @return the column number for the start of the expression
     */
    protected final int expandedTabsColumnNo(DetailAST ast)
    {
        final String line =
            indentCheck.getLine(ast.getLineNo() - 1);

        return Utils.lengthExpandedTabs(line, ast.getColumnNo(),
            indentCheck.getIndentationTabWidth());
    }
}
