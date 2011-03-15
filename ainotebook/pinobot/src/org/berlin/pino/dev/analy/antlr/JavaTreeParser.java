// $ANTLR 2.7.7 (20060906): "scripts/antlr/java15.tree.g" -> "JavaTreeParser.java"$

package org.berlin.pino.dev.analy.antlr;

import java.util.ArrayList;
import java.util.List;

import org.berlin.pino.dev.analy.metric.antlr.CompilationUnitBuilder;
import org.berlin.pino.dev.analy.metric.antlr.JavaType;
import org.berlin.pino.dev.analy.metric.antlr.Type;

import antlr.NoViableAltException;
import antlr.RecognitionException;
import antlr.collections.AST;
import antlr.collections.impl.BitSet;


import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;


public class JavaTreeParser extends antlr.TreeParser       implements JavaTreeParserTokenTypes
 {

	CompilationUnitBuilder builder;
	Type ignore;

	private String s(AST ast) {
	   return ast.getText();
	}
		 
    public CompilationUnitBuilder getBuilder() {
        return builder;
    }
    
    public void setBuilder(CompilationUnitBuilder builder) {
        this.builder = builder;
    }
	
public JavaTreeParser() {
	tokenNames = _tokenNames;
}

	public final void compilationUnit(AST _t) throws RecognitionException {
		
		AST compilationUnit_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PACKAGE_DEF:
			{
				packageDefinition(_t);
				_t = _retTree;
				break;
			}
			case 3:
			case CLASS_DEF:
			case INTERFACE_DEF:
			case IMPORT:
			case STATIC_IMPORT:
			case ENUM_DEF:
			case ANNOTATION_DEF:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			{
			_loop4:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==IMPORT||_t.getType()==STATIC_IMPORT)) {
					importDefinition(_t);
					_t = _retTree;
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			{
			_loop6:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_0.member(_t.getType()))) {
					typeDefinition(_t);
					_t = _retTree;
				}
				else {
					break _loop6;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void packageDefinition(AST _t) throws RecognitionException {
		
		AST packageDefinition_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST pkgIdent = null;
		
		try {      // for error handling
			AST __t8 = _t;
			AST tmp1_AST_in = (AST)_t;
			match(_t,PACKAGE_DEF);
			_t = _t.getFirstChild();
			annotations(_t);
			_t = _retTree;
			pkgIdent = _t==ASTNULL ? null : (AST)_t;
			identifier(_t);
			_t = _retTree;
			_t = __t8;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void importDefinition(AST _t) throws RecognitionException {
		
		AST importDefinition_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IMPORT:
			{
				AST __t10 = _t;
				AST tmp2_AST_in = (AST)_t;
				match(_t,IMPORT);
				_t = _t.getFirstChild();
				identifierStar(_t);
				_t = _retTree;
				_t = __t10;
				_t = _t.getNextSibling();
				break;
			}
			case STATIC_IMPORT:
			{
				AST __t11 = _t;
				AST tmp3_AST_in = (AST)_t;
				match(_t,STATIC_IMPORT);
				_t = _t.getFirstChild();
				identifierStar(_t);
				_t = _retTree;
				_t = __t11;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void typeDefinition(AST _t) throws RecognitionException {
		
		AST typeDefinition_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST m = null;
		AST i = null;
		
		Type extendsType;
		List<Type> impls;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case CLASS_DEF:
			{
				AST __t13 = _t;
				AST tmp4_AST_in = (AST)_t;
				match(_t,CLASS_DEF);
				_t = _t.getFirstChild();
				m = _t==ASTNULL ? null : (AST)_t;
				modifiers(_t);
				_t = _retTree;
				i = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case TYPE_PARAMETERS:
				{
					ignore=typeParameters(_t);
					_t = _retTree;
					break;
				}
				case EXTENDS_CLAUSE:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				extendsType=extendsClause(_t);
				_t = _retTree;
				impls=implementsClause(_t);
				_t = _retTree;
				
					       builder.startType(i.getLine(), s(i), extendsType, impls);
					
				objBlock(_t);
				_t = _retTree;
				_t = __t13;
				_t = _t.getNextSibling();
				builder.endType();
				break;
			}
			case INTERFACE_DEF:
			{
				AST __t15 = _t;
				AST tmp5_AST_in = (AST)_t;
				match(_t,INTERFACE_DEF);
				_t = _t.getFirstChild();
				modifiers(_t);
				_t = _retTree;
				AST tmp6_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case TYPE_PARAMETERS:
				{
					ignore=typeParameters(_t);
					_t = _retTree;
					break;
				}
				case EXTENDS_CLAUSE:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				extendsType=extendsClause(_t);
				_t = _retTree;
				interfaceBlock(_t);
				_t = _retTree;
				_t = __t15;
				_t = _t.getNextSibling();
				break;
			}
			case ENUM_DEF:
			{
				AST __t17 = _t;
				AST tmp7_AST_in = (AST)_t;
				match(_t,ENUM_DEF);
				_t = _t.getFirstChild();
				modifiers(_t);
				_t = _retTree;
				AST tmp8_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				impls=implementsClause(_t);
				_t = _retTree;
				enumBlock(_t);
				_t = _retTree;
				_t = __t17;
				_t = _t.getNextSibling();
				break;
			}
			case ANNOTATION_DEF:
			{
				AST __t18 = _t;
				AST tmp9_AST_in = (AST)_t;
				match(_t,ANNOTATION_DEF);
				_t = _t.getFirstChild();
				modifiers(_t);
				_t = _retTree;
				AST tmp10_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				annotationBlock(_t);
				_t = _retTree;
				_t = __t18;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void annotations(AST _t) throws RecognitionException {
		
		AST annotations_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t62 = _t;
			AST tmp11_AST_in = (AST)_t;
			match(_t,ANNOTATIONS);
			_t = _t.getFirstChild();
			{
			_loop64:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==ANNOTATION)) {
					annotation(_t);
					_t = _retTree;
				}
				else {
					break _loop64;
				}
				
			} while (true);
			}
			_t = __t62;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void identifier(AST _t) throws RecognitionException {
		
		AST identifier_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IDENT:
			{
				AST tmp12_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				break;
			}
			case DOT:
			{
				AST __t157 = _t;
				AST tmp13_AST_in = (AST)_t;
				match(_t,DOT);
				_t = _t.getFirstChild();
				identifier(_t);
				_t = _retTree;
				AST tmp14_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				_t = __t157;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void identifierStar(AST _t) throws RecognitionException {
		
		AST identifierStar_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IDENT:
			{
				AST tmp15_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				break;
			}
			case DOT:
			{
				AST __t159 = _t;
				AST tmp16_AST_in = (AST)_t;
				match(_t,DOT);
				_t = _t.getFirstChild();
				identifier(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case STAR:
				{
					AST tmp17_AST_in = (AST)_t;
					match(_t,STAR);
					_t = _t.getNextSibling();
					break;
				}
				case IDENT:
				{
					AST tmp18_AST_in = (AST)_t;
					match(_t,IDENT);
					_t = _t.getNextSibling();
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t159;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void modifiers(AST _t) throws RecognitionException {
		
		AST modifiers_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t57 = _t;
			AST tmp19_AST_in = (AST)_t;
			match(_t,MODIFIERS);
			_t = _t.getFirstChild();
			{
			_loop59:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_1.member(_t.getType()))) {
					modifier(_t);
					_t = _retTree;
				}
				else {
					break _loop59;
				}
				
			} while (true);
			}
			_t = __t57;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final Type  typeParameters(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST typeParameters_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t20 = _t;
			AST tmp20_AST_in = (AST)_t;
			match(_t,TYPE_PARAMETERS);
			_t = _t.getFirstChild();
			{
			int _cnt22=0;
			_loop22:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==TYPE_PARAMETER)) {
					type=typeParameter(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt22>=1 ) { break _loop22; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt22++;
			} while (true);
			}
			_t = __t20;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  extendsClause(AST _t) throws RecognitionException {
		Type type=null;
		
		AST extendsClause_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t79 = _t;
			AST tmp21_AST_in = (AST)_t;
			match(_t,EXTENDS_CLAUSE);
			_t = _t.getFirstChild();
			{
			_loop81:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==IDENT||_t.getType()==DOT)) {
					type=classOrInterfaceType(_t);
					_t = _retTree;
				}
				else {
					break _loop81;
				}
				
			} while (true);
			}
			_t = __t79;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final List<Type>  implementsClause(AST _t) throws RecognitionException {
		List<Type> impls=new ArrayList<Type>();;
		
		AST implementsClause_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		Type c;
		
		try {      // for error handling
			AST __t83 = _t;
			AST tmp22_AST_in = (AST)_t;
			match(_t,IMPLEMENTS_CLAUSE);
			_t = _t.getFirstChild();
			{
			_loop85:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==IDENT||_t.getType()==DOT)) {
					c=classOrInterfaceType(_t);
					_t = _retTree;
					impls.add(c);
				}
				else {
					break _loop85;
				}
				
			} while (true);
			}
			_t = __t83;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return impls;
	}
	
	public final void objBlock(AST _t) throws RecognitionException {
		
		AST objBlock_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t91 = _t;
			AST tmp23_AST_in = (AST)_t;
			match(_t,OBJBLOCK);
			_t = _t.getFirstChild();
			{
			_loop95:
			do {
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case CTOR_DEF:
				{
					ctorDef(_t);
					_t = _retTree;
					break;
				}
				case METHOD_DEF:
				{
					methodDef(_t);
					_t = _retTree;
					break;
				}
				case VARIABLE_DEF:
				{
					variableDef(_t);
					_t = _retTree;
					break;
				}
				case CLASS_DEF:
				case INTERFACE_DEF:
				case ENUM_DEF:
				case ANNOTATION_DEF:
				{
					typeDefinition(_t);
					_t = _retTree;
					break;
				}
				case STATIC_INIT:
				{
					AST __t93 = _t;
					AST tmp24_AST_in = (AST)_t;
					match(_t,STATIC_INIT);
					_t = _t.getFirstChild();
					slist(_t);
					_t = _retTree;
					_t = __t93;
					_t = _t.getNextSibling();
					break;
				}
				case INSTANCE_INIT:
				{
					AST __t94 = _t;
					AST tmp25_AST_in = (AST)_t;
					match(_t,INSTANCE_INIT);
					_t = _t.getFirstChild();
					slist(_t);
					_t = _retTree;
					_t = __t94;
					_t = _t.getNextSibling();
					break;
				}
				default:
				{
					break _loop95;
				}
				}
			} while (true);
			}
			_t = __t91;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void interfaceBlock(AST _t) throws RecognitionException {
		
		AST interfaceBlock_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t87 = _t;
			AST tmp26_AST_in = (AST)_t;
			match(_t,OBJBLOCK);
			_t = _t.getFirstChild();
			{
			_loop89:
			do {
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case METHOD_DEF:
				{
					methodDecl(_t);
					_t = _retTree;
					break;
				}
				case VARIABLE_DEF:
				{
					variableDef(_t);
					_t = _retTree;
					break;
				}
				case CLASS_DEF:
				case INTERFACE_DEF:
				case ENUM_DEF:
				case ANNOTATION_DEF:
				{
					typeDefinition(_t);
					_t = _retTree;
					break;
				}
				default:
				{
					break _loop89;
				}
				}
			} while (true);
			}
			_t = __t87;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void enumBlock(AST _t) throws RecognitionException {
		
		AST enumBlock_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t101 = _t;
			AST tmp27_AST_in = (AST)_t;
			match(_t,OBJBLOCK);
			_t = _t.getFirstChild();
			{
			_loop103:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==ENUM_CONSTANT_DEF)) {
					enumConstantDef(_t);
					_t = _retTree;
				}
				else {
					break _loop103;
				}
				
			} while (true);
			}
			{
			_loop107:
			do {
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case CTOR_DEF:
				{
					ctorDef(_t);
					_t = _retTree;
					break;
				}
				case METHOD_DEF:
				{
					methodDef(_t);
					_t = _retTree;
					break;
				}
				case VARIABLE_DEF:
				{
					variableDef(_t);
					_t = _retTree;
					break;
				}
				case CLASS_DEF:
				case INTERFACE_DEF:
				case ENUM_DEF:
				case ANNOTATION_DEF:
				{
					typeDefinition(_t);
					_t = _retTree;
					break;
				}
				case STATIC_INIT:
				{
					AST __t105 = _t;
					AST tmp28_AST_in = (AST)_t;
					match(_t,STATIC_INIT);
					_t = _t.getFirstChild();
					slist(_t);
					_t = _retTree;
					_t = __t105;
					_t = _t.getNextSibling();
					break;
				}
				case INSTANCE_INIT:
				{
					AST __t106 = _t;
					AST tmp29_AST_in = (AST)_t;
					match(_t,INSTANCE_INIT);
					_t = _t.getFirstChild();
					slist(_t);
					_t = _retTree;
					_t = __t106;
					_t = _t.getNextSibling();
					break;
				}
				default:
				{
					break _loop107;
				}
				}
			} while (true);
			}
			_t = __t101;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void annotationBlock(AST _t) throws RecognitionException {
		
		AST annotationBlock_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t97 = _t;
			AST tmp30_AST_in = (AST)_t;
			match(_t,OBJBLOCK);
			_t = _t.getFirstChild();
			{
			_loop99:
			do {
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case ANNOTATION_FIELD_DEF:
				{
					annotationFieldDecl(_t);
					_t = _retTree;
					break;
				}
				case VARIABLE_DEF:
				{
					variableDef(_t);
					_t = _retTree;
					break;
				}
				case CLASS_DEF:
				case INTERFACE_DEF:
				case ENUM_DEF:
				case ANNOTATION_DEF:
				{
					typeDefinition(_t);
					_t = _retTree;
					break;
				}
				default:
				{
					break _loop99;
				}
				}
			} while (true);
			}
			_t = __t97;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final Type  typeParameter(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST typeParameter_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t24 = _t;
			AST tmp31_AST_in = (AST)_t;
			match(_t,TYPE_PARAMETER);
			_t = _t.getFirstChild();
			AST tmp32_AST_in = (AST)_t;
			match(_t,IDENT);
			_t = _t.getNextSibling();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE_UPPER_BOUNDS:
			{
				type=typeUpperBounds(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t24;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  typeUpperBounds(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST typeUpperBounds_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t27 = _t;
			AST tmp33_AST_in = (AST)_t;
			match(_t,TYPE_UPPER_BOUNDS);
			_t = _t.getFirstChild();
			{
			int _cnt29=0;
			_loop29:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==IDENT||_t.getType()==DOT)) {
					type=classOrInterfaceType(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt29>=1 ) { break _loop29; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt29++;
			} while (true);
			}
			_t = __t27;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  classOrInterfaceType(AST _t) throws RecognitionException {
		Type info = null;;
		
		AST classOrInterfaceType_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		info = builder.toType(classOrInterfaceType_AST_in);
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IDENT:
			{
				AST tmp34_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case TYPE_ARGUMENTS:
				{
					ignore=typeArguments(_t);
					_t = _retTree;
					break;
				}
				case 3:
				case ARRAY_DECLARATOR:
				case ELIST:
				case IDENT:
				case DOT:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				break;
			}
			case DOT:
			{
				AST __t37 = _t;
				AST tmp35_AST_in = (AST)_t;
				match(_t,DOT);
				_t = _t.getFirstChild();
				ignore=classOrInterfaceType(_t);
				_t = _retTree;
				_t = __t37;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return info;
	}
	
	public final Type  typeSpec(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST typeSpec_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t31 = _t;
			AST tmp36_AST_in = (AST)_t;
			match(_t,TYPE);
			_t = _t.getFirstChild();
			type=typeSpecArray(_t);
			_t = _retTree;
			_t = __t31;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  typeSpecArray(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST typeSpecArray_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ARRAY_DECLARATOR:
			{
				AST __t33 = _t;
				AST tmp37_AST_in = (AST)_t;
				match(_t,ARRAY_DECLARATOR);
				_t = _t.getFirstChild();
				type=typeSpecArray(_t);
				_t = _retTree;
				_t = __t33;
				_t = _t.getNextSibling();
				break;
			}
			case IDENT:
			case DOT:
			case LITERAL_void:
			case LITERAL_boolean:
			case LITERAL_byte:
			case LITERAL_char:
			case LITERAL_short:
			case LITERAL_int:
			case LITERAL_float:
			case LITERAL_long:
			case LITERAL_double:
			{
				type=type(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  type(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST type_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IDENT:
			case DOT:
			{
				type=classOrInterfaceType(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_void:
			case LITERAL_boolean:
			case LITERAL_byte:
			case LITERAL_char:
			case LITERAL_short:
			case LITERAL_int:
			case LITERAL_float:
			case LITERAL_long:
			case LITERAL_double:
			{
				type=builtInType(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  builtInType(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST builtInType_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_void:
			{
				AST tmp38_AST_in = (AST)_t;
				match(_t,LITERAL_void);
				_t = _t.getNextSibling();
				type = JavaType.VOID;
				break;
			}
			case LITERAL_boolean:
			{
				AST tmp39_AST_in = (AST)_t;
				match(_t,LITERAL_boolean);
				_t = _t.getNextSibling();
				type = JavaType.BOOLEAN;
				break;
			}
			case LITERAL_byte:
			{
				AST tmp40_AST_in = (AST)_t;
				match(_t,LITERAL_byte);
				_t = _t.getNextSibling();
				type = JavaType.BYTE;
				break;
			}
			case LITERAL_char:
			{
				AST tmp41_AST_in = (AST)_t;
				match(_t,LITERAL_char);
				_t = _t.getNextSibling();
				type = JavaType.CHAR;
				break;
			}
			case LITERAL_short:
			{
				AST tmp42_AST_in = (AST)_t;
				match(_t,LITERAL_short);
				_t = _t.getNextSibling();
				type = JavaType.SHORT;
				break;
			}
			case LITERAL_int:
			{
				AST tmp43_AST_in = (AST)_t;
				match(_t,LITERAL_int);
				_t = _t.getNextSibling();
				type = JavaType.INT;
				break;
			}
			case LITERAL_float:
			{
				AST tmp44_AST_in = (AST)_t;
				match(_t,LITERAL_float);
				_t = _t.getNextSibling();
				type = JavaType.FLOAT;
				break;
			}
			case LITERAL_long:
			{
				AST tmp45_AST_in = (AST)_t;
				match(_t,LITERAL_long);
				_t = _t.getNextSibling();
				type = JavaType.LONG;
				break;
			}
			case LITERAL_double:
			{
				AST tmp46_AST_in = (AST)_t;
				match(_t,LITERAL_double);
				_t = _t.getNextSibling();
				type = JavaType.DOUBLE;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  typeArguments(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST typeArguments_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t39 = _t;
			AST tmp47_AST_in = (AST)_t;
			match(_t,TYPE_ARGUMENTS);
			_t = _t.getFirstChild();
			{
			int _cnt41=0;
			_loop41:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==TYPE_ARGUMENT)) {
					ignore=typeArgument(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt41>=1 ) { break _loop41; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt41++;
			} while (true);
			}
			_t = __t39;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  typeArgument(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST typeArgument_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t43 = _t;
			AST tmp48_AST_in = (AST)_t;
			match(_t,TYPE_ARGUMENT);
			_t = _t.getFirstChild();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE:
			{
				type=typeSpec(_t);
				_t = _retTree;
				break;
			}
			case WILDCARD_TYPE:
			{
				type=wildcardType(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t43;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  wildcardType(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST wildcardType_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t46 = _t;
			AST tmp49_AST_in = (AST)_t;
			match(_t,WILDCARD_TYPE);
			_t = _t.getFirstChild();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE_UPPER_BOUNDS:
			case TYPE_LOWER_BOUNDS:
			{
				type=typeArgumentBounds(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t46;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final Type  typeArgumentBounds(AST _t) throws RecognitionException {
		Type type = null;;
		
		AST typeArgumentBounds_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE_UPPER_BOUNDS:
			{
				AST __t49 = _t;
				AST tmp50_AST_in = (AST)_t;
				match(_t,TYPE_UPPER_BOUNDS);
				_t = _t.getFirstChild();
				{
				int _cnt51=0;
				_loop51:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==IDENT||_t.getType()==DOT)) {
						type=classOrInterfaceType(_t);
						_t = _retTree;
					}
					else {
						if ( _cnt51>=1 ) { break _loop51; } else {throw new NoViableAltException(_t);}
					}
					
					_cnt51++;
				} while (true);
				}
				_t = __t49;
				_t = _t.getNextSibling();
				break;
			}
			case TYPE_LOWER_BOUNDS:
			{
				AST __t52 = _t;
				AST tmp51_AST_in = (AST)_t;
				match(_t,TYPE_LOWER_BOUNDS);
				_t = _t.getFirstChild();
				{
				int _cnt54=0;
				_loop54:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==IDENT||_t.getType()==DOT)) {
						type=classOrInterfaceType(_t);
						_t = _retTree;
					}
					else {
						if ( _cnt54>=1 ) { break _loop54; } else {throw new NoViableAltException(_t);}
					}
					
					_cnt54++;
				} while (true);
				}
				_t = __t52;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final void modifier(AST _t) throws RecognitionException {
		
		AST modifier_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_private:
			{
				AST tmp52_AST_in = (AST)_t;
				match(_t,LITERAL_private);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_public:
			{
				AST tmp53_AST_in = (AST)_t;
				match(_t,LITERAL_public);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_protected:
			{
				AST tmp54_AST_in = (AST)_t;
				match(_t,LITERAL_protected);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_static:
			{
				AST tmp55_AST_in = (AST)_t;
				match(_t,LITERAL_static);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_transient:
			{
				AST tmp56_AST_in = (AST)_t;
				match(_t,LITERAL_transient);
				_t = _t.getNextSibling();
				break;
			}
			case FINAL:
			{
				AST tmp57_AST_in = (AST)_t;
				match(_t,FINAL);
				_t = _t.getNextSibling();
				break;
			}
			case ABSTRACT:
			{
				AST tmp58_AST_in = (AST)_t;
				match(_t,ABSTRACT);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_native:
			{
				AST tmp59_AST_in = (AST)_t;
				match(_t,LITERAL_native);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_threadsafe:
			{
				AST tmp60_AST_in = (AST)_t;
				match(_t,LITERAL_threadsafe);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_synchronized:
			{
				AST tmp61_AST_in = (AST)_t;
				match(_t,LITERAL_synchronized);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_const:
			{
				AST tmp62_AST_in = (AST)_t;
				match(_t,LITERAL_const);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_volatile:
			{
				AST tmp63_AST_in = (AST)_t;
				match(_t,LITERAL_volatile);
				_t = _t.getNextSibling();
				break;
			}
			case STRICTFP:
			{
				AST tmp64_AST_in = (AST)_t;
				match(_t,STRICTFP);
				_t = _t.getNextSibling();
				break;
			}
			case ANNOTATION:
			{
				annotation(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void annotation(AST _t) throws RecognitionException {
		
		AST annotation_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t66 = _t;
			AST tmp65_AST_in = (AST)_t;
			match(_t,ANNOTATION);
			_t = _t.getFirstChild();
			identifier(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE:
			case TYPECAST:
			case INDEX_OP:
			case POST_INC:
			case POST_DEC:
			case METHOD_CALL:
			case UNARY_MINUS:
			case UNARY_PLUS:
			case SUPER_CTOR_CALL:
			case CTOR_CALL:
			case ANNOTATION:
			case ANNOTATION_ARRAY_INIT:
			case IDENT:
			case DOT:
			case QUESTION:
			case LITERAL_super:
			case LT:
			case GT:
			case SR:
			case BSR:
			case STAR:
			case BAND:
			case LITERAL_this:
			case LOR:
			case LAND:
			case BOR:
			case BXOR:
			case NOT_EQUAL:
			case EQUAL:
			case LE:
			case GE:
			case LITERAL_instanceof:
			case SL:
			case PLUS:
			case MINUS:
			case DIV:
			case MOD:
			case INC:
			case DEC:
			case BNOT:
			case LNOT:
			case LITERAL_true:
			case LITERAL_false:
			case LITERAL_null:
			case LITERAL_new:
			case NUM_INT:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NUM_FLOAT:
			case NUM_LONG:
			case NUM_DOUBLE:
			{
				annotationMemberValueInitializer(_t);
				_t = _retTree;
				break;
			}
			case ANNOTATION_MEMBER_VALUE_PAIR:
			{
				{
				int _cnt69=0;
				_loop69:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==ANNOTATION_MEMBER_VALUE_PAIR)) {
						anntotationMemberValuePair(_t);
						_t = _retTree;
					}
					else {
						if ( _cnt69>=1 ) { break _loop69; } else {throw new NoViableAltException(_t);}
					}
					
					_cnt69++;
				} while (true);
				}
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t66;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void annotationMemberValueInitializer(AST _t) throws RecognitionException {
		
		AST annotationMemberValueInitializer_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE:
			case TYPECAST:
			case INDEX_OP:
			case POST_INC:
			case POST_DEC:
			case METHOD_CALL:
			case UNARY_MINUS:
			case UNARY_PLUS:
			case SUPER_CTOR_CALL:
			case CTOR_CALL:
			case IDENT:
			case DOT:
			case QUESTION:
			case LITERAL_super:
			case LT:
			case GT:
			case SR:
			case BSR:
			case STAR:
			case BAND:
			case LITERAL_this:
			case LOR:
			case LAND:
			case BOR:
			case BXOR:
			case NOT_EQUAL:
			case EQUAL:
			case LE:
			case GE:
			case LITERAL_instanceof:
			case SL:
			case PLUS:
			case MINUS:
			case DIV:
			case MOD:
			case INC:
			case DEC:
			case BNOT:
			case LNOT:
			case LITERAL_true:
			case LITERAL_false:
			case LITERAL_null:
			case LITERAL_new:
			case NUM_INT:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NUM_FLOAT:
			case NUM_LONG:
			case NUM_DOUBLE:
			{
				conditionalExpr(_t);
				_t = _retTree;
				break;
			}
			case ANNOTATION:
			{
				annotation(_t);
				_t = _retTree;
				break;
			}
			case ANNOTATION_ARRAY_INIT:
			{
				annotationMemberArrayInitializer(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void anntotationMemberValuePair(AST _t) throws RecognitionException {
		
		AST anntotationMemberValuePair_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t72 = _t;
			AST tmp66_AST_in = (AST)_t;
			match(_t,ANNOTATION_MEMBER_VALUE_PAIR);
			_t = _t.getFirstChild();
			AST tmp67_AST_in = (AST)_t;
			match(_t,IDENT);
			_t = _t.getNextSibling();
			annotationMemberValueInitializer(_t);
			_t = _retTree;
			_t = __t72;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void conditionalExpr(AST _t) throws RecognitionException {
		
		AST conditionalExpr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case QUESTION:
			{
				AST __t228 = _t;
				AST tmp68_AST_in = (AST)_t;
				match(_t,QUESTION);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t228;
				_t = _t.getNextSibling();
				break;
			}
			case LOR:
			{
				AST __t229 = _t;
				AST tmp69_AST_in = (AST)_t;
				match(_t,LOR);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t229;
				_t = _t.getNextSibling();
				break;
			}
			case LAND:
			{
				AST __t230 = _t;
				AST tmp70_AST_in = (AST)_t;
				match(_t,LAND);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t230;
				_t = _t.getNextSibling();
				break;
			}
			case BOR:
			{
				AST __t231 = _t;
				AST tmp71_AST_in = (AST)_t;
				match(_t,BOR);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t231;
				_t = _t.getNextSibling();
				break;
			}
			case BXOR:
			{
				AST __t232 = _t;
				AST tmp72_AST_in = (AST)_t;
				match(_t,BXOR);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t232;
				_t = _t.getNextSibling();
				break;
			}
			case BAND:
			{
				AST __t233 = _t;
				AST tmp73_AST_in = (AST)_t;
				match(_t,BAND);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t233;
				_t = _t.getNextSibling();
				break;
			}
			case NOT_EQUAL:
			{
				AST __t234 = _t;
				AST tmp74_AST_in = (AST)_t;
				match(_t,NOT_EQUAL);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t234;
				_t = _t.getNextSibling();
				break;
			}
			case EQUAL:
			{
				AST __t235 = _t;
				AST tmp75_AST_in = (AST)_t;
				match(_t,EQUAL);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t235;
				_t = _t.getNextSibling();
				break;
			}
			case LT:
			{
				AST __t236 = _t;
				AST tmp76_AST_in = (AST)_t;
				match(_t,LT);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t236;
				_t = _t.getNextSibling();
				break;
			}
			case GT:
			{
				AST __t237 = _t;
				AST tmp77_AST_in = (AST)_t;
				match(_t,GT);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t237;
				_t = _t.getNextSibling();
				break;
			}
			case LE:
			{
				AST __t238 = _t;
				AST tmp78_AST_in = (AST)_t;
				match(_t,LE);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t238;
				_t = _t.getNextSibling();
				break;
			}
			case GE:
			{
				AST __t239 = _t;
				AST tmp79_AST_in = (AST)_t;
				match(_t,GE);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t239;
				_t = _t.getNextSibling();
				break;
			}
			case SL:
			{
				AST __t240 = _t;
				AST tmp80_AST_in = (AST)_t;
				match(_t,SL);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t240;
				_t = _t.getNextSibling();
				break;
			}
			case SR:
			{
				AST __t241 = _t;
				AST tmp81_AST_in = (AST)_t;
				match(_t,SR);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t241;
				_t = _t.getNextSibling();
				break;
			}
			case BSR:
			{
				AST __t242 = _t;
				AST tmp82_AST_in = (AST)_t;
				match(_t,BSR);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t242;
				_t = _t.getNextSibling();
				break;
			}
			case PLUS:
			{
				AST __t243 = _t;
				AST tmp83_AST_in = (AST)_t;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t243;
				_t = _t.getNextSibling();
				break;
			}
			case MINUS:
			{
				AST __t244 = _t;
				AST tmp84_AST_in = (AST)_t;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t244;
				_t = _t.getNextSibling();
				break;
			}
			case DIV:
			{
				AST __t245 = _t;
				AST tmp85_AST_in = (AST)_t;
				match(_t,DIV);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t245;
				_t = _t.getNextSibling();
				break;
			}
			case MOD:
			{
				AST __t246 = _t;
				AST tmp86_AST_in = (AST)_t;
				match(_t,MOD);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t246;
				_t = _t.getNextSibling();
				break;
			}
			case STAR:
			{
				AST __t247 = _t;
				AST tmp87_AST_in = (AST)_t;
				match(_t,STAR);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t247;
				_t = _t.getNextSibling();
				break;
			}
			case INC:
			{
				AST __t248 = _t;
				AST tmp88_AST_in = (AST)_t;
				match(_t,INC);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				_t = __t248;
				_t = _t.getNextSibling();
				break;
			}
			case DEC:
			{
				AST __t249 = _t;
				AST tmp89_AST_in = (AST)_t;
				match(_t,DEC);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				_t = __t249;
				_t = _t.getNextSibling();
				break;
			}
			case POST_INC:
			{
				AST __t250 = _t;
				AST tmp90_AST_in = (AST)_t;
				match(_t,POST_INC);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				_t = __t250;
				_t = _t.getNextSibling();
				break;
			}
			case POST_DEC:
			{
				AST __t251 = _t;
				AST tmp91_AST_in = (AST)_t;
				match(_t,POST_DEC);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				_t = __t251;
				_t = _t.getNextSibling();
				break;
			}
			case BNOT:
			{
				AST __t252 = _t;
				AST tmp92_AST_in = (AST)_t;
				match(_t,BNOT);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				_t = __t252;
				_t = _t.getNextSibling();
				break;
			}
			case LNOT:
			{
				AST __t253 = _t;
				AST tmp93_AST_in = (AST)_t;
				match(_t,LNOT);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				_t = __t253;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_instanceof:
			{
				AST __t254 = _t;
				AST tmp94_AST_in = (AST)_t;
				match(_t,LITERAL_instanceof);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t254;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_MINUS:
			{
				AST __t255 = _t;
				AST tmp95_AST_in = (AST)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				_t = __t255;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_PLUS:
			{
				AST __t256 = _t;
				AST tmp96_AST_in = (AST)_t;
				match(_t,UNARY_PLUS);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				_t = __t256;
				_t = _t.getNextSibling();
				break;
			}
			case TYPE:
			case TYPECAST:
			case INDEX_OP:
			case METHOD_CALL:
			case SUPER_CTOR_CALL:
			case CTOR_CALL:
			case IDENT:
			case DOT:
			case LITERAL_super:
			case LITERAL_this:
			case LITERAL_true:
			case LITERAL_false:
			case LITERAL_null:
			case LITERAL_new:
			case NUM_INT:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NUM_FLOAT:
			case NUM_LONG:
			case NUM_DOUBLE:
			{
				primaryExpression(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void annotationMemberArrayInitializer(AST _t) throws RecognitionException {
		
		AST annotationMemberArrayInitializer_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t74 = _t;
			AST tmp97_AST_in = (AST)_t;
			match(_t,ANNOTATION_ARRAY_INIT);
			_t = _t.getFirstChild();
			{
			_loop76:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_2.member(_t.getType()))) {
					annotationMemberArrayValueInitializer(_t);
					_t = _retTree;
				}
				else {
					break _loop76;
				}
				
			} while (true);
			}
			_t = __t74;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void annotationMemberArrayValueInitializer(AST _t) throws RecognitionException {
		
		AST annotationMemberArrayValueInitializer_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE:
			case TYPECAST:
			case INDEX_OP:
			case POST_INC:
			case POST_DEC:
			case METHOD_CALL:
			case UNARY_MINUS:
			case UNARY_PLUS:
			case SUPER_CTOR_CALL:
			case CTOR_CALL:
			case IDENT:
			case DOT:
			case QUESTION:
			case LITERAL_super:
			case LT:
			case GT:
			case SR:
			case BSR:
			case STAR:
			case BAND:
			case LITERAL_this:
			case LOR:
			case LAND:
			case BOR:
			case BXOR:
			case NOT_EQUAL:
			case EQUAL:
			case LE:
			case GE:
			case LITERAL_instanceof:
			case SL:
			case PLUS:
			case MINUS:
			case DIV:
			case MOD:
			case INC:
			case DEC:
			case BNOT:
			case LNOT:
			case LITERAL_true:
			case LITERAL_false:
			case LITERAL_null:
			case LITERAL_new:
			case NUM_INT:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NUM_FLOAT:
			case NUM_LONG:
			case NUM_DOUBLE:
			{
				conditionalExpr(_t);
				_t = _retTree;
				break;
			}
			case ANNOTATION:
			{
				annotation(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void methodDecl(AST _t) throws RecognitionException {
		
		AST methodDecl_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST z = null;
		
		try {      // for error handling
			AST __t113 = _t;
			AST tmp98_AST_in = (AST)_t;
			match(_t,METHOD_DEF);
			_t = _t.getFirstChild();
			z = _t==ASTNULL ? null : (AST)_t;
			modifiers(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE_PARAMETERS:
			{
				ignore=typeParameters(_t);
				_t = _retTree;
				break;
			}
			case TYPE:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			ignore=typeSpec(_t);
			_t = _retTree;
			methodHead(_t);
			_t = _retTree;
			_t = __t113;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void variableDef(AST _t) throws RecognitionException {
		
		AST variableDef_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST m = null;
		AST v = null;
		AST i = null;
		Type type;
		
		try {      // for error handling
			AST __t120 = _t;
			AST tmp99_AST_in = (AST)_t;
			match(_t,VARIABLE_DEF);
			_t = _t.getFirstChild();
			m = _t==ASTNULL ? null : (AST)_t;
			modifiers(_t);
			_t = _retTree;
			type=typeSpec(_t);
			_t = _retTree;
			v = _t==ASTNULL ? null : (AST)_t;
			variableDeclarator(_t);
			_t = _retTree;
			i = _t==ASTNULL ? null : (AST)_t;
			varInitializer(_t);
			_t = _retTree;
			_t = __t120;
			_t = _t.getNextSibling();
			builder.type.addField(s(v), type, builder.visibility(m), builder.isStatic(m), builder.isFinal(m));
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void ctorDef(AST _t) throws RecognitionException {
		
		AST ctorDef_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t109 = _t;
			AST tmp100_AST_in = (AST)_t;
			match(_t,CTOR_DEF);
			_t = _t.getFirstChild();
			modifiers(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE_PARAMETERS:
			{
				ignore=typeParameters(_t);
				_t = _retTree;
				break;
			}
			case IDENT:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			methodHead(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case SLIST:
			{
				slist(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t109;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void methodDef(AST _t) throws RecognitionException {
		
		AST methodDef_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST z = null;
		
		try {      // for error handling
			AST __t116 = _t;
			AST tmp101_AST_in = (AST)_t;
			match(_t,METHOD_DEF);
			_t = _t.getFirstChild();
			z = _t==ASTNULL ? null : (AST)_t;
			modifiers(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE_PARAMETERS:
			{
				ignore=typeParameters(_t);
				_t = _retTree;
				break;
			}
			case TYPE:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			ignore=typeSpec(_t);
			_t = _retTree;
			methodHead(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case SLIST:
			{
				slist(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t116;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void slist(AST _t) throws RecognitionException {
		
		AST slist_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t162 = _t;
			AST tmp102_AST_in = (AST)_t;
			match(_t,SLIST);
			_t = _t.getFirstChild();
			{
			_loop164:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_3.member(_t.getType()))) {
					stat(_t);
					_t = _retTree;
				}
				else {
					break _loop164;
				}
				
			} while (true);
			}
			_t = __t162;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void annotationFieldDecl(AST _t) throws RecognitionException {
		
		AST annotationFieldDecl_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t126 = _t;
			AST tmp103_AST_in = (AST)_t;
			match(_t,ANNOTATION_FIELD_DEF);
			_t = _t.getFirstChild();
			modifiers(_t);
			_t = _retTree;
			ignore=typeSpec(_t);
			_t = _retTree;
			AST tmp104_AST_in = (AST)_t;
			match(_t,IDENT);
			_t = _t.getNextSibling();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE:
			case TYPECAST:
			case INDEX_OP:
			case POST_INC:
			case POST_DEC:
			case METHOD_CALL:
			case UNARY_MINUS:
			case UNARY_PLUS:
			case SUPER_CTOR_CALL:
			case CTOR_CALL:
			case ANNOTATION:
			case ANNOTATION_ARRAY_INIT:
			case IDENT:
			case DOT:
			case QUESTION:
			case LITERAL_super:
			case LT:
			case GT:
			case SR:
			case BSR:
			case STAR:
			case BAND:
			case LITERAL_this:
			case LOR:
			case LAND:
			case BOR:
			case BXOR:
			case NOT_EQUAL:
			case EQUAL:
			case LE:
			case GE:
			case LITERAL_instanceof:
			case SL:
			case PLUS:
			case MINUS:
			case DIV:
			case MOD:
			case INC:
			case DEC:
			case BNOT:
			case LNOT:
			case LITERAL_true:
			case LITERAL_false:
			case LITERAL_null:
			case LITERAL_new:
			case NUM_INT:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NUM_FLOAT:
			case NUM_LONG:
			case NUM_DOUBLE:
			{
				annotationMemberValueInitializer(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t126;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void enumConstantDef(AST _t) throws RecognitionException {
		
		AST enumConstantDef_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t129 = _t;
			AST tmp105_AST_in = (AST)_t;
			match(_t,ENUM_CONSTANT_DEF);
			_t = _t.getFirstChild();
			annotations(_t);
			_t = _retTree;
			AST tmp106_AST_in = (AST)_t;
			match(_t,IDENT);
			_t = _t.getNextSibling();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ELIST:
			{
				elist(_t);
				_t = _retTree;
				break;
			}
			case 3:
			case OBJBLOCK:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case OBJBLOCK:
			{
				enumConstantBlock(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t129;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void methodHead(AST _t) throws RecognitionException {
		
		AST methodHead_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST tmp107_AST_in = (AST)_t;
			match(_t,IDENT);
			_t = _t.getNextSibling();
			AST __t148 = _t;
			AST tmp108_AST_in = (AST)_t;
			match(_t,PARAMETERS);
			_t = _t.getFirstChild();
			{
			_loop150:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==PARAMETER_DEF)) {
					parameterDef(_t);
					_t = _retTree;
				}
				else {
					break _loop150;
				}
				
			} while (true);
			}
			_t = __t148;
			_t = _t.getNextSibling();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_throws:
			{
				throwsClause(_t);
				_t = _retTree;
				break;
			}
			case 3:
			case SLIST:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void variableDeclarator(AST _t) throws RecognitionException {
		
		AST variableDeclarator_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IDENT:
			{
				AST tmp109_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				break;
			}
			case LBRACK:
			{
				AST tmp110_AST_in = (AST)_t;
				match(_t,LBRACK);
				_t = _t.getNextSibling();
				variableDeclarator(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void varInitializer(AST _t) throws RecognitionException {
		
		AST varInitializer_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ASSIGN:
			{
				AST __t141 = _t;
				AST tmp111_AST_in = (AST)_t;
				match(_t,ASSIGN);
				_t = _t.getFirstChild();
				initializer(_t);
				_t = _retTree;
				_t = __t141;
				_t = _t.getNextSibling();
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void parameterDef(AST _t) throws RecognitionException {
		
		AST parameterDef_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t122 = _t;
			AST tmp112_AST_in = (AST)_t;
			match(_t,PARAMETER_DEF);
			_t = _t.getFirstChild();
			modifiers(_t);
			_t = _retTree;
			ignore=typeSpec(_t);
			_t = _retTree;
			AST tmp113_AST_in = (AST)_t;
			match(_t,IDENT);
			_t = _t.getNextSibling();
			_t = __t122;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void variableLengthParameterDef(AST _t) throws RecognitionException {
		
		AST variableLengthParameterDef_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t124 = _t;
			AST tmp114_AST_in = (AST)_t;
			match(_t,VARIABLE_PARAMETER_DEF);
			_t = _t.getFirstChild();
			modifiers(_t);
			_t = _retTree;
			ignore=typeSpec(_t);
			_t = _retTree;
			AST tmp115_AST_in = (AST)_t;
			match(_t,IDENT);
			_t = _t.getNextSibling();
			_t = __t124;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void elist(AST _t) throws RecognitionException {
		
		AST elist_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t209 = _t;
			AST tmp116_AST_in = (AST)_t;
			match(_t,ELIST);
			_t = _t.getFirstChild();
			{
			_loop211:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==EXPR)) {
					expression(_t);
					_t = _retTree;
				}
				else {
					break _loop211;
				}
				
			} while (true);
			}
			_t = __t209;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void enumConstantBlock(AST _t) throws RecognitionException {
		
		AST enumConstantBlock_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t133 = _t;
			AST tmp117_AST_in = (AST)_t;
			match(_t,OBJBLOCK);
			_t = _t.getFirstChild();
			{
			_loop136:
			do {
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case METHOD_DEF:
				{
					methodDef(_t);
					_t = _retTree;
					break;
				}
				case VARIABLE_DEF:
				{
					variableDef(_t);
					_t = _retTree;
					break;
				}
				case CLASS_DEF:
				case INTERFACE_DEF:
				case ENUM_DEF:
				case ANNOTATION_DEF:
				{
					typeDefinition(_t);
					_t = _retTree;
					break;
				}
				case INSTANCE_INIT:
				{
					AST __t135 = _t;
					AST tmp118_AST_in = (AST)_t;
					match(_t,INSTANCE_INIT);
					_t = _t.getFirstChild();
					slist(_t);
					_t = _retTree;
					_t = __t135;
					_t = _t.getNextSibling();
					break;
				}
				default:
				{
					break _loop136;
				}
				}
			} while (true);
			}
			_t = __t133;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void objectinitializer(AST _t) throws RecognitionException {
		
		AST objectinitializer_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t138 = _t;
			AST tmp119_AST_in = (AST)_t;
			match(_t,INSTANCE_INIT);
			_t = _t.getFirstChild();
			slist(_t);
			_t = _retTree;
			_t = __t138;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void initializer(AST _t) throws RecognitionException {
		
		AST initializer_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case EXPR:
			{
				expression(_t);
				_t = _retTree;
				break;
			}
			case ARRAY_INIT:
			{
				arrayInitializer(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void expression(AST _t) throws RecognitionException {
		
		AST expression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t213 = _t;
			AST tmp120_AST_in = (AST)_t;
			match(_t,EXPR);
			_t = _t.getFirstChild();
			expr(_t);
			_t = _retTree;
			_t = __t213;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void arrayInitializer(AST _t) throws RecognitionException {
		
		AST arrayInitializer_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t144 = _t;
			AST tmp121_AST_in = (AST)_t;
			match(_t,ARRAY_INIT);
			_t = _t.getFirstChild();
			{
			_loop146:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==EXPR||_t.getType()==ARRAY_INIT)) {
					initializer(_t);
					_t = _retTree;
				}
				else {
					break _loop146;
				}
				
			} while (true);
			}
			_t = __t144;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void throwsClause(AST _t) throws RecognitionException {
		
		AST throwsClause_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t153 = _t;
			AST tmp122_AST_in = (AST)_t;
			match(_t,LITERAL_throws);
			_t = _t.getFirstChild();
			{
			_loop155:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==IDENT||_t.getType()==DOT)) {
					ignore=classOrInterfaceType(_t);
					_t = _retTree;
				}
				else {
					break _loop155;
				}
				
			} while (true);
			}
			_t = __t153;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void stat(AST _t) throws RecognitionException {
		
		AST stat_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case CLASS_DEF:
			case INTERFACE_DEF:
			case ENUM_DEF:
			case ANNOTATION_DEF:
			{
				typeDefinition(_t);
				_t = _retTree;
				break;
			}
			case VARIABLE_DEF:
			{
				variableDef(_t);
				_t = _retTree;
				break;
			}
			case EXPR:
			{
				expression(_t);
				_t = _retTree;
				break;
			}
			case LABELED_STAT:
			{
				AST __t166 = _t;
				AST tmp123_AST_in = (AST)_t;
				match(_t,LABELED_STAT);
				_t = _t.getFirstChild();
				AST tmp124_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				stat(_t);
				_t = _retTree;
				_t = __t166;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_if:
			{
				AST __t167 = _t;
				AST tmp125_AST_in = (AST)_t;
				match(_t,LITERAL_if);
				_t = _t.getFirstChild();
				expression(_t);
				_t = _retTree;
				stat(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case SLIST:
				case VARIABLE_DEF:
				case CLASS_DEF:
				case INTERFACE_DEF:
				case LABELED_STAT:
				case EXPR:
				case EMPTY_STAT:
				case ENUM_DEF:
				case ANNOTATION_DEF:
				case LITERAL_synchronized:
				case LITERAL_if:
				case LITERAL_while:
				case LITERAL_do:
				case LITERAL_break:
				case LITERAL_continue:
				case LITERAL_return:
				case LITERAL_switch:
				case LITERAL_throw:
				case LITERAL_assert:
				case LITERAL_for:
				case LITERAL_try:
				{
					stat(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t167;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_for:
			{
				AST __t169 = _t;
				AST tmp126_AST_in = (AST)_t;
				match(_t,LITERAL_for);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case FOR_INIT:
				{
					AST __t171 = _t;
					AST tmp127_AST_in = (AST)_t;
					match(_t,FOR_INIT);
					_t = _t.getFirstChild();
					{
					if (_t==null) _t=ASTNULL;
					switch ( _t.getType()) {
					case VARIABLE_DEF:
					{
						{
						int _cnt174=0;
						_loop174:
						do {
							if (_t==null) _t=ASTNULL;
							if ((_t.getType()==VARIABLE_DEF)) {
								variableDef(_t);
								_t = _retTree;
							}
							else {
								if ( _cnt174>=1 ) { break _loop174; } else {throw new NoViableAltException(_t);}
							}
							
							_cnt174++;
						} while (true);
						}
						break;
					}
					case ELIST:
					{
						elist(_t);
						_t = _retTree;
						break;
					}
					case 3:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(_t);
					}
					}
					}
					_t = __t171;
					_t = _t.getNextSibling();
					AST __t175 = _t;
					AST tmp128_AST_in = (AST)_t;
					match(_t,FOR_CONDITION);
					_t = _t.getFirstChild();
					{
					if (_t==null) _t=ASTNULL;
					switch ( _t.getType()) {
					case EXPR:
					{
						expression(_t);
						_t = _retTree;
						break;
					}
					case 3:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(_t);
					}
					}
					}
					_t = __t175;
					_t = _t.getNextSibling();
					AST __t177 = _t;
					AST tmp129_AST_in = (AST)_t;
					match(_t,FOR_ITERATOR);
					_t = _t.getFirstChild();
					{
					if (_t==null) _t=ASTNULL;
					switch ( _t.getType()) {
					case ELIST:
					{
						elist(_t);
						_t = _retTree;
						break;
					}
					case 3:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(_t);
					}
					}
					}
					_t = __t177;
					_t = _t.getNextSibling();
					break;
				}
				case FOR_EACH_CLAUSE:
				{
					AST __t179 = _t;
					AST tmp130_AST_in = (AST)_t;
					match(_t,FOR_EACH_CLAUSE);
					_t = _t.getFirstChild();
					parameterDef(_t);
					_t = _retTree;
					expression(_t);
					_t = _retTree;
					_t = __t179;
					_t = _t.getNextSibling();
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				stat(_t);
				_t = _retTree;
				_t = __t169;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_while:
			{
				AST __t180 = _t;
				AST tmp131_AST_in = (AST)_t;
				match(_t,LITERAL_while);
				_t = _t.getFirstChild();
				expression(_t);
				_t = _retTree;
				stat(_t);
				_t = _retTree;
				_t = __t180;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_do:
			{
				AST __t181 = _t;
				AST tmp132_AST_in = (AST)_t;
				match(_t,LITERAL_do);
				_t = _t.getFirstChild();
				stat(_t);
				_t = _retTree;
				expression(_t);
				_t = _retTree;
				_t = __t181;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_break:
			{
				AST __t182 = _t;
				AST tmp133_AST_in = (AST)_t;
				match(_t,LITERAL_break);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case IDENT:
				{
					AST tmp134_AST_in = (AST)_t;
					match(_t,IDENT);
					_t = _t.getNextSibling();
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t182;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_continue:
			{
				AST __t184 = _t;
				AST tmp135_AST_in = (AST)_t;
				match(_t,LITERAL_continue);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case IDENT:
				{
					AST tmp136_AST_in = (AST)_t;
					match(_t,IDENT);
					_t = _t.getNextSibling();
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t184;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_return:
			{
				AST __t186 = _t;
				AST tmp137_AST_in = (AST)_t;
				match(_t,LITERAL_return);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case EXPR:
				{
					expression(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t186;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_switch:
			{
				AST __t188 = _t;
				AST tmp138_AST_in = (AST)_t;
				match(_t,LITERAL_switch);
				_t = _t.getFirstChild();
				expression(_t);
				_t = _retTree;
				{
				_loop190:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==CASE_GROUP)) {
						caseGroup(_t);
						_t = _retTree;
					}
					else {
						break _loop190;
					}
					
				} while (true);
				}
				_t = __t188;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_throw:
			{
				AST __t191 = _t;
				AST tmp139_AST_in = (AST)_t;
				match(_t,LITERAL_throw);
				_t = _t.getFirstChild();
				expression(_t);
				_t = _retTree;
				_t = __t191;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_synchronized:
			{
				AST __t192 = _t;
				AST tmp140_AST_in = (AST)_t;
				match(_t,LITERAL_synchronized);
				_t = _t.getFirstChild();
				expression(_t);
				_t = _retTree;
				stat(_t);
				_t = _retTree;
				_t = __t192;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_try:
			{
				tryBlock(_t);
				_t = _retTree;
				break;
			}
			case SLIST:
			{
				slist(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_assert:
			{
				AST __t193 = _t;
				AST tmp141_AST_in = (AST)_t;
				match(_t,LITERAL_assert);
				_t = _t.getFirstChild();
				expression(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case EXPR:
				{
					expression(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t193;
				_t = _t.getNextSibling();
				break;
			}
			case EMPTY_STAT:
			{
				AST tmp142_AST_in = (AST)_t;
				match(_t,EMPTY_STAT);
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void caseGroup(AST _t) throws RecognitionException {
		
		AST caseGroup_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t196 = _t;
			AST tmp143_AST_in = (AST)_t;
			match(_t,CASE_GROUP);
			_t = _t.getFirstChild();
			{
			int _cnt199=0;
			_loop199:
			do {
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case LITERAL_case:
				{
					AST __t198 = _t;
					AST tmp144_AST_in = (AST)_t;
					match(_t,LITERAL_case);
					_t = _t.getFirstChild();
					expression(_t);
					_t = _retTree;
					_t = __t198;
					_t = _t.getNextSibling();
					break;
				}
				case LITERAL_default:
				{
					AST tmp145_AST_in = (AST)_t;
					match(_t,LITERAL_default);
					_t = _t.getNextSibling();
					break;
				}
				default:
				{
					if ( _cnt199>=1 ) { break _loop199; } else {throw new NoViableAltException(_t);}
				}
				}
				_cnt199++;
			} while (true);
			}
			slist(_t);
			_t = _retTree;
			_t = __t196;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void tryBlock(AST _t) throws RecognitionException {
		
		AST tryBlock_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t201 = _t;
			AST tmp146_AST_in = (AST)_t;
			match(_t,LITERAL_try);
			_t = _t.getFirstChild();
			slist(_t);
			_t = _retTree;
			{
			_loop203:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_catch)) {
					handler(_t);
					_t = _retTree;
				}
				else {
					break _loop203;
				}
				
			} while (true);
			}
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_finally:
			{
				AST __t205 = _t;
				AST tmp147_AST_in = (AST)_t;
				match(_t,LITERAL_finally);
				_t = _t.getFirstChild();
				slist(_t);
				_t = _retTree;
				_t = __t205;
				_t = _t.getNextSibling();
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t201;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void handler(AST _t) throws RecognitionException {
		
		AST handler_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t207 = _t;
			AST tmp148_AST_in = (AST)_t;
			match(_t,LITERAL_catch);
			_t = _t.getFirstChild();
			parameterDef(_t);
			_t = _retTree;
			slist(_t);
			_t = _retTree;
			_t = __t207;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void expr(AST _t) throws RecognitionException {
		
		AST expr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE:
			case TYPECAST:
			case INDEX_OP:
			case POST_INC:
			case POST_DEC:
			case METHOD_CALL:
			case UNARY_MINUS:
			case UNARY_PLUS:
			case SUPER_CTOR_CALL:
			case CTOR_CALL:
			case IDENT:
			case DOT:
			case QUESTION:
			case LITERAL_super:
			case LT:
			case GT:
			case SR:
			case BSR:
			case STAR:
			case BAND:
			case LITERAL_this:
			case LOR:
			case LAND:
			case BOR:
			case BXOR:
			case NOT_EQUAL:
			case EQUAL:
			case LE:
			case GE:
			case LITERAL_instanceof:
			case SL:
			case PLUS:
			case MINUS:
			case DIV:
			case MOD:
			case INC:
			case DEC:
			case BNOT:
			case LNOT:
			case LITERAL_true:
			case LITERAL_false:
			case LITERAL_null:
			case LITERAL_new:
			case NUM_INT:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NUM_FLOAT:
			case NUM_LONG:
			case NUM_DOUBLE:
			{
				conditionalExpr(_t);
				_t = _retTree;
				break;
			}
			case ASSIGN:
			{
				AST __t215 = _t;
				AST tmp149_AST_in = (AST)_t;
				match(_t,ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t215;
				_t = _t.getNextSibling();
				break;
			}
			case PLUS_ASSIGN:
			{
				AST __t216 = _t;
				AST tmp150_AST_in = (AST)_t;
				match(_t,PLUS_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t216;
				_t = _t.getNextSibling();
				break;
			}
			case MINUS_ASSIGN:
			{
				AST __t217 = _t;
				AST tmp151_AST_in = (AST)_t;
				match(_t,MINUS_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t217;
				_t = _t.getNextSibling();
				break;
			}
			case STAR_ASSIGN:
			{
				AST __t218 = _t;
				AST tmp152_AST_in = (AST)_t;
				match(_t,STAR_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t218;
				_t = _t.getNextSibling();
				break;
			}
			case DIV_ASSIGN:
			{
				AST __t219 = _t;
				AST tmp153_AST_in = (AST)_t;
				match(_t,DIV_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t219;
				_t = _t.getNextSibling();
				break;
			}
			case MOD_ASSIGN:
			{
				AST __t220 = _t;
				AST tmp154_AST_in = (AST)_t;
				match(_t,MOD_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t220;
				_t = _t.getNextSibling();
				break;
			}
			case SR_ASSIGN:
			{
				AST __t221 = _t;
				AST tmp155_AST_in = (AST)_t;
				match(_t,SR_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t221;
				_t = _t.getNextSibling();
				break;
			}
			case BSR_ASSIGN:
			{
				AST __t222 = _t;
				AST tmp156_AST_in = (AST)_t;
				match(_t,BSR_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t222;
				_t = _t.getNextSibling();
				break;
			}
			case SL_ASSIGN:
			{
				AST __t223 = _t;
				AST tmp157_AST_in = (AST)_t;
				match(_t,SL_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t223;
				_t = _t.getNextSibling();
				break;
			}
			case BAND_ASSIGN:
			{
				AST __t224 = _t;
				AST tmp158_AST_in = (AST)_t;
				match(_t,BAND_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t224;
				_t = _t.getNextSibling();
				break;
			}
			case BXOR_ASSIGN:
			{
				AST __t225 = _t;
				AST tmp159_AST_in = (AST)_t;
				match(_t,BXOR_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t225;
				_t = _t.getNextSibling();
				break;
			}
			case BOR_ASSIGN:
			{
				AST __t226 = _t;
				AST tmp160_AST_in = (AST)_t;
				match(_t,BOR_ASSIGN);
				_t = _t.getFirstChild();
				expr(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t226;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void primaryExpression(AST _t) throws RecognitionException {
		
		AST primaryExpression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IDENT:
			{
				AST tmp161_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				break;
			}
			case DOT:
			{
				AST __t258 = _t;
				AST tmp162_AST_in = (AST)_t;
				match(_t,DOT);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case TYPE:
				case TYPECAST:
				case INDEX_OP:
				case POST_INC:
				case POST_DEC:
				case METHOD_CALL:
				case UNARY_MINUS:
				case UNARY_PLUS:
				case SUPER_CTOR_CALL:
				case CTOR_CALL:
				case IDENT:
				case DOT:
				case QUESTION:
				case LITERAL_super:
				case LT:
				case GT:
				case SR:
				case BSR:
				case STAR:
				case ASSIGN:
				case BAND:
				case LITERAL_this:
				case PLUS_ASSIGN:
				case MINUS_ASSIGN:
				case STAR_ASSIGN:
				case DIV_ASSIGN:
				case MOD_ASSIGN:
				case SR_ASSIGN:
				case BSR_ASSIGN:
				case SL_ASSIGN:
				case BAND_ASSIGN:
				case BXOR_ASSIGN:
				case BOR_ASSIGN:
				case LOR:
				case LAND:
				case BOR:
				case BXOR:
				case NOT_EQUAL:
				case EQUAL:
				case LE:
				case GE:
				case LITERAL_instanceof:
				case SL:
				case PLUS:
				case MINUS:
				case DIV:
				case MOD:
				case INC:
				case DEC:
				case BNOT:
				case LNOT:
				case LITERAL_true:
				case LITERAL_false:
				case LITERAL_null:
				case LITERAL_new:
				case NUM_INT:
				case CHAR_LITERAL:
				case STRING_LITERAL:
				case NUM_FLOAT:
				case NUM_LONG:
				case NUM_DOUBLE:
				{
					expr(_t);
					_t = _retTree;
					{
					if (_t==null) _t=ASTNULL;
					switch ( _t.getType()) {
					case IDENT:
					{
						AST tmp163_AST_in = (AST)_t;
						match(_t,IDENT);
						_t = _t.getNextSibling();
						break;
					}
					case INDEX_OP:
					{
						arrayIndex(_t);
						_t = _retTree;
						break;
					}
					case LITERAL_this:
					{
						AST tmp164_AST_in = (AST)_t;
						match(_t,LITERAL_this);
						_t = _t.getNextSibling();
						break;
					}
					case LITERAL_class:
					{
						AST tmp165_AST_in = (AST)_t;
						match(_t,LITERAL_class);
						_t = _t.getNextSibling();
						break;
					}
					case LITERAL_new:
					{
						newExpression(_t);
						_t = _retTree;
						break;
					}
					case LITERAL_super:
					{
						AST tmp166_AST_in = (AST)_t;
						match(_t,LITERAL_super);
						_t = _t.getNextSibling();
						break;
					}
					case 3:
					case TYPE_ARGUMENTS:
					{
						{
						if (_t==null) _t=ASTNULL;
						switch ( _t.getType()) {
						case TYPE_ARGUMENTS:
						{
							ignore=typeArguments(_t);
							_t = _retTree;
							break;
						}
						case 3:
						{
							break;
						}
						default:
						{
							throw new NoViableAltException(_t);
						}
						}
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(_t);
					}
					}
					}
					break;
				}
				case ARRAY_DECLARATOR:
				{
					AST __t262 = _t;
					AST tmp167_AST_in = (AST)_t;
					match(_t,ARRAY_DECLARATOR);
					_t = _t.getFirstChild();
					ignore=typeSpecArray(_t);
					_t = _retTree;
					_t = __t262;
					_t = _t.getNextSibling();
					break;
				}
				case LITERAL_void:
				case LITERAL_boolean:
				case LITERAL_byte:
				case LITERAL_char:
				case LITERAL_short:
				case LITERAL_int:
				case LITERAL_float:
				case LITERAL_long:
				case LITERAL_double:
				{
					ignore=builtInType(_t);
					_t = _retTree;
					{
					if (_t==null) _t=ASTNULL;
					switch ( _t.getType()) {
					case LITERAL_class:
					{
						AST tmp168_AST_in = (AST)_t;
						match(_t,LITERAL_class);
						_t = _t.getNextSibling();
						break;
					}
					case 3:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(_t);
					}
					}
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t258;
				_t = _t.getNextSibling();
				break;
			}
			case INDEX_OP:
			{
				arrayIndex(_t);
				_t = _retTree;
				break;
			}
			case METHOD_CALL:
			{
				AST __t264 = _t;
				AST tmp169_AST_in = (AST)_t;
				match(_t,METHOD_CALL);
				_t = _t.getFirstChild();
				primaryExpression(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case TYPE_ARGUMENTS:
				{
					ignore=typeArguments(_t);
					_t = _retTree;
					break;
				}
				case ELIST:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				elist(_t);
				_t = _retTree;
				_t = __t264;
				_t = _t.getNextSibling();
				break;
			}
			case SUPER_CTOR_CALL:
			case CTOR_CALL:
			{
				ctorCall(_t);
				_t = _retTree;
				break;
			}
			case TYPECAST:
			{
				AST __t266 = _t;
				AST tmp170_AST_in = (AST)_t;
				match(_t,TYPECAST);
				_t = _t.getFirstChild();
				ignore=typeSpec(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				_t = __t266;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_new:
			{
				newExpression(_t);
				_t = _retTree;
				break;
			}
			case NUM_INT:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NUM_FLOAT:
			case NUM_LONG:
			case NUM_DOUBLE:
			{
				constant(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_super:
			{
				AST tmp171_AST_in = (AST)_t;
				match(_t,LITERAL_super);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_true:
			{
				AST tmp172_AST_in = (AST)_t;
				match(_t,LITERAL_true);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_false:
			{
				AST tmp173_AST_in = (AST)_t;
				match(_t,LITERAL_false);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_this:
			{
				AST tmp174_AST_in = (AST)_t;
				match(_t,LITERAL_this);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_null:
			{
				AST tmp175_AST_in = (AST)_t;
				match(_t,LITERAL_null);
				_t = _t.getNextSibling();
				break;
			}
			case TYPE:
			{
				ignore=typeSpec(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void arrayIndex(AST _t) throws RecognitionException {
		
		AST arrayIndex_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t272 = _t;
			AST tmp176_AST_in = (AST)_t;
			match(_t,INDEX_OP);
			_t = _t.getFirstChild();
			expr(_t);
			_t = _retTree;
			expression(_t);
			_t = _retTree;
			_t = __t272;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void newExpression(AST _t) throws RecognitionException {
		
		AST newExpression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t275 = _t;
			AST tmp177_AST_in = (AST)_t;
			match(_t,LITERAL_new);
			_t = _t.getFirstChild();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case TYPE_ARGUMENTS:
			{
				ignore=typeArguments(_t);
				_t = _retTree;
				break;
			}
			case IDENT:
			case DOT:
			case LITERAL_void:
			case LITERAL_boolean:
			case LITERAL_byte:
			case LITERAL_char:
			case LITERAL_short:
			case LITERAL_int:
			case LITERAL_float:
			case LITERAL_long:
			case LITERAL_double:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			ignore=type(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ARRAY_DECLARATOR:
			{
				newArrayDeclarator(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case ARRAY_INIT:
				{
					arrayInitializer(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				break;
			}
			case ELIST:
			{
				elist(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case OBJBLOCK:
				{
					objBlock(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t275;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void ctorCall(AST _t) throws RecognitionException {
		
		AST ctorCall_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case CTOR_CALL:
			{
				AST __t268 = _t;
				AST tmp178_AST_in = (AST)_t;
				match(_t,CTOR_CALL);
				_t = _t.getFirstChild();
				elist(_t);
				_t = _retTree;
				_t = __t268;
				_t = _t.getNextSibling();
				break;
			}
			case SUPER_CTOR_CALL:
			{
				AST __t269 = _t;
				AST tmp179_AST_in = (AST)_t;
				match(_t,SUPER_CTOR_CALL);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case ELIST:
				{
					elist(_t);
					_t = _retTree;
					break;
				}
				case TYPE:
				case TYPECAST:
				case INDEX_OP:
				case METHOD_CALL:
				case SUPER_CTOR_CALL:
				case CTOR_CALL:
				case IDENT:
				case DOT:
				case LITERAL_super:
				case LITERAL_this:
				case LITERAL_true:
				case LITERAL_false:
				case LITERAL_null:
				case LITERAL_new:
				case NUM_INT:
				case CHAR_LITERAL:
				case STRING_LITERAL:
				case NUM_FLOAT:
				case NUM_LONG:
				case NUM_DOUBLE:
				{
					primaryExpression(_t);
					_t = _retTree;
					elist(_t);
					_t = _retTree;
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t269;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void constant(AST _t) throws RecognitionException {
		
		AST constant_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case NUM_INT:
			{
				AST tmp180_AST_in = (AST)_t;
				match(_t,NUM_INT);
				_t = _t.getNextSibling();
				break;
			}
			case CHAR_LITERAL:
			{
				AST tmp181_AST_in = (AST)_t;
				match(_t,CHAR_LITERAL);
				_t = _t.getNextSibling();
				break;
			}
			case STRING_LITERAL:
			{
				AST tmp182_AST_in = (AST)_t;
				match(_t,STRING_LITERAL);
				_t = _t.getNextSibling();
				break;
			}
			case NUM_FLOAT:
			{
				AST tmp183_AST_in = (AST)_t;
				match(_t,NUM_FLOAT);
				_t = _t.getNextSibling();
				break;
			}
			case NUM_DOUBLE:
			{
				AST tmp184_AST_in = (AST)_t;
				match(_t,NUM_DOUBLE);
				_t = _t.getNextSibling();
				break;
			}
			case NUM_LONG:
			{
				AST tmp185_AST_in = (AST)_t;
				match(_t,NUM_LONG);
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void newArrayDeclarator(AST _t) throws RecognitionException {
		
		AST newArrayDeclarator_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t281 = _t;
			AST tmp186_AST_in = (AST)_t;
			match(_t,ARRAY_DECLARATOR);
			_t = _t.getFirstChild();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ARRAY_DECLARATOR:
			{
				newArrayDeclarator(_t);
				_t = _retTree;
				break;
			}
			case 3:
			case EXPR:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case EXPR:
			{
				expression(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t281;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"BLOCK",
		"MODIFIERS",
		"OBJBLOCK",
		"SLIST",
		"CTOR_DEF",
		"METHOD_DEF",
		"VARIABLE_DEF",
		"INSTANCE_INIT",
		"STATIC_INIT",
		"TYPE",
		"CLASS_DEF",
		"INTERFACE_DEF",
		"PACKAGE_DEF",
		"ARRAY_DECLARATOR",
		"EXTENDS_CLAUSE",
		"IMPLEMENTS_CLAUSE",
		"PARAMETERS",
		"PARAMETER_DEF",
		"LABELED_STAT",
		"TYPECAST",
		"INDEX_OP",
		"POST_INC",
		"POST_DEC",
		"METHOD_CALL",
		"EXPR",
		"ARRAY_INIT",
		"IMPORT",
		"UNARY_MINUS",
		"UNARY_PLUS",
		"CASE_GROUP",
		"ELIST",
		"FOR_INIT",
		"FOR_CONDITION",
		"FOR_ITERATOR",
		"EMPTY_STAT",
		"\"final\"",
		"\"abstract\"",
		"\"strictfp\"",
		"SUPER_CTOR_CALL",
		"CTOR_CALL",
		"VARIABLE_PARAMETER_DEF",
		"STATIC_IMPORT",
		"ENUM_DEF",
		"ENUM_CONSTANT_DEF",
		"FOR_EACH_CLAUSE",
		"ANNOTATION_DEF",
		"ANNOTATIONS",
		"ANNOTATION",
		"ANNOTATION_MEMBER_VALUE_PAIR",
		"ANNOTATION_FIELD_DEF",
		"ANNOTATION_ARRAY_INIT",
		"TYPE_ARGUMENTS",
		"TYPE_ARGUMENT",
		"TYPE_PARAMETERS",
		"TYPE_PARAMETER",
		"WILDCARD_TYPE",
		"TYPE_UPPER_BOUNDS",
		"TYPE_LOWER_BOUNDS",
		"\"package\"",
		"SEMI",
		"\"import\"",
		"\"static\"",
		"LBRACK",
		"RBRACK",
		"IDENT",
		"DOT",
		"QUESTION",
		"\"extends\"",
		"\"super\"",
		"LT",
		"COMMA",
		"GT",
		"SR",
		"BSR",
		"\"void\"",
		"\"boolean\"",
		"\"byte\"",
		"\"char\"",
		"\"short\"",
		"\"int\"",
		"\"float\"",
		"\"long\"",
		"\"double\"",
		"STAR",
		"\"private\"",
		"\"public\"",
		"\"protected\"",
		"\"transient\"",
		"\"native\"",
		"\"threadsafe\"",
		"\"synchronized\"",
		"\"volatile\"",
		"AT",
		"LPAREN",
		"RPAREN",
		"ASSIGN",
		"LCURLY",
		"RCURLY",
		"\"class\"",
		"\"interface\"",
		"\"enum\"",
		"BAND",
		"\"default\"",
		"\"implements\"",
		"\"this\"",
		"\"throws\"",
		"TRIPLE_DOT",
		"COLON",
		"\"if\"",
		"\"else\"",
		"\"while\"",
		"\"do\"",
		"\"break\"",
		"\"continue\"",
		"\"return\"",
		"\"switch\"",
		"\"throw\"",
		"\"assert\"",
		"\"for\"",
		"\"case\"",
		"\"try\"",
		"\"finally\"",
		"\"catch\"",
		"PLUS_ASSIGN",
		"MINUS_ASSIGN",
		"STAR_ASSIGN",
		"DIV_ASSIGN",
		"MOD_ASSIGN",
		"SR_ASSIGN",
		"BSR_ASSIGN",
		"SL_ASSIGN",
		"BAND_ASSIGN",
		"BXOR_ASSIGN",
		"BOR_ASSIGN",
		"LOR",
		"LAND",
		"BOR",
		"BXOR",
		"NOT_EQUAL",
		"EQUAL",
		"LE",
		"GE",
		"\"instanceof\"",
		"SL",
		"PLUS",
		"MINUS",
		"DIV",
		"MOD",
		"INC",
		"DEC",
		"BNOT",
		"LNOT",
		"\"true\"",
		"\"false\"",
		"\"null\"",
		"\"new\"",
		"NUM_INT",
		"CHAR_LITERAL",
		"STRING_LITERAL",
		"NUM_FLOAT",
		"NUM_LONG",
		"NUM_DOUBLE",
		"WS",
		"SL_COMMENT",
		"ML_COMMENT",
		"ESC",
		"HEX_DIGIT",
		"VOCAB",
		"EXPONENT",
		"FLOAT_SUFFIX",
		"\"const\""
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 633318697648128L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2255648104382464L, 4278190082L, 70368744177664L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 2265000655724544L, 19791217703792L, 274877905920L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 633593848185984L, 1728537833053880320L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	}
	
