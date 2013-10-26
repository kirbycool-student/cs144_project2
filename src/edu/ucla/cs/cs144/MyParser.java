/* CS144
 *
 * Parser skeleton for processing item-???.xml files. Must be compiled in
 * JDK 1.5 or above.
 *
 * Instructions:
 *
 * This program processes all files passed on the command line (to parse
 * an entire diectory, type "java MyParser myFiles/*.xml" at the shell).
 *
 * At the point noted below, an individual XML file has been parsed into a
 * DOM Document node. You should fill in code to process the node. Java's
 * interface for the Document Object Model (DOM) is in package
 * org.w3c.dom. The documentation is available online at
	    e.ge *
 * http://java.sun.com/j2se/1.5.0/docs/api/index.html
 *
 * A tutorial of Java's XML Parsing can be found at:
 *
 * http://java.sun.com/webservices/jaxp/
 *
 * Some auxiliary methods have been written for you. You may find them
 * useful.
 */

package edu.ucla.cs.cs144;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;


class MyParser {
    
    static final String columnSeparator = "|*|";
    static DocumentBuilder builder;
    static ArrayList<String> categories = new ArrayList<String>();
    static int bidIter = 0;
    static String itemID;
    
    static final String[] typeName = {
	"none",
	"Element",
	"Attr",
	"Text",
	"CDATA",
	"EntityRef",
	"Entity",
	"ProcInstr",
	"Comment",
	"Document",
	"DocType",
	"DocFragment",
	"Notation",
    };
    
    static class MyErrorHandler implements ErrorHandler {
        
        public void warning(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void error(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void fatalError(SAXParseException exception)
        throws SAXException {
            exception.printStackTrace();
            System.out.println("There should be no errors " +
                               "in the supplied XML files.");
            System.exit(3);
        }
        
    }
    
    /* Non-recursive (NR) version of Node.getElementsByTagName(...)
     */
    static Element[] getElementsByTagNameNR(Element e, String tagName) {
        Vector< Element > elements = new Vector< Element >();
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
            {
                elements.add( (Element)child );
            }
            child = child.getNextSibling();
        }
        Element[] result = new Element[elements.size()];
        elements.copyInto(result);
        return result;
    }
    
    /* Returns the first subelement of e matching the given tagName, or
     * null if one does not exist. NR means Non-Recursive.
     */
    static Element getElementByTagNameNR(Element e, String tagName) {
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
                return (Element) child;
            child = child.getNextSibling();
        }
        return null;
    }
    
    /* Returns the text associated with the given element (which must have
     * type #PCDATA) as child, or "" if it contains no text.
     */
    static String getElementText(Element e) {
        if (e.getChildNodes().getLength() == 1) {
            Text elementText = (Text) e.getFirstChild();
            return elementText.getNodeValue();
        }
        else
            return "";
    }
    
