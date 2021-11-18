package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pi.entities.ExchangeRequest;

public interface IExchangeRepository extends CrudRepository<ExchangeRequest, Integer> {

}
