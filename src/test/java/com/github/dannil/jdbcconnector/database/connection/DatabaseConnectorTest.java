package com.github.dannil.jdbcconnector.database.connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DatabaseConnectorTest {

    @Test
    public void constructor() {
        DatabaseConnector connector = new DatabaseConnector("testurl", "testuser", "testpass");

        assertEquals("testurl", connector.getUrl());
        assertEquals("testuser", connector.getUser());
        assertEquals("testpass", connector.getPassword());
    }

}
