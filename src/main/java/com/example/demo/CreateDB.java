package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDB {

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1", "sa", "sa");
    }

    public static void createData() {
        System.out.println("beforeAll");
        try {
            executeUpdate("CREATE TABLE public.client (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"name\" varchar NULL,\n"
                    + "\t\"discount\" int4 NULL,\n"
                    + "\tCONSTRAINT client_pk PRIMARY KEY (id)\n"
                    + ");");
            executeUpdate("CREATE TABLE public.reservation (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"price\" int4 NULL,\n"
                    + "\t\"startTime\" varchar NULL,\n"
                    + "\tCONSTRAINT reservation_pk PRIMARY KEY (id)\n"
                    + ");");
            executeUpdate("CREATE TABLE public.car (\n"
                    + "\tid varchar NOT NULL,\n"
                    + "\t\"model\" varchar NULL,\n"
                    + "\t\"clientId\" int4 NULL,\n"
                    + "\t\"reservationId\" int4 NULL,\n"
                    + "\tCONSTRAINT car_pk PRIMARY KEY (id),\n"
                    + "\tCONSTRAINT car_fk FOREIGN KEY (\"clientId\") REFERENCES public.client(id),\n"
                    + "\tCONSTRAINT car_fk_1 FOREIGN KEY (\"reservationId\") REFERENCES public.reservation(id)\n"
                    + ");");
            executeUpdate("CREATE TABLE public.parking (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"carId\" varchar NULL,\n"
                    + "\t\"available\" varchar NOT NULL,\n"
                    + "\t\"overrun\" varchar NOT NULL,\n"
                    + "\tCONSTRAINT parking_pk PRIMARY KEY (id),\n"
                    + "\tCONSTRAINT parking_fk FOREIGN KEY (\"carId\") REFERENCES public.car(id)\n"
                    + ");");
            executeUpdate(""
                    + "INSERT INTO public.client(id, \"name\", \"discount\") VALUES (1, 'Richard', 0);\n"
                    + "INSERT INTO public.client(id, \"name\", \"discount\") VALUES (2, 'Maria', 10);\n"
                    + "INSERT INTO public.client(id, \"name\", \"discount\") VALUES (3, 'Lily', 0);\n"
                    + "INSERT INTO public.client(id, \"name\", \"discount\") VALUES (4, 'Tommy', 5);\n");
            executeUpdate(""
                    + "INSERT INTO public.reservation(id, \"price\", \"startTime\") VALUES (1, 100, '15:03:42');\n"
                    + "INSERT INTO public.reservation(id, \"price\", \"startTime\") VALUES (2, 5000, '10:45:00');\n"
                    + "INSERT INTO public.reservation(id, \"price\", \"startTime\") VALUES (3, 500, '21:00:05');\n"
                    + "INSERT INTO public.reservation(id, \"price\", \"startTime\") VALUES (4, 5000, '11:05:48');\n"
                    + "INSERT INTO public.reservation(id, \"price\", \"startTime\") VALUES (5, 1000, '09:59:08');\n");
            executeUpdate(""
                    + "INSERT INTO public.car(id, \"model\", \"clientId\", \"reservationId\") VALUES ('BR564L', 'BMW', 2, 2);\n"
                    + "INSERT INTO public.car(id, \"model\", \"clientId\", \"reservationId\") VALUES ('GTY87P', 'KIA', 1, 3);\n"
                    + "INSERT INTO public.car(id, \"model\", \"clientId\", \"reservationId\") VALUES ('BTT65H', 'Hyundai', 4, 1);\n"
                    + "INSERT INTO public.car(id, \"model\", \"clientId\", \"reservationId\") VALUES ('BTY09S', 'Toyota', 2, 5);\n"
                    + "INSERT INTO public.car(id, \"model\", \"clientId\", \"reservationId\") VALUES ('VRT55E', 'Toyota', 3, 4);\n");
            executeUpdate(""
                    + "INSERT INTO public.parking(id, \"carId\", \"available\", \"overrun\") VALUES (1, 'BTT65H', 'false', 'false');\n"
                    + "INSERT INTO public.parking(id, \"carId\", \"available\", \"overrun\") VALUES (2, NULL, 'true', 'false');\n"
                    + "INSERT INTO public.parking(id, \"carId\", \"available\", \"overrun\") VALUES (3, 'BR564L', 'false', 'false');\n"
                    + "INSERT INTO public.parking(id, \"carId\", \"available\", \"overrun\") VALUES (4, 'GTY87P', 'false', 'true');\n"
                    + "INSERT INTO public.parking(id, \"carId\", \"available\", \"overrun\") VALUES (5, NULL, 'true', 'false');\n"
                    + "INSERT INTO public.parking(id, \"carId\", \"available\", \"overrun\") VALUES (6, 'VRT55E', 'false', 'true');\n"
                    + "INSERT INTO public.parking(id, \"carId\", \"available\", \"overrun\") VALUES (7, 'BTY09S', 'false', 'false');\n");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        connection
                .createStatement()
                .executeUpdate(sql);
        connection.close();
    }
}

