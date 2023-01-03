package rs.ac.uns.ftn.services.impl;

import rs.ac.uns.ftn.dataAccess.AutorskoDeloDataAccess;
import rs.ac.uns.ftn.dataAccess.Test;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import org.springframework.stereotype.Service;

@Service
public class AutorskoDeloIServicempl implements AutorskoDeloService{

	public void saveNewFile() {
		AutorskoDeloDataAccess da = new AutorskoDeloDataAccess();
		Test test = new Test();
		//test.testSaveFile(da);
	}

}
