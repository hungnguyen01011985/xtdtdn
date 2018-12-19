package vn.toancauxanh.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.toancauxanh.rest.repository.KeyApiRepository;

@Service
public class KeyApiService {

	private KeyApiRepository keyApiRepository;
	
	@Autowired
	public KeyApiService(KeyApiRepository keyApiRepository) {
		this.keyApiRepository = keyApiRepository;
	}
	
	public boolean checkKey(String keyApi) {
		return keyApiRepository.getByKey(keyApi) == null ? false : true;
	}
}
