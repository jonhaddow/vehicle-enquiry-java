package Record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Jon Haddow on 02/03/2016.
 */
public class Postgres {

    private final String host;
    private final String dbName;
    private final String user;
    private final String pass;

    private Connection conn;

    public Postgres(String host, String dbName, String user, String pass) {
        this.host = host;
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }

    /**
     * Establishes the connection
     * @return true if connection is made
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean connect() throws SQLException, ClassNotFoundException {
        if (host.isEmpty() || dbName.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            throw  new SQLException("Database credentials missing");
        }

        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection(
                this.host +this.dbName,
                this.user,this.pass);
        return true;
    }

    /**
     * Execute the sql query
     * @param query
     * @return
     * @throws SQLException
     */
    public ResultSet execQuery(String query) throws SQLException {
        return this.conn.createStatement().executeQuery(query);
    }

    /**
     * Inserts a new row into a table
     * @param table
     * @param values
     * @return
     * @throws SQLException
     */
    public int insert(String table, Map values) throws SQLException {

        StringBuilder columns = new StringBuilder();
        StringBuilder vals = new StringBuilder();

        for (Object colu : values.keySet()) {
            String col = (String) colu;
            columns.append(col).append(",");

            if (values.get(col) instanceof String) {
                vals.append("'").append(values.get(col)).append("', ");
            }
            else vals.append(values.get(col)).append(",");
        }

        //delete the final comma
        columns.setLength(columns.length()-1);
        vals.setLength(vals.length()-1);

        String query = String.format("INSERT INTO %s (%s) VALUES (%s)", table,
                columns.toString(), vals.toString());

        return this.conn.createStatement().executeUpdate(query);
    }
}
