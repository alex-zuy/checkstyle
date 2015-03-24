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

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Checks line wrapping indentation of subtrees which lie within parenthesis.
 *
 * @author pirat9600q
 */
public class ParenthesisLineWrappingHandler extends RangedLineWrapHandler
{
    /**
     * Creates instance of ParenthesisLineWrappingHandler.
     * @param instance instance of IndentationCheck.
     * @param baseNode base node which is parent of parenthesis.
     */
    public ParenthesisLineWrappingHandler(IndentationCheck instance, DetailAST baseNode)
    {
        super(instance, baseNode,
            baseNode.findFirstToken(TokenTypes.LPAREN),
            baseNode.findFirstToken(TokenTypes.RPAREN));
    }

    @Override
    protected int getCurrentIndentation()
    {
        return getLineStart(getFirstNode()) + getLineWrappingIndent();
    }
}
