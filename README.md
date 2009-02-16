What is JFileHelpers?
---------------------

Chances are every programmer will end up having to manipulate structured text files at any point in his career. Those files are called flat files and, until now, you had to go through that same boring script to work with them. You had to open the file, create a reader, then start reading and parsing line by line. No more.

JFileHelpers is a library that automates the tedious task of parsing and creating structured text files. It handles fixed width or delimited files with Java annotations sweetness.

JFileHelpers started as a port of the awesome Marcos Meli's FileHelpers but it's starting to take its own path and we are in need of passinate developers who would like to give us a hand.

Go ahead and try it out! Download it now:

* [Latest Binary Distribution (0.2a)](http://downloads.sourceforge.net/jfilehelpers/jfilehelpers-0.2a-20080607.jar)

* [Latest Source Distribution (0.2a)](http://downloads.sourceforge.net/jfilehelpers/jfilehelpers-src-0.2a-20080607.zip)


We just opened our [forum](http://forum.jfilehelpers.com/viewforum.php?f=2) to support our users and announce new features. Log in and tell us if you have any questions or comments!

## So what's code like? ##

Let's take, for instance, a fixed length structured text file, that handles customer data (you can see the ever-growing list of examples as well).

Here's how our bean should be:

    @FixedLengthRecord()  
    public class Customer {  
        @FieldFixedLength(4)  
        public Integer custId;  
      
        @FieldAlign(alignMode=AlignMode.Right)  
        @FieldFixedLength(20)  
        public String name;  
      
        @FieldFixedLength(3)  
        public Integer rating;  
      
        @FieldTrim(trimMode=TrimMode.Right)  
        @FieldFixedLength(10)  
        @FieldConverter(converter = ConverterKind.Date,   
            format = "dd-MM-yyyy")  
        public Date addedDate;  
          
        @FieldFixedLength(3)  
        @FieldOptional  
        public String stockSimbol;    
    }  
              

This could would handle a text file structured like this:

    ....|....1....|....2....|....3....|....4				
    1   Antonio Pereira     10012-12-1978ABC
    2   Felipe Coury          201-01-2007
    3   Anderson Polga       4212-11-2007DEF
		

And reading that file is as easy as:

    FileHelperEngine<Customer> engine =   
        new FileHelperEngine<Customer>(Customer.class);     
    List<Customer> customers =   
        new ArrayList<Customer>();  
      
    customers = engine.readResource(  
        "/samples/customers-fixed.txt");  

This way, you can manipulate any properties of the beans contained on the ArrayList collection and eventually, even recreate a file with same format, also as easy as:

    FileHelperEngine<Customer> engine =   
        new FileHelperEngine<Customer>(Customer.class);     
    List<Customer> customers = new ArrayList<Customer>();  
      
    customers = engine.readResource(  
        "/samples/customers-fixed.txt");  
      
    // retrieves customer 3 - Anderson Polga  
    Customer c = customers.get(2);  
    // changes a couple of properties  
    c.rating = 82;  
    c.stockSimbol = "APR";  
      
    // and removes first customer - Antonio Pereira  
    customers.remove(0);  
      
    // writes the output file  
    engine.writeFile("customers-fixed-out.txt", customers);  

As you may have already anticipated, the output file will look like this:

	....|....1....|....2....|....3....|....4				
	   2        Felipe Coury  201-01-2007   
	   3      Anderson Polga 8212-11-2007APR
