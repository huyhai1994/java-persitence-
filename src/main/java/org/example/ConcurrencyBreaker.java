package org.example;

import config.DBConnection;

import java.sql.*;
import java.util.concurrent.*;

public class ConcurrencyBreaker {
    private static final int THREAD_COUNT = 2000;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        // Submit mixed read/write operations to be executed concurrently
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                try {
                    // Half threads do updates, half do reads
                    if (threadId % 2 == 0) {
                        performUpdate(threadId);
                    } else {
                        performSelect(threadId);
                    }
                } catch (Exception e) {
                    System.err.println("Thread " + threadId + " error: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Concurrency test completed");
    }

    private static void performUpdate(int threadId) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            // Disable auto-commit to create longer transactions
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE users SET name = ? WHERE id = ?")) {

                // Randomly select a user to update
                int userId = (threadId % 4) + 1;
                stmt.setString(1, "Updated by thread " + threadId);
                stmt.setInt(2, userId);

                System.out.println("Thread " + threadId + " updating user " + userId);

                // Sleep to increase chance of conflict
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 500));

                stmt.executeUpdate();

                // Random commit or rollback
                if (ThreadLocalRandom.current().nextBoolean()) {
                    conn.commit();
                    System.out.println("Thread " + threadId + " committed");
                } else {
                    conn.rollback();
                    System.out.println("Thread " + threadId + " rolled back");
                }
            } catch (InterruptedException e) {
                conn.rollback();
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void performSelect(int threadId) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            // Set random isolation level to create inconsistent reads
            int[] levels = {
                    Connection.TRANSACTION_READ_UNCOMMITTED,
                    Connection.TRANSACTION_READ_COMMITTED,
                    Connection.TRANSACTION_REPEATABLE_READ
            };
            conn.setTransactionIsolation(levels[threadId % levels.length]);

            String query = "SELECT id, name, email FROM users WHERE id <= 5";
            try (Statement stmt = conn.createStatement()) {
                System.out.println("Thread " + threadId + " reading users");

                // Execute query twice with delay between to catch inconsistencies
                ResultSet rs1 = stmt.executeQuery(query);
                printResults(rs1, "First read by thread " + threadId);

                Thread.sleep(ThreadLocalRandom.current().nextInt(50, 200));

                ResultSet rs2 = stmt.executeQuery(query);
                printResults(rs2, "Second read by thread " + threadId);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void printResults(ResultSet rs, String label) throws SQLException {
        System.out.println(label + ":");
        while (rs.next()) {
            System.out.printf("  User %d: %s, %s%n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"));
        }
    }
}