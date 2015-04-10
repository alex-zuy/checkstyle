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
import java.util.Collections;
import java.util.List;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * This class checks line wrapping for method call statements.
 *
 * @author pirat9600q
 */
public class MethodCallLineWrappingHandler extends LineWrappingHandler
{
    /**
     * Creates new instance of MethodCallLineWrapHandler.
     * @param instance
     *        instance of IndentationCheck.
     * @param methCall
     *        method call node.
     */
    public MethodCallLineWrappingHandler(IndentationCheck instance,
        DetailAST methCall)
    {
        super(instance, methCall);
    }

    @Override
    public void checkIndentation()
    {
        checkMethodCallTargetChainWrapping();
        final List<DetailAST> nodes = getNodeChildren(getBaseNode());
        nodes.remove(nodes.size() - 1);
        checkSubtreesIndentation(nodes);
    }

    /**
     * Checks method call target chain nodes.
     */
    private void checkMethodCallTargetChainWrapping()
    {
        final List<DetailAST> chain = getMethodCallTargetChain(getBaseNode());

        if (chain.size() < 2) {
            return;
        }
        else {
            final int expectedIndent = getLineStart(
                getMethodCallStartNode(getBaseNode()))
                + getLineWrappingIndent();

            chain.remove(0);
            for (DetailAST node : chain) {
                checkAndLog(node, expectedIndent);
            }
        }
    }

    @Override
    protected void checkAndLog(DetailAST currentNode, int correctIndent)
    {
        if (startsLine(currentNode)) {
            final int currenNodeIndent = currentNode.getColumnNo();
            if (getForceStrictCondition() && currenNodeIndent != correctIndent
                || currenNodeIndent < correctIndent)
            {
                logWarningMessage(currentNode, correctIndent);
            }
        }
    }

    /**
     * Returns method call target chain.
     * @param methCall
     *        method call.
     * @return list of nodes which form method call target chain.
     */
    private static List<DetailAST> getMethodCallTargetChain(DetailAST methCall)
    {
        final List<DetailAST> chain = new ArrayList<>();

        final DetailAST methCallFirstChild = methCall.getFirstChild();
        if (methCallFirstChild.getType() == TokenTypes.DOT) {
            DetailAST dotNode = methCallFirstChild;
            do {
                chain.add(dotNode.getLastChild());
                chain.add(dotNode);
                dotNode = dotNode.getFirstChild();
            }
            while (dotNode.getType() == TokenTypes.DOT);

            if (dotNode.getType() == TokenTypes.LITERAL_NEW
                && dotNode.getFirstChild().getType() == TokenTypes.IDENT)
            {
                chain.add(dotNode.getFirstChild());
            }
            else {
                chain.add(dotNode);
            }

            Collections.reverse(chain);
            return chain;
        }
        else {
            chain.add(methCallFirstChild);
            return chain;
        }
    }
}
