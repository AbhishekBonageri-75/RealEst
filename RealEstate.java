
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RealEstate {
    static Connection conn;

//    public static Date convert(String dateString)
//    {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = format.parse(dateString);
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
//        return date;
//    }

    public static void search() throws SQLException
    {
        System.out.println("\n1.Search Renter\n2.Search Agent \n3.Search Property\n4.Search Booking");
        int in , id ;
        String email;
        Scanner sc = new Scanner(System.in);
        in=sc.nextInt();
        System.out.println("Enter the Search ID");
        id = sc.nextInt();
        if(in == 1)
        {
            //search renter
            // assuming that "renterId" is the ID of the renter you're checking
            String query = "SELECT email FROM renter WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Renter exists, return their email
                email = resultSet.getString("email");
                System.out.println("Renter found -- Email : "+ email);
            } else {
                // Renter not found
                System.out.println("Renter not found");
            }


        }
        else if(in==2)
        {
            //Search Agent
            String query = "SELECT email FROM agents WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Renter exists, return their email
                email = resultSet.getString("email");
                System.out.println("Agent found -- Email : "+ email);
            } else {
                // Renter not found
                System.out.println("Agent not found");
            }
        }
        else if(in==3)
        {
            String name , type ;
            int price;
            //Search Property
            String query = "SELECT name , type , price FROM property WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Renter exists, return their email
                name = resultSet.getString("name");
                type = resultSet.getString("type");
                price = resultSet.getInt("price");
                System.out.println("Property found -- Name : "+ name + "\ttype:"+type+"\tprice:"+price+"\n");
            } else {
                // Renter not found
                System.out.println("Property not found");
            }
        }
        else if(in==4)
        {
            //Search Booking
            String name , type ;
            int price,propertyid,renterid;
            //Search Property
            String query = "SELECT propertyid , renterid  FROM Booking WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Renter exists, return their email
                propertyid = resultSet.getInt("propertyid");
                renterid = resultSet.getInt("renterid");
                System.out.println("Booking found -- propertyid : "+ propertyid + "\trenterid:"+renterid+"\n");
            } else {
                // Renter not found
                System.out.println("Booking not found");
            }
        }
        else {
            System.out.println("Wrong Option selected");
            System.exit(0);
        }
    }

    public static void insert() throws SQLException {
        int cardid,id ,renterAddressId , budget,price,cardbilladdid;
        String availability,moveindate,address,city, state, zipCode;
        String name , email , phone , jobTitle,agency,description,type,preferedlocation;
        String   cardNumber,cardHolderName,expirationDate , cvv ,billingAddress,billingCity,billingState,billingZipCode;

        System.out.println("\n1.Insert Agent\n2.Insert Renter\n3.Insert Property\n4.Book a Property(Only for renters)");
        Scanner sc = new Scanner(System.in);
        int opt = sc.nextInt();
        if(opt ==1)
        {
            System.out.println("\nEnter New Agent Details");
            System.out.println("ID:"); id=sc.nextInt();
            System.out.println("\nName:"); name=sc.next();
            System.out.println("\nEmail:");email = sc.next();
            System.out.println("\nPhone:");phone = sc.next();
            System.out.println("\njobTitle:");jobTitle = sc.next();
            System.out.println("\nagency:");agency = sc.next();

            String sql = "INSERT INTO agents (id,name, email,phone , jobTitle , agency) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, jobTitle);
            pstmt.setString(6, agency);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " rows inserted.");
            pstmt.close();

        }
        else if(opt == 2)
        {
            System.out.println("\nEnter New Renter Details");
            System.out.println("ID:"); id=sc.nextInt();
            System.out.println("\nName:"); name=sc.next();
            System.out.println("\nEmail:");email = sc.next();
            System.out.println("\nPhone:");phone = sc.next();
            System.out.println("\nMoveInDate:");moveindate = sc.next();
//            String date = moveindate;
            System.out.println("\nPref_Location:");preferedlocation = sc.next();
            System.out.println("\nBudget:");budget = sc.nextInt();

            System.out.println("\nrenterAddressId:");renterAddressId = sc.nextInt();
            System.out.println("\nRenter address");address = sc.next();
            System.out.println("\nCity:");city = sc.next();
            System.out.println("\nState:");state=sc.next();
            System.out.println("\nzipcode:");zipCode=sc.next();

            System.out.println("\ncardid:");cardid = sc.nextInt();
            System.out.println("\ncardNumber");cardNumber = sc.next();
            System.out.println("\ncardHolderName:");cardHolderName = sc.next();
            System.out.println("\nexpirationDate:");expirationDate=sc.next();
            System.out.println("\ncvv:");cvv=sc.next();

            System.out.println("\ncardbilladdid:");cardbilladdid = sc.nextInt();
            System.out.println("\nbillingAddress");billingAddress = sc.next();
            System.out.println("\nbillingCity:");billingCity = sc.next();
            System.out.println("\nbillingState:");billingState=sc.next();
            System.out.println("\nbillingZipCode:");billingZipCode=sc.next();



//            String sql = "INSERT INTO renter (id,name, email,phone , moveindate , preferedlocation,budget) VALUES (?, ?, ?, ?, ?, ?,?)";
//            String insertQuery = "INSERT INTO renter (id, name, email, phone, moveindate, preferedlocation, budget) VALUES (?, ?, ?, ?, ?, ?, ?)"
//                    + "INSERT INTO renterAddress (id, renterId, address, city, state, zipCode) VALUES (?, ?, ?, ?, ?, ?)"
//                    + "INSERT INTO renterCreditCards (id, renterId, cardNumber, cardHolderName, expirationDate, cvv) VALUES (?, ?, ?, ?, ?, ?)"
//                    + "INSERT INTO RenterCreditCardBillingAddress (id, renterCreditCardId, billingAddress, billingCity, billingState, billingZipCode) VALUES (?, ?, ?, ?, ?, ?)";
////
            String insertQuery1 = "INSERT INTO renter (id, name, email, phone, moveindate, preferedlocation, budget) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String insertQuery2 = "INSERT INTO renterAddress (id, renterId, address, city, state, zipCode) VALUES (?, ?, ?, ?, ?, ?)";
            String insertQuery3 ="INSERT INTO renterCreditCards (id, renterId, cardNumber, cardHolderName, expirationDate, cvv) VALUES (?, ?, ?, ?, ?, ?)";
            String insertQuery4 = "INSERT INTO RenterCreditCardBillingAddress (id, renterCreditCardId, billingAddress, billingCity, billingState, billingZipCode) VALUES (?, ?, ?, ?, ?, ?)";


            PreparedStatement pstmt1 = conn.prepareStatement(insertQuery1);
            PreparedStatement pstmt2 = conn.prepareStatement(insertQuery2);
            PreparedStatement pstmt3 = conn.prepareStatement(insertQuery3);
            PreparedStatement pstmt4 = conn.prepareStatement(insertQuery4);

            pstmt1.setInt(1, id);
            pstmt1.setString(2, name);
            pstmt1.setString(3, email);
            pstmt1.setString(4, phone);
            pstmt1.setString(5,  moveindate);
            pstmt1.setString(6, preferedlocation);
            pstmt1.setInt(7, budget);

            pstmt2.setInt(1, renterAddressId);
            pstmt2.setInt(2, id);
            pstmt2.setString(3, address);
            pstmt2.setString(4, city);
            pstmt2.setString(5, state);
            pstmt2.setString(6, zipCode);


            pstmt3.setInt(1, cardid);
            pstmt3.setInt(2, id);
            pstmt3.setString(3, cardNumber);
            pstmt3.setString(4, cardHolderName);
            pstmt3.setString(5, expirationDate);
            pstmt3.setString(6, cvv);

            pstmt4.setInt(1, cardbilladdid);
            pstmt4.setInt(2, cardid);
            pstmt4.setString(3, billingAddress);
            pstmt4.setString(4, billingCity);
            pstmt4.setString(5, billingState);
            pstmt4.setString(6, billingZipCode);


            int rowsInserted = pstmt1.executeUpdate();
            System.out.println(rowsInserted + " Renter rows inserted.");
            pstmt1.close();

             rowsInserted = pstmt2.executeUpdate();
            System.out.println(rowsInserted + " RenAdd rows inserted.");
            pstmt2.close();

             rowsInserted = pstmt3.executeUpdate();
            System.out.println(rowsInserted + " CC rows inserted.");
            pstmt3.close();

             rowsInserted = pstmt4.executeUpdate();
            System.out.println(rowsInserted + "CC Billing ADD rows inserted.");
            pstmt4.close();

        }
        else if(opt ==3)
        {
            String emailCheck;
            System.out.println("Enter Email");
            emailCheck = sc.next();
            String query = "SELECT CASE WHEN EXISTS (SELECT 1 FROM agents WHERE email = ?) THEN 1 ELSE 0 END AS email_exists";
            int emailExists = 0;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, emailCheck);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                emailExists = rs.getInt("email_exists");
                if(emailExists ==0)
                {
                    System.out.println("You are not an agent , you can not add a new property.");
                }
                else
                {
                    System.out.println("\nEnter New Property Details");
                    System.out.println("ID:"); id=sc.nextInt();
                    System.out.println("availability:"); availability=sc.next();
                    System.out.println("description:"); description=sc.next();
                    System.out.println("name:"); name=sc.next();
                    System.out.println("Price:"); price=sc.nextInt();
                    System.out.println("Type:"); type=sc.next();

                    String sql = "INSERT INTO property (id,availability, description,name , Price , Type) VALUES (?, ?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, id);
                    pstmt.setString(2, availability);
                    pstmt.setString(3, description);
                    pstmt.setString(4, name);
                    pstmt.setInt(5, price);
                    pstmt.setString(6, type);

                    int rowsInserted = pstmt.executeUpdate();
                    System.out.println(rowsInserted + " rows inserted.");
                    pstmt.close();

                }
            }
        } else if (opt==4) {
            //check if a renter first
            String emailCheck;
            System.out.println("Enter Email");
            emailCheck = sc.next();
            String query = "SELECT CASE WHEN EXISTS (SELECT 1 FROM renter WHERE email = ?) THEN 1 ELSE 0 END AS email_exists";
            int emailExists = 0;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, emailCheck);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                emailExists = rs.getInt("email_exists");
                if(emailExists ==0)
                {
                    System.out.println("You are not an agent , you can not add a new property.");
                    return;
                }
            }

            // First, ask the user for their renter ID
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your renter ID:");
            int renterId = scanner.nextInt();

            String sql = "SELECT id, cardNumber FROM renterCreditCards WHERE renterId = ?";

            // Get a connection to the database and create a prepared statement

            pstmt = conn.prepareStatement(sql);
            // Set the renter ID parameter on the prepared statement
            pstmt.setInt(1, renterId);

            // Execute the prepared statement to fetch the credit cards associated with the renter ID
             rs = pstmt.executeQuery();
            // Display the credit cards to the user
            System.out.println("Available credit cards:");
            while (rs.next()) {
                int creditCardId = rs.getInt("id");
                cardNumber = rs.getString("cardNumber");
                System.out.println("Credit card ID: " + creditCardId + ", Card number: " + cardNumber);
            }

            int idd , pid, ccid;
            System.out.print("Now, Input this Booking \n ID: ");idd=sc.nextInt();
            System.out.print("\nProperty ID: ");pid=sc.nextInt();
            System.out.print("\nChoose the card from above(Card ID):"); ccid = sc.nextInt();
            String sqlq = "INSERT into Booking (id,propertyId,renterId,renterCreditCardId) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sqlq);
            pstmt.setInt(1, idd);
            pstmt.setInt(2, pid);
            pstmt.setInt(3, renterId);
            pstmt.setInt(4, ccid);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " rows inserted.");
            pstmt.close();
