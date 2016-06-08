package se.panok.spike.protobuf.repo;

import se.panok.spike.protobuf.model.CustomerProtos.Customer;

public interface CustomerRepository {

	Customer findById(int id);
}
