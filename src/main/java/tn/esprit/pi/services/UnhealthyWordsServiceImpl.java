package tn.esprit.pi.services;

import tn.esprit.pi.entities.UnhealthyWord;
import tn.esprit.pi.repositories.IUnhealthyWordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnhealthyWordsServiceImpl implements IUnhealthyWordService {
	@Autowired 
	IUnhealthyWordRepository unhealthyWordRepository;     

    @Override
    public void removeWord(String word) {
        unhealthyWordRepository.deleteByWordIs(word);
    }

    @Override
    public boolean wordExists(String word) {
        return unhealthyWordRepository.existsByWord(word);
    }

    @Override
    public void addWord(UnhealthyWord u) {
            unhealthyWordRepository.save(u);
    }

    @Override
    public void deleteWord(String word) {
        unhealthyWordRepository.deleteByWordIs(word);
    }

    @Override
    public List<UnhealthyWord> getUnhealthyWordList() {
        return (List<UnhealthyWord>) unhealthyWordRepository.findAll();
    }

}