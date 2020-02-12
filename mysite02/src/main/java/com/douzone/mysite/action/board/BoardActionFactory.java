package com.douzone.mysite.action.board;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "listform" : return new ListFormAction(); 
		case "writeform" : return new WriteFormAction();
		case "modifyform" : return new ModifyFormAction();
		case "viewform" : return new ViewFormAction();
		case "write" : return new WriteAction();
		}
		
		return null; 
	}

}
