/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//jelly/src/java/org/apache/commons/jelly/impl/TextScript.java,v 1.12 2003/01/24 05:26:13 morgand Exp $
 * $Revision: 1.12 $
 * $Date: 2003/01/24 05:26:13 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 * 
 * $Id: TextScript.java,v 1.12 2003/01/24 05:26:13 morgand Exp $
 */
package org.apache.commons.jelly.impl;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import org.xml.sax.SAXException;

/** <p><code>TextScript</code> outputs some static text.</p>
  *
  * @author <a href="mailto:jstrachan@apache.org">James Strachan</a>
  * @version $Revision: 1.12 $
  */
public class TextScript implements Script {
 
    /** the text output by this script */
    private String text;
 
    public TextScript() {
    }

    public TextScript(String text) {
        this.text = text;
    }

    public String toString() {
        return super.toString() + "[text=" + text + "]";
    }
    
    /**
     * Trims whitespace from the start and end of the text in this script
     */
    public void trimWhitespace() {
        this.text = text.trim();
    }
    
    /**
     * Trims whitespace from the start of the text
     */
    public void trimStartWhitespace() {
        int index = 0;        
        for ( int length = text.length(); index < length; index++ ) {
            char ch = text.charAt(index);
            if (!Character.isWhitespace(ch)) {
                break;
            }            
        }
        if ( index > 0 ) {
            this.text = text.substring(index);
        }
    }
    
    /**
     * Trims whitespace from the end of the text
     */
    public void trimEndWhitespace() {
        int index = text.length();
        while (--index >= 0) {
            char ch = text.charAt(index);
            if (!Character.isWhitespace(ch)) {
                break;
            }
        }
        index++;
        if ( index < text.length() ) {
            this.text = text.substring(0,index);
        }
    }

    /** @return the text output by this script */
    public String getText() {
        return text;
    }

    /** Sets the text output by this script */
    public void setText(String text) {
        this.text = text;
    }
 
    // Script interface
    //-------------------------------------------------------------------------                
    public Script compile() {
        return this;
    }

    /** Evaluates the body of a tag */
    public void run(JellyContext context, XMLOutput output) throws JellyException {
        if ( text != null ) {
            try {
              output.write(text);
            } catch (SAXException e) {
                throw new JellyException("could not write to XMLOutput",e);
            }
        }
    }
}