    /* Returns the text (#PCDATA) associated with the first subelement X
     * of e with the given tagName. If no such X exists or X contains no
     * text, "" is returned. NR means Non-Recursive.
     */
    static String getElementTextByTagNameNR(Element e, String tagName) {
        Element elem = getElementByTagNameNR(e, tagName);
        if (elem != null)
            return getElementText(elem);
        else
            return "";
    }
    
    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) {
        if (money.equals(""))
            return money;
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all " +
                                   "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }
    
    /* Process one items-???.xml file.
     */
    static void processFile(File xmlFile) {
        Document doc = null;
        try {
            doc = builder.parse(xmlFile);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
        catch (SAXException e) {
            System.out.println("Parsing error on file " + xmlFile);
            System.out.println("  (not supposed to happen with supplied XML files)");
            e.printStackTrace();
            System.exit(3);
        }
        
        /* At this point 'doc' contains a DOM representation of an 'Items' XML
         * file. Use doc.getDocumentElement() to get the root Element. */
        System.out.println("Successfully parsed - " + xmlFile);
        
        /* Fill in code here (you will probably need to write auxiliary
            methods). */

/*	File item = new File("Item.csv");
	item.delete();
	File user = new File("User.csv");
	user.delete();
	File category = new File("Category.csv");
	category.delete();
	File itemcategory = new File("ItemCategory.csv");
	itemcategory.delete();
	File bid = new File("Bid.csv");
	bid.delete();
	File itembid = new File("ItemBid.csv");
	itembid.delete();
*/
        recursiveDescent(doc.getDocumentElement());
        
        /**************************************************************/
        
    }

   public static void recursiveDescent(Element e) {
	if(e.getTagName() == "Items")
	{
	    Element [] next = getElementsByTagNameNR(e, "Item");
	    for(int i = 0; i < next.length; i++)
		recursiveDescent(next[i]);
	}
	else if(e.getTagName() == "Item")
	{
	    itemID = (e.getAttributeNode("ItemID")).getValue();
	    recursiveDescent(getElementByTagNameNR(e, "Seller"));
	    recursiveDescent(getElementByTagNameNR(e, "Bids"));
	    

	    printToCSV("Item.csv", itemID, false, false);
	    
	    printToCSV("Item.csv", getElementTextByTagNameNR(e, "Name"), true, false);
	    printToCSV("Item.csv", strip(getElementTextByTagNameNR(e, "Currently")), false, false);
	    printToCSV("Item.csv", strip(getElementTextByTagNameNR(e, "Buy_Price")), false, false);
	    printToCSV("Item.csv", strip(getElementTextByTagNameNR(e, "First_Bid")), false, false);
	    printToCSV("Item.csv", getElementTextByTagNameNR(e, "Number_of_Bids"), false, false);
	    printToCSV("Item.csv", getElementTextByTagNameNR(e, "Location"), true, false);
	    printToCSV("Item.csv", getElementTextByTagNameNR(e, "Country"), true, false);
	    printToCSV("Item.csv", getElementTextByTagNameNR(e, "Started"), false, false);
	    printToCSV("Item.csv", getElementTextByTagNameNR(e, "Ends"), false, false);
	    printToCSV("Item.csv", getElementTextByTagNameNR(e, "Description"), true, true);

	    Element [] next = getElementsByTagNameNR(e, "Category");
	    for(int i = 0; i < next.length; i++)
		recursiveDescent(next[i]);

	}
	else if(e.getTagName() == "Seller")
	{
	    printToCSV("User.csv", (e.getAttributeNode("UserID")).getValue(), true, false);
	    printToCSV("User.csv", (e.getAttributeNode("Rating")).getValue(), false, false);
	    printToCSV("User.csv", "", true, false);
	    printToCSV("User.csv", "", true, true);
	}
	else if(e.getTagName() == "Bids")
	{
	    Element [] next = getElementsByTagNameNR(e, "Bid");
	    for(int i = 0; i < next.length; i++)
		recursiveDescent(next[i]);
	}
	else if(e.getTagName() == "Bid")
	{
	    recursiveDescent(getElementByTagNameNR(e, "Bidder"));
	    printToCSV("Bid.csv", itemID, false, false);
	    printToCSV("Bid.csv", ((getElementByTagNameNR(e, "Bidder")).getAttributeNode("UserID")).getValue(), true, false);
	    printToCSV("Bid.csv", getElementTextByTagNameNR(e, "Time"), false, false);
	    printToCSV("Bid.csv", strip(getElementTextByTagNameNR(e, "Amount")), false, true);

/*
	    printToCSV("ItemBid.csv", itemID, false, false);
	    printToCSV("ItemBid.csv", Integer.toString(bidIter), false, true);
	    bidIter++;
*/
	}
	else if(e.getTagName() == "Bidder")
	{
	    printToCSV("User.csv", (e.getAttributeNode("UserID")).getValue(), true, false);
	    printToCSV("User.csv", (e.getAttributeNode("Rating")).getValue(), false, false);
	    printToCSV("User.csv", getElementTextByTagNameNR(e, "Location"), true, false);
	    printToCSV("User.csv", getElementTextByTagNameNR(e, "Country"), true, true);
	}
	else if(e.getTagName() == "Category")
	{
/*	    int index = categories.indexOf(getElementText(e));
	    if(index == -1)
	    {
		categories.add(getElementText(e));
		index = categories.indexOf(getElementText(e));
		printToCSV("Category.csv", Integer.toString(index), false, false);
		printToCSV("Category.csv", getElementText(e), true, true);
	    }
*/
	    printToCSV("ItemCategory.csv", getElementText(e), true, false);
	    printToCSV("ItemCategory.csv", itemID, false, true);
	}
	else
	{
	    System.out.println("SOMETHING FUCKED UP\n");
	    System.exit(1);
	}
    }

   public static void printToCSV(String filename, String text, boolean string, boolean end)
   {
        Writer file = null;
	try {
            file = new BufferedWriter(new OutputStreamWriter(
                      new FileOutputStream(filename, true), "utf-8"));
	if(string)
	{
	    text = text.replaceAll("\\\\", "\\\\\\\\");
	    text = "\"" + text.replaceAll("\"", "\\\\\"") + "\"";
	}
	if(end)
	{
	    text = text + "\n";
	}
	else
	{
	    text = text + ",";
	}
	file.write(text);
	} catch (IOException ex) {
	    System.out.println(ex);
	} finally {
            try {file.close();} catch (Exception ex) {}
	}
   }

   public static void printEndToCSV(String filename, String text, boolean string)
   {
        Writer test = null;
	try {
            test = new BufferedWriter(new OutputStreamWriter(
                      new FileOutputStream(filename, true), "utf-8"));
	if(string)
	{
	    text = text.replaceAll("\"", "\\\\\"");
	    test.write("\"" + text + "\"\n");
	}
	else
	{
            test.write(text + "\n");
	}
	} catch (IOException ex) {
	    System.out.println(ex);
	} finally {
            try {test.close();} catch (Exception ex) {}
	}
   }


   public static void main (String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java MyParser [file] [file] ...");
            System.exit(1);
        }
        
        /* Initialize parser. */
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);      
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new MyErrorHandler());
        }
        catch (FactoryConfigurationError e) {
            System.out.println("unable to get a document builder factory");
            System.exit(2);
        } 
        catch (ParserConfigurationException e) {
            System.out.println("parser was unable to be configured");
            System.exit(2);
        }
        
        /* Process all files listed on command line. */
        for (int i = 0; i < args.length; i++) {
            File currentFile = new File(args[i]);
            processFile(currentFile);
        }
    }
}
