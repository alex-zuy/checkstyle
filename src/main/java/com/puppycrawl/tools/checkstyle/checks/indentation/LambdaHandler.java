package com.puppycrawl.tools.checkstyle.checks.indentation;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LambdaHandler extends BlockParentHandler
{
    public LambdaHandler(IndentationCheck instance,
        DetailAST lambda,
        ExpressionHandler parent)
    {
        super(instance,"lambda", lambda, parent);
    }

    @Override
    public IndentLevel getLevelImpl()
    {
        DetailAST firstToken = getFirstToken(getMainAst());
        
        if(startsLine(firstToken))
        {
            return new IndentLevel(getParent().getLevel(), getIndentCheck().getLineWrappingIndentation());
        }
        else
        {
            return getParent().getLevel();
        }
    }

    @Override
    public void checkIndentation()
    {
        DetailAST leftParen = getLParen();
        DetailAST rightParen = getRParen();
        
        LineWrappingHandler lineWrap =
            new LineWrappingHandler(getIndentCheck(), getMainAst().getFirstChild(), getMainAst());
        
        lineWrap.checkIndentation();
        
//        if(leftParen != null && rightParen != null)
//        {
//            LineWrappingHandler lineWrapHandler =
//                new LineWrappingHandler(getIndentCheck(), leftParen, rightParen);
//            lineWrapHandler.checkIndentation();
//        }
//        else
//        {
//            DetailAST lambdaArgument = getMainAst().findFirstToken(TokenTypes.IDENT);
//            
//            if(!getLevel().accept(lambdaArgument.getColumnNo()))
//            {
//                logError(lambdaArgument, " argument",
//                    lambdaArgument.getColumnNo(), getLevel());
//            }
//        }
//        
//        DetailAST lambdaBody = getMainAst().findFirstToken(TokenTypes.EXPR);
//        if(lambdaBody != null)
//        {
//            LineWrappingHandler lineWrap = new LineWrappingHandler(getIndentCheck(), lambdaBody, lambdaBody);
//            lineWrap.checkIndentation();
////            checkExpressionSubtree(lambdaBody,
////                new IndentLevel(
////                    getLevel(), getIndentCheck().getLineWrappingIndentation()),
////                true, false);
//        }
    }
}
