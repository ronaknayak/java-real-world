package com.ronak.javarealworld.solid.violations.dip;

/** Deliberately violates DIP: policy constructs and depends directly on infrastructure details. */
public final class CustomerRegistrationService {
    private final PostgreSqlCustomerRepository customerRepository = new PostgreSqlCustomerRepository();
    private final EmailProvider emailProvider = new EmailProvider();

    public void register(String customerId, String email) {
        customerRepository.insert(customerId, email);
        emailProvider.sendWelcomeEmail(email);
    }

    private static final class PostgreSqlCustomerRepository {
        void insert(String customerId, String email) {
            System.out.println("PostgreSQL insert for " + customerId);
        }
    }

    private static final class EmailProvider {
        void sendWelcomeEmail(String email) {
            System.out.println("Provider API call for " + email);
        }
    }
}
