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
 * Checks line wrapping of given node subtree up to specified child.
 *
 * @author pirat9600q
 */
public class SubrangeLineWrappingHandler extends LineWrappingHandler
{
    /**
     * Last child to check.
     */
    private DetailAST lastChild;

    /**
     * Creates line wrapping handler to check line wrapping of specified
     * node, its direct children up to last child and their subtrees.
     * @param instance
     *     instance of IndentationCheck.
     * @param node
     *     node for checking.
     * @param lastChild
     *     the last child of specified node which must be checked.
     */
    public SubrangeLineWrappingHandler(IndentationCheck instance,
        DetailAST node, DetailAST lastChild)
    {
        super(instance, node);
        this.lastChild = lastChild;
    }

    @Override
    public void checkIndentation()
    {
        final List<DetailAST> toplevelNodes = getNodeChildrenUpToLastNode(getBaseNode(), lastChild);
        checkSubtreesIndentation(toplevelNodes);
    }

    /**
     * Returns all node children up to last child.
     * @param node
     *     node to collect children.
     * @param lastChild
     *     last node child.
     * @return list of node children.
     */
    private static List<DetailAST> getNodeChildrenUpToLastNode(DetailAST node, DetailAST lastChild)
    {
        final List<DetailAST> children = getNodeChildren(node);
        final int lastNodeIndex = children.indexOf(lastChild);
        if (lastNodeIndex >= 0) {
            return children.subList(0, lastNodeIndex + 1);
        }
        else {
            return children;
        }
    }
}
