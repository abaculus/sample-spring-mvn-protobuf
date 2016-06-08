package se.panok.spike.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import se.panok.spike.protobuf.model.CustomerProtos;
import se.panok.spike.protobuf.model.CustomerProtos.Customer;
import se.panok.spike.protobuf.repo.CustomerRepository;

@Configuration
public class CustomConfig {
	
	@Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

	@Bean
    CustomerRepository customerRepository() {
        Map<Integer, Customer> customers = new ConcurrentHashMap<>();
        // populate with some dummy data
        Arrays.asList(
                customer(1, "Chris", "Richardson", Arrays.asList("crichardson@email.com")),
                customer(2, "Josh", "Long", Arrays.asList("jlong@email.com")),
                customer(3, "Matt", "Stine", Arrays.asList("mstine@email.com")),
                customer(4, "Russ", "Miles", Arrays.asList("rmiles@email.com"))
        ).forEach(c -> customers.put(c.getId(), c));

        // our lambda just gets forwarded to Map#get(Integer)
        return customers::get;
    }
	
	private CustomerProtos.Customer customer(int id, String f, String l, Collection<String> emails) {
        Collection<CustomerProtos.Customer.EmailAddress> emailAddresses =
                emails.stream().map(e -> CustomerProtos.Customer.EmailAddress.newBuilder()
                        .setType(CustomerProtos.Customer.EmailType.PROFESSIONAL)
                        .setEmail(e).build())
                        .collect(Collectors.toList());

        return CustomerProtos.Customer.newBuilder()
                .setFirstName(f)
                .setLastName(l)
                .setId(id)
                .addAllEmail(emailAddresses)
                .build();
    }
}