//            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
//                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//                // Set the renter ID parameter on the prepared statement
//                pstmt.setInt(1, renterId);
//
//                // Execute the prepared statement to fetch the credit cards associated with the renter ID
//                ResultSet rs = pstmt.executeQuery();
//
//                // Display the credit cards to the user
//                System.out.println("Available credit cards:");
//                while (rs.next()) {
//                    int creditCardId = rs.getInt("id");
//                    String cardNumber = rs.getString("cardNumber");
//                    System.out.println("Credit card ID: " + creditCardId + ", Card number: " + cardNumber);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }


        }
    }

    public static void showRenter()
    {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from renter");
            while (rs.next()) {
                // Process results
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone =rs.getString("phone") ;
                Date moveindate = rs.getDate("moveindate") ;
                String preferedlocation =rs.getString("preferedlocation");
                int budget = rs.getInt("budget");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("email: " + email);
                System.out.println("phone: " + phone);
                System.out.println("moveindate: " + moveindate);
                System.out.println("preferedlocation: " + preferedlocation);
                System.out.println("budget: " + budget);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public static void delete() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int in , id,ccbaddId ,rccId ,raddId;
        String email;
        System.out.println("\n1.Delete Renter\n2.Delete Agent(Only Agent can do)\n3.Delete Property(Only Agent can do)\n4.Delete Booking(Only Renters can do)\n");
        in = scanner.nextInt();

        if(in==1)
        {
            System.out.println("Enter id");
            id = scanner.nextInt();
//            ccbaddId = scanner.nextInt();
//            rccId = scanner.nextInt();
//            raddId = scanner.nextInt();

            String deleteBillingAddressQuery = "DELETE FROM renterCreditCardBillingAddress WHERE renterCreditCardId IN (SELECT id FROM renterCreditCards WHERE renterId = ?)";
            PreparedStatement deleteBillingAddressStmt = conn.prepareStatement(deleteBillingAddressQuery);
            deleteBillingAddressStmt.setInt(1, id);
            int billingAddressRowsDeleted = deleteBillingAddressStmt.executeUpdate();

            String deleteCreditCardQuery = "DELETE FROM renterCreditCards WHERE renterId= ?";
            PreparedStatement deleteCreditCardStmt = conn.prepareStatement(deleteCreditCardQuery);
            deleteCreditCardStmt.setInt(1, id);
            int creditCardRowsDeleted = deleteCreditCardStmt.executeUpdate();

            String deleteAddressQuery = "DELETE FROM renterAddress WHERE renterId = ?";
            PreparedStatement deleteAddressStmt = conn.prepareStatement(deleteAddressQuery);
            deleteAddressStmt.setInt(1, id);
            int addressRowsDeleted = deleteAddressStmt.executeUpdate();


            // Assuming "renterId" is the ID of the renter you want to delete
            String query = "DELETE FROM renter WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("rowsDeleted:"+rowsDeleted);

        }
        else if (in==2)
        {
//Delete Agent(Only Agent can do)
            Scanner sc = new Scanner(System.in);
            String emailCheck;
            System.out.println("Enter Email (You should be an Agent to do it)");
            emailCheck = sc.next();
            String query = "SELECT CASE WHEN EXISTS (SELECT 1 FROM agents WHERE email = ?) THEN 1 ELSE 0 END AS email_exists";
            int emailExists = 0;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, emailCheck);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                emailExists = rs.getInt("email_exists");
                if(emailExists ==0)
                {
                    System.out.println("You are not an agent , you can not add a new property.");
                }
                else
                {
                    String sql = "delete from agents where email=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, emailCheck);

                    int rowsInserted = pstmt.executeUpdate();
                    System.out.println(rowsInserted + " rows Deleted.");
                    pstmt.close();

                }
            }
        }
        else if(in==3)
        {
//            Delete Property(Only Agent can do)
            Scanner sc = new Scanner(System.in);
            String emailCheck;
            System.out.println("Enter Email (You should be an Agent to do it)");
            emailCheck = sc.next();
            String query = "SELECT CASE WHEN EXISTS (SELECT 1 FROM agents WHERE email = ?) THEN 1 ELSE 0 END AS email_exists";
            int emailExists = 0;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, emailCheck);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                emailExists = rs.getInt("email_exists");
                if(emailExists ==0)
                {
                    System.out.println("You are not an agent , you can not add a new property.");
                }
                else
                {
                    System.out.println("Enter Property id");
                    id = scanner.nextInt();

//                    String sql = "INSERT INTO property (id,availability, description,name , Price , Type) VALUES (?, ?, ?, ?, ?, ?)";
                    String sql = "delete from property where id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, id);

                    int rowsInserted = pstmt.executeUpdate();
                    System.out.println(rowsInserted + " rows Deleted.");
                    pstmt.close();

                }
            }
        } else if (in==4)
        {
        //Delete Booking(Only Renters can do)
            Scanner sc = new Scanner(System.in);
            String emailCheck;
            System.out.println("Enter Email (You should be an Renter to do it)");
            emailCheck = sc.next();
            String query = "SELECT CASE WHEN EXISTS (SELECT 1 FROM renter WHERE email = ?) THEN 1 ELSE 0 END AS email_exists";
            int emailExists = 0;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, emailCheck);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                emailExists = rs.getInt("email_exists");
                if(emailExists ==0)
                {
                    System.out.println("You are not a renter , you can not Delete a booking.");
                }
                else
                {
                    System.out.println("Enter Booking id");
                    id = scanner.nextInt();

                    String sql = "delete from Booking where id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, id);

                    int rowsInserted = pstmt.executeUpdate();
                    System.out.println(rowsInserted + " rows Deleted.");
                    pstmt.close();

                }
            }
        }
        else{
            System.out.println("Wrong Option");
            System.exit(0);
        }
    }

    public static void main(String[] args) throws SQLException {
        int option = 1;
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/reales";
        String user = "root";
        String password = "root";

        conn = ConnectionUtil.getConnection(url, user, password);
        System.out.println("Connection to MySQL database established!");

        do
        {
            System.out.println("1.Insert\n2.Search\n3.Update\n4.Delete\n0.Exit");
            option=sc.nextInt();
            switch (option)
            {
                case 1://code for insert
                    System.out.println("Insert");
                    insert();
                    break;
                case 2:System.out.println("Search");
                    search();
                    break;
                case 3:System.out.println("Update");
                    break;
                case 4:delete();
                    System.out.println("Delete");

                    break;
                case 0: System.exit(0);
                default:
                    System.out.println("Wrong option");
            }

        }while(option != 0);


    }
}
