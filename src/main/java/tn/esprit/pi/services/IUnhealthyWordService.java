package tn.esprit.pi.services;

import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.UnhealthyWord;

import java.util.List;

@Service
public interface IUnhealthyWordService {
    public void removeWord(String word);

    public boolean wordExists(String word);

    public void deleteWord(String word);

    public List<UnhealthyWord> getUnhealthyWordList();

	public void addWord(UnhealthyWord u);
}