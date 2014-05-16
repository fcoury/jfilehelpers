What is JFileHelpers?
---------------------

Chances are every programmer will end up having to manipulate structured text files at any point in his career. Those files are called flat files and, until now, you had to go through that same boring script to work with them. You had to open the file, create a reader, then start reading and parsing line by line. No more.

JFileHelpers is a library that automates the tedious task of parsing and creating structured text files. It handles fixed width or delimited files with Java annotations sweetness.

JFileHelpers started as a port of the awesome Marcos Meli's FileHelpers but it's starting to take its own path and we are in need of passinate developers who would like to give us a hand.

Go ahead and try it out!

The latest binary release can always be found on the [Releases](https://github.com/fcoury/jfilehelpers/releases) area.

## So what's code like? ##

Let's take, for instance, a fixed length structured text file, that handles customer data (you can see the ever-growing list of examples as well).

Here's how our bean should be:

```java
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
```

This could would handle a text file structured like this:

```
....|....1....|....2....|....3....|....4
1   Antonio Pereira     10012-12-1978ABC
2   Felipe Coury          201-01-2007
3   Anderson Polga       4212-11-2007DEF
```

And reading that file is as easy as:

```java
  FileHelperEngine<Customer> engine =
      new FileHelperEngine<Customer>(Customer.class);
  List<Customer> customers =
      new ArrayList<Customer>();

  customers = engine.readResource(
      "/samples/customers-fixed.txt");
```

This way, you can manipulate any properties of the beans contained on the ArrayList collection and eventually, even recreate a file with same format, also as easy as:

```java
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
```

As you may have already anticipated, the output file will look like this:

```
....|....1....|....2....|....3....|....4
   2        Felipe Coury  201-01-2007
   3      Anderson Polga 8212-11-2007APR
```

## Examples ##

### Enum conversion ###

This library has the out-of-the-box ability to export and import values to/from Java enum classes.

Take the following enum as an example:

```java
public enum Enum2 { One, Two, Three }
```

If we have an input file containing the following lines:

```
One
one
Two
Three
Three
```

And a very simple bean like this:

```java
@DelimitedRecord(",")
public class EnumType2 {
    public Enum2 enumValue;
}
```

It would be natural to write code for loading the file:

```java
public static void main(String[] args) throws IOException {
    FileHelperEngine engine =
        new FileHelperEngine<EnumType2>(EnumType2.class);

    List<EnumType2> res =
        engine.readResource(
            "/test/Good/EnumConverter2.txt");

    System.out.println("Size: " + res.size());

    System.out.println(
        Enum2.One.equals(res.get(0).enumValue));
    System.out.println(
        Enum2.Two.equals(res.get(2).enumValue));
    System.out.println(
        Enum2.Three.equals(res.get(3).enumValue));
    System.out.println(
        Enum2.Three.equals(res.get(4).enumValue));
}
```

... and that would print out:

```
Size: 5
true
true
true
true
```

### Master-detail ###

See how easy it is to handle master-detail formatted files:

```
ALFKI|Alfreds Futterkiste|Maria Anders|Sales Representative|Obere Str. 57|Berlin|Germany
10248|VINET|5|04071996|01081996|16071996|3|32.38
10249|TOMSP|6|05071996|16081996|10071996|1|11.61
10250|HANAR|4|08071996|05081996|12071996|2|65.83
10251|VICTE|3|08071996|05081996|15071996|1|41.34
ANATR|Ana Trujillo Emparedados y helados|Ana Trujillo|Owner|Avda. de la Constitución 2222|México D.F.|Mexico
10252|SUPRD|4|09071996|06081996|11071996|2|51.3
10253|HANAR|3|10071996|24071996|16071996|2|58.17
10254|CHOPS|5|11071996|08081996|23071996|2|22.98
ANTON|Antonio Moreno Taquería|Antonio Moreno|Owner|Mataderos  2312|México D.F.|Mexico
10257|HILAA|4|16071996|13081996|22071996|3|81.91
10258|ERNSH|1|17071996|14081996|23071996|1|140.51
DUMON|Du monde entier|Janine Labrune|Owner|67, rue des Cinquante Otages|Nantes|France
```

To indicate what records are to be considered as master as what are to be considered as detail, we use the following code:

```java
engine = new MasterDetailEngine<CustomersVerticalBar,
    OrdersVerticalBar>(CustomersVerticalBar.class, OrdersVerticalBar.class,

        new MasterDetailSelector() {

            @Override
            public RecordAction getRecordAction(String recordString) {
                // if the first char on the record is a letter
                // we'll consider it a master record and
                // if not, we'll consider it a detail record
                if (Character.isLetter(recordString.charAt(0)))
                    return RecordAction.Master;
                else
                    return RecordAction.Detail;
            }

        });

List<MasterDetails<CustomersVerticalBar, OrdersVerticalBar>> res =
    (List<MasterDetails<CustomersVerticalBar, OrdersVerticalBar>>)
        Common.readTest(engine, "Good/MasterDetail1.txt");
```

And here's how the beans are:

```java
@DelimitedRecord("|")
public class CustomersVerticalBar
    implements ComparableRecord<CustomersVerticalBar> {

    public String customerID;
    public String companyName;
    public String contactName;
    public String contactTitle;
    public String address;
    public String city;
    public String country;

    @Override
    public boolean equalsRecord(CustomersVerticalBar record) {
        if (this.customerID == null) {
            return false;
        }
        return this.customerID.equals(record);
    }

}

@DelimitedRecord("|")
public class OrdersVerticalBar {

    public int orderID;
    public String customerID;
    public int employeeID;
    public Date orderDate;
    public Date requiredDate;
    @FieldNullValue("2005-1-1")
    public Date shippedDate;
    public int shipVia;
    public float freight;

}
```
