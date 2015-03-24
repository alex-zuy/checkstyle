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

import java.util.List;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

/**
 * Checks line wrapping of node`s children from first node to last node.
 *
 * @author lexa
 */
public class RangedLineWrapHandler extends LineWrappingHandler
{
    /**
     * First node that will be checked.
     */
    private final DetailAST firstNode;

    /**
     * Last node that will be checked.
     */
    private final DetailAST lastNode;

    /**
     * Returns first node that will be checked.
     *
     * @return first node that will be checked.
     */
    protected DetailAST getFirstNode()
    {
        return firstNode;
    }

    /**
     * Returns last node that will be checked.
     * @return last node that will be checked.
     */
    protected DetailAST getLastNode()
    {
        return lastNode;
    }

    /**
     * Creates instance of RangedLineWrapHandler.
     * @param instance of IndentationCheck.
     * @param baseNode base node from which line wrapping starts.
     * @param firstNode first child node that will be checked.
     * @param lastNode last child node that will be checked.
     */
    public RangedLineWrapHandler(IndentationCheck instance,
        DetailAST baseNode, DetailAST firstNode, DetailAST lastNode)
    {
        super(instance, baseNode);
        this.firstNode = firstNode;
        this.lastNode = lastNode;
    }

    @Override
    public void checkIndentation()
    {
        final List<DetailAST> toplevelNodes = getNodeChildrenRange(getBaseNode(),
            firstNode, lastNode);
        checkSubtreesIndentation(toplevelNodes);
    }

    /**
     * Returns node children which lie within range firstChild - lastChild.
     * @param node node whose children will be returned.
     * @param firstChild first child in range.
     * @param lastChild last child in range.
     * @return list of children which lie within range.
     */
    protected static List<DetailAST> getNodeChildrenRange(DetailAST node,
        DetailAST firstChild, DetailAST lastChild)
    {
        final List<DetailAST> children = getNodeChildren(node);

        final int firstChildIndex = children.indexOf(firstChild);
        final int lastChildIndex = children.indexOf(lastChild);

        return children.subList(firstChildIndex, lastChildIndex + 1);
    }

}
